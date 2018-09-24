package com.olam.score.product.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.olam.score.common.constants.Constants;
import com.olam.score.common.domain.DropDownModel;
import com.olam.score.common.domain.UserBean;
import com.olam.score.common.util.CommonUtil;
import com.olam.score.common.util.ScoreBaseException;
import com.olam.score.common.util.WebServiceCallUtil;
import com.olam.score.product.domain.MasterBlendInput;
import com.olam.score.product.domain.MasterBlendInputCertification;
import com.olam.score.product.domain.MasterBlendOutput;
import com.olam.score.product.domain.MasterBlendTemplate;
import com.olam.score.product.dto.BlendInputCertificationDto;
import com.olam.score.product.dto.BlendInputDto;
import com.olam.score.product.dto.BlendOutputDto;
import com.olam.score.product.dto.BlendTemplateDto;
import com.olam.score.product.repository.BlendInputCertRepository;
import com.olam.score.product.repository.BlendInputRepository;
import com.olam.score.product.repository.BlendOutputRepository;
import com.olam.score.product.repository.BlendTemplateRepository;
import com.olam.score.product.repository.BrandRepository;
import com.olam.score.product.repository.CertificationRepository;
import com.olam.score.product.repository.GradeRepository;
import com.olam.score.product.repository.OriginRepository;
import com.olam.score.product.repository.ProductRepository;

@Service
public class BlendService {
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	BlendTemplateRepository blendTemplateRepository;
	@Autowired
	BlendInputCertRepository blendInputCertRepository;
	@Autowired
	BlendInputRepository blendInputRepository;
	@Autowired
	BlendOutputRepository blendOutputRepository;
	@Autowired
	private WebServiceCallUtil serviceCall;
	@Autowired
	ProductRepository productRepository;
	@Autowired
	BrandRepository brandRepository;
	
	@Autowired
	GradeRepository gradeRepository;
	@Autowired
	OriginRepository originRepository;
	@Autowired
	CertificationRepository certificationRepository;
	private int chkValue=0;
	
	@Transactional
	public String create(BlendTemplateDto inputData) {
		String addStatus = Constants.BLEND_ADD_SUCESS;
		String action = inputData.getAction();
		if (action.equalsIgnoreCase(Constants.SAVE)) {
			
			inputData.setFkStatusId(returnStatusIdByName(Constants.DRAFT));
			log.info("===draft action to perform for create ===");
		
		} else if (action.equalsIgnoreCase(Constants.SUBMIT)) {
			inputData.setFkStatusId(returnStatusIdByName(Constants.ACTIVE));
			log.info("===save(active) action to perform for create ===");
			inputData.setStatusName(Constants.ACTIVE);
		}
		
		MasterBlendTemplate masterBlendTemplate = getMasterBlendTemplate(inputData,"create");
		
		if(inputData.getBlendOutputList()!=null)
		{
			masterBlendTemplate.setMasterBlendOutputAssoc(createAndreturnMasterBlendOutput(inputData.getBlendOutputList(),masterBlendTemplate));
		}
		if(inputData.getBlendInputList()!=null)
		{
			masterBlendTemplate.setMasterBlendInputAssoc(createAndreturnMasterBlendInput(inputData.getBlendInputList(),masterBlendTemplate));
		}
		if(inputData.getBlendInputCertificationList()!=null)
		{
			masterBlendTemplate.setMasterBlendInputCertificationAssoc(createAndreturnMasterBlendInputCerttification(inputData.getBlendInputCertificationList(),masterBlendTemplate));
		}
		validate(masterBlendTemplate, 0);
	    blendTemplateRepository.saveAndFlush(masterBlendTemplate);
		return addStatus;
	}
	@Transactional 
	private List<MasterBlendInputCertification> createAndreturnMasterBlendInputCerttification(
			List<BlendInputCertificationDto> blendInputCertificationList, MasterBlendTemplate masterBlendTemplate) {
		List<MasterBlendInputCertification> masterBlendInputCertificationList=new ArrayList<>();
		List<Integer> updatedList=new ArrayList<>();
		for(BlendInputCertificationDto blendInputCertificationDto:blendInputCertificationList)
		{
			MasterBlendInputCertification masterBlendInputCertification=new MasterBlendInputCertification();
			if(blendInputCertificationDto.getPkBlendInputCertificationId()!=null)
			{
				masterBlendInputCertification.setPkBlendInputCertificationId(blendInputCertificationDto.getPkBlendInputCertificationId());
				updatedList.add(blendInputCertificationDto.getPkBlendInputCertificationId());
			}
			masterBlendInputCertification.setCertificationPercentage(blendInputCertificationDto.getCertificationPercentage());
			masterBlendInputCertification.setFkBlendInputId(blendInputCertificationDto.getFkBlendInputId());
			if(blendInputCertificationDto.getFkProdCertId()!=null){
			masterBlendInputCertification.setFkProdCertId(certificationRepository.findOne(blendInputCertificationDto.getFkProdCertId()));}
			masterBlendInputCertification.setMasterBlendInputCertAssoc(masterBlendTemplate);
			masterBlendInputCertification.setFkStatusId(masterBlendTemplate.getFkStatusId());
			masterBlendInputCertificationList.add(masterBlendInputCertification);
			
			
		}
		if(!updatedList.isEmpty()) {
			blendInputCertRepository.deleteByMasterBlendInputCertAssocAndPkBlendInputCertificationIdNotIn(masterBlendTemplate, updatedList);
		}
		
		return masterBlendInputCertificationList;
	}
	@Transactional 
	private List<MasterBlendInput> createAndreturnMasterBlendInput(List<BlendInputDto> blendInputList, MasterBlendTemplate masterBlendTemplate) {
		List<MasterBlendInput> masterBlendInputList=new ArrayList<>();
		List<Integer> updatedList=new ArrayList<>();
		for(BlendInputDto blendInputDto:blendInputList)
		{
			MasterBlendInput masterBlendInput=new MasterBlendInput();
			if(blendInputDto.getPkBlendInputId()!=null){
				masterBlendInput.setPkBlendInputId(blendInputDto.getPkBlendInputId());
				updatedList.add(blendInputDto.getPkBlendInputId());
			}
			masterBlendInput.setCertificationPercentage(blendInputDto.getCertificationPercentage());
			if(blendInputDto.getFkGradeId()!=null){
			masterBlendInput.setFkGradeId(gradeRepository.findOne(blendInputDto.getFkGradeId()));}
			if(blendInputDto.getFkOriginId()!=null){
			masterBlendInput.setFkOriginId(originRepository.findOne(blendInputDto.getFkOriginId()));}
			if(blendInputDto.getFkProdCertId()!=null){
			masterBlendInput.setFkProdCertId(certificationRepository.findOne(blendInputDto.getFkProdCertId()));}
			if(blendInputDto.getFkProdId()!=null){
			masterBlendInput.setFkProdId(productRepository.findOne(blendInputDto.getFkProdId()));}
			masterBlendInput.setFkStatusId(masterBlendTemplate.getFkStatusId());
			masterBlendInput.setMasterBlendInputAssoc(masterBlendTemplate);
			masterBlendInput.setQuantityPercentage(blendInputDto.getQuantityPercentage());
			masterBlendInputList.add(masterBlendInput);
		}
		if(!updatedList.isEmpty()) {
			blendInputRepository.deleteByMasterBlendInputAssocAndPkBlendInputIdNotIn(masterBlendTemplate, updatedList);
		}
		return masterBlendInputList;
	}
	@Transactional 
	private List<MasterBlendOutput> createAndreturnMasterBlendOutput(List<BlendOutputDto> blendOutputList, MasterBlendTemplate masterBlendTemplate) {
		
		List<MasterBlendOutput> masterBlendOutputList=new ArrayList<>();
		List<Integer> updatedList=new ArrayList<>();
		for(BlendOutputDto blendOutputDto:blendOutputList)
		{
			MasterBlendOutput masterBlendOutput=new MasterBlendOutput();
			if(blendOutputDto.getPkBlendOutputId()!=null){
				masterBlendOutput.setPkBlendOutputId(blendOutputDto.getPkBlendOutputId());
				updatedList.add(blendOutputDto.getPkBlendOutputId());
			}
			masterBlendOutput.setBrandName(blendOutputDto.getBrandName());
			masterBlendOutput.setCertificationPercentage(blendOutputDto.getCertificationPercentage());
			
		
			masterBlendOutput.setFkProdId(productRepository.findOne(blendOutputDto.getFkProdId()));
			masterBlendOutput.setFkGradeId(gradeRepository.findOne(blendOutputDto.getFkGradeId()));
			masterBlendOutput.setFkOriginId(originRepository.findOne(blendOutputDto.getFkOriginId()));
			if(blendOutputDto.getFkProdCertId()!=null){
			masterBlendOutput.setFkProdCertId(certificationRepository.findOne(blendOutputDto.getFkProdCertId()));}
			masterBlendOutput.setFkStatusId(masterBlendTemplate.getFkStatusId());
			masterBlendOutput.setMasterBlendTemplateAssoc(masterBlendTemplate);
			masterBlendOutput.setQuantityPercentage(blendOutputDto.getQuantityPercentage());
			masterBlendOutput.setValueRatio(blendOutputDto.getAbilityToBearRatio());
			masterBlendOutputList.add(masterBlendOutput);
			
			
			
		}
		if(!updatedList.isEmpty()) {
			blendOutputRepository.deleteByMasterBlendTemplateAssocAndPkBlendOutputIdNotIn(masterBlendTemplate, updatedList);
		}
		return masterBlendOutputList;
	}
	@Transactional 
	private MasterBlendTemplate getMasterBlendTemplate(BlendTemplateDto inputData, String string) {
		MasterBlendTemplate masterBlendTemplate =new MasterBlendTemplate();
		if(inputData.getPkBlendTemplateId()!=null) {
			masterBlendTemplate.setPkBlendTemplateId(inputData.getPkBlendTemplateId());
			}
		masterBlendTemplate.setTemplateName(inputData.getTemplateName());
		masterBlendTemplate.setFkStatusId(inputData.getFkStatusId());
		masterBlendTemplate.setTemplateCode(inputData.getTemplateCode());
		masterBlendTemplate.setTemplateDesc(inputData.getTemplateDesc());
		return masterBlendTemplate;
	}
	

	
	@Transactional
	public List<BlendTemplateDto> readAll() {
		List<MasterBlendTemplate> masterBlendTemplateList = blendTemplateRepository.getAllBlendTemplate();
		return modelToDto(masterBlendTemplateList);
	}

	private List<BlendTemplateDto> modelToDto(List<MasterBlendTemplate> masterBlendTemplateList) {
		List<BlendTemplateDto> blendTemplateDtoList = new ArrayList<>();
        
		for (MasterBlendTemplate masterBlendTemplate : masterBlendTemplateList) {
			BlendTemplateDto blendTemplateDto = new BlendTemplateDto();
			blendTemplateDto.setTemplateName(masterBlendTemplate.getTemplateName());
			blendTemplateDto.setTemplateDesc(masterBlendTemplate.getTemplateDesc());
			blendTemplateDto.setTemplateCode(masterBlendTemplate.getTemplateCode());
			blendTemplateDto.setStatusName(returnStatusNameById(masterBlendTemplate.getFkStatusId()));
			blendTemplateDto.setFkStatusId(masterBlendTemplate.getFkStatusId());
			blendTemplateDto.setPkBlendTemplateId(masterBlendTemplate.getPkBlendTemplateId());
			
			blendTemplateDtoList.add(blendTemplateDto);
		}
		
		return blendTemplateDtoList;
	}
	
	private Sort sortByIdDsc() {
		return new Sort(Direction.DESC, Arrays.asList("createdDate","modifiedDate"));
	}
	@Transactional
	public List<BlendTemplateDto> readById(Integer blendId) {
		List<BlendTemplateDto> blendTemplateDtoList=new ArrayList<>();
		MasterBlendTemplate masterBlendTemplate=blendTemplateRepository.findOne(blendId);
		BlendTemplateDto blendTemplateDto=new BlendTemplateDto();
		blendTemplateDto.setTemplateName(masterBlendTemplate.getTemplateName());
		blendTemplateDto.setTemplateDesc(masterBlendTemplate.getTemplateDesc());
		blendTemplateDto.setTemplateCode(masterBlendTemplate.getTemplateCode());
		blendTemplateDto.setPkBlendTemplateId(masterBlendTemplate.getPkBlendTemplateId());
		blendTemplateDto.setFkStatusId(masterBlendTemplate.getFkStatusId());
		blendTemplateDto.setStatusName(returnStatusNameById(masterBlendTemplate.getFkStatusId()));
		blendTemplateDto.setBlendOutputList(returnBlendOutput(masterBlendTemplate.getMasterBlendOutputAssoc()));
		blendTemplateDto.setBlendInputList(returnBlendInput(masterBlendTemplate.getMasterBlendInputAssoc()));
		blendTemplateDto.setBlendInputCertificationList(returnBlendInputCertification(masterBlendTemplate.getMasterBlendInputCertificationAssoc()));
		blendTemplateDtoList.add(blendTemplateDto);
		return blendTemplateDtoList;
	}
	private List<BlendInputCertificationDto> returnBlendInputCertification(
			List<MasterBlendInputCertification> masterBlendInputCertificationAssoc) {
		List<BlendInputCertificationDto> blendInputCertificationDtoList=new ArrayList<>();
		for(MasterBlendInputCertification masterBlendInputCertification:masterBlendInputCertificationAssoc)
		{
			BlendInputCertificationDto blendInputCertificationDto=new BlendInputCertificationDto();
			blendInputCertificationDto.setCertificationPercentage(masterBlendInputCertification.getCertificationPercentage());
			if(masterBlendInputCertification.getFkProdCertId()!=null){
			blendInputCertificationDto.setFkProdCertId(masterBlendInputCertification.getFkProdCertId().getPkProdCertId());
			blendInputCertificationDto.setFkProdCertName(masterBlendInputCertification.getFkProdCertId().getProdCertName());}
			blendInputCertificationDto.setFkStatusId(masterBlendInputCertification.getFkStatusId());
			blendInputCertificationDto.setStatusName(returnStatusNameById(masterBlendInputCertification.getFkStatusId()));
			blendInputCertificationDto.setPkBlendInputCertificationId(masterBlendInputCertification.getPkBlendInputCertificationId());
			blendInputCertificationDtoList.add(blendInputCertificationDto);
		}
		return blendInputCertificationDtoList;
	}

	private List<BlendInputDto> returnBlendInput(List<MasterBlendInput> masterBlendInputAssoc) {
		List<BlendInputDto> blendInputDtoList=new ArrayList<>();
		for(MasterBlendInput masterBlendInput:masterBlendInputAssoc)
		{
			BlendInputDto blendInputDto=new BlendInputDto();
			blendInputDto.setCertificationPercentage(masterBlendInput.getCertificationPercentage());
			blendInputDto.setFkGradeId(masterBlendInput.getFkGradeId().getPkGradeId());
			blendInputDto.setFkGradeName(masterBlendInput.getFkGradeId().getGradeName());
			blendInputDto.setFkOriginId(masterBlendInput.getFkOriginId().getPkOriginId());
			blendInputDto.setFkOriginName(masterBlendInput.getFkOriginId().getOriginName());
			if(masterBlendInput.getFkProdCertId()!=null){
			blendInputDto.setFkProdCertId(masterBlendInput.getFkProdCertId().getPkProdCertId());
			blendInputDto.setFkProdCertName(masterBlendInput.getFkProdCertId().getProdCertName());
		}
			blendInputDto.setFkProdId(masterBlendInput.getFkProdId().getPkProdId());
			blendInputDto.setFkProdName(masterBlendInput.getFkProdId().getProdName());
			blendInputDto.setFkStatusId(masterBlendInput.getFkStatusId());
			blendInputDto.setStatusName(returnStatusNameById(masterBlendInput.getFkStatusId()));
			blendInputDto.setPkBlendInputId(masterBlendInput.getPkBlendInputId());
			blendInputDto.setQuantityPercentage(masterBlendInput.getQuantityPercentage());
			blendInputDtoList.add(blendInputDto);
		}
		return blendInputDtoList;
	}

	private List<BlendOutputDto> returnBlendOutput(List<MasterBlendOutput> masterBlendOutputAssoc) {
		List<BlendOutputDto> blendOutputDtoList=new ArrayList<>();
		for(MasterBlendOutput masterBlendOutput:masterBlendOutputAssoc)
		{
			BlendOutputDto blendOutputDto=new BlendOutputDto();
			blendOutputDto.setAbilityToBearRatio(masterBlendOutput.getValueRatio());
			blendOutputDto.setCertificationPercentage(masterBlendOutput.getCertificationPercentage());
			blendOutputDto.setFkBlendTemplateId(masterBlendOutput.getPkBlendOutputId());
			blendOutputDto.setFkGradeId(masterBlendOutput.getFkGradeId().getPkGradeId());
			blendOutputDto.setFkGradeName(masterBlendOutput.getFkGradeId().getGradeName());
			blendOutputDto.setFkOriginId(masterBlendOutput.getFkOriginId().getPkOriginId());
			blendOutputDto.setFkOriginName(masterBlendOutput.getFkOriginId().getOriginName());
			blendOutputDto.setFkProdCertId(masterBlendOutput.getFkProdCertId().getPkProdCertId());
			blendOutputDto.setFkProdCertName(masterBlendOutput.getFkProdCertId().getProdCertName());
			blendOutputDto.setFkProdId(masterBlendOutput.getFkProdId().getPkProdId());
			blendOutputDto.setFkProdName(masterBlendOutput.getFkProdId().getProdName());
			blendOutputDto.setFkStatusId(masterBlendOutput.getFkStatusId());
			blendOutputDto.setStatusName(returnStatusNameById(masterBlendOutput.getFkStatusId()));
			blendOutputDto.setPkBlendOutputId(masterBlendOutput.getPkBlendOutputId());
			blendOutputDto.setQuantityPercentage(masterBlendOutput.getQuantityPercentage());
			blendOutputDtoList.add(blendOutputDto);
			
		}
		return blendOutputDtoList;
	}

	public int returnStatusIdByName(String name)
	{
		Map<Integer, String> statusMap = serviceCall.getAllStatus();
		Optional<Integer> statusId = statusMap.entrySet().stream()
				.filter(e -> e.getValue().equals(name)).map(Map.Entry::getKey).findFirst();
		if (statusId.isPresent()) {
			log.info("===Got Status Id in Status map ===");
			return statusId.get();
		}
		return 0;
	}
	public String returnStatusNameById(int id)
	{
		String toReturn;
		Map<Integer, String> statusMap = serviceCall.getAllStatus();
		toReturn=statusMap.get(id);
		return toReturn;
	}
	
	public void deleteBlendTemplate(List<Map<String, Integer>> body) {
		log.info("In blendservice class delete");
		Map<Integer, String> statusMap = serviceCall.getAllStatus();

		Optional<Integer> statusId1 = statusMap.entrySet().stream().filter(e -> e.getValue().equals(Constants.INACTIVE))
				.map(Map.Entry::getKey).findFirst();
		Integer inactiveStatusId = null;
		if (statusId1.isPresent()) {
			inactiveStatusId = statusId1.get();
		}

		Optional<Integer> statusId2 = statusMap.entrySet().stream().filter(e -> e.getValue().equals(Constants.ACTIVE))
				.map(Map.Entry::getKey).findFirst();
		Integer activeStatusId = null;
		if (statusId2.isPresent()) {
			activeStatusId = statusId2.get();
		}

		for (Map<String, Integer> map : body) {

			MasterBlendTemplate masterBlendTemplate;
			masterBlendTemplate = blendTemplateRepository.findOne(map.get("pkBlendTemplateId"));

			if (masterBlendTemplate.getFkStatusId() != null) {
				String currentStatusName;

				currentStatusName = statusMap.get(masterBlendTemplate.getFkStatusId());
				log.info("Curerent Status Name : ");
				log.info(currentStatusName);
				if (currentStatusName.equals(Constants.ACTIVE)) {
					masterBlendTemplate.setFkStatusId(inactiveStatusId);
					blendTemplateRepository.saveAndFlush(masterBlendTemplate);

				} else if (currentStatusName.equals(Constants.INACTIVE)) {

					masterBlendTemplate.setFkStatusId(activeStatusId);
					blendTemplateRepository.saveAndFlush(masterBlendTemplate);
				} else if (currentStatusName.equals(Constants.DRAFT)) {
					blendTemplateRepository.delete(masterBlendTemplate);
				}

			}

		}

	}
	
	@Transactional
	public String delete(Integer blendId) {

		MasterBlendTemplate masterBlendTemplate=blendTemplateRepository.findOne(blendId);
		int status=masterBlendTemplate.getFkStatusId();
		String statusname=returnStatusNameById(status);
		String response = null;
		if (Constants.DRAFT.equalsIgnoreCase(statusname)) {
			response = Constants.DELETE_SUCCESS;
			blendTemplateRepository.delete(blendId);
		} else if (Constants.ACTIVE.equalsIgnoreCase(statusname)) {
			masterBlendTemplate.setFkStatusId(returnStatusIdByName(Constants.INACTIVE));
			blendTemplateRepository.save(masterBlendTemplate);
			response = Constants.UPDATE_SUCCESS;
		} else if (Constants.INACTIVE.equalsIgnoreCase(statusname)) {
			masterBlendTemplate.setFkStatusId(returnStatusIdByName(Constants.ACTIVE));
			blendTemplateRepository.save(masterBlendTemplate);
			response = Constants.UPDATE_SUCCESS;
		}
		return response;
	}
	@Transactional 
	public String update(BlendTemplateDto inputData) {
		

		String addStatus = Constants.UPDATE_SUCCESS;
		
			if(inputData.getAction()!=null){
			String action = inputData.getAction();
			
			if (action.equalsIgnoreCase(Constants.SAVE)) {
				log.info("===draft action to perform for create ===");
				inputData.setFkStatusId(returnStatusIdByName(Constants.DRAFT));
			} else if (action.equalsIgnoreCase(Constants.SUBMIT)) {
				log.info("===save(active) action to perform for create ===");
				inputData.setFkStatusId(returnStatusIdByName(Constants.ACTIVE));
			}else if (action.equalsIgnoreCase(Constants.DEACTIVE)) {
				log.info("===save(Deactive) action to perform for create ===");
				inputData.setFkStatusId(returnStatusIdByName(Constants.INACTIVE));
			}
			else if (action.equalsIgnoreCase(Constants.UPDATE)) {
				log.info("===save(activate) action to perform for create ===");
				inputData.setFkStatusId(returnStatusIdByName(Constants.ACTIVE));
			}
			}
			else
			{
				inputData.setFkStatusId(returnStatusIdByName(Constants.ACTIVE));
			}
			MasterBlendTemplate masterBlendTemplate=getMasterBlendTemplate(inputData,"create");
			if(!(inputData.getBlendOutputList().isEmpty()))
			{
				masterBlendTemplate.setMasterBlendOutputAssoc(createAndreturnMasterBlendOutput(inputData.getBlendOutputList(),masterBlendTemplate));
			}
			else
			{
				blendOutputRepository.deleteByMasterBlendTemplateAssoc(masterBlendTemplate);
			}
				
			if(!(inputData.getBlendInputList().isEmpty()))
			{
				masterBlendTemplate.setMasterBlendInputAssoc(createAndreturnMasterBlendInput(inputData.getBlendInputList(),masterBlendTemplate));
			}
			else
			{
				blendInputRepository.deleteByMasterBlendInputAssoc(masterBlendTemplate);
			}
			if(!(inputData.getBlendInputCertificationList().isEmpty()))
			{
				masterBlendTemplate.setMasterBlendInputCertificationAssoc(createAndreturnMasterBlendInputCerttification(inputData.getBlendInputCertificationList(),masterBlendTemplate));
			}
			else
			{
				List<MasterBlendInputCertification> masterBlendInputCertificationList =blendInputCertRepository.getcertid(masterBlendTemplate);
				for(MasterBlendInputCertification masterBlendInputCertification:masterBlendInputCertificationList){
					blendInputCertRepository.delete(masterBlendInputCertification.getPkBlendInputCertificationId());
				}
				
			}
			validateUpdate(masterBlendTemplate,0);
		blendTemplateRepository.saveAndFlush(masterBlendTemplate);
		return addStatus;
	}
	
	
	
private void validate(MasterBlendTemplate masterBlendTemplate,Integer id){
	UserBean userBean = new UserBean();
	Locale locale;
	locale=userBean.getLocale();
	if(locale==null){
		locale=Locale.getDefault();
	}
	String message=CommonUtil.getTranslationMessage(locale, "error_duplicate");
		if(blendTemplateRepository.uniqueBlendTemplateCode(masterBlendTemplate.getTemplateCode()) > chkValue && blendTemplateRepository.uniqueBlendTemplateName(masterBlendTemplate.getTemplateName()) > chkValue){
			message=message.replace("$", masterBlendTemplate.getTemplateCode() +" Code"+ " and " +masterBlendTemplate.getTemplateName()+" Name");
			throw new ScoreBaseException(id, message, HttpStatus.CONFLICT);
		}
		if (blendTemplateRepository.uniqueBlendTemplateCode(masterBlendTemplate.getTemplateCode()) > chkValue) {
			message=message.replace("$", masterBlendTemplate.getTemplateCode()+" Code");
			throw new ScoreBaseException(0, message, HttpStatus.CONFLICT);
		}
		if (blendTemplateRepository.uniqueBlendTemplateName(masterBlendTemplate.getTemplateName()) > chkValue) {
			message=message.replace("$", masterBlendTemplate.getTemplateName()+" Name");
			throw new ScoreBaseException(0, message, HttpStatus.CONFLICT);
		}
		
	}

private void validateUpdate(MasterBlendTemplate masterBlendTemplate,Integer id){
	UserBean userBean = new UserBean();
	Locale locale;
	locale=userBean.getLocale();
	if(locale==null){
		locale=Locale.getDefault();
	}
	String message=CommonUtil.getTranslationMessage(locale, "error_duplicate");
	
	if(blendTemplateRepository.getByTemplateCodeAndPkBlendTemplateIdNotIn( masterBlendTemplate.getTemplateCode(),  masterBlendTemplate.getPkBlendTemplateId()).size()>chkValue && blendTemplateRepository.getByTemplateNameAndPkBlendTemplateIdNotIn(masterBlendTemplate.getTemplateName(), masterBlendTemplate.getPkBlendTemplateId()).size()>chkValue)
	{	message=message.replace("$", masterBlendTemplate.getTemplateCode() +" Code"+ " and " +masterBlendTemplate.getTemplateName()+" Name");
			throw new ScoreBaseException(id, message, HttpStatus.CONFLICT);
		}
		if (blendTemplateRepository.getByTemplateCodeAndPkBlendTemplateIdNotIn( masterBlendTemplate.getTemplateCode(),  masterBlendTemplate.getPkBlendTemplateId()).size() > chkValue) {
			message=message.replace("$", masterBlendTemplate.getTemplateCode()+" Code");
			throw new ScoreBaseException(0, message, HttpStatus.CONFLICT);
		}
		if (blendTemplateRepository.getByTemplateNameAndPkBlendTemplateIdNotIn(masterBlendTemplate.getTemplateName(), masterBlendTemplate.getPkBlendTemplateId()).size() > chkValue) {
			message=message.replace("$", masterBlendTemplate.getTemplateName()+" Name");
			throw new ScoreBaseException(0, message, HttpStatus.CONFLICT);
		}
		
	}

public List<DropDownModel> getDropDownValues(Integer prodId) {
	
	List valueList=blendTemplateRepository.getDropDownValues(prodId);
	List<DropDownModel> dropDownModelList=new ArrayList<>();
	
	for (int i = 0; i < valueList.size(); i++) {
		DropDownModel dropDownModel=new DropDownModel();
		dropDownModel.setValue(String.valueOf(valueList.get(i)));
		dropDownModelList.add(dropDownModel);
	}
	return dropDownModelList;
}

public List<String> autoSuggestion(BlendTemplateDto dto) {
	List<String> toReturn = new ArrayList<>();
	List<MasterBlendTemplate> blendTemplateDtoList;
	Pageable pageable = new PageRequest(0, 20);
	if (Constants.NAME.equalsIgnoreCase(dto.getToValidate())) {
		blendTemplateDtoList = blendTemplateRepository
				.findByTemplateNameContaining(dto.getTemplateName(), pageable);
		for (MasterBlendTemplate masterBlendTemplate : blendTemplateDtoList) {
			toReturn.add(masterBlendTemplate.getTemplateName());
		}
	} else if (Constants.CODE.equalsIgnoreCase(dto.getToValidate())) {
		blendTemplateDtoList = blendTemplateRepository
				.findByTemplateCodeContaining(dto.getTemplateCode(), pageable);
		for (MasterBlendTemplate masterBlendTemplate : blendTemplateDtoList) {
			toReturn.add(masterBlendTemplate.getTemplateCode());
		}
	}
	return toReturn;
}
/*public List<DropDownModel> getDropDownValuesForGrade(Integer prodId, Integer origionId) {
	List<Map<Integer, String>> valueList=blendTemplateRepository.getDropDownValuesForGrade(prodId,origionId);
	List<DropDownModel> dropDownModelList=new ArrayList<>();
	//DropDownModel dropDownModel=new DropDownModel();
	
	
	 for (int i = 0 ; i<=valueList.size() ; i++) {
         Map<Integer, String> myMap = valueList.get(i);
         System.out.println("Data For Map" + i);
         for (Entry<Integer, String> entrySet : myMap.entrySet()) {
        	 DropDownModel dropDownModel=new DropDownModel();
             System.out.println("Key = " + entrySet.getKey() + " , Value = " + entrySet.getValue());
             dropDownModel.setValue(String.valueOf( entrySet.getKey()));
         	         	dropDownModel.setCode(entrySet.getValue());
         	dropDownModelList.add(dropDownModel);
         }
     }

	//Get the set
	for (int i = 0; i < valueList.size(); i++) {
	Set set6 = (Set) valueMap.entrySet();
	//Create iterator on Set 
	Iterator iterator6 = set6.iterator();
	while (iterator6.hasNext()) {
	Map.Entry mapEntry = (Map.Entry) iterator6.next();
	
	dropDownModel.setValue(String.valueOf((int) mapEntry.getKey()));
	String value = (String) mapEntry.getValue();
	dropDownModel.setCode(value);
	dropDownModelList.add(dropDownModel);
	}
	return dropDownModelList;
}*/


}

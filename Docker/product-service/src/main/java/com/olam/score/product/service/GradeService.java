package com.olam.score.product.service;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.olam.score.common.constants.Constants;
import com.olam.score.common.domain.UserBean;
import com.olam.score.common.dto.PageDto;
import com.olam.score.common.dto.ViewDto;
import com.olam.score.common.util.CommonUtil;
import com.olam.score.common.util.ErrorMessageUtil;
import com.olam.score.common.util.ListViewUtil;
import com.olam.score.common.util.Mapping;
import com.olam.score.common.util.ScoreBaseException;
import com.olam.score.common.util.ScoreBaseExceptionResponse;
import com.olam.score.common.util.WebServiceCallUtil;
import com.olam.score.product.controller.GradeController;
import com.olam.score.product.domain.MasterGrade;
import com.olam.score.product.domain.MasterGradeExternalMapping;
import com.olam.score.product.domain.MasterGradeIntlCode;
import com.olam.score.product.domain.MasterGradeOriginAssoc;
import com.olam.score.product.domain.MasterProcessSubTypeRef;
import com.olam.score.product.domain.MasterProduct;
import com.olam.score.product.domain.MasterProductProcess;
import com.olam.score.product.domain.MasterProductProcessSub;
import com.olam.score.product.dto.ExternalSystemDto;
import com.olam.score.product.dto.GradeDto;
import com.olam.score.product.dto.IntlGradeDto;
import com.olam.score.product.dto.OriginGradeDto;
import com.olam.score.product.repository.GradeExternalMappingRepository;
import com.olam.score.product.repository.GradeGroupingRefRepository;
import com.olam.score.product.repository.GradeIntlCodeRepository;
import com.olam.score.product.repository.GradeOriginAssocRepository;
import com.olam.score.product.repository.GradeRepository;
import com.olam.score.product.repository.IntlCodeTypeRefRepository;
import com.olam.score.product.repository.OriginRepository;
import com.olam.score.product.repository.ProcessSubTypeRefRepository;
import com.olam.score.product.repository.ProductIcoIndexRepository;
import com.olam.score.product.repository.ProductRepository;
import com.olam.score.product.util.GradeSpecificationsBuilder;
@Service
public class GradeService {
	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private GradeRepository repository;

	@Autowired
	private GradeOriginAssocRepository gradeOriginAssocRepository;

	@Autowired
	private ProcessSubTypeRefRepository processSubTypeRefRepository;
	@Autowired
	private IntlCodeTypeRefRepository codeTypeRefRepository;
	@Autowired
	private GradeIntlCodeRepository gradeIntlCodeRepository;
	@Autowired
	private ListViewUtil listview;
	@Autowired
	private GradeGroupingRefRepository gradeGroupingRefRepository;
	@Autowired
	private OriginRepository originRepository;
	@Autowired
	private WebServiceCallUtil webServiceCall;
	@Autowired
	private ProductRepository prodRepository;
	@Autowired
	private ProductIcoIndexRepository prodIcoRepository;
	@Autowired 
	private GradeExternalMappingRepository gradeExtRepository;

	@Value("${product.name}")
	private String productName;
	@Value("${reference.name}")
	private String referenceName;
	@Value("${finance.name}")
	private String financeName;

	@Transactional
	public GradeDto readById(Integer gradeId) {

		MasterGrade grade = repository.findOne(gradeId);
		List<MasterGradeOriginAssoc> gradeOriginAssocList = gradeOriginAssocRepository.findAll();
		Map<Integer, String> statusMap = webServiceCall.getAllStatus();
		
		List<Map<Object, Object>> brands = webServiceCall.getInternalServiceData(productName, "/product/brand/viewBrand");
		List<MasterProcessSubTypeRef> subTypeRef = processSubTypeRefRepository.findAll();
		
		List<Map<Object, Object>> uomList = webServiceCall.getInternalServiceData(referenceName, "/reference/uomservice/uom");
		List<Map<Object, Object>> currencyList = webServiceCall.getInternalServiceData(referenceName, "/reference/currencyservice/currency");		
		GradeDto gradeDto = grade.gradeModelToDto(gradeOriginAssocList, statusMap, brands, subTypeRef, uomList,
				currencyList);
		gradeDto.setExternalSystemList(generateExternalSystem(grade));
		return gradeDto;
	}

	@Transactional
	public List<GradeDto> readAll(Integer prodId, String gradeIsRaw) {
		ViewDto view =getViewDetails(GradeController.class);
		List<GradeDto> newList = new ArrayList<>();
		List<MasterGrade> allGrades=null;
		List<String> filterList = view.getFiltersArray();
		GradeSpecificationsBuilder builder = new GradeSpecificationsBuilder();
		for (int filterSize = 0; filterSize < filterList.size(); filterSize++) {
			builder.with(filterList.get(filterSize).substring(0, filterList.get(filterSize).indexOf("=")),
					filterList.get(filterSize).substring(filterList.get(filterSize).indexOf("=") + 1));
		}
		Specification<MasterGrade> spec = builder.build();
		Pageable pageRequest = ListViewUtil.createPageRequest(view);
		log.info("===Pageable object created from view object===");
		if(prodId!= null && gradeIsRaw!=null){
		allGrades = repository.findRawOrFinishedGrade(prodId,gradeIsRaw);
		}else{
		 allGrades = repository.findAll(spec, pageRequest).getContent();
		}
		List<MasterGradeOriginAssoc> gradeOriginAssocList = gradeOriginAssocRepository.findAll();
		Map<Integer, String> statusMap = webServiceCall.getAllStatus();
		List<Map<Object, Object>> brands = webServiceCall.getInternalServiceData(productName, "/product/brand/viewBrand");
		List<MasterProcessSubTypeRef> subTypeRef = processSubTypeRefRepository.findAll();
		
		List<Map<Object, Object>> uomList = webServiceCall.getInternalServiceData(referenceName, "/reference/uomservice/uom");
		List<Map<Object, Object>> currencyList = webServiceCall.getInternalServiceData(referenceName, "/reference/currencyservice/currency");
		
		for (MasterGrade grade : allGrades) {
			GradeDto gradeDto = grade.gradeModelToDto(gradeOriginAssocList, statusMap, brands, subTypeRef, uomList,
					currencyList);
			
			newList.add(gradeDto);
		}
		return newList;
	}


	@Transactional
	public GradeDto create(GradeDto gradeDto) {
		
		MasterGrade masterGrade=null;
		MasterGrade masterGradeName=null;
		MasterGrade masterGradeCode=null;
		Map<Integer, String> statusMap = webServiceCall.getAllStatus();
		String action =gradeDto.getAction();
		if (action.equalsIgnoreCase(Constants.DRAFT)) {
			log.info("===draft action to perform for create ===");
			gradeDto.setStatusName(Constants.DRAFT);
		}else if (action.equalsIgnoreCase(Constants.SAVE)) {
			log.info("===save(active) action to perform for create ===");
			gradeDto.setStatusName(Constants.ACTIVE);
		}
		masterGradeCode=repository.findByCode(gradeDto.getGradeCode());
		masterGradeName=repository.findByName(gradeDto.getGradeName());
		List<ScoreBaseExceptionResponse> errorBeans = new ArrayList<>();
		if (masterGradeCode != null || masterGradeName != null) {
			if (masterGradeCode != null) {
				log.error("===Duplicate Grade Code found in Database===");
				ErrorMessageUtil.errorHandler(new Object[] { gradeDto.getGradeCode() }, errorBeans,
						Constants.ERROR_DUPLICATE_GRADE_CODE, Constants.ERROR_TYPE_DUPLICATE_CODE);
			}
			if (masterGradeName != null) {
				log.error("===Duplicate Grade Name found in Database===");
				ErrorMessageUtil.errorHandler(new Object[] { gradeDto.getGradeName() }, errorBeans,
						Constants.ERROR_DUPLICATE_GRADE_NAME, Constants.ERROR_TYPE_DUPLICATE_NAME);
			}
			throw new ScoreBaseException(errorBeans, HttpStatus.CONFLICT);
		} else {
			masterGrade=repository.saveAndFlush(generateGradeEntity(gradeDto,"create",statusMap));
		}
		
		List<MasterGradeOriginAssoc> gradeOriginAssocList = gradeOriginAssocRepository.findAll();
		List<Map<Object, Object>> brands = webServiceCall.getInternalServiceData(productName, "/product/brand/viewBrand");
		List<MasterProcessSubTypeRef> subTypeRef = processSubTypeRefRepository.findAll();
		
		List<Map<Object, Object>> uomList = webServiceCall.getInternalServiceData(referenceName, "/reference/uomservice/uom");
		List<Map<Object, Object>> currencyList = webServiceCall.getInternalServiceData(referenceName, "/reference/currencyservice/currency");
		GradeDto dto = masterGrade.gradeModelToDto(gradeOriginAssocList, statusMap, brands, subTypeRef, uomList,
				currencyList);
		gradeDto.setExternalSystemList(generateExternalSystem(masterGrade));
		return dto;
	}

	private MasterGrade generateGradeEntity(GradeDto gradeDto, String action, Map<Integer, String> statusMap) {
		
		MasterGrade masterGrade=new MasterGrade();
		if ("create".equalsIgnoreCase(action)) {
			masterGrade.setCreatedBy("Test");
			masterGrade.setCreatedDate(new Timestamp(System.currentTimeMillis()));
			masterGrade.setModifiedBy("Test");
			masterGrade.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		}else if("update".equalsIgnoreCase(action)){
			masterGrade.setPkGradeId(gradeDto.getGradeId());
			masterGrade.setModifiedBy("Test");
			masterGrade.setModifiedDate(new Timestamp(System.currentTimeMillis()));

		}
		Optional<Integer> statusId = statusMap.entrySet().stream()
				.filter(e -> e.getValue().equals(gradeDto.getStatusName())).map(Map.Entry::getKey).findFirst();
		if (statusId.isPresent()) {
			log.info("===Got Status Id in Status map ===");
			masterGrade.setFkStatusId(statusId.get());
		}
		masterGrade.setGradeCode(gradeDto.getGradeCode());
		masterGrade.setGradeFullname(gradeDto.getGradeFullName());
		if(gradeDto.getHedgeRatio()!=null){
		masterGrade.setGradeHedgeRatio(gradeDto.getHedgeRatio().toString());
		}else{
			masterGrade.setGradeHedgeRatio(Constants.HEDGE_RATIO);
		}
		masterGrade.setGradeIsBase(gradeDto.getIsBase());
		if("N".equalsIgnoreCase(gradeDto.getIsBase())){
			masterGrade.setFkParentGradeId(repository.findOne(gradeDto.getBaseGradeId()));
		}
		MasterProduct masterProduct=prodRepository.findOne(gradeDto.getProduct().getProdId());
		masterGrade.setFkProdId(masterProduct);
		if(gradeDto.getIcoIndexId()!=null){
		masterGrade.setFkProductIcoIndex(prodIcoRepository.findOne(gradeDto.getIcoIndexId()));
		}
		masterGrade.setPremDisc(gradeDto.getPremiumOrDiscount());
		masterGrade.setPreDiscCurrencyId(gradeDto.getCurrencyId());
		masterGrade.setGradeIsBlended(gradeDto.getIsBlended());
		masterGrade.setGradeIsBrand(gradeDto.getIsBrand());
		masterGrade.setGradeIsRaw(gradeDto.getGradeIsRaw());
		masterGrade.setGradeIsTenderable(gradeDto.getIsTenderable());
		masterGrade.setGradeName(gradeDto.getGradeName());
		if(gradeDto.getBrand()!=null &&(!"".equals(gradeDto.getBrand().getBrandId())) && gradeDto.getBrand().getBrandId()!=null){
		masterGrade.setFkBrandId(Integer.parseInt(gradeDto.getBrand().getBrandId()));
		}
		masterGrade.setFkDefaultGradeUomId(gradeDto.getUomId());
		if(gradeDto.getGradeGroupId()!=null){
		masterGrade.setFkgradeGroupRef(gradeGroupingRefRepository.findOne(gradeDto.getGradeGroupId()));
		}
		List<MasterGradeIntlCode> masterGradeIntlCodes = new ArrayList<>();
		List<IntlGradeDto> intlGradeList=gradeDto.getIntlGradeList();
		for(IntlGradeDto intlGradeDto:intlGradeList){
			MasterGradeIntlCode gradeIntlCode=new MasterGradeIntlCode();
			gradeIntlCode.setIntlCode(intlGradeDto.getIntlCode());
			gradeIntlCode.setFkGradeId(masterGrade);
			if(intlGradeDto.getIntlId()!=null){
			gradeIntlCode.setFkIntlCodeTypeRefId(codeTypeRefRepository.findOne(intlGradeDto.getIntlId()));
			}
			gradeIntlCode.setDesc(intlGradeDto.getIntlDesc());
			masterGradeIntlCodes.add(gradeIntlCode);
		}
		masterGrade.setMasterGradeIntlCodeCollection(masterGradeIntlCodes);
		Collection<MasterProductProcessSub> productSubProcesses = new ArrayList<>();
		Collection<MasterProductProcess> productProcesses=masterProduct.getMasterProductProcessCollection();
		for (MasterProductProcess masterProductProcess : productProcesses) {
			if (gradeDto.getProcessingType()!=null && gradeDto.getProcessingType().getPkProductProcessId() != null && masterProductProcess
					.getPkProductProcessId().equals(gradeDto.getProcessingType().getPkProductProcessId())) {
				masterGrade.setFkProductProcessId(masterProductProcess);
				productSubProcesses = masterProductProcess.getMasterProductProcessSubCollection();
				break;
			}
		}
		for(MasterProductProcessSub masterProductProcessSub:productSubProcesses){
			if(gradeDto.getProcessingSubType()!=null && gradeDto.getProcessingSubType().getProcessSubTypeRefId()!=null && masterProductProcessSub.getFkProcessSubTypeRefId().equals(gradeDto.getProcessingSubType().getProcessSubTypeRefId())){
				masterGrade.setFkProductProcessSubId(masterProductProcessSub);
				break;
			}
		}
		List<OriginGradeDto> originList=gradeDto.getOriginList();
		Collection<MasterGradeOriginAssoc> masterGradeOriginAssocCollection = new ArrayList<>();
		for(OriginGradeDto originGrade:originList){
			MasterGradeOriginAssoc masterGradeOriginAssoc=new MasterGradeOriginAssoc();
			if(originGrade.getOriginId()!=null){
			masterGradeOriginAssoc.setFkOriginId(originRepository.findOne(originGrade.getOriginId()));
			}
			masterGradeOriginAssoc.setFkGradeId(masterGrade);
			masterGradeOriginAssocCollection.add(masterGradeOriginAssoc);
		}
		masterGrade.setMasterGradeOriginAssocCollection(masterGradeOriginAssocCollection);
		Collection<MasterGradeExternalMapping> gradeExternalMappings=new ArrayList<>();
		List<ExternalSystemDto> externalSystemList=gradeDto.getExternalSystemList();
		for (ExternalSystemDto externalSystemDto : externalSystemList) {
			MasterGradeExternalMapping gradeExternalMapping= new MasterGradeExternalMapping();
			gradeExternalMapping.setFkGradeId(masterGrade);
			gradeExternalMapping.setFkExternalSystemRefId(externalSystemDto.getExternalSystemId());
			gradeExternalMapping.setExternalCode(externalSystemDto.getAttribute1());
			gradeExternalMapping.setMappingType(externalSystemDto.getAttribute2());
			gradeExternalMappings.add(gradeExternalMapping);
		}
		masterGrade.setGradeExternalMappings(gradeExternalMappings);
		return masterGrade;
	}
	public ViewDto getViewDetails(Class<? extends Object> currentfeature) {
		String featureId = currentfeature.getAnnotation(Mapping.class).featureId();
		ViewDto viewdto = listview.getView(featureId);
		log.info("===viewdto from common has been generated for JSON===");
		PageDto pageDto = new PageDto();
		pageDto.setPageNumber(0);
		pageDto.setPageSize(Constants.PAGESIZE);
		pageDto.setTotalCount(repository.count());
		pageDto.setOperation("Client");// To be set from Database
		viewdto.setPage(pageDto);
		Class<GradeDto> uomDto = GradeDto.class;
		Field[] fields = uomDto.getDeclaredFields();
		Map<String, String> dataType = new HashMap<>();
		for (int i = 0; i < fields.length; i++) {
			String type = fields[i].getType().toString();
			type = type.substring(type.lastIndexOf('.') + 1, type.length());
			dataType.put(fields[i].getName(), type);
		}
		viewdto.setDataType(dataType);
		log.info("===dataType is set in view according to entity===");

		return viewdto;
	}

	public void delete(List<Map<String, Integer>> body) {
		log.info("In GradeService class deleteProduct().");
		Map<Integer, String> statusMap = webServiceCall.getAllStatus();

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

			MasterGrade grade;
			grade = repository.findOne(map.get("gradeId"));

			if (grade.getFkStatusId() != null) {
				String currentStatusName = null;

				currentStatusName = statusMap.get(grade.getFkStatusId());
				log.info("Curerent Status Name : ");
				log.info(currentStatusName);
				if (currentStatusName.equals(Constants.ACTIVE)) {
					grade.setFkStatusId(inactiveStatusId);
					repository.saveAndFlush(grade);

				} else if (currentStatusName.equals(Constants.INACTIVE)) {

					grade.setFkStatusId(activeStatusId);
					repository.saveAndFlush(grade);
				} else if (currentStatusName.equals(Constants.DRAFT)) {
					gradeOriginAssocRepository.deleteByFkGradeId(grade);
					repository.delete(grade);
				}

			}

		}

	}

	public GradeDto update(GradeDto gradeDto) {
		MasterGrade masterGrade;
		MasterGrade masterGradeName=null;
		MasterGrade masterGradeCode=null;
		masterGradeCode=repository.findByCode(gradeDto.getGradeCode());
		masterGradeName=repository.findByName(gradeDto.getGradeName());
		List<ScoreBaseExceptionResponse> errorBeans = new ArrayList<>();
		if ((masterGradeCode != null && !masterGradeCode.getPkGradeId().equals(gradeDto.getGradeId())) || (masterGradeName != null && !masterGradeName.getPkGradeId().equals(gradeDto.getGradeId()))) {
			if (masterGradeCode != null && !masterGradeCode.getPkGradeId().equals(gradeDto.getGradeId())) {
				log.error("===Duplicate Grade Code found in Database===");
				ErrorMessageUtil.errorHandler(new Object[] { gradeDto.getGradeCode() }, errorBeans,
						Constants.ERROR_DUPLICATE_GRADE_CODE, Constants.ERROR_TYPE_DUPLICATE_CODE);
			}
			if (masterGradeName != null && !masterGradeName.getPkGradeId().equals(gradeDto.getGradeId())) {
				log.error("===Duplicate Grade Name found in Database===");
				ErrorMessageUtil.errorHandler(new Object[] { gradeDto.getGradeName() }, errorBeans,
						Constants.ERROR_DUPLICATE_GRADE_NAME, Constants.ERROR_TYPE_DUPLICATE_NAME);
			}
			throw new ScoreBaseException(errorBeans, HttpStatus.CONFLICT);
		} else {
	
		deleteExtOriginIntlMapping(gradeDto);
		Map<Integer, String> statusMap = webServiceCall.getAllStatus();
		masterGrade=repository.saveAndFlush(generateGradeEntity(gradeDto,"update",statusMap));
		List<MasterGradeOriginAssoc> gradeOriginAssocList = gradeOriginAssocRepository.findAll();
		List<Map<Object, Object>> brands = webServiceCall.getInternalServiceData(productName, "/product/brand/viewBrand");
		List<MasterProcessSubTypeRef> subTypeRef = processSubTypeRefRepository.findAll();
		
		List<Map<Object, Object>> uomList = webServiceCall.getInternalServiceData(referenceName, "/reference/uomservice/uom");
		List<Map<Object, Object>> currencyList = webServiceCall.getInternalServiceData(referenceName, "/reference/currencyservice/currency");
		
		
		GradeDto dto = masterGrade.gradeModelToDto(gradeOriginAssocList, statusMap, brands, subTypeRef, uomList,
				currencyList);
		gradeDto.setExternalSystemList(generateExternalSystem(masterGrade));
		return dto;

		}


	}

	private void deleteExtOriginIntlMapping(GradeDto gradeDto) {
		MasterGrade grade=repository.findOne(gradeDto.getGradeId());
			gradeExtRepository.deleteByFkGradeId(grade);
			gradeIntlCodeRepository.deleteByFkGradeId(grade);
			gradeOriginAssocRepository.deleteByFkGradeId(grade);
	}

	private List<ExternalSystemDto> generateExternalSystem(MasterGrade masterGrade) {
		List<ExternalSystemDto> externalSystemDtos = new ArrayList<>();
		List<Map<Object, Object>> externalSystemList = webServiceCall.getInternalServiceData(financeName, "/finance/glservice/gl/externalsystemref");
		List<MasterGradeExternalMapping> gradeExternalMappings = (List<MasterGradeExternalMapping>) masterGrade.getGradeExternalMappings();
		
		if (gradeExternalMappings != null && !gradeExternalMappings.isEmpty()) {
			for (MasterGradeExternalMapping gradeExternalMapping : gradeExternalMappings) {
				for (Map<Object, Object> externalSystem  : externalSystemList) {
					if (externalSystem.get("externalSystemRefId").equals(gradeExternalMapping.getFkExternalSystemRefId())) {
						ExternalSystemDto externalSystemDto = new ExternalSystemDto();
						externalSystemDto.setExternalSystemId((Integer) externalSystem.get("externalSystemRefId"));
						externalSystemDto.setExternalSystemCode((String) externalSystem.get("externalSystemRefCode"));
						externalSystemDto.setExternalSystemName((String) externalSystem.get("externalSystemRefName"));
						externalSystemDto.setExternalSystemMappingId((Integer) externalSystem.get("externalSystemRefId"));
						externalSystemDto.setAttribute1(gradeExternalMapping.getMappingType());
						externalSystemDto.setAttribute2(gradeExternalMapping.getExternalCode());
						externalSystemDtos.add(externalSystemDto);
						break;
					}
				}
			}
		}
		return externalSystemDtos;
	}
}


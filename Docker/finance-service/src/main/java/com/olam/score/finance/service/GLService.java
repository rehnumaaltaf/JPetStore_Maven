package com.olam.score.finance.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.olam.score.common.constants.Constants;
import com.olam.score.common.domain.UserBean;
import com.olam.score.common.dto.ResponseData;
import com.olam.score.common.util.CommonUtil;
import com.olam.score.common.util.EnumStatus;
import com.olam.score.common.util.ScoreBaseException;
import com.olam.score.common.util.WebServiceCallUtil;
import com.olam.score.finance.domain.MasterExternalSystemRef;
import com.olam.score.finance.domain.MasterGl;
import com.olam.score.finance.domain.MasterGlExternalMapping;
import com.olam.score.finance.domain.MasterGlTypeRef;
import com.olam.score.finance.dto.ExternalGLMappingDto;
import com.olam.score.finance.dto.ExternalSystemRefDto;
import com.olam.score.finance.dto.GLBasicDto;
import com.olam.score.finance.dto.GLDto;
import com.olam.score.finance.dto.GlTypeRef;
import com.olam.score.finance.repository.GLRepository;

@Service
public class GLService {

	private final Logger log = LoggerFactory.getLogger(getClass());
	@Autowired 
	private WebServiceCallUtil webServiceCall;
	/*@Autowired 
	private ListViewUtil listview;*/
	@Autowired
	private GLRepository repository;

	@Autowired
	private ModelMapper modelMapper;
	
	@Value("${party.name}")
	private String partyName;
	
	Map<Integer, String> statusMap;
	List<Map<Object, Object>> partyList;

	@Transactional
	public GLDto create(GLDto glDto) {
		MasterGl masterGL;
		GLDto dto = null;
		UserBean userBean = new UserBean();
		Locale locale;
		String action = glDto.getStatusName();
		if (action.equalsIgnoreCase(Constants.DRAFT)) {
			glDto.setStatusName(Constants.DRAFT);
		} else if (action.equalsIgnoreCase(Constants.SAVE)) {
			glDto.setStatusName(Constants.ACTIVE);
		}
		String glName;
		String glCode;
		glName = repository.findByGLName(glDto.getGlBasicDto().getGlName());
		glCode = repository.findByGLCode(glDto.getGlBasicDto().getGlCode());
		
		locale=userBean.getLocale();
		if(locale==null){
			locale=Locale.getDefault();
			}
		String message=CommonUtil.getTranslationMessage(locale, "error_duplicate");
		if((glName!=null) && (glCode!=null))
		 {
			message=message.replace("$", glCode + " and " +glName);
			throw new ScoreBaseException(0, message, HttpStatus.CONFLICT);
			//throw new ScoreBaseException(0, "GlName "+glName +" and Glcode"+ glCode  +"already exists in database", HttpStatus.CONFLICT);
		 }else if((glName!=null))
		 {
			 message=message.replace("$", glName);
			 throw new ScoreBaseException(0, message, HttpStatus.CONFLICT);
			//throw new ScoreBaseException(0, "GlName "+glName +" exists in database", HttpStatus.CONFLICT);
		  }else if((glCode!=null))
			{
			  message=message.replace("$", glCode);
			  throw new ScoreBaseException(0, message, HttpStatus.CONFLICT);
			//throw new ScoreBaseException(0,"GlCode "+glCode +" exists in database", HttpStatus.CONFLICT);
		}else{
			masterGL = repository.saveAndFlush(generateMasterGL(glDto, "create"));
			dto=generateGlDto(masterGL);
			/*Map<Integer, String> statusMap1 = serviceCall.getAllStatus();
			dto = masterGL.convertToGLDto(statusMap1);*/
		}
		return dto;
	}

	/*
	 * Generate the Master GL
	 */
	@SuppressWarnings({"unchecked","rawtypes" })
	private MasterGl generateMasterGL(GLDto glDto, String action) {
		MasterGl masterGL = new MasterGl();
		/*Map<Integer, String> */statusMap = webServiceCall.getAllStatus();
		Optional<Integer> statusId = statusMap.entrySet().stream()
				  .filter(e -> e.getValue().equals(glDto.getStatusName()))
				  .map(Map.Entry::getKey)
				  .findFirst();
		if(statusId.isPresent()){
			masterGL.setFkStatusId(statusId.get());
		}
		/*ResponseEntity<ResponseData> responseEntity = CommonUtil.getServiceCall(url + "/statusservice/status");
		ResponseData<List<StatusDto>> body = responseEntity.getBody();
		List<Map<Object, Object>> list = (List<Map<Object, Object>>) body.getBody();
		Integer statusId=1;
		for (int x=0; x<list.size(); x++){
			String statusname=(String) list.get(x).get("statusName");
			if(statusname!=null && glDto.getStatusName()!=null && glDto.getStatusName().equalsIgnoreCase(statusname)){
				statusId=(Integer) list.get(x).get("pkStatusId");
			}
		}*/

		/*if (glDto.getGlBasicDto().getParentGlId()!=null) {
			MasterGl masterGLParent = repository.findByParentGLName(glName)(glDto.getGlBasicDto().getParentGlId());
			masterGL.setFkParentGlId(masterGLParent);
		}*/
		masterGL.setGlName(glDto.getGlBasicDto().getGlName());
		masterGL.setGlCode(glDto.getGlBasicDto().getGlCode());
		masterGL.setGlDesc(glDto.getGlBasicDto().getGlDesc());
		masterGL.setGlIsGroup(glDto.getGlBasicDto().getGlIsGroup());
		if(glDto.getGlBasicDto().getParentGlId()!=null)
		masterGL.setFkParentGlId(repository.findOne(glDto.getGlBasicDto().getParentGlId()));
		ResponseEntity<ResponseData> partyResponseEntity = webServiceCall.internalServiceCall(partyName , "/partyservice/party");
		ResponseData<List<Object>> partyBody = partyResponseEntity.getBody();
		/*List<Map<Object, Object>> */partyList = (List<Map<Object, Object>>) partyBody.getBody();

		//String partyName = null;
		Integer partyId = 0;
		for (int x = 0; x < partyList.size(); x++) {
			//partyName = (String) partyList.get(x).get("partyName");
			partyId = (Integer) partyList.get(x).get("partyId");
			if (glDto.getGlBasicDto().getPartyId() > 0 && partyId != null
					&& partyId.intValue() == glDto.getGlBasicDto().getPartyId()) {
				//partyId = (Integer) partyList.get(x).get("partyId");
				masterGL.setFkPartyId(partyId);
			}
			/*if (glDto.getGlBasicDto().getPartyName() != null && partyName != null
					&& partyName.equals(glDto.getGlBasicDto().getPartyName())) {
				partyId = (Integer) partyList.get(x).get("partyId");
				masterGL.setFkPartyId(partyId);
			}*/
		}
		//masterGL.setFkStatusId(statusId);	
		
		//GL Type...
		if(glDto.getGlBasicDto().getGlTypeRefId()!=null){
		MasterGlTypeRef gltype = repository.findByGlTypeId(glDto.getGlBasicDto().getGlTypeRefId());//repository.findByGlTypename(glDto.getGlBasicDto().getGlTypeRefName());
		masterGL.setFkGlTypeRefId(gltype);	
		}
		
		
		if ("create".equalsIgnoreCase(action)) {
			masterGL.setCreatedBy("Test");// TODO: Hard Coding needs changes
			masterGL.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		} else if ("update".equalsIgnoreCase(action)) {
				masterGL.setPkGlId(glDto.getGlBasicDto().getGlId());
				masterGL.setModifiedBy("Test");// TODO: Hard Coding needs changes
				masterGL.setModifiedDate(new Timestamp(System.currentTimeMillis()));
				
				masterGL.setCreatedBy("Test");//glDto.getGlBasicDto().getCreatedBy());
				masterGL.setCreatedDate(new Timestamp(System.currentTimeMillis()));//glDto.getGlBasicDto().getCreatedDate());
			}
		Collection<MasterGlExternalMapping> masterExternalGlMappingCollection = new ArrayList<>();
		for (ExternalGLMappingDto externalGLMappingDto : glDto.getExternalGLMappingDto()) {
			MasterGlExternalMapping externalGLMapping = new MasterGlExternalMapping();
			//id based retrieve glmapping name
			//externalGLMapping.setExternalGlMappingName(repository.
			//externalGLMapping.setExternalGlMappingName(externalGLMappingDto.getExternalGlMappingName());
			externalGLMapping.setExternalGlMappingCode(externalGLMappingDto.getExternalGlMappingCode());
			externalGLMapping.setMappingType(externalGLMappingDto.getMappingType());
			if ("create".equalsIgnoreCase(action)) {
				externalGLMapping.setCreatedBy("Test");// TODO: Hard Coding needs changes
				externalGLMapping.setCreatedDate(new Timestamp(System.currentTimeMillis()));
			}
			else if ("update".equalsIgnoreCase(action)) {
				externalGLMapping.setModifiedBy("Test");// TODO: Hard Coding needs changes
				externalGLMapping.setModifiedDate(new Timestamp(System.currentTimeMillis()));
				externalGLMapping.setCreatedBy("Test");//externalGLMappingDto.getCreatedBy());
				externalGLMapping.setCreatedDate(new Timestamp(System.currentTimeMillis()));//externalGLMappingDto.getCreatedDate());
				//externalGLMapping.setPkExternalGlMappingId(repository.findExternalGlMappingId(glDto.getGlBasicDto().getGlId()));
				externalGLMapping.setPkExternalGlMappingId(externalGLMappingDto.getExternalGlMappingId());
			}
			if(statusId.isPresent()){
				externalGLMapping.setFkStatusId(statusId.get());
			}
			//externalGLMapping.setFkStatusId(statusId);
			externalGLMapping.setFkGlId(masterGL);
			if(externalGLMappingDto.getExternalSystemRefId()!=null){
				MasterExternalSystemRef extSysRef = repository.findByExtRefId(externalGLMappingDto.getExternalSystemRefId());
				externalGLMapping.setFkExternalSystemRefId(extSysRef);
				externalGLMapping.setExternalGlMappingName(extSysRef.getExternalSystemRefName());
			}
			/*if(externalGLMappingDto.getExternalGlMappingName()!=null){
				MasterExternalSystemRef extSysRef = repository.findByExtRefName(externalGLMappingDto.getExternalGlMappingName());
				externalGLMapping.setFkExternalSystemRefId(extSysRef);
			}*/
			masterExternalGlMappingCollection.add(externalGLMapping);
		}
		masterGL.setMasterGlExternalMappingCollection(masterExternalGlMappingCollection);

		return masterGL;
	}
	/*public ViewDto getViewDetails(Class<? extends Object> currentfeature){
		String featureId=currentfeature.getAnnotation(Mapping.class).featureId();
		ViewDto viewdto = listview.getView(featureId);
		PageDto pageDto=new PageDto();
		pageDto.setPageNumber(0);
		pageDto.setPageSize(Constants.PAGESIZE);
		viewdto.setPage(pageDto);
		Class<UomDto> uomDto=UomDto.class;
		Field[] fields = uomDto.getDeclaredFields();
		Map<String,String> dataType=new HashMap<>();
		for (int i = 0; i < fields.length; i++) {
			String type=fields[i].getType().toString();
			type=type.substring(type.lastIndexOf('.')+1, type.length());
			dataType.put(fields[i].getName(), type);
		}
		viewdto.setDataType(dataType);
		return viewdto;
	}*/

	@Transactional
	@SuppressWarnings({"unchecked","rawtypes" })
	public List<GLDto> readAll() {
		List<GLDto> newList = new ArrayList<>();
		List<String> sortColumns=new ArrayList<>();
		sortColumns.add("createdDate");
		sortColumns.add("modifiedDate");
		Sort sort = new Sort(Direction.DESC,sortColumns);
		List<MasterGl> oldList = repository.findAll(sort);	
		/*Map<Integer, String>*/ statusMap = webServiceCall.getAllStatus();
		ResponseEntity<ResponseData> partyResponseEntity = webServiceCall.internalServiceCall(partyName, "/partyservice/party");
		ResponseData<List<Object>> partyBody = partyResponseEntity.getBody();
		/*List<Map<Object, Object>>*/ partyList = (List<Map<Object, Object>>) partyBody.getBody();
		if(oldList==null)
			throw new ScoreBaseException(0, "No Record Found", HttpStatus.NOT_FOUND);
		for (MasterGl masterGL : oldList) {
			GLDto dto = generateGlDto(masterGL);
			newList.add(dto);
		}
		return newList;
	}

	//@SuppressWarnings({"unchecked","rawtypes" })
	private GLDto generateGlDto(MasterGl masterGL) {
		GLDto dto = new GLDto();
		String statusname="";
		GLBasicDto glBasic = new GLBasicDto();
		glBasic.setGlId(masterGL.getPkGlId());
		glBasic.setCreatedBy(masterGL.getCreatedBy());
		glBasic.setCreatedDate((Timestamp)masterGL.getCreatedDate());
		glBasic.setModifiedBy(masterGL.getModifiedBy());
		glBasic.setModifiedDate((Timestamp)masterGL.getModifiedDate());
		glBasic.setGlName(masterGL.getGlName());
		glBasic.setGlCode(masterGL.getGlCode());
		glBasic.setGlDesc(masterGL.getGlDesc());
		glBasic.setGlIsGroup(masterGL.getGlIsGroup());
		if(masterGL.getFkParentGlId()!=null){
		glBasic.setParentGlId(masterGL.getFkParentGlId().getPkGlId());
			
			}
		//glBasic.setPartyName(masterGL.getFkPartyId());
		/*if(masterGL.getFkPartyId()!=null){
		glBasic.setPartyName(masterGL.getFkPartyId().getPartyName());}*/

		/*ResponseEntity<ResponseData> partyResponseEntity = CommonUtil.getServiceCall(partyurl + "/partyservice/party");
		ResponseData<List<Object>> partyBody = partyResponseEntity.getBody();
		List<Map<Object, Object>> partyList = (List<Map<Object, Object>>) partyBody.getBody();*/

		String partyName = null;
		 Integer partyId = 0;

		 System.out.println("partyList.size() "+partyList.size());
		for (int x = 0; x < partyList.size(); x++) {
			System.out.println("partyId "+partyId);
			partyId = (int) partyList.get(x).get("partyId");
			System.out.println("partyId    "+partyId);
			if (partyId != 0 && masterGL.getFkPartyId() == partyId) {
				partyName = (String) partyList.get(x).get("partyName");
				log.info("partyName "+partyName);
				glBasic.setPartyName(partyName);
				glBasic.setPartyId(partyId);
			}
		}
		
//		Map<Integer, String> statusMap = serviceCall.getAllStatus();
		Optional<String> statusName = statusMap.entrySet().stream()
				  .filter(e -> e.getKey().equals(masterGL.getFkStatusId()))
				  .map(Map.Entry::getValue)
				  .findFirst();
		if(statusName.isPresent()){
			glBasic.setStatusName(statusName.get());
			dto.setStatusName(statusName.get());
			statusname = statusName.get();
		}
		
		
		/*ResponseEntity<ResponseData> responseEntity = CommonUtil.getServiceCall(url + "/statusservice/status");
		ResponseData<List<StatusDto>> body = responseEntity.getBody();
		List<Map<Object, Object>> list = (List<Map<Object, Object>>) body.getBody();
		Integer statusId=1;
		for (int x=0; x<list.size(); x++){
			statusId=(Integer) list.get(x).get("pkStatusId");
			if(statusId!=null && masterGL.getFkStatusId()==statusId){
				statusname=(String) list.get(x).get("statusName");
				glBasic.setStatusName(statusname);
			}
		}*/
		if(masterGL.getFkParentGlId()!=null){
			glBasic.setParentGl(masterGL.getFkParentGlId().getGlName());
		}
		
		if(masterGL.getFkGlTypeRefId() != null){
			glBasic.setGlTypeRefId(masterGL.getFkGlTypeRefId().getPkGlTypeRefId());
			glBasic.setGlTypeRefName(masterGL.getFkGlTypeRefId().getGlTypeRefName());
		}
		dto.setGlBasicDto(glBasic);
		List<ExternalGLMappingDto> externalGlList = new ArrayList<>();
		
		if(!masterGL.getMasterGlExternalMappingCollection().isEmpty()){
			for(MasterGlExternalMapping extglMappingMaster:masterGL.getMasterGlExternalMappingCollection()){
				ExternalGLMappingDto extglMapping=new ExternalGLMappingDto();
				extglMapping.setExternalGlMappingId(extglMappingMaster.getPkExternalGlMappingId());
				extglMapping.setExternalGlMappingName(extglMappingMaster.getExternalGlMappingName());
				extglMapping.setExternalGlMappingCode(extglMappingMaster.getExternalGlMappingCode());
				extglMapping.setCreatedBy(extglMappingMaster.getCreatedBy());
				extglMapping.setCreatedDate((Timestamp)extglMappingMaster.getCreatedDate());
				extglMapping.setModifiedBy(extglMappingMaster.getModifiedBy());
				extglMapping.setModifiedDate((Timestamp)extglMappingMaster.getModifiedDate());

				extglMapping.setStatusName(statusname);
				extglMapping.setGlId(extglMappingMaster.getFkGlId().getPkGlId());
				if(extglMappingMaster.getFkExternalSystemRefId()!= null)
					extglMapping.setExternalSystemRefId(extglMappingMaster.getFkExternalSystemRefId().getPkExternalSystemRefId());

				extglMapping.setMappingType(extglMappingMaster.getMappingType());
				externalGlList.add(extglMapping);
				
			}
			dto.setExternalGLMappingDto(externalGlList);
		}
		return (dto);
	}
		
	@Transactional
	public GLDto readById(Integer id) {
		GLDto dto;
		if(id==null)
			throw new ScoreBaseException(0, "GlId : "+id, HttpStatus.NOT_FOUND);
		MasterGl masterGl = repository.findOne(id);
		if(masterGl==null)
			throw new ScoreBaseException(0, "GlId : "+id +" not found in database", HttpStatus.NOT_FOUND);
		
		dto = generateGlDto(masterGl);
		return dto;
	}

	 @Transactional
		public void deleteGL1(List<Map<String, Integer>> body){
			for (Map<String, Integer> i : body) {
				i.forEach((attribute, glId) -> {
					MasterGl masterGl = new MasterGl();
					masterGl = repository.findOne(glId);
					if(masterGl.getFkStatusId()!=0 && EnumStatus.ACTIVE.getValue()==masterGl.getFkStatusId()){
						log.info(glId.toString());
						log.info("===Changing Active status to Inactive===");
						masterGl.setFkStatusId(EnumStatus.INACTIVE.getValue());
						repository.saveAndFlush(masterGl);
					}else if(masterGl.getFkStatusId()!=0 && EnumStatus.DRAFT.getValue()==masterGl.getFkStatusId()){
						log.info(glId.toString());
						log.info("===Deleting the draft UOM===");
						repository.delete(masterGl);
					}else if(masterGl.getFkStatusId()!=0 && EnumStatus.INACTIVE.getValue()==masterGl.getFkStatusId()){
						masterGl.setFkStatusId(EnumStatus.ACTIVE.getValue());
						repository.saveAndFlush(masterGl);
					}
				});

			}
			
		}
	public void deleteGL(List<Map<String, Integer>> body) {
		Map<Object,Object> notDeletedIds=new HashMap<>();
		for (Map<String, Integer> i : body) {
			i.forEach((k, v) -> {
				MasterGl masterGl = new MasterGl();
				masterGl = repository.findOne(v);
				if(masterGl==null) {
					throw new ScoreBaseException(v, v+" GL Id Not Found For deletion ", HttpStatus.NOT_FOUND);
				}
				List<Integer> list=repository.findByParentGl(v);
				if(list!=null && !(list.isEmpty())){
					notDeletedIds.put(v, "This is associated as a parent to some other GL");
				}else if(masterGl!=null){
					if (masterGl.getFkStatusId()!=0 &&
							EnumStatus.ACTIVE.getValue()==masterGl.getFkStatusId()) {
						masterGl.setFkStatusId(EnumStatus.INACTIVE.getValue());
						if(!masterGl.getMasterGlExternalMappingCollection().isEmpty()){
							for(MasterGlExternalMapping extglMappingMaster:masterGl.getMasterGlExternalMappingCollection()){
								extglMappingMaster.setFkStatusId(EnumStatus.INACTIVE.getValue());
							}
						}
						repository.saveAndFlush(masterGl);
					} else if (masterGl.getFkStatusId()!=0
							&& EnumStatus.DRAFT.getValue()==masterGl.getFkStatusId()) {
						repository.delete(masterGl);
					} else if (masterGl.getFkStatusId()!=0 && EnumStatus.INACTIVE.getValue()==masterGl.getFkStatusId()){
						masterGl.setFkStatusId(EnumStatus.ACTIVE.getValue());
						if(!masterGl.getMasterGlExternalMappingCollection().isEmpty()){
							for(MasterGlExternalMapping extglMappingMaster:masterGl.getMasterGlExternalMappingCollection()){
								extglMappingMaster.setFkStatusId(EnumStatus.ACTIVE.getValue());
							}
						}
						repository.saveAndFlush(masterGl);
					}
					
					}
			});

			if(!(notDeletedIds.isEmpty())){
				throw new ScoreBaseException(notDeletedIds, HttpStatus.INTERNAL_SERVER_ERROR);

			}
		}
	}

	public List<GLDto> updateGL(List<GLDto> body) {
		GLDto dto;
		List<GLDto> newList = new ArrayList<>();
		System.out.println("checking..."+body.get(0).getExternalGLMappingDto().get(0).getExternalGlMappingCode());
		for (GLDto glDto : body) {	
			/*UserBean userBean = new UserBean();
			Locale locale;
			Integer glNameInt;
			Integer glCodeInt;
			glNameInt = repository.findGLIDGLName(glDto.getGlBasicDto().getGlName());
			glCodeInt = repository.findGLIDGLCode(glDto.getGlBasicDto().getGlCode());
			locale=Locale.getDefault();
			String message=CommonUtil.getTranslationMessage(locale, "error_duplicate");*/
			
			/*if((glNameInt!=null && (glDto.getGlBasicDto().getGlId().intValue()!= glNameInt.intValue())) && (glCodeInt!=null && (glDto.getGlBasicDto().getGlId().intValue()!=glCodeInt.intValue())))
			 {
				message=message.replace("$", glDto.getGlBasicDto().getGlCode() + " and " + glDto.getGlBasicDto().getGlName());
				throw new ScoreBaseException(0, message, HttpStatus.CONFLICT);
				//throw new ScoreBaseException(0, "GlName and Glcode already exists in database", HttpStatus.CONFLICT);
			 }else if((glNameInt!=null && (glDto.getGlBasicDto().getGlId().intValue()!=glNameInt.intValue())))
			 {
				 message=message.replace("$", glDto.getGlBasicDto().getGlName());
			   	 throw new ScoreBaseException(0, message, HttpStatus.CONFLICT);
				//throw new ScoreBaseException(0, "GlName exists in database", HttpStatus.CONFLICT);
			  }else if((glCodeInt!=null && (glDto.getGlBasicDto().getGlId().intValue()!=glCodeInt.intValue())))
				{
				  message=message.replace("$", glDto.getGlBasicDto().getGlCode());
				  throw new ScoreBaseException(0, message, HttpStatus.CONFLICT);
				//throw new ScoreBaseException(0,"GlCode exists in database", HttpStatus.CONFLICT);
			}else{*/
		
				MasterGl masterGl = repository.findOne(glDto.getGlBasicDto().getGlId());
				
				
				glDto.getGlBasicDto().setCreatedBy(masterGl.getCreatedBy());
				glDto.getGlBasicDto().setCreatedDate((Timestamp)masterGl.getCreatedDate());
				
				/*if(!masterGl.getMasterGlExternalMappingCollection().isEmpty()){
					List<ExternalGLMappingDto> externalGlMappingList = new ArrayList<>();
					for(MasterGlExternalMapping extglMappingMaster:masterGl.getMasterGlExternalMappingCollection()){
						ExternalGLMappingDto externalGLMappingDto = new ExternalGLMappingDto();
						externalGLMappingDto.setCreatedBy(extglMappingMaster.getCreatedBy());
						externalGLMappingDto.setCreatedDate((Timestamp)extglMappingMaster.getCreatedDate());
						externalGLMappingDto.setExternalGlMappingId(extglMappingMaster.getPkGlExternalMappingId());
						externalGlMappingList.add(externalGLMappingDto);
					}
					glDto.setExternalGLMappingDto(externalGlMappingList);
				}*/
			//repository.deleteAllExtMappingOfGlId(glDto.getGlBasicDto().getGlId());

			 masterGl = repository.saveAndFlush(generateMasterGL(glDto, "update"));
				dto = generateGlDto(masterGl);
				newList.add(dto);
			//}		
		}
		return newList;
	}
	
	//get all type names
	@Transactional
	public List<GlTypeRef> getGlTypeRef() {
		List<MasterGlTypeRef> glTypeList = repository.getAllTypeReference();
		if(!glTypeList.isEmpty()){
			List<GlTypeRef> glTypeDtoList = new ArrayList<>();
			for(MasterGlTypeRef glType:glTypeList){
				GlTypeRef glTypeDto = new GlTypeRef();
				glTypeDto.setGlTypeRefId(glType.getPkGlTypeRefId());
				glTypeDto.setGlTypeRefName(glType.getGlTypeRefName());
				glTypeDtoList.add(glTypeDto);
			}			
			return glTypeDtoList;
		}
		return null;
	}
	
	//get all type names
		@Transactional
		public List<ExternalSystemRefDto> getExternalSystemRef() {
			List<MasterExternalSystemRef> externalSystemRefList = repository.getAllExternalSystemRef();
			if(!externalSystemRefList.isEmpty()){
				List<ExternalSystemRefDto> externalSystemRefDtoList = new ArrayList<>();
				for(MasterExternalSystemRef externalSystemRef:externalSystemRefList){
					ExternalSystemRefDto externalSystemRefDto = new ExternalSystemRefDto();
					externalSystemRefDto.setExternalSystemRefId(externalSystemRef.getPkExternalSystemRefId());
					externalSystemRefDto.setExternalSystemRefName(externalSystemRef.getExternalSystemRefName());
					externalSystemRefDto.setExternalSystemRefCode(externalSystemRef.getExternalSystemRefCode());
					externalSystemRefDtoList.add(externalSystemRefDto);
				}			
				return externalSystemRefDtoList;
			}
			return null;
		}
		
		@Transactional
		@SuppressWarnings({"unchecked","rawtypes" })
		public List<GLDto> getParentGl() {
			List<GLDto> newList = new ArrayList<>();
			List<MasterGl> oldList = repository.getAllParentGl("Y");	
			/*Map<Integer, String>*/ statusMap = webServiceCall.getAllStatus();
			ResponseEntity<ResponseData> partyResponseEntity = webServiceCall.internalServiceCall(partyName , "/partyservice/party");
			ResponseData<List<Object>> partyBody = partyResponseEntity.getBody();
			/*List<Map<Object, Object>>*/ partyList = (List<Map<Object, Object>>) partyBody.getBody();
			if(oldList==null)
				throw new ScoreBaseException(0, "No Record Found", HttpStatus.NOT_FOUND);
			for (MasterGl masterGL : oldList) {
				GLDto dto = generateGlDto(masterGL);
				newList.add(dto);
			}
			return newList;
		}
}






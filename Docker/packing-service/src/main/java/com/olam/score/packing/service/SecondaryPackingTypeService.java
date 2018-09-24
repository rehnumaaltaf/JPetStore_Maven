package com.olam.score.packing.service;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.olam.score.common.constants.Constants;
import com.olam.score.common.constants.ResponseConstants;
import com.olam.score.common.dto.PageDto;
import com.olam.score.common.dto.ResponseData;
import com.olam.score.common.dto.ViewDto;
import com.olam.score.common.util.ListViewUtil;
import com.olam.score.common.util.Mapping;
import com.olam.score.common.util.ScoreBaseException;
import com.olam.score.common.util.WebServiceCallUtil;
import com.olam.score.packing.controller.SecondaryPackingTypeController;
import com.olam.score.packing.domain.MasterPackingAssoc;
import com.olam.score.packing.domain.MasterSecondaryPackingType;
import com.olam.score.packing.dto.SecondaryPackingTypeDTO;
import com.olam.score.packing.dto.SecondaryPackingaAssocDTO;
import com.olam.score.packing.repository.SecondaryPackingAssocRepository;
import com.olam.score.packing.repository.SecondaryPackingTypeRepository;
import com.olam.score.packing.util.SecondaryPackingSpecificationsBuilder;
@Service
public class SecondaryPackingTypeService {
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Value("${reference.name}")
	private String refURL;
	
	@Value("${packing.name}")
	private String packingName;
	
	@Value("${product.name}")
	private String productName;
	private int chkValue=0;
	@Autowired
	private WebServiceCallUtil webServiceCall;
	@Autowired
	private SecondaryPackingTypeRepository secondaryPackingTypeRepository;
	@Autowired
	private SecondaryPackingAssocRepository secondaryPackingAssocRepository;
	List<Map<Object, Object>> productList;
	List<Map<Object, Object>> primaryPackingList;
	List<Map<Object, Object>> originList;
	@Autowired
	private ListViewUtil listview;

	@Transactional
	public String create(SecondaryPackingTypeDTO inputData) {
		String addStatus = ResponseConstants.LOCATION_ADD_SUCESS;
		String action = inputData.getAction();
		if (action.equalsIgnoreCase(Constants.SAVE)) {
			log.info("===draft action to perform for create ===");
			inputData.setStatusName(Constants.DRAFT);
		} else if (action.equalsIgnoreCase(Constants.SUBMIT)) {
			log.info("===save(active) action to perform for create ===");
			inputData.setStatusName(Constants.ACTIVE);
		}
		Map<Integer, String> statusMap = webServiceCall.getAllStatus();
		MasterSecondaryPackingType masterSecondaryPackingType = getMappingSecondaryPackingType(inputData,"create");
	    validate(masterSecondaryPackingType,0);
	    secondaryPackingTypeRepository.saveAndFlush(masterSecondaryPackingType);
		return addStatus;
	}
	
	private void validate(MasterSecondaryPackingType masterSecondaryPackingType,Integer id){
		String msg = null;
		if (secondaryPackingTypeRepository.uniqueSecPackCode(masterSecondaryPackingType.getSecondaryPackingTypeCode()) > chkValue) {
			msg = "Secondary Packing Material '"+masterSecondaryPackingType.getSecondaryPackingTypeCode()+"' exists. Please reenter valid unique data";
			throw new ScoreBaseException(id, msg,HttpStatus.CONFLICT);
		}
		if (secondaryPackingTypeRepository.uniqueSecPackName(masterSecondaryPackingType.getSecondaryPackingTypeName()) > chkValue) {
			msg = "Secondary Packing Material '"+masterSecondaryPackingType.getSecondaryPackingTypeName()+"' exists. Please reenter valid unique data";
			throw new ScoreBaseException(id, msg,HttpStatus.CONFLICT);
		}
		
	}
	
	@Transactional
	public String update(SecondaryPackingTypeDTO inputData) {
		String msg = null;
		String addStatus = ResponseConstants.LOCATION_UPDATED_SUCESS;
		Map<Integer, String> statusMap = webServiceCall.getAllStatus();
		if(inputData.getStatusName() == null) {
			inputData.setStatusName(statusMap.get(inputData.getFkStatusId()));
		}
		MasterSecondaryPackingType masterSecondaryPackingType = getMappingSecondaryPackingType(inputData, "update");
		chkValue = 1;
		// validate(masterSecondaryPackingType, masterSecondaryPackingType.getPkSecondaryPackingTypeId());
		if (!secondaryPackingTypeRepository.getBySecondaryPackingTypeCodeAndPkSecondaryPackingTypeIdNotIn(masterSecondaryPackingType.getSecondaryPackingTypeCode(), masterSecondaryPackingType.getPkSecondaryPackingTypeId()).isEmpty()) {
			msg = "Secondary Packing Material '"+masterSecondaryPackingType.getSecondaryPackingTypeCode()+"' exists. Please reenter valid unique data";
			throw new ScoreBaseException(masterSecondaryPackingType.getPkSecondaryPackingTypeId(), msg,HttpStatus.CONFLICT);
		}
		if (!secondaryPackingTypeRepository.getBySecondaryPackingTypeNameAndPkSecondaryPackingTypeIdNotIn(masterSecondaryPackingType.getSecondaryPackingTypeName(), masterSecondaryPackingType.getPkSecondaryPackingTypeId()).isEmpty()) {
			msg = "Secondary Packing Material '"+masterSecondaryPackingType.getSecondaryPackingTypeName()+"' exists. Please reenter valid unique data";
			throw new ScoreBaseException(masterSecondaryPackingType.getPkSecondaryPackingTypeId(), msg,HttpStatus.CONFLICT);
		}
		secondaryPackingAssocRepository.deleteAssocId(inputData.getPkSecondaryPackingTypeId());
		secondaryPackingTypeRepository.saveAndFlush(masterSecondaryPackingType);
		return addStatus;
	}

	
	@Transactional
	public List<SecondaryPackingTypeDTO> readAll() {
		List<MasterSecondaryPackingType> masterSecPackingList = secondaryPackingTypeRepository.findAll(sortByIdDsc());
		return modelToDto(masterSecPackingList,"all");
	}
	
	

	private List<SecondaryPackingTypeDTO> modelToDto(List<MasterSecondaryPackingType> masterSecPackingList,String listType) {
		
	/*	ResponseEntity<ResponseData> responseEntity = webServiceCall.internalServiceCall(refURL + "/reference/statusservice/status");
		ResponseData<List<StatusDto>> body = responseEntity.getBody();
		List<Map<Object, Object>> list = (List<Map<Object, Object>>) body.getBody();*/
		
		/*ResponseEntity<ResponseData> responseEntityProd = webServiceCall.internalServiceCall(productName,"/product/productservice/product");
		ResponseData<List<ProductDto>> bodyProd = responseEntityProd.getBody();
		List<Map<Object, Object>> listProd = (List<Map<Object, Object>>) bodyProd.getBody();*/

		/*ResponseEntity<ResponseData> responseEntityPri = webServiceCall.internalServiceCall(packingName,"/packing/PrimaryPackingType/viewPrimaryPackingType");
		ResponseData<List<PrimaryPackingTypeDto>> bodyPri = responseEntityPri.getBody();
		List<Map<Object, Object>>  listPri = (List<Map<Object, Object>>) bodyPri.getBody();*/
		
		/*ResponseEntity<ResponseData> responseEntityOrigin = webServiceCall.internalServiceCall(productName,"/product/originservice/origin");
		ResponseData<List<PrimaryPackingTypeDto>> bodyOrigin = responseEntityOrigin.getBody();
		List<Map<Object, Object>>  listOrigin = (List<Map<Object, Object>>) bodyOrigin.getBody();*/
		List<SecondaryPackingTypeDTO> secondaryPackingTypeDTOList = new ArrayList<>();
		try{
		List<Map<Object, Object>> listProd = null;
		List<Map<Object, Object>>  listOrigin = null;
		List<Map<Object, Object>>  listPri = null;
	
		//Integer statusId;
		for(MasterSecondaryPackingType masterSecondaryPackingType : masterSecPackingList){
			SecondaryPackingTypeDTO secondaryPackingTypeDTO =new SecondaryPackingTypeDTO();
			secondaryPackingTypeDTO.setPkSecondaryPackingTypeId(masterSecondaryPackingType.getPkSecondaryPackingTypeId());
			secondaryPackingTypeDTO.setSecondaryPackingTypeCode(masterSecondaryPackingType.getSecondaryPackingTypeCode());
			secondaryPackingTypeDTO.setSecondaryPackingTypeName(masterSecondaryPackingType.getSecondaryPackingTypeName());
			secondaryPackingTypeDTO.setSecondaryPackingTypeDesc(masterSecondaryPackingType.getSecondaryPackingTypeDesc());
			secondaryPackingTypeDTO.setStatusName(returnStatusNameById(masterSecondaryPackingType.getFkStatusId()));
			if(masterSecondaryPackingType.getFkStatusId() != null) {
				secondaryPackingTypeDTO.setFkStatusId(masterSecondaryPackingType.getFkStatusId());
				if(masterSecondaryPackingType.getFkStatusId() == 1) {
					secondaryPackingTypeDTO.setStatusName(Constants.ACTIVE);
				} else if(masterSecondaryPackingType.getFkStatusId() == 2) {
					secondaryPackingTypeDTO.setStatusName(Constants.DRAFT);
				} else if(masterSecondaryPackingType.getFkStatusId() == 3) {
					secondaryPackingTypeDTO.setStatusName(Constants.INACTIVE);
				}
			}
			secondaryPackingTypeDTO.setSecondaryPackingTypeIsBulk(masterSecondaryPackingType.getSecondaryPackingTypeIsBulk());
			List<SecondaryPackingaAssocDTO> secondaryPackingaAssocList =null;
			if(listType.equalsIgnoreCase("indv")){
				loadBasicList();
				secondaryPackingaAssocList = modelToDtoAssoc(masterSecondaryPackingType.getMasterPackingAssocList(),listProd,listPri, listOrigin);
			}
			secondaryPackingTypeDTO.setSecPackAssocList(secondaryPackingaAssocList);
			secondaryPackingTypeDTOList.add(secondaryPackingTypeDTO);
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		return secondaryPackingTypeDTOList;
	}
	
	 private String getPrimaryPackingName(Integer ppId) {
	        String primaryPackingName=null;
	        if(ppId!=null && ppId>0) {
	              ObjectMapper map=new ObjectMapper();
	              ResponseEntity<ResponseData> responseEntityPrimaryPacking=webServiceCall.internalServiceCall(packingName,"/packing/PrimaryPackingType/viewPrimaryPackingType/"+ppId.toString());
	              Map<Object, Object> pps = (Map<Object, Object>) responseEntityPrimaryPacking.getBody().getBody();
	              primaryPackingName=(String) pps.get("internalPackingTypeName");
	             
	              return primaryPackingName;
	        }else {
	              return null;
	        }
	        //return null;
	  }
	 
	 private String getProductName(Integer ppId) {
	        String productName=null;
	        if(ppId!=null && ppId>0) {
	              ObjectMapper map=new ObjectMapper();
	              ResponseEntity<ResponseData> responseEntityPrimaryPacking=webServiceCall.internalServiceCall(productName,"/product/productservice/product/"+ppId.toString());
	              Map<Object, Object> pps = (Map<Object, Object>) responseEntityPrimaryPacking.getBody().getBody();
	              productName=(String) pps.get("prodId");
	             
	              return productName;
	        }else {
	              return null;
	        }
	        //return null;
	  }

	private List<SecondaryPackingaAssocDTO> modelToDtoAssoc(List<MasterPackingAssoc> masterPackingAssocList,List<Map<Object, Object>> listProd,List<Map<Object, Object>>  listPri, List<Map<Object, Object>>  listOrigin){
	
		List<SecondaryPackingaAssocDTO> secondaryPackingaAssocList = new ArrayList<>();
		for(MasterPackingAssoc masterPackingAssoc: masterPackingAssocList){
			SecondaryPackingaAssocDTO secondaryPackingaAssocDTO =new SecondaryPackingaAssocDTO();
			secondaryPackingaAssocDTO.setPkPackingAssocId(masterPackingAssoc.getPkPackingAssocId());
			secondaryPackingaAssocDTO.setCountOfPrimary(masterPackingAssoc.getCountOfPrimary());		
			
			secondaryPackingaAssocDTO.setFkPrimaryPackingTypeId(masterPackingAssoc.getFkPrimaryPackingTypeId());
			secondaryPackingaAssocDTO.setFkPrimaryPackingTypeName(getNameById(masterPackingAssoc.getFkPrimaryPackingTypeId(), primaryPackingList, "internalPackingTypeId", "internalPackingTypeName"));
			
			secondaryPackingaAssocDTO.setFkProdId(masterPackingAssoc.getFkProdId());
			secondaryPackingaAssocDTO.setFkProdName(getNameById(masterPackingAssoc.getFkProdId(), productList, "prodId", "prodName"));
			
			secondaryPackingaAssocDTO.setFkOriginId(masterPackingAssoc.getFkOriginId());
			secondaryPackingaAssocDTO.setFkOriginName(getOriginNameById(masterPackingAssoc.getFkOriginId()));
		
			secondaryPackingaAssocList.add(secondaryPackingaAssocDTO);
		}
		return secondaryPackingaAssocList;
	}

	private MasterSecondaryPackingType getMappingSecondaryPackingType(SecondaryPackingTypeDTO inputData, String action) {
		MasterSecondaryPackingType masterSecondaryPackingType =new MasterSecondaryPackingType();
		masterSecondaryPackingType.setFkStatusId(returnStatusIdByName(inputData.getStatusName()));
		
		masterSecondaryPackingType.setPkSecondaryPackingTypeId(inputData.getPkSecondaryPackingTypeId());
		masterSecondaryPackingType.setSecondaryPackingTypeCode(inputData.getSecondaryPackingTypeCode());
		masterSecondaryPackingType.setSecondaryPackingTypeName(inputData.getSecondaryPackingTypeName());
		masterSecondaryPackingType.setSecondaryPackingTypeDesc(inputData.getSecondaryPackingTypeDesc());
		masterSecondaryPackingType.setSecondaryPackingTypeIsBulk(inputData.getSecondaryPackingTypeIsBulk());
		
		List<MasterPackingAssoc> masterPackingAssocList =new ArrayList<>();
		for(SecondaryPackingaAssocDTO secondaryPackingType: inputData.getSecPackAssocList()){
			MasterPackingAssoc masterPackingAssoc =new MasterPackingAssoc();
			masterPackingAssoc.setSecPackAssoc(masterSecondaryPackingType);
			masterPackingAssoc.setPkPackingAssocId(secondaryPackingType.getPkPackingAssocId());
			masterPackingAssoc.setFkOriginId(secondaryPackingType.getFkOriginId());
			masterPackingAssoc.setFkProdId(secondaryPackingType.getFkProdId());
			masterPackingAssoc.setFkPrimaryPackingTypeId(secondaryPackingType.getFkPrimaryPackingTypeId());
			masterPackingAssoc.setCountOfPrimary(secondaryPackingType.getCountOfPrimary());
			masterPackingAssocList.add(masterPackingAssoc);
		}
		if ("create".equalsIgnoreCase(action)) {
			masterSecondaryPackingType.setCreatedBy("Test");// TODO: Hard Coding needs changes
			masterSecondaryPackingType.setCreatedDate(new Timestamp(System.currentTimeMillis()));
			masterSecondaryPackingType.setModifiedBy("Test");// TODO: Hard Coding needs changes
			masterSecondaryPackingType.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		} else if ("update".equalsIgnoreCase(action)) {
			masterSecondaryPackingType.setCreatedBy(inputData.getCreatedBy());
			masterSecondaryPackingType.setCreatedDate(inputData.getCreatedDate());
			masterSecondaryPackingType.setModifiedBy("Test");// TODO: Hard Coding needs changes
			masterSecondaryPackingType.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		}
		masterSecondaryPackingType.setMasterPackingAssocList(masterPackingAssocList); 
		
		return masterSecondaryPackingType;
	}
	
	@Transactional
	public List<SecondaryPackingTypeDTO> getSecPackById(Integer secPackId) {
		List<MasterSecondaryPackingType> masterSecondaryPackingType = secondaryPackingTypeRepository.getSecPackById(secPackId);
		return modelToDto(masterSecondaryPackingType,"indv");
	}
	
	public List<String> suggestSecCode(String secCode) {

		List<String> suggestSecCode = null;
		try {
			suggestSecCode = secondaryPackingTypeRepository.suggestSecCode(secCode);
		} catch (Exception e) {
			log.error("Suggest Code:",e);
		}

		return suggestSecCode;
	}
	
	public List<String> suggestSecName(String secName) {

		List<String> suggestSecName = null;
		try {
			suggestSecName = secondaryPackingTypeRepository.suggestSecName(secName);
		} catch (Exception e) {
			
			log.error("Suggest Sec Name:",e);
		}

		return suggestSecName;
	}

	
	@Transactional
	public String delete(Integer secPackId) {

		String deleteStatus = ResponseConstants.CURRENCY_DELETE_SUCESS;
		try {
			secondaryPackingTypeRepository.delete(secPackId);
		} catch (EmptyResultDataAccessException e) {
			deleteStatus = "Empty Result";
			throw new ScoreBaseException(0, "Empty Result",HttpStatus.CONFLICT);
			//log.error("Delete:", e);

		}
		return deleteStatus;
	}
	
	private Sort sortByIdDsc() {
		return new Sort(Direction.DESC, Arrays.asList("createdDate","modifiedDate"));
	}
	
	
	public int returnStatusIdByName(String name)
	{
		Map<Integer, String> statusMap = webServiceCall.getAllStatus();
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
		String toReturn=null;
		Map<Integer, String> statusMap = webServiceCall.getAllStatus();
		toReturn=statusMap.get(id);
		return toReturn;
	}
	
	private void loadBasicList() {
		loadProductList();
		loadPrimaryPackingList();
		loadOriginList();
	}
	
	@SuppressWarnings("unchecked")
	private void loadProductList() {	
		ResponseEntity<ResponseData> responseEntityProd = webServiceCall.internalServiceCall(productName,"/product/productservice/product/basicdetails");
		productList = (List<Map<Object, Object>>) responseEntityProd.getBody().getBody();
	}
	
	@SuppressWarnings("unchecked")
	private void loadPrimaryPackingList() {	
		ResponseEntity<ResponseData> responseEntityProd = webServiceCall.internalServiceCall(packingName,"/packing/PrimaryPackingType/basicdetails");
		primaryPackingList = (List<Map<Object, Object>>) responseEntityProd.getBody().getBody();
	}
	
	@SuppressWarnings("unchecked")
	private void loadOriginList() {	
		ResponseEntity<ResponseData> responseEntityProd = webServiceCall.internalServiceCall(productName,"/product/originservice/origin");
		originList = (List<Map<Object, Object>>) responseEntityProd.getBody().getBody();
	}
	
	/*private String getProductNameById(Integer id) {
		String name = null;
		if(!productList.isEmpty()) {
			for (int x = 0; x < productList.size(); x++) {
				Integer prodId = (Integer) productList.get(x).get("prodId");
				if (prodId != null && id != null && id.equals(prodId)) {
					name = (String) productList.get(x).get("prodName");
					break;
				}
			}
		}
		return name;
	}*/
	
	private String getNameById(Integer id, List<Map<Object, Object>> list, String idProperty, String nameProperty) {
		String name = null;
		if(!list.isEmpty()) {
			for (int x = 0; x < list.size(); x++) {
				Integer prodId = (Integer) list.get(x).get(idProperty);
				if (prodId != null && id != null && id.equals(prodId)) {
					name = (String) list.get(x).get(nameProperty);
					break;
				}
			}
		}
		return name;
	}
	
	@SuppressWarnings("unchecked")
	private String getOriginNameById(Integer id) {
		String name = null;
		if(!originList.isEmpty()) {
			for (int x = 0; x < originList.size(); x++) {
				LinkedHashMap<String, Object> tempMap = (LinkedHashMap<String, Object>) originList.get(x).get("originDto");
				Integer originId = (Integer) tempMap.get("pkOriginId");
				if (originId != null && id != null && id == originId) {
					name = (String) tempMap.get("originName");
					break;
				}
			}
		}
		return name;
	}
	
	
	public ViewDto getViewDetails(Class<? extends Object> currentfeature) {
		String featureId = currentfeature.getAnnotation(Mapping.class).featureId();
		ViewDto viewdto = listview.getView(featureId);
		log.info("===viewdto from common has been generated for JSON===");
		PageDto pageDto = new PageDto();
		pageDto.setPageNumber(0);
		pageDto.setPageSize(Constants.PAGESIZE);
		pageDto.setTotalCount(secondaryPackingTypeRepository.count());
		pageDto.setOperation("Client");//To be set from Database
		viewdto.setPage(pageDto);
		Class<SecondaryPackingTypeDTO> secondaryPackingTypeDTO = SecondaryPackingTypeDTO.class;
		Field[] fields = secondaryPackingTypeDTO.getDeclaredFields();
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
	
	public List<SecondaryPackingTypeDTO> readBasicDetails() {

		ViewDto view = getViewDetails(SecondaryPackingTypeController.class);
		List<SecondaryPackingTypeDTO> newList = new ArrayList<>();
		List<String> filterList = view.getFiltersArray();
		SecondaryPackingSpecificationsBuilder builder = new SecondaryPackingSpecificationsBuilder();
		for (int filterSize = 0; filterSize < filterList.size(); filterSize++) {
			builder.with(filterList.get(filterSize).substring(0, filterList.get(filterSize).indexOf("=")),
					filterList.get(filterSize).substring(filterList.get(filterSize).indexOf("=") + 1));
		}
		Specification<MasterSecondaryPackingType> spec = builder.build();
		Pageable pageRequest = ListViewUtil.createPageRequest(view);
		log.info("===Pageable object created from view object===");
		List<MasterSecondaryPackingType> masterSecondaryPackingTypeList = secondaryPackingTypeRepository.findAll(spec, pageRequest).getContent();
		for (MasterSecondaryPackingType masterSecondaryPackingType : masterSecondaryPackingTypeList) {
			SecondaryPackingTypeDTO secondaryPackingTypeDTO = masterSecondaryPackingType.convertBasicEntityToDto();
			newList.add(secondaryPackingTypeDTO);
		}
		return newList;
	}

}

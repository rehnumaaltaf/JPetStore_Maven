package com.olam.score.location.service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.olam.score.common.constants.Constants;
import com.olam.score.common.constants.ErrorConstants;
import com.olam.score.common.dto.PageDto;
import com.olam.score.common.dto.ResponseData;
import com.olam.score.common.dto.ViewDto;
import com.olam.score.common.util.CommonUtil;
import com.olam.score.common.util.ErrorMessageUtil;
import com.olam.score.common.util.ListViewUtil;
import com.olam.score.common.util.Mapping;
import com.olam.score.common.util.ScoreBaseException;
import com.olam.score.common.util.ScoreBaseExceptionResponse;
import com.olam.score.common.util.WebServiceCallUtil;
import com.olam.score.location.controller.GeoController;
import com.olam.score.location.domain.MasterGeography;
import com.olam.score.location.domain.MasterGeographyLevel;
import com.olam.score.location.dto.GeographyDto;
import com.olam.score.location.repository.GeoLevelRepository;
import com.olam.score.location.repository.GeoRepository;
import com.olam.score.location.util.GeoSpecificationsBuilder;


@Service
public class GeoService {
	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	public GeoRepository geoRepository;
	@Autowired
	public GeoLevelRepository typeRepository;
	@Autowired
	EntityManager entityManager;
	@Autowired
	private ListViewUtil listview;
	@Value("${reference.name}")
	public String referenceName;

	@Autowired
	private WebServiceCallUtil webServiceCall;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Transactional(readOnly=true)
	public List<GeographyDto> readAll(Model model) 
	{	
		ViewDto view =getViewDetails(GeoController.class);
		List<GeographyDto> newList = new ArrayList<>();
		List<String> filterList = view.getFiltersArray();
		Map<Integer, String> statusMap = webServiceCall.getAllStatus();
		GeoSpecificationsBuilder builder = new GeoSpecificationsBuilder();
		for (int filterSize = 0; filterSize < filterList.size(); filterSize++) {
			builder.with(filterList.get(filterSize).substring(0, filterList.get(filterSize).indexOf(Constants.EQUAL)),
					filterList.get(filterSize).substring(filterList.get(filterSize).indexOf(Constants.EQUAL) + 1));
		}
		if(model!=null){
			Map<String,Object> params=model.asMap();
			if (params.get(Constants.STATUS) != null) {
				Optional<Integer> statusId = statusMap.entrySet().stream().filter(e -> e.getValue().equalsIgnoreCase((String) params.get("status")))
						.map(Map.Entry::getKey).findFirst();
				if (statusId.isPresent()) {
					params.put("fkStatusId", statusId.get());
					params.remove(Constants.STATUS);
				}
				
				}
			params.forEach((attribute, value) -> {
				builder.with(attribute, value);
			});
			}
		Specification<MasterGeography> spec = builder.build();
		Pageable pageRequest = ListViewUtil.createPageRequest(view);
		log.info("===Pageable object created from view object===");
		List<MasterGeography> oldList = geoRepository.findAll(spec, pageRequest).getContent();
		ResponseEntity<ResponseData> marketDesk=webServiceCall.internalServiceCall(referenceName , Constants.MARKET_DESK_URL);
		ResponseData<List<Object>> marketDeskBody;
		List<Map<Object, Object>> marketDeskList =new ArrayList<>();
		if(marketDesk!=null && marketDesk.getBody()!=null){
			marketDeskBody= marketDesk.getBody();
			marketDeskList = (List<Map<Object, Object>>) marketDeskBody.getBody();
		}
		for (MasterGeography masterGeo : oldList) {
			GeographyDto geoDto = masterGeo.geographyModelToDto(statusMap,marketDeskList);
			newList.add(geoDto);
		}

		return newList;
	}

		public ViewDto getViewDetails(Class<? extends Object> currentfeature) {
		String featureId = currentfeature.getAnnotation(Mapping.class).featureId();
		ViewDto viewdto = listview.getView(featureId);
		log.info("===viewdto from common has been generated for JSON===");
		PageDto pageDto = new PageDto();
		pageDto.setPageNumber(0);
		pageDto.setPageSize(Constants.PAGESIZE);
		pageDto.setTotalCount(geoRepository.count());
		pageDto.setOperation("Client");//To be set from Database
		viewdto.setPage(pageDto);
		Class<GeographyDto> uomDto = GeographyDto.class;
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

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Transactional
	public GeographyDto create(GeographyDto geogrpahyDto) {
		MasterGeography masterGeoName = null;
		MasterGeography masterGeoCode = null;
		MasterGeography masterGeography = null;
		GeographyDto dto = null;
		String action = geogrpahyDto.getAction();
		if (action.equalsIgnoreCase(Constants.DRAFT)) {
			log.info("===draft action to perform for create ===");
			geogrpahyDto.setStatusName(Constants.DRAFT);
		} else if (action.equalsIgnoreCase(Constants.SAVE)) {
			log.info("===save(active) action to perform for create ===");
			geogrpahyDto.setStatusName(Constants.ACTIVE);
		}
		log.info("In Service class. Find geography with the name supplied by user");
		masterGeoName = geoRepository.findByGeographyName(geogrpahyDto.getGeoName().toUpperCase());
		log.info("In Service class. Find geography with the code supplied by user");
		masterGeoCode = geoRepository.findByGeoCode(geogrpahyDto.getGeoCode().toUpperCase());
		List<ScoreBaseExceptionResponse> errorBeans=new ArrayList<>();
		// Check for duplicates and throw exception
		if(masterGeoCode != null || masterGeoName != null){
		if (masterGeoName != null) {
			ErrorMessageUtil.errorHandler(new Object[]{geogrpahyDto.getGeoName()}, errorBeans, Constants.ERROR_DUPLICATE_GEO_NAME,Constants.ERROR_TYPE_DUPLICATE_NAME);		} 
		if (masterGeoCode != null) {
			ErrorMessageUtil.errorHandler(new Object[]{geogrpahyDto.getGeoCode()}, errorBeans, Constants.ERROR_DUPLICATE_GEO_CODE,Constants.ERROR_TYPE_DUPLICATE_CODE);
		}
		throw new ScoreBaseException(errorBeans, HttpStatus.CONFLICT);
		}
		log.info("Status Name : ");
		log.info(geogrpahyDto.getStatusName());
		Map<Integer, String> statusMap = webServiceCall.getAllStatus();
		masterGeography = geoRepository.saveAndFlush(generateGeoEntity(geogrpahyDto,statusMap,Constants.CREATE));
		ResponseEntity<ResponseData> marketDesk=webServiceCall.internalServiceCall(referenceName , Constants.MARKET_DESK_URL);
		ResponseData<List<Object>> marketDeskBody;
		List<Map<Object, Object>> marketDeskList =new ArrayList<>();
		if(marketDesk!=null && marketDesk.getBody()!=null){
			marketDeskBody= marketDesk.getBody();
			marketDeskList = (List<Map<Object, Object>>) marketDeskBody.getBody();
		}
		dto = masterGeography.geographyModelToDto(statusMap,marketDeskList);
		return dto;
	}
	private MasterGeography generateGeoEntity(GeographyDto geogrpahyDto, Map<Integer, String> statusMap,String action) {
		Integer statusId = null;
		MasterGeographyLevel geoType=new MasterGeographyLevel();
		MasterGeography parentGeo=null;
		Optional<Integer> statusId1 = statusMap.entrySet().stream()
				.filter(e -> e.getValue().equalsIgnoreCase(geogrpahyDto.getStatusName())).map(Map.Entry::getKey).findFirst();
		if (statusId1.isPresent()) {
			statusId = statusId1.get();
		}
		if(geogrpahyDto.getGeoTypeId()!=null){
		geoType=typeRepository.findOne(geogrpahyDto.getGeoTypeId());
		}
		if(geogrpahyDto.getGeoParentId()!=null){
		parentGeo=geoRepository.findOne(geogrpahyDto.getGeoParentId());
		}
		return geogrpahyDto.geographyDtoToModel(action, statusId,geoType,parentGeo);
	}

	@Transactional
	public Map<Integer,String> deleteGeography(List<Map<String, Integer>> body) {
		log.info("In GeoService class deleteGeography().");
		Map<Integer, String> statusMap = webServiceCall.getAllStatus();
		Map<Integer,String> deleteStatusMap=new HashMap<>();
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
			String message="";
			MasterGeography geography;
			geography = geoRepository.findOne(map.get(Constants.GEO_ID));
			if(geography==null){
				message = CommonUtil.getTranslationMessage(Locale.getDefault(), ErrorConstants.RECORD_NOT_FOUND.getErrorConstants());
				deleteStatusMap.put(map.get(Constants.GEO_ID), message);
				continue;
			}
			if (geography.getFkStatusId() != null) {
				String currentStatusName =statusMap.get(geography.getFkStatusId());
				log.info("Current Status Name : ");
				log.info(currentStatusName);
				if (currentStatusName.equals(Constants.ACTIVE)) {
					geography.setFkStatusId(inactiveStatusId);
					geoRepository.saveAndFlush(geography);
					message = CommonUtil.getTranslationMessage(Locale.getDefault(), ErrorConstants.STATUS_INACTIVATED.getErrorConstants());
					deleteStatusMap.put(map.get(Constants.GEO_ID), message);
				} else if (currentStatusName.equals(Constants.INACTIVE)) {

					geography.setFkStatusId(activeStatusId);
					geoRepository.saveAndFlush(geography);
					message = CommonUtil.getTranslationMessage(Locale.getDefault(), ErrorConstants.STATUS_ACTIVATED.getErrorConstants());
					deleteStatusMap.put(map.get(Constants.GEO_ID), message);
				} else if (currentStatusName.equals(Constants.DRAFT)) {
					geoRepository.delete(geography);
					message = CommonUtil.getTranslationMessage(Locale.getDefault(), ErrorConstants.RECORD_DELETED.getErrorConstants());
					deleteStatusMap.put(map.get(Constants.GEO_ID), message);
				}else{
					message = CommonUtil.getTranslationMessage(Locale.getDefault(), ErrorConstants.INVALID_STATUS.getErrorConstants());
					deleteStatusMap.put(map.get(Constants.GEO_ID),message);
				}

			}

		}
		return deleteStatusMap;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Transactional(readOnly=true)
	public GeographyDto readById(Integer geographyId) {
		MasterGeography geo = geoRepository.findOne(geographyId);
		Map<Integer, String> statusMap = webServiceCall.getAllStatus();
		ResponseEntity<ResponseData> marketDesk=webServiceCall.internalServiceCall(referenceName , Constants.MARKET_DESK_URL);
		ResponseData<List<Object>> marketDeskBody;
		List<Map<Object, Object>> marketDeskList =new ArrayList<>();
		if(marketDesk!=null && marketDesk.getBody()!=null){
			marketDeskBody= marketDesk.getBody();
			marketDeskList = (List<Map<Object, Object>>) marketDeskBody.getBody();
		}		return geo.geographyModelToDto(statusMap, marketDeskList);
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Transactional
	public GeographyDto update(GeographyDto geogrpahyDto) {
		GeographyDto dto = null;
		MasterGeography masterGeoName = null;
		MasterGeography masterGeoCode = null;
		MasterGeography masterGeography = null;
		log.info("In Service class. Find geography with the name supplied by user");
		masterGeoName = geoRepository.findByGeographyName(geogrpahyDto.getGeoName().toUpperCase());
		log.info("In Service class. Find geography with the code supplied by user");
		masterGeoCode = geoRepository.findByGeoCode(geogrpahyDto.getGeoCode().toUpperCase());
		List<ScoreBaseExceptionResponse> errorBeans=new ArrayList<>();
		// Check for duplicates and throw exception
		if((masterGeoCode != null && !masterGeoCode.getPkGeoId().equals(geogrpahyDto.getPkGeoId())) || (masterGeoName != null && !masterGeoName.getPkGeoId().equals(geogrpahyDto.getPkGeoId()))){
		if (masterGeoName != null && !masterGeoName.getPkGeoId().equals(geogrpahyDto.getPkGeoId())) {
			ErrorMessageUtil.errorHandler(new Object[]{geogrpahyDto.getGeoName()}, errorBeans, Constants.ERROR_DUPLICATE_GEO_NAME,Constants.ERROR_TYPE_DUPLICATE_NAME);
		} 
		if (masterGeoCode != null && !masterGeoCode.getPkGeoId().equals(geogrpahyDto.getPkGeoId())) {
			ErrorMessageUtil.errorHandler(new Object[]{geogrpahyDto.getGeoCode()}, errorBeans, Constants.ERROR_DUPLICATE_GEO_CODE,Constants.ERROR_TYPE_DUPLICATE_CODE);
		}
		throw new ScoreBaseException(errorBeans, HttpStatus.CONFLICT);
		}
		log.info("Status Name : ");
		log.info(geogrpahyDto.getStatusName());
		Map<Integer, String> statusMap = webServiceCall.getAllStatus();
		masterGeography = geoRepository.saveAndFlush(generateGeoEntity(geogrpahyDto,statusMap,Constants.UPDATE));
		ResponseEntity<ResponseData> marketDesk=webServiceCall.internalServiceCall(referenceName , Constants.MARKET_DESK_URL);
		ResponseData<List<Object>> marketDeskBody;
		List<Map<Object, Object>> marketDeskList =new ArrayList<>();
		if(marketDesk!=null && marketDesk.getBody()!=null){
			marketDeskBody= marketDesk.getBody();
			marketDeskList = (List<Map<Object, Object>>) marketDeskBody.getBody();
		}
		dto = masterGeography.geographyModelToDto(statusMap,marketDeskList);

		return dto;
	}

}

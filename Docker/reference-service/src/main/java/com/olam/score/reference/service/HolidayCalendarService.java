package com.olam.score.reference.service;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.olam.score.common.constants.Constants;
import com.olam.score.common.domain.UserBean;
import com.olam.score.common.dto.PageDto;
import com.olam.score.common.dto.ResponseData;
import com.olam.score.common.dto.ViewDto;
import com.olam.score.common.util.CommonUtil;
import com.olam.score.common.util.EnumStatus;
import com.olam.score.common.util.ListViewUtil;
import com.olam.score.common.util.Mapping;
import com.olam.score.common.util.ScoreBaseException;
import com.olam.score.common.util.WebServiceCallUtil;
import com.olam.score.reference.domain.MasterHolidayCalender;
import com.olam.score.reference.domain.MasterHolidayList;
import com.olam.score.reference.domain.MasterShipmentWeekTimeframe;
import com.olam.score.reference.dto.HolidayCalendarDto;
import com.olam.score.reference.dto.HolidayListDto;
import com.olam.score.reference.repository.HolidayCalendarRepository;
import com.olam.score.reference.util.HolidayCalenderSpecificationsBuilder;

@Service
public class HolidayCalendarService {

	private final Logger log = LoggerFactory.getLogger(getClass());
	@Autowired 
	private WebServiceCallUtil webServiceCall;
	@Autowired
	private ListViewUtil listview;
	@Autowired
	private HolidayCalendarRepository repository;
	/*@Autowired
	private HolidayListRepository repository1;*/
	@Autowired
	EntityManager entityManager;

	@Value("${location.name}")
	private String referenceName;

	@Transactional
	public HolidayCalendarDto create(HolidayCalendarDto holidayCalendarDto) {
		MasterHolidayCalender masterHolidayCalender;
		HolidayCalendarDto dto;
		UserBean userBean = new UserBean();
		Locale locale;
		String action = holidayCalendarDto.getStatusName();
		if (action.equalsIgnoreCase(Constants.DRAFT)) {
			log.info("===draft action to perform for create ===");
			holidayCalendarDto.setStatusName(Constants.DRAFT);
		} else if (action.equalsIgnoreCase(Constants.SAVE)) {
			log.info("===save(active) action to perform for create ===");
			holidayCalendarDto.setStatusName(Constants.ACTIVE);
		}
		Map<Integer, String> statusMap = webServiceCall.getAllStatus();

		String holidayCalenderName;
		String holidayCalenderCode;
		holidayCalenderName = repository.findByHolidayCalName(holidayCalendarDto.getHolidayCalName());
		holidayCalenderCode = repository.findByHolidayCalCode(holidayCalendarDto.getHolidayCalCode());
		//masterHolidayCalender = repository.findByHolidayCalCode(holidayCalendarDto.getHolidayCalCode());
		locale=userBean.getLocale();
		if(locale==null){
			locale=Locale.getDefault();
		}
		String message=CommonUtil.getTranslationMessage(locale, "error_duplicate");
		if((holidayCalenderName!=null) && (holidayCalenderCode!=null))
		{
			message=message.replace("$", holidayCalenderCode +" Code"+ " and " +holidayCalenderName+" Name");
			throw new ScoreBaseException(0, message, HttpStatus.CONFLICT);
		}else if((holidayCalenderName!=null))
		{
			message=message.replace("$", holidayCalenderName+" Name");
			throw new ScoreBaseException(0, message, HttpStatus.CONFLICT);
		}else if((holidayCalenderCode!=null))
		{
			message=message.replace("$", holidayCalenderCode+" Code");
			throw new ScoreBaseException(0, message, HttpStatus.CONFLICT);
		}else{

			/*if (masterHolidayCalender != null) {
			log.error("===Duplicate HolidayCalendar Code found in Database===");
			message=message.replace("$", holidayCalendarDto.getHolidayCalCode());
			throw new ScoreBaseException(masterHolidayCalender.getPkHolidayCalId(), message, HttpStatus.CONFLICT);
		} else {*/
			masterHolidayCalender = repository.saveAndFlush(generateMasterHolidayCal(holidayCalendarDto, "create",statusMap));
			dto = masterHolidayCalender.convertToHolidayCalendarDto(statusMap,null);
		}
		return dto;
	}

	/*
	 * Generate the Master HolidayCalender
	 */
	private MasterHolidayCalender generateMasterHolidayCal(HolidayCalendarDto holidayCalendarDto, String action, Map<Integer,String> statusMap) {

		MasterHolidayCalender masterHolidayCalender = new MasterHolidayCalender();
		Optional<Integer> statusId = statusMap.entrySet().stream()
				.filter(e -> e.getValue().equals(holidayCalendarDto.getStatusName()))
				.map(Map.Entry::getKey)
				.findFirst();
		if(statusId.isPresent()){
			log.info("===Got Status Id in Status map ===");
			masterHolidayCalender.setFkStatusId(statusId.get());
		}
		//trim() to avoid space
		masterHolidayCalender.setHolidayCalCode(holidayCalendarDto.getHolidayCalCode().trim());
		masterHolidayCalender.setHolidayCalName(holidayCalendarDto.getHolidayCalName().trim());
		//check science trim() cannot be applied on null
		if(holidayCalendarDto.getHolidayCalDesc()!=null)
		masterHolidayCalender.setHolidayCalDesc(holidayCalendarDto.getHolidayCalDesc().trim());
		masterHolidayCalender.setFkGeoId(holidayCalendarDto.getGeoId());

		if ("create".equalsIgnoreCase(action)) {
			masterHolidayCalender.setCreatedBy("Test");
			masterHolidayCalender.setCreatedDate(new Timestamp(System.currentTimeMillis()));
			masterHolidayCalender.setModifiedBy("Test");
			masterHolidayCalender.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		}
		else if ("update".equalsIgnoreCase(action)) {
			masterHolidayCalender.setPkHolidayCalId(holidayCalendarDto.getHolidayCalId());
			masterHolidayCalender.setModifiedBy("Test");
			masterHolidayCalender.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		}

		Collection<MasterHolidayList> masterExternalGlMappingCollection = new ArrayList<>();
		for (HolidayListDto holidayListDto : holidayCalendarDto.getHolidayListDto()) {
			/*if(holidayListDto.getIsDeleted()!=null && holidayListDto.getIsDeleted().equalsIgnoreCase("Y"))
			{
				repository.deleteByHolidayListId(holidayListDto.getHolidayListId());
				continue;
			}*/
			MasterHolidayList masterHolidayList = new MasterHolidayList();
			masterHolidayList.setFkHolidayCalId(masterHolidayCalender);
			masterHolidayList.setFkStatusId(statusId.get());
			masterHolidayList.setHolidayListName(holidayListDto.getHolidayListName().trim());
			/*DateFormat formatter;
			formatter = new SimpleDateFormat("yyyy-MM-dd");//("dd/MM/yyyy");
			// you can change format of date
			Date date;
			try {
				date = formatter.parse(holidayListDto.getHolidayListDate());
				java.sql.Timestamp timeStampDate = new Timestamp(date.getTime());
				masterHolidayList.setHolidayListDate(timeStampDate);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}*/
			 System.out.println("holidayListDto.getHolidayListDate() ");
            System.out.println("holidayListDto.getHolidayListDate() "+holidayListDto.getHolidayListDate());
			masterHolidayList.setHolidayListDate(holidayListDto.getHolidayListDate());


			if ("create".equalsIgnoreCase(action)) {
				masterHolidayList.setCreatedBy("Test");// TODO: Hard Coding needs changes
				masterHolidayList.setCreatedDate(new Timestamp(System.currentTimeMillis()));
			}
			else if ("update".equalsIgnoreCase(action)) {
				if(holidayListDto.getHolidayListId()!=null){
					masterHolidayList.setPkHolidayListId(holidayListDto.getHolidayListId());
				}
				else{
					masterHolidayList.setCreatedBy("Test");// TODO: Hard Coding needs changes
					masterHolidayList.setCreatedDate(new Timestamp(System.currentTimeMillis()));
				}
				masterHolidayList.setModifiedBy("Test");// TODO: Hard Coding needs changes
				masterHolidayList.setModifiedDate(new Timestamp(System.currentTimeMillis()));
			}
			masterExternalGlMappingCollection.add(masterHolidayList);
		}
		
		if(holidayCalendarDto.getDeletedIds()!=null && !holidayCalendarDto.getDeletedIds().isEmpty())
		{
			for(Integer idToBeDeleted:holidayCalendarDto.getDeletedIds())
			{
				repository.deleteByHolidayListId(idToBeDeleted);
			}
			
		}
		masterHolidayCalender.setMasterHolidayListCollection(masterExternalGlMappingCollection);

		return masterHolidayCalender;
	}

	@SuppressWarnings({"unchecked","rawtypes" })
	@Transactional
	public HolidayCalendarDto readById(Integer id) {

		MasterHolidayCalender masterHolidayCalender = repository.findOne(id);
		Map<Integer, String> statusMap = webServiceCall.getAllStatus();
		//goeservice rest call
		ResponseEntity<ResponseData> geoResponseEntity = webServiceCall.internalServiceCall(referenceName, "/location/geoservice/geo");
		ResponseData<List<Object>> geoBody = geoResponseEntity.getBody();
		List<Map<Object, Object>> geoList = (List<Map<Object, Object>>) geoBody.getBody();

		return masterHolidayCalender.convertToHolidayCalendarDto(statusMap, geoList);
	}

	@Transactional
	public void deleteHolidayCalendar(List<Map<String, Integer>> body) {
		for (Map<String, Integer> i : body) {
			i.forEach((attribute, holidayCalenderId) -> {
				MasterHolidayCalender masterHolidayCalender = new MasterHolidayCalender();
				masterHolidayCalender = repository.findOne(holidayCalenderId);
				if (masterHolidayCalender.getFkStatusId() != 0 && EnumStatus.ACTIVE.getValue() == masterHolidayCalender.getFkStatusId()) {
					log.info(holidayCalenderId.toString());
					log.info("===Changing Active status to Inactive===");
					masterHolidayCalender.setFkStatusId(EnumStatus.INACTIVE.getValue());
					if(!masterHolidayCalender.getMasterHolidayListCollection().isEmpty()){
						for(MasterHolidayList holidayList:masterHolidayCalender.getMasterHolidayListCollection()){
							holidayList.setFkStatusId(EnumStatus.INACTIVE.getValue());
						}
					}
					repository.saveAndFlush(masterHolidayCalender);
				} else if (masterHolidayCalender.getFkStatusId() != 0 && EnumStatus.DRAFT.getValue() == masterHolidayCalender.getFkStatusId()) {
					log.info(holidayCalenderId.toString());
					log.info("===Deleting the draft HolidayCalendar===");
					repository.delete(masterHolidayCalender);
				} else if (masterHolidayCalender.getFkStatusId() != 0
						&& EnumStatus.INACTIVE.getValue() == masterHolidayCalender.getFkStatusId()) {
					masterHolidayCalender.setFkStatusId(EnumStatus.ACTIVE.getValue());
					if(!masterHolidayCalender.getMasterHolidayListCollection().isEmpty()){
						for(MasterHolidayList holidayList:masterHolidayCalender.getMasterHolidayListCollection()){
							holidayList.setFkStatusId(EnumStatus.ACTIVE.getValue());
						}
					}
					repository.saveAndFlush(masterHolidayCalender);
				}
			});

		}

	}

	@SuppressWarnings({"unchecked","rawtypes" })
	public List<HolidayCalendarDto> update(List<HolidayCalendarDto> body) {
		List<HolidayCalendarDto> newList = new ArrayList<>();
		MasterHolidayCalender masterHolidayCalender;
		Map<Integer, String> statusMap = webServiceCall.getAllStatus();
		UserBean userBean = new UserBean();
		Locale locale;
		locale=userBean.getLocale();
		if(locale==null){
			locale=Locale.getDefault();
		}
		for (HolidayCalendarDto holidayCalendarDto : body) {
			MasterHolidayCalender holidayCalenderNameId = null;
			MasterHolidayCalender holidayCalenderCodeId = null;
			List<MasterHolidayCalender> nameList = repository.findByholidayCalNameandId(holidayCalendarDto.getHolidayCalName());
			List<MasterHolidayCalender> codeList = repository.findByholidayCalCodeandId(holidayCalendarDto.getHolidayCalCode());
			if(!nameList.isEmpty())
			holidayCalenderNameId = nameList.get(0);
			if(!codeList.isEmpty())
			holidayCalenderCodeId = codeList.get(0);
			//masterHolidayCalender = repository.findByHolidayCalCode(holidayCalendarDto.getHolidayCalCode());
			String message=CommonUtil.getTranslationMessage(locale, "error_duplicate");
			if((holidayCalenderNameId!=null && !holidayCalenderNameId.getPkHolidayCalId().equals(holidayCalendarDto.getHolidayCalId())) && (holidayCalenderCodeId!=null && !holidayCalenderCodeId.getPkHolidayCalId().equals(holidayCalendarDto.getHolidayCalId())))
			{
				message=message.replace("$", holidayCalenderCodeId.getHolidayCalCode() +" Code"+ " and " +holidayCalenderNameId.getHolidayCalName()+" Name");
				throw new ScoreBaseException(0, message, HttpStatus.CONFLICT);
			}else if(holidayCalenderNameId!=null && !holidayCalenderNameId.getPkHolidayCalId().equals(holidayCalendarDto.getHolidayCalId()))
			{
				message=message.replace("$", holidayCalenderNameId.getHolidayCalName()+" Name");
				throw new ScoreBaseException(0, message, HttpStatus.CONFLICT);
			}else if(holidayCalenderCodeId!=null && !holidayCalenderCodeId.getPkHolidayCalId().equals(holidayCalendarDto.getHolidayCalId()))
			{
				message=message.replace("$", holidayCalenderCodeId.getHolidayCalCode()+" Code");
				throw new ScoreBaseException(0, message, HttpStatus.CONFLICT);
			}else{
			//repository.findOne(holidayCalendarDto.getHolidayCalId()).getMasterHolidayListCollection().clear();
			masterHolidayCalender = repository.saveAndFlush(generateMasterHolidayCal(holidayCalendarDto, "update", statusMap));
			log.info("===Updated the HolidayCalender ID===");
			//goeservice rest call
			ResponseEntity<ResponseData> geoResponseEntity = webServiceCall.internalServiceCall(referenceName, "/location/geoservice/geo");
			ResponseData<List<Object>> geoBody = geoResponseEntity.getBody();
			List<Map<Object, Object>> geoList = (List<Map<Object, Object>>) geoBody.getBody();
			holidayCalendarDto = masterHolidayCalender.convertToHolidayCalendarDto(statusMap,geoList);
			newList.add(holidayCalendarDto);
			}
		}
		return newList;
	}

	/*@Transactional
	@SuppressWarnings({"unchecked","rawtypes" })
	public List<HolidayCalendarDto> readAll() {
		List<HolidayCalendarDto> newList = new ArrayList<>();
		List<String> sortColumns=new ArrayList<>();
		sortColumns.add("createdDate");
		sortColumns.add("modifiedDate");
		Sort sort = new Sort(Direction.DESC,sortColumns);
		List<MasterHolidayCalender> oldList = repository.findAll(sort);	
		Map<Integer, String> statusMap = webServiceCall.getAllStatus();
		//goeservice rest call
		ResponseEntity<ResponseData> geoResponseEntity = webServiceCall.internalServiceCall(geourl + "/geoservice/geo");
		ResponseData<List<Object>> geoBody = geoResponseEntity.getBody();
		List<Map<Object, Object>> geoList = (List<Map<Object, Object>>) geoBody.getBody();
		if(oldList==null)
			throw new ScoreBaseException(0, "No Record Found", HttpStatus.NOT_FOUND);
		for (MasterHolidayCalender masterHolidayCalender : oldList) {
			HolidayCalendarDto dto = masterHolidayCalender.convertToHolidayCalendarDto(statusMap,geoList);
			newList.add(dto);
		}
		return newList;
	}*/

	@Transactional
	@SuppressWarnings({"unchecked","rawtypes" })
	public List<HolidayCalendarDto> readAll(ViewDto view) {
		List<HolidayCalendarDto> newList = new ArrayList<>();
		List<String> filterList = view.getFiltersArray();
		HolidayCalenderSpecificationsBuilder builder = new HolidayCalenderSpecificationsBuilder();
		for (int filterSize = 0; filterSize < filterList.size(); filterSize++) {
			builder.with(filterList.get(filterSize).substring(0, filterList.get(filterSize).indexOf("=")),
					filterList.get(filterSize).substring(filterList.get(filterSize).indexOf("=") + 1));
		}
		Specification<MasterHolidayCalender> spec = builder.build();
		Pageable pageRequest = ListViewUtil.createPageRequest(view);
		log.info("===Pageable object created from view object===");
		List<MasterHolidayCalender> oldList = repository.findAll(spec, pageRequest).getContent();
		Map<Integer, String> statusMap = webServiceCall.getAllStatus();
		//geoservice rest call
		List<Map<Object, Object>> geoList;
		try{
		ResponseEntity<ResponseData> geoResponseEntity = webServiceCall.internalServiceCall(referenceName, "/location/geoservice/geo");
		ResponseData<List<Object>> geoBody = geoResponseEntity.getBody();
		/*List<Map<Object, Object>> */geoList = (List<Map<Object, Object>>) geoBody.getBody();
		}catch (Exception e) {
			// TODO: handle exception
			geoList=null;
		}
		for (MasterHolidayCalender masterHolidayCalender : oldList) {
			HolidayCalendarDto holidayCalendarDto;
			holidayCalendarDto = masterHolidayCalender.convertToHolidayCalendarDto(statusMap, geoList);
			newList.add(holidayCalendarDto);
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
		pageDto.setTotalCount(repository.count());
		pageDto.setOperation("Client");//To be set from Database
		viewdto.setPage(pageDto);
		Class<HolidayCalendarDto> holidayCalendarDto = HolidayCalendarDto.class;
		Field[] fields = holidayCalendarDto.getDeclaredFields();
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
}

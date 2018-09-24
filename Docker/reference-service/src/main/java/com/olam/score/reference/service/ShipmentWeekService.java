package com.olam.score.reference.service;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.ArrayList;
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
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.olam.score.common.constants.Constants;
import com.olam.score.common.domain.UserBean;
import com.olam.score.common.dto.PageDto;
import com.olam.score.common.dto.ViewDto;
import com.olam.score.common.util.CommonUtil;
import com.olam.score.common.util.EnumStatus;
import com.olam.score.common.util.ListViewUtil;
import com.olam.score.common.util.Mapping;
import com.olam.score.common.util.ScoreBaseException;
import com.olam.score.common.util.WebServiceCallUtil;
import com.olam.score.reference.domain.MasterShipmentRule;
import com.olam.score.reference.domain.MasterShipmentWeekTimeframe;
import com.olam.score.reference.dto.ShipmentRuleDto;
import com.olam.score.reference.dto.ShipmentWeekDto;
import com.olam.score.reference.repository.ShipmentWeekRepository;
import com.olam.score.reference.util.ShipmentWeekSpecificationsBuilder;

@Service
public class ShipmentWeekService {

	private final Logger log = LoggerFactory.getLogger(getClass());
	@Autowired 
	private WebServiceCallUtil webServiceCall;
	@Autowired
	private ListViewUtil listview;
	@Autowired
	private ShipmentWeekRepository repository;
	@Autowired
	EntityManager entityManager;
	
	@Transactional
	public ShipmentWeekDto create(ShipmentWeekDto shipmentWeekDto) {
		MasterShipmentWeekTimeframe masterShipmentWeekTimeframe;
		ShipmentWeekDto dto;
		UserBean userBean = new UserBean();
		Locale locale;
		String action = shipmentWeekDto.getStatusName();
		if (action.equalsIgnoreCase(Constants.DRAFT)) {
			log.info("===draft action to perform for create ===");
			shipmentWeekDto.setStatusName(Constants.DRAFT);
		} else if (action.equalsIgnoreCase(Constants.SAVE)) {
			log.info("===save(active) action to perform for create ===");
			shipmentWeekDto.setStatusName(Constants.ACTIVE);
		}
		Map<Integer, String> statusMap = webServiceCall.getAllStatus();

		String shipmentWeekName;
		String shipmentWeekCode;
		shipmentWeekName = repository.findByShipmentWeekName(shipmentWeekDto.getShipmentTimeframeName());
		shipmentWeekCode = repository.findByShipmentWeekCode(shipmentWeekDto.getShipmentTimeframeCode());
		locale=userBean.getLocale();
		if(locale==null){
			locale=Locale.getDefault();
		}
		String message=CommonUtil.getTranslationMessage(locale, "error_duplicate");
		if((shipmentWeekName!=null) && (shipmentWeekCode!=null))
		{
			message=message.replace("$", shipmentWeekCode +" Code"+ " and " +shipmentWeekName+" Name");
			throw new ScoreBaseException(0, message, HttpStatus.CONFLICT);
		}else if((shipmentWeekName!=null))
		{
			message=message.replace("$", shipmentWeekName+" Name");
			throw new ScoreBaseException(0, message, HttpStatus.CONFLICT);
		}else if((shipmentWeekCode!=null))
		{
			message=message.replace("$", shipmentWeekCode+" Code");
			throw new ScoreBaseException(0, message, HttpStatus.CONFLICT);
		}else{
			masterShipmentWeekTimeframe = repository.saveAndFlush(generateShipmentWeek(shipmentWeekDto, "create",statusMap));
			dto = masterShipmentWeekTimeframe.convertToShipmentWeekDto(statusMap);
		}
		return dto;
	}

	/*
	 * Generate the Master ShipmentWeek
	 */
	private MasterShipmentWeekTimeframe generateShipmentWeek(ShipmentWeekDto shipmentWeekDto, String action, Map<Integer,String> statusMap) {

		MasterShipmentWeekTimeframe masterShipmentWeekTimeframe = new MasterShipmentWeekTimeframe();
		Optional<Integer> statusId = statusMap.entrySet().stream()
				.filter(e -> e.getValue().equals(shipmentWeekDto.getStatusName()))
				.map(Map.Entry::getKey)
				.findFirst();
		if(statusId.isPresent()){
			log.info("===Got Status Id in Status map ===");
			masterShipmentWeekTimeframe.setFkStatusId(statusId.get());
		}
		masterShipmentWeekTimeframe.setShipmentTimeframeCode(shipmentWeekDto.getShipmentTimeframeCode());
		masterShipmentWeekTimeframe.setShipmentTimeframeName(shipmentWeekDto.getShipmentTimeframeName());
		masterShipmentWeekTimeframe.setShipmentTimeframeDesc(shipmentWeekDto.getShipmentTimeframeDesc());
		masterShipmentWeekTimeframe.setPrintDescription(shipmentWeekDto.getPrintDescription());
		masterShipmentWeekTimeframe.setRuleText(shipmentWeekDto.getRuleText());
		masterShipmentWeekTimeframe.setFkShipmentRuleId(repository.findByShipmentRuleId(shipmentWeekDto.getShipmentRuleId()));

		if ("create".equalsIgnoreCase(action)) {
			masterShipmentWeekTimeframe.setCreatedBy("Test");
			masterShipmentWeekTimeframe.setCreatedDate(new Timestamp(System.currentTimeMillis()));
			//modified added sortby:modified date
			masterShipmentWeekTimeframe.setModifiedBy("Test");
			masterShipmentWeekTimeframe.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		}
		else if ("update".equalsIgnoreCase(action)) {
			masterShipmentWeekTimeframe.setPkShipmentWeekTimeframeId(shipmentWeekDto.getShipmentWeekTimeframeId());
			masterShipmentWeekTimeframe.setModifiedBy("Test");
			masterShipmentWeekTimeframe.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		}

		return masterShipmentWeekTimeframe;
	}

	@SuppressWarnings({"unchecked","rawtypes" })
	@Transactional
	public ShipmentWeekDto readById(Integer id) {

		MasterShipmentWeekTimeframe masterShipmentWeekTimeframe = repository.findOne(id);
		Map<Integer, String> statusMap = webServiceCall.getAllStatus();
		//repository.getRuleById();
		return masterShipmentWeekTimeframe.convertToShipmentWeekDto(statusMap);
	}

	@Transactional
	public void deleteHolidayCalendar(List<Map<String, Integer>> body) {
		for (Map<String, Integer> i : body) {
			i.forEach((attribute, shipmentWeekTimeframeId) -> {
				MasterShipmentWeekTimeframe masterShipmentWeekTimeframe = new MasterShipmentWeekTimeframe();
				masterShipmentWeekTimeframe = repository.findOne(shipmentWeekTimeframeId);
				if (masterShipmentWeekTimeframe.getFkStatusId() != 0 && EnumStatus.ACTIVE.getValue() == masterShipmentWeekTimeframe.getFkStatusId()) {
					log.info(shipmentWeekTimeframeId.toString());
					log.info("===Changing Active status to Inactive===");
					masterShipmentWeekTimeframe.setFkStatusId(EnumStatus.INACTIVE.getValue());
					repository.saveAndFlush(masterShipmentWeekTimeframe);
				} else if (masterShipmentWeekTimeframe.getFkStatusId() != 0 && EnumStatus.DRAFT.getValue() == masterShipmentWeekTimeframe.getFkStatusId()) {
					log.info(shipmentWeekTimeframeId.toString());
					log.info("===Deleting the draft HolidayCalendar===");
					repository.delete(masterShipmentWeekTimeframe);
				} else if (masterShipmentWeekTimeframe.getFkStatusId() != 0
						&& EnumStatus.INACTIVE.getValue() == masterShipmentWeekTimeframe.getFkStatusId()) {
					masterShipmentWeekTimeframe.setFkStatusId(EnumStatus.ACTIVE.getValue());
					repository.saveAndFlush(masterShipmentWeekTimeframe);
				}
			});

		}

	}

	@SuppressWarnings({"unchecked","rawtypes" })
	public List<ShipmentWeekDto> update(List<ShipmentWeekDto> body) {
		List<ShipmentWeekDto> newList = new ArrayList<>();
		MasterShipmentWeekTimeframe masterShipmentWeekTimeframe;
		Map<Integer, String> statusMap = webServiceCall.getAllStatus();			
			
		for (ShipmentWeekDto shipmentWeekDto : body) {
			UserBean userBean = new UserBean();
			Locale locale;
			locale=userBean.getLocale();
			MasterShipmentWeekTimeframe shipmentWeekNameId=null;
			MasterShipmentWeekTimeframe shipmentWeekCodeId=null;
			List<MasterShipmentWeekTimeframe> nameList= repository.findByShipmentWeekNameEdit(shipmentWeekDto.getShipmentTimeframeName());
			List<MasterShipmentWeekTimeframe> codeList= repository.findByShipmentWeekCodeEdit(shipmentWeekDto.getShipmentTimeframeCode());
			if(!nameList.isEmpty())
			shipmentWeekNameId = nameList.get(0);
			if(!codeList.isEmpty())
			shipmentWeekCodeId = codeList.get(0);
			
			if(locale==null)
			{
				locale=Locale.getDefault();
			}
			String message=CommonUtil.getTranslationMessage(locale, "error_duplicate");
			
			if((shipmentWeekNameId!=null && !shipmentWeekNameId.getPkShipmentWeekTimeframeId().equals(shipmentWeekDto.getShipmentWeekTimeframeId())) && (shipmentWeekCodeId!=null && !shipmentWeekCodeId.getPkShipmentWeekTimeframeId().equals(shipmentWeekDto.getShipmentWeekTimeframeId())))
			{
				message=message.replace("$", shipmentWeekCodeId.getShipmentTimeframeCode() +" Code"+ " and " +shipmentWeekNameId.getShipmentTimeframeName()+" Name");
				throw new ScoreBaseException(shipmentWeekCodeId.getPkShipmentWeekTimeframeId(), message, HttpStatus.CONFLICT);
			}else if((shipmentWeekNameId!=null) && !shipmentWeekNameId.getPkShipmentWeekTimeframeId().equals(shipmentWeekDto.getShipmentWeekTimeframeId()))
			{
				message=message.replace("$", shipmentWeekNameId.getShipmentTimeframeName()+" Name");
				System.out.println("message "+message);
				throw new ScoreBaseException(0, message, HttpStatus.CONFLICT);
			}else if((shipmentWeekCodeId!=null)&& !shipmentWeekCodeId.getPkShipmentWeekTimeframeId().equals(shipmentWeekDto.getShipmentWeekTimeframeId()))
			{
				message=message.replace("$", shipmentWeekCodeId.getShipmentTimeframeCode()+" Code");
				throw new ScoreBaseException(0, message, HttpStatus.CONFLICT);
			}
			else{		
			masterShipmentWeekTimeframe = repository.saveAndFlush(generateShipmentWeek(shipmentWeekDto, "update", statusMap));
			log.info("===Updated the Shipment Week ID===");			
			shipmentWeekDto = masterShipmentWeekTimeframe.convertToShipmentWeekDto(statusMap);
			newList.add(shipmentWeekDto);
			}
		}
		return newList;
	}

	@Transactional
	@SuppressWarnings({"unchecked","rawtypes" })
	public List<ShipmentWeekDto> readAll1() {
		List<ShipmentWeekDto> newList = new ArrayList<>();
		List<String> sortColumns=new ArrayList<>();
		sortColumns.add("createdDate");
		sortColumns.add("modifiedDate");
		Sort sort = new Sort(Direction.DESC,sortColumns);
		List<MasterShipmentWeekTimeframe> oldList = repository.findAll(sort);	
		Map<Integer, String> statusMap = webServiceCall.getAllStatus();

		if(oldList==null)
			throw new ScoreBaseException(0, "No Record Found", HttpStatus.NOT_FOUND);
		for (MasterShipmentWeekTimeframe masterShipmentWeekTimeframe : oldList) {
			ShipmentWeekDto dto = masterShipmentWeekTimeframe.convertToShipmentWeekDto(statusMap);
			newList.add(dto);
		}
		return newList;
	}

	@Transactional
	@SuppressWarnings({"unchecked","rawtypes" })
	public List<ShipmentWeekDto> readAll(ViewDto view) {
		List<ShipmentWeekDto> newList = new ArrayList<>();
		List<String> filterList = view.getFiltersArray();
		ShipmentWeekSpecificationsBuilder builder = new ShipmentWeekSpecificationsBuilder();
		for (int filterSize = 0; filterSize < filterList.size(); filterSize++) {
			builder.with(filterList.get(filterSize).substring(0, filterList.get(filterSize).indexOf("=")),
					filterList.get(filterSize).substring(filterList.get(filterSize).indexOf("=") + 1));
		}
		Specification<MasterShipmentWeekTimeframe> spec = builder.build();
		Pageable pageRequest = ListViewUtil.createPageRequest(view);
		log.info("===Pageable object created from view object===");
		List<MasterShipmentWeekTimeframe> oldList = repository.findAll(spec, pageRequest).getContent();
		Map<Integer, String> statusMap = webServiceCall.getAllStatus();

		for (MasterShipmentWeekTimeframe masterShipmentWeekTimeframe : oldList) {
			ShipmentWeekDto shipmentWeekDto;
			shipmentWeekDto = masterShipmentWeekTimeframe.convertToShipmentWeekDto(statusMap);
			newList.add(shipmentWeekDto);
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
		Class<ShipmentWeekDto> shipmentWeekDto = ShipmentWeekDto.class;
		Field[] fields = shipmentWeekDto.getDeclaredFields();
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

	//get all rules
	@Transactional
	public List<ShipmentRuleDto> getShipmentRule() {
		List<ShipmentRuleDto> shipmentRuleDtoList = new ArrayList<>();
		List<MasterShipmentRule> shipmentRuleList = repository.getAllShipmentRule();
		if(!shipmentRuleList.isEmpty()){

			for(MasterShipmentRule shipmentRule:shipmentRuleList){
				ShipmentRuleDto shipmentRuleDto = new ShipmentRuleDto();
				shipmentRuleDto.setShipmentRuleId(shipmentRule.getPkShipmentRuleId());
				shipmentRuleDto.setShipmentRuleName(shipmentRule.getShipmentRuleName());
				shipmentRuleDto.setShipmentRuleCode(shipmentRule.getShipmentRuleCode());
				shipmentRuleDto.setShipmentRuleDesc(shipmentRule.getShipmentRuleDesc());
				shipmentRuleDto.setRangeType(shipmentRule.getRangeType());
				shipmentRuleDto.setStartRange(shipmentRule.getStartRange());
				shipmentRuleDto.setEndRange(shipmentRule.getEndRange());
				shipmentRuleDtoList.add(shipmentRuleDto);
			}			
			return shipmentRuleDtoList;
		}
		return shipmentRuleDtoList;
	}
}

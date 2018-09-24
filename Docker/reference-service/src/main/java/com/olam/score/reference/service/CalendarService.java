package com.olam.score.reference.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.olam.score.common.constants.Constants;
import com.olam.score.common.dto.ResponseData;
import com.olam.score.common.util.ScoreBaseException;
import com.olam.score.common.util.WebServiceCallUtil;
import com.olam.score.reference.domain.MasterCalendar;
import com.olam.score.reference.domain.MasterCalendarTypeRef;
import com.olam.score.reference.dto.CalendarDto;
import com.olam.score.reference.repository.CalendarRepository;

@Service
public class CalendarService {

	@Autowired
	CalendarRepository calendarRepository;

	@Autowired
	private WebServiceCallUtil webServiceCall;

	// @Value("${product.name}")
	// public String productName;

	@Value("${reference.name}")
	public String calndrType;

	@Value("${party.name}")
	public String partyName;

	// @Value("${party.name}")
	// public String partyName;

	@Transactional
	// @SuppressWarnings({ "unchecked", "rawtypes" })
	public List<CalendarDto> getAllCalendar() {

		List<CalendarDto> calendarDtoList = new ArrayList<>();
		List<MasterCalendar> masterCalendarList = new ArrayList<>();

		masterCalendarList = calendarRepository.findAll();

		for (MasterCalendar calendar : masterCalendarList) {
			CalendarDto calendarDto = new CalendarDto();
			//int calendarTypeID = calendar.getCalendarTypeId();
		//	String calendarName = getCalendarName(calendarTypeID);
			
			calendarDto = generateCalendarDto(calendar);
		//	calendarDto.setCalendarTypeName(calendarName);
			calendarDtoList.add(calendarDto);
		}

		return calendarDtoList;
	}

	public CalendarDto getCalendarById(Integer calendarSetupId) {

		CalendarDto calendarDto;
		if (calendarSetupId == null)
			throw new ScoreBaseException(0, "calendarSetupId : " + calendarSetupId, HttpStatus.NOT_FOUND);

		MasterCalendar masterCalendar = calendarRepository.findOne(calendarSetupId);

		if (masterCalendar == null)
			throw new ScoreBaseException(0, "calendarSetupId : " + calendarSetupId + " not found in database",
					HttpStatus.CONFLICT);
		int calendarTypeID = masterCalendar.getCalendarTypeId();
		String calendarName = getCalendarName(calendarTypeID);
		masterCalendar.setCalendarName(calendarName);

		// setting party Name
		//Integer exchangeId = masterCalendar.getExchangeId();

		// String partyName=getPartyName(exchangeId);

		// System.out.println("partyName"+partyName);

		// setting product Name

		calendarDto = generateCalendarDto(masterCalendar);
		// calendarDto.setExchangeName(partyName);

		return calendarDto;

	}

	private String getCalendarName(int calendarType) {
		String calendarName = "";
		Map<Integer, String> statusMap = webServiceCall.getAllStatus();
		ResponseEntity<ResponseData> responseEntity = webServiceCall.internalServiceCall(calndrType,
				"/reference/calendartyperef/");
		List<Map<Object, Object>> calendarTypelist = (List<Map<Object, Object>>) responseEntity.getBody().getBody();
		for (int i = 0; i < calendarTypelist.size(); i++) {
			Integer id = (Integer) calendarTypelist.get(i).get("calendarTypeRefId");
			if (id.equals(calendarType)) {
				calendarName = (String) calendarTypelist.get(i).get("calendarTypeName");
			}
		}
		return calendarName;
	}

	private String getPartyName(int partyId) {
		String partyName = "";
		Map<Integer, String> statusMap = webServiceCall.getAllStatus();
		ResponseEntity<ResponseData> responseEntity = webServiceCall.internalServiceCall(partyName,
				"/party/partyservice/party");
		List<Map<Object, Object>> partyNamelist = (List<Map<Object, Object>>) responseEntity.getBody().getBody();
		for (int i = 0; i < partyNamelist.size(); i++) {
			Integer id = (Integer) partyNamelist.get(i).get("partyId");
			if (id.equals(partyId)) {
				partyName = (String) partyNamelist.get(i).get("partyName");
			}
		}
		return partyName;
	}

	private CalendarDto generateCalendarDto(MasterCalendar masterCalendar) {
		CalendarDto calendarDto = new CalendarDto();
		calendarDto.setCalendarCode(masterCalendar.getCalendarCode());
		calendarDto.setCalendarName(masterCalendar.getCalendarName());
		calendarDto.setCalendarDesc(masterCalendar.getCalendarDesc());
		calendarDto.setCalendarSetupId(masterCalendar.getCalendarSetupId());
		calendarDto.setCalendarTypeId(masterCalendar.getCalendarTypeId());
		// calendarDto.setCalendarTypeName(calendarTypeName);
		calendarDto.setDlvryMonth(masterCalendar.getDlvryMonth());
		calendarDto.setDurationType(masterCalendar.getDurationType());
		calendarDto.setExchangeId(masterCalendar.getExchangeId());
		// calendarDto.setExchangeName(exchangeName);
		calendarDto.setFinalStlmntDate(masterCalendar.getFinalStlmntDate());
		calendarDto.setFirstDlvryDate(masterCalendar.getFirstDlvryDate());
		calendarDto.setFirstNoticeDate(masterCalendar.getFirstNoticeDate());
		calendarDto.setFirstTradeDate(masterCalendar.getFirstTradeDate());
		calendarDto.setFutursCalendarId(masterCalendar.getFutursCalendarId());
		// calendarDto.setFutursCalendarName(futursCalendarName);
		calendarDto.setLastDlvryDate(masterCalendar.getLastDlvryDate());
		calendarDto.setLastNoticeDate(masterCalendar.getLastNoticeDate());
		calendarDto.setLastTradeDate(masterCalendar.getLastTradeDate());
		calendarDto.setPhyDlvryEndDate(masterCalendar.getPhyDlvryEndDate());
		calendarDto.setPhyDlvryStrtDate(masterCalendar.getPhyDlvryStrtDate());
		calendarDto.setProductId(masterCalendar.getProductId());
		// calendarDto.setProductName(productName);
		calendarDto.setSpan(masterCalendar.getSpan());
		calendarDto.setSpreadMonth1(masterCalendar.getSpreadMonth1());
		calendarDto.setSpreadMonth2(masterCalendar.getSpreadMonth2());
		calendarDto.setStatus(masterCalendar.getStatus());
		/*if (1==masterCalendar.getStatus()) {
			
			calendarDto.setStatus(Constants.ACTIVE);
		} else if (2==masterCalendar.getStatus()) {

			calendarDto.setStatus(Constants.DRAFT);
		} else if (3==masterCalendar.getStatus() ) {

			calendarDto.setStatus(Constants.INACTIVE);
		}*/
		
		calendarDto.setTenorId(masterCalendar.getTenorId());
		// calendarDto.setTenorName(tenorName);
		calendarDto.setTrickerCode(masterCalendar.getTrickerCode());

		return calendarDto;

	}

	private MasterCalendar generateMasterCalendar(CalendarDto calendarDto) {
		MasterCalendar masterCalendar = new MasterCalendar();
		masterCalendar.setCalendarSetupId(calendarDto.getCalendarSetupId());
		masterCalendar.setCalendarCode(calendarDto.getCalendarCode());
		masterCalendar.setCalendarName(calendarDto.getCalendarName());
		masterCalendar.setCalendarDesc(calendarDto.getCalendarDesc());
		masterCalendar.setCalendarTypeId(calendarDto.getCalendarTypeId());
		masterCalendar.setExchangeId(calendarDto.getExchangeId());
		masterCalendar.setProductId(calendarDto.getProductId());

		masterCalendar.setStatus(calendarDto.getStatus());
		/*if (Constants.ACTIVE.equalsIgnoreCase(calendarDto.getStatus().trim())) {

			masterCalendar.setStatus(1);
		} else if (Constants.DRAFT.equalsIgnoreCase(calendarDto.getStatus().trim())) {
			masterCalendar.setStatus(2);
		} else if (Constants.INACTIVE.equalsIgnoreCase(calendarDto.getStatus().trim())) {
			masterCalendar.setStatus(3);
		}
	*/
		masterCalendar.setTrickerCode(calendarDto.getTrickerCode());
		masterCalendar.setPhyDlvryStrtDate(calendarDto.getPhyDlvryStrtDate());
		masterCalendar.setPhyDlvryEndDate(calendarDto.getPhyDlvryEndDate());
		masterCalendar.setFutursCalendarId(calendarDto.getFutursCalendarId());
		masterCalendar.setDlvryMonth(calendarDto.getDlvryMonth());
		masterCalendar.setFirstTradeDate(calendarDto.getFirstTradeDate());
		masterCalendar.setLastTradeDate(calendarDto.getLastTradeDate());
		masterCalendar.setFirstNoticeDate(calendarDto.getFirstNoticeDate());
		masterCalendar.setLastNoticeDate(calendarDto.getLastNoticeDate());
		masterCalendar.setFirstDlvryDate(calendarDto.getFirstDlvryDate());
		masterCalendar.setLastDlvryDate(calendarDto.getLastDlvryDate());
		masterCalendar.setFinalStlmntDate(calendarDto.getFinalStlmntDate());
		masterCalendar.setSpreadMonth1(calendarDto.getSpreadMonth1());
		masterCalendar.setSpreadMonth2(calendarDto.getSpreadMonth2());
		masterCalendar.setTenorId(calendarDto.getTenorId());
		masterCalendar.setSpan(calendarDto.getSpan());
		masterCalendar.setDurationType(calendarDto.getDurationType());
		masterCalendar.setCreatedBy("Test");
		masterCalendar.setCreatedDate(new Date());
		masterCalendar.setModifiedBy("Test");
		masterCalendar.setModifiedDate(new Date());

		return masterCalendar;

	}

	public CalendarDto addNewCalendar(CalendarDto calendarDto) {
		// validation in dto
		MasterCalendar masterCalendar = new MasterCalendar();
		masterCalendar = generateMasterCalendar(calendarDto);
		// validating unique taxCode and taxName
		validationForAddingNewCalendar(masterCalendar);
		// saving mastterCalendar
		calendarRepository.saveAndFlush(masterCalendar);
		// convert domain object again into dto object and return it back
		CalendarDto dto = generateCalendarDto(masterCalendar);
		return dto;
	}

	public String updateCalendar(CalendarDto calendarDto) {
		String addStatus = "DATA Succcessfully Updated";
		// validation in dto
		MasterCalendar masterCalendar = new MasterCalendar();
		masterCalendar = generateMasterCalendar(calendarDto);
		// validating unique calendarCode and calendar name
		validationForUpdatingCalendar(masterCalendar);
		// saving mastterCalendar
		calendarRepository.saveAndFlush(masterCalendar);

		return addStatus;
	}

	public String deleteCalendarById(Integer calendarSetupId) {

		if (calendarSetupId <= 0)
			return Constants.CALENDAR_DELETE_FAILURE;
		else {
			int statusId = 0;
			MasterCalendar masterCalendar = calendarRepository.findOne(calendarSetupId);
			statusId = masterCalendar.getStatus();
			// for draft:-> hard delete
			if (Constants.DRAFT_STATUS_ID == statusId) {

				calendarRepository.delete(calendarSetupId);
				String response = Constants.CALENDAR_DELETE_SUCCESS;
				response = response.replace(Constants.CALENDAR_NAME_MESSAGE, masterCalendar.getCalendarName());
				return response;
			}
			// for Active: -> Soft delete - Change Status : DEACTIVATING
			else if (Constants.ACTIVE_STATUS_ID == statusId) {
				// changing status to DEACTIVATE }
				int status = Constants.INACTIVE_STATUS_ID;

				calendarRepository.deactivateOrReactivateCalendar(status, calendarSetupId);
				String response = Constants.CALENDAR_DEACTIVATE_SUCCESS;
				response = response.replace(Constants.CALENDAR_NAME_MESSAGE, masterCalendar.getCalendarName());
				return response;
				// for Deactivating: -> Soft delete - Change Status : Activating
			} else if (Constants.INACTIVE_STATUS_ID == statusId) {

				int status = Constants.ACTIVE_STATUS_ID;
				calendarRepository.deactivateOrReactivateCalendar(status, calendarSetupId);
				String response = Constants.CALENDAR_REACTIVATE_SUCCESS;
				response = response.replace(Constants.CALENDAR_NAME_MESSAGE, masterCalendar.getCalendarName());

				return response;
			} else {
				String response = Constants.CALENDAR_BLANK_STATUS_ID;

				return response;
			}
		}
	}

	public String updateStatus(Integer calendarSetupId) {

		if (calendarSetupId <= 0)
			return Constants.CALENDAR_DEACTIVATE_FAILURE;
		else {
			int statusId = 0;
			int masterStatusId = 0;
			String response = "";
			MasterCalendar calendar = calendarRepository.findOne(calendarSetupId);
			masterStatusId = calendar.getStatus();

			if (Constants.ACTIVE_STATUS_ID == masterStatusId) {
				// changing status to DEACTIVATE }
				statusId = Constants.INACTIVE_STATUS_ID;
				response = Constants.CALENDAR_DEACTIVATE_SUCCESS;
			} else if (Constants.INACTIVE_STATUS_ID == masterStatusId) {
				// changing status to DEACTIVATE }
				statusId = Constants.ACTIVE_STATUS_ID;
				response = Constants.CALENDAR_REACTIVATE_SUCCESS;
			}
			calendarRepository.deactivateOrReactivateCalendar(statusId, calendarSetupId);
			// taxRateRepository.deactivateOrReactivateTaxRate(statusId, calendarSetupId);
			return response;

		}
	}

	public List<CalendarDto> getFutureCalendar(Integer clndrTypeId) {

		List<CalendarDto> calendarDtoList = new ArrayList<>();
		;
		if (clndrTypeId == null)
			throw new ScoreBaseException(0, "clndrTypeId : " + clndrTypeId, HttpStatus.NOT_FOUND);

		List<MasterCalendar> masterCalendar = calendarRepository.futureCalendar(clndrTypeId);

		if (masterCalendar == null)
			throw new ScoreBaseException(0, "calendarSetupId : " + clndrTypeId + " not found in database",
					HttpStatus.CONFLICT);

		for (MasterCalendar calendar : masterCalendar) {
			CalendarDto calendarDto = new CalendarDto();

			calendarDto = generateCalendarDto(calendar);
			calendarDtoList.add(calendarDto);
		}

		return calendarDtoList;

		// List<CalendarDto> calendarDtoList = new ArrayList<>();
		// List<MasterCalendar> masterCalendarList = new ArrayList<>();
		//
		// masterCalendarList = calendarRepository.futureCalendar();
		//
		// for(MasterCalendar calendar:masterCalendarList) {
		// CalendarDto calendarDto = new CalendarDto();
		//
		// calendarDto = generateCalendarDto(calendar);
		// calendarDtoList.add(calendarDto);
		// }
		//
		// return calendarDtoList;
		//

	}

	// public List<Map<Object, Object>> getProductName() {
	// List<Map<Object, Object>> listPrdct = null;
	//	
	// ResponseEntity<ResponseData>
	// responseEntityLocatn=webServiceCall.internalServiceCall(productName,
	// Constants.PRODUCT_URL);
	// ResponseData<Map<Object, Object>> bodyLctn = responseEntityLocatn.getBody();
	// listPrdct = (List<Map<Object, Object>>) bodyLctn.getBody();
	//
	// return listPrdct;
	// }
	private void validationForAddingNewCalendar(MasterCalendar masterCalendar) {
		int chkValue = 0;

		if (calendarRepository.uniqueCalendarCode(masterCalendar.getCalendarCode()) > chkValue) {
			throw new ScoreBaseException(masterCalendar.getCalendarSetupId(), "Calendar Code already exists",
					HttpStatus.CONFLICT);
		}

		if (calendarRepository.uniqueCalendarName(masterCalendar.getCalendarName()) > chkValue) {
			throw new ScoreBaseException(masterCalendar.getCalendarSetupId(), "Calender Name already exists",
					HttpStatus.PRECONDITION_FAILED);
		}

	}

	private void validationForUpdatingCalendar(MasterCalendar masterCalendar) {
		int chkValue = 0;
		if (calendarRepository.uniqueCalendarCodeForUpdate(masterCalendar.getCalendarCode(),
				masterCalendar.getCalendarSetupId()) > chkValue) {
			throw new ScoreBaseException(0, "Calendar Code already exists", HttpStatus.CONFLICT);
		}

		if (calendarRepository.uniqueCalendarNameForUpdate(masterCalendar.getCalendarName(),
				masterCalendar.getCalendarSetupId()) > chkValue) {
			throw new ScoreBaseException(1, "Calendar Name already exists", HttpStatus.PRECONDITION_FAILED);
		}

	}

}

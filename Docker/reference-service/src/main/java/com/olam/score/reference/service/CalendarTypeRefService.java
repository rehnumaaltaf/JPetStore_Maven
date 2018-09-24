package com.olam.score.reference.service;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.olam.score.reference.repository.CalaendarTypeRefRepository;
import com.olam.score.common.constants.Constants;
import com.olam.score.common.util.ScoreBaseException;
import com.olam.score.reference.domain.MasterCalendarTypeRef;
import com.olam.score.reference.dto.CalendarTypeRefDto;

@Service
public class CalendarTypeRefService {
	@Autowired
	CalaendarTypeRefRepository refRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Transactional
	public List<CalendarTypeRefDto> getAllRefCalendar() {
		List<MasterCalendarTypeRef> masterCalendarTypeRef = refRepository.findAll();
		List<CalendarTypeRefDto> calTypeRefDto = new ArrayList<CalendarTypeRefDto>();

		for (MasterCalendarTypeRef calendarTypeRef : masterCalendarTypeRef) {
			CalendarTypeRefDto refDto = new CalendarTypeRefDto();
			refDto = modelMapper.map(calendarTypeRef, CalendarTypeRefDto.class);
			calTypeRefDto.add(refDto);
		}
		return calTypeRefDto;
	}

	@Transactional
	public CalendarTypeRefDto readCalendarRefId(Integer calendarTypeRefId) {
		CalendarTypeRefDto refDto;

		if (calendarTypeRefId == null)
			throw new ScoreBaseException(0, "CalenderTypeRef : " + calendarTypeRefId, HttpStatus.NOT_FOUND);
		MasterCalendarTypeRef calendarTypeRef = refRepository.findOne(calendarTypeRefId);

		if (calendarTypeRef == null)
			throw new ScoreBaseException(0, "taxCodeUID : " + calendarTypeRefId + " not found in database",
					HttpStatus.CONFLICT);

		refDto = modelMapper.map(calendarTypeRef, CalendarTypeRefDto.class);
		return refDto;

	}

	public CalendarTypeRefDto addNewCalendarTypeRef(CalendarTypeRefDto calendarTypeRefDto) {
		MasterCalendarTypeRef calendarTypeRef = modelMapper.map(calendarTypeRefDto, MasterCalendarTypeRef.class);
		refRepository.save(calendarTypeRef);
		CalendarTypeRefDto calendarRefDto = modelMapper.map(calendarTypeRef, CalendarTypeRefDto.class);
		return calendarRefDto;
	}

	public String deleteDeactivateOrReactivateTaxRareCalendarTypeRef(Integer calendarTypeRefId) {

		if (calendarTypeRefId <= 0)
			//need to make changes
			
			return Constants.ACTIVATED;
		else {
			int statusId = 0;
			MasterCalendarTypeRef calendarTypeRef = refRepository.findOne(calendarTypeRefId);
			statusId = calendarTypeRef.getFkStatusId();
			// for draft:-> hard delete
			if (Constants.DRAFT_STATUS_ID == statusId) {
				refRepository.delete(calendarTypeRefId);
				String response = Constants.DELETE_SUCCESS;
				return response;
			}
			// for Active: -> Soft delete - Change Status : DEACTIVATING
			else if (Constants.ACTIVE_STATUS_ID == statusId) {
				// changing status to DEACTIVATE }
				int status = Constants.INACTIVE_STATUS_ID;
				refRepository.deactivateOrReactivateCalendarTypeRef(status, calendarTypeRefId);
				String response = Constants.UPDATE_SUCCESS;
				return response;
				// for Deactivating: -> Soft delete - Change Status : Activating
			} else if (Constants.INACTIVE_STATUS_ID == statusId) {

				int status = Constants.ACTIVE_STATUS_ID;
				refRepository.deactivateOrReactivateCalendarTypeRef(status, calendarTypeRefId);
				String response = Constants.UPDATE_SUCCESS;
				return response;
			} else {
				String response = Constants.NODATA_AVILABLE;

				return response;
			}
		}

	}
}

package com.olam.score.reference.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.olam.score.common.dto.ResponseData;
import com.olam.score.reference.dto.CalendarTypeRefDto;
import com.olam.score.reference.service.CalendarTypeRefService;

@CrossOrigin
@RestController
@RequestMapping("/reference/calendartyperef")
public class CalenderTypeRefController {

	@Autowired
	CalendarTypeRefService service;

	@RequestMapping(value = "", method = RequestMethod.GET, produces = { "application/json" })
	public ResponseEntity<ResponseData<List<CalendarTypeRefDto>>> getAllCalendarRefCalendar() {
		ResponseData<List<CalendarTypeRefDto>> response = new ResponseData<>();

		response.setBody(service.getAllRefCalendar());
		response.setLinks(null);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/{calendarTypeRefId}", method = RequestMethod.GET, produces = { "application/json" })
	public ResponseEntity<ResponseData<CalendarTypeRefDto>> geCalendarRefById(
			@PathVariable("calendarTypeRefId") Integer calendarTypeRefId) {
		ResponseData<CalendarTypeRefDto> response = new ResponseData<>();
		response.setBody(service.readCalendarRefId(calendarTypeRefId));
		response.setLinks(null);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	
	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = { "application/json" }, produces = {
			"application/json" })
	public ResponseEntity<ResponseData<CalendarTypeRefDto>> addCalendarTypeRef(
			@Valid @RequestBody CalendarTypeRefDto CalendarTypeRefDto) {
		ResponseData<CalendarTypeRefDto> response = new ResponseData<>();
		response.setBody(service.addNewCalendarTypeRef(CalendarTypeRefDto));
		response.setLinks(null);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{calendarTypeRefId}", method = RequestMethod.DELETE)
	public ResponseEntity<ResponseData<String>> deleteTaxRate(
			@PathVariable("calendarTypeRefId") Integer calendarTypeRefId) {

		ResponseData<String> response = new ResponseData<>();
		response.setBody(service.deleteDeactivateOrReactivateTaxRareCalendarTypeRef(calendarTypeRefId));
		response.setLinks(null);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}

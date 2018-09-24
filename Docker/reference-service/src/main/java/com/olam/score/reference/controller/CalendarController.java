package com.olam.score.reference.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.olam.score.common.dto.ResponseData;

import com.olam.score.reference.dto.CalendarDto;
import com.olam.score.reference.dto.UomDto;
import com.olam.score.reference.service.CalendarService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin
@RestController
@RequestMapping("/reference/calendarservice")
@Api(value = "CALENDAR", description = "Operations pertaining to Calendar Setup in OLAM CTRM")
public class CalendarController {

	@Autowired
	private CalendarService calendarService;
	private final Logger log = LoggerFactory.getLogger(getClass());

	// Adding new calendar
	@ApiOperation(value = "Create new calendar setup", response = CalendarDto.class)
	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = { "application/json" }, produces = {
			"application/json" })
	public ResponseEntity<ResponseData<CalendarDto>> addNewCalendar(@RequestBody CalendarDto calendarDto) {
		ResponseData<CalendarDto> response = new ResponseData<>();

		response.setBody(calendarService.addNewCalendar(calendarDto));
		response.setLinks(null);// links
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// updating calendar
	@RequestMapping(value = "/", method = RequestMethod.PUT, consumes = { "application/json" }, produces = {
			"application/json" })
	public ResponseEntity<ResponseData<String>> updateCalendar(@RequestBody CalendarDto calendarDto) {
		ResponseData<String> response = new ResponseData<>();

		response.setBody(calendarService.updateCalendar(calendarDto));
		response.setLinks(null);// links
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	// get all calendars

	@RequestMapping(value = "", method = RequestMethod.GET, produces = { "application/json" })
	public ResponseEntity<ResponseData<List<CalendarDto>>> getAllTaxRate() {
		ResponseData<List<CalendarDto>> response = new ResponseData<>();
		// TODO: User Authorization needs to happen
		response.setBody(calendarService.getAllCalendar());
		response.setLinks(null);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// change Status -ACTIVE<-> INACTIVE
	@RequestMapping(value = "/{calendarSetupId}", method = RequestMethod.PUT, produces = { "application/json" })
	public ResponseEntity<ResponseData<String>> changeStatus(@PathVariable("calendarSetupId") Integer calendarSetupId) {

		ResponseData<String> response = new ResponseData<>();
		// TODO: User Authorization needs to happen
		response.setBody(calendarService.updateStatus(calendarSetupId));
		response.setLinks(null);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping(value = "/{calendarSetupId}", produces = { "application/json" })
	public ResponseEntity<ResponseData<CalendarDto>> getCalendarById(
			@PathVariable("calendarSetupId") Integer calendarSetupId) {
		ResponseData<CalendarDto> response = new ResponseData<>();
		// TODO: User Authorization needs to happen
		response.setBody(calendarService.getCalendarById(calendarSetupId));
		response.setLinks(null);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@DeleteMapping(value = "/{calendarSetupId}", produces = { "application/json" })
	public ResponseEntity<ResponseData<String>> deleteById(@PathVariable("calendarSetupId") Integer calendarSetupId) {
		ResponseData<String> response = new ResponseData<>();
		// TODO: User Authorization needs to happen
		response.setBody(calendarService.deleteCalendarById(calendarSetupId));
		response.setLinks(null);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/futurCalendar", method = RequestMethod.GET, produces = { "application/json" })
	public ResponseEntity<ResponseData<List<CalendarDto>>> getFutureCalendar() {
		ResponseData<List<CalendarDto>> response = new ResponseData<>();
		// TODO: User Authorization needs to happen
		// response.setBody(calendarService.getFutureCalendar());
		response.setLinks(null);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}

package com.olam.score.reference.controller;

import java.util.List;
import java.util.Map;

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
import com.olam.score.common.dto.ViewDto;
import com.olam.score.common.util.Mapping;
import com.olam.score.reference.dto.HolidayCalendarDto;
import com.olam.score.reference.service.HolidayCalendarService;

@CrossOrigin // TODO: need to remove cross origin in future
@RestController
@Mapping(featureName = "HOLIDAY_CALENDER", featureId = "10")
@RequestMapping("/reference/holidaycalendarservice/holidaycalendar")
public class HolidayCalendarController {

	@Autowired
	HolidayCalendarService service;

	@RequestMapping(value = "", produces = { "application/json" }, consumes = {
	"application/json" }, method = RequestMethod.POST)
	public ResponseEntity<ResponseData<HolidayCalendarDto>> addHolidayCalendar(@Valid @RequestBody HolidayCalendarDto body) {
		ResponseData<HolidayCalendarDto> response = new ResponseData<>();
		response.setBody(service.create(body));
		response.setLinks(null);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/{holidayCalId}", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<ResponseData<HolidayCalendarDto>> getHolidayCalendarById(@PathVariable("holidayCalId") Integer holidayCalId) {

		ResponseData<HolidayCalendarDto> response = new ResponseData<>();
		//List<Link<?>> links = utilService.getFeatures(this.getClass());

		response.setBody(service.readById(holidayCalId));
		response.setLinks(null);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "", method = RequestMethod.DELETE)
	public ResponseEntity<ResponseData<HolidayCalendarDto>> deleteHolidayCalendar(@RequestBody List<Map<String, Integer>> body) {
		ResponseData<HolidayCalendarDto> response = new ResponseData<>();
		service.deleteHolidayCalendar(body);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}
	
	@RequestMapping(value = "", produces = { "application/json" }, consumes = {
	"application/json" }, method = RequestMethod.PUT)
	public ResponseEntity<ResponseData<List<HolidayCalendarDto>>> updateHolidayCalendar(@Valid @RequestBody List<HolidayCalendarDto> body) {
		ResponseData<List<HolidayCalendarDto>> response = new ResponseData<>();
		//List<Link<?>> links = utilService.getFeatures(this.getClass());
		response.setBody(service.update(body));
		response.setLinks(null);// TOOD: Need to get links for the entity
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	/*@RequestMapping(value = "", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<ResponseData<List<HolidayCalendarDto>>> getHolidayCalendar(){
		ResponseData<List<HolidayCalendarDto>> response = new ResponseData<>();
		response.setBody(service.readAll());
		response.setLinks(null);//TOOD: Need to get links for the entity
		return new ResponseEntity<>(response, HttpStatus.OK);
	}*/
	
	
	@RequestMapping(value = "", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<ResponseData<List<HolidayCalendarDto>>> getHolidayCalendar1() {
		ResponseData<List<HolidayCalendarDto>> response = new ResponseData<>();
		//List<Link<?>> links = utilService.getFeatures(this.getClass());
		ViewDto view = service.getViewDetails(this.getClass());
		response.setBody(service.readAll(view));
		response.setLinks(null);
		response.setView(view); 
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}

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
import com.olam.score.reference.dto.ShipmentRuleDto;
import com.olam.score.reference.dto.ShipmentWeekDto;
import com.olam.score.reference.service.ShipmentWeekService;

@CrossOrigin // TODO: need to remove cross origin in future
@RestController
@Mapping(featureName = "SHIPMENT_WEEK", featureId = "17")
@RequestMapping("/reference/shipmentweekservice/shipmentweek")
public class ShipmentWeekController {

	@Autowired
	ShipmentWeekService service;
	
	@RequestMapping(value = "", produces = { "application/json" }, consumes = {
	"application/json" }, method = RequestMethod.POST)
	public ResponseEntity<ResponseData<ShipmentWeekDto>> addShipmentWeek(@Valid @RequestBody ShipmentWeekDto body) {
		ResponseData<ShipmentWeekDto> response = new ResponseData<>();
		response.setBody(service.create(body));
		response.setLinks(null);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/{shipmentWeekId}", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<ResponseData<ShipmentWeekDto>> getShipmentWeekById(@PathVariable("shipmentWeekId") Integer shipmentWeekTimeframeId) {

		ResponseData<ShipmentWeekDto> response = new ResponseData<>();
		//List<Link<?>> links = utilService.getFeatures(this.getClass());

		response.setBody(service.readById(shipmentWeekTimeframeId));
		response.setLinks(null);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "", method = RequestMethod.DELETE)
	public ResponseEntity<ResponseData<ShipmentWeekDto>> deleteShipmentWeek(@RequestBody List<Map<String, Integer>> body) {
		ResponseData<ShipmentWeekDto> response = new ResponseData<>();
		service.deleteHolidayCalendar(body);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}
	
	@RequestMapping(value = "", produces = { "application/json" }, consumes = {
	"application/json" }, method = RequestMethod.PUT)
	public ResponseEntity<ResponseData<List<ShipmentWeekDto>>> updateShipmentWeek(@Valid @RequestBody List<ShipmentWeekDto> body) {
		ResponseData<List<ShipmentWeekDto>> response = new ResponseData<>();
		//List<Link<?>> links = utilService.getFeatures(this.getClass());
		response.setBody(service.update(body));
		response.setLinks(null);// TOOD: Need to get links for the entity
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<ResponseData<List<ShipmentWeekDto>>> getshipmentWeek(){
		ResponseData<List<ShipmentWeekDto>> response = new ResponseData<>();
		ViewDto view = service.getViewDetails(this.getClass());
		response.setBody(service.readAll(view));
		//response.setBody(service.readAll());
		response.setLinks(null);//TOOD: Need to get links for the entity
		response.setView(view); 
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/shipmentrule", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<ResponseData<List<ShipmentRuleDto>>> getShipmentRule()//@Valid @QueryParam("action") String action,  @Valid @RequestBody GLDto body)
	{
		ResponseData<List<ShipmentRuleDto>> response = new ResponseData<>();		
		response.setBody(service.getShipmentRule());
		response.setLinks(null);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	/*@RequestMapping(value = "", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<ResponseData<List<HolidayCalendarDto>>> getHolidayCalendar1() {
		ResponseData<List<HolidayCalendarDto>> response = new ResponseData<>();
		//List<Link<?>> links = utilService.getFeatures(this.getClass());
		ViewDto view = service.getViewDetails(this.getClass());
		response.setBody(service.readAll(view));
		response.setLinks(null);
		response.setView(view); 
		return new ResponseEntity<>(response, HttpStatus.OK);
	}*/
}

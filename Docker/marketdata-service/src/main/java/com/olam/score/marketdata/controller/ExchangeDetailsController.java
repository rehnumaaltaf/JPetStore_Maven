package com.olam.score.marketdata.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.olam.score.common.dto.ResponseData;
import com.olam.score.marketdata.dto.ExchangeDetailsDto;
import com.olam.score.marketdata.service.ExchangeDetailsService;

@CrossOrigin // TODO: need to remove cross origin in future
@RestController
@RequestMapping("/marketdata/partyexchangeservice/exchangeservice")
public class ExchangeDetailsController {
	
	@Autowired
	private ExchangeDetailsService service;
	
	@RequestMapping(value = "", produces = { "application/json" }, consumes = { "application/json" }, method = RequestMethod.POST)
    public ResponseEntity<ResponseData<ExchangeDetailsDto>> addPartyLimits(@Valid @RequestBody ExchangeDetailsDto body){
    	ResponseData<ExchangeDetailsDto> response = new ResponseData<>();
    	try {
    		response.setBody(service.create(body));
    		response.setLinks(null);
    	} catch (Exception e) {
    		return new ResponseEntity<>(response, HttpStatus.CONFLICT);
		}
    	return new ResponseEntity<>(response, HttpStatus.OK);
    } 
	
	@GetMapping(value = "/{counterPartyId}")
	public ResponseEntity<ResponseData<List<ExchangeDetailsDto>>> getPartyLimitsByPartyId
								(@PathVariable("counterPartyId") Integer counterPartyId){
		ResponseData<List<ExchangeDetailsDto>> response = new ResponseData<>();
		// TODO: User Authorization needs to happen
		response.setBody(service.readByPartyId(counterPartyId));
		response.setLinks(null);// TOOD: Need to get links for the entity
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}

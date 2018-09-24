package com.olam.score.limit.controller;

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
import com.olam.score.limit.dto.PartyLimitDto;
import com.olam.score.limit.service.PartyLimitService;

@CrossOrigin // TODO: need to remove cross origin in future
@RestController
@RequestMapping("/limit/counterpartylimitservice/counterpartylimit")
public class PartyLimitController {
	
	@Autowired
	private PartyLimitService service;
	
	@RequestMapping(value = "", produces = { "application/json" }, method = RequestMethod.POST)
    public ResponseEntity<ResponseData<PartyLimitDto>> addPartyLimits(@Valid @RequestBody PartyLimitDto body){
    	ResponseData<PartyLimitDto> response = new ResponseData<>();
    	try {
    		response.setBody(service.create(body));
    		response.setLinks(null);
    	} catch (Exception e) {
    		return new ResponseEntity<>(response, HttpStatus.CONFLICT);
		}
    	return new ResponseEntity<>(response, HttpStatus.OK);
    } 
	
	@GetMapping(value = "/{counterPartyId}")
	public ResponseEntity<ResponseData<List<PartyLimitDto>>> getPartyLimitsByPartyId
								(@PathVariable("counterPartyId") Integer counterPartyId){
		ResponseData<List<PartyLimitDto>> response = new ResponseData<>();
		// TODO: User Authorization needs to happen
		response.setBody(service.readByCounterPartyId(counterPartyId));
		response.setLinks(null);// TOOD: Need to get links for the entity
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}

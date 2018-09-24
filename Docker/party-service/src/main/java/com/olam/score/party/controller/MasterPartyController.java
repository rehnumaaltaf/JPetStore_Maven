package com.olam.score.party.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.olam.score.common.dto.ResponseData;
import com.olam.score.party.dto.PartyDto;
import com.olam.score.party.service.MasterPartyService;

@CrossOrigin
@RestController
@RequestMapping(value = "/party", produces = MediaType.APPLICATION_JSON_VALUE)
public class MasterPartyController {

	@Autowired
	MasterPartyService service;

	@GetMapping("/partyCode")
	public ResponseEntity<ResponseData<List<PartyDto>>> getPartyCode()
	{
		ResponseData<List<PartyDto>> responseData = new ResponseData<>();
		responseData.setBody(service.fetchPartyCode());		
		return new ResponseEntity<>(responseData, HttpStatus.OK);
	}

	@GetMapping("/partyType/{partyInternalFlag}")
	public ResponseEntity<ResponseData<List<PartyDto>>> fetchGroupCode(@PathVariable("partyInternalFlag") String partyInternalFlag){

		ResponseData<List<PartyDto>> response = new ResponseData<>();
		response.setBody(service.fetchGroupPartyName(partyInternalFlag));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	}

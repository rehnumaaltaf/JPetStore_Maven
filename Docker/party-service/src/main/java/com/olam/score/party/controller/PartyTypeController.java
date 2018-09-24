package com.olam.score.party.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.olam.score.common.dto.ResponseData;
import com.olam.score.party.dto.PartyTypeDTO;
import com.olam.score.party.service.PartyTypeService;

@CrossOrigin // TODO: need to remove cross origin in future
@RestController
@RequestMapping(value = "/party/partytype", produces = MediaType.APPLICATION_JSON_VALUE)
public class PartyTypeController {
	
	@Autowired
	PartyTypeService service;
	
	@GetMapping
	public @ResponseBody ResponseEntity<ResponseData<List<PartyTypeDTO>>> fetchPartyTypeRecords(){

		ResponseData<List<PartyTypeDTO>> response = new ResponseData<>();
		response.setBody(service.fetchPartyType());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}

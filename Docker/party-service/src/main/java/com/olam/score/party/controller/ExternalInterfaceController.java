package com.olam.score.party.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.olam.score.common.dto.ResponseData;
import com.olam.score.common.util.Mapping;
import com.olam.score.party.dto.ExternalInterfaceDto;
import com.olam.score.party.dto.ResponseDto;
import com.olam.score.party.service.ExternalInterfaceService;

@CrossOrigin // TODO: need to remove cross origin in future
@RestController
@Mapping(featureName = "PARTY", featureId = "19")
@RequestMapping(value = "/party/ctrmpartyinterface/sap",produces = { "application/json" })
public class ExternalInterfaceController {
	
	@Autowired
	private ExternalInterfaceService service;
	
	@RequestMapping(value = "", produces = { "application/json" }, consumes = { "application/json" }, method = RequestMethod.POST)
    public ResponseEntity<ResponseData<ResponseDto>> addParty(@Valid @RequestBody ExternalInterfaceDto body){
    	ResponseData<ResponseDto> response = new ResponseData<>();
    	try {
    		response.setBody(service.create(body));
    		response.setLinks(null);
    	} catch (Exception e) {
    		return new ResponseEntity<>(response, HttpStatus.CONFLICT);
		}
    	return new ResponseEntity<>(response, HttpStatus.OK);
    } 

}

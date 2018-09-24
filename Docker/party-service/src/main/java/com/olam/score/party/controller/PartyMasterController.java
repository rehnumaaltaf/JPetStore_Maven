package com.olam.score.party.controller;

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
import com.olam.score.common.util.Mapping;
import com.olam.score.party.dto.PartyMasterDto;
import com.olam.score.party.service.PartyMasterEditService;
import com.olam.score.party.service.PartyMasterService;

@CrossOrigin // TODO: need to remove cross origin in future
@RestController
@Mapping(featureName = "PARTY", featureId = "19")
@RequestMapping(value = "/party/partymasterservice/party",produces = { "application/json" })
public class PartyMasterController {
	
	@Autowired
	private PartyMasterService service;
	
	@Autowired
	private PartyMasterEditService editService;

	@GetMapping
	public ResponseEntity<ResponseData<List<PartyMasterDto>>> getParty(){
		ResponseData<List<PartyMasterDto>> response = new ResponseData<>();
		// TODO: User Authorization needs to happen
		response.setBody(service.readAll());
		response.setLinks(null);// TOOD: Need to get links for the entity
		response.setView(service.getViewDetails(this.getClass()));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{partyId}")
	public ResponseEntity<ResponseData<PartyMasterDto>> getProducts(@PathVariable("partyId") Integer partyId) {

		ResponseData<PartyMasterDto> response = new ResponseData<>();
		// TODO: User Authorization needs to happen
		response.setBody(service.readById(partyId));
		response.setLinks(null);// TOOD: Need to get links for the entity
		return new ResponseEntity<>(response, HttpStatus.OK);

	}
	
	@RequestMapping(value = "", produces = { "application/json" }, consumes = { "application/json" }, method = RequestMethod.POST)
    public ResponseEntity<ResponseData<PartyMasterDto>> addPartyMaster(@Valid @RequestBody PartyMasterDto body){
    	ResponseData<PartyMasterDto> response = new ResponseData<>();
    	try {
    		response.setBody(service.create(body));
    		response.setLinks(null);
    	} catch (Exception e) {
    		return new ResponseEntity<>(response, HttpStatus.CONFLICT);
		}
    	return new ResponseEntity<>(response, HttpStatus.OK);
    } 
	
	@RequestMapping(value = "", produces = { "application/json" }, consumes = { "application/json" }, method = RequestMethod.PUT)
    public ResponseEntity<ResponseData<PartyMasterDto>> editPartyMaster(@Valid @RequestBody PartyMasterDto body){
    	ResponseData<PartyMasterDto> response = new ResponseData<>();
    	try {
    		response.setBody(editService.editPartyMaster(body));
    		response.setLinks(null);
    	} catch (Exception e) {
    		return new ResponseEntity<>(response, HttpStatus.CONFLICT);
		}
    	return new ResponseEntity<>(response, HttpStatus.OK);
    } 
	
	

}

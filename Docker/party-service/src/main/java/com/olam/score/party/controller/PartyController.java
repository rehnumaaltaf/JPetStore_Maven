package com.olam.score.party.controller;

import java.util.List;

import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;

import com.olam.score.common.dto.ResponseData;
import com.olam.score.party.dto.PartyDto;
import com.olam.score.party.service.PartyService;

@CrossOrigin //TODO: need to remove cross origin in future
@RestController
@RequestMapping("/party/partyservice/party")
//@Mapping(featureName="PartyFeature") //TODO : feature name in database should be same

public class PartyController {

	@Autowired
	PartyService service;

	@RequestMapping(value = "", method = RequestMethod.GET, produces = { "application/json" })
	public ResponseEntity<ResponseData<List<PartyDto>>> getParty(@QueryParam("partyTypeName") String partyTypeName, @QueryParam("partyForLimitByClass") String partyForLimitByClass, @QueryParam("party") String party){
		ResponseData<List<PartyDto>> response = new ResponseData<>();
		//TODO: User Authorization needs to happen
				if (null != partyTypeName) {
					response.setBody(service.readAllByPartyTypeName(partyTypeName.toUpperCase()));
				}else if (null != partyForLimitByClass && null!=party) {
						response.setBody(service.readAllPartyForLimitByClass(partyForLimitByClass.toUpperCase(), party));
					
				} else {
					response.setBody(service.readAll());
				}
					response.setLinks(null);//TOOD: Need to get links for the entity
				return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@RequestMapping(value= "/{partyId}", method = RequestMethod.GET, produces = { "application/json" })
	public ResponseEntity<ResponseData<PartyDto>> getPartyById(@PathVariable("partyId") Integer partyId) {

		ResponseData<PartyDto> response = new ResponseData<>();
		//TODO: User Authorization needs to happen
		try {
			response.setBody(service.readById(partyId));
			response.setLinks(null);
		} catch (HttpStatusCodeException e) {
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
}
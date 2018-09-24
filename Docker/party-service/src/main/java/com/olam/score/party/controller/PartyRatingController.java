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
import com.olam.score.party.dto.PartyRatingDTO;
import com.olam.score.party.service.PartyRatingService;

@CrossOrigin
@RestController
@RequestMapping(value = "/party/rating", produces = MediaType.APPLICATION_JSON_VALUE)
public class PartyRatingController {
	
	@Autowired
	private PartyRatingService service;
	
	
	@GetMapping
	public @ResponseBody ResponseEntity<ResponseData<List<PartyRatingDTO>>> fetchPartyRating(){

		ResponseData<List<PartyRatingDTO>> response = new ResponseData<>();
		// TODO: User Authorization needs to happen
		response.setBody(service.fetchPartyRating());
		response.setLinks(null);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	
}

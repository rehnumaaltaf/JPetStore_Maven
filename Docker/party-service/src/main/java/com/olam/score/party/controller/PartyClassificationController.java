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
import com.olam.score.party.dto.PartyClassificationDTO;
import com.olam.score.party.service.PartyClassificationService;

@CrossOrigin
@RestController
@RequestMapping( value = "/party/classification", produces = MediaType.APPLICATION_JSON_VALUE)
public class PartyClassificationController {
	
	@Autowired
	private PartyClassificationService service;
	
	@GetMapping
	public @ResponseBody ResponseEntity<ResponseData<List<PartyClassificationDTO>>> fetchRecords(){

		ResponseData<List<PartyClassificationDTO>> response = new ResponseData<>();
		response.setBody(service.fetchClassificationRecords());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	

}

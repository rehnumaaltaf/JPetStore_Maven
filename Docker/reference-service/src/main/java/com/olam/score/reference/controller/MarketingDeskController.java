package com.olam.score.reference.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.olam.score.common.dto.ResponseData;
import com.olam.score.reference.dto.MarketingDeskDTO;
import com.olam.score.reference.service.MarketingDeskService;

@CrossOrigin
@RestController
@RequestMapping(value = "/reference/marketDesk", produces = MediaType.APPLICATION_JSON_VALUE)
public class MarketingDeskController {
	
	@Autowired
	private MarketingDeskService service;
	
	@GetMapping
	public @ResponseBody ResponseEntity<ResponseData<List<MarketingDeskDTO>>> fetchMarketingDeskRecords(){
		ResponseData<List<MarketingDeskDTO>> response = new ResponseData<>();
		response.setBody(service.readAll());
		return new ResponseEntity<>(response, HttpStatus.OK);	
	}
	
	@GetMapping(value = "/{deskId}")
	public @ResponseBody ResponseEntity<ResponseData<MarketingDeskDTO>> readByDeskId(
			@PathVariable("deskId") Integer deskId)
	{
		ResponseData<MarketingDeskDTO> response = new ResponseData<>();
		response.setBody(service.readById(deskId));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}

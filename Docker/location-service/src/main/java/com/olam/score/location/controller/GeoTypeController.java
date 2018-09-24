package com.olam.score.location.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.olam.score.common.dto.ResponseData;
import com.olam.score.location.dto.GeoTypeDto;
import com.olam.score.location.service.GeoTypeService;

@CrossOrigin // TODO: need to remove cross origin in future
@RestController
@RequestMapping("/location/geotypeservice/geotype")

public class GeoTypeController {
	@Autowired
	GeoTypeService service;
	
	@GetMapping(produces = { "application/json" })
	public @ResponseBody ResponseEntity<ResponseData<List<GeoTypeDto>>> fetchMarketingDeskRecords(){
		ResponseData<List<GeoTypeDto>> response = new ResponseData<>();
		response.setBody(service.readAll());
		return new ResponseEntity<>(response, HttpStatus.OK);	
	}
}

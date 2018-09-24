package com.olam.score.location.controller;

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
import com.olam.score.location.dto.MasterLocationDTO;
import com.olam.score.location.service.WarehouseLocationService;

@CrossOrigin
@RestController
@RequestMapping(value = "/location/warehouseLocation", produces = MediaType.APPLICATION_JSON_VALUE)
public class WareHouseLocationController {

	@Autowired
	private WarehouseLocationService service;
	
	@GetMapping
	public @ResponseBody ResponseEntity<ResponseData<List<MasterLocationDTO>>> locationDetails(){
		
		ResponseData<List<MasterLocationDTO>> response = new ResponseData<>();
		response.setBody(service.fetchLocationDetails());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("/{pkLocId}")
	public @ResponseBody ResponseEntity<ResponseData<MasterLocationDTO>> readByLocationId(
			@PathVariable("pkLocId") Integer pkLocId){
		
		ResponseData<MasterLocationDTO> response = new ResponseData<>();
		response.setBody(service.readById(pkLocId));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
}

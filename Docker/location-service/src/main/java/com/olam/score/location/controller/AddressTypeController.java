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
import com.olam.score.location.dto.AddressTypeDTO;
import com.olam.score.location.service.AddressTypeService;

@CrossOrigin
@RestController
@RequestMapping(value = "/location/addressType", produces = MediaType.APPLICATION_JSON_VALUE)
public class AddressTypeController {
	
	@Autowired
	private AddressTypeService service;
	
	@GetMapping
	public @ResponseBody ResponseEntity<ResponseData<List<AddressTypeDTO>>> addressTypeRecords(){
		
		ResponseData<List<AddressTypeDTO>> response = new ResponseData<>();
		response.setBody(service.fetchAddressType());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping(value = "/{addressTypeId}")
	public @ResponseBody ResponseEntity<ResponseData<AddressTypeDTO>> readByAddressTypeId(
			@PathVariable("addressTypeId") Integer addressTypeId)
	{
		ResponseData<AddressTypeDTO> response = new ResponseData<>();
		response.setBody(service.readById(addressTypeId));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}

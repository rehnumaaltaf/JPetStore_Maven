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
import com.olam.score.party.dto.AddressDto;
import com.olam.score.party.service.PartyAddressService;

@CrossOrigin
@RestController
@RequestMapping(value = "/party/address", produces = MediaType.APPLICATION_JSON_VALUE)
public class PartyAddressController {

	@Autowired
	private PartyAddressService service;

	@GetMapping
	public @ResponseBody ResponseEntity<ResponseData<List<AddressDto>>> getAddressList(){

		ResponseData<List<AddressDto>> response = new ResponseData<>();
		response.setBody(service.fetchAddress());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
}

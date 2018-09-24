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
import com.olam.score.party.dto.AccountTypeDTO;
import com.olam.score.party.service.MasterAccountTypeService;

@CrossOrigin
@RestController
@RequestMapping(value = "/party/accountType", produces = MediaType.APPLICATION_JSON_VALUE)
public class PartyAccountTypeController {
	
	@Autowired
	private MasterAccountTypeService service;
	
	@GetMapping
	public @ResponseBody ResponseEntity<ResponseData<List<AccountTypeDTO>>> getAccountType(){
		
		ResponseData<List<AccountTypeDTO>> response = new ResponseData<>();
		// TODO: User Authorization needs to happen
		response.setBody(service.fetchAccountType());
		response.setLinks(null);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	

	
	
	
}


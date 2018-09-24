package com.olam.score.limit.controller;

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
import com.olam.score.limit.dto.MasterCounterPartyLimitTypeDTO;
import com.olam.score.limit.service.MasterCounterPartyLimitTypeService;

@CrossOrigin
@RestController
@RequestMapping(value = "limit/MasterCounterPartyLimitTypeService/counterPartyLimitType", produces = MediaType.APPLICATION_JSON_VALUE)
public class MasterCounterPartyLimitTypeController {


	@Autowired
	private MasterCounterPartyLimitTypeService service;


	@GetMapping
	public @ResponseBody ResponseEntity<ResponseData<List<MasterCounterPartyLimitTypeDTO>>> getCounterPartyLimitType() {
		ResponseData<List<MasterCounterPartyLimitTypeDTO>> response = new ResponseData<>();
		// TODO: User Authorization needs to happen
		response.setBody(service.readAll());
		response.setLinks(null);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping(value = "/{counterPartyLimitId}")
	public @ResponseBody ResponseEntity<ResponseData<MasterCounterPartyLimitTypeDTO>> readByCounterPartyId(
			@PathVariable("counterPartyLimitId") Integer counterPartyLimitId)
	{
		ResponseData<MasterCounterPartyLimitTypeDTO> response = new ResponseData<>();
		response.setBody(service.readById(counterPartyLimitId));
		return new ResponseEntity<>(response, HttpStatus.OK);

	}


}


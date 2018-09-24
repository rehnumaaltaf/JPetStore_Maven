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
import com.olam.score.limit.dto.MasterLimitAlertLevelDTO;
import com.olam.score.limit.service.MasterLimitAlertLevelService;


@CrossOrigin
@RestController
@RequestMapping(value = "limit/MasterLimitAlertLevelService/masterLimitAlertLevel", produces = MediaType.APPLICATION_JSON_VALUE)
public class MasterLimitAlertLevelController {

	@Autowired
	MasterLimitAlertLevelService service;
	
	@GetMapping
	public @ResponseBody ResponseEntity<ResponseData<List<MasterLimitAlertLevelDTO>>> getLimitAlertLevel() {
		ResponseData<List<MasterLimitAlertLevelDTO>> response = new ResponseData<>();
		// TODO: User Authorization needs to happen
		response.setBody(service.readAll());
		response.setLinks(null);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{limitAlertId}")
	public @ResponseBody ResponseEntity<ResponseData<MasterLimitAlertLevelDTO>> readByAlertId(
			@PathVariable("limitAlertId") Integer limitAlertId)
	{
		ResponseData<MasterLimitAlertLevelDTO> response = new ResponseData<>();
		response.setBody(service.readById(limitAlertId));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}

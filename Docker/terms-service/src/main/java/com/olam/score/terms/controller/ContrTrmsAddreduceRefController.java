package com.olam.score.terms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.olam.score.common.dto.ResponseData;
import com.olam.score.terms.dto.ContrTrmsAddreduceRefDto;
import com.olam.score.terms.service.ContrTrmsAddreduceRefService;

@CrossOrigin // TODO: need to remove cross origin in future
@RestController
@RequestMapping("/terms/contrtrmsaddreduceservice/contrtrmsaddreduce")
public class ContrTrmsAddreduceRefController {
	@Autowired
	ContrTrmsAddreduceRefService service;
	
	@RequestMapping(value = "", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<ResponseData<List<ContrTrmsAddreduceRefDto>>> getContrTrmsAddreduceRef() {
		ResponseData<List<ContrTrmsAddreduceRefDto>> response = new ResponseData<>();
		response.setBody(service.getContrTrmsAddreduceRef());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}

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
import com.olam.score.terms.dto.ContractTermsBaseRefDto;
import com.olam.score.terms.dto.ContractTermsBasisRefDto;
import com.olam.score.terms.service.ContractTermsBasisRefService;

@CrossOrigin // TODO: need to remove cross origin in future
@RestController
@RequestMapping("/terms/contracttermsbasisservice/contracttermsbasis")
public class ContractTermsBasisRefController {

	@Autowired
	ContractTermsBasisRefService service;
	
	@RequestMapping(value = "", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<ResponseData<List<ContractTermsBasisRefDto>>> getcontractTermBasis() {
		ResponseData<List<ContractTermsBasisRefDto>> response = new ResponseData<>();
		response.setBody(service.getContractTermsBasis());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}

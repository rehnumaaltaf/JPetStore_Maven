package com.olam.score.terms.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.olam.score.common.dto.ResponseData;
import com.olam.score.common.dto.ViewDto;
import com.olam.score.common.util.Mapping;
import com.olam.score.terms.dto.ContractTermsDto;
import com.olam.score.terms.service.ContractTermsService;

@CrossOrigin // TODO: need to remove cross origin in future
@RestController
@Mapping(featureName = "CONTRACT_TERMS", featureId = "18")
@RequestMapping("/terms/contracttermsservice/contractterms")
public class ContractTermsController {

	@Autowired
	ContractTermsService service;
	
	@RequestMapping(value = "", produces = { "application/json" }, consumes = {
	"application/json" }, method = RequestMethod.POST)
	public ResponseEntity<ResponseData<ContractTermsDto>> addContractTerms(@Valid @RequestBody ContractTermsDto body) {
		ResponseData<ContractTermsDto> response = new ResponseData<>();
		response.setBody(service.create(body));
		response.setLinks(null);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{contractTermsId}", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<ResponseData<ContractTermsDto>> getContractTermsById(@PathVariable("contractTermsId") Integer contractTermsId) {

		ResponseData<ContractTermsDto> response = new ResponseData<>();
		response.setBody(service.readById(contractTermsId));
		response.setLinks(null);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "", method = RequestMethod.DELETE)
	public ResponseEntity<ResponseData<ContractTermsDto>> deleteContractTerms(@RequestBody List<Map<String, Integer>> body) {
		ResponseData<ContractTermsDto> response = new ResponseData<>();
		service.deleteContractTerms(body);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}
	
	@RequestMapping(value = "", produces = { "application/json" }, consumes = {
	"application/json" }, method = RequestMethod.PUT)
	public ResponseEntity<ResponseData<List<ContractTermsDto>>> updateContractTerms(@Valid @RequestBody List<ContractTermsDto> body) {
		ResponseData<List<ContractTermsDto>> response = new ResponseData<>();
		response.setBody(service.update(body));
		response.setLinks(null);// TOOD: Need to get links for the entity
		return new ResponseEntity<>(response, HttpStatus.OK);
	}	
	
	@RequestMapping(value = "", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<ResponseData<List<ContractTermsDto>>> getContractTerms() {
		ResponseData<List<ContractTermsDto>> response = new ResponseData<>();
		ViewDto view = service.getViewDetails(this.getClass());
		response.setBody(service.readAll(view));
		response.setLinks(null);
		response.setView(view); 
		return new ResponseEntity<>(response, HttpStatus.OK);		
	}
}

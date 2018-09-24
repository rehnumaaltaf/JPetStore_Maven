package com.olam.score.terms.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.olam.score.common.dto.ResponseData;
import com.olam.score.common.util.Mapping;
import com.olam.score.terms.dto.BasePaymentDto;
import com.olam.score.terms.service.BasePaymentTermService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin // TODO: need to remove cross origin in future
@RestController
@Mapping(featureName = "BASE_PAYMENT_TERM", featureId = "7")
@RequestMapping("/terms/basepaymentservice/basepayment")
@Api(value = "base Payment Terms", description = "Operations pertaining to base Payment Terms in OLAM CTRM")
public class BasePaymentTermController {

	@Autowired
	BasePaymentTermService service;
	
	@ApiOperation(value = "View a Base Payment Terms", response = BasePaymentDto.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "User is not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource user is trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource user is trying to reach is not found") })
	@RequestMapping(value = "/{baseTermId}", method = RequestMethod.GET,produces = { "application/json" })
	public ResponseEntity<ResponseData<BasePaymentDto>> getBasePaymentTermById(@PathVariable("baseTermId") Integer baseTermId) {

		ResponseData<BasePaymentDto> response = new ResponseData<>();
		response.setBody(service.readById(baseTermId));
		response.setLinks(null);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@ApiOperation(value = "View a list of available Base payment terms", response = BasePaymentDto.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "User is not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource user is trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource user is trying to reach is not found") })
	@RequestMapping(value = "", method = RequestMethod.GET,produces = { "application/json" })
	public ResponseEntity<ResponseData<List<BasePaymentDto>>> getBasePaymentTerm(
			@RequestParam(value = "termBaseCode", required=false) String termBaseCode,
			@RequestParam(value = "termBaseName", required=false) String termBaseName,
			@RequestParam(value = "status", required=false) String status,Model model) {
		if(termBaseCode!=null && termBaseCode.length()>0){
			model.addAttribute("termBaseCode", termBaseCode);
		}
		if(termBaseName!=null && termBaseName.length()>0){
			model.addAttribute("termBaseName", termBaseName);
		}
		if(status!=null && status.length()>0){
			model.addAttribute("status", status);
		}
		ResponseData<List<BasePaymentDto>> response = new ResponseData<>();
		response.setBody(service.readAll(model));
		response.setLinks(null);
		response.setView(service.getViewDetails(this.getClass()));

		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	@ApiOperation(value = "Create a Base payment terms", response = BasePaymentDto.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "User is not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource user is trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource user is trying to reach is not found") })
	@RequestMapping(value = "", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.POST)
	public ResponseEntity<ResponseData<BasePaymentDto>> addBasePaymentTerm(@Valid @RequestBody BasePaymentDto body) {
		ResponseData<BasePaymentDto> response = new ResponseData<>();
		response.setBody(service.create(body));
		response.setLinks(null);// TOOD: Need to get links for the entity
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	@ApiOperation(value = "Update a Base payment terms", response = BasePaymentDto.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "User is not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource user is trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource user is trying to reach is not found") })
	@RequestMapping(value = "", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.PUT)
	public ResponseEntity<ResponseData<List<BasePaymentDto>>> updateBasePaymentTerm(@Valid @RequestBody List<BasePaymentDto> body) {
		ResponseData<List<BasePaymentDto>> response = new ResponseData<>();
		response.setBody(service.update(body));
		response.setLinks(null);// TOOD: Need to get links for the entity
		return new ResponseEntity<>(response, HttpStatus.OK);

	}
	@ApiOperation(value = "Delete Base payment terms")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "User is not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource user is trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource user is trying to reach is not found") })
	@RequestMapping(value = "", method = RequestMethod.DELETE)
	public ResponseEntity<ResponseData<BasePaymentDto>> deleteBasePaymentTerm(@RequestBody List<Map<String, Integer>> body) {
		ResponseData<BasePaymentDto> response = new ResponseData<>();
		service.deleteBasePaymentTerm(body);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}
}

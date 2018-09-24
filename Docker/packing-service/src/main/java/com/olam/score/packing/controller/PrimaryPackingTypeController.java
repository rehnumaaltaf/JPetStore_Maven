package com.olam.score.packing.controller;

import java.text.ParseException;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.olam.score.common.constants.Constants;
import com.olam.score.common.dto.Link;
import com.olam.score.common.dto.ResponseData;
import com.olam.score.common.service.UtilService;
import com.olam.score.common.util.Mapping;
import com.olam.score.packing.dto.PrimaryPackingTypeDto;
import com.olam.score.packing.service.PrimaryPackingTypeService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
/**
*
* This class act as a controller for the RESTFULL services . will take the
* request from the user and give the response back to user by connecting to
* other all dependent classes
*
* @author Anandhi
* @version 1.0.0, 28 August 2017
*/
@CrossOrigin
@RestController
@RequestMapping(value = "/packing/PrimaryPackingType")
@Mapping(featureName = "PRIMARY_PACKING", featureId = "5")
public class PrimaryPackingTypeController {
	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private PrimaryPackingTypeService service;
	
	@Autowired
	private UtilService utilService;
	
	
	/*
	 * Add Primary Packing
	 */
	@ApiOperation(value = "Create Primary Packing", response = String.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "User is not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource user is trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource user is trying to reach is not found") })
	@RequestMapping(value = "/addPrimaryPackingType", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.POST)
	public ResponseEntity<ResponseData<String>> addPrimaryPacking(@RequestBody PrimaryPackingTypeDto body)
			throws ParseException {
		String toReturn=null;
		ResponseData<String> rsp = new ResponseData<>();
		
			List<Link<?>> links = utilService.getFeatures(this.getClass());
			toReturn=service.create(body);
			rsp.setBody(toReturn);
			rsp.setLinks(links);
		
		return new ResponseEntity<>(rsp, HttpStatus.OK);
			
	}

	/*
	 * This method is used to delete Primary packing
	 */
	@ApiOperation(value = "Delete/Deactivate/Reactivate Primary Packing", response = String.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "User is not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource user is trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource user is trying to reach is not found") })
	@RequestMapping(value = "/deletePrimaryPackingType/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<ResponseData<String>> deletePrimaryPacking(@PathVariable String id) {
		ResponseData<String> rsp = new ResponseData<>();
		try {
			String toReturn = service.deletePrimaryPacking(id);
			List<Link<?>> links = utilService.getFeatures(this.getClass());
			rsp.setBody(toReturn);
			rsp.setLinks(links);
		} catch (Exception e) {
			log.info("delete ip", e);
			return new ResponseEntity<>(rsp, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(rsp, HttpStatus.OK);
	}
	@ApiOperation(value = "Validate Primary Packing", response = String.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "User is not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource user is trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource user is trying to reach is not found") })
	@RequestMapping(value = "/validate", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.POST)
	public ResponseData<String> validate(@RequestBody PrimaryPackingTypeDto request) {
		ResponseData<String> responseData = new ResponseData<>();
		try {
		String value = service.validatePrimaryPacking(request);
		responseData.setBody(value);
		}catch(Exception ex)
		{
			log.info("Validate ip", ex);
		}
		return responseData;
	}
	/*
	 * View all primary packing details
	 */
	@ApiOperation(value = "View Primary Packing", response = String.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "User is not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource user is trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource user is trying to reach is not found") })
	@RequestMapping(value = "/viewPrimaryPackingType", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<ResponseData<List<PrimaryPackingTypeDto>>> getAllPrimaryPacking() {
		ResponseData<List<PrimaryPackingTypeDto>> rsp = new ResponseData<>();
		try {
			List<PrimaryPackingTypeDto> ipTypeList = service.createAndReturnPrimaryPackingViewModelList();
			List<Link<?>> links = utilService.getFeatures(this.getClass());
			rsp.setBody(ipTypeList);
			rsp.setLinks(links);
		} catch (Exception e) {
			log.info("view ip", e);
			return new ResponseEntity<>(rsp, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(rsp, HttpStatus.OK);
	}
	/*
	 * View a particular primary packing
	 */
	@ApiOperation(value = "View Individual Primary Packing", response = String.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "User is not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource user is trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource user is trying to reach is not found") })
	@RequestMapping(value = "/viewPrimaryPackingType/{id}", produces = { "application/json" },method = RequestMethod.GET)
	public ResponseEntity<ResponseData<PrimaryPackingTypeDto>> getPrimaryPackingById(@PathVariable int id) {
		ResponseData<PrimaryPackingTypeDto> rsp = new ResponseData<>();
		try {
			PrimaryPackingTypeDto ipTypeDto = service.createAndReturnPrimaryPackingViewModelListById(id);
			List<Link<?>> links = utilService.getFeatures(this.getClass());
			rsp.setBody(ipTypeDto);
			rsp.setLinks(links);
		} catch (Exception e) {
			log.info("view ip", e);
			return new ResponseEntity<>(rsp, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(rsp, HttpStatus.OK);
	}
	/*
	 * Edit primary packing
	 */
	@ApiOperation(value = "Update a Primary packing", response = String.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "User is not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource user is trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource user is trying to reach is not found") })
	@RequestMapping(value = "/editPrimaryPacking", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.PUT)
	public ResponseEntity<ResponseData<String>> updatePrimaryPacking(@Valid @RequestBody PrimaryPackingTypeDto body) {
		ResponseData<String> response = new ResponseData<>();
		String toReturn=null;
		
			List<Link<?>> links = utilService.getFeatures(this.getClass());
			toReturn=service.update(body);
			response.setBody(toReturn);
			response.setLinks(links);
			return new ResponseEntity<>(response, HttpStatus.OK);
			

	}
	/*
	 * View Internal packing submit
	 */
	@ApiOperation(value = "View Primary Packing Submit", response = String.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "User is not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource user is trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource user is trying to reach is not found") })
	@RequestMapping(value = "/viewSubmit", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.POST)
	public ResponseEntity<ResponseData<String>> viewPrimaryPackingSubmit(@RequestBody PrimaryPackingTypeDto body)
			throws ParseException {
		ResponseData<String> rsp = new ResponseData<>();
		try {
			List<Link<?>> links = utilService.getFeatures(this.getClass());
			rsp.setBody(service.viewPrimaryPackingAction(body));
			rsp.setLinks(links);
		} catch (Exception ex) {
			log.info("View Primary packing submit", ex);
			return new ResponseEntity<>(rsp, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(rsp, HttpStatus.OK);
	}
	/*
	 * Autosuggesting code and name for primary packing on typing
	 */
	@ApiOperation(value = "Auto suggestion for Primary packing code or name", response = String.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "User is not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource user is trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource user is trying to reach is not found") })
	@RequestMapping(value = "/suggest", produces = {"application/json" }, consumes = {"application/json" }, method = RequestMethod.POST)
	public ResponseEntity<ResponseData<List<String>>> getCurrName(@RequestBody PrimaryPackingTypeDto body) {

		ResponseData<List<String>> rsp = new ResponseData<>();
		try{
			List<Link<?>> links = utilService.getFeatures(this.getClass());
			rsp.setBody(service.autoSuggestion(body));
			rsp.setLinks(links);
			return new ResponseEntity<>(rsp, HttpStatus.OK);
		}catch(Exception ex){
			log.info("Inside type ahead", ex);
			return new ResponseEntity<>(rsp, HttpStatus.NOT_FOUND);
		}

	}
	
	@GetMapping(value="/basicdetails",produces = { "application/json" })
	public ResponseEntity<ResponseData<List<PrimaryPackingTypeDto>>> getProductsBasicDetails() {
		ResponseData<List<PrimaryPackingTypeDto>> response = new ResponseData<>();
		// TODO: User Authorization needs to happen
		response.setBody(service.readBasicDetails());
		response.setLinks(null);// TOOD: Need to get links for the entity
		response.setView(service.getViewDetails(this.getClass()));
		return new ResponseEntity<>(response, HttpStatus.OK);

	}
}

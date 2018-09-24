package com.olam.score.packing.controller;

import java.text.ParseException;
import java.util.List;

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

import com.olam.score.common.dto.Link;
import com.olam.score.common.dto.ResponseData;
import com.olam.score.common.service.UtilService;
import com.olam.score.common.util.Mapping;
import com.olam.score.packing.dto.PrimaryPackingTypeDto;
import com.olam.score.packing.dto.SecondaryPackingTypeDTO;
import com.olam.score.packing.service.SecondaryPackingTypeService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin
@RestController
@RequestMapping(value = "/packing/secPackTypeService/secPackType")
@Mapping(featureName = "SECONDARY_PACKING", featureId = "25") // TODO: featureId should be changed
public class SecondaryPackingTypeController {
	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private SecondaryPackingTypeService service;
	
	@Autowired
	private UtilService utilService;
	
	
	/*
	 * Add Secondary Packing
	 */
	@ApiOperation(value = "Create Secondary Packing", response = String.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "User is not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource user is trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource user is trying to reach is not found") })
	@RequestMapping(value = "", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.POST)
	public ResponseEntity<ResponseData<String>> addSecondaryPacking(@RequestBody SecondaryPackingTypeDTO body)
			throws ParseException {
		ResponseData<String> rsp = new ResponseData<>();
		List<Link<?>> links = utilService.getFeatures(this.getClass());
		rsp.setBody(service.create(body));
		rsp.setLinks(links);
		return new ResponseEntity<>(rsp, HttpStatus.OK);
	}
	
	@ApiOperation(value = "View Secondary Packing", response = String.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "User is not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource user is trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource user is trying to reach is not found") })
	@RequestMapping(value = "", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<ResponseData<List<SecondaryPackingTypeDTO>>>  getAllSecondaryPacking() {
		
		ResponseData<List<SecondaryPackingTypeDTO>> rsp = new ResponseData<>();
		List<Link<?>> links = utilService.getFeatures(this.getClass());
		rsp.setBody(service.readAll());
		rsp.setLinks(links);
		return new ResponseEntity<ResponseData<List<SecondaryPackingTypeDTO>>>(rsp, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Update a Secondary packing", response = String.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "User is not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource user is trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource user is trying to reach is not found") })
	@RequestMapping(value = "", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.PUT)
	public ResponseEntity<ResponseData<String>> updateSecondaryPacking(@RequestBody SecondaryPackingTypeDTO body) {
		ResponseData<String> response = new ResponseData<>();
		List<Link<?>> links = utilService.getFeatures(this.getClass());
		response.setBody(service.update(body));
		response.setLinks(links);

		return new ResponseEntity<>(response, HttpStatus.OK);

	}
	
	@ApiOperation(value = "Delete secPack based on the id in Secondary packing Master", response = String.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "User is not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource user is trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource user is trying to reach is not found") })
	@RequestMapping(value = "/{secPackId}", method = RequestMethod.DELETE)
	public ResponseEntity<ResponseData<String>>  deleteSecPack(@PathVariable("secPackId") Integer secPackId) {

		ResponseData<String> rsp = new ResponseData<>();
		List<Link<?>> links = utilService.getFeatures(this.getClass());
		rsp.setBody(service.delete(secPackId));
		rsp.setLinks(links);
		return new ResponseEntity<>(rsp, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{secPackId}", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<ResponseData<List<SecondaryPackingTypeDTO>>> getLocationDetailsById(
			@PathVariable("secPackId") Integer secPackId) {
		
		ResponseData<List<SecondaryPackingTypeDTO>> rsp = new ResponseData<>();
		List<Link<?>> links = utilService.getFeatures(this.getClass());
		rsp.setBody(service.getSecPackById(secPackId));
		rsp.setLinks(links);
		return new ResponseEntity<ResponseData<List<SecondaryPackingTypeDTO>>>(rsp, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/suggestSecCode/{secCode}", produces = { "application/json" }, method = RequestMethod.GET)
	public List<String> getSecCode(@PathVariable("secCode") String secCode) {

		return service.suggestSecCode(secCode);

	}

	@RequestMapping(value = "/suggestSecName/{secName}", produces = { "application/json" }, method = RequestMethod.GET)
	public List<String> getSecName(@PathVariable("secName") String secName) {

		return service.suggestSecName(secName);

	}
	
	@GetMapping(value="/basicdetails",produces = { "application/json" })
	public ResponseEntity<ResponseData<List<SecondaryPackingTypeDTO>>> getProductsBasicDetails() {
		ResponseData<List<SecondaryPackingTypeDTO>> response = new ResponseData<>();
		// TODO: User Authorization needs to happen
		response.setBody(service.readBasicDetails());
		response.setLinks(null);// TOOD: Need to get links for the entity
		response.setView(service.getViewDetails(this.getClass()));
		return new ResponseEntity<>(response, HttpStatus.OK);

	}
	
}

package com.olam.score.location.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.olam.score.common.dto.Link;
import com.olam.score.common.dto.ResponseData;
import com.olam.score.common.service.UtilService;
import com.olam.score.common.util.Mapping;
import com.olam.score.location.dto.GeographyDto;
import com.olam.score.location.service.GeoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin // TODO: need to remove cross origin in future
@RestController
@Mapping(featureName = "GEOGRAPHY", featureId = "3")
@RequestMapping("/location/geoservice/geo")
@Api(value = "Geography", description = "Operations pertaining to Geography in OLAM CTRM")

public class GeoController {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	GeoService geoService;
	
	@Autowired
	UtilService utilService;
	@ApiOperation(value = "Create a Geography", response = GeographyDto.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "User is not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource user is trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource user is trying to reach is not found") })

	@PostMapping(produces = { "application/json" }, consumes = {"application/json" })
	public ResponseEntity<ResponseData<GeographyDto>> addGeo(@Valid @RequestBody GeographyDto body) {
		log.info("In Create Geography service. InputJson");
		ResponseData<GeographyDto> response = new ResponseData<>();
		List<Link<?>> links = utilService.getFeatures(this.getClass());
		response.setBody(geoService.create(body));
		response.setLinks(links);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}
	@ApiOperation(value = "View a Geography", response = GeographyDto.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "User is not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource user is trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource user is trying to reach is not found") })

	@GetMapping(value = "/{geographyId}",produces = { "application/json" })
	public ResponseEntity<ResponseData<GeographyDto>> getGeoById(@PathVariable("geographyId") Integer geographyId) {
		ResponseData<GeographyDto> response = new ResponseData<>();
		List<Link<?>> links = utilService.getFeatures(this.getClass());
		response.setBody(geoService.readById(geographyId));
		response.setLinks(links);// TOOD: Need to get links for the entity
		return new ResponseEntity<>(response, HttpStatus.OK);	}
	@ApiOperation(value = "Delete Geography")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "User is not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource user is trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource user is trying to reach is not found") })

	@DeleteMapping(produces = { "application/json" }, consumes = {"application/json" })
	public ResponseEntity<ResponseData<Map<Integer,String>>> deleteGeography(@RequestBody List<Map<String, Integer>> body) {
		ResponseData<Map<Integer,String>> response = new ResponseData<>();
		response.setBody(geoService.deleteGeography(body));
		return new ResponseEntity<>(response, HttpStatus.OK);


	}
	@ApiOperation(value = "View a list of availableGeography", response = GeographyDto.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "User is not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource user is trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource user is trying to reach is not found") })
	@GetMapping(produces = { "application/json" })
	public ResponseEntity<ResponseData<List<GeographyDto>>> getGeography(
			@RequestParam(value = "geoCode", required=false) String geoCode,
			@RequestParam(value = "geoName", required=false) String geoName,
			@RequestParam(value = "status", required=false) String status,Model model) {
		if(geoCode!=null && geoCode.length()>0){
			model.addAttribute("geoCode", geoCode);
		}
		if(geoName!=null && geoName.length()>0){
			model.addAttribute("geoName", geoName);
		}
		if(status!=null && status.length()>0){
			model.addAttribute("status", status);
		}
		ResponseData<List<GeographyDto>> response = new ResponseData<>();
		List<Link<?>> links = utilService.getFeatures(this.getClass());
		response.setBody(geoService.readAll(model));
		response.setLinks(links);// TOOD: Need to get links for the entity
		response.setView(geoService.getViewDetails(this.getClass()));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	@ApiOperation(value = "Update a Geography", response = GeographyDto.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "User is not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource user is trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource user is trying to reach is not found") })

	@PutMapping(produces = { "application/json" }, consumes = {"application/json" })
	public ResponseEntity<ResponseData<GeographyDto>> updateGeography(
			@Valid @RequestBody GeographyDto body) {
		ResponseData<GeographyDto> response = new ResponseData<>();
		List<Link<?>> links = utilService.getFeatures(this.getClass());
		response.setBody(geoService.update(body));
		response.setLinks(links);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}

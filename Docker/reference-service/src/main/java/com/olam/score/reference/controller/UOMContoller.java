package com.olam.score.reference.controller;

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

import com.olam.score.common.dto.Link;
import com.olam.score.common.dto.ResponseData;
import com.olam.score.common.service.UtilService;
import com.olam.score.common.util.Mapping;
import com.olam.score.reference.dto.UomDto;
import com.olam.score.reference.service.UOMService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin // TODO: need to remove cross origin in future
@RestController
@Mapping(featureName = "UOM", featureId = "2")
@RequestMapping("/reference/uomservice/uom")
@Api(value = "UOM", description = "Operations pertaining to UOM in OLAM CTRM")
public class UOMContoller {

	@Autowired
	UOMService service;

	@Autowired
	UtilService utilService;

	@ApiOperation(value = "Create a Unit Of Measures", response = UomDto.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "User is not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource user is trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource user is trying to reach is not found") })
	@RequestMapping(value = "", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.POST)
	public ResponseEntity<ResponseData<UomDto>> addUOM(@Valid @RequestBody UomDto body) {
		ResponseData<UomDto> response = new ResponseData<>();
		List<Link<?>> links = utilService.getFeatures(this.getClass());

		response.setBody(service.create(body));
		response.setLinks(links);// TOOD: Need to get links for the entity
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@ApiOperation(value = "View a Unit Of Measures", response = UomDto.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "User is not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource user is trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource user is trying to reach is not found") })
	@RequestMapping(value = "/{uomId}", method = RequestMethod.GET,produces = { "application/json" })
	public ResponseEntity<ResponseData<UomDto>> getUOMById(@PathVariable("uomId") Integer uomId) {

		ResponseData<UomDto> response = new ResponseData<>();
		List<Link<?>> links = utilService.getFeatures(this.getClass());

		response.setBody(service.readById(uomId));
		response.setLinks(links);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}


	@ApiOperation(value = "Delete Unit Of Measures")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "User is not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource user is trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource user is trying to reach is not found") })
	@RequestMapping(value = "", method = RequestMethod.DELETE)
	public ResponseEntity<ResponseData<Map<Integer,String>>> deleteUOM(@RequestBody List<Map<String, Integer>> body) {
		ResponseData<Map<Integer,String>> response = new ResponseData<>();
		response.setBody(service.deleteUOM(body));
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@ApiOperation(value = "View a list of available Unit Of Measures", response = UomDto.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "User is not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource user is trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource user is trying to reach is not found") })
	@RequestMapping(value = "", method = RequestMethod.GET,produces = { "application/json" })
	public ResponseEntity<ResponseData<List<UomDto>>> getUOM(
		@RequestParam(value = "uomCode", required=false) String uomCode,
		@RequestParam(value = "uomName", required=false) String uomName,
		@RequestParam(value = "status", required=false) String status,Model model) {
		if(uomCode!=null && uomCode.length()>0){
			model.addAttribute("uomCode", uomCode);
		}
		if(uomName!=null && uomName.length()>0){
			model.addAttribute("uomName", uomName);
		}
		if(status!=null && status.length()>0){
			model.addAttribute("status", status);
		}

		ResponseData<List<UomDto>> response = new ResponseData<>();
		List<Link<?>> links = utilService.getFeatures(this.getClass());
		response.setBody(service.readAll(model));
		response.setLinks(links);
		response.setView(service.getViewDetails(this.getClass()));

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@ApiOperation(value = "Update a Unit Of Measures", response = UomDto.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "User is not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource user is trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource user is trying to reach is not found") })
	@RequestMapping(value = "", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.PUT)
	public ResponseEntity<ResponseData<List<UomDto>>> updateUOM(@Valid @RequestBody List<UomDto> body) {
		ResponseData<List<UomDto>> response = new ResponseData<>();
		List<Link<?>> links = utilService.getFeatures(this.getClass());
		response.setBody(service.update(body));
		response.setLinks(links);// TOOD: Need to get links for the entity
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

}

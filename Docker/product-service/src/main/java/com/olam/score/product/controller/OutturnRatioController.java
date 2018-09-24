package com.olam.score.product.controller;


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
import org.springframework.web.client.HttpStatusCodeException;

import com.olam.score.common.dto.Link;
//import com.olam.score.common.dto.Link;
import com.olam.score.common.dto.ResponseData;
import com.olam.score.common.dto.ViewDto;
import com.olam.score.common.service.UtilService;
import com.olam.score.common.util.Mapping;
import com.olam.score.product.dto.MasterOutturnDto;
import com.olam.score.product.service.OutturnRatioService;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin // TODO: need to remove cross origin in future
@RestController
@Mapping(featureName = "OutturnRatio", featureId = "11")
//@RequestMapping("/outturnratioservice/outturnratio")
@RequestMapping("/product/outturnratioservice/outturnratio")
@Api(value = "OutturnRatio", description = "Operations pertaining to Outturn ratio in OLAM CTRM")
public class OutturnRatioController {
	
	@Autowired
	OutturnRatioService service;
	
	@Autowired
	UtilService utilService;
	
	@ApiOperation(value = "Create an Outturn Ratio", response = MasterOutturnDto.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "User is not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource user is trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource user is trying to reach is not found") })
	@RequestMapping(produces = { "application/json" }, consumes = {
	"application/json" }, method = RequestMethod.POST)
	public ResponseEntity<ResponseData<MasterOutturnDto>> addOutturnRatio(@Valid @RequestBody MasterOutturnDto body) {
		ResponseData<MasterOutturnDto> response = new ResponseData<>();
		//List<Link<?>> links = utilService.getFeatures(this.getClass());

		response.setBody(service.create(body));
		//response.setLinks(links);// TOOD: Need to get links for the entity
		response.setLinks(null);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.DELETE)
	public ResponseEntity<ResponseData<MasterOutturnDto>> deleteOutturnRatio(@RequestBody List<Map<String, Integer>> body) {
		ResponseData<MasterOutturnDto> response = new ResponseData<>();
		try {
			service.deleteOutturn(body);
		} catch (HttpStatusCodeException e) {
			
		}
		return new ResponseEntity<>(response, HttpStatus.OK);

	
				
	
	}
	
	/*@ApiOperation(value = "View a list of available Unit Of Measures", response = UomDto.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "User is not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource user is trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource user is trying to reach is not found") })*/
	@RequestMapping(method = RequestMethod.GET,produces = { "application/json" })
	public ResponseEntity<ResponseData<List<MasterOutturnDto>>> getOutturnRatio() {
		ResponseData<List<MasterOutturnDto>> response = new ResponseData<>();
		//List<Link<?>> links = utilService.getFeatures(this.getClass());
		ViewDto view = service.getViewDetails(this.getClass());
		response.setBody(service.readAll(view));
		response.setLinks(null);//todo: set links
		response.setView(view);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	
	/*@ApiOperation(value = "View a Unit Of Measures", response = UomDto.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "User is not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource user is trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource user is trying to reach is not found") })*/
	@RequestMapping(value= "/{outturnRawGradeId}", method = RequestMethod.GET, produces = { "application/json" })
	public ResponseEntity<ResponseData<MasterOutturnDto>> getOutturnRatioById(@PathVariable("outturnRawGradeId") Integer outturnRawGradeId) {

		ResponseData<MasterOutturnDto> response = new ResponseData<>();
		List<Link<?>> links = utilService.getFeatures(this.getClass());

		response.setBody(service.readById(outturnRawGradeId));
		response.setLinks(links);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Update an Outturn Ratio", response = MasterOutturnDto.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "User is not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource user is trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource user is trying to reach is not found") })
	@RequestMapping( produces = { "application/json" }, consumes = {"application/json" }, method = RequestMethod.PUT)
	public ResponseEntity<ResponseData<List<MasterOutturnDto>>> updateOutturnRatio(@Valid @RequestBody List<MasterOutturnDto> body) {
		ResponseData<List<MasterOutturnDto>> response = new ResponseData<>();
		//List<Link<?>> links = utilService.getFeatures(this.getClass());
		response.setBody(service.update(body));
		response.setLinks(null);// TOOD: Need to get links for the entity
		return new ResponseEntity<>(response, HttpStatus.OK);

	}


	
	
}

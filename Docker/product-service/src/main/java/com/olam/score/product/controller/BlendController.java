package com.olam.score.product.controller;

import java.util.List;


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

import com.olam.score.common.domain.DropDownModel;
import com.olam.score.common.dto.Link;
import com.olam.score.common.dto.ResponseData;

import com.olam.score.common.service.UtilService;
import com.olam.score.common.util.Mapping;

import com.olam.score.product.dto.BlendTemplateDto;

import com.olam.score.product.service.BlendService;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.text.ParseException;
/**
*
* This class act as a controller for the RESTFULL services . will take the
* request from the user and give the response back to user by connecting to
* other all dependent classes
*
* @author Manoj Kumar SP
* @version 1.0.0, 20 September 2017
*/


@CrossOrigin
@RestController
@Mapping(featureName = "BLEND")
@RequestMapping(value = "/product/blendService/blend")
@Api(value = "BLEND", description = "Blend Realted Information")
public class BlendController {
	
	@Autowired
	BlendService service;
	@Autowired
	UtilService utilService;
	@ApiOperation(value = "Create a Blend Master", response = BlendTemplateDto.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "User is not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource user is trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource user is trying to reach is not found") })
	@RequestMapping(value = "", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.POST)
	public ResponseEntity<ResponseData<String>> addBlendTemplate(@Valid @RequestBody BlendTemplateDto body)throws ParseException  {
		ResponseData<String> rsp = new ResponseData<>();
    	rsp.setBody(service.create(body));
    	return new ResponseEntity<>(rsp, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Update a Blend Master", response = BlendTemplateDto.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "User is not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource user is trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource user is trying to reach is not found") })
	@RequestMapping(value="", produces = { "application/json" }, consumes = { "application/json" }, method = RequestMethod.PUT)
	public ResponseEntity<ResponseData<String>> updateBlendTemplate(@Valid @RequestBody BlendTemplateDto body) {

		ResponseData<String> rsp = new ResponseData<>();
	   	rsp.setBody(service.update(body));
	   	return new ResponseEntity<>(rsp, HttpStatus.OK);
	}
	
	@ApiOperation(value = "view a Blend Master", response = BlendTemplateDto.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "User is not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource user is trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource user is trying to reach is not found") })
	@RequestMapping(value = "", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<ResponseData<List<BlendTemplateDto>>> getBlendTemplateDetails() {
		ResponseData<List<BlendTemplateDto>> rsp = new ResponseData<>();
		rsp.setBody(service.readAll());
		return new ResponseEntity<ResponseData<List<BlendTemplateDto>>>(rsp, HttpStatus.OK);
    
	}
	
	
	
	
	@RequestMapping(value = "/{blendId}", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<ResponseData<List<BlendTemplateDto>>> getBlendDetailsById(
			@PathVariable("blendId") Integer blendId) {
		
		ResponseData<List<BlendTemplateDto>> rsp = new ResponseData<>();
		List<Link<?>> links = utilService.getFeatures(this.getClass());
		rsp.setBody(service.readById(blendId));
		rsp.setLinks(links);
		return new ResponseEntity<ResponseData<List<BlendTemplateDto>>>(rsp, HttpStatus.OK);
	}
	
	
	/*@ApiOperation(value = "Delete secPack based on the id in Blend Master", response = String.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "User is not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource user is trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource user is trying to reach is not found") })
	@RequestMapping(value = "", method = RequestMethod.DELETE,produces = { "application/json" }, consumes = {
	"application/json" })
	public ResponseEntity<ResponseData<BlendTemplateDto>>  deleteCost(@RequestBody List<Map<String, Integer>> body) {
		ResponseData<BlendTemplateDto> response=new ResponseData<>();
			service.deleteBlendTemplate(body);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}*/

	@ApiOperation(value = "Delete blend Master based on the id ", response = String.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "User is not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource user is trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource user is trying to reach is not found") })
	@RequestMapping(value = "/{blendId}", method = RequestMethod.DELETE,produces = { "application/json" })
	public ResponseEntity<ResponseData<String>>  deleteBlendTemplate(@PathVariable("blendId") Integer blendId) {

		ResponseData<String> rsp = new ResponseData<>();
		List<Link<?>> links = utilService.getFeatures(this.getClass());
		rsp.setBody(service.delete(blendId));
		rsp.setLinks(links);
		return new ResponseEntity<>(rsp, HttpStatus.OK);
	}
	
	@ApiOperation(value = "dropdown values", response = String.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "User is not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource user is trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource user is trying to reach is not found") })
	@RequestMapping(value = "/dropdown/{ProdId}", method = RequestMethod.GET,produces = { "application/json" })
	public ResponseEntity<ResponseData<List<DropDownModel>>>  getDropDownValues(@PathVariable("ProdId") Integer ProdId) {

		ResponseData<List<DropDownModel>> rsp = new ResponseData<>();
		List<Link<?>> links = utilService.getFeatures(this.getClass());
		rsp.setBody(service.getDropDownValues(ProdId));
		rsp.setLinks(links);
		return new ResponseEntity<>(rsp, HttpStatus.OK);
	}
	
	/*@ApiOperation(value = "dropdown values", response = String.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "User is not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource user is trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource user is trying to reach is not found") })
	@RequestMapping(value = "/dropdown/{ProdId}/{OrigionId}", method = RequestMethod.GET,produces = { "application/json" })
	public ResponseEntity<ResponseData<List<DropDownModel>>>  getDropDownValuesForGrade(@PathVariable("ProdId") Integer ProdId,@PathVariable("OrigionId") Integer OrigionId) {

		ResponseData<List<DropDownModel>> rsp = new ResponseData<>();
		List<Link<?>> links = utilService.getFeatures(this.getClass());
		rsp.setBody(service.getDropDownValuesForGrade(ProdId,OrigionId));
		rsp.setLinks(links);
		return new ResponseEntity<>(rsp, HttpStatus.OK);
	}*/
	@RequestMapping(value = "/suggest", produces = { "application/json" }, consumes = {
	"application/json" }, method = RequestMethod.POST)
public ResponseEntity<ResponseData<List<String>>> getSuggestions(
	@RequestBody BlendTemplateDto blendTemplateDto) {

ResponseData<List<String>> rsp = new ResponseData<>();

	List<Link<?>> links = utilService.getFeatures(this.getClass());
	rsp.setBody(service.autoSuggestion(blendTemplateDto));
	rsp.setLinks(links);
	return new ResponseEntity<>(rsp, HttpStatus.OK);


}

	
}

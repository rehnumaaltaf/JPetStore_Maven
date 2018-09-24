package com.olam.score.product.controller;

import static org.mockito.Matchers.contains;

import java.text.ParseException;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.olam.score.product.dto.BrandDto;
import com.olam.score.product.service.BrandService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin
@RestController
@RequestMapping(value = "/product/brand")
@Mapping(featureName = "BRAND")
public class BrandController {
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private BrandService service;
	
	@Autowired
	private UtilService utilService;
	
	/*
	 * Add Brand
	 */
	@ApiOperation(value = "Create Brand", response = String.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "User is not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource user is trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource user is trying to reach is not found") })
	@RequestMapping(value = "/addBrand", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.POST)
	public ResponseEntity<ResponseData<String>> addBrand(@RequestBody BrandDto body)
			throws ParseException {
		ResponseData<String> rsp = new ResponseData<>();
		String toReturn=null;
		try {
			List<Link<?>> links = utilService.getFeatures(this.getClass());
			toReturn=service.create(body);
			rsp.setBody(toReturn);
			rsp.setLinks(links);
		} catch (Exception ex) {
			log.info("addBrand", ex);
			return new ResponseEntity<>(rsp, HttpStatus.NOT_FOUND);
		}
		if(toReturn.contains(Constants.BRAND_HTTP_RESPONSE_COMPARISON_STRING))
		return new ResponseEntity<>(rsp, HttpStatus.OK);
		else
			return new ResponseEntity<>(rsp, HttpStatus.CONFLICT);	
		
	}
	
	@ApiOperation(value = "View Brands", response = String.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "User is not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource user is trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource user is trying to reach is not found") })
	@RequestMapping(value = "/viewBrand",produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<ResponseData<List<BrandDto>>> getAllBrands() {
		ResponseData<List<BrandDto>> rsp = new ResponseData<>();
		try {
			List<Link<?>> links = utilService.getFeatures(this.getClass());
			rsp.setBody(service.viewBrandList());
			rsp.setLinks(links);
		} catch (Exception e) {
			log.info("view ip", e);
			return new ResponseEntity<>(rsp, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(rsp, HttpStatus.OK);
	}
	
	@ApiOperation(value = "View Brands", response = String.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "User is not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource user is trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource user is trying to reach is not found") })
	@RequestMapping(value = "/viewBrand/{id}",produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<ResponseData<BrandDto>> getBrand(@PathVariable int id) {
		ResponseData<BrandDto> rsp = new ResponseData<>();
		try {
			List<Link<?>> links = utilService.getFeatures(this.getClass());
			rsp.setBody(service.viewBrand(id));
			rsp.setLinks(links);
		} catch (Exception e) {
			log.info("view ip", e);
			return new ResponseEntity<>(rsp, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(rsp, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Delete/Deactivate/Reactivate Brand", response = String.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "User is not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource user is trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource user is trying to reach is not found") })
	@RequestMapping(value = "/deleteBrand/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<ResponseData<String>> deleteBrand(@PathVariable int id) {
		ResponseData<String> rsp = new ResponseData<>();
		try {
			String toReturn = service.deleteBrand(id);
			List<Link<?>> links = utilService.getFeatures(this.getClass());
			rsp.setBody(toReturn);
			rsp.setLinks(links);
		} catch (Exception e) {
			log.info("delete ip", e);
			return new ResponseEntity<>(rsp, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(rsp, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Auto suggestion for brand code or name", response = String.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "User is not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource user is trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource user is trying to reach is not found") })
	@RequestMapping(value = "/suggest", produces = {"application/json" }, consumes = {"application/json" }, method = RequestMethod.POST)
	public ResponseEntity<ResponseData<List<String>>> getSuggestions(@RequestBody BrandDto body) {

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
	
	@ApiOperation(value = "Update a Brand", response = String.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "User is not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource user is trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource user is trying to reach is not found") })
	@RequestMapping(value = "/editBrand", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.POST)
	public ResponseEntity<ResponseData<String>> updatePrimaryPacking(@Valid @RequestBody BrandDto body) {
		ResponseData<String> response = new ResponseData<>();
		String toReturn=null;
		try {
			List<Link<?>> links = utilService.getFeatures(this.getClass());
			toReturn=service.update(body);
			response.setBody(service.update(body));
			response.setLinks(links);
		} catch (Exception e) {
			log.info("Update brand", e);
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
		if(toReturn.contains(Constants.IP_UPDATE_HTTP_STATUS_COMPARISON_STRING))
			return new ResponseEntity<>(response, HttpStatus.OK);
		else
			return new ResponseEntity<>(response, HttpStatus.CONFLICT);

	}
	
	@RequestMapping(value = "/status/{name}", method = RequestMethod.GET)
	public ResponseEntity<ResponseData<Integer>> getStatusId(@PathVariable String name) {
		ResponseData<Integer> rsp = new ResponseData<>();
		try {
			List<Link<?>> links = utilService.getFeatures(this.getClass());
			rsp.setBody(service.returnStatusIdByName(name));
			rsp.setLinks(links);
		} catch (Exception e) {
			log.info("view ip", e);
			return new ResponseEntity<>(rsp, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(rsp, HttpStatus.OK);
	}
}



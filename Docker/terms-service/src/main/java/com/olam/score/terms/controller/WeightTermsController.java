package com.olam.score.terms.controller;

import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
//import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;
import com.olam.score.common.dto.Link;
import com.olam.score.common.dto.ResponseData;
import com.olam.score.common.dto.ViewDto;
import com.olam.score.common.service.UtilService;
import com.olam.score.common.util.Mapping;
import com.olam.score.terms.domain.MasterWeightTerms;
import com.olam.score.terms.dto.BasePaymentDto;
import com.olam.score.terms.dto.FranchiseDto;
import com.olam.score.terms.dto.PaymentTermsDto;
import com.olam.score.terms.dto.ToleranceDto;
import com.olam.score.terms.dto.WeightTermDto;
import com.olam.score.terms.service.WeightTermsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin
@RestController
@Mapping(featureName = "WeightTerms", featureId = "15")
@RequestMapping("/terms/weighttermsservice/weightterms")
@Api(value = "WeightTerms", description = "Operations pertaining to Weightterms in OLAM CTRM")

public class WeightTermsController {
	
	@Autowired
	WeightTermsService service;
	
	@Autowired
	UtilService utilService;
	
	
	/*@ApiOperation(value = "Create an Weight Term", response = WeightTermDto.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "User is not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource user is trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource user is trying to reach is not found") })*/
	@RequestMapping(produces = { "application/json" }, consumes = {
	"application/json" }, method = RequestMethod.POST)
	public ResponseEntity<ResponseData<WeightTermDto>> addOutturnRatio(@Valid @RequestBody WeightTermDto body) {
		ResponseData<WeightTermDto> response = new ResponseData<>();
		//List<Link<?>> links = utilService.getFeatures(this.getClass());

		response.setBody(service.create(body));
		//response.setLinks(links);// TOOD: Need to get links for the entity
		response.setLinks(null);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@RequestMapping(value= "",produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<ResponseData<List<WeightTermDto>>> getWeightTerms() {
		ResponseData<List<WeightTermDto>> response = new ResponseData<>();
		ViewDto view = service.getViewDetails(this.getClass());
		List<WeightTermDto> weightTermsList = service.readAll(view);
		response.setBody(weightTermsList);
		response.setLinks(null);
		response.setView(view);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}
	
// View By Id
	@RequestMapping(value= "/{weightTermsId}", method = RequestMethod.GET, produces = { "application/json" })
	public ResponseEntity<ResponseData<WeightTermDto>> getWeightTermsById(@PathVariable("weightTermsId") Integer weightTermsId) {

		ResponseData<WeightTermDto> response = new ResponseData<>();
		List<Link<?>> links = utilService.getFeatures(this.getClass());

		response.setBody(service.readById(weightTermsId));
		response.setLinks(links);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	//For Update functionality
	
	/*@ApiOperation(value = "Update an Weight Terms", response = WeightTermDto.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "User is not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource user is trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource user is trying to reach is not found") })*/
	@RequestMapping( produces = { "application/json" }, consumes = {"application/json" }, method = RequestMethod.PUT)
	public ResponseEntity<ResponseData<List<WeightTermDto>>> updateWeightTerm(@Valid @RequestBody List<WeightTermDto> body) {
		ResponseData<List<WeightTermDto>> response = new ResponseData<>();
		//List<Link<?>> links = utilService.getFeatures(this.getClass());
		response.setBody(service.update(body));
		response.setLinks(null);// TOOD: Need to get links for the entity
		return new ResponseEntity<>(response, HttpStatus.OK);

	}
	
	// For delete service
	@RequestMapping(method = RequestMethod.DELETE)
	public ResponseEntity<ResponseData<WeightTermDto>> deleteWeightTerm(@RequestBody List<Map<String, Integer>> body) {
		ResponseData<WeightTermDto> response = new ResponseData<>();
		try {
			service.deleteWeightTerm(body);
		} catch (HttpStatusCodeException e) {
			
		}
		return new ResponseEntity<>(response, HttpStatus.OK);

	
				
	
	}

}

package com.olam.score.reference.controller;

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

import com.olam.score.common.dto.Link;
import com.olam.score.common.dto.ResponseData;
import com.olam.score.common.service.UtilService;
import com.olam.score.common.util.Mapping;
import com.olam.score.reference.dto.FinancialCalenderDTO;
import com.olam.score.reference.dto.UomDto;
import com.olam.score.reference.service.FinancialCalenderService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;



/**
 *
 * This class act as a controller for the RESTFULL services . It will take the
 * request from the user and give the response back to user by connecting to
 * other all dependent classes
 *
 * @author Prabhakar
 * @version 1.0.0, 20 July 2017
 */
@CrossOrigin
@RestController
@Mapping(featureName = "FINCAL")
@RequestMapping(value = "/reference/referenceService/financialCalender")
public class FinancialCalenderController {
	private final Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	private FinancialCalenderService financialCalenderService;
	@Autowired
	UtilService utilService;

	@ApiOperation(value = "Create a Financial Calendar", response = UomDto.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "User is not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource user is trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource user is trying to reach is not found") })
	@RequestMapping(value = "", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.POST)
	public ResponseEntity<ResponseData<FinancialCalenderDTO>> addFinCal(@Valid @RequestBody FinancialCalenderDTO financialCalenderDTO)
			throws ParseException {
		ResponseData<FinancialCalenderDTO> response = new ResponseData<>();
		List<Link<?>> links = utilService.getFeatures(this.getClass());
		response.setBody(financialCalenderService.createFinCal(financialCalenderDTO));
		response.setLinks(links);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/validateFinCal/{fiscalYear}", produces = {
			"application/json" }, method = RequestMethod.GET)
	public ResponseEntity<ResponseData<String>> validateFinCal(@PathVariable("fiscalYear") String fiscalYear) {
		String value = financialCalenderService.validate(fiscalYear);
		ResponseData<String> response = new ResponseData<>();
		//List<Link<?>> links = utilService.getFeatures(this.getClass());
			response.setBody(value);
		//	response.setLinks(links);
			return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/validateFinCalMonth/{fiscalYear}/{month}", produces = {
			"application/json" }, method = RequestMethod.GET)
	public ResponseEntity<ResponseData<String>> validateFinCalMonth(@PathVariable("fiscalYear") String fiscalYear,
			@PathVariable("month") String month) {
		String value = financialCalenderService.validateMonth(fiscalYear, month);
		ResponseData<String> response = new ResponseData<>();
		//List<Link<?>> links = utilService.getFeatures(this.getClass());
			response.setBody(value);
		//	response.setLinks(links);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/fiscalYearFinCal/{fiscalYear}", produces = {
			"application/json" }, method = RequestMethod.GET)
	public List<String> fisicalYearFinCal(@PathVariable("fiscalYear") String fiscalYear) {
		return financialCalenderService.getFisicalYear(fiscalYear);
	}

	@RequestMapping(value = "", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<ResponseData<List<?>>> viewFinCal() {
		ResponseData<List<?>> response = new ResponseData<>();
		List<Link<?>> links = utilService.getFeatures(this.getClass());
		response.setBody(financialCalenderService.readFinCal());
		response.setLinks(links);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}
	
	@RequestMapping(value = "/{id}", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<ResponseData<List<?>>> viewFinCalById(@PathVariable("id") Integer fcId) {
		ResponseData<List<?>> response = new ResponseData<>();
		//List<Link<?>> links = utilService.getFeatures(this.getClass());
			response.setBody(financialCalenderService.readFinCalById(fcId));
		//	response.setLinks(links);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@RequestMapping(value = "", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.PUT)
	public ResponseEntity<ResponseData<String>> editFinCal(@RequestBody FinancialCalenderDTO financialCalenderDTO) throws ParseException {

		ResponseData<String> response = new ResponseData<>();
		//List<Link<?>> links = utilService.getFeatures(this.getClass());
		response.setBody(financialCalenderService.updateFinCal(financialCalenderDTO));
		//response.setLinks(links);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@RequestMapping(value = "/{id}", produces = { "application/json" }, method = RequestMethod.DELETE)
	public ResponseData<String> deleteFinCal(@PathVariable("id") Integer fcId) {

		ResponseData<String> rsp = new ResponseData<>();
		try {
			String value = financialCalenderService.deleteFinCal(fcId);
		//	List<Link<?>> links = utilService.getFeatures(this.getClass());
			rsp.setBody(value);
			//rsp.setLinks(links);
		} catch (Exception e) {
			log.error("ProfitCenter Not found:", e);
		}
		return rsp;

	}
	
	@RequestMapping(value = "/changeStatus/{id}", produces = { "application/json" }, method = RequestMethod.PUT)
	public ResponseEntity<ResponseData<String>> changeStatus(@PathVariable("id") Integer fcId) {
		ResponseData<String> response = new ResponseData<>();
		//List<Link<?>> links = utilService.getFeatures(this.getClass());
			response.setBody(financialCalenderService.chnageStatus(fcId));
		//	response.setLinks(links);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}
	

}

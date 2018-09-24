package com.olam.score.finance.controller;

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

import com.olam.score.common.dto.ResponseData;
import com.olam.score.finance.dto.TaxRateDto;
import com.olam.score.finance.service.TaxRateService;

@CrossOrigin
@RestController
@RequestMapping("/finance/taxrate")
public class TaxRateController {

	@Autowired
	TaxRateService service;

	// get by iD
	@RequestMapping(value = "/{taxCodeUID}", method = RequestMethod.GET, produces = { "application/json" })
	public ResponseEntity<ResponseData<TaxRateDto>> getaxRateById(@PathVariable("taxCodeUID") Integer taxCodeUID) {

		ResponseData<TaxRateDto> response = new ResponseData<>();
		// TODO: User Authorization needs to happen
		response.setBody(service.readByTaxRateId(taxCodeUID));
		response.setLinks(null);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// change Status -ACTIVE<-> INACTIVE
	@RequestMapping(value = "/{taxCodeUID}", method = RequestMethod.PUT, produces = { "application/json" })
	public ResponseEntity<ResponseData<String>> changeStatus(@PathVariable("taxCodeUID") Integer taxCodeUID) {

		ResponseData<String> response = new ResponseData<>();
		// TODO: User Authorization needs to happen
		response.setBody(service.updateStatus(taxCodeUID));
		response.setLinks(null);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = { "application/json" }, produces = {
			"application/json" })
	public ResponseEntity<ResponseData<TaxRateDto>> addtaxRate(@Valid @RequestBody TaxRateDto taxrateDto) {
		ResponseData<TaxRateDto> response = new ResponseData<>();

		response.setBody(service.addNewTaxRate(taxrateDto));
		response.setLinks(null);// links
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "", method = RequestMethod.GET, produces = { "application/json" })
	public ResponseEntity<ResponseData<List<TaxRateDto>>> getAllTaxRate() {
		ResponseData<List<TaxRateDto>> response = new ResponseData<>();
		// TODO: User Authorization needs to happen		
		response.setBody(service.getAllTaxRates());
		response.setLinks(null);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.PUT)
	public ResponseEntity<ResponseData<String>> updateTaxRate(@Valid @RequestBody TaxRateDto taxRateDto) {
		ResponseData<String> response = new ResponseData<>();	
		response.setBody(service.updateTaxRate(taxRateDto));
		// response.setLinks(links);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	// delete
	@RequestMapping(value = "/{taxCodeUID}", method = RequestMethod.DELETE)
	public ResponseEntity<ResponseData<String>> deleteTaxRate(@PathVariable("taxCodeUID") Integer taxCodeUID) {

		ResponseData<String> response = new ResponseData<>();
		response.setBody(service.deleteDeactivateOrReactivateTaxRare(taxCodeUID));
		response.setLinks(null);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}

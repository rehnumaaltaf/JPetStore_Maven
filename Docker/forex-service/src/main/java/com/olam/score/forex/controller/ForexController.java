package com.olam.score.forex.controller;

import java.text.ParseException;
import java.util.List;

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

import com.olam.score.forex.domain.MasterForex;
import com.olam.score.forex.dto.ForexCurrencyDto;
import com.olam.score.forex.dto.ForexDto;
import com.olam.score.forex.repository.ForexRepository;
import com.olam.score.forex.repository.TenorTypeRepository;
import com.olam.score.forex.service.ForexCurrencyService;
import com.olam.score.forex.service.ForexService;
import com.olam.score.common.util.Mapping;
import com.olam.score.common.dto.ResponseData;

/**
 * Forex Controller handles all the Forex related request CRUD and Server
 * validation are there
 *
 * @author Anandhi Vijayaraghavan
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/forex/forex")
public class ForexController {
	private final Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	private ForexService service;
	
	@Autowired
	private ForexCurrencyService forexCurrencyService;

	@Autowired
	private ForexRepository repository;
	
	
	
	@Autowired
	private TenorTypeRepository tenorTypeRepository;
	
	/*
	 * This method is used to send all the forex details
	 */
	@RequestMapping(value = "/viewForex", method = RequestMethod.GET)
	public ResponseEntity<ResponseData<List<ForexDto>>> getForex() {
		ResponseData<List<ForexDto>> rsp = new ResponseData<>();
		try {
			List<MasterForex> forexModelList = repository.findAllByOrderByCreatedDateDesc();
			List<ForexDto> forexList = service.createAndReturnForexViewModelList(forexModelList);
			rsp.setBody(forexList);
			rsp.setLinks(null);
		} catch (Exception e) {
			log.info("view forex", e);
			return new ResponseEntity<>(rsp, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(rsp, HttpStatus.OK);
	}
	/*
	 * This method returns all the drop down values
	 */
	@RequestMapping(value = "/ddlist", method = RequestMethod.GET)
	public ResponseEntity<ResponseData<ForexDto>> getDropDownValues() {
		ResponseData<ForexDto> rsp = new ResponseData<>();
		try {
			rsp.setBody(service.returnAllDropDownValues());
			rsp.setLinks(null);
		} catch (Exception e) {
			log.info("ddlist", e);
			return new ResponseEntity<>(rsp, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(rsp, HttpStatus.OK);
	}
	
	@RequestMapping(value = "suggest/{toSuggest}/{value}", produces = {
	"application/json" }, method = RequestMethod.GET)
	public ResponseEntity<ResponseData<List<String>>> getCurrName(@PathVariable("toSuggest") String toSuggest,@PathVariable("value") String value) {

		ResponseData<List<String>> rsp = new ResponseData<>();
		try{
			rsp.setBody(service.returnValuesForKeying(toSuggest, value));
			rsp.setLinks(null);
			return new ResponseEntity<>(rsp, HttpStatus.OK);
		}catch(Exception ex){
			log.info("Inside type ahead", ex);
			return new ResponseEntity<>(rsp, HttpStatus.NOT_FOUND);
		}

	}
	/*
	 * This method returns details of the particular forex with respect to the id passed
	 */
	@RequestMapping(value = "/viewForexDetail/{id}", method = RequestMethod.GET)
	public ResponseEntity<ResponseData<ForexDto>> returnForexDetails(@PathVariable int id) {
		ResponseData<ForexDto> rsp = new ResponseData<>();
		try {
			ForexDto toReturn = service.returnForexDetailsToViewForex(id);
			rsp.setBody(toReturn);
			rsp.setLinks(null);
		} catch (Exception e) {
			log.info("viewForexDetail", e);
			return new ResponseEntity<>(rsp, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(rsp, HttpStatus.OK);
	}
	/*
	 * This method is used to add forex
	 */
	@RequestMapping(value = "/addForex", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.POST)
	public ResponseEntity<ResponseData<String>> addForex(@RequestBody ForexDto body) throws ParseException {
		ResponseData<String> rsp = new ResponseData<>();
		try {
			rsp.setBody(service.create(body));
			} catch (Exception ex) {
			log.info("addForex", ex);
			return new ResponseEntity<>(rsp, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(rsp, HttpStatus.OK);
	}
	/*
	 * This method is used to delete forex
	 */
	@RequestMapping(value = "/deleteForex/{id}/{status}", method = RequestMethod.DELETE)
	public ResponseEntity<ResponseData<String>> deleteForex(@PathVariable int id,@PathVariable int status) {
		ResponseData<String> rsp = new ResponseData<>();
		try {
			String toReturn = service.deactivateOrReactivateForex(id,status);
			rsp.setBody(toReturn);
			rsp.setLinks(null);
		} catch (Exception e) {
			log.info("deleteForex", e);
			return new ResponseEntity<>(rsp, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(rsp, HttpStatus.OK);
	}
	/*
	 * this method is used to validate forex
	 */
	@RequestMapping(value = "/validate", produces = { "application/json" }, consumes = { "application/json" }, method = RequestMethod.POST)
    public ResponseData<String> validate(@RequestBody ForexDto request) {
		String value = service.validateForex(request);
		ResponseData<String> responseData = new ResponseData<>();
    	responseData.setBody(value);
    	return responseData;
    }
	
	@RequestMapping(value = "/viewTenor/{id}", method = RequestMethod.GET)
	public ResponseData<String> returnTenorDetails(@PathVariable int id) {
		ResponseData<String> rsp = new ResponseData<>();
		try {
			String toReturn=tenorTypeRepository.getByPkTenorTypeId(id).getTenorTypeName();
			rsp.setBody(toReturn);
			rsp.setLinks(null);
		} catch (Exception e) {
			log.info("viewForexDetail", e);
			return rsp;
		}
		return rsp;
	}
	
	@RequestMapping(value = "/forexCurrency", method = RequestMethod.GET, produces = { "application/json" })
	public ResponseEntity<ResponseData<List<ForexCurrencyDto>>> getForexCurrency() {
		ResponseData<List<ForexCurrencyDto>> rsp = new ResponseData<>();
		rsp.setBody(forexCurrencyService.readAll());
		rsp.setLinks(null);
		return new ResponseEntity<>(rsp, HttpStatus.OK);
	}

	@RequestMapping(value = "/forexCurrency/{forexCurrencyId}", method = RequestMethod.GET, produces = { "application/json" })
	public ResponseEntity<ResponseData<ForexCurrencyDto>> getForexCurrencyById(
			@PathVariable("forexCurrencyId") Integer forexCurrencyId) {

		ResponseData<ForexCurrencyDto> response = new ResponseData<>();
		response.setBody(forexCurrencyService.readById(forexCurrencyId));
		response.setLinks(null);// TOOD: Need to get links for the entity
		return new ResponseEntity<>(response, HttpStatus.OK);

	}
	
}

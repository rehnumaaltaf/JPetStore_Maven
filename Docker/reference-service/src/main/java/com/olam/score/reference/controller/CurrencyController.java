package com.olam.score.reference.controller;

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

import com.olam.score.reference.domain.MasterCurrency;
import com.olam.score.reference.dto.CurrencyDTO;
import com.olam.score.common.dto.ResponseData;
import com.olam.score.reference.service.CurrencyService;

/**
 * Currency Controller handles all the Currency related request CRUD and Server
 * validation are there
 *
 * @author Manoj Kumar SP
 * @version 1.0
 * @since 2017-07-15
 */

@CrossOrigin
@RestController
@RequestMapping(value = "reference/currencyservice/currency")
public class CurrencyController {

	@Autowired
	private CurrencyService service;
	private final Logger log = LoggerFactory.getLogger(getClass());

	/**
	 * This method is used to add new Currency in the list
	 * 
	 * @param numA
	 *            Passing CurrencyDTO as JSON
	 * @return status of add status.
	 */
	@RequestMapping(produces = { "application/json" }, consumes = { "application/json" }, method = RequestMethod.POST)
	public ResponseEntity<ResponseData<String>>  addCurrency(@Valid @RequestBody CurrencyDTO body) {
		
		ResponseData<String> rsp = new ResponseData<>();
		rsp.setBody(service.create(body));
		return new ResponseEntity<>(rsp, HttpStatus.OK);

	}

	/**
	 * This method is used to delete Currency in the list
	 * 
	 * @param numA
	 *            currencyId is a primary key based on that delete happens
	 * @return status of delete status.
	 */
	@RequestMapping(value = "/{currencyId}", method = RequestMethod.DELETE)
	public ResponseEntity<ResponseData<String>>  deleteCurrency(@PathVariable("currencyId") Integer currencyId) {

		ResponseData<String> rsp = new ResponseData<>();
		rsp.setBody(service.delete(currencyId));
		return new ResponseEntity<>(rsp, HttpStatus.OK);
	}

	/**
	 * This method is used to view Currency in the list
	 * 
	 * @return status of currency list.
	 */
	@RequestMapping(method = RequestMethod.GET,produces = { "application/json" })
	public ResponseEntity<ResponseData<List<CurrencyDTO>>> getCurrency() {
		ResponseData<List<CurrencyDTO>> rsp = new ResponseData<>();
		rsp.setBody(service.readAll());
		rsp.setLinks(null);
		return new ResponseEntity<>(rsp, HttpStatus.OK);
	}

	/**
	 * This method is used to Update Currency in the list
	 * 
	 * @param numA
	 *            Passing CurrencyDTO as JSON
	 * @return status of update status.
	 */
	@RequestMapping(produces = { "application/json" }, consumes = { "application/json" }, method = RequestMethod.PUT)
	public ResponseEntity<ResponseData<String>>  updateCurrency(@Valid @RequestBody CurrencyDTO body) {

		ResponseData<String> rsp = new ResponseData<>();
		rsp.setBody(service.update(body));
		return new ResponseEntity<>(rsp, HttpStatus.OK);
	}

	/**
	 * This method is used to auto suggest and unique constraint validation
	 * 
	 * @param numA
	 *            currencyName check if already exist
	 * @return list of currencyName matches.
	 */
	@RequestMapping(value = "suggestCurrName/{currencyName}", produces = {
			"application/json" }, method = RequestMethod.GET)
	public List<String> getCurrName(@PathVariable("currencyName") String currencyName) {

		return service.suggestName(currencyName);

	}

	/**
	 * This method is used to auto suggest and unique constraint validation
	 * 
	 * @param numA
	 *            currencyCode check if already exist
	 * @return list of currencyCode matches.
	 */
	@RequestMapping(value = "suggestCurrCode/{currencyCode}", produces = {
			"application/json" }, method = RequestMethod.GET)
	public List<String> getCurrCode(@PathVariable("currencyCode") String currencyCode) {

		return service.suggestCode(currencyCode);

	}

	/**
	 * This method is used to auto suggest and unique constraint validation
	 * 
	 * @param numA
	 *            currencyName check if already exist
	 * @return list of currencyName matches.
	 */
	@RequestMapping(value = "uniqueCurrName/{currencyName}", produces = {
			"application/json" }, method = RequestMethod.GET)
	public int getuniqueCurrName(@PathVariable("currencyName") String currencyName) {

		return service.uniqueName(currencyName);

	}

	/**
	 * This method is used to auto suggest and unique constraint validation
	 * 
	 * @param numA
	 *            currencyCode check if already exist
	 * @return list of currencyCode matches.
	 */
	@RequestMapping(value = "uniqueCurrCode/{currencyCode}", produces = {
			"application/json" }, method = RequestMethod.GET)
	public int getuniqueCurrCode(@PathVariable("currencyCode") String currencyCode) {

		return service.uniqueCode(currencyCode);

	}

	/**
	 * This method is used to pre load drop down during add and edit
	 * 
	 * @return list of currencySymbol list.
	 */
	/*@RequestMapping(value = "/getCurrList", produces = { "application/json" }, method = RequestMethod.GET)
	public List<String> getDropDownList() {

		return service.getDropdownList();
	}*/

	@RequestMapping(value = "/getCurrList",produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseData<List<MasterCurrency>> returnCurrencyList() {
		
		ResponseData<List<MasterCurrency>> rsp = new ResponseData<>();
		rsp.setBody(service.getAllCurrency());
		return rsp;

	}

	@RequestMapping(value = "/getCurr/{id}",produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseData<MasterCurrency> returnCurrency(@PathVariable("id") int id) {
		ResponseData<MasterCurrency> rsp = new ResponseData<>();
		rsp.setBody(service.returnCurrencyById(id));
		return rsp;

	}

	@RequestMapping(value = "/getCurrency/{id}", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseData<CurrencyDTO> getCurrencyById(@PathVariable("id") Integer id) {
		
		ResponseData<CurrencyDTO> rsp = new ResponseData<>();
		rsp.setBody(service.getCurrencyById(id));
		return rsp;
	}

	@RequestMapping(value = "/{currencyId}", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<ResponseData<CurrencyDTO>> getCurrencyDetailsById(
			@PathVariable("currencyId") Integer currencyId) {
		
		ResponseData<CurrencyDTO> rsp = new ResponseData<>();
		rsp.setBody(service.getCurrencyById(currencyId));
		return new ResponseEntity<>(rsp, HttpStatus.OK);
	}
}

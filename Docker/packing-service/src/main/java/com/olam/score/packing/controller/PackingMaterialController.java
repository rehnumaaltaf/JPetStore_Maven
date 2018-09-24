package com.olam.score.packing.controller;

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

import com.olam.score.common.dto.Link;
import com.olam.score.common.dto.ResponseData;
import com.olam.score.common.service.UtilService;
import com.olam.score.common.util.Mapping;
import com.olam.score.packing.dto.PackingMaterialDTO;
import com.olam.score.packing.service.PackingMaterialService;

/**
 *
 * This class act as a controller for the RESTFULL services . will take the
 * request from the user and give the response back to user by connecting to
 * other all dependent classes
 *
 * @author Prabhakar
 * @version 1.0.0, 21 August 2017
 */

@CrossOrigin
@RestController
@Mapping(featureName = "PACKING_MATERIAL")
@RequestMapping(value = "/packing/packingMaterial")
public class PackingMaterialController {
	@Autowired
	private PackingMaterialService packingMaterialService;
	@Autowired
	private UtilService utilService;
	private final Logger log = LoggerFactory.getLogger(getClass());

	@RequestMapping(value = "/addPackingMaterial", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.POST)
	public ResponseEntity<ResponseData<String>> addPackingMaterial(@RequestBody PackingMaterialDTO packingMaterialDTO) {
		ResponseData<String> response = new ResponseData<>();
		List<Link<?>> links = utilService.getFeatures(this.getClass());
		response.setBody(packingMaterialService.createPackingMaterial(packingMaterialDTO));
		response.setLinks(links);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@RequestMapping(value = "/editPackingMaterial", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.PUT)
	public ResponseEntity<ResponseData<String>> editPackingMaterial(
			@RequestBody PackingMaterialDTO packingMaterialDTO) {
		ResponseData<String> response = new ResponseData<>();
		List<Link<?>> links = utilService.getFeatures(this.getClass());
		response.setBody(packingMaterialService.updatePackingMaterial(packingMaterialDTO));
		response.setLinks(links);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@RequestMapping(value = "/validatePackingMaterial", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.POST)
	public ResponseEntity<ResponseData<String>> validatePackingMaterial(
			@RequestBody PackingMaterialDTO packingMaterialDTO) {
		ResponseData<String> response = new ResponseData<>();
		List<Link<?>> links = utilService.getFeatures(this.getClass());
		response.setBody(packingMaterialService.validatePackingMaterial(packingMaterialDTO));
		response.setLinks(links);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@RequestMapping(value = "/viewPackMaterial", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<ResponseData<List<?>>> viewPackMaterial() {
		ResponseData<List<?>> response = new ResponseData<>();
		List<Link<?>> links = utilService.getFeatures(this.getClass());
		response.setBody(packingMaterialService.readPackingmaterial());
		response.setLinks(links);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@RequestMapping(value = "/deletePackingMaterial/{packingMaterialId}", produces = {
			"application/json" }, method = RequestMethod.DELETE)
	public ResponseEntity<ResponseData<String>> deletePackingMaterial(
			@PathVariable("packingMaterialId") Integer packingMaterialId) {
		ResponseData<String> response = new ResponseData<>();
		List<Link<?>> links = utilService.getFeatures(this.getClass());
		response.setBody(packingMaterialService.deletePackingMaterial(packingMaterialId));
		response.setLinks(links);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@RequestMapping(value = "/viewPackingMaterialId/{id}", produces = {
			"application/json" }, method = RequestMethod.GET)
	public ResponseEntity<ResponseData<List<?>>> viewPackingMaterialId(@PathVariable("id") Integer id) {
		ResponseData<List<?>> response = new ResponseData<>();
		List<Link<?>> links = utilService.getFeatures(this.getClass());
		response.setBody(packingMaterialService.readPackingMaterialId(id));
		response.setLinks(links);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@RequestMapping(value = "/changeStatus/{id}", produces = { "application/json" }, method = RequestMethod.PUT)
	public ResponseEntity<ResponseData<String>> changeStatus(@PathVariable("id") Integer id) {
		ResponseData<String> response = new ResponseData<>();
		List<Link<?>> links = utilService.getFeatures(this.getClass());
		response.setBody(packingMaterialService.changeStatus(id));
		response.setLinks(links);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@RequestMapping(value = "/suggest", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.POST)
	public ResponseEntity<ResponseData<List<String>>> getSuggestions(
			@RequestBody PackingMaterialDTO packingMaterialDTO) {

		ResponseData<List<String>> rsp = new ResponseData<>();
		try {
			List<Link<?>> links = utilService.getFeatures(this.getClass());
			rsp.setBody(packingMaterialService.autoSuggestion(packingMaterialDTO));
			rsp.setLinks(links);
			return new ResponseEntity<>(rsp, HttpStatus.OK);
		} catch (Exception ex) {
			log.info("Inside type ahead", ex);
			return new ResponseEntity<>(rsp, HttpStatus.NOT_FOUND);
		}

	}

}

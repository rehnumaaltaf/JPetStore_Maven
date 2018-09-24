package com.olam.score.product.controller;

import java.util.List;
import java.util.Map;

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
import com.olam.score.product.dto.CropYearDto;
import com.olam.score.product.service.CropYearService;

@CrossOrigin // TODO: need to remove cross origin in future
@RestController
@Mapping(featureName = "CROPYEAR")
@RequestMapping("/product/cropyearservice/cropyear")
public class CropYearController {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	CropYearService cropYearService;

	@Autowired
	UtilService utilService;

	@RequestMapping(value = "", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.POST)
	public ResponseEntity<ResponseData<CropYearDto>> addCropyear(@Valid @RequestBody CropYearDto body) {

		log.info("In Create Crop year Controller addCropyear().");
		ResponseData<CropYearDto> response = new ResponseData<>();
		if (body.getAction().equalsIgnoreCase(Constants.DRAFT)) {
			body.setStatusName(Constants.DRAFT);
		} else if (body.getAction().equalsIgnoreCase(Constants.SAVE)) {
			body.setStatusName(Constants.ACTIVE);
		}

		List<Link<?>> links = utilService.getFeatures(this.getClass());
		response.setBody(cropYearService.create(body));
		// TODO: need to set the links based on authorization
		response.setLinks(links);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@RequestMapping(value = "", method = RequestMethod.DELETE, produces = { "application/json" }, consumes = {
			"application/json" })
	public HttpStatus deleteCropyear(@RequestBody List<Map<String, Integer>> body) {
		log.info("In Delete Crop year Controller deleteCropyear().");
		cropYearService.deleteCropyear(body);
		return HttpStatus.OK;

	}

	@RequestMapping(value = "", method = RequestMethod.GET, produces = { "application/json" })
	public ResponseEntity<ResponseData<List<CropYearDto>>> getCropYear() {
		ResponseData<List<CropYearDto>> response = new ResponseData<>();
		List<Link<?>> links = utilService.getFeatures(this.getClass());
		response.setBody(cropYearService.readAll());
		response.setLinks(links);// TOOD: Need to get links for the entity
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/{cropYearId}", method = RequestMethod.GET, produces = { "application/json" })
	public ResponseEntity<ResponseData<CropYearDto>> getCropYearById(@PathVariable("cropYearId") Integer cropYearId) {
		ResponseData<CropYearDto> response = new ResponseData<>();
		List<Link<?>> links = utilService.getFeatures(this.getClass());
		response.setBody(cropYearService.readById(cropYearId));
		response.setLinks(links);// TOOD: Need to get links for the entity
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.PUT)
	public ResponseEntity<ResponseData<CropYearDto>> updateCropYear(@Valid @RequestBody CropYearDto body) {
		log.info("In Update Crop year Controller updateCropYear().");
		ResponseData<CropYearDto> response = new ResponseData<>();
		List<Link<?>> links = utilService.getFeatures(this.getClass());
		response.setBody(cropYearService.update(body));
		response.setLinks(links);// TOOD: Need to get links for the entity
		return new ResponseEntity<>(response, HttpStatus.OK);

	}
}

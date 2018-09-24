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
import com.olam.score.common.dto.ViewDto;
import com.olam.score.common.service.UtilService;
import com.olam.score.common.util.Mapping;
import com.olam.score.product.dto.CertificationDto;
import com.olam.score.product.dto.CropYearDto;
import com.olam.score.product.service.CertificationService;

@CrossOrigin // TODO: need to remove cross origin in future
@RestController
@Mapping(featureName = "CERTIFICATION", featureId = "8")
@RequestMapping("/product/certificationservice/certification")
public class CertificationController {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	CertificationService certificationService;

	@Autowired
	UtilService utilService;

	@RequestMapping(value = "", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.POST)
	public ResponseEntity<ResponseData<CertificationDto>> addCertification(@Valid @RequestBody CertificationDto body) {
		ResponseData<CertificationDto> response = new ResponseData<>();
		String action = body.getAction();
		if (action.equalsIgnoreCase(Constants.DRAFT)) {
			body.setStatusName(Constants.DRAFT);
		} else if (action.equalsIgnoreCase(Constants.SAVE)) {
			body.setStatusName(Constants.ACTIVE);
		}
		response.setBody(certificationService.create(body));
		response.setLinks(null);// TOOD: Need to get links for the entity
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "", method = RequestMethod.GET, produces = { "application/json" })
	public ResponseEntity<ResponseData<List<CertificationDto>>> getCertification() {
		ResponseData<List<CertificationDto>> response = new ResponseData<>();
		List<Link<?>> links = utilService.getFeatures(this.getClass());
		ViewDto view = certificationService.getViewDetails(this.getClass());
		response.setBody(certificationService.readAll(view));
		response.setLinks(links);// TOOD: Need to get links for the entity
		response.setView(view);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/{certificationId}", method = RequestMethod.GET, produces = { "application/json" })
	public ResponseEntity<ResponseData<CertificationDto>> getCertificationById(
			@PathVariable("certificationId") Integer certificationId) {
		ResponseData<CertificationDto> response = new ResponseData<>();
		List<Link<?>> links = utilService.getFeatures(this.getClass());
		response.setBody(certificationService.readById(certificationId));
		response.setLinks(links);// TOOD: Need to get links for the entity
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "", method = RequestMethod.DELETE, produces = { "application/json" }, consumes = {
			"application/json" })
	public HttpStatus deleteCertification(@RequestBody List<Map<String, Integer>> body) {
		log.info("In Delete Crop year Controller deleteCropyear().");
		certificationService.deleteCertification(body);
		return HttpStatus.OK;

	}

	@RequestMapping(value = "", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.PUT)
	public ResponseEntity<ResponseData<CertificationDto>> updateCertification(@Valid @RequestBody CertificationDto body) {
		log.info("In Update Crop year Controller updateCropYear().");
		ResponseData<CertificationDto> response = new ResponseData<>();
		List<Link<?>> links = utilService.getFeatures(this.getClass());
		response.setBody(certificationService.update(body));
		response.setLinks(links);// TOOD: Need to get links for the entity
		return new ResponseEntity<>(response, HttpStatus.OK);

	}
}

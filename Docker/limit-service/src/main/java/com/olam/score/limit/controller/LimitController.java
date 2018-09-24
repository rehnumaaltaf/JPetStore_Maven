package com.olam.score.limit.controller;

import java.util.List;
import java.util.Map;

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
import com.olam.score.common.util.Mapping;
import com.olam.score.limit.dto.LimitBasisTypeDto;
import com.olam.score.limit.dto.LimitDto;
import com.olam.score.limit.dto.LimitVolumeValueTypeDto;
import com.olam.score.limit.service.LimitService;

/**
 * @author Mohit_Agrawal08
 *
 */
@CrossOrigin // TODO: need to remove cross origin in future
@RestController
@Mapping(featureName = "LIMIT", featureId = "23")
@RequestMapping("/limit/limitservice/limit")
public class LimitController {

	@Autowired
	LimitService service;

	@RequestMapping(value = "/{limitId}", method = RequestMethod.GET, produces = { "application/json" })
	public ResponseEntity<ResponseData<LimitDto>> getLimitById(@PathVariable("limitId") Integer limitId) {

		ResponseData<LimitDto> response = new ResponseData<>();
		response.setBody(service.readById(limitId));
		response.setLinks(null);// TOOD: Need to get links for the entity
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@RequestMapping(value = "", method = RequestMethod.GET, produces = { "application/json" })
	public ResponseEntity<ResponseData<List<LimitDto>>> getLimit() {
		ResponseData<List<LimitDto>> response = new ResponseData<>();
		response.setBody(service.readAll());
		response.setLinks(null);// TOOD: Need to get links for the entity
		response.setView(service.getViewDetails(this.getClass()));
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@RequestMapping(value = "", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.POST)
	public ResponseEntity<ResponseData<LimitDto>> addLimit(@Valid @RequestBody LimitDto body) {
		ResponseData<LimitDto> response = new ResponseData<>();
		response.setBody(service.create(body));
		response.setLinks(null);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "", produces = { "application/json" }, consumes = {
	"application/json" }, method = RequestMethod.PUT)
	public ResponseEntity<ResponseData<LimitDto>> updateLimit(@Valid @RequestBody LimitDto body){
		ResponseData<LimitDto> response = new ResponseData<>();
		response.setBody(service.update(body));
		response.setLinks(null);
	return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/limitBasisType", method = RequestMethod.GET, produces = { "application/json" })
	public ResponseEntity<ResponseData<List<LimitBasisTypeDto>>> getLimitBasisType() {
		ResponseData<List<LimitBasisTypeDto>> response = new ResponseData<>();
		// TODO: User Authorization needs to happen
		response.setBody(service.readLimitBasisType());
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@RequestMapping(value = "/limitVolumeValueType", method = RequestMethod.GET, produces = { "application/json" })
	public ResponseEntity<ResponseData<List<LimitVolumeValueTypeDto>>> getLimitVolumeValueType() {
		ResponseData<List<LimitVolumeValueTypeDto>> response = new ResponseData<>();
		// TODO: User Authorization needs to happen
		response.setBody(service.readLimitVolumeValueType());
		return new ResponseEntity<>(response, HttpStatus.OK);

	}
	
	@RequestMapping(value = "", method = RequestMethod.DELETE)
	public ResponseEntity<ResponseData<LimitDto>> deleteLimit(@RequestBody List<Map<String, Integer>> body){
		ResponseData<LimitDto> response=new ResponseData<>();
		service.delete(body);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}

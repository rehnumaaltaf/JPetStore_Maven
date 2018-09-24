package com.olam.score.cost.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.olam.score.common.dto.ResponseData;
import com.olam.score.cost.dto.CostGroupDto;
import com.olam.score.cost.service.CostGroupService;

@CrossOrigin // TODO: need to remove cross origin in future
@RestController
@RequestMapping("/cost/costgroupservice/costgroup")
public class CostGroupController {

	@Autowired
	CostGroupService service;
	
	@RequestMapping(value = "", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<ResponseData<List<CostGroupDto>>> getcostGroup() {
		ResponseData<List<CostGroupDto>> response = new ResponseData<>();
		response.setBody(service.getCostGroup());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}

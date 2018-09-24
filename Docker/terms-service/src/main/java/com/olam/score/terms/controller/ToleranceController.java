package com.olam.score.terms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.olam.score.common.dto.ResponseData;
import com.olam.score.common.dto.ViewDto;
import com.olam.score.common.service.UtilService;
import com.olam.score.terms.dto.ToleranceDto;
import com.olam.score.terms.service.ToleranceService;
@CrossOrigin
@RestController
//@Mapping(featureName = "WeightTerms", featureId = "12")
@RequestMapping("/terms/toleranceservice/tolerance")

public class ToleranceController {
	
	@Autowired
	ToleranceService service;
	
	@Autowired
	UtilService utilService;
	
	@RequestMapping(value= "", method = RequestMethod.GET, produces = {"application/json"})
	public ResponseEntity<ResponseData<List<ToleranceDto>>> getToleranceValueUnit() {
		ResponseData<List<ToleranceDto>> response = new ResponseData<>();
		//ViewDto view = service.getViewDetails(this.getClass());
		List<ToleranceDto> toleranceList = service.readAll1(null);
		response.setBody(toleranceList);
		response.setLinks(null);
		response.setView(null);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	

}

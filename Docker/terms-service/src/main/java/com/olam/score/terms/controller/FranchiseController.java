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
import com.olam.score.terms.dto.FranchiseDto;
import com.olam.score.terms.service.FranchiseService;
import com.olam.score.terms.service.WeightTermsService;
@CrossOrigin
@RestController
//@Mapping(featureName = "WeightTerms", featureId = "12")
@RequestMapping("/terms/franchiseservice/franchise")

public class FranchiseController {

	@Autowired
	FranchiseService service;
	
	@Autowired
	UtilService utilService;
	
	@RequestMapping(value= "", method = RequestMethod.GET, produces = {"application/json"})
	public ResponseEntity<ResponseData<List<FranchiseDto>>> getFranchiseValueUnit() {
		ResponseData<List<FranchiseDto>> response = new ResponseData<>();
		//ViewDto view = service.getViewDetails(this.getClass());
		List<FranchiseDto> franchiseList = service.readAll1(null);
		response.setBody(franchiseList);
		response.setLinks(null);
		response.setView(null);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}

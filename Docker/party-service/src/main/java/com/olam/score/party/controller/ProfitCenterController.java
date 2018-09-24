package com.olam.score.party.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.olam.score.common.dto.ResponseData;
import com.olam.score.common.util.Mapping;
import com.olam.score.party.dto.ProfitCenterDto;
import com.olam.score.party.service.ProfitCenterService;
/**
*
* This class act as a controller   for the  RESTFULL services .
*It will take the request from the user and give the response back to user by connecting to other all dependent classes
*
* @author Prabhakar
* @version 1.00, 10 July 2017
*/

@CrossOrigin
@RestController
@Mapping(featureName = "UNIT")
@RequestMapping(value = "party/unitservice/unit")
public class ProfitCenterController {
	
	@Autowired
	private ProfitCenterService service;
	
	public final Logger log = LoggerFactory.getLogger(getClass());

	@RequestMapping(value = "", method = RequestMethod.GET, produces = { "application/json" })
    public ResponseEntity<ResponseData<List<?>>> getUM(){
    	ResponseData<List<?>> rsp = new ResponseData<>();
    	try {
    		rsp.setBody(service.readAll());
    		rsp.setLinks(null);
    	} catch (Exception e) {
    		log.error("ProfitCenter Not found",e);
    		return new ResponseEntity<>(rsp, HttpStatus.NOT_FOUND);
		}
    	return new ResponseEntity<>(rsp, HttpStatus.OK);
    }
	
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = { "application/json" })
    public ResponseEntity<ResponseData<ProfitCenterDto>> getUnitMaster(@PathVariable("id") Integer unitMasterId){
    	ResponseData<ProfitCenterDto> response = new ResponseData<>();
    	try {
    		response.setBody(service.readAllByuintId(unitMasterId));
    		response.setLinks(null);
    	} catch (Exception e) {
    		log.error("ProfitCenter Not found:",e);
    		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
    	return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
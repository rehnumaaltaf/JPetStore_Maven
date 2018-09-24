package com.olam.score.product.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.ws.rs.QueryParam;

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
import com.olam.score.product.dto.GradeDto;
import com.olam.score.product.service.GradeService;

/**
 * @author Mohit_Agrawal08
 *
 */
@CrossOrigin // TODO: need to remove cross origin in future
@RestController
@Mapping(featureName = "GRADE", featureId = "16")
@RequestMapping("/product/gradeservice/grade")
public class GradeController {

	@Autowired
	GradeService service;

	@RequestMapping(value = "/{gradeId}", method = RequestMethod.GET,produces = { "application/json" })
	public ResponseEntity<ResponseData<GradeDto>> getGradeById(@PathVariable("gradeId") Integer gradeId) {

		ResponseData<GradeDto> response = new ResponseData<>();
		// TODO: User Authorization needs to happen
		response.setBody(service.readById(gradeId));
		response.setLinks(null);// TOOD: Need to get links for the entity
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@RequestMapping(value = "", method = RequestMethod.GET,produces = { "application/json" })
	public ResponseEntity<ResponseData<List<GradeDto>>> getGrades(@QueryParam ("prodId") Integer prodId,@QueryParam ("gradeIsRaw") String gradeIsRaw) {
		ResponseData<List<GradeDto>> response = new ResponseData<>();
		// TODO: User Authorization needs to happen
		response.setBody(service.readAll(prodId,gradeIsRaw));
		response.setLinks(null);// TOOD: Need to get links for the entity
		response.setView(service.getViewDetails(this.getClass()));
		return new ResponseEntity<>(response, HttpStatus.OK);

	}
	
	@RequestMapping(value = "", produces = { "application/json" }, consumes = { "application/json" }, method = RequestMethod.POST)
    public ResponseEntity<ResponseData<GradeDto>> addProductGrade(@Valid @RequestBody GradeDto body){
    	ResponseData<GradeDto> response = new ResponseData<>();
    		response.setBody(service.create(body));
    		response.setLinks(null);
    	return new ResponseEntity<>(response, HttpStatus.OK);
    } 	
	@RequestMapping(value = "", method = RequestMethod.DELETE)
	public ResponseEntity<ResponseData<GradeDto>> deleteGrade(@RequestBody List<Map<String, Integer>> body){
		ResponseData<GradeDto> response=new ResponseData<>();
		service.delete(body);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}
	@RequestMapping(value = "", produces = { "application/json" }, consumes = {
	"application/json" }, method = RequestMethod.PUT)
	public ResponseEntity<ResponseData<GradeDto>> updateGrade(@Valid @RequestBody GradeDto body){
		ResponseData<GradeDto> response = new ResponseData<>();
		response.setBody(service.update(body));
		response.setLinks(null);
	return new ResponseEntity<>(response, HttpStatus.OK);
	}
}


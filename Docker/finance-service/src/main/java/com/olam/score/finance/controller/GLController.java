package com.olam.score.finance.controller;

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
import com.olam.score.finance.dto.ExternalSystemRefDto;
import com.olam.score.finance.dto.GLDto;
import com.olam.score.finance.dto.GlTypeRef;
import com.olam.score.finance.service.GLService;

@CrossOrigin //TODO: need to remove cross origin in future
@RestController
//@Mapping(featureName="GL",featureId="2") //TODO : feature name in database should be same
@RequestMapping("/finance/glservice/gl")
//@Api(value = "GL", description = "Operations pertaining to GL in OLAM CTRM")

public class GLController {

	@Autowired
	GLService service;
	/*@Autowired
	UtilService utilService;*/


	@RequestMapping(value = "", produces = { "application/json" }, consumes = { "application/json" }, method = RequestMethod.POST)
	public ResponseEntity<ResponseData<GLDto>> addGL(@Valid @RequestBody GLDto body)//@Valid @QueryParam("action") String action,  @Valid @RequestBody GLDto body)
	{
		ResponseData<GLDto> response = new ResponseData<>();
		/*String action = body.getStatusName();
		if (action.equalsIgnoreCase(Constants.DRAFT)) {
			body.setStatusName(Constants.DRAFT);
		} else if (action.equalsIgnoreCase(Constants.SAVE)) {
			body.setStatusName(Constants.ACTIVE);
		}*/

		response.setBody(service.create(body));
		response.setLinks(null);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@RequestMapping(value= "/{glId}", method = RequestMethod.GET)
	public ResponseEntity<ResponseData<GLDto>> getGLById(@PathVariable("glId") Integer glId) {

		ResponseData<GLDto> response = new ResponseData<>();
		//TODO: User Authorization needs to happen
		response.setBody(service.readById(glId));
		response.setLinks(null);//links
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	@RequestMapping(value = "", method = RequestMethod.DELETE)
	public ResponseEntity<ResponseData<GLDto>> deleteGL(@RequestBody List<Map<String, Integer>> body) {
		ResponseData<GLDto> response = new ResponseData<>();
		service.deleteGL(body);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}


	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<ResponseData<List<GLDto>>> getGL(){
		ResponseData<List<GLDto>> response = new ResponseData<>();
		//TODO: User Authorization needs to happen
		//ViewDto view=service.getViewDetails(this.getClass());
		response.setBody(service.readAll());
		response.setLinks(null);//TOOD: Need to get links for the entity
		return new ResponseEntity<>(response, HttpStatus.OK);
	}


	@RequestMapping(value = "", produces = { "application/json" }, consumes = {
	"application/json" }, method = RequestMethod.PUT)
	public ResponseEntity<ResponseData<List<GLDto>>> updateGL(@Valid @RequestBody List<GLDto> body) {
		ResponseData<List<GLDto>> response = new ResponseData<>();
		response.setBody(service.updateGL(body));
		response.setLinks(null);// TOOD: Need to get links for the entity
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@RequestMapping(value = "/gltype", method = RequestMethod.GET)
	public ResponseEntity<ResponseData<List<GlTypeRef>>> getGLType()//@Valid @QueryParam("action") String action,  @Valid @RequestBody GLDto body)
	{
		ResponseData<List<GlTypeRef>> response = new ResponseData<>();		
		response.setBody(service.getGlTypeRef());
		response.setLinks(null);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/externalsystemref", method = RequestMethod.GET,produces = { "application/json" })
	public ResponseEntity<ResponseData<List<ExternalSystemRefDto>>> getExternalSystemRef()//@Valid @QueryParam("action") String action,  @Valid @RequestBody GLDto body)
	{
		ResponseData<List<ExternalSystemRefDto>> response = new ResponseData<>();		
		response.setBody(service.getExternalSystemRef());
		response.setLinks(null);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	@RequestMapping(value = "/parentGl", method = RequestMethod.GET)
	public ResponseEntity<ResponseData<List<GLDto>>> getParentGl()//@Valid @QueryParam("action") String action,  @Valid @RequestBody GLDto body)
	{
		ResponseData<List<GLDto>> response = new ResponseData<>();		
		response.setBody(service.getParentGl());
		response.setLinks(null);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
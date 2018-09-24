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
import org.springframework.web.client.HttpStatusCodeException;

import com.olam.score.common.dto.ResponseData;
import com.olam.score.common.dto.ViewDto;
import com.olam.score.common.util.Mapping;
import com.olam.score.product.dto.MasterOriginDto;
import com.olam.score.product.dto.MasterOutturnDto;
import com.olam.score.product.service.OriginService;

@CrossOrigin // TODO: need to remove cross origin in future
@RestController
@Mapping(featureName = "ORIGIN", featureId = "11")
//@RequestMapping("/originservice/origin")
@RequestMapping("/product/originservice/origin")
public class OriginController {

	@Autowired
	private OriginService service;
	
	
	/*@ApiOperation(value = "View a list of available Unit Of Measures", response = UomDto.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "User is not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource user is trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource user is trying to reach is not found") })*/
	@RequestMapping(method = RequestMethod.GET, produces = { "application/json" })
	public ResponseEntity<ResponseData<List<MasterOriginDto>>> getOutturnRatio() {
		ResponseData<List<MasterOriginDto>> response = new ResponseData<>();
		//List<Link<?>> links = utilService.getFeatures(this.getClass());
		ViewDto view = service.getViewDetails(this.getClass());
		response.setBody(service.readAll(view));
		response.setLinks(null);//todo: set links
		response.setView(view);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/*@RequestMapping(method = RequestMethod.GET, produces = { "application/json" })
	public ResponseEntity<ResponseData> getOrigin() {
		ResponseData response = new ResponseData();
		// TODO: User Authorization needs to happen
		try {
			response.setBody(service.readAll());
			// response.setLabels(null);//TODO: Need to get Labels for the
			// entity
			response.setLinks(null);// TODO: Need to get links for the entity
		} catch (Exception e) {

			// TODO: proper Error Handling needs to be done
			return new ResponseEntity<ResponseData>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<ResponseData>(response, HttpStatus.OK);
	}*/

	@RequestMapping(value = "/{originId}", method = RequestMethod.GET)
	public ResponseEntity<ResponseData<MasterOriginDto>> getUOMById(@PathVariable("originId") Integer originId) {

		ResponseData<MasterOriginDto> response = new ResponseData<>();
		// List<Link<?>> links = utilService.getFeatures(this.getClass());

		//response.setBody(service.readById(originId));
		response.setLinks(null);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = { "application/json" }, produces = { "application/json" })
	public ResponseEntity<ResponseData<MasterOriginDto>> addOrigin(@QueryParam("action") String action,
			@Valid @RequestBody MasterOriginDto reqBody) {
		ResponseData<MasterOriginDto> response = new ResponseData<MasterOriginDto>();
		// TODO: User Authorization needs to happen

		try {
			//response.setBody(service.create(reqBody, action));
			response.setLinks(null); // TODO: Need to get links for the entity
		} catch (HttpStatusCodeException e) {

		}

		return new ResponseEntity<ResponseData<MasterOriginDto>>(response, HttpStatus.OK);

	}

	@RequestMapping(produces = { "application/json" }, consumes = { "application/json" }, method = RequestMethod.PUT)
	public ResponseEntity<ResponseData<List<MasterOriginDto>>> updateUOM(@Valid @RequestBody List<MasterOriginDto> body) {
		ResponseData<List<MasterOriginDto>> response = new ResponseData<>();
		//List<Link<?>> links = utilService.getFeatures(this.getClass());
		//response.setBody(service.update(body));
		response.setLinks(null);// TOOD: Need to get links for the entity
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.DELETE)
	public ResponseEntity<ResponseData<MasterOriginDto>> deleteOrigin(@RequestBody List<Map<String, Integer>> body) {
		ResponseData<MasterOriginDto> response = new ResponseData<>();
		try {
			//service.deleteOrigin(body);
		} catch (HttpStatusCodeException e) {

		}
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

}

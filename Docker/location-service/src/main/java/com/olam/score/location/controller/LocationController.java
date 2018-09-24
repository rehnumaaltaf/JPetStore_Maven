package com.olam.score.location.controller;

import java.util.List;

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

import com.olam.score.common.dto.Link;
import com.olam.score.location.dto.LocationDTO;
import com.olam.score.common.dto.ResponseData;
import com.olam.score.location.service.LocationService;
import com.olam.score.common.util.Mapping;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
*
* This class act as a controller for the RESTFULL services . will take the
* request from the user and give the response back to user by connecting to
* other all dependent classes
*
* @author Manoj Kumar  
* @version 2.0.0, 26 September 2017
*/

@CrossOrigin
@RestController
@Mapping(featureName = "LOCATION" , featureId = "4")
@RequestMapping(value = "location/locationservice/location")
@Api(value = "LOCATION", description = "Operations pertaining to Loaction in OLAM CTRM")
public class LocationController {

	@Autowired
	private LocationService service;

	@ApiOperation(value = "Create a Location Master", response = LocationDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "User is not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource user is trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource user is trying to reach is not found") })
	@RequestMapping(produces = { "application/json" }, consumes = { "application/json" }, method = RequestMethod.POST)
	public ResponseEntity<ResponseData<String>> addLocation(@Valid @RequestBody LocationDTO body) {
		ResponseData<String> rsp = new ResponseData<>();
		rsp.setBody(service.create(body));
		return new ResponseEntity<>(rsp, HttpStatus.OK);

	}

	@ApiOperation(value = "View a Location Master", response = LocationDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "User is not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource user is trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource user is trying to reach is not found") })
	@RequestMapping(value = "", method = RequestMethod.GET, produces = { "application/json" })
	public ResponseEntity<ResponseData<List<LocationDTO>>> getLocation() {
		ResponseData<List<LocationDTO>> rsp = new ResponseData<>();
		// List<Link<?>> links = utilService.getFeatures(this.getClass());
		List<Link<?>> links = null;
		rsp.setBody(service.readAll());
		rsp.setLinks(links);

		return new ResponseEntity<>(rsp, HttpStatus.OK);
	}

	@ApiOperation(value = "Update a Location Master", response = LocationDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "User is not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource user is trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource user is trying to reach is not found") })
	@RequestMapping(produces = { "application/json" }, consumes = { "application/json" }, method = RequestMethod.PUT)
	public ResponseEntity<ResponseData<String>> updateLocation(@Valid @RequestBody LocationDTO body) {
		ResponseData<String> rsp = new ResponseData<>();
		rsp.setBody(service.update(body));
		return new ResponseEntity<>(rsp, HttpStatus.OK);

	}

	@ApiOperation(value = "Delete location based on the id in Location Master", response = LocationDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "User is not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource user is trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource user is trying to reach is not found") })
	@RequestMapping(value = "/{locationId}", method = RequestMethod.DELETE)
	public ResponseEntity<ResponseData<String>> deleteLocation(@PathVariable("locationId") Integer locationId) {

		ResponseData<String> rsp = new ResponseData<>();
		rsp.setBody(service.delete(locationId));
		return new ResponseEntity<>(rsp, HttpStatus.OK);
	}
    
	@ApiOperation(value = "View Individual List in Location Master ", response = LocationDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "User is not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource user is trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource user is trying to reach is not found") })
	@RequestMapping(value = "/{locationId}", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<ResponseData<List<LocationDTO>>> getLocationDetailsById(
			@PathVariable("locationId") Integer locationId) {

		ResponseData<List<LocationDTO>> rsp = new ResponseData<>();
		rsp.setBody(service.getLocationById(locationId));
		rsp.setLinks(null);
		return new ResponseEntity<ResponseData<List<LocationDTO>>>(rsp, HttpStatus.OK);
	}

}

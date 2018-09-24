package com.olam.score.authorizationconfig.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.olam.score.authorizationconfig.dto.PermissionGroupDto;
import com.olam.score.authorizationconfig.dto.TraderDto;
import com.olam.score.authorizationconfig.service.TraderService;
import com.olam.score.common.dto.ResponseData;

@CrossOrigin
@RestController
@RequestMapping("/authorization/traderservice/trader")
public class TraderController {

	@Autowired
	TraderService service;

	@RequestMapping(value = "/{traderId}", method = RequestMethod.GET, produces = { "application/json" })
	public ResponseEntity<ResponseData<TraderDto>> getPermissionGroupMappingById(
			@PathVariable("traderId") Integer traderId) {

		ResponseData<TraderDto> response = new ResponseData<>();
		response.setBody(service.readById(traderId));
		response.setLinks(null);// TOOD: Need to get links for the entity
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@RequestMapping(value = "", method = RequestMethod.GET, produces = { "application/json" })
	public ResponseEntity<ResponseData<List<TraderDto>>> getTraders() {

		ResponseData<List<TraderDto>> response = new ResponseData<>();
		response.setBody(service.readAll());
		response.setLinks(null);// TOOD: Need to get links for the entity
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}

package com.olam.score.party.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.olam.score.common.dto.ResponseData;
import com.olam.score.party.dto.PortfolioDto;
import com.olam.score.party.service.PortfolioService;

/**
 * @author Mohit_Agrawal08
 *
 */
@CrossOrigin // TODO: need to remove cross origin in future
@RestController
@RequestMapping("party/portfolioservice/portfolio")
public class PortfolioController {

	@Autowired
	PortfolioService service;

	@RequestMapping(value = "/{portfolioId}", method = RequestMethod.GET, produces = { "application/json" })
	public ResponseEntity<ResponseData<PortfolioDto>> getPortfolio(@PathVariable("portfolioId") Integer portfolioId) {

		ResponseData<PortfolioDto> response = new ResponseData<>();
		// TODO: User Authorization needs to happen
		response.setBody(service.readById(portfolioId));
		response.setLinks(null);// TOOD: Need to get links for the entity
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@RequestMapping(value = "", method = RequestMethod.GET, produces = { "application/json" })
	public ResponseEntity<ResponseData<List<PortfolioDto>>> getPortfolios() {

		ResponseData<List<PortfolioDto>> response = new ResponseData<>();
		// TODO: User Authorization needs to happen
		response.setBody(service.readAll());
		response.setLinks(null);// TOOD: Need to get links for the entity
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

}

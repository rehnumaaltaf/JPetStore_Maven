package com.olam.score.reference.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.olam.score.common.dto.ResponseData;
import com.olam.score.reference.dto.MasterLanguageDTO;
import com.olam.score.reference.service.MasterLanguageService;

@CrossOrigin
@RestController
@RequestMapping(value = "reference/MasterLanguageService/language", produces= MediaType.APPLICATION_JSON_VALUE)
public class MasterLanguageController {
	
	@Autowired
	private MasterLanguageService service;
	
	@GetMapping
	public @ResponseBody ResponseEntity<ResponseData<List<MasterLanguageDTO>>> getAllLanguages(){
		ResponseData<List<MasterLanguageDTO>> response = new ResponseData<>();
		response.setBody(service.readAll());
		response.setLinks(null);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{languageId}")
	public @ResponseBody ResponseEntity<ResponseData<MasterLanguageDTO>> readByLanguageId(
			@PathVariable("languageId") Integer languageId)
	{
		ResponseData<MasterLanguageDTO> response = new ResponseData<>();
		response.setBody(service.readById(languageId));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}

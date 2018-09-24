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
import com.olam.score.reference.dto.DocumentTypeRefDTO;
import com.olam.score.reference.service.DocumentTypeService;

@CrossOrigin
@RestController
@RequestMapping(value = "reference/DocumentTypeService/docType", produces= MediaType.APPLICATION_JSON_VALUE)
public class DocumentTypeRefController {
	
	@Autowired
	private DocumentTypeService service;
	
	@GetMapping
	public @ResponseBody ResponseEntity<ResponseData<List<DocumentTypeRefDTO>>> getDocumentTypeRefs(){
		
		ResponseData<List<DocumentTypeRefDTO>> response = new ResponseData<>();
		
		response.setBody(service.readAll());
		response.setLinks(null);
		return new ResponseEntity<>(response, HttpStatus.OK);		
	}
	
	@GetMapping(value = "/{documentTypeId}")
	public @ResponseBody ResponseEntity<ResponseData<DocumentTypeRefDTO>> readByDocumentTypeId(
			@PathVariable("documentTypeId") Integer documentTypeId)
	{
		ResponseData<DocumentTypeRefDTO> response = new ResponseData<>();
		response.setBody(service.readById(documentTypeId));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}

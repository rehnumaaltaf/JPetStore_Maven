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
import com.olam.score.reference.dto.DocumentNameDTO;
import com.olam.score.reference.service.DocumentNameService;

@CrossOrigin // TODO: need to remove cross origin in future
@RestController
@RequestMapping(value="reference/DocumentNameService/docName", produces= MediaType.APPLICATION_JSON_VALUE)
public class DocumentNameController {

	@Autowired
	private DocumentNameService service;

	@GetMapping
	public @ResponseBody ResponseEntity<ResponseData<List<DocumentNameDTO>>> getDocumentNames() {
		ResponseData<List<DocumentNameDTO>> response = new ResponseData<>();
		// TODO: User Authorization needs to happen
		response.setBody(service.readAll());
		response.setLinks(null);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{documentId}")
	public @ResponseBody ResponseEntity<ResponseData<DocumentNameDTO>> readByDocId(
			@PathVariable("documentId") Integer documentId)
	{
		ResponseData<DocumentNameDTO> response = new ResponseData<>();
		response.setBody(service.readById(documentId));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}

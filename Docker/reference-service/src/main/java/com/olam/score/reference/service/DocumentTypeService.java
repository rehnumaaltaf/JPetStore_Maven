/**
 * 
 */
package com.olam.score.reference.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.olam.score.common.util.WebServiceCallUtil;
import com.olam.score.reference.domain.MasterDocumentTypeRef;
import com.olam.score.reference.dto.DocumentTypeRefDTO;
import com.olam.score.reference.repository.DocumentTypeRepository;

@Service
public class DocumentTypeService {

	@Autowired
	private DocumentTypeRepository repository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private WebServiceCallUtil webServiceCall;

	@Transactional
	public List<DocumentTypeRefDTO> readAll()
	{
		List<DocumentTypeRefDTO> typeList = new ArrayList<>();
		repository.findAll().forEach(data -> {
			typeList.add(modelMapper.map(data, DocumentTypeRefDTO.class));
		});
		
		return typeList;
	}
	
	@Transactional
	public DocumentTypeRefDTO readById(Integer documentTypeId)
	{
		MasterDocumentTypeRef docType = repository.findOne(documentTypeId);
		Map<Integer, String> statusMap = webServiceCall.getAllStatus();
		DocumentTypeRefDTO docTypeDto = modelMapper.map(docType, DocumentTypeRefDTO.class);
		docTypeDto.setStatusName(statusMap.get(docTypeDto.getFkStatusId()));
		return docTypeDto;		
	}
}

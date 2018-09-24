package com.olam.score.reference.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.olam.score.common.util.WebServiceCallUtil;
import com.olam.score.reference.domain.MasterDocumentRef;
import com.olam.score.reference.dto.DocumentNameDTO;
import com.olam.score.reference.repository.DocumentNameRepository;

@Service
public class DocumentNameService {

	@Autowired
	private DocumentNameRepository repository;

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private WebServiceCallUtil webServiceCall;

	@Transactional
	public List<DocumentNameDTO> readAll() {

		List<DocumentNameDTO> docList = new ArrayList<DocumentNameDTO>();
		repository.findAll().forEach(data -> {
			docList.add(modelMapper.map(data, DocumentNameDTO.class));
		});

		return docList;
	}

	@Transactional
	public DocumentNameDTO readById(Integer documentId) {
		
		MasterDocumentRef docList = repository.findOne(documentId);
		Map<Integer, String> statusMap = webServiceCall.getAllStatus();
		DocumentNameDTO docDto = modelMapper.map(docList, DocumentNameDTO.class);
		docDto.setStatusName(statusMap.get(docDto.getFkStatusId()));
		return docDto;		
		
	}

}

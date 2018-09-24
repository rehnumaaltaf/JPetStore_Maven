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
import com.olam.score.reference.domain.MasterLanguage;
import com.olam.score.reference.dto.MasterLanguageDTO;
import com.olam.score.reference.repository.LanguageRefRepository;


@Service
public class MasterLanguageService {

	@Autowired
	private LanguageRefRepository repository;

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private WebServiceCallUtil webServiceCall;

	@Transactional
	public List<MasterLanguageDTO> readAll(){

		List<MasterLanguageDTO> langList = new ArrayList<MasterLanguageDTO>();
		repository.findAll().forEach(data -> {
			langList.add(modelMapper.map(data, MasterLanguageDTO.class));
		});

		return langList;
	}
	
	@Transactional
	public MasterLanguageDTO readById(Integer languageId)
	{
		MasterLanguage langResult = repository.findOne(languageId);
		Map<Integer, String> statusMap = webServiceCall.getAllStatus();
		MasterLanguageDTO langDto = modelMapper.map(langResult, MasterLanguageDTO.class);
		langDto.setStatusName(statusMap.get(langResult.getFkStatusId()));
		return langDto;		
	}
}

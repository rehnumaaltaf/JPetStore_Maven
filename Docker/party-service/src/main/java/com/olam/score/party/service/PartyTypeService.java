package com.olam.score.party.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.olam.score.party.dto.PartyTypeDTO;
import com.olam.score.party.repository.PartyTypeRepository;

@Service
public class PartyTypeService {
	
	@Autowired
	PartyTypeRepository repository;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Transactional
	public List<PartyTypeDTO> fetchPartyType()
	{
		List<PartyTypeDTO> typeList = new ArrayList<PartyTypeDTO>();
		repository.findAll().forEach(data -> {
			typeList.add(modelMapper.map(data, PartyTypeDTO.class));
		});
		return typeList;
	}

}

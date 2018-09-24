package com.olam.score.party.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.olam.score.party.dto.PartyClassificationDTO;
import com.olam.score.party.repository.PartyClassificationRepository;


@Service
public class PartyClassificationService {
	
	@Autowired
	private PartyClassificationRepository repository;
	
	@Autowired
	private ModelMapper modelMapper;
	

	@Transactional
	public List<PartyClassificationDTO> fetchClassificationRecords(){
		
		List<PartyClassificationDTO> newList = new ArrayList<>();
		repository.findAll().forEach(data -> {
			newList.add(modelMapper.map(data, PartyClassificationDTO.class));
		});
		return newList;
	}


}

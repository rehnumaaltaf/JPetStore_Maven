package com.olam.score.party.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.olam.score.party.dto.AddressDto;
import com.olam.score.party.repository.PartyAddressRepository;


@Service
public class PartyAddressService {
	
	@Autowired
	private PartyAddressRepository repository;
	
	@Autowired
	private ModelMapper modelMapper;


	@Transactional
	public List<AddressDto> fetchAddress(){
		
		List<AddressDto> addrList = new ArrayList<AddressDto>();		
		repository.findAll().forEach(data -> {
			addrList.add(modelMapper.map(data, AddressDto.class));
		});
		
		return addrList;
	}

}

package com.olam.score.party.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.olam.score.party.dto.PartyDto;
import com.olam.score.party.repository.MasterPartyRepository;

@Service
public class MasterPartyService {

	@Autowired
	private MasterPartyRepository repository;

	@Autowired
	private ModelMapper modelMapper;
	
	@Transactional
	public List<PartyDto> fetchPartyCode() {

		List<PartyDto> partyCodeList = new ArrayList<>();

		repository.findAll().stream().filter(flag -> "Y".equals(flag.getPartyInternalFlag())).forEach(party -> {
			partyCodeList.add(modelMapper.map(party, PartyDto.class));
		});
		return partyCodeList;
	}

	@Transactional
	public List<PartyDto> fetchGroupPartyName(String internalFlag) {
		List<PartyDto> groupList = new ArrayList<>();
		repository.findAll().stream().filter(flag -> Optional.of(flag.getPartyInternalFlag().equals(internalFlag)).get())
				.forEach(party -> {
					groupList.add(modelMapper.map(party, PartyDto.class));
				});
		return groupList;
	}

}

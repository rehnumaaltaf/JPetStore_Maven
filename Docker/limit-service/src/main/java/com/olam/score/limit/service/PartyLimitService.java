package com.olam.score.limit.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.olam.score.limit.domain.MasterCounterPartyLimit;
import com.olam.score.limit.domain.MasterLimitAlertLevel;
import com.olam.score.limit.dto.PartyLimitDto;
import com.olam.score.limit.repository.PartyLimitRepository;
@Service
public class PartyLimitService {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PartyLimitRepository repository;
	
	@Transactional
	public PartyLimitDto create(PartyLimitDto partyLimitDetails) {
		try {
		MasterCounterPartyLimit partyLimits = modelMapper.map(partyLimitDetails,MasterCounterPartyLimit.class);
		if(partyLimitDetails.getFkLimitAlertLevelKey()!=null) {
			MasterLimitAlertLevel limitAlertLevel = new MasterLimitAlertLevel(partyLimitDetails.getFkLimitAlertLevelKey());
			partyLimits.setFkLimitAlertLevelKey(limitAlertLevel);
		}
		repository.saveAndFlush(partyLimits);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return partyLimitDetails;
	}
	
	@Transactional
	public List<PartyLimitDto> readByCounterPartyId(Integer counterPartyId) {
		
		List<MasterCounterPartyLimit> partyLimits = repository.findByCounterPartyId(counterPartyId);
		List<PartyLimitDto> partyLimitDetails = new ArrayList<>();
				partyLimits.stream().forEach(data -> {partyLimitDetails.add(
							modelMapper.map(data, PartyLimitDto.class));});
		return partyLimitDetails;
	}

}

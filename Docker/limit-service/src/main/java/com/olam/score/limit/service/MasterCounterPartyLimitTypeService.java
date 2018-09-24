package com.olam.score.limit.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.olam.score.common.util.WebServiceCallUtil;
import com.olam.score.limit.domain.MasterCounterPartyLimitType;
import com.olam.score.limit.dto.MasterCounterPartyLimitTypeDTO;
import com.olam.score.limit.repository.MasterCounterPartyLimitTypeRepository;

@Service
public class MasterCounterPartyLimitTypeService {

	@Autowired
	private MasterCounterPartyLimitTypeRepository repository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private WebServiceCallUtil webServiceCall;

	@Transactional
	public List<MasterCounterPartyLimitTypeDTO> readAll()
	{
		List<MasterCounterPartyLimitTypeDTO> newList = new ArrayList<MasterCounterPartyLimitTypeDTO>();

		repository.findAll().forEach(data -> {
			newList.add(modelMapper.map(data, MasterCounterPartyLimitTypeDTO.class));
		});

		return newList;
	}

	@Transactional
	public MasterCounterPartyLimitTypeDTO readById(Integer counterPartyLimitId)
	{
		MasterCounterPartyLimitType partyLimit = repository.findOne(counterPartyLimitId);
		Map<Integer, String> statusMap = webServiceCall.getAllStatus();

		MasterCounterPartyLimitTypeDTO partyDto = modelMapper.map(partyLimit, MasterCounterPartyLimitTypeDTO.class);
		partyDto.setStatusName(statusMap.get(partyLimit.getFkStatusId()));

		return partyDto;
	}

}

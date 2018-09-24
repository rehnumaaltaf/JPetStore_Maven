package com.olam.score.location.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.olam.score.common.util.WebServiceCallUtil;
import com.olam.score.location.domain.MasterLocation;
import com.olam.score.location.dto.MasterLocationDTO;
import com.olam.score.location.repository.MasterLocationRepository;

@Service
public class WarehouseLocationService {

	@Autowired
	private MasterLocationRepository repository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private WebServiceCallUtil webServiceCall;

	@Transactional
	public List<MasterLocationDTO> fetchLocationDetails()
	{
		List<MasterLocationDTO> locList = new ArrayList<>();
		repository.findAll().forEach(data -> {
			locList.add(modelMapper.map(data, MasterLocationDTO.class));
		});

		return locList;
	}

	@Transactional
	public MasterLocationDTO readById(Integer pkLocId)
	{
		MasterLocation locationResults = repository.findOne(pkLocId);
		Map<Integer, String> statusMap = webServiceCall.getAllStatus();
		MasterLocationDTO locDto = modelMapper.map(locationResults, MasterLocationDTO.class);
		locDto.setStatusName(statusMap.get(locDto.getFkStatusId()));
		return locDto;
	}
}

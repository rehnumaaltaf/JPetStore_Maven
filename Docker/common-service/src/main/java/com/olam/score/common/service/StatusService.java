package com.olam.score.common.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.olam.score.common.dto.StatusDto;
import com.olam.score.common.domain.MasterStatus;
import com.olam.score.common.repository.Statusrepository;

@Service
public class StatusService {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private Statusrepository repository;
	
	public List<StatusDto> readAll() {
		List<StatusDto> newList = new ArrayList<>();
		List<String> sortColumns=new ArrayList<>();
		sortColumns.add("createdDate");
		sortColumns.add("modifiedDate");
		Sort sort = new Sort(Direction.DESC,sortColumns);
		List<MasterStatus> oldList = repository.findAll(sort);

		for (MasterStatus masterStatus : oldList) {
			StatusDto uomDto;
			uomDto = modelMapper.map(masterStatus, StatusDto.class);
			newList.add(uomDto);
		}

		return newList;
	}
	
	@Transactional
	public String returnNameById(int id)
	{
		try {
		return repository.findOne(id).getStatusName();
		}catch(Exception ex)
		{
			return null;
		}
	}
}

/**
 * 
 */
package com.olam.score.limit.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.olam.score.common.util.WebServiceCallUtil;
import com.olam.score.limit.domain.MasterLimitAlertLevel;
import com.olam.score.limit.dto.MasterLimitAlertLevelDTO;
import com.olam.score.limit.repository.MasterLimitAlertLevelRepository;


@Service
public class MasterLimitAlertLevelService {

	@Autowired
	private MasterLimitAlertLevelRepository repository;

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private WebServiceCallUtil webServiceCall;

		@Transactional
	public List<MasterLimitAlertLevelDTO> readAll()
	{
		List<MasterLimitAlertLevelDTO> alertList = new ArrayList<>();
		repository.findAll().forEach(data -> {
			alertList.add(modelMapper.map(data, MasterLimitAlertLevelDTO.class));
		});

		return alertList;
	}
	
	@Transactional
	public MasterLimitAlertLevelDTO readById(Integer limitAlertId)
	{
		MasterLimitAlertLevel alertList = repository.findOne(limitAlertId);
		Map<Integer, String> statusMap = webServiceCall.getAllStatus();
		
		MasterLimitAlertLevelDTO alertDto = modelMapper.map(alertList, MasterLimitAlertLevelDTO.class);
		alertDto.setStatusName(statusMap.get(alertDto.getFkStatusId()));
		
		return alertDto;
		
	}

}

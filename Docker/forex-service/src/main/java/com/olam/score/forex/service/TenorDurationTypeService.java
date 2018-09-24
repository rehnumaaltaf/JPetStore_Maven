package com.olam.score.forex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.olam.score.forex.domain.MasterTenorDurationType;
import com.olam.score.forex.repository.TenorDurationTypeRepository;
import com.olam.score.common.constants.Constants;

@Service
public class TenorDurationTypeService {
	@Autowired
	private TenorDurationTypeRepository repository;
	
	@Transactional
	public MasterTenorDurationType returnMasterTenorDurationTypeById(int id)
	{
		return repository.getByPkTenorDurationTypeId(id);
	}
	@Transactional
	public List<MasterTenorDurationType> returnAllMasterTenorDurationType()
	{
		return repository.returnAll(Constants.ACTIVE_STATUS_ID);
	}
}

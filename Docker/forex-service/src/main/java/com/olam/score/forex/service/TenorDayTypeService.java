package com.olam.score.forex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.olam.score.forex.domain.MasterTenorDayType;
import com.olam.score.forex.repository.TenorDayTypeRepository;
import com.olam.score.common.constants.Constants;

@Service
public class TenorDayTypeService {
	@Autowired
	private TenorDayTypeRepository repository;
	
	@Transactional
	public MasterTenorDayType returnTenorDayTypeById(int id)
	{
		return repository.getByPkTenorDayTypeId(id);
	}
	@Transactional
	public List<MasterTenorDayType> returnAllMasterTenorDayType()
	{
		return repository.returnAll(Constants.ACTIVE_STATUS_ID);
	}
	
}

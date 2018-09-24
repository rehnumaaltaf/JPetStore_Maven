package com.olam.score.forex.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.olam.score.forex.domain.MasterTenorType;
import com.olam.score.forex.repository.TenorTypeRepository;
import com.olam.score.common.constants.Constants;

@Service
public class TenorTypeService {
	@Autowired
	private TenorTypeRepository tenorTypeRepository;
	
	@Transactional
	public MasterTenorType returnTenorTypeById(int id)
	{
		return tenorTypeRepository.getByPkTenorTypeId(id);
	}
	@Transactional
	public List<MasterTenorType> returnAllTenorType()
	{
		return tenorTypeRepository.returnAll(Constants.ACTIVE_STATUS_ID);
	}
}

package com.olam.score.party.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.olam.score.party.domain.MasterUnit;
import com.olam.score.party.dto.ProfitCenterDto;
import com.olam.score.party.repository.MasterUnitRepository;

/**
 *
 * This service class having the methods to connect to dataBase using hibernate.
 *
 *
 * @author prabhakar
 * @version 1.00, 10 july 2017
 */

@Service
public class ProfitCenterService {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private MasterUnitRepository masterUnitRepository;

	@Transactional

	public List<ProfitCenterDto> readAll() {
		List<MasterUnit> masterUnit = masterUnitRepository.findAll();
		List<ProfitCenterDto> profitCenterList = new ArrayList<>();
		for (MasterUnit listunit : masterUnit) {
			ProfitCenterDto profitCenterDTO = new ProfitCenterDto();
			profitCenterDTO.setUnitId(listunit.getPkUnitId());
			profitCenterDTO.setUnitName(listunit.getUnitName());
			profitCenterDTO.setUnitCode(listunit.getUnitCode());
			profitCenterList.add(profitCenterDTO);
		}
		return profitCenterList;
	}

	@Transactional
	public ProfitCenterDto readAllByuintId(Integer id) {
		MasterUnit masterUnit = masterUnitRepository.findOne(id);
		ProfitCenterDto profitCenterDTO = new ProfitCenterDto();
		profitCenterDTO.setUnitId(masterUnit.getPkUnitId());
		profitCenterDTO.setUnitName(masterUnit.getUnitName());
		profitCenterDTO.setUnitCode(masterUnit.getUnitCode());
		return profitCenterDTO;
	}

}

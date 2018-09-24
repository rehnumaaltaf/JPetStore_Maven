package com.olam.score.forex.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.olam.score.forex.domain.MasterForex;
import com.olam.score.forex.dto.ForexCurrencyDto;
import com.olam.score.forex.repository.ForexRepository;

@Service
public class ForexCurrencyService {
	
	@Autowired
    private ForexRepository repository;

	@Transactional
	public List<ForexCurrencyDto> readAll() {
		// ForexCurrency are hard coded as Security module is not implemented.
		// Need to
		// replace once security module is done.
		List<ForexCurrencyDto> newList = new ArrayList<>();
		List<MasterForex> oldList = repository.findAll();
		for (MasterForex forex : oldList) {
			ForexCurrencyDto dto;
			dto = convertToTraderDto(forex);
			newList.add(dto);
		}

		return newList;
	}

	@Transactional
	public ForexCurrencyDto readById(Integer forexCurrencyId) {
		// ForexCurrencys are hard coded as module is not implemented.
		// Need to implement.
		MasterForex forex = repository.findOne(forexCurrencyId);
		return convertToTraderDto(forex);
	}
	
	private ForexCurrencyDto convertToTraderDto(MasterForex forex) {
		ForexCurrencyDto forexCurrencyDto = new ForexCurrencyDto();
		forexCurrencyDto.setForexcurrencyId(forex.getPkForexId());
		forexCurrencyDto.setForexcurrencyCode(forex.getForexCode());
		forexCurrencyDto.setForexcurrencyName(forex.getForexName());
		return forexCurrencyDto;
	}

}

package com.olam.score.authorizationconfig.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.olam.score.authorizationconfig.domain.AuthAppUser;
import com.olam.score.authorizationconfig.dto.TraderDto;
import com.olam.score.authorizationconfig.repository.TraderRepository;

@Service
public class TraderService {

	@Autowired
	private TraderRepository repository;

	@Transactional
	public TraderDto readById(Integer AuthAppUser) {
		// Traders are hard coded as Security module is not implemented. Need to
		// replace once security module is done.
		AuthAppUser trader = repository.findOne(AuthAppUser);
		return convertToTraderDto(trader);
	}

	@Transactional
	public List<TraderDto> readAll() {
		// Traders are hard coded as Security module is not implemented. Need to
		// replace once security module is done.
		List<TraderDto> newList = new ArrayList<>();
		List<AuthAppUser> oldList = repository.findAll();
		for (AuthAppUser trader : oldList) {
			TraderDto dto;
			dto = convertToTraderDto(trader);
			newList.add(dto);
		}

		return newList;
	}

	private TraderDto convertToTraderDto(AuthAppUser trader) {
		TraderDto traderDto = new TraderDto();
		traderDto.setUserId(trader.getPkAppUserId());
		traderDto.setUserCode(trader.getAppUserCode());
		traderDto.setUserName(trader.getAppUserName());
		return traderDto;
	}

}

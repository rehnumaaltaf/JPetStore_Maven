package com.olam.score.party.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.olam.score.party.domain.MasterPortfolio;
import com.olam.score.party.dto.PortfolioDto;
import com.olam.score.party.repository.PortfolioRepository;

@Service
public class PortfolioService {

	@Autowired
	private PortfolioRepository repository;

	@Autowired
	private ModelMapper modelMapper;

	@Transactional
	public PortfolioDto readById(Integer portfolioId) {

		MasterPortfolio findOne = repository.findOne(portfolioId);
		return modelMapper.map(findOne, PortfolioDto.class);
	}

	@Transactional
	public List<PortfolioDto> readAll() {

		List<PortfolioDto> newList = new ArrayList<>();
		List<MasterPortfolio> allPortfolio = repository.findAll();
		for (MasterPortfolio portfolio : allPortfolio) {
			PortfolioDto pgmd = modelMapper.map(portfolio, PortfolioDto.class);
			newList.add(pgmd);
		}
		return newList;
	}
}

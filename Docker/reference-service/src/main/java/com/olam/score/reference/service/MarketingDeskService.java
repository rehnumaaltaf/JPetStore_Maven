package com.olam.score.reference.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.olam.score.common.util.WebServiceCallUtil;
import com.olam.score.reference.domain.MasterMarketingDesk;
import com.olam.score.reference.dto.MarketingDeskDTO;
import com.olam.score.reference.repository.MarketingDeskRepository;

@Service
public class MarketingDeskService {
	
	@Autowired
	private MarketingDeskRepository repository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private WebServiceCallUtil webServiceCall;
		
	@Transactional
	public List<MarketingDeskDTO> readAll() {

		List<MarketingDeskDTO> marketList = new ArrayList<MarketingDeskDTO>();
		repository.findAll().forEach(data -> {
			marketList.add(modelMapper.map(data, MarketingDeskDTO.class));
		});
		return marketList;
	}

	@Transactional
	public MarketingDeskDTO readById(Integer deskId) {
		
		MasterMarketingDesk deskList = repository.findOne(deskId);
		Map<Integer, String> statusMap = webServiceCall.getAllStatus();
		MarketingDeskDTO deskDto = modelMapper.map(deskList, MarketingDeskDTO.class);
		deskDto.setStatusName(statusMap.get(deskDto.getFkStatusId()));
		return deskDto;	
	}

}

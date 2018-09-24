package com.olam.score.marketdata.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.olam.score.marketdata.domain.MasterExchange;
import com.olam.score.marketdata.domain.MasterXchngProductAssociation;
import com.olam.score.marketdata.dto.ExchangeDetailsDto;
import com.olam.score.marketdata.repository.ExchangeDetailsRepository;

@Service
public class ExchangeDetailsService {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private ExchangeDetailsRepository repository;
	
	@Transactional
	public ExchangeDetailsDto create(ExchangeDetailsDto exchangeDetails) {
		MasterExchange exchange = new MasterExchange();
		exchange.setFkParentPartyId(exchangeDetails.getPartyNumber());
		List<MasterXchngProductAssociation> xProdAssocList = new ArrayList<>();
		MasterXchngProductAssociation xProdAssoc = modelMapper.map(exchangeDetails, MasterXchngProductAssociation.class);
		xProdAssocList.add(xProdAssoc);
		exchange.setMasterXchngProductAssociationCollection(xProdAssocList);
		try {
		repository.saveAndFlush(exchange);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return exchangeDetails;
	}
	
	@Transactional
	public List<ExchangeDetailsDto> readByPartyId(Integer partyId) {
		
		List<MasterExchange> exchange = repository.findByPartyId(partyId);
		List<ExchangeDetailsDto> exchangeDetails = new ArrayList<>();
		if(exchange.size()>0) {
			exchange.get(0).getMasterXchngProductAssociationCollection().stream().forEach(data -> {exchangeDetails.add(
					modelMapper.map(data, ExchangeDetailsDto.class));});
		}
		return exchangeDetails;
	}

}

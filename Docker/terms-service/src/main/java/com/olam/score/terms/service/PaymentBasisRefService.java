package com.olam.score.terms.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.olam.score.terms.domain.MasterPaymentBasisRef;
import com.olam.score.terms.dto.PaymentBasisRefDto;
import com.olam.score.terms.repository.PaymentBasisRefRepository;

@Service
public class PaymentBasisRefService {
	
	@Autowired 
	private PaymentBasisRefRepository repository;


	
	@Autowired
	private ModelMapper modelMapper;

	public List<PaymentBasisRefDto> readAll() {
		List<PaymentBasisRefDto> paymentBasisDtoList = new ArrayList<>();
		try{
			List<MasterPaymentBasisRef> paymentBasisRefList = repository.findAll();
			
			for(MasterPaymentBasisRef paymentBasis:paymentBasisRefList){
				PaymentBasisRefDto map = modelMapper.map(paymentBasis,PaymentBasisRefDto.class);
				paymentBasisDtoList.add(map);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return paymentBasisDtoList;
		
	}

}

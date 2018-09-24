package com.olam.score.party.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.olam.score.party.dto.AccountTypeDTO;
import com.olam.score.party.repository.MasterAccountTypeRepository;

@Service
public class MasterAccountTypeService {
	
	@Autowired
	private MasterAccountTypeRepository repository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Transactional
	public List<AccountTypeDTO> fetchAccountType() {
		List<AccountTypeDTO> accountList = new ArrayList<>();
		
		repository.findAll().forEach(data -> {
			accountList.add(modelMapper.map(data, AccountTypeDTO.class));
		});
		return accountList;
	}
	
	

}

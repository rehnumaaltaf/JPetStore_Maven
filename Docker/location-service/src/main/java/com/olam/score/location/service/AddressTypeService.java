package com.olam.score.location.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.olam.score.common.util.WebServiceCallUtil;
import com.olam.score.location.domain.MasterAddressTypeRef;
import com.olam.score.location.dto.AddressTypeDTO;
import com.olam.score.location.repository.AddressTypeRepository;

@Service
public class AddressTypeService {
	
	@Autowired
	private  AddressTypeRepository repository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private WebServiceCallUtil webServiceCall;
	
		@Transactional
	public List<AddressTypeDTO> fetchAddressType()
	{
		List<AddressTypeDTO> addrTypeList = new ArrayList<>();
		
		repository.findAll().forEach(data -> {
			addrTypeList.add(modelMapper.map(data, AddressTypeDTO.class));
		});
		
		return addrTypeList;
	}

		public AddressTypeDTO readById(Integer addressTypeId) {
			
			MasterAddressTypeRef addrTypeList = repository.findOne(addressTypeId);
			Map<Integer, String> statusMap = webServiceCall.getAllStatus();
			AddressTypeDTO addrTypeDto = modelMapper.map(addrTypeList, AddressTypeDTO.class);
			addrTypeDto.setStatusName(statusMap.get(addrTypeDto.getFkStatusId()));
			return addrTypeDto;	
			
		}

}

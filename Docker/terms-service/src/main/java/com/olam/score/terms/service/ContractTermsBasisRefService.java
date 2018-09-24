package com.olam.score.terms.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.olam.score.common.util.WebServiceCallUtil;
import com.olam.score.terms.domain.MasterContractTermsBasisRef;
import com.olam.score.terms.dto.ContractTermsBasisRefDto;
import com.olam.score.terms.repository.ContractTermsRepository;

@Service
public class ContractTermsBasisRefService {

	private final Logger log = LoggerFactory.getLogger(getClass());
	@Autowired 
	private WebServiceCallUtil serviceCall;
	@Autowired
	private ContractTermsRepository repository;
	
	//get all contractTermsBase
		@Transactional
		public List<ContractTermsBasisRefDto> getContractTermsBasis() {
			Map<Integer, String> statusMap = serviceCall.getAllStatus();
			List<ContractTermsBasisRefDto> contractTermsBasisRefList = new ArrayList<>();
			List<MasterContractTermsBasisRef> masterContractTermsBasisRefList = repository.getAllContractTermsBasisRef();
			if(!masterContractTermsBasisRefList.isEmpty()){

				for(MasterContractTermsBasisRef contTermBasis:masterContractTermsBasisRefList){
					ContractTermsBasisRefDto contractTermsBasisRefDto = new ContractTermsBasisRefDto();
					
					contractTermsBasisRefDto.setContractTermsBasisRefId(contTermBasis.getPkContractTermsBasisRefId());
					contractTermsBasisRefDto.setContractTermsBasisCode(contTermBasis.getContractTermsBasisCode());
					contractTermsBasisRefDto.setContractTermsBasisName(contTermBasis.getContractTermsBasisName());
					contractTermsBasisRefDto.setContractTermsBasisDesc(contTermBasis.getContractTermsBasisDesc());
					contractTermsBasisRefDto.setStatusName(statusMap.get(contTermBasis.getFkStatusId()));
					contractTermsBasisRefDto.setStatusId(contTermBasis.getFkStatusId());
					
					contractTermsBasisRefList.add(contractTermsBasisRefDto);
				}			
				return contractTermsBasisRefList;
			}
			return contractTermsBasisRefList;
		}
}

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
import com.olam.score.terms.domain.MasterContractTermsBaseRef;
import com.olam.score.terms.dto.ContractTermsBaseRefDto;
import com.olam.score.terms.repository.ContractTermsRepository;

@Service
public class ContractTermsBaseRefService {

	private final Logger log = LoggerFactory.getLogger(getClass());
	@Autowired 
	private WebServiceCallUtil serviceCall;
	@Autowired
	private ContractTermsRepository repository;
	
	//get all contractTermsBase
		@Transactional
		public List<ContractTermsBaseRefDto> getContractTermsBase() {
			Map<Integer, String> statusMap = serviceCall.getAllStatus();
			List<ContractTermsBaseRefDto> contractTermsBaseRefList = new ArrayList<>();
			List<MasterContractTermsBaseRef> masterContractTermsBaseRefList = repository.getAllContractTermsBaseRef();
			if(!masterContractTermsBaseRefList.isEmpty()){

				for(MasterContractTermsBaseRef contTermBase:masterContractTermsBaseRefList){
					ContractTermsBaseRefDto contractTermsBaseRefDto = new ContractTermsBaseRefDto();
					
					contractTermsBaseRefDto.setContractTermsBaseRefId(contTermBase.getPkContractTermsBaseRefId());
					contractTermsBaseRefDto.setContractTermsBaseCode(contTermBase.getContractTermsBaseCode());
					contractTermsBaseRefDto.setContractTermsBaseName(contTermBase.getContractTermsBaseName());
					contractTermsBaseRefDto.setContractTermsBaseDesc(contTermBase.getContractTermsBaseDesc());
					contractTermsBaseRefDto.setStatusName(statusMap.get(contTermBase.getFkStatusId()));
					contractTermsBaseRefDto.setStatusId(contTermBase.getFkStatusId());
					
					contractTermsBaseRefList.add(contractTermsBaseRefDto);
				}			
				return contractTermsBaseRefList;
			}
			return contractTermsBaseRefList;
		}
}

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
import com.olam.score.terms.domain.MasterContrTrmsAddreduceRef;
import com.olam.score.terms.domain.MasterContractTermsBasisRef;
import com.olam.score.terms.dto.ContrTrmsAddreduceRefDto;
import com.olam.score.terms.dto.ContractTermsBasisRefDto;
import com.olam.score.terms.repository.ContractTermsRepository;

@Service
public class ContrTrmsAddreduceRefService {
	private final Logger log = LoggerFactory.getLogger(getClass());
	@Autowired 
	private WebServiceCallUtil serviceCall;
	@Autowired
	private ContractTermsRepository repository;
	
	//get all contractTermsBase
		@Transactional
		public List<ContrTrmsAddreduceRefDto> getContrTrmsAddreduceRef() {
			Map<Integer, String> statusMap = serviceCall.getAllStatus();
			List<ContrTrmsAddreduceRefDto> contrTrmsAddreduceRefList = new ArrayList<>();
			List<MasterContrTrmsAddreduceRef> masterContrTrmsAddreduceRefList = repository.getAllContrTrmsAddreduceRef();
			if(!masterContrTrmsAddreduceRefList.isEmpty()){

				for(MasterContrTrmsAddreduceRef contTermAddreduce:masterContrTrmsAddreduceRefList){
					ContrTrmsAddreduceRefDto contrTrmsAddreduceRefDto = new ContrTrmsAddreduceRefDto();
					
					contrTrmsAddreduceRefDto.setContrTrmsAddreduceRefId(contTermAddreduce.getPkContrTrmsAddreduceRefId());
					contrTrmsAddreduceRefDto.setAddReduceCode(contTermAddreduce.getAddReduceCode());
					contrTrmsAddreduceRefDto.setAddReduceName(contTermAddreduce.getAddReduceName());
					contrTrmsAddreduceRefDto.setStatusName(statusMap.get(contTermAddreduce.getFkStatusId()));
					contrTrmsAddreduceRefDto.setStatusId(contTermAddreduce.getFkStatusId());
					
					contrTrmsAddreduceRefList.add(contrTrmsAddreduceRefDto);
				}			
				return contrTrmsAddreduceRefList;
			}
			return contrTrmsAddreduceRefList;
		}
}

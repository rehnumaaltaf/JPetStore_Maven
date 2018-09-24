package com.olam.score.cost.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.olam.score.common.util.WebServiceCallUtil;
import com.olam.score.cost.domain.MasterCostGroup;
import com.olam.score.cost.dto.CostGroupDto;
import com.olam.score.cost.repository.CostGroupRepository;

@Service
public class CostGroupService {

	private final Logger log = LoggerFactory.getLogger(getClass());
	@Autowired 
	private WebServiceCallUtil serviceCall;
	@Autowired
	private CostGroupRepository repository;
	
	//get all contractTermsBase
		@Transactional
		public List<CostGroupDto> getCostGroup() {
			Map<Integer, String> statusMap = serviceCall.getAllStatus();
			List<CostGroupDto> costGroupList = new ArrayList<>();
			List<MasterCostGroup> masterCostGroupList = repository.getAllCostGroup();
			if(!masterCostGroupList.isEmpty()){

				for(MasterCostGroup costGroup:masterCostGroupList){
					CostGroupDto costGroupDto = new CostGroupDto();
					
					costGroupDto.setCostGroupId(costGroup.getPkCostGroupId());
					costGroupDto.setCostGroupName(costGroup.getCostGroupName());
					costGroupDto.setCostGroupCode(costGroup.getCostGroupCode());
					costGroupDto.setCostGroupDesc(costGroup.getCostGroupDesc());
					costGroupDto.setStatusName(statusMap.get(costGroup.getFkStatusId()));
					costGroupDto.setStatusId(costGroup.getFkStatusId());
					
					costGroupList.add(costGroupDto);
				}			
				return costGroupList;
			}
			return costGroupList;
		}
}

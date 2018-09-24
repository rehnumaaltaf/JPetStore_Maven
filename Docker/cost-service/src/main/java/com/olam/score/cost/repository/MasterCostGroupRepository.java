package com.olam.score.cost.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.olam.score.cost.domain.MasterCostGroup;

public interface MasterCostGroupRepository extends JpaRepository<MasterCostGroup, Integer>{
	
	public MasterCostGroup getByCostGroupName(String name);

}

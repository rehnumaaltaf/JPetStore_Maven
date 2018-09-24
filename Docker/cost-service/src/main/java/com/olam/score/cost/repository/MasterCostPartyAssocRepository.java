package com.olam.score.cost.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.olam.score.cost.domain.MasterCost;
import com.olam.score.cost.domain.MasterCostPartyAssoc;



public interface MasterCostPartyAssocRepository extends JpaRepository<MasterCostPartyAssoc, Integer>{
	
	int deleteByCostPartyAssocAndPkCostPartyAssocIdNotIn(MasterCost masterCost,List<Integer> delPartyAssocList);
  
	
}

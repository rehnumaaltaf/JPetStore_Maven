package com.olam.score.cost.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.olam.score.cost.domain.MasterFreightCostMatricPartyAssoc;
import com.olam.score.cost.domain.MasterFreightCostMatrix;

public interface MasterFreightCostMatrixPartyAssocRepository extends JpaRepository<MasterFreightCostMatricPartyAssoc, Integer>{
	int deleteByFkFreightCostMatrixIdAndPkFreightCostPartyAssocIdNotIn(MasterFreightCostMatrix masterFreightCostMatrix,List<Integer> pkFreightCostPartyAssocIdList);

}

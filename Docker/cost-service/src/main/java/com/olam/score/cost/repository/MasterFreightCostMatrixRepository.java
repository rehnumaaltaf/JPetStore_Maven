package com.olam.score.cost.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.olam.score.cost.domain.MasterCost;
import com.olam.score.cost.domain.MasterFreightCostMatrix;

public interface MasterFreightCostMatrixRepository extends JpaRepository<MasterFreightCostMatrix, Integer>{
	int deleteByFkCostIdAndPkFreightCostMatrixIdNotIn(MasterCost masterCost,List<Integer> pkFreightCostMatrixIdList);
	int deleteByFkCostId(MasterCost masterCost);
	List<Integer> findPkFreightCostMatrixIdByFkCostId(MasterCost masterCost);
}

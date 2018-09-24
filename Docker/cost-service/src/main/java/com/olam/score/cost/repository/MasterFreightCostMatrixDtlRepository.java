package com.olam.score.cost.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.olam.score.cost.domain.MasterFreightCostMatrix;
import com.olam.score.cost.domain.MasterFreightCostMatrixDtl;

public interface MasterFreightCostMatrixDtlRepository extends JpaRepository<MasterFreightCostMatrixDtl, Integer>{
	int deleteByFkFreightCostMatrixIdAndPkFreightCostMatrixDtlIdNotIn(MasterFreightCostMatrix masterFreightCostMatrix,List<Integer> pkFreightCostMatrixDtlIdList);
}

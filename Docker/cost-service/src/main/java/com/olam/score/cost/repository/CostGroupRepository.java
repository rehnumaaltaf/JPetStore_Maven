package com.olam.score.cost.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.olam.score.cost.domain.MasterCostGroup;

public interface CostGroupRepository extends JpaRepository<MasterCostGroup,Integer> {

	//getAllCostGroup
	@Query("select s from MasterCostGroup s")
	List<MasterCostGroup> getAllCostGroup();
}

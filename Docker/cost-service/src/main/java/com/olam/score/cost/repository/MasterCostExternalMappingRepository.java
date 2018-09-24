package com.olam.score.cost.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.olam.score.cost.domain.MasterCost;
import com.olam.score.cost.domain.MasterCostExternalMapping;

public interface MasterCostExternalMappingRepository extends JpaRepository<MasterCostExternalMapping, Integer>{
	int deleteByCostExterMapAssocAndPkCostExternalMappingIdNotIn(MasterCost masterCost,List<Integer> delPkCostExternalMappingIdList);
	@Modifying
	@Transactional
	@Query("UPDATE MasterCostExternalMapping f SET f.fkStatusId= :fkStatusId WHERE f.costExterMapAssoc= :costExterMapAssoc")
	public int deactivateOrReactivateCostExternalMapping(@Param("fkStatusId") int fkStatusId,@Param("costExterMapAssoc") MasterCost costExterMapAssoc);
	
}

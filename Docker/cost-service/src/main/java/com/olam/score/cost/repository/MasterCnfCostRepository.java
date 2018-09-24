package com.olam.score.cost.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.olam.score.cost.domain.MasterCnfCost;
import com.olam.score.cost.domain.MasterCost;

public interface MasterCnfCostRepository extends JpaRepository<MasterCnfCost, Integer>{
	
	int deleteByFkCostIdAndPkCnfCostIdNotIn(MasterCost masterCost,List<Integer> pkCnfCostIdList);
	
	int deleteByFkCostId(MasterCost masterCost);
	
	List<Integer> findPkCnfCostIdByFkCostId(MasterCost masterCost);
	
	List<Integer> findPkCnfCostIdByFkCostIdAndPkCnfCostIdNotIn(MasterCost masterCost,List<Integer> pkCnfCostIdList);
	
	@Modifying
	@Transactional
	@Query("UPDATE MasterCnfCost f SET f.fkStatusId= :fkStatusId WHERE f.pkCnfCostId= :pkCnfCostId")
	public int deactivateOrReactivateMasterCnfCost(@Param("fkStatusId") int fkStatusId,@Param("pkCnfCostId") int pkCnfCostId);

}

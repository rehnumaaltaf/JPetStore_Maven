package com.olam.score.cost.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.olam.score.cost.domain.MasterCnfCost;
import com.olam.score.cost.domain.MasterCnfCostDetail;

public interface MasterCnfCostDetailRepository extends JpaRepository<MasterCnfCostDetail, Integer>{
	
	int deleteByFkCnfCostIdAndPkCnfCostDetailIdNotIn(MasterCnfCost masterCnfCost,List<Integer> pkCnfCostDetailIdList);
	
	List<Integer> findPkCnfCostDetailIdByFkCnfCostIdAndPkCnfCostDetailIdNotIn(MasterCnfCost masterCnfCost,List<Integer> pkCnfCostDetailIdList);
	
	@Modifying
	@Transactional
	@Query("UPDATE MasterCnfCostDetail f SET f.fkStatusId= :fkStatusId WHERE f.pkCnfCostDetailId= :pkCnfCostDetailId")
	public int deactivateOrReactivateMasterCnfCostDetail(@Param("fkStatusId") int fkStatusId,@Param("pkCnfCostDetailId") int pkCnfCostDetailId);
}

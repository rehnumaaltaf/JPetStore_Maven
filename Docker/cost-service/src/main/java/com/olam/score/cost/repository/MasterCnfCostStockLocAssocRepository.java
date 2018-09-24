package com.olam.score.cost.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.olam.score.cost.domain.MasterCnfCost;
import com.olam.score.cost.domain.MasterCnfCostStockLocAssoc;

public interface MasterCnfCostStockLocAssocRepository extends JpaRepository<MasterCnfCostStockLocAssoc, Integer>{
	int deleteByFkCnfCostIdAndPkCnfCostStockLocAssocIdNotIn(MasterCnfCost masterCnfCost,List<Integer> pkCnfCostStockLocAssocIdList);
	
	List<Integer> findPkCnfCostStockLocAssocIdByFkCnfCostIdAndPkCnfCostStockLocAssocIdNotIn(MasterCnfCost masterCnfCost,List<Integer> pkCnfCostStockLocAssocIdList);
	
	@Modifying
	@Transactional
	@Query("UPDATE MasterCnfCostStockLocAssoc f SET f.fkStatusId= :fkStatusId WHERE f.pkCnfCostStockLocAssocId= :pkCnfCostStockLocAssocId")
	public int deactivateOrReactivateMasterCnfCostDetail(@Param("fkStatusId") int fkStatusId,@Param("pkCnfCostStockLocAssocId") int pkCnfCostStockLocAssocId);

}

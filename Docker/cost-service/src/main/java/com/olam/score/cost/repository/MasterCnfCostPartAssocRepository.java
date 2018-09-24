package com.olam.score.cost.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.olam.score.cost.domain.MasterCnfCost;
import com.olam.score.cost.domain.MasterCnfCostPartyAssoc;

public interface MasterCnfCostPartAssocRepository extends JpaRepository<MasterCnfCostPartyAssoc, Integer>,CrudRepository<MasterCnfCostPartyAssoc, Integer>{
	
	int deleteByFkCnfCostIdAndPkCnfCostPartyAssocIdNotIn(MasterCnfCost masterCnfCost,List<Integer> pkCnfCostPartyAssocIdList);
	
	List<Integer> findPkCnfCostPartyAssocIdByFkCnfCostIdAndPkCnfCostPartyAssocIdNotIn(MasterCnfCost masterCnfCost,List<Integer> pkCnfCostPartyAssocIdList);
	
	@Modifying
	@Transactional
	@Query("UPDATE MasterCnfCostPartyAssoc f SET f.fkStatusId= :fkStatusId WHERE f.pkCnfCostPartyAssocId= :pkCnfCostPartyAssocId")
	public int deactivateOrReactivateMasterCnfCostDetail(@Param("fkStatusId") int fkStatusId,@Param("pkCnfCostPartyAssocId") int pkCnfCostPartyAssocId);

}

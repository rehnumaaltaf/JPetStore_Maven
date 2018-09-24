package com.olam.score.cost.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.olam.score.cost.domain.MasterWhCost;
import com.olam.score.cost.domain.MasterWhCostPartyAssoc;

public interface MasterWhCostPartyAssocRepository extends JpaRepository<MasterWhCostPartyAssoc, Integer>{
int deleteByFkWhCostIdAndPkWhCostPartyAssocIdNotIn(MasterWhCost masterWhCost,List<Integer> pkWhCostPartyAssocIdList);
	
	List<Integer> findPkWhCostPartyAssocIdByFkWhCostIdAndPkWhCostPartyAssocIdNotIn(MasterWhCost masterWhCost,List<Integer> pkWhCostPartyAssocIdList);
	
	@Modifying
	@Transactional
	@Query("UPDATE MasterWhCostPartyAssoc f SET f.fkStatusId= :fkStatusId WHERE f.pkWhCostPartyAssocId= :pkWhCostPartyAssocId")
	public int deactivateOrReactivateMasterWhCostDetail(@Param("fkStatusId") int fkStatusId,@Param("pkWhCostPartyAssocId") int pkWhCostPartyAssocId);
}

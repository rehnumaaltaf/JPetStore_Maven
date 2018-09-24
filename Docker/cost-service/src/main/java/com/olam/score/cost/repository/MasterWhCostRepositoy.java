package com.olam.score.cost.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.olam.score.cost.domain.MasterCost;
import com.olam.score.cost.domain.MasterWhCost;

public interface MasterWhCostRepositoy extends JpaRepository<MasterWhCost,Integer>{
int deleteByFkCostIdAndPkWhCostIdNotIn(MasterCost masterCost,List<Integer> pkWhCostIdList);
	
	List<Integer> findPkWhCostIdByFkCostIdAndPkWhCostIdNotIn(MasterCost masterCost,List<Integer> pkWhCostIdList);
	int deleteByFkCostId(MasterCost masterCost);
	
	List<Integer> findPkWhCostIdByFkCostId(MasterCost masterCost);
	
	@Modifying
	@Transactional
	@Query("UPDATE MasterWhCost f SET f.fkStatusId= :fkStatusId WHERE f.pkWhCostId= :pkWhCostId")
	public int deactivateOrReactivateMasterWhCost(@Param("fkStatusId") int fkStatusId,@Param("pkWhCostId") int pkWhCostId);
	
	
	
	

}

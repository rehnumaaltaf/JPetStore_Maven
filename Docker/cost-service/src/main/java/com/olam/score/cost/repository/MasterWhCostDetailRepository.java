package com.olam.score.cost.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.olam.score.cost.domain.MasterWhCost;
import com.olam.score.cost.domain.MasterWhCostDetail;

public interface MasterWhCostDetailRepository extends JpaRepository<MasterWhCostDetail, Integer>{
int deleteByFkWhCostIdAndPkWhCostDetailIdNotIn(MasterWhCost masterWhCost,List<Integer> pkWhCostDetailIdList);
	
	List<Integer> findPkWhCostDetailIdByFkWhCostIdAndPkWhCostDetailIdNotIn(MasterWhCost masterWhCost,List<Integer> pkWhCostDetailIdList);
	
	@Modifying
	@Transactional
	@Query("UPDATE MasterWhCostDetail f SET f.fkStatusId= :fkStatusId WHERE f.pkWhCostDetailId= :pkWhCostDetailId")
	public int deactivateOrReactivateMasterWhCostDetail(@Param("fkStatusId") int fkStatusId,@Param("pkWhCostDetailId") int pkWhCostDetailId);
}

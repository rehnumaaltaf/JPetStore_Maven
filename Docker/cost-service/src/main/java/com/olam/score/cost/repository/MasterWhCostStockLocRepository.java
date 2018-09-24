package com.olam.score.cost.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.olam.score.cost.domain.MasterWhCost;
import com.olam.score.cost.domain.MasterWhCostStockLocAssoc;

public interface MasterWhCostStockLocRepository extends JpaRepository<MasterWhCostStockLocAssoc, Integer>{
	int deleteByFkWhCostIdAndPkWhCostStockAssocIdNotIn(MasterWhCost masterWhCost,List<Integer> pkWhCostStockAssocIdList);
	
	List<Integer> findPkWhCostStockAssocIdByFkWhCostIdAndPkWhCostStockAssocIdNotIn(MasterWhCost masterWhCost,List<Integer> pkWhCostStockAssocIdList);
	
	@Modifying
	@Transactional
	@Query("UPDATE MasterWhCostStockLocAssoc f SET f.fkStatusId= :fkStatusId WHERE f.pkWhCostStockAssocId= :pkWhCostStockAssocId")
	public int deactivateOrReactivateMasterWhCostDetail(@Param("fkStatusId") int fkStatusId,@Param("pkWhCostStockAssocId") int pkWhCostStockAssocId);
}

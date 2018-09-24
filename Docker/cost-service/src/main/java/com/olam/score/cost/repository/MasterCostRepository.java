package com.olam.score.cost.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.olam.score.cost.domain.MasterCost;


public interface MasterCostRepository  extends JpaRepository<MasterCost, Integer>{
	
	@Query("select count(m.costCode) from MasterCost m where m.costCode =?1")
	int uniqueCostCode(@Param("costCode")String costCode);
	
	@Query("select count(m.costName) from MasterCost m where m.costName =?1")
	int uniqueCostName(@Param("costName")String costName);
	
	public List<MasterCost> getByCostName(String name);
	
	public List<MasterCost> getByCostCode(String code);
	
	public List<MasterCost> getByCostNameAndPkCostIdNotIn(String name,int id);
	
	public List<MasterCost> getByCostCodeAndPkCostIdNotIn(String code,int id);
	
	@Query("select mfc from MasterCost mfc order by mfc.pkCostId desc")
	public List<MasterCost> getAllCost();
	
	public List<MasterCost> findAllByOrderByCreatedDateDesc();
	
	@Modifying
	@Transactional
	@Query("UPDATE MasterCost f SET f.fkStatusId= :fkStatusId WHERE f.pkCostId= :pkCostId")
	public int deactivateOrReactivateMasterCost(@Param("fkStatusId") int fkStatusId,@Param("pkCostId") int pkCostId);

}

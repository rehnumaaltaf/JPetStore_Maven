package com.olam.score.cost.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.olam.score.cost.domain.MasterWhCostTypeRef;





public interface MasterWhCostTypeRefRepository extends JpaRepository<MasterWhCostTypeRef, Integer> {
	
	@Query("select mfm from MasterWhCostTypeRef mfm where mfm.whCostTypeName=:whCostTypeName")
	public MasterWhCostTypeRef getname(@Param("whCostTypeName") String whCostTypeName);
	
}

package com.olam.score.terms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.olam.score.terms.domain.MasterFranchiseValueUnit;
import com.olam.score.terms.domain.MasterWeightTerms;

@Repository
public interface FranchiseRepository extends JpaRepository<MasterFranchiseValueUnit, Integer> {

	@Query("select s.franchiseValueUnitCode from MasterFranchiseValueUnit s where s.franchiseValueUnitCode = ?1")
	String findByFranchiseValueUnitCode(@Param("franchiseValueUnitCode") String franchiseValueUnitCode);
	
}


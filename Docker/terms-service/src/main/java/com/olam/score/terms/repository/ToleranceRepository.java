package com.olam.score.terms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.olam.score.terms.domain.MasterFranchiseValueUnit;
import com.olam.score.terms.domain.MasterToleranceValueUnit;

@Repository
public interface ToleranceRepository extends JpaRepository<MasterToleranceValueUnit, Integer> {

	@Query("select s.toleranceValueUnitCode from MasterToleranceValueUnit s where s.toleranceValueUnitCode = ?1")
	String findByToleranceValueUnitCode(@Param("toleranceValueUnitCode") String toleranceValueUnitCode);
	
}


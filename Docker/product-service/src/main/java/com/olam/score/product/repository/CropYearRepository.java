package com.olam.score.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.olam.score.product.domain.MasterCropYear;

@Repository
public interface CropYearRepository extends JpaRepository<MasterCropYear, Integer> {

	@Query("select cy from MasterCropYear cy where upper(cy.cropYearName) = ?1")
	MasterCropYear findByCropYearName(@Param("cropYearName") String cropYearName);

	@Query("select cy from MasterCropYear cy where upper(cy.cropYearCode) = ?1")
	MasterCropYear findByCropYearCode(@Param("cropYearCode") String cropYearCode);
}

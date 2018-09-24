package com.olam.score.product.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.olam.score.product.domain.MasterCropYearProductAsso;

@Repository
public interface CropYearProductRepository extends JpaRepository<MasterCropYearProductAsso, Integer> {

	/*
	 * @Query(
	 * "select cyp from MasterCropYearProductAsso cyp join fetch cyp.masterCropYear cy join fetch cyp.masterProduct p"
	 * ) List<MasterCropYearProductAsso> findAllCropYearProd(Sort sort);
	 */

	@Query("select cyp from MasterCropYearProductAsso cyp where cyp.masterCropYear.pkCropYearId = ?1")
	List<MasterCropYearProductAsso> findByCropYearId(@Param("pkCropYearId") Integer pkCropYearId);

	@Modifying
	@Transactional
	@Query("delete from MasterCropYearProductAsso cyp where cyp.masterCropYear.pkCropYearId = ?1")
	void deleteAllWithCropYear(@Param("existingProductIds") Integer existingProductIds);
}

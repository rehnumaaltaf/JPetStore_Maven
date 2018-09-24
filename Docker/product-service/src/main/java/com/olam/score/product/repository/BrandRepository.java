package com.olam.score.product.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.olam.score.product.domain.MasterBrand;

public interface BrandRepository extends JpaRepository<MasterBrand, Integer>,CrudRepository<MasterBrand,Integer>{

	@Query("select count(f) from MasterBrand f where upper(f.brandName)=upper(:brandName)")
	public Integer getByName(@Param("brandName") String brandName);
	
	@Query("select count(f) from MasterBrand f where upper(f.brandCode)=upper(:brandCode)")
	public Integer getByCode(@Param("brandCode") String brandCode);
	
	@Query("select count(f) from MasterBrand f where upper(f.brandName)=upper(:brandName) and f.pkBrandId!=:pkBrandId")
	public Integer getByNameForEdit(@Param("brandName") String brandName,@Param("pkBrandId") int pkBrandId);
	
	@Query("select count(f) from MasterBrand f where upper(f.brandCode)=upper(:brandCode) and f.pkBrandId!=:pkBrandId")
	public Integer getByCodeForEdit(@Param("brandCode") String brandCode,@Param("pkBrandId") int pkBrandId);
	
	public List<MasterBrand> findAllByOrderByCreatedDateDesc();
	
	public List<MasterBrand> findByBrandNameContaining(String name,Pageable pageable);
	
	public List<MasterBrand> findByBrandCodeContaining(String code,Pageable pageable);
	
	@Modifying
	@Transactional
	@Query("UPDATE MasterBrand f SET f.fkStatusId= :fkStatusId WHERE f.pkBrandId= :pkBrandId")
	public int deactivateOrReactivatePrimaryPackingType(@Param("fkStatusId") int fkStatusId,@Param("pkBrandId") int pkBrandId);
}

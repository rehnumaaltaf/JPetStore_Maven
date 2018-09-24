package com.olam.score.packing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.olam.score.packing.domain.MasterSecondaryPackingType;

@Repository
public interface SecondaryPackingTypeRepository extends JpaRepository<MasterSecondaryPackingType, Integer>, JpaSpecificationExecutor<MasterSecondaryPackingType>{

	@Query("select ml from MasterSecondaryPackingType ml where ml.pkSecondaryPackingTypeId =:pkSecondaryPackingTypeId")
	List<MasterSecondaryPackingType> getSecPackById(@Param("pkSecondaryPackingTypeId") Integer pkSecondaryPackingTypeId);
	
	@Query("select count(ml.secondaryPackingTypeCode) from MasterSecondaryPackingType ml where ml.secondaryPackingTypeCode=:secCode")
	int uniqueSecPackCode(@Param("secCode")String secCode);
	
	@Query("select count(ml.secondaryPackingTypeName) from MasterSecondaryPackingType ml where ml.secondaryPackingTypeName=:secName")
	int uniqueSecPackName(@Param("secName")String secName);
	
	@Query("select ml.secondaryPackingTypeCode from MasterSecondaryPackingType ml where ml.secondaryPackingTypeCode like CONCAT(:secCode,'%')")
	List<String> suggestSecCode(@Param("secCode")String secCode);
	
	@Query("select ml.secondaryPackingTypeName from MasterSecondaryPackingType ml where ml.secondaryPackingTypeName like CONCAT(:secName,'%')")
	List<String> suggestSecName(@Param("secName")String secName);
	
	List<MasterSecondaryPackingType> getBySecondaryPackingTypeCodeAndPkSecondaryPackingTypeIdNotIn(String code,int id);
	List<MasterSecondaryPackingType> getBySecondaryPackingTypeNameAndPkSecondaryPackingTypeIdNotIn(String name,int id);
	
}
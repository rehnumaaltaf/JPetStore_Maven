package com.olam.score.packing.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.olam.score.packing.domain.MasterPackingMaterial;



public interface PackingMaterialRepository extends JpaRepository<MasterPackingMaterial, Integer>{
	
	@Query("select count(f) from MasterPackingMaterial f where upper(f.packingMaterialName)=upper(:packingMaterialName)")
	public Integer getByName(@Param("packingMaterialName") String packingMaterialName);
	
	
	@Query("select count(f) from MasterPackingMaterial f where upper(f.packingMaterialCode)=upper(:packingMaterialCode)")
	public Integer getByCode(@Param("packingMaterialCode") String packingMaterialCode);
	
	@Query("select mfc from MasterPackingMaterial mfc order by mfc.pkPackingMaterialId desc")
	public List<MasterPackingMaterial> getAllMaterials();
	
public List<MasterPackingMaterial> findByPackingMaterialNameContaining(String name,Pageable pageable);
	
	public List<MasterPackingMaterial> findByPackingMaterialCodeContaining(String code,Pageable pageable);
	
	@Query("select count(ml.packingMaterialCode) from MasterPackingMaterial ml where ml.packingMaterialCode =?1")
	int uniquePackCode(@Param("packingMaterialCode")String packingMaterialCode);
	
	@Query("select count(ml.packingMaterialName) from MasterPackingMaterial ml where ml.packingMaterialName =?1")
	int uniquePackName(@Param("packingMaterialName")String packingMaterialName);
	
	List<MasterPackingMaterial> getByPackingMaterialCodeAndPkPackingMaterialIdNotIn(String code,int id);
	List<MasterPackingMaterial> getByPackingMaterialNameAndPkPackingMaterialIdNotIn(String name,int id);
	
}

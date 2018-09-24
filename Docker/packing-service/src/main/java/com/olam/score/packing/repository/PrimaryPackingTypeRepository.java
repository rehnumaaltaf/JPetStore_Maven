package com.olam.score.packing.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.olam.score.packing.domain.MasterPrimaryPackingType;

@Repository
public interface PrimaryPackingTypeRepository extends JpaRepository<MasterPrimaryPackingType, Integer>,CrudRepository<MasterPrimaryPackingType,Integer>,PagingAndSortingRepository<MasterPrimaryPackingType, Integer>,JpaSpecificationExecutor<MasterPrimaryPackingType>{
	public MasterPrimaryPackingType getByPkPrimaryPackingTypeId(int id);
	
	@Query("select count(f) from MasterPrimaryPackingType f where upper(f.primaryPackingTypeName)=upper(:primaryPackingTypeName)")
	public Integer getByName(@Param("primaryPackingTypeName") String primaryPackingTypeName);
	
	public List<MasterPrimaryPackingType> findByPrimaryPackingTypeName(String primaryPackingTypeName);
	
	@Query("select count(f) from MasterPrimaryPackingType f where upper(f.primaryPackingTypeCode)=upper(:primaryPackingTypeCode)")
	public Integer getByCode(@Param("primaryPackingTypeCode") String primaryPackingTypeCode);
	
	public List<MasterPrimaryPackingType> findByPrimaryPackingTypeCode(String primaryPackingTypeCode);
	
	@Query("select count(f) from MasterPrimaryPackingType f where upper(f.primaryPackingTypeName)=upper(:primaryPackingTypeName) and f.pkPrimaryPackingTypeId!=:pkPrimaryPackingTypeId")
	public Integer getByNameForEdit(@Param("primaryPackingTypeName") String primaryPackingTypeName,@Param("pkPrimaryPackingTypeId") int pkPrimaryPackingTypeId);
	
	@Query("select count(f) from MasterPrimaryPackingType f where upper(f.primaryPackingTypeCode)=upper(:primaryPackingTypeCode) and f.pkPrimaryPackingTypeId!=:pkPrimaryPackingTypeId")
	public Integer getByCodeForEdit(@Param("primaryPackingTypeCode") String primaryPackingTypeCode,@Param("pkPrimaryPackingTypeId") int pkPrimaryPackingTypeId);
	
	public List<MasterPrimaryPackingType> findAllByOrderByCreatedDateDesc(Pageable pageable);
	
	public List<MasterPrimaryPackingType> findAllByOrderByCreatedDateDesc();
	
	public List<MasterPrimaryPackingType> findByPrimaryPackingTypeNameContaining(String name);
	
	public List<MasterPrimaryPackingType> findByPrimaryPackingTypeNameContaining(String name,Pageable pageable);
	public List<MasterPrimaryPackingType> findByPrimaryPackingTypeCodeContaining(String name,Pageable pageable);
	
	public List<MasterPrimaryPackingType> getByPrimaryPackingTypeNameAndPkPrimaryPackingTypeIdNotIn(String primaryPackingTypeName,int pkPrimaryPackingTypeId);
	public List<MasterPrimaryPackingType> getByPrimaryPackingTypeCodeAndPkPrimaryPackingTypeIdNotIn(String primaryPackingTypeCode,int pkPrimaryPackingTypeId);
	
	@Modifying
	@Transactional
	@Query("UPDATE MasterPrimaryPackingType f SET f.fkStatusId= :fkStatusId WHERE f.pkPrimaryPackingTypeId= :pkPrimaryPackingTypeId")
	public int deactivateOrReactivatePrimaryPackingType(@Param("fkStatusId") int fkStatusId,@Param("pkPrimaryPackingTypeId") int pkPrimaryPackingTypeId);
}

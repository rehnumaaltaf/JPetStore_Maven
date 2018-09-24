package com.olam.score.forex.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.olam.score.forex.domain.MasterTenorType;

@Repository
public interface TenorTypeRepository extends JpaRepository<MasterTenorType, String>,CrudRepository<MasterTenorType,String>{
	public MasterTenorType getByPkTenorTypeId(int pkTenorTypeId);
	
	@Query("select f from MasterTenorType f where f.fkStatusId=:fkStatusId")
	public List<MasterTenorType> returnAll(@Param("fkStatusId")int fkStatusId);
	
}

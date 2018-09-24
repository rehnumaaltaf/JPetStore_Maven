package com.olam.score.forex.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.olam.score.forex.domain.MasterTenorDayType;

@Repository
public interface TenorDayTypeRepository extends JpaRepository<MasterTenorDayType, String>,CrudRepository<MasterTenorDayType,String>{
	
	public MasterTenorDayType getByPkTenorDayTypeId(int id);
	
	
	@Query("select f from MasterTenorDayType f where f.fkStatusId=:fkStatusId")
	public List<MasterTenorDayType> returnAll(@Param("fkStatusId")int fkStatusId);
	
}

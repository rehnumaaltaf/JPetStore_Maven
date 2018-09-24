package com.olam.score.forex.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.olam.score.forex.domain.MasterTenorDurationType;

@Repository
public interface TenorDurationTypeRepository extends JpaRepository<MasterTenorDurationType, String>,CrudRepository<MasterTenorDurationType,String>{
	
	public MasterTenorDurationType getByPkTenorDurationTypeId(int id);
	
	@Query("select f from MasterTenorDurationType f where f.fkStatusId=:fkStatusId")
	public List<MasterTenorDurationType> returnAll(@Param("fkStatusId")int fkStatusId);
	
}

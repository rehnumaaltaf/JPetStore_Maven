package com.olam.score.forex.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.olam.score.forex.domain.MasterForexFrequency;



@Repository
public interface ForexFrequencyRepository extends JpaRepository<MasterForexFrequency, String>,CrudRepository<MasterForexFrequency,String>{
	
	public MasterForexFrequency getByPkForexFrequencyId(int id);
	
	@Query("select f from MasterForexFrequency f where f.fkStatusId=:fkStatusId")
	public List<MasterForexFrequency> returnAll(@Param("fkStatusId")int fkStatusId);
}

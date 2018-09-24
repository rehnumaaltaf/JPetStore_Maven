package com.olam.score.location.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.olam.score.location.domain.MasterLocationType;

@Repository
public interface LocationTypeRepository extends JpaRepository<MasterLocationType, Integer>{
	
	
	//String findByLocTypeName(String id);
	
	@Query("SELECT t.locTypeName FROM MasterLocationType t where t.pkLocTypeId = :pkLocTypeId") 
    String findByLocTypeName(@Param("pkLocTypeId") int pkLocTypeId);

}

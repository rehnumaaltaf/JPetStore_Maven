package com.olam.score.location.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.olam.score.location.domain.MasterLocation;


@Repository
public interface LocationRepository extends JpaRepository<MasterLocation, Integer> {
 
	@Query("select count(ml.locCode) from MasterLocation ml where ml.locCode =?1")
	int uniqueLocCode(@Param("locCode")String locCode);
	
	@Query("select count(ml.locName) from MasterLocation ml where ml.locName =?1")
	int uniqueLocName(@Param("locName")String locName);
	
	@Query("select count(ml.locFullname) from MasterLocation ml where ml.locFullname =?1")
	int uniqueLocFullName(@Param("locFullname")String locFullname);
	
	@Query("select ml from MasterLocation ml where ml.pkLocId = :pkLocId")
	List<MasterLocation> getLocationById(@Param("pkLocId")Integer pkLocId);
	
	List<MasterLocation> getByLocCodeAndPkLocIdNotIn(String code,int id);
	List<MasterLocation> getByLocNameAndPkLocIdNotIn(String name,int id);
	
	@Modifying
	@Transactional
	@Query("UPDATE MasterLocation f SET f.fkStatusId= :fkStatusId WHERE f.pkLocId= :pkLocId")
	public int deactivateOrReactivateLocation(@Param("fkStatusId") int fkStatusId,@Param("pkLocId") int pkLocId);
}

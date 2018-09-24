package com.olam.score.location.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.olam.score.location.domain.MasterLocLocTypeAssoc;
import com.olam.score.location.domain.MasterLocation;

@Repository
public interface LocTypeAssocRepository extends JpaRepository<MasterLocLocTypeAssoc, Integer> {
    
	@Modifying
	@Transactional
	@Query("delete from MasterLocLocTypeAssoc ml where ml.pkLocId =:pkLocId")
	int deleteLocTypeAssocId(@Param("pkLocId") MasterLocation pkLocId);

}

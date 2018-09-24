package com.olam.score.packing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.olam.score.packing.domain.MasterPackingAssoc;

@Repository
public interface SecondaryPackingAssocRepository extends JpaRepository<MasterPackingAssoc, Integer>{
	
	@Modifying
	@Transactional
	@Query("delete from MasterPackingAssoc ml where ml.secPackAssoc.pkSecondaryPackingTypeId =:pkPackingAssocId") 
	int deleteAssocId(@Param("pkPackingAssocId") Integer pkPackingAssocId);

}
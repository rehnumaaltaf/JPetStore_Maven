package com.olam.score.reference.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.olam.score.reference.domain.MasterUom;

@Repository
public interface UOMRepository extends JpaRepository<MasterUom, Integer>,JpaSpecificationExecutor<MasterUom> {

	@Query("select s from MasterUom s where s.uomCode = ?1")
	MasterUom findByUomCode(@Param("uomCode") String uomCode);
	
	@Query("select s.pkUomId from MasterUom s where s.fkBaseUomId.pkUomId = ?1")
	List<Integer> findByParentUom(@Param("masterUom") Integer uomId);
	
	List<MasterUom> findByFkStatusId(int id);
	
	@Query("select s from MasterUom s where s.uomName = ?1")
	MasterUom findByUomName(@Param("uomName") String uomName);
	

}

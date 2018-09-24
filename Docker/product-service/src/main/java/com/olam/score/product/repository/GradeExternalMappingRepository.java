package com.olam.score.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.olam.score.product.domain.MasterGrade;
import com.olam.score.product.domain.MasterGradeExternalMapping;

@Repository
public interface GradeExternalMappingRepository extends  JpaRepository<MasterGradeExternalMapping,Integer>{
	@Modifying
	@Transactional
	@Query("delete from MasterGradeExternalMapping m where m.fkGradeId=?1")
	void deleteByFkGradeId(@Param("fkGradeId") MasterGrade grade);

}

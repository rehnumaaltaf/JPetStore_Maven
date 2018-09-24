package com.olam.score.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.olam.score.product.domain.MasterGrade;
import com.olam.score.product.domain.MasterGradeIntlCode;
import com.olam.score.product.domain.MasterProduct;


public interface GradeIntlCodeRepository extends JpaRepository<MasterGradeIntlCode, Integer>,JpaSpecificationExecutor<MasterGradeIntlCode> {

	@Query(value = "select product from MasterGradeIntlCode product"
			+ " where product.fkGradeId.pkGradeId = :fkGradeId",nativeQuery = false)
	MasterGradeIntlCode findByName(@Param("fkGradeId") String fkGradeId);
	
	@Modifying
	@Transactional
	@Query("delete from MasterGradeIntlCode m where m.fkGradeId=?1")
	void deleteByFkGradeId(@Param("fkGradeId") MasterGrade grade);

	
}

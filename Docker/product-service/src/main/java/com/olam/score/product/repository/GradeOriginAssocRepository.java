package com.olam.score.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.olam.score.product.domain.MasterGrade;
import com.olam.score.product.domain.MasterGradeOriginAssoc;
@Repository
public interface GradeOriginAssocRepository
		extends JpaRepository<MasterGradeOriginAssoc, Integer>, JpaSpecificationExecutor<MasterGradeOriginAssoc> {
	@Modifying
	@Transactional
	@Query("delete from MasterGradeOriginAssoc m where m.fkGradeId=?1")
	void deleteByFkGradeId(@Param("fkGradeId") MasterGrade grade);

	/*
	 * @Query(value =
	 * "select gradeOriginAssoc from MasterGradeOriginAssoc gradeOriginAssoc" +
	 * " where gradeOriginAssoc.fkGradeId = :fkGradeId", nativeQuery = false)
	 * List<MasterGradeOriginAssoc> findByName(@Param("fkGradeId") String
	 * fkGradeId);
	 */

}

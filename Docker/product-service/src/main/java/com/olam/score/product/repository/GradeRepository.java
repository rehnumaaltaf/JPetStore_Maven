package com.olam.score.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.olam.score.product.domain.MasterGrade;

public interface GradeRepository extends JpaRepository<MasterGrade, Integer>, JpaSpecificationExecutor<MasterGrade> {

	@Query(value = "select grade from MasterGrade grade" + " where grade.gradeName = :gradeName", nativeQuery = false)
	MasterGrade findByName(@Param("gradeName") String gradeName);

	@Query(value = "select grade from MasterGrade grade" + " where grade.gradeCode = :gradeCode", nativeQuery = false)
	MasterGrade findByCode(@Param("gradeCode") String gradeCode);

	@Query("select s from MasterGrade s where s.fkProdId.pkProdId=?1 and s.gradeIsRaw =?2  ")
	List<MasterGrade> findRawOrFinishedGrade(@Param( "prodId") Integer prodId,@Param ("gradeIsRaw") String gradeIsRaw) ;
}

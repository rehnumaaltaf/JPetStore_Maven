package com.olam.score.product.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.olam.score.product.domain.MasterOutturnRawGrade;
import com.olam.score.product.dto.OutturnRawGradeDto;


@Repository
public interface OutturnRatioRepository extends JpaRepository<MasterOutturnRawGrade, Integer>, JpaSpecificationExecutor<MasterOutturnRawGrade> {




	@Transactional
	@Modifying
	@Query("delete from MasterOutturnRatio m where m.pkOutturnRatioId = ?1")
	void deleteByOuturnRatioListId(@Param("pkOutturnRatioId") Integer pkOutturnRatioId); 
	
	//m.pkOutturnRawGradeId,m.fkProdId,m.fkOriginId,m.fkGradeId
	@Query("select m from MasterOutturnRawGrade m where m.fkProdId = ?1 and m.fkOriginId =?2 and m.fkGradeId=?3")
	MasterOutturnRawGrade findByProdOriginGrade(@Param("prodId") Integer prodId,@Param("originId") Integer originId,@Param("gradeId") Integer gradeId); 
	


}












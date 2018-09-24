package com.olam.score.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.olam.score.product.domain.MasterGradeGroupRef;
import com.olam.score.product.domain.MasterOriginGradeGroup;

public interface GradeGroupOriginAssoc extends JpaRepository<MasterOriginGradeGroup,Integer>{

	List<MasterOriginGradeGroup> findByFkGradeGroupRefId(MasterGradeGroupRef findOne);

}

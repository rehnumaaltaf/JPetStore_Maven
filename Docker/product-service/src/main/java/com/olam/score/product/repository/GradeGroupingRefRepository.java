package com.olam.score.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.olam.score.product.domain.MasterGradeGroupRef;

@Repository
public interface GradeGroupingRefRepository extends JpaRepository<MasterGradeGroupRef,Integer>{

}

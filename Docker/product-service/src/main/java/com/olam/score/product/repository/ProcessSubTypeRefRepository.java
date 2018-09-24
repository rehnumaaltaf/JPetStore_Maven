package com.olam.score.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.olam.score.product.domain.MasterProcessSubTypeRef;

@Repository
public interface ProcessSubTypeRefRepository extends JpaRepository<MasterProcessSubTypeRef, Integer> {

}

package com.olam.score.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.olam.score.product.domain.MasterProductIntlCode;

public interface InternationGradeProductRepository extends JpaRepository<MasterProductIntlCode, Integer>,JpaSpecificationExecutor<MasterProductIntlCode>  {
	

}

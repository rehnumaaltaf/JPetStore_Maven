package com.olam.score.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.olam.score.product.domain.MasterProductArbitrationMapping;

public interface ProductArbitrationRepository extends JpaRepository<MasterProductArbitrationMapping, Integer>,JpaSpecificationExecutor<MasterProductArbitrationMapping>  {

}

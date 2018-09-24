package com.olam.score.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.olam.score.product.domain.MasterProduct;


public interface ProductRepository extends JpaRepository<MasterProduct, Integer>,JpaSpecificationExecutor<MasterProduct> {

	@Query(value = "select product from MasterProduct product"
			+ " where product.prodName = :prodName",nativeQuery = false)
	MasterProduct findByName(@Param("prodName") String prodName);

	@Query(value = "select product from MasterProduct product"
			+ " where product.prodCode = :prodCode",nativeQuery = false)
	MasterProduct findByCode(@Param("prodCode") String prodCode);
	
}

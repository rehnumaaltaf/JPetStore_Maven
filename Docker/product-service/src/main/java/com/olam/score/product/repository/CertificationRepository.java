package com.olam.score.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.olam.score.product.domain.MasterProductCertification;

@Repository
public interface CertificationRepository extends JpaRepository<MasterProductCertification, Integer>,
		JpaSpecificationExecutor<MasterProductCertification> {

	@Query("select pc from MasterProductCertification pc where upper(pc.prodCertName) = ?1")
	MasterProductCertification findByCertificationName(@Param("cropYearName") String certificationName);

	@Query("select pc from MasterProductCertification pc where upper(pc.prodCertCode) = ?1")
	MasterProductCertification findByCertificationCode(@Param("cropYearCode") String certificationCode);

	@Query("select pc from MasterProductCertification pc where upper(pc.registrationNumber) = ?1")
	MasterProductCertification findByCertificationRegistrationNumber(@Param("registrationNumber") String registrationNumber);
}

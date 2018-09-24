package com.olam.score.terms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.olam.score.terms.domain.MasterPaymentTerm;

@Repository
public interface PaymentTermsRepository extends JpaRepository<MasterPaymentTerm, Integer> ,JpaSpecificationExecutor<MasterPaymentTerm>{

	@Query(value = "select paymentTerm from MasterPaymentTerm paymentTerm"
			+ " where paymentTerm.payTermName = :paymentTermName",nativeQuery = false)
	MasterPaymentTerm findByName(@Param("paymentTermName") String paymentTermName);

	@Query(value = "select paymentTerm from MasterPaymentTerm paymentTerm"
			+ " where paymentTerm.payTermCode = :payTermCode",nativeQuery = false)
	MasterPaymentTerm findByCode(@Param("payTermCode") String payTermCode);

}

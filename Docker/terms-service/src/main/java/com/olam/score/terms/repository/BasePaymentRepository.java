package com.olam.score.terms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.olam.score.terms.domain.MasterPaymentTermBase;

@Repository
public interface BasePaymentRepository extends JpaRepository<MasterPaymentTermBase, Integer>,JpaSpecificationExecutor<MasterPaymentTermBase> {

	MasterPaymentTermBase findByTermBaseCode(String baseTermCode);
	MasterPaymentTermBase findByTermBaseName(String baseTermName);

}

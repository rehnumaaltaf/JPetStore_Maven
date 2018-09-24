package com.olam.score.terms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.olam.score.terms.domain.MasterPaymentTermBase;
import com.olam.score.terms.domain.MasterPaymentTermBaseNego;

@Repository
public interface BasePaymentNegoRepository extends JpaRepository<MasterPaymentTermBaseNego, Integer>,JpaSpecificationExecutor<MasterPaymentTermBaseNego>{
	List<MasterPaymentTermBaseNego> findByFkPaymentTermBaseId(MasterPaymentTermBase fkPaymentTermBaseId);

	@Modifying
	@Transactional
	@Query("delete from MasterPaymentTermBaseNego m where m.fkPaymentNegotiationId.pkPaymentNegotiationId in ?1")
	void deleteByBasePaymentId(@Param("pkPaymentNegotiationId") List<Integer> pkPaymentNegotiationId);

	@Modifying
	@Transactional
	@Query("delete from MasterPaymentTermBaseNego m where m.fkPaymentTermBaseId.pkPaymentTermBaseId in ?1")
	void deleteAllByBasePaymentId(@Param("pkPaymentTermBaseId") Integer pkPaymentTermBaseId);

}

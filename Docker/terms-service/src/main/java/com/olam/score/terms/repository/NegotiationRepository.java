package com.olam.score.terms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.olam.score.terms.domain.MasterPaymentNegotiation;

@Repository
public interface NegotiationRepository extends JpaRepository<MasterPaymentNegotiation,Integer>,JpaSpecificationExecutor<MasterPaymentNegotiation>{
	@Modifying
	@Transactional
	@Query("delete from MasterPaymentNegotiation m where m.pkPaymentNegotiationId in ?1")
	void deleteNegotiation(@Param("pkPaymentNegotiationId") List<Integer> pkPaymentNegotiationId);

}

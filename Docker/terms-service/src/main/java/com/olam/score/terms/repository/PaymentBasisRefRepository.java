package com.olam.score.terms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.olam.score.terms.domain.MasterPaymentBasisRef;

@Repository
public interface PaymentBasisRefRepository extends JpaRepository<MasterPaymentBasisRef, Integer> {

}

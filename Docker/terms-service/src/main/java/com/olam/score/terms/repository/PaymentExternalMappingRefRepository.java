package com.olam.score.terms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.olam.score.terms.domain.MasterPaymentExternalMapping;

public interface PaymentExternalMappingRefRepository extends JpaRepository<MasterPaymentExternalMapping, Integer>{

}

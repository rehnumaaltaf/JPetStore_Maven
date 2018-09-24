package com.olam.score.finance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.olam.score.finance.domain.TaxRateDetails;

@Repository
public interface TaxRateDetailsRepository extends JpaRepository<TaxRateDetails, Integer> {

	@Query("select m from TaxRateDetails m where m.taxCodeUID=:taxCodeUID")
	public List<TaxRateDetails> rerurnAllByTaxRateUID(@Param("taxCodeUID") int taxCodeUID);

	@Modifying
	@Transactional
	@Query("delete from TaxRateDetails m where m.taxCodeUID=:taxCodeUID")
	public int deleteAllchildTaxRateId(@Param("taxCodeUID") int taxCodeUID);

}

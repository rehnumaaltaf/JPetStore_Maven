package com.olam.score.finance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.olam.score.finance.domain.MasterTaxRate;

@Repository
public interface TaxRateRepository extends JpaRepository<MasterTaxRate, Integer> {
	
	
	@Transactional
	@Query("select count(m.taxCode) from MasterTaxRate m where m.taxCode =?1")
	public int uniqueTaxCode(@Param("taxCode") String taxCode);
	
	@Transactional
	@Query("select count(m.taxCode) from MasterTaxRate m where m.taxCode =?1 and m.taxCodeUID!=?2 ")
	public int uniqueTaxCodeForUpdate(@Param("taxCode") String taxCode, @Param("taxCodeUID") int taxCodeUID);
	
	@Transactional
	@Query("select count(m.taxCode) from MasterTaxRate m where m.taxName =?1 and m.taxCodeUID!=?2 ")
	public int uniqueTaxNameForUpdate(@Param("taxName") String taxName, @Param("taxCodeUID") int taxCodeUID);
	
	@Transactional
	@Query("select count(m.taxName) from MasterTaxRate m where m.taxName =?1")
	public int uniqueTaxName(@Param("taxName") String taxName);

	@Modifying
	@Transactional
	@Query("UPDATE MasterTaxRate m SET m.status= :status WHERE m.taxCodeUID= :taxCodeUID")
	public int deactivateOrReactivateTaxRate(@Param("status") int status,@Param("taxCodeUID") int taxCodeUID);

}

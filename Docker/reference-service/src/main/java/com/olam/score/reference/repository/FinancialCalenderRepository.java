package com.olam.score.reference.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.olam.score.reference.domain.MasterFinancialCalender;


@Repository
public interface FinancialCalenderRepository extends JpaRepository<MasterFinancialCalender, Integer> {
	
	
	@Query("select mf from MasterFinancialCalender mf where mf.finCalFiscalYear=:finCalFiscalYear")
	public MasterFinancialCalender getFisCalYear(@Param("finCalFiscalYear") String finCalFiscalYear);
	
	@Query("select mf.pkFinCalId from MasterFinancialCalender mf where mf.finCalFiscalYear=:finCalFiscalYear")
	public Integer getPkFinCalId(@Param("finCalFiscalYear") String finCalFiscalYear);
	
	@Query("select mfc from MasterFinancialCalender mfc order by mfc.pkFinCalId desc")
	public List<MasterFinancialCalender> getAllFinCal();
	@Query("select mf.finCalFiscalYear from MasterFinancialCalender mf where mf.finCalFiscalYear like CONCAT(:finCalFiscalYear,'%')")
	List<String> getFiscalYearSuggitions(@Param("finCalFiscalYear")String finCalFiscalYear);
	
	@Query("select mfc from MasterFinancialCalender mfc where mfc.pkFinCalId=:id")
	public MasterFinancialCalender getValuesById(@Param("id") Integer id);
	
	@Query("select mfc from MasterFinancialCalender mfc where mfc.pkFinCalId=:id")
	public List<MasterFinancialCalender> getValuesByIdList(@Param("id") Integer id);
	
	
	
	
	
}

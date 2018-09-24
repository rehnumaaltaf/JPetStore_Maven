package com.olam.score.reference.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.olam.score.reference.domain.MasterFinancialCalenderMapping;



@Repository
public interface FinancialCalenderMappingRepository  extends JpaRepository<MasterFinancialCalenderMapping, Integer>{
	
	@Query("select mfm.finCalMappingMonthName from MasterFinancialCalenderMapping mfm where mfm.fkFinCalId=:fkFinCalId")
	public String getFisCalMonth(@Param("fkFinCalId") Integer fkFinCalId);
	
	@Query("select mfm from MasterFinancialCalenderMapping mfm where mfm.fkFinCalId=:fkFinCalId")
	public List<MasterFinancialCalenderMapping> getFisCalFkId(@Param("fkFinCalId") Integer fkFinCalId);
	
	
	@Query("select mfm from MasterFinancialCalenderMapping mfm where mfm.fkFinCalId=:fkFinCalId")
	public MasterFinancialCalenderMapping getFinCalMapping(@Param("fkFinCalId") Integer fkFinCalId);
	
	@Query("delete from MasterFinancialCalenderMapping mfm where mfm.fkFinCalId=:fkFinCalId")
	@Modifying
	public void deleteByFinCalId(@Param("fkFinCalId") Integer fkFinCalId);

}

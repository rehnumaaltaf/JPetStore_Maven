package com.olam.score.reference.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.olam.score.reference.domain.MasterCurrency;

public interface CurrencyRepository extends JpaRepository<MasterCurrency, Integer>{
	
	@Query("select mc.currencyName from MasterCurrency mc where mc.currencyName like CONCAT(:currencyName,'%')")
	List<String> suggestCurrName(@Param("currencyName")String currencyName);
	
	@Query("select mc.currencyCode from MasterCurrency mc where mc.currencyCode like CONCAT(:currencyCode,'%')")
	List<String> suggestCurrCode(@Param("currencyCode")String currencyCode);
	
	@Query("select count(mc.currencyName) from MasterCurrency mc where mc.currencyName =?1")
	int uniqueCurrName(@Param("currencyName")String currencyName);
	
	@Query("select count(mc.currencyCode) from MasterCurrency mc where mc.currencyCode =?1")
	int uniqueCurrCode(@Param("currencyCode")String currencyCode);
	
	public MasterCurrency getByPkCurrencyId(int id);
	
	@Query("select mc from MasterCurrency mc where mc.pkCurrencyId = :id")
	public MasterCurrency getCurrencyById(@Param("id")Integer id);
	
	@Query("select mc from MasterCurrency mc where mc.fkStatusId=:fkStatusId")
	public List<MasterCurrency> getActiveCurrency(@Param("fkStatusId") int fkStatusId);
	
	@Query("select mfc from MasterCurrency mfc order by mfc.pkCurrencyId desc")
	public List<MasterCurrency> getAllCurrency();
	
	List<MasterCurrency> getByCurrencyCodeAndPkCurrencyIdNotIn(String code,int id);
	List<MasterCurrency> getByCurrencyNameAndPkCurrencyIdNotIn(String name,int id);
	
}

package com.olam.score.forex.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.olam.score.forex.domain.MasterForex;
import com.olam.score.forex.domain.MasterForexFrequency;



@Repository
public interface ForexRepository extends JpaRepository<MasterForex, Integer>,CrudRepository<MasterForex,Integer>{
	
	
	@Query("SELECT f.forexName FROM MasterForex f WHERE f.forexName like :forexName" )
	public List<String> findByforexNameKeying(@Param("forexName") String forexName);
	
	@Query("SELECT f.forexDesc FROM MasterForex f WHERE f.forexDesc like :forexDesc" )
	public List<String> findByforexDescKeying(@Param("forexDesc") String forexDesc);
	
	@Query("SELECT count(f) FROM MasterForex f WHERE f.fkBaseCurrencyId = :fkBaseCurrencyId and f.fkCounterCurrencyId = :fkCounterCurrencyId" )
    public int findByCurrency(@Param("fkBaseCurrencyId") int fbBaseCurrencyId,@Param("fkCounterCurrencyId") int fkCounterCurrencyId );
	
	
	@Query("select s from MasterForexFrequency s where s.pkForexFrequencyId =:pkForexFrequencyId")
	public MasterForexFrequency findByFrequencyId(@Param("pkForexFrequencyId") int pkForexFrequencyId);
	
	
	@Query("select count(f) from MasterForex f where upper(f.forexName)=upper(:forexName)")
	public Integer getByName(@Param("forexName") String forexName);
	
	@Query("select count(f) from MasterForex f where upper(f.forexCode)=upper(:forexCode)")
	public Integer getByCode(@Param("forexCode") String forexCode);
	
	public MasterForex getByForexCode(String code);
	
	public MasterForex getByForexName(String name);
	
	public List<MasterForex> findAllByOrderByCreatedDateDesc();
	
	public MasterForex getByPkForexId(int id);
	
	
	@Modifying
	@Transactional
	@Query("UPDATE MasterForex f SET f.fkStatusId= :fkStatusId WHERE f.pkForexId= :pkForexId")
	public int deactivateOrReactivateForex(@Param("fkStatusId") int fkStatusId,@Param("pkForexId") int pkForexId);
	
	@Modifying
	@Transactional
	@Query("UPDATE MasterForexForwardTenor f SET f.fkStatusId= :fkStatusId WHERE f.fkForexId= :fkForexId")
	public int deactivateOrReactivateForexForwarTenor(@Param("fkStatusId") int fkStatusId,@Param("fkForexId") MasterForex fkForexId);
	
	
	@Modifying
	@Transactional
	@Query("DELETE FROM MasterForexForwardTenor f WHERE f.fkForexId= :fkForexId")
	public int deleteForexForwarTenor(@Param("fkForexId") MasterForex fkForexId);
	
}

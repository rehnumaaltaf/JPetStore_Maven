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
import com.olam.score.forex.domain.MasterForexForwardTenor;


@Repository
public interface ForexForwardTenorRepository extends JpaRepository<MasterForexForwardTenor, Integer>,CrudRepository<MasterForexForwardTenor, Integer>{
	
	
	public List<MasterForexForwardTenor> getByFkForexId(MasterForex id);
	
	public MasterForexForwardTenor getByPkForexForwardTenorId(int id);
	
	@Modifying
	@Transactional
	@Query("UPDATE MasterForexForwardTenor f SET f.fkStatusId= :fkStatusId WHERE f.fkForexId= :fkForexId")
	public int deactivateOrReactivateForexForwardTenor(@Param("fkStatusId") int fkStatusId,@Param("fkForexId") MasterForex fkForexId);
	
	
}

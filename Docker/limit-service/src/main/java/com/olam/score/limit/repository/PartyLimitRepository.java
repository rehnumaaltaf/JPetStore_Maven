package com.olam.score.limit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.olam.score.limit.domain.MasterCounterPartyLimit;

@Repository
public interface PartyLimitRepository extends JpaRepository<MasterCounterPartyLimit, Integer>,JpaSpecificationExecutor<MasterCounterPartyLimit>{

	@Query(value = "select counterPartyLimit from MasterCounterPartyLimit counterPartyLimit where "
			+ " counterPartyLimit.internalPartyId = :internalPartyId",nativeQuery=false)
	List<MasterCounterPartyLimit> findByCounterPartyId(@Param("internalPartyId") Integer internalPartyId);

}

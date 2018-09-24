package com.olam.score.party.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.olam.score.party.domain.MasterParty;

@Repository
public interface PartyMasterRepository extends JpaRepository<MasterParty, Integer>,JpaSpecificationExecutor<MasterParty>{

	@Query(value = "select party from MasterParty party where "
			+ " party.partyName = :partyName",nativeQuery=false)
	public MasterParty findByPartyName(@Param("partyName") String partyName);
	
	@Query(value = "select party from MasterParty party where "
            + " party.partyCode = :partyCode",nativeQuery=false)
	public MasterParty findByPartyCode(@Param("partyCode") String customerCode);

}

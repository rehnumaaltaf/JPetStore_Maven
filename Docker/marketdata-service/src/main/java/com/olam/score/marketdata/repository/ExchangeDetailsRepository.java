package com.olam.score.marketdata.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.olam.score.marketdata.domain.MasterExchange;

@Repository
public interface ExchangeDetailsRepository extends JpaRepository<MasterExchange, Integer>,JpaSpecificationExecutor<MasterExchange>  {

	@Query(value = "select exchange from MasterExchange exchange where "
			+ " exchange.fkParentPartyId = :partyId",nativeQuery=false)
	public List<MasterExchange> findByPartyId(@Param("partyId") Integer partyId);
	
}

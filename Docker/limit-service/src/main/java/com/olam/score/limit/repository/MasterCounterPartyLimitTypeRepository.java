package com.olam.score.limit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.olam.score.limit.domain.MasterCounterPartyLimitType;

@Repository
public interface MasterCounterPartyLimitTypeRepository extends JpaRepository<MasterCounterPartyLimitType, Integer>{

}

package com.olam.score.reference.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.olam.score.reference.domain.MasterMarketingDesk;

@Repository
public interface MarketingDeskRepository extends JpaRepository<MasterMarketingDesk, Integer>{

}

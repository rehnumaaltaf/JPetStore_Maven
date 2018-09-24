package com.olam.score.party.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.olam.score.party.domain.MasterAccountType;

@Repository
public interface MasterAccountTypeRepository extends JpaRepository<MasterAccountType, Integer>{

}

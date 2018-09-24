package com.olam.score.common.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.olam.score.common.domain.MasterStatus;




@Repository
public interface Statusrepository extends JpaRepository<MasterStatus, Integer>{
	
	
}


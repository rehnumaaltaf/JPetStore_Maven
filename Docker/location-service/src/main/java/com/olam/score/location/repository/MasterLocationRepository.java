package com.olam.score.location.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.olam.score.location.domain.MasterLocation;

@Repository
public interface MasterLocationRepository extends JpaRepository<MasterLocation, Integer> {

}

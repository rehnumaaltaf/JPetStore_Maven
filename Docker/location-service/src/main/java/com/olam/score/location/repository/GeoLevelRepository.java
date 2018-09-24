package com.olam.score.location.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.olam.score.location.domain.MasterGeographyLevel;

@Repository

public interface GeoLevelRepository extends JpaRepository<MasterGeographyLevel, Integer> {

}

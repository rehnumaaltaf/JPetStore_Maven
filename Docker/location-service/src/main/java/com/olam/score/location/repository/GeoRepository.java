package com.olam.score.location.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.olam.score.location.domain.MasterGeography;

@Repository
public interface GeoRepository extends JpaRepository<MasterGeography, Integer>,JpaSpecificationExecutor<MasterGeography> {

	@Query("select s from MasterGeography s where upper(s.geoName) = ?1")
	MasterGeography findByGeographyName(@Param("geoName") String geoName);

	@Query("select s from MasterGeography s where upper(s.geoCode) = ?1")
	MasterGeography findByGeoCode(@Param("geoCode") String geoCode);
}

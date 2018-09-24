package com.olam.score.location.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.olam.score.location.domain.MasterAddressTypeRef;

@Repository
public interface AddressTypeRepository extends JpaRepository<MasterAddressTypeRef, Integer> {

}

package com.olam.score.limit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.olam.score.limit.domain.MasterLimitAttributeRef;

public interface LimitAttributeRefRepository extends JpaRepository<MasterLimitAttributeRef, Integer> {

	MasterLimitAttributeRef findByLimitAttributeName(String string);

}

package com.olam.score.limit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.olam.score.limit.domain.MasterLimitDetail;
import com.olam.score.limit.domain.MasterLimitDetailAttribute;

public interface LimitAttributeDetailRepository extends JpaRepository<MasterLimitDetailAttribute, Integer> {

	@Modifying
	@Transactional
	@Query("delete from MasterLimitDetailAttribute m where m.fkLimitDetailId=?1")
	void deleteByFkLimitDetailId(@Param("fkLimitDetailId") MasterLimitDetail limitDetail);
}

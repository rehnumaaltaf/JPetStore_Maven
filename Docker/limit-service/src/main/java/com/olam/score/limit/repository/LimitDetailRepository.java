package com.olam.score.limit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.olam.score.limit.domain.MasterLimit;
import com.olam.score.limit.domain.MasterLimitDetail;

public interface LimitDetailRepository extends JpaRepository<MasterLimitDetail, Integer> {

	@Modifying
	@Transactional
	@Query("delete from MasterLimitDetail m where m.fkLimitId=?1")
	void deleteByFkLimitId(@Param("fkLimitId") MasterLimit limit);
}

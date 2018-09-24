package com.olam.score.common.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.olam.score.common.domain.MasterCustomViewDetail;

@Repository
public interface CommonRepository extends JpaRepository<MasterCustomViewDetail, Integer>{
	@Query("select s from MasterCustomViewDetail s where s.fkCustomViewId.fkEntityId = ?1 and s.fkCustomViewId.customViewIsDefault=?2 order by s.customViewDetailOrder")
	List<MasterCustomViewDetail> findDefaultView(@Param("featureId") Integer featureId,@Param("isDefault") String isDefault);

	@Query("select s from MasterCustomViewDetail s where s.fkCustomViewId.fkEntityId =?1 and s.fkCustomViewId.pkCustomViewId=?2")
	List<MasterCustomViewDetail> findByEntityId(@Param("featureId") Integer featureId,@Param("viewId") Integer viewId);

}


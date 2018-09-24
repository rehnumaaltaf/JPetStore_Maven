package com.olam.score.reference.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.olam.score.reference.domain.MasterHolidayCalender;

@Repository
public interface HolidayCalendarRepository extends JpaRepository<MasterHolidayCalender, Integer>,JpaSpecificationExecutor<MasterHolidayCalender> {

	/*@Query("select s from MasterHolidayCalender s where s.holidayCalCode = ?1")
	MasterHolidayCalender findByHolidayCalCode(@Param("holidayCalCode") String holidayCalCode);*/

	@Query("select s.holidayCalName from MasterHolidayCalender s where s.holidayCalName = ?1")
	String findByHolidayCalName(@Param("holidayCalName") String holidayCalName);

	@Query("select s.holidayCalCode from MasterHolidayCalender s where s.holidayCalCode = ?1")
	String findByHolidayCalCode(@Param("holidayCalCode") String holidayCalCode);
	
	@Transactional
	  @Modifying
	@Query("delete from MasterHolidayList m where m.pkHolidayListId = ?1")
	void deleteByHolidayListId(@Param("pkHolidayListId") Integer pkHolidayListId);
	
	//for edit duplicate check
		@Query("SELECT new com.olam.score.reference.domain.MasterHolidayCalender(d.pkHolidayCalId, d.holidayCalName, d.holidayCalCode) FROM MasterHolidayCalender d where d.holidayCalName = :holidayCalName")
		List<MasterHolidayCalender> findByholidayCalNameandId(@Param("holidayCalName") String holidayCalName);
		
		@Query("SELECT new com.olam.score.reference.domain.MasterHolidayCalender(d.pkHolidayCalId, d.holidayCalName, d.holidayCalCode) FROM MasterHolidayCalender d where d.holidayCalCode = :holidayCalCode")
		List<MasterHolidayCalender> findByholidayCalCodeandId(@Param("holidayCalCode") String holidayCalCode);
}

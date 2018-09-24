package com.olam.score.reference.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.olam.score.reference.domain.MasterCalendar;

@Repository

public interface CalendarRepository extends JpaRepository<MasterCalendar, Integer> {

	@Transactional
	@Query("select count(m.calendarCode) from MasterCalendar m where m.calendarCode =?1")
	public int uniqueCalendarCode(@Param("calendarCode") String calendarCode);

	@Transactional
	@Query("select count(m.calendarName) from MasterCalendar m where m.calendarName =?1")
	public int uniqueCalendarName(@Param("calendarName") String calendarName);

	@Modifying
	@Transactional
	@Query("UPDATE MasterCalendar m SET m.status= :status WHERE m.calendarSetupId= :calendarSetupId")
	public int deactivateOrReactivateCalendar(@Param("status") int status,
			@Param("calendarSetupId") int calendarSetupId);
	
	@Transactional
	@Query("select count(m.calendarCode) from MasterCalendar m where m.calendarCode =?1 and m.calendarSetupId!=?2 ")
	public int uniqueCalendarCodeForUpdate(@Param("calendarCode") String calendarCode, @Param("calendarSetupId") int calendarSetupId);
	
	@Transactional
	@Query("select count(m.calendarName) from MasterCalendar m where m.calendarName =?1 and m.calendarSetupId!=?2 ")
	public int uniqueCalendarNameForUpdate(@Param("taxName") String taxName, @Param("calendarSetupId") int calendarSetupId);
	@Transactional
	@Query("select m from MasterCalendar m where m.calendarTypeId =:calendarTypeId")
	public List<MasterCalendar> futureCalendar(@Param("calendarTypeId") int calendarTypeId);
}

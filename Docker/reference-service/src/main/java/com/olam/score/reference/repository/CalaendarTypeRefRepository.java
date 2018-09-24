package com.olam.score.reference.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.olam.score.reference.domain.MasterCalendarTypeRef;
@Repository
public interface CalaendarTypeRefRepository extends JpaRepository<MasterCalendarTypeRef, Integer> {
	@Modifying
	@Transactional
	@Query("UPDATE MasterCalendarTypeRef m SET m.fkStatusId= :fkStatusId WHERE m.calendarTypeRefId= :calendarTypeRefId")
	public int deactivateOrReactivateCalendarTypeRef(@Param("fkStatusId") int fkStatusId,@Param("calendarTypeRefId") int calendarTypeRefId);
}

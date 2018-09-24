package com.olam.score.reference.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.olam.score.reference.domain.MasterShipmentRule;
import com.olam.score.reference.domain.MasterShipmentWeekTimeframe;

@Repository
public interface ShipmentWeekRepository  extends JpaRepository<MasterShipmentWeekTimeframe, Integer>,JpaSpecificationExecutor<MasterShipmentWeekTimeframe> {

	@Query("select s.shipmentTimeframeName from MasterShipmentWeekTimeframe s where s.shipmentTimeframeName = ?1")
	String findByShipmentWeekName(@Param("shipmentTimeframeName") String shipmentTimeframeName);

	@Query("select s.shipmentTimeframeCode from MasterShipmentWeekTimeframe s where s.shipmentTimeframeCode = ?1")
	String findByShipmentWeekCode(@Param("shipmentTimeframeCode") String shipmentTimeframeCode);
	
	@Query("select s from MasterShipmentRule s")
	List<MasterShipmentRule> getAllShipmentRule();
	
	@Query("select s from MasterShipmentRule s where s.pkShipmentRuleId = ?1")
	MasterShipmentRule findByShipmentRuleId(@Param("pkShipmentRuleId") Integer pkShipmentRuleId);
	
	//duplicate check for edit
	@Query("SELECT new com.olam.score.reference.domain.MasterShipmentWeekTimeframe(d.pkShipmentWeekTimeframeId, d.shipmentTimeframeName, d.shipmentTimeframeCode) FROM MasterShipmentWeekTimeframe d where d.shipmentTimeframeName = :shipmentTimeframeName")
	List<MasterShipmentWeekTimeframe> findByShipmentWeekNameEdit(@Param("shipmentTimeframeName") String shipmentTimeframeName);
	
	@Query("SELECT new com.olam.score.reference.domain.MasterShipmentWeekTimeframe(d.pkShipmentWeekTimeframeId, d.shipmentTimeframeName, d.shipmentTimeframeCode) FROM MasterShipmentWeekTimeframe d where d.shipmentTimeframeCode = :shipmentTimeframeCode")
	List<MasterShipmentWeekTimeframe> findByShipmentWeekCodeEdit(@Param("shipmentTimeframeCode") String shipmentTimeframeCode);
	
	
}

package com.olam.score.product.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.olam.score.product.domain.MasterOrigin;
//import com.olam.score.product.domain.MasterStatus;
import com.olam.score.product.domain.MasterOutturnRawGrade;

public interface OriginRepository extends JpaRepository<MasterOrigin,Integer>,JpaSpecificationExecutor<MasterOrigin>{
	/*@Query("select s from MasterStatus s where s.statusName = ?1")
	MasterStatus findByStatusname(@Param("statusName") String statusName);*/
	
	@Query("select s.originCode from MasterOrigin s where s.originCode = ?1")
	String findByOriginCode(@Param("originCode") String originCode);
	
	@Query("select s.originName from MasterOrigin s where s.originName = ?1")
	String findByOriginName(@Param("originName") String originName);
	
	@Query("select s.pkOriginId from MasterOrigin s where s.originName = ?1")
	Integer findOriginIdByName(@Param("originName") String originName);
	
	@Query("select s.pkOriginId from MasterOrigin s where s.originCode = ?1")
	Integer findOriginIdByCode(@Param("originCode") String originCode);
}

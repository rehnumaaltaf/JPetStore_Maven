

package com.olam.score.finance.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.olam.score.finance.domain.MasterExternalSystemRef;
import com.olam.score.finance.domain.MasterGl;
import com.olam.score.finance.domain.MasterGlExternalMapping;
import com.olam.score.finance.domain.MasterGlTypeRef;

@Repository
public interface GLRepository extends JpaRepository<MasterGl, Integer>{
	/*@Query("select s from MasterStatus s where s.statusName = ?1")
	  MasterStatus findByStatusname(@Param("statusName") String statusName);

	@Query("select s from MasterParty s where s.partyName = ?1")
	  MasterParty findByPartyname(@Param("partyName") String partyName);*/
	
	@Query("select s from MasterGl s where s.glName = ?1")
	  MasterGl findByParentGLName(@Param("glName") String glName);
	
	/*@Query("select s from MasterExternalSystemRef s where s.externalSystemRefName = ?1")
	  MasterExternalSystemRef findByExtRefName(@Param("externalSystemRefName") String externalSystemRefName);*/
	
	@Query("select s from MasterExternalSystemRef s where s.pkExternalSystemRefId = ?1")
	  MasterExternalSystemRef findByExtRefId(@Param("externalSystemRefId") Integer externalSystemRefId);
	
	@Query("select s.glName from MasterGl s where s.glName = ?1")
	String findByGLName(@Param("glName") String glName);
	
	@Query("select s.glCode from MasterGl s where s.glCode = ?1")
	  String findByGLCode(@Param("glCode") String glCode);
	
	/*@Query("select s from MasterGl s where s.fkParentGlId = ?1")
	  List<MasterGl> findByGLIdIsParent(@Param("fkParentGlId") MasterGl parentGlId);*/

	@Query("select s.pkGlId from MasterGl s where s.fkParentGlId.pkGlId = ?1")
	List<Integer> findByParentGl(@Param("fkParentGlId") Integer glId);
	
	@Query("select s.pkGlExternalMappingId from MasterGlExternalMapping s where s.fkGlId.pkGlId = ?1")
	Integer findExternalGlMappingId(@Param("fkGlId") Integer glId);
	
	//for edit
	@Query("select s.pkGlId from MasterGl s where s.glName = ?1")
	  Integer findGLIDGLName(@Param("glName") String glName);
	@Query("select s.pkGlId from MasterGl s where s.glCode = ?1")
	Integer findGLIDGLCode(@Param("glCode") String glCode);
	
	//getTypeId
	/*@Query("select s.pkGlTypeRefId from MasterGlTypeRef s where s.glTypeRefName = ?1")
	Integer findByGlTypename(@Param("statusName") String glTypeName);*/
	/*@Query("select s from MasterGlTypeRef s where s.glTypeRefName = ?1")
	MasterGlTypeRef findByGlTypename(@Param("glTypeName") String glTypeName);*/
	
	@Query("select s from MasterGlTypeRef s where s.pkGlTypeRefId = ?1")
	MasterGlTypeRef findByGlTypeId(@Param("getGlTypeRefId") Integer getGlTypeRefId);
	
	@Query("select s from MasterGlTypeRef s")
	List<MasterGlTypeRef> getAllTypeReference();
	
	@Query("select s from MasterExternalSystemRef s")
	List<MasterExternalSystemRef> getAllExternalSystemRef();

    @Query("select s from MasterGl s where s.glIsGroup = ?1")
	List<MasterGl> getAllParentGl(@Param("glIsGroup") String glIsGroup);
	
    
/*    @Query("delete s from MasterGlExternalMapping s where s.fkGlId = ?1")
	void deleteAllExtMappingOfGlId(@Param("glId") Integer glId);*/
    
}
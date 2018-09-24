package com.olam.score.terms.repository;



import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.olam.score.terms.domain.MasterWeightTerms;

@Repository
public interface WeightTermsRepository extends JpaRepository<MasterWeightTerms, Integer>,JpaSpecificationExecutor<MasterWeightTerms> {
	
	/*@Query("select s.weightTermsCode from MasterWeightTerms s where s.weightTermsCode = ?1")
	String findByWeightTermsCode(@Param("weightTermsCode") String weightTermsCode);
	
	@Query("select s.weightTermsName from MasterWeightTerms s where s.weightTermsName = ?1")
	String findByWeightTermsName(@Param("weightTermsName") String weightTermsName);
*/

	@Query("select s from MasterWeightTerms s where s.weightTermsCode = ?1")
	MasterWeightTerms findByWeightTermsCode(@Param("weightTermsCode") String weightTermsCode);
	
	@Query("select s from MasterWeightTerms s where s.weightTermsName = ?1")
	MasterWeightTerms findByWeightTermsName(@Param("weightTermsName") String weightTermsName);
	
	
	@Transactional
	@Modifying
	@Query("delete from MasterWeightTerms m where m.pkWeightTermsId = ?1")
	void deleteByWeightTermsListId(@Param("pkWeightTermsId") Integer pkWeightTermsId); 
	
	/*//for edit duplicate check
			@Query("SELECT new com.olam.score.terms.domain.MasterWeightTerms(d.pkWeightTermsId, d.weightTermsName, d.weightTermsCode) FROM MasterWeightTerms d where d.weightTermsName = :weightTermsName")
			MasterWeightTerms findByweightTermsNameandId(@Param("weightTermsName") String weightTermsName);
			
			@Query("SELECT new com.olam.score.terms.domain.MasterWeightTerms(d.pkWeightTermsId, d.weightTermsName, d.weightTermsCode) FROM MasterWeightTerms d where d.weightTermsCode = :weightTermsCode")
			MasterWeightTerms findByweighttermsCodeandId(@Param("weightTermsCode") String weightTermsCode);

*/
	
	

	
	
}


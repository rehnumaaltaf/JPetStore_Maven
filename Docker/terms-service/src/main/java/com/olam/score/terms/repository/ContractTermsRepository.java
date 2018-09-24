package com.olam.score.terms.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.olam.score.terms.domain.MasterContrTrmsAddreduceRef;
import com.olam.score.terms.domain.MasterContractTerms;
import com.olam.score.terms.domain.MasterContractTermsBaseRef;
import com.olam.score.terms.domain.MasterContractTermsBasisRef;

@Repository
public interface ContractTermsRepository extends JpaRepository<MasterContractTerms,Integer>,JpaSpecificationExecutor<MasterContractTerms> {

	@Query("select s from MasterContractTermsBaseRef s")
	List<MasterContractTermsBaseRef> getAllContractTermsBaseRef();

	@Query("select s from MasterContractTermsBasisRef s")
	List<MasterContractTermsBasisRef> getAllContractTermsBasisRef();

	@Query("select s from MasterContrTrmsAddreduceRef s")
	List<MasterContrTrmsAddreduceRef> getAllContrTrmsAddreduceRef();

	@Query("select s.contractTermsName from MasterContractTerms s where s.contractTermsName = ?1")
	String findByContractTermsName(@Param("contractTermsName") String contractTermsName);

	@Query("select s.contractTermsCode from MasterContractTerms s where s.contractTermsCode = ?1")
	String findByContractTermsCode(@Param("contractTermsCode") String contractTermsCode);

	@Query("select s from MasterContrTrmsAddreduceRef s where s.pkContrTrmsAddreduceRefId = ?1")
	MasterContrTrmsAddreduceRef findAddreduce(@Param("contrTrmsAddreduceRefId") Integer contrTrmsAddreduceRefId);

	@Query("select s from MasterContractTermsBaseRef s where s.pkContractTermsBaseRefId = ?1")
	MasterContractTermsBaseRef findBaseRef(@Param("contractTermsBaseRefId") Integer contractTermsBaseRefId);

	@Query("select s from MasterContractTermsBasisRef s where s.pkContractTermsBasisRefId = ?1")
	MasterContractTermsBasisRef findBasisRef(@Param("contractTermsBaseRefId") Integer contractTermsBaseRefId);

	//for deleting child entries
	@Transactional
	@Modifying
	@Query("delete from MasterContractTermsPurchase m where m.pkContractTermsPurchaseId = ?1")
	void deleteByPurchaseId(@Param("contractTermsPurchaseId") Integer contractTermsPurchaseId);

	@Transactional
	@Modifying
	@Query("delete from MasterContractTermsSales m where m.pkContractTermsSalesId = ?1")
	void deleteBySalesId(@Param("contractTermsSalesId") Integer contractTermsSalesId);

	//for edit duplicate check
	@Query("SELECT new com.olam.score.terms.domain.MasterContractTerms(d.pkContractTermsId, d.contractTermsName, d.contractTermsCode) FROM MasterContractTerms d where d.contractTermsName = :contractTermsName")
	List<MasterContractTerms> findByContractTermsNameandId(@Param("contractTermsName") String contractTermsName);
	
	@Query("SELECT new com.olam.score.terms.domain.MasterContractTerms(d.pkContractTermsId, d.contractTermsName, d.contractTermsCode) FROM MasterContractTerms d where d.contractTermsCode = :contractTermsCode")
	List<MasterContractTerms> findByContractTermsCodeandId(@Param("contractTermsCode") String contractTermsCode);
}

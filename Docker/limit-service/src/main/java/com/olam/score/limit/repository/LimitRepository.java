package com.olam.score.limit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.olam.score.limit.domain.MasterLimit;

public interface LimitRepository extends JpaRepository<MasterLimit, Integer>, JpaSpecificationExecutor<MasterLimit> {

	/*
	 * @Query(value = "select grade from MasterLimit grade" +
	 * " where grade.gradeName = :gradeName", nativeQuery = false) MasterLimit
	 * findByName(@Param("gradeName") String gradeName);
	 * 
	 * @Query(value = "select grade from MasterLimit grade" +
	 * " where grade.gradeCode = :gradeCode", nativeQuery = false) MasterLimit
	 * findByCode(@Param("gradeCode") String gradeCode);
	 */
	@Query(value = "select limit from MasterLimit limit where limit.fkPrimaryLimitBasisTypeId = :fkPrimaryLimitBasisTypeId and limit.primaryLimitBasisId = :primaryLimitBasisId", nativeQuery = false) MasterLimit
			findByBasis(@Param("fkPrimaryLimitBasisTypeId") Integer fkPrimaryLimitBasisTypeId,@Param("primaryLimitBasisId")  Integer primaryLimitBasisId);

	@Query(value = "select limit from MasterLimit limit where limit.fkPrimaryLimitBasisTypeId = :fkPrimaryLimitBasisTypeId and limit.primaryLimitBasisId = :primaryLimitBasisId and limit.primaryLimitBasisId = :fkAdditionalLmtBasisTypeId and limit.primaryLimitBasisId = :additionalLimitBasisId", nativeQuery = false) MasterLimit
	findByBasisAdditionalbasis(@Param("fkPrimaryLimitBasisTypeId") Integer fkPrimaryLimitBasisTypeId,@Param("primaryLimitBasisId")  Integer primaryLimitBasisId, @Param("fkAdditionalLmtBasisTypeId") Integer fkAdditionalLmtBasisTypeId,@Param("additionalLimitBasisId")  Integer additionalLimitBasisId);

}

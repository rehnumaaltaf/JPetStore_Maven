package com.olam.score.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.olam.score.product.domain.MasterBlendInputCertification;
import com.olam.score.product.domain.MasterBlendTemplate;

public interface BlendInputCertRepository extends JpaRepository<MasterBlendInputCertification, Integer>{
	
	int deleteByMasterBlendInputCertAssoc(MasterBlendTemplate masterBlendTemplate);
	
	@Query("select  mc from MasterBlendInputCertification mc where mc.masterBlendInputCertAssoc =?1")
	List<MasterBlendInputCertification> getcertid(MasterBlendTemplate masterBlendTemplate);
	
	int deleteByMasterBlendInputCertAssocAndPkBlendInputCertificationIdNotIn(MasterBlendTemplate masterBlendTemplate,List<Integer> pkBlendInputCertList);

	/*@Modifying
	@Transactional
	@Query("delete from MasterBlendInputCertification ml where ml.masterBlendInputCertAssoc.pkBlendInputCertificationId =:pkBlendTemplateId") 
	int deleteAssocId(@Param("pkBlendTemplateId") Integer pkBlendTemplateId);*/
	
}

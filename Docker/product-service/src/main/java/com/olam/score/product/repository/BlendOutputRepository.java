package com.olam.score.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.olam.score.product.domain.MasterBlendOutput;
import com.olam.score.product.domain.MasterBlendTemplate;

public interface BlendOutputRepository extends JpaRepository<MasterBlendOutput, Integer>{
	
	int deleteByMasterBlendTemplateAssocAndPkBlendOutputIdNotIn(MasterBlendTemplate masterBlendTemplate,List<Integer> pkBlendOutputList);

	int deleteByMasterBlendTemplateAssoc(MasterBlendTemplate masterBlendTemplate);

}

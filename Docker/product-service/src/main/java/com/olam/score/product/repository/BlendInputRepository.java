package com.olam.score.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.olam.score.product.domain.MasterBlendInput;
import com.olam.score.product.domain.MasterBlendTemplate;


public interface BlendInputRepository extends JpaRepository<MasterBlendInput, Integer>{
	
	int deleteByMasterBlendInputAssocAndPkBlendInputIdNotIn(MasterBlendTemplate masterBlendTemplate,List<Integer> pkBlendInputList);

	int deleteByMasterBlendInputAssoc(MasterBlendTemplate masterBlendTemplate);
	
	

}

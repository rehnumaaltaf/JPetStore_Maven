package com.olam.score.product.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.olam.score.product.domain.MasterBlendTemplate;


public interface BlendTemplateRepository extends JpaRepository<MasterBlendTemplate, Integer> {
	
	@Query("select count(ml.templateCode) from MasterBlendTemplate ml where ml.templateCode =?1")
	int uniqueBlendTemplateCode(@Param("packingMaterialCode")String packingMaterialCode);
	
	@Query("select count(ml.templateName) from MasterBlendTemplate ml where ml.templateName =?1")
	int uniqueBlendTemplateName(@Param("templateName")String templateName);
	
	List<MasterBlendTemplate> getByTemplateCodeAndPkBlendTemplateIdNotIn(String code,int id);
	List<MasterBlendTemplate> getByTemplateNameAndPkBlendTemplateIdNotIn(String name,int id);
	
	@Query(value ="select DISTINCT ga.[FK_ORIGIN_ID] from [MASTER_GRADE_ORIGIN_ASSOC] ga where ga.Fk_GRADE_ID in (SELECT m.[PK_GRADE_ID] FROM [MASTER_GRADE] m where  m.[FK_PROD_ID] =?1)",nativeQuery = true)
	List getDropDownValues(@Param("prodId") int prodId);
	
	@Query("select mfc from MasterBlendTemplate mfc order by mfc.pkBlendTemplateId desc")
	List<MasterBlendTemplate> getAllBlendTemplate();
	
public List<MasterBlendTemplate> findByTemplateNameContaining(String name,Pageable pageable);
	
	public List<MasterBlendTemplate> findByTemplateCodeContaining(String code,Pageable pageable);
	/*@Query(value ="select DISTINCT m.Pk_GRADE_ID,m.[GRADE_NAME] FROM [MASTER_GRADE] m,[MASTER_GRADE_ORIGIN_ASSOC] ga where m.[FK_PROD_ID] =?1 and [FK_ORIGIN_ID]=?2",nativeQuery = true)
	List<Map<Integer,String>> getDropDownValuesForGrade(Integer prodId, Integer origionId);*/
	
	
}

package com.olam.score.location.util;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.olam.score.common.dto.SearchCriteria;
import com.olam.score.location.domain.MasterGeography;

public class GeoSpecification implements Specification<MasterGeography> {
	
    private SearchCriteria criteria;
 
    public GeoSpecification(SearchCriteria criteria){
    	this.criteria=criteria;
    }

	@Override
	public Predicate toPredicate(Root<MasterGeography> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
System.out.println("========root.<String>get(criteria.getKey())================"+root.<String>get(criteria.getKey()));
System.out.println("============criteria.getValue()========================="+criteria.getValue());
        if (root.get(criteria.getKey()).getJavaType() == String.class) {
        	//query.groupBy(root.get("uomName"));
            return builder.like(
              root.<String>get(criteria.getKey()), criteria.getValue() + "%");
        } else {
            return builder.equal(root.get(criteria.getKey()), criteria.getValue());
        }

}


}

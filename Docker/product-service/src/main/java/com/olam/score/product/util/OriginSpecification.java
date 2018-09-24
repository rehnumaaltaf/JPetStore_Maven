package com.olam.score.product.util;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.olam.score.common.dto.SearchCriteria;
import com.olam.score.product.domain.MasterOrigin;

public class OriginSpecification implements Specification<MasterOrigin>{
	
    private SearchCriteria criteria;
 
    public OriginSpecification(SearchCriteria criteria){
    	this.criteria=criteria;
    }

	@Override
	public Predicate toPredicate(Root<MasterOrigin> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

            if (root.get(criteria.getKey()).getJavaType() == String.class) {
            	//query.groupBy(root.get("uomName"));
                return builder.like(
                  root.<String>get(criteria.getKey()), "%" + criteria.getValue() + "%");
            } else {
                return builder.equal(root.get(criteria.getKey()), criteria.getValue());
            }
    
	}
}


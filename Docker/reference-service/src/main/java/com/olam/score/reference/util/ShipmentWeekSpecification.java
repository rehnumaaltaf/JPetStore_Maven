package com.olam.score.reference.util;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.olam.score.common.dto.SearchCriteria;
import com.olam.score.reference.domain.MasterShipmentWeekTimeframe;

public class ShipmentWeekSpecification implements Specification<MasterShipmentWeekTimeframe>{

	private SearchCriteria criteria;
	 
    public ShipmentWeekSpecification(SearchCriteria criteria){
    	this.criteria=criteria;
    }

	@Override
	public Predicate toPredicate(Root<MasterShipmentWeekTimeframe> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

            if (root.get(criteria.getKey()).getJavaType() == String.class) {
            	//query.groupBy(root.get("holidayCalenderName"));
                return builder.like(
                  root.<String>get(criteria.getKey()), "%" + criteria.getValue() + "%");
            } else {
                return builder.equal(root.get(criteria.getKey()), criteria.getValue());
            }
    
	}
}


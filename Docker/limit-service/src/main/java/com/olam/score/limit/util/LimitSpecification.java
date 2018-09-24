package com.olam.score.limit.util;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.olam.score.common.dto.SearchCriteria;
import com.olam.score.limit.domain.MasterLimit;

public class LimitSpecification implements Specification<MasterLimit> {

	private SearchCriteria criteria;

	public LimitSpecification(SearchCriteria criteria) {
		this.criteria = criteria;
	}

	@Override
	public Predicate toPredicate(Root<MasterLimit> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

		if (root.get(criteria.getKey()).getJavaType() == String.class) {
			return builder.like(root.<String> get(criteria.getKey()), "%" + criteria.getValue() + "%");
		} else {
			return builder.equal(root.get(criteria.getKey()), criteria.getValue());
		}

	}
}

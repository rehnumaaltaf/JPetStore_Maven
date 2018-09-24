package com.olam.score.product.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import com.olam.score.common.dto.SearchCriteria;
import com.olam.score.product.domain.MasterGrade;

public class GradeSpecificationsBuilder {

	private final List<SearchCriteria> params;

	public GradeSpecificationsBuilder() {
		params = new ArrayList<>();
	}

	public GradeSpecificationsBuilder with(String key, Object value) {
		params.add(new SearchCriteria(key, value));
		return this;
	}

	@SuppressWarnings("unchecked")
	public Specification<MasterGrade> build() {
		if (params.isEmpty()) {
			return null;
		}

		List<Specification<?>> specs = new ArrayList<>();
		for (SearchCriteria param : params) {
			specs.add(new ProductSpecification(param));
		}

		Specification<MasterGrade> result = (Specification<MasterGrade>) specs.get(0);
		for (int i = 1; i < specs.size(); i++) {
			result = Specifications.where(result).and((Specification<MasterGrade>) specs.get(i));
		}
		return result;
	}

}

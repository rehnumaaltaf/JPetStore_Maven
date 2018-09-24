package com.olam.score.limit.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import com.olam.score.common.dto.SearchCriteria;
import com.olam.score.limit.domain.MasterLimit;

public class LimitSpecificationsBuilder {

	private final List<SearchCriteria> params;

	public LimitSpecificationsBuilder() {
		params = new ArrayList<>();
	}

	public LimitSpecificationsBuilder with(String key, Object value) {
		params.add(new SearchCriteria(key, value));
		return this;
	}

	@SuppressWarnings("unchecked")
	public Specification<MasterLimit> build() {
		if (params.isEmpty()) {
			return null;
		}

		List<Specification<?>> specs = new ArrayList<>();
		for (SearchCriteria param : params) {
			specs.add(new LimitSpecification(param));
		}

		Specification<MasterLimit> result = (Specification<MasterLimit>) specs.get(0);
		for (int i = 1; i < specs.size(); i++) {
			result = Specifications.where(result).and((Specification<MasterLimit>) specs.get(i));
		}
		return result;
	}

}

package com.olam.score.reference.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import com.olam.score.common.dto.SearchCriteria;
import com.olam.score.reference.domain.MasterUom;

public class UomSpecificationsBuilder {
     
    private final List<SearchCriteria> params;
 
    public UomSpecificationsBuilder() {
        params = new ArrayList<>();
    }
 
    public UomSpecificationsBuilder with(String key, Object value) {
        params.add(new SearchCriteria(key, value));
        return this;
    }
 
    @SuppressWarnings("unchecked")
	public Specification<MasterUom> build() {
        if (params.isEmpty()) {
            return null;
        }
 
        List<Specification<?>> specs = new ArrayList<>();
        for (SearchCriteria param : params) {
            specs.add(new UomSpecification(param));
        }
 
		Specification<MasterUom> result = (Specification<MasterUom>) specs.get(0);
        for (int i = 1; i < specs.size(); i++) {
            result = Specifications.where(result).and((Specification<MasterUom>) specs.get(i));
        }
        return result;
    }
}


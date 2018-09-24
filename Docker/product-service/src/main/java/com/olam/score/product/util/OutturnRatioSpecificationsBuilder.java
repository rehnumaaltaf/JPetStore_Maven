package com.olam.score.product.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import com.olam.score.common.dto.SearchCriteria;
import com.olam.score.product.domain.MasterOutturnRawGrade;


public class OutturnRatioSpecificationsBuilder {
     
    private final List<SearchCriteria> params;
 
    public OutturnRatioSpecificationsBuilder() {
        params = new ArrayList<>();
    }
 
    public OutturnRatioSpecificationsBuilder with(String key, Object value) {
        params.add(new SearchCriteria(key, value));
        return this;
    }
 
    @SuppressWarnings("unchecked")
	public Specification<MasterOutturnRawGrade> build() {
        if (params.isEmpty()) {
            return null;
        }
 
        List<Specification<?>> specs = new ArrayList<>();
        for (SearchCriteria param : params) {
            specs.add(new OutturnRatioSpecification(param));
        }
 
		Specification<MasterOutturnRawGrade> result = (Specification<MasterOutturnRawGrade>) specs.get(0);
        for (int i = 1; i < specs.size(); i++) {
            result = Specifications.where(result).and((Specification<MasterOutturnRawGrade>) specs.get(i));
        }
        return result;
    }
}


package com.olam.score.terms.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import com.olam.score.common.dto.SearchCriteria;
//import com.olam.score.product.domain.MasterOutturnRawGrade;
import com.olam.score.terms.domain.MasterWeightTerms;


public class WeightTermsSpecificationsBuilder {
     
    private final List<SearchCriteria> params;
 
    public WeightTermsSpecificationsBuilder() {
        params = new ArrayList<>();
    }
 
    public WeightTermsSpecificationsBuilder with(String key, Object value) {
        params.add(new SearchCriteria(key, value));
        return this;
    }
 
    @SuppressWarnings("unchecked")
	public Specification<MasterWeightTerms> build() {
        if (params.isEmpty()) {
            return null;
        }
 
        List<Specification<?>> specs = new ArrayList<>();
        for (SearchCriteria param : params) {
            specs.add(new WeightTermsSpecification(param));
        }
 
		Specification<MasterWeightTerms> result = (Specification<MasterWeightTerms>) specs.get(0);
        for (int i = 1; i < specs.size(); i++) {
            result = Specifications.where(result).and((Specification<MasterWeightTerms>) specs.get(i));
        }
        return result;
    }
}


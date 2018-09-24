package com.olam.score.terms.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import com.olam.score.common.dto.SearchCriteria;
import com.olam.score.terms.domain.MasterContractTerms;

public class ContractTermsSpecificationsBuilder {

	private final List<SearchCriteria> params;
	 
    public ContractTermsSpecificationsBuilder() {
        params = new ArrayList<>();
    }
 
    public ContractTermsSpecificationsBuilder with(String key, Object value) {
        params.add(new SearchCriteria(key, value));
        return this;
    }
 
    @SuppressWarnings("unchecked")
	public Specification<MasterContractTerms> build() {
        if (params.isEmpty()) {
            return null;
        }
 
        List<Specification<?>> specs = new ArrayList<>();
        for (SearchCriteria param : params) {
            specs.add(new ContractTermsSpecification(param));
        }
 
		Specification<MasterContractTerms> result = (Specification<MasterContractTerms>) specs.get(0);
        for (int i = 1; i < specs.size(); i++) {
            result = Specifications.where(result).and((Specification<MasterContractTerms>) specs.get(i));
        }
        return result;
    }
}

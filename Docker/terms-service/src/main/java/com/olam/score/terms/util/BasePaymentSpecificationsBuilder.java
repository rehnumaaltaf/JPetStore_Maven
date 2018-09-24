package com.olam.score.terms.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import com.olam.score.common.dto.SearchCriteria;
import com.olam.score.terms.domain.MasterPaymentTermBase;


public class BasePaymentSpecificationsBuilder {
    
   private final List<SearchCriteria> params;

   public BasePaymentSpecificationsBuilder() {
       params = new ArrayList<>();
   }

   public BasePaymentSpecificationsBuilder with(String key, Object value) {
       params.add(new SearchCriteria(key, value));
       return this;
   }

   @SuppressWarnings("unchecked")
	public Specification<MasterPaymentTermBase> build() {
       if (params.isEmpty()) {
           return null;
       }

       List<Specification<?>> specs = new ArrayList<>();
       for (SearchCriteria param : params) {
           specs.add(new BasePaymentSpecification(param));
       }

		Specification<MasterPaymentTermBase> result = (Specification<MasterPaymentTermBase>) specs.get(0);
       for (int i = 1; i < specs.size(); i++) {
           result = Specifications.where(result).and((Specification<MasterPaymentTermBase>) specs.get(i));
       }
       return result;
   }


}

package com.olam.score.packing.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import com.olam.score.common.dto.SearchCriteria;
import com.olam.score.packing.domain.MasterSecondaryPackingType;

public class SecondaryPackingSpecificationsBuilder {
    
   private final List<SearchCriteria> params;

   public SecondaryPackingSpecificationsBuilder() {
       params = new ArrayList<>();
   }

   public SecondaryPackingSpecificationsBuilder with(String key, Object value) {
       params.add(new SearchCriteria(key, value));
       return this;
   }

   @SuppressWarnings("unchecked")
	public Specification<MasterSecondaryPackingType> build() {
       if (params.isEmpty()) {
           return null;
       }

       List<Specification<?>> specs = new ArrayList<>();
       for (SearchCriteria param : params) {
           specs.add(new SecondaryPackingSpecification(param));
       }

		Specification<MasterSecondaryPackingType> result = (Specification<MasterSecondaryPackingType>) specs.get(0);
       for (int i = 1; i < specs.size(); i++) {
           result = Specifications.where(result).and((Specification<MasterSecondaryPackingType>) specs.get(i));
       }
       return result;
   }

}

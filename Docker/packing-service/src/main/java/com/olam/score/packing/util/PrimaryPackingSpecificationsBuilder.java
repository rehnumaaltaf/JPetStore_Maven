package com.olam.score.packing.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import com.olam.score.common.dto.SearchCriteria;
import com.olam.score.packing.domain.MasterPrimaryPackingType;

public class PrimaryPackingSpecificationsBuilder {
    
   private final List<SearchCriteria> params;

   public PrimaryPackingSpecificationsBuilder() {
       params = new ArrayList<>();
   }

   public PrimaryPackingSpecificationsBuilder with(String key, Object value) {
       params.add(new SearchCriteria(key, value));
       return this;
   }

   @SuppressWarnings("unchecked")
	public Specification<MasterPrimaryPackingType> build() {
       if (params.isEmpty()) {
           return null;
       }

       List<Specification<?>> specs = new ArrayList<>();
       for (SearchCriteria param : params) {
           specs.add(new PrimaryPackingSpecification(param));
       }

		Specification<MasterPrimaryPackingType> result = (Specification<MasterPrimaryPackingType>) specs.get(0);
       for (int i = 1; i < specs.size(); i++) {
           result = Specifications.where(result).and((Specification<MasterPrimaryPackingType>) specs.get(i));
       }
       return result;
   }

}

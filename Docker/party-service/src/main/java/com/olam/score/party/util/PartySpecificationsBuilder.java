package com.olam.score.party.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import com.olam.score.common.dto.SearchCriteria;
import com.olam.score.party.domain.MasterParty;


public class PartySpecificationsBuilder {
    
   private final List<SearchCriteria> params;

   public PartySpecificationsBuilder() {
       params = new ArrayList<>();
   }

   public PartySpecificationsBuilder with(String key, Object value) {
       params.add(new SearchCriteria(key, value));
       return this;
   }

   @SuppressWarnings("unchecked")
	public Specification<MasterParty> build() {
       if (params.isEmpty()) {
           return null;
       }

       List<Specification<?>> specs = new ArrayList<>();
       for (SearchCriteria param : params) {
           specs.add(new PartySpecification(param));
       }

		Specification<MasterParty> result = (Specification<MasterParty>) specs.get(0);
       for (int i = 1; i < specs.size(); i++) {
           result = Specifications.where(result).and((Specification<MasterParty>) specs.get(i));
       }
       return result;
   }

}

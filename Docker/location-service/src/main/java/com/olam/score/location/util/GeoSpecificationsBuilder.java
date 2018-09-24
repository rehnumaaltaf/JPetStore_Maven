package com.olam.score.location.util;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import com.olam.score.common.dto.SearchCriteria;
import com.olam.score.location.domain.MasterGeography;


public class GeoSpecificationsBuilder {
	private final Logger log = LoggerFactory.getLogger(getClass());

   private final List<SearchCriteria> params;

   public GeoSpecificationsBuilder() {
       params = new ArrayList<>();
   }

   public GeoSpecificationsBuilder with(String key, Object value) {
	   log.info("===key=and==valuein GeoSpecificationsBuilder=");
	   log.info(key+"="+value);
       params.add(new SearchCriteria(key, value));
       return this;
   }

   @SuppressWarnings("unchecked")
	public Specification<MasterGeography> build() {
       if (params.isEmpty()) {
           return null;
       }

       List<Specification<?>> specs = new ArrayList<>();
       for (SearchCriteria param : params) {
           specs.add(new GeoSpecification(param));
       }

		Specification<MasterGeography> result = (Specification<MasterGeography>) specs.get(0);
       for (int i = 1; i < specs.size(); i++) {
           result = Specifications.where(result).and((Specification<MasterGeography>) specs.get(i));
       }
       return result;
   }
}

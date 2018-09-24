package com.olam.score.product.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import com.olam.score.common.dto.SearchCriteria;
import com.olam.score.product.domain.MasterProduct;


public class ProductSpecificationsBuilder {
    
   private final List<SearchCriteria> params;

   public ProductSpecificationsBuilder() {
       params = new ArrayList<>();
   }

   public ProductSpecificationsBuilder with(String key, Object value) {
       params.add(new SearchCriteria(key, value));
       return this;
   }

   @SuppressWarnings("unchecked")
	public Specification<MasterProduct> build() {
       if (params.isEmpty()) {
           return null;
       }

       List<Specification<?>> specs = new ArrayList<>();
       for (SearchCriteria param : params) {
           specs.add(new ProductSpecification(param));
       }

		Specification<MasterProduct> result = (Specification<MasterProduct>) specs.get(0);
       for (int i = 1; i < specs.size(); i++) {
           result = Specifications.where(result).and((Specification<MasterProduct>) specs.get(i));
       }
       return result;
   }

}

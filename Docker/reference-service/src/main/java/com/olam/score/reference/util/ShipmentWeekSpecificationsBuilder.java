package com.olam.score.reference.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import com.olam.score.common.dto.SearchCriteria;
import com.olam.score.reference.domain.MasterShipmentWeekTimeframe;

public class ShipmentWeekSpecificationsBuilder {

	 private final List<SearchCriteria> params;
	 
	    public ShipmentWeekSpecificationsBuilder() {
	        params = new ArrayList<>();
	    }
	 
	    public ShipmentWeekSpecificationsBuilder with(String key, Object value) {
	        params.add(new SearchCriteria(key, value));
	        return this;
	    }
	 
	    @SuppressWarnings("unchecked")
		public Specification<MasterShipmentWeekTimeframe> build() {
	        if (params.isEmpty()) {
	            return null;
	        }
	 
	        List<Specification<?>> specs = new ArrayList<>();
	        for (SearchCriteria param : params) {
	            specs.add(new ShipmentWeekSpecification(param));
	        }
	 
			Specification<MasterShipmentWeekTimeframe> result = (Specification<MasterShipmentWeekTimeframe>) specs.get(0);
	        for (int i = 1; i < specs.size(); i++) {
	            result = Specifications.where(result).and((Specification<MasterShipmentWeekTimeframe>) specs.get(i));
	        }
	        return result;
	    }


}

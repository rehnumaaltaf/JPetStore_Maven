package com.olam.score.product.dto;

import java.util.Date;
import java.util.List;

//import com.olam.score.finance.domain.MasterExternalSystemRef;

public class ExternalSystemMappingDto {
	
	private int destinationSystem;
	private String type;
	private String mapping;
	public int getDestinationSystem() {
		return destinationSystem;
	}
	public void setDestinationSystem(int destinationSystem) {
		this.destinationSystem = destinationSystem;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMapping() {
		return mapping;
	}
	public void setMapping(String mapping) {
		this.mapping = mapping;
	}
	
	
	
	
	
}

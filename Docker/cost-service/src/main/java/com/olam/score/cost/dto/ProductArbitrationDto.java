package com.olam.score.cost.dto;

import com.fasterxml.jackson.annotation.JsonProperty;


public class ProductArbitrationDto {
	
	public Integer getTableMappingId() {
		return tableMappingId;
	}
	public void setTableMappingId(Integer tableMappingId) {
		this.tableMappingId = tableMappingId;
	}
	public String getArbitrationShortName() {
		return arbitrationShortName;
	}
	public void setArbitrationShortName(String arbitrationShortName) {
		this.arbitrationShortName = arbitrationShortName;
	}
	public String getArbitrationFullName() {
		return arbitrationFullName;
	}
	public void setArbitrationFullName(String arbitrationFullName) {
		this.arbitrationFullName = arbitrationFullName;
	}
	public Integer getArbitrationAgencyId() {
		return arbitrationAgencyId;
	}
	public void setArbitrationAgencyId(Integer arbitrationAgencyId) {
		this.arbitrationAgencyId = arbitrationAgencyId;
	}
	@JsonProperty("mappingId")
	private Integer tableMappingId;//The value of mapping stored in the MasterProductArbitrationMapping table
	private String arbitrationShortName;//Get the name from the arbitration name
	private String arbitrationFullName;
	private Integer arbitrationAgencyId;//Get the id from the arbitration table

}

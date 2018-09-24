package com.olam.score.cost.dto;

import com.fasterxml.jackson.annotation.JsonProperty;



public class ProcessingSubTypeDto {
	
	@JsonProperty("processSubTypeRefId")
	private Integer processSubId;
	@JsonProperty("subTypeCode")
	private String processSubCode;
	@JsonProperty("subTypeName")
	private String processSubName;
	@JsonProperty("processSubTypeMappingId")
	private Integer mappingId;
	public Integer getProcessSubId() {
		return processSubId;
	}
	public void setProcessSubId(Integer processSubId) {
		this.processSubId = processSubId;
	}
	public String getProcessSubCode() {
		return processSubCode;
	}
	public void setProcessSubCode(String processSubCode) {
		this.processSubCode = processSubCode;
	}
	public String getProcessSubName() {
		return processSubName;
	}
	public void setProcessSubName(String processSubName) {
		this.processSubName = processSubName;
	}
	public Integer getMappingId() {
		return mappingId;
	}
	public void setMappingId(Integer mappingId) {
		this.mappingId = mappingId;
	}

}

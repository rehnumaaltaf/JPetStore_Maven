package com.olam.score.authorizationconfig.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FeatureMappingDto {
	
	@JsonProperty("feature")
	private int featureId;
	
	@JsonProperty("module")
	private int moduleId;
	
	@JsonProperty("mappingId")
	private int mappingId;
	public int getFeatureId() {
		return featureId;
	}
	public void setFeatureId(int featureId) {
		this.featureId = featureId;
	}
	public int getModuleId() {
		return moduleId;
	}
	public void setModuleId(int moduleId) {
		this.moduleId = moduleId;
	}
	public int getMappingId() {
		return mappingId;
	}
	public void setMappingId(int mappingId) {
		this.mappingId = mappingId;
	}
	
}

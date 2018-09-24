package com.olam.score.authorizationconfig.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Feature {
	
	public Feature(int featureId, String featureName, String featureDesc,int roleFeatureMappingId) {
		super();
		this.featureId = featureId;
		this.featureName = featureName;
		this.featureDesc = featureDesc;
		this.roleFeatureMappingId = roleFeatureMappingId;
	}
	
	public Feature(){
		
	}
	@JsonProperty("id")
	private int featureId;
	
	@JsonProperty("value")
	private String featureName;
	
	private String featureDesc;
	
	@JsonProperty("mappingId")
	private int roleFeatureMappingId;
	
	public int getRoleFeatureMappingId() {
		return roleFeatureMappingId;
	}

	public void setRoleFeatureMappingId(int roleFeatureMappingId) {
		this.roleFeatureMappingId = roleFeatureMappingId;
	}

	public int getFeatureId() {
		return featureId;
	}
	public void setFeatureId(int featureId) {
		this.featureId = featureId;
	}
	public String getFeatureName() {
		return featureName;
	}
	public void setFeatureName(String featureName) {
		this.featureName = featureName;
	}
	public String getFeatureDesc() {
		return featureDesc;
	}
	public void setFeatureDesc(String featureDesc) {
		this.featureDesc = featureDesc;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((featureDesc == null) ? 0 : featureDesc.hashCode());
		result = prime * result + featureId;
		result = prime * result + ((featureName == null) ? 0 : featureName.hashCode());
		result = prime * result + roleFeatureMappingId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Feature other = (Feature) obj;
		if (featureDesc == null) {
			if (other.featureDesc != null)
				return false;
		} else if (!featureDesc.equals(other.featureDesc))
			return false;
		if (featureId != other.featureId)
			return false;
		if (featureName == null) {
			if (other.featureName != null)
				return false;
		} else if (!featureName.equals(other.featureName))
			return false;
		if(roleFeatureMappingId != other.roleFeatureMappingId)
			return false;
		return true;
	}
	
}

package com.olam.score.authorizationconfig.dto;

import java.util.List;

public class RoleFeatureModel {
	
	private int roleId;
	private String roleName;
	private String roleDesc;
	private List<FeatureMap> features;
	private String statusName;
	private String errorMessage;
	private List<FeatureMappingDto> featureMapping;
	
	public List<FeatureMappingDto> getFeatureMapping() {
		return featureMapping;
	}
	public void setFeatureMapping(List<FeatureMappingDto> featureMapping) {
		this.featureMapping = featureMapping;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleDesc() {
		return roleDesc;
	}
	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}
	public List<FeatureMap> getFeatures() {
		return features;
	}
	public void setFeatures(List<FeatureMap> features) {
		this.features = features;
	}
	
	
	

}

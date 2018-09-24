package com.olam.score.authorizationconfig.dto;

import java.util.ArrayList;
import java.util.List;

public class RoleFeatureDto {
	
	private int roleId;
	private String roleName;
	private String roleDesc;
	private String roleStatusId;
	private String roleStatusName;
	private Feature feature;
	private List<Feature> features;
	private List<DisplayValue> featureValues;
	private List<DisplayValue> moduleValues;
	private String errorMessage;
	private List<FeatureMap> featureMap = new ArrayList<>();
	
	
	
	public List<FeatureMap> getFeatureMap() {
		return featureMap;
	}
	public void setFeatureMap(List<FeatureMap> featureMap) {
		this.featureMap = featureMap;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public List<DisplayValue> getModuleValues() {
		return moduleValues;
	}
	public void setModuleValues(List<DisplayValue> moduleValues) {
		this.moduleValues = moduleValues;
	}
	public List<DisplayValue> getFeatureValues() {
		return featureValues;
	}
	public void setFeatureValues(List<DisplayValue> featureValues) {
		this.featureValues = featureValues;
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
	public Feature getFeature() {
		return feature;
	}
	public void setFeature(Feature feature) {
		this.feature = feature;
	}
	public List<Feature> getFeatures() {
		return features;
	}
	public void setFeatures(List<Feature> features) {
		this.features = features;
	}
	public String getRoleStatusId() {
		return roleStatusId;
	}
	public void setRoleStatusId(String roleStatusId) {
		this.roleStatusId = roleStatusId;
	}
	public String getRoleStatusName() {
		return roleStatusName;
	}
	public void setRoleStatusName(String roleStatusName) {
		this.roleStatusName = roleStatusName;
	}
	
}

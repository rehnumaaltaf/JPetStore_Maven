package com.olam.score.common.dto;

public class AuthorizationWrapper {
	private Integer featureId;
	private String featureName;
	private Integer permissionLevelId;
	private String permissionLevelname;
	private Integer entityId;
	private String entityName;
	private String entityUrl;
	private Integer moduleId;
	private String moduleName;
	private String moduleUrl;
	public Integer getFeatureId() {
		return featureId;
	}
	public void setFeatureId(Integer featureId) {
		this.featureId = featureId;
	}
	public String getFeatureName() {
		return featureName;
	}
	public void setFeatureName(String featureName) {
		this.featureName = featureName;
	}
	public Integer getPermissionLevelId() {
		return permissionLevelId;
	}
	public void setPermissionLevelId(Integer permissionLevelId) {
		this.permissionLevelId = permissionLevelId;
	}
	public String getPermissionLevelname() {
		return permissionLevelname;
	}
	public void setPermissionLevelname(String permissionLevelname) {
		this.permissionLevelname = permissionLevelname;
	}
	public Integer getEntityId() {
		return entityId;
	}
	public void setEntityId(Integer entityId) {
		this.entityId = entityId;
	}
	public String getEntityName() {
		return entityName;
	}
	public String getEntityUrl() {
		return entityUrl;
	}
	public void setEntityUrl(String entityUrl) {
		this.entityUrl = entityUrl;
	}
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
	public Integer getModuleId() {
		return moduleId;
	}
	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public String getModuleUrl() {
		return moduleUrl;
	}
	public void setModuleUrl(String moduleUrl) {
		this.moduleUrl = moduleUrl;
	}
	
}

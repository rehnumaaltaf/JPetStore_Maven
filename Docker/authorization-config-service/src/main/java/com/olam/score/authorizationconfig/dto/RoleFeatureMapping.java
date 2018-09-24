package com.olam.score.authorizationconfig.dto;

public class RoleFeatureMapping {
	
	private int roleId;
	private String roleName;
	private String roleDesc;
	private int roleStatusId;
	private String roleStatusName;
	private FeatureMap featureMap;
	
	public String getRoleDesc() {
		return roleDesc;
	}
	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
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
	public FeatureMap getFeatureMap() {
		return featureMap;
	}
	public void setFeatureMap(FeatureMap featureMap) {
		this.featureMap = featureMap;
	}
	public int getRoleStatusId() {
		return roleStatusId;
	}
	public void setRoleStatusId(int roleStatusId) {
		this.roleStatusId = roleStatusId;
	}
	public String getRoleStatusName() {
		return roleStatusName;
	}
	public void setRoleStatusName(String roleStatusName) {
		this.roleStatusName = roleStatusName;
	}
	public RoleFeatureMapping(int roleId, String roleName, String roleDesc, int roleStatusId, String roleStatusName,
			int featureId, String featureName, String featureDesc,int roleFeatureMapping) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
		this.roleDesc = roleDesc;
		this.roleStatusId = roleStatusId;
		this.roleStatusName = roleStatusName;
		this.featureMap = new FeatureMap(featureId,featureName,featureDesc,roleFeatureMapping);
	}
	public RoleFeatureMapping(int roleId, String roleName, String roleDesc, int roleStatusId, String roleStatusName) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
		this.roleDesc = roleDesc;
		this.roleStatusId = roleStatusId;
		this.roleStatusName = roleStatusName;
	}
	
	public RoleFeatureMapping(int roleId, String roleName, String roleDesc, int roleStatusId,
			int featureId, String featureName, String featureDesc,int roleFeatureMapping) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
		this.roleDesc = roleDesc;
		this.roleStatusId = roleStatusId;
		this.featureMap = new FeatureMap(featureId,featureName,featureDesc,roleFeatureMapping);
	}
	
	public RoleFeatureMapping(int roleId, String roleName, String roleDesc, int roleStatusId,
			int featureId, String featureName, String featureDesc,int roleFeatureMapping,int moduleId,String moduleName,
			String moduleDesc) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
		this.roleDesc = roleDesc;
		this.roleStatusId = roleStatusId;
		this.roleStatusName = roleStatusName;
		this.featureMap = new FeatureMap(featureId,featureName,featureDesc,roleFeatureMapping,moduleId,
				moduleName,moduleDesc);
	}
	

}

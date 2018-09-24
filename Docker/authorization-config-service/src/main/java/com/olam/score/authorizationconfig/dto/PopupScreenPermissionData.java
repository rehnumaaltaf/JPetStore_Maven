package com.olam.score.authorizationconfig.dto;

public class PopupScreenPermissionData implements Comparable<PopupScreenPermissionData> {
	
	private String roleName;
	private String roleDesc;
	private Feature features;
	
	public PopupScreenPermissionData(String roleName, String roleDesc, int featureId,
			String featureName,String featureDesc,int mappingId) {
		super();
		this.roleName = roleName;
		this.roleDesc = roleDesc;
		this.features = new Feature(featureId,featureName,featureDesc,mappingId);
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
	public Feature getFeatures() {
		return features;
	}
	public void setFeatures(Feature features) {
		this.features = features;
	}
	
	@Override
	public int compareTo(PopupScreenPermissionData newData) {
		if(roleName.equals(newData.getRoleName())&&roleDesc.equals(newData.getRoleDesc())&&
				features.equals(newData.getFeatures()))
			return 0 ;
		else 
		    return 1;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((features == null) ? 0 : features.hashCode());
		result = prime * result + ((roleDesc == null) ? 0 : roleDesc.hashCode());
		result = prime * result + ((roleName == null) ? 0 : roleName.hashCode());
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
		PopupScreenPermissionData other = (PopupScreenPermissionData) obj;
		if (features == null) {
			if (other.features != null)
				return false;
		} else if (!features.equals(other.features))
			return false;
		if (roleDesc == null) {
			if (other.roleDesc != null)
				return false;
		} else if (!roleDesc.equals(other.roleDesc))
			return false;
		if (roleName == null) {
			if (other.roleName != null)
				return false;
		} else if (!roleName.equals(other.roleName))
			return false;
		return true;
	}
}

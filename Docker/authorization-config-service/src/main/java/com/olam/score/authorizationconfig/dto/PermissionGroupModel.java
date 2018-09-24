package com.olam.score.authorizationconfig.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PermissionGroupModel {
	
	private int permissionGroupId;
	
	private String permissionGroupName;
	
	private String permissionGroupDesc;
	
	@JsonProperty("permissionOwner")
	private String permissionGroupOwner;
	
	private List<PermissionGroupRoleMapping> permissionGroupRoleMapping = new ArrayList<>();

	private String statusName;
	
	private String errorMessage;
	
	public String getPermissionGroupOwner() {
		return permissionGroupOwner;
	}

	public void setPermissionGroupOwner(String permissionGroupOwner) {
		this.permissionGroupOwner = permissionGroupOwner;
	}
	
	public String getPermissionGroupDesc() {
		return permissionGroupDesc;
	}

	public void setPermissionGroupDesc(String permissionGroupDesc) {
		this.permissionGroupDesc = permissionGroupDesc;
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

	public int getPermissionGroupId() {
		return permissionGroupId;
	}

	public void setPermissionGroupId(int permissionGroupId) {
		this.permissionGroupId = permissionGroupId;
	}

	public String getPermissionGroupName() {
		return permissionGroupName;
	}

	public void setPermissionGroupName(String permissionGroupName) {
		this.permissionGroupName = permissionGroupName;
	}

	public List<PermissionGroupRoleMapping> getPermissionGroupRoleMapping() {
		return permissionGroupRoleMapping;
	}

	public void setPermissionGroupRoleMapping(List<PermissionGroupRoleMapping> permissionGroupRoleMapping) {
		this.permissionGroupRoleMapping = permissionGroupRoleMapping;
	}

	
}

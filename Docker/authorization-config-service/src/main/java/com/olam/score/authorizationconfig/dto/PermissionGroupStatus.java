package com.olam.score.authorizationconfig.dto;

public class PermissionGroupStatus {
	
	private int permissionGroupId;
	private String permissionGroupName;
	private String permissionGroupDesc;
	private int statusId;
	private String statusName;
	
	public PermissionGroupStatus(int permissionGroupId, String permissionGroupName, String permissionGroupDesc,
			int statusId, String statusName) {
		super();
		this.permissionGroupId = permissionGroupId;
		this.permissionGroupName = permissionGroupName;
		this.permissionGroupDesc = permissionGroupDesc;
		this.statusId = statusId;
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
	public String getPermissionGroupDesc() {
		return permissionGroupDesc;
	}
	public void setPermissionGroupDesc(String permissionGroupDesc) {
		this.permissionGroupDesc = permissionGroupDesc;
	}
	public int getStatusId() {
		return statusId;
	}
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	
	
	

}

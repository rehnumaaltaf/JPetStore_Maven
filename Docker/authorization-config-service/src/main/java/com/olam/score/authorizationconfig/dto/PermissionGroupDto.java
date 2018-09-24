package com.olam.score.authorizationconfig.dto;

import java.util.List;

public class PermissionGroupDto {
	
	private int permissionGroupId;
	
	private String permissionGroupName;
	
	private String permissionGroupDesc;
	
	private String permissionGroupOwner;
	
	private String statusName;
	
	private int statusId;
	
	private List<PermissionGroupRoleMapping> permissionGroupRoleMapping;
	
	private PermissionGroupRoleMapping permissionGroupRoleMappingDto;
	//Fields containing the dropbox options
	
	private List<DisplayValue> roles;
	private List<DisplayValue> parties;
	private List<DisplayValue> products;
	private List<DisplayValue> units;
	private List<DisplayValue> portfolios;
	
	private String errorMessage;
	
	public String getPermissionGroupOwner() {
		return permissionGroupOwner;
	}
	public void setPermissionGroupOwner(String permissionGroupOwner) {
		this.permissionGroupOwner = permissionGroupOwner;
	}
	
	public int getStatusId() {
		return statusId;
	}
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getPermissionGroupDesc() {
		return permissionGroupDesc;
	}
	public void setPermissionGroupDesc(String permissionGroupDesc) {
		this.permissionGroupDesc = permissionGroupDesc;
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
	
	public PermissionGroupRoleMapping getPermissionGroupRoleMappingDto() {
		return permissionGroupRoleMappingDto;
	}
	
	public void setPermissionGroupRoleMappingDto(PermissionGroupRoleMapping permissionGroupRoleMappingDto) {
		this.permissionGroupRoleMappingDto = permissionGroupRoleMappingDto;
	}
	public List<PermissionGroupRoleMapping> getPermissionGroupRoleMapping() {
		return permissionGroupRoleMapping;
	}
	public void setPermissionGroupRoleMapping(List<PermissionGroupRoleMapping> permissionGroupRoleMapping) {
		this.permissionGroupRoleMapping = permissionGroupRoleMapping;
	}
	public List<DisplayValue> getRoles() {
		return roles;
	}
	public void setRoles(List<DisplayValue> roles) {
		this.roles = roles;
	}
	public List<DisplayValue> getParties() {
		return parties;
	}
	public void setParties(List<DisplayValue> parties) {
		this.parties = parties;
	}
	public List<DisplayValue> getProducts() {
		return products;
	}
	public void setProducts(List<DisplayValue> products) {
		this.products = products;
	}
	public List<DisplayValue> getUnits() {
		return units;
	}
	public void setUnits(List<DisplayValue> units) {
		this.units = units;
	}
	public List<DisplayValue> getPortfolios() {
		return portfolios;
	}
	public void setPortfolios(List<DisplayValue> portfolios) {
		this.portfolios = portfolios;
	}
}

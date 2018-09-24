package com.olam.score.authorizationconfig.dto;


public class PermissionGroupMapping {
	
	private int permissionGroupId;
	
	private String permissionGroupName;
	
	private PermissionGroupRoleMapping permissionGroupRoleMappingDto;

	public PermissionGroupMapping(int mappingId,int permissionGroupId, String permissionGroupName, int roleId, String role,
			int partyId, String party, int productId, String product,
			int unitId, String unit, int portfolioId, String portfolio) {
		super();
		this.permissionGroupId = permissionGroupId;
		this.permissionGroupName = permissionGroupName;
		this.permissionGroupRoleMappingDto = new PermissionGroupRoleMapping(mappingId, roleId,  role,  partyId,  party,  productId,  product,
			 unitId,  unit,  portfolioId,  portfolio);
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
	
}

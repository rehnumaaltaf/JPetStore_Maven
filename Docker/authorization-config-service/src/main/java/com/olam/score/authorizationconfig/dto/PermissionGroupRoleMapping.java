package com.olam.score.authorizationconfig.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PermissionGroupRoleMapping {

	public PermissionGroupRoleMapping(int mappingId,int roleId, String role, int partyId, String party, int productId, String product,
			int unitId, String unit, int portfolioId, String portfolio) {
		super();
		this.mappingId = mappingId;
		this.role = new RoleModel(roleId,role);
		this.party = new PartyModel(partyId,party);
		this.product = new ProductModel(productId,product);
		this.unit = new UnitModel(unitId,unit);
		this.portfolio = new PortfolioModel(portfolioId,portfolio);
	}
	
	public PermissionGroupRoleMapping(){
		
	}
	
	private int mappingId;
	private RoleModel role;
	private PartyModel party;
	private ProductModel product;
	private UnitModel unit;
	private PortfolioModel portfolio;
	
	@JsonProperty("roleId")
	private Integer roleId;
	
	@JsonProperty("partyId")
	private Integer partyId;
	
	@JsonProperty("productId")
	private Integer productId;
	
	@JsonProperty("unitId")
	private Integer unitId;
	
	@JsonProperty("portfolioId")
	private Integer portfolioId;
	
	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getPartyId() {
		return partyId;
	}

	public void setPartyId(Integer partyId) {
		this.partyId = partyId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public Integer getPortfolioId() {
		return portfolioId;
	}

	public void setPortfolioId(Integer portfolioId) {
		this.portfolioId = portfolioId;
	}

	public RoleModel getRole() {
		return role;
	}

	public void setRole(RoleModel role) {
		this.role = role;
	}

	public PartyModel getParty() {
		return party;
	}

	public void setParty(PartyModel party) {
		this.party = party;
	}

	public ProductModel getProduct() {
		return product;
	}

	public void setProduct(ProductModel product) {
		this.product = product;
	}

	public UnitModel getUnit() {
		return unit;
	}

	public void setUnit(UnitModel unit) {
		this.unit = unit;
	}

	public PortfolioModel getPortfolio() {
		return portfolio;
	}

	public void setPortfolio(PortfolioModel portfolio) {
		this.portfolio = portfolio;
	}
	
	public PermissionGroupRoleMapping(Integer mappingId,Integer roleId, Integer partyId, Integer productId,
			Integer unitId,  Integer portfolioId) {
		super();
		this.mappingId = mappingId;
		this.role = new RoleModel(roleId,"Test");
		this.party = new PartyModel(partyId,"Test");
		this.product = new ProductModel(productId,"Test");
		this.unit = new UnitModel(unitId,"Test");
		this.portfolio = new PortfolioModel(portfolioId,"Test");
	}

	public int getMappingId() {
		return mappingId;
	}

	public void setMappingId(int mappingId) {
		this.mappingId = mappingId;
	}
	
}

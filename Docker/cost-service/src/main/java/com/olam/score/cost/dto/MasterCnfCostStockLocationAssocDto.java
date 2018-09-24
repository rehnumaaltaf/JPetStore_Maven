package com.olam.score.cost.dto;

import java.util.Date;

public class MasterCnfCostStockLocationAssocDto {
	
	private Integer pkCnfCostStockLocAssocId;
	private Integer fkCnfCostId;
	private Integer fkPartyWhStockLocationId;
	private String fkPartyWhStockLocationName;
	public String getFkPartyWhStockLocationName() {
		return fkPartyWhStockLocationName;
	}
	public void setFkPartyWhStockLocationName(String fkPartyWhStockLocationName) {
		this.fkPartyWhStockLocationName = fkPartyWhStockLocationName;
	}
	private String createdBy;
	private Date createdDate;
	private String modifiedBy;
	private Date modifiedDate;
	private Integer fkStatusId;
	private String fkStatusName;
	public Integer getPkCnfCostStockLocAssocId() {
		return pkCnfCostStockLocAssocId;
	}
	public void setPkCnfCostStockLocAssocId(Integer pkCnfCostStockLocAssocId) {
		this.pkCnfCostStockLocAssocId = pkCnfCostStockLocAssocId;
	}
	public Integer getFkCnfCostId() {
		return fkCnfCostId;
	}
	public void setFkCnfCostId(Integer fkCnfCostId) {
		this.fkCnfCostId = fkCnfCostId;
	}
	public Integer getFkPartyWhStockLocationId() {
		return fkPartyWhStockLocationId;
	}
	public void setFkPartyWhStockLocationId(Integer fkPartyWhStockLocationId) {
		this.fkPartyWhStockLocationId = fkPartyWhStockLocationId;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public Integer getFkStatusId() {
		return fkStatusId;
	}
	public void setFkStatusId(Integer fkStatusId) {
		this.fkStatusId = fkStatusId;
	}
	public String getFkStatusName() {
		return fkStatusName;
	}
	public void setFkStatusName(String fkStatusName) {
		this.fkStatusName = fkStatusName;
	}
	
}

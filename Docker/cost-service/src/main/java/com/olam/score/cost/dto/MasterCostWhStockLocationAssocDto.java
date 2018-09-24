package com.olam.score.cost.dto;

import java.util.Date;

public class MasterCostWhStockLocationAssocDto {
	
	
	private Integer pkWhCostStockLocAssocId;
	private Integer fkWhCostId;
	private Integer fkPartyWhStockLocId;
	private String createdBy;
	private Date createdDate;
	private String modifiedBy;
	private Date modifiedDate;
	private Integer fkStatusId;
	public String getFkPartyWhStockLocIdName() {
		return fkPartyWhStockLocIdName;
	}
	public void setFkPartyWhStockLocIdName(String fkPartyWhStockLocIdName) {
		this.fkPartyWhStockLocIdName = fkPartyWhStockLocIdName;
	}
	private String fkStatusName;
	private String fkPartyWhStockLocIdName;
	public Integer getPkWhCostStockLocAssocId() {
		return pkWhCostStockLocAssocId;
	}
	public void setPkWhCostStockLocAssocId(Integer pkWhCostStockLocAssocId) {
		this.pkWhCostStockLocAssocId = pkWhCostStockLocAssocId;
	}
	public Integer getFkWhCostId() {
		return fkWhCostId;
	}
	public void setFkWhCostId(Integer fkWhCostId) {
		this.fkWhCostId = fkWhCostId;
	}
	public Integer getFkPartyWhStockLocId() {
		return fkPartyWhStockLocId;
	}
	public void setFkPartyWhStockLocId(Integer fkPartyWhStockLocId) {
		this.fkPartyWhStockLocId = fkPartyWhStockLocId;
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

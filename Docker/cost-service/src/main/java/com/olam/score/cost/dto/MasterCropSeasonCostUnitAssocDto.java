package com.olam.score.cost.dto;

import java.util.Date;

public class MasterCropSeasonCostUnitAssocDto {
	private Integer pkCropSeasonCostUnitId;
	private Integer fkCropSeasonCostId;
	private Integer fkPartyId;
	private String createdBy;
	private Date createdDate;
	private String modifiedBy;
	private Date modifiedDate;
	private Integer fkStatusId;
	private String fkStatusName;
	public Integer getPkCropSeasonCostUnitId() {
		return pkCropSeasonCostUnitId;
	}
	public void setPkCropSeasonCostUnitId(Integer pkCropSeasonCostUnitId) {
		this.pkCropSeasonCostUnitId = pkCropSeasonCostUnitId;
	}
	public Integer getFkCropSeasonCostId() {
		return fkCropSeasonCostId;
	}
	public void setFkCropSeasonCostId(Integer fkCropSeasonCostId) {
		this.fkCropSeasonCostId = fkCropSeasonCostId;
	}
	public Integer getFkPartyId() {
		return fkPartyId;
	}
	public void setFkPartyId(Integer fkPartyId) {
		this.fkPartyId = fkPartyId;
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

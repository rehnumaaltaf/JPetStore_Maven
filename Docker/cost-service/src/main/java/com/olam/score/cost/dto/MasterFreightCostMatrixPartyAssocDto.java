package com.olam.score.cost.dto;

import java.util.Date;

public class MasterFreightCostMatrixPartyAssocDto {
	private Integer pkFreightCostMatrixPartyAssocId;
	private Integer fkFreightCostMatrixId;
	private Integer fkPartyId;
	private String fkPartyName;
	public String getFkPartyName() {
		return fkPartyName;
	}
	public void setFkPartyName(String fkPartyName) {
		this.fkPartyName = fkPartyName;
	}
	private String createdBy;
	private Date createdDate;
	private String modifiedBy;
	private Date modifiedDate;
	private Integer fkStatusId;
	private String fkStatusName;
	public Integer getPkFreightCostMatrixPartyAssocId() {
		return pkFreightCostMatrixPartyAssocId;
	}
	public void setPkFreightCostMatrixPartyAssocId(Integer pkFreightCostMatrixPartyAssocId) {
		this.pkFreightCostMatrixPartyAssocId = pkFreightCostMatrixPartyAssocId;
	}
	public Integer getFkFreightCostMatrixId() {
		return fkFreightCostMatrixId;
	}
	public void setFkFreightCostMatrixId(Integer fkFreightCostMatrixId) {
		this.fkFreightCostMatrixId = fkFreightCostMatrixId;
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

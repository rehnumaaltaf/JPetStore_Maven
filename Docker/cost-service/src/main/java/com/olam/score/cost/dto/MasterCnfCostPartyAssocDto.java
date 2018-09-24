package com.olam.score.cost.dto;

import java.util.Date;

public class MasterCnfCostPartyAssocDto {
	private Integer pkCnfCostPartyAssocId;
	private Integer fkCnfCostId;
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
	public Integer getPkCnfCostPartyAssocId() {
		return pkCnfCostPartyAssocId;
	}
	public void setPkCnfCostPartyAssocId(Integer pkCnfCostPartyAssocId) {
		this.pkCnfCostPartyAssocId = pkCnfCostPartyAssocId;
	}
	public Integer getFkCnfCostId() {
		return fkCnfCostId;
	}
	public void setFkCnfCostId(Integer fkCnfCostId) {
		this.fkCnfCostId = fkCnfCostId;
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

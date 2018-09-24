package com.olam.score.cost.dto;

import java.util.Date;

public class MasterCostExternalMappingDto {
	
	private Integer pkCostExternalMappingId;
	private Integer fkCostId;
	private Integer fkExternalSystemRefId;
	private String mappingType;
	private String externalCode;
	private Integer fkStatusId;
	private String createdBy;
	private Date createdDate;
	private String modifiedBy;
	private Date modifiedDate;
	private String fkExternalSystemRefName;
private String fkCostName; 
	
	public String getFkCostName() {
		return fkCostName;
	}
	public void setFkCostName(String fkCostName) {
		this.fkCostName = fkCostName;
	}
	public String getFkExternalSystemRefName() {
		return fkExternalSystemRefName;
	}
	public void setFkExternalSystemRefName(String fkExternalSystemRefName) {
		this.fkExternalSystemRefName = fkExternalSystemRefName;
	}
	
	public Integer getPkCostExternalMappingId() {
		return pkCostExternalMappingId;
	}
	public void setPkCostExternalMappingId(Integer pkCostExternalMappingId) {
		this.pkCostExternalMappingId = pkCostExternalMappingId;
	}
	public Integer getFkCostId() {
		return fkCostId;
	}
	public void setFkCostId(Integer fkCostId) {
		this.fkCostId = fkCostId;
	}
	public Integer getFkExternalSystemRefId() {
		return fkExternalSystemRefId;
	}
	public void setFkExternalSystemRefId(Integer fkExternalSystemRefId) {
		this.fkExternalSystemRefId = fkExternalSystemRefId;
	}
	public String getMappingType() {
		return mappingType;
	}
	public void setMappingType(String mappingType) {
		this.mappingType = mappingType;
	}
	public String getExternalCode() {
		return externalCode;
	}
	public void setExternalCode(String externalCode) {
		this.externalCode = externalCode;
	}
	public Integer getFkStatusId() {
		return fkStatusId;
	}
	public void setFkStatusId(Integer fkStatusId) {
		this.fkStatusId = fkStatusId;
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
	
	

}

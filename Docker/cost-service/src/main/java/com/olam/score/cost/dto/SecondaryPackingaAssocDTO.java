package com.olam.score.cost.dto;

import java.util.Date;

public class SecondaryPackingaAssocDTO {
	
    private Integer pkPackingAssocId;
    private Integer fkSecondaryPackingTypeId;
    private Integer fkOriginId;
    private String  fkOriginName;
    private Integer fkProdId;
    private String  fkProdName;
    private Integer fkPrimaryPackingTypeId;
    private String  fkPrimaryPackingTypeName;
    private Float countOfPrimary;
    private Integer fkStatusId;
    private String createdBy;
    private Date createdDate;
    private String modifiedBy;
    private Date modifiedDate;
	public Integer getPkPackingAssocId() {
		return pkPackingAssocId;
	}
	public void setPkPackingAssocId(Integer pkPackingAssocId) {
		this.pkPackingAssocId = pkPackingAssocId;
	}
	public Integer getFkSecondaryPackingTypeId() {
		return fkSecondaryPackingTypeId;
	}
	public void setFkSecondaryPackingTypeId(Integer fkSecondaryPackingTypeId) {
		this.fkSecondaryPackingTypeId = fkSecondaryPackingTypeId;
	}
	public Integer getFkOriginId() {
		return fkOriginId;
	}
	public void setFkOriginId(Integer fkOriginId) {
		this.fkOriginId = fkOriginId;
	}
	public String getFkOriginName() {
		return fkOriginName;
	}
	public void setFkOriginName(String fkOriginName) {
		this.fkOriginName = fkOriginName;
	}
	public Integer getFkProdId() {
		return fkProdId;
	}
	public void setFkProdId(Integer fkProdId) {
		this.fkProdId = fkProdId;
	}
	public String getFkProdName() {
		return fkProdName;
	}
	public void setFkProdName(String fkProdName) {
		this.fkProdName = fkProdName;
	}
	public Integer getFkPrimaryPackingTypeId() {
		return fkPrimaryPackingTypeId;
	}
	public void setFkPrimaryPackingTypeId(Integer fkPrimaryPackingTypeId) {
		this.fkPrimaryPackingTypeId = fkPrimaryPackingTypeId;
	}
	public String getFkPrimaryPackingTypeName() {
		return fkPrimaryPackingTypeName;
	}
	public void setFkPrimaryPackingTypeName(String fkPrimaryPackingTypeName) {
		this.fkPrimaryPackingTypeName = fkPrimaryPackingTypeName;
	}
	public Float getCountOfPrimary() {
		return countOfPrimary;
	}
	public void setCountOfPrimary(Float countOfPrimary) {
		this.countOfPrimary = countOfPrimary;
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

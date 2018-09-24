package com.olam.score.cost.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SecondaryPackingTypeDTO {
	
	private Integer pkSecondaryPackingTypeId;
    private String secondaryPackingTypeCode;
    private String secondaryPackingTypeName;
    private String secondaryPackingTypeDesc;
    private String secondaryPackingTypeIsBulk;
    private Integer fkStatusId;
    private String statusName;
    private String createdBy;
    private Date createdDate;
    private String modifiedBy;
    private Date modifiedDate;
    private String action;
    private List<SecondaryPackingaAssocDTO> secPackAssocList =new ArrayList<>();
    
	public Integer getPkSecondaryPackingTypeId() {
		return pkSecondaryPackingTypeId;
	}
	public void setPkSecondaryPackingTypeId(Integer pkSecondaryPackingTypeId) {
		this.pkSecondaryPackingTypeId = pkSecondaryPackingTypeId;
	}
	public String getSecondaryPackingTypeCode() {
		return secondaryPackingTypeCode;
	}
	public void setSecondaryPackingTypeCode(String secondaryPackingTypeCode) {
		this.secondaryPackingTypeCode = secondaryPackingTypeCode;
	}
	public String getSecondaryPackingTypeName() {
		return secondaryPackingTypeName;
	}
	public void setSecondaryPackingTypeName(String secondaryPackingTypeName) {
		this.secondaryPackingTypeName = secondaryPackingTypeName;
	}
	public String getSecondaryPackingTypeDesc() {
		return secondaryPackingTypeDesc;
	}
	public void setSecondaryPackingTypeDesc(String secondaryPackingTypeDesc) {
		this.secondaryPackingTypeDesc = secondaryPackingTypeDesc;
	}
	public String getSecondaryPackingTypeIsBulk() {
		return secondaryPackingTypeIsBulk;
	}
	public void setSecondaryPackingTypeIsBulk(String secondaryPackingTypeIsBulk) {
		this.secondaryPackingTypeIsBulk = secondaryPackingTypeIsBulk;
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
	public List<SecondaryPackingaAssocDTO> getSecPackAssocList() {
		return secPackAssocList;
	}
	public void setSecPackAssocList(List<SecondaryPackingaAssocDTO> secPackAssocList) {
		this.secPackAssocList = secPackAssocList;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
    
    

}

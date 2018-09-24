package com.olam.score.cost.dto;

import java.util.Date;
import java.util.List;

public class MasterFreightCostMatrixDto {
	
	private Integer pkFreightCostId;
	   
	private	Integer fkCostId;
  
    private Integer fkStatusId;
    
    private String createdBy;
   
    private Date createdDate;
   
    private String modifiedBy;
    
    private Date modifiedDate;
    
   
    private Date freightCostValidFrom;
   
   
    private Date freightCostValidTo;
   
    private String freightCostContrRefNo;
    
    private String freightCostRemarks;
    
    
    private String remarks;
    
    private int isApplicableToAllPartyInd;
    
    private int freightCostIsDetailedInd;
    
    private Integer fkProdId;
    
    private String fkProdName;
  
    private Integer fkServiceProviderPartyId;
    
    private String fkServiceProviderPartyName;
    
    private List<MasterFreightCostMatrixDtlDto>  masterFreightCostdetailDto;
    
    private List<MasterFreightCostMatrixPartyAssocDto> fkPartyId;

	public Integer getPkFreightCostId() {
		return pkFreightCostId;
	}

	public void setPkFreightCostId(Integer pkFreightCostId) {
		this.pkFreightCostId = pkFreightCostId;
	}

	public Integer getFkCostId() {
		return fkCostId;
	}

	public void setFkCostId(Integer fkCostId) {
		this.fkCostId = fkCostId;
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

	public Date getFreightCostValidFrom() {
		return freightCostValidFrom;
	}

	public void setFreightCostValidFrom(Date freightCostValidFrom) {
		this.freightCostValidFrom = freightCostValidFrom;
	}

	public Date getFreightCostValidTo() {
		return freightCostValidTo;
	}

	public void setFreightCostValidTo(Date freightCostValidTo) {
		this.freightCostValidTo = freightCostValidTo;
	}

	public String getFreightCostContrRefNo() {
		return freightCostContrRefNo;
	}

	public void setFreightCostContrRefNo(String freightCostContrRefNo) {
		this.freightCostContrRefNo = freightCostContrRefNo;
	}

	public String getFreightCostRemarks() {
		return freightCostRemarks;
	}

	public void setFreightCostRemarks(String freightCostRemarks) {
		this.freightCostRemarks = freightCostRemarks;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public int getIsApplicableToAllPartyInd() {
		return isApplicableToAllPartyInd;
	}

	public void setIsApplicableToAllPartyInd(int isApplicableToAllPartyInd) {
		this.isApplicableToAllPartyInd = isApplicableToAllPartyInd;
	}

	

	public int getFreightCostIsDetailedInd() {
		return freightCostIsDetailedInd;
	}

	public void setFreightCostIsDetailedInd(int freightCostIsDetailedInd) {
		this.freightCostIsDetailedInd = freightCostIsDetailedInd;
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

	public String getFkServiceProviderPartyName() {
		return fkServiceProviderPartyName;
	}

	public void setFkServiceProviderPartyName(String fkServiceProviderPartyName) {
		this.fkServiceProviderPartyName = fkServiceProviderPartyName;
	}

	public Integer getFkServiceProviderPartyId() {
		return fkServiceProviderPartyId;
	}

	public void setFkServiceProviderPartyId(Integer fkServiceProviderPartyId) {
		this.fkServiceProviderPartyId = fkServiceProviderPartyId;
	}

	public List<MasterFreightCostMatrixDtlDto> getMasterFreightCostdetailDto() {
		return masterFreightCostdetailDto;
	}

	public void setMasterFreightCostdetailDto(List<MasterFreightCostMatrixDtlDto> masterFreightCostdetailDto) {
		this.masterFreightCostdetailDto = masterFreightCostdetailDto;
	}

	public List<MasterFreightCostMatrixPartyAssocDto> getFkPartyId() {
		return fkPartyId;
	}

	public void setFkPartyId(List<MasterFreightCostMatrixPartyAssocDto> fkPartyId) {
		this.fkPartyId = fkPartyId;
	}
    

}

package com.olam.score.cost.dto;

import java.math.BigDecimal;
import java.util.Date;

public class MasterFreightCostMatrixDtlDto {
	private int pkFreightCostDetailId;
	private int fkSourceGeoId;
	private String fkSourceGeoName;
	private int fkSourceLocationId;
	private String fkSourceLocationName;
	private int fkDestinationGeoId;
	private String fkDestinationGeoName;
	private int fkDestinationLocationId;
	private String fkDestinationLocationName;
	private String isBulkInd;		
	private BigDecimal rate;		
	private int fkRateCurrencyId;
	private String fkRateCurrencyName;
	private int fkRateBasisRefId;
	private String fkRateBasisRefName; 
	private int fkRateUomId;
	private String fkRateUomName;
	private int fkRatePrimaryPackingId;
	private String fkRatePrimaryPackingName;
	private int fkRateSecondaryPacking;
	private String fkRateSecondaryPackingName;
	private int transitDays;

	private String remarks;


	private String createdBy;

	private Date createdDate;	   
	private String modifiedBy;	  
	private Date modifiedDate;
	private int freeDaysAtLoadPort;
	private int freeDaysAtDestiantionPort;
	public int getPkFreightCostDetailId() {
		return pkFreightCostDetailId;
	}
	public void setPkFreightCostDetailId(int pkFreightCostDetailId) {
		this.pkFreightCostDetailId = pkFreightCostDetailId;
	}
	public int getFkSourceGeoId() {
		return fkSourceGeoId;
	}
	public void setFkSourceGeoId(int fkSourceGeoId) {
		this.fkSourceGeoId = fkSourceGeoId;
	}
	public int getFkSourceLocationId() {
		return fkSourceLocationId;
	}
	public void setFkSourceLocationId(int fkSourceLocationId) {
		this.fkSourceLocationId = fkSourceLocationId;
	}
	public int getFkDestinationGeoId() {
		return fkDestinationGeoId;
	}
	public void setFkDestinationGeoId(int fkDestinationGeoId) {
		this.fkDestinationGeoId = fkDestinationGeoId;
	}
	public int getFkDestinationLocationId() {
		return fkDestinationLocationId;
	}
	public void setFkDestinationLocationId(int fkDestinationLocationId) {
		this.fkDestinationLocationId = fkDestinationLocationId;
	}
	public String getIsBulkInd() {
		return isBulkInd;
	}
	public void setIsBulkInd(String isBulkInd) {
		this.isBulkInd = isBulkInd;
	}
	public BigDecimal getRate() {
		return rate;
	}
	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}
	public int getFkRateCurrencyId() {
		return fkRateCurrencyId;
	}
	public void setFkRateCurrencyId(int fkRateCurrencyId) {
		this.fkRateCurrencyId = fkRateCurrencyId;
	}
	public int getFkRateBasisRefId() {
		return fkRateBasisRefId;
	}
	public void setFkRateBasisRefId(int fkRateBasisRefId) {
		this.fkRateBasisRefId = fkRateBasisRefId;
	}
	public int getFkRateUomId() {
		return fkRateUomId;
	}
	public void setFkRateUomId(int fkRateUomId) {
		this.fkRateUomId = fkRateUomId;
	}
	public int getFkRatePrimaryPackingId() {
		return fkRatePrimaryPackingId;
	}
	public void setFkRatePrimaryPackingId(int fkRatePrimaryPackingId) {
		this.fkRatePrimaryPackingId = fkRatePrimaryPackingId;
	}
	public int getFkRateSecondaryPacking() {
		return fkRateSecondaryPacking;
	}
	public void setFkRateSecondaryPacking(int fkRateSecondaryPacking) {
		this.fkRateSecondaryPacking = fkRateSecondaryPacking;
	}
	public int getTransitDays() {
		return transitDays;
	}
	public void setTransitDays(int transitDays) {
		this.transitDays = transitDays;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
	public int getFreeDaysAtLoadPort() {
		return freeDaysAtLoadPort;
	}
	public void setFreeDaysAtLoadPort(int freeDaysAtLoadPort) {
		this.freeDaysAtLoadPort = freeDaysAtLoadPort;
	}
	public int getFreeDaysAtDestiantionPort() {
		return freeDaysAtDestiantionPort;
	}
	public void setFreeDaysAtDestiantionPort(int freeDaysAtDestiantionPort) {
		this.freeDaysAtDestiantionPort = freeDaysAtDestiantionPort;
	}
	public String getFkSourceGeoName() {
		return fkSourceGeoName;
	}
	public void setFkSourceGeoName(String fkSourceGeoName) {
		this.fkSourceGeoName = fkSourceGeoName;
	}
	public String getFkSourceLocationName() {
		return fkSourceLocationName;
	}
	public void setFkSourceLocationName(String fkSourceLocationName) {
		this.fkSourceLocationName = fkSourceLocationName;
	}
	public String getFkDestinationGeoName() {
		return fkDestinationGeoName;
	}
	public void setFkDestinationGeoName(String fkDestinationGeoName) {
		this.fkDestinationGeoName = fkDestinationGeoName;
	}
	public String getFkDestinationLocationName() {
		return fkDestinationLocationName;
	}
	public void setFkDestinationLocationName(String fkDestinationLocationName) {
		this.fkDestinationLocationName = fkDestinationLocationName;
	}
	public String getFkRateCurrencyName() {
		return fkRateCurrencyName;
	}
	public void setFkRateCurrencyName(String fkRateCurrencyName) {
		this.fkRateCurrencyName = fkRateCurrencyName;
	}
	public String getFkRateBasisRefName() {
		return fkRateBasisRefName;
	}
	public void setFkRateBasisRefName(String fkRateBasisRefName) {
		this.fkRateBasisRefName = fkRateBasisRefName;
	}
	public String getFkRateUomName() {
		return fkRateUomName;
	}
	public void setFkRateUomName(String fkRateUomName) {
		this.fkRateUomName = fkRateUomName;
	}
	public String getFkRatePrimaryPackingName() {
		return fkRatePrimaryPackingName;
	}
	public void setFkRatePrimaryPackingName(String fkRatePrimaryPackingName) {
		this.fkRatePrimaryPackingName = fkRatePrimaryPackingName;
	}
	public String getFkRateSecondaryPackingName() {
		return fkRateSecondaryPackingName;
	}
	public void setFkRateSecondaryPackingName(String fkRateSecondaryPackingName) {
		this.fkRateSecondaryPackingName = fkRateSecondaryPackingName;
	}
	
}

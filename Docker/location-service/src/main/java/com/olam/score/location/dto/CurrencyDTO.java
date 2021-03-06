package com.olam.score.location.dto;

import java.util.Date;

public class CurrencyDTO  {

	private int pkCurrencyId;
	
	private int statusId;
	
	private Date createdDate;
	
	private Date modifiedDate;
	
	private String createdBy;
	
	private String currencyCode;
	
	private String currencyDesc;
	
	private String currencyName;
	
	private String currencySymbol;
	
	private String modifiedBy;
	private String action;
    
	public int getPkCurrencyId() {
		return pkCurrencyId;
	}

	public void setPkCurrencyId(int pkCurrencyId) {
		this.pkCurrencyId = pkCurrencyId;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public Date  getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date  createdDate) {
		this.createdDate = createdDate;
	}

	public Date  getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date  modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getCurrencyDesc() {
		return currencyDesc;
	}

	public void setCurrencyDesc(String currencyDesc) {
		this.currencyDesc = currencyDesc;
	}

	public String getCurrencyName() {
		return currencyName;
	}

	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}

	public String getCurrencySymbol() {
		return currencySymbol;
	}

	public void setCurrencySymbol(String currencySymbol) {
		this.currencySymbol = currencySymbol;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
}

package com.olam.score.forex.dto;

import java.util.List;

import com.olam.score.forex.model.DropDownModel;

public class ForexDto {
	
	private String forexName;
	private String forexCode;
	private String forexDesc;
	private String baseCurrency;
	private String counterCurrency;
	private String frequency;
	private String status;
	private String action;
	private List<ForexForwardTenorDto> tenorList;
	public String getForexName() {
		return forexName;
	}
	public void setForexName(String forexName) {
		this.forexName = forexName;
	}
	public String getForexCode() {
		return forexCode;
	}
	public void setForexCode(String forexCode) {
		this.forexCode = forexCode;
	}
	public String getForexDesc() {
		return forexDesc;
	}
	public void setForexDesc(String forexDesc) {
		this.forexDesc = forexDesc;
	}
	public String getBaseCurrency() {
		return baseCurrency;
	}
	public void setBaseCurrency(String baseCurrency) {
		this.baseCurrency = baseCurrency;
	}
	public String getCounterCurrency() {
		return counterCurrency;
	}
	public void setCounterCurrency(String counterCurrency) {
		this.counterCurrency = counterCurrency;
	}
	public String getFrequency() {
		return frequency;
	}
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<ForexForwardTenorDto> getTenorList() {
		return tenorList;
	}
	public void setTenorList(List<ForexForwardTenorDto> tenorList) {
		this.tenorList = tenorList;
	}
	
	
	
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}



	//For View
	private int forexId;
	private int statusId;
	
	
	public int getStatusId() {
		return statusId;
	}
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}
	public int getForexId() {
		return forexId;
	}
	public void setForexId(int forexId) {
		this.forexId = forexId;
	}
	
	
	/*
	 * Drop down list
	 */
	private List<DropDownModel> frequencyList;
	private List<DropDownModel> baseCurrencyList;
	private List<DropDownModel> counterCurrencyList;
	private List<DropDownModel> tenorDropDownList;
	private List<DropDownModel> tenorDurationTypeList;
	private List<DropDownModel> tenorDayTypeList;
	public List<DropDownModel> getFrequencyList() {
		return frequencyList;
	}
	public void setFrequencyList(List<DropDownModel> frequencyList) {
		this.frequencyList = frequencyList;
	}
	public List<DropDownModel> getBaseCurrencyList() {
		return baseCurrencyList;
	}
	public void setBaseCurrencyList(List<DropDownModel> baseCurrencyList) {
		this.baseCurrencyList = baseCurrencyList;
	}
	public List<DropDownModel> getCounterCurrencyList() {
		return counterCurrencyList;
	}
	public void setCounterCurrencyList(List<DropDownModel> counterCurrencyList) {
		this.counterCurrencyList = counterCurrencyList;
	}
	public List<DropDownModel> getTenorDropDownList() {
		return tenorDropDownList;
	}
	public void setTenorDropDownList(List<DropDownModel> tenorDropDownList) {
		this.tenorDropDownList = tenorDropDownList;
	}
	public List<DropDownModel> getTenorDurationTypeList() {
		return tenorDurationTypeList;
	}
	public void setTenorDurationTypeList(List<DropDownModel> tenorDurationTypeList) {
		this.tenorDurationTypeList = tenorDurationTypeList;
	}
	public List<DropDownModel> getTenorDayTypeList() {
		return tenorDayTypeList;
	}
	public void setTenorDayTypeList(List<DropDownModel> tenorDayTypeList) {
		this.tenorDayTypeList = tenorDayTypeList;
	}
	
	/*
	 * To validate
	 */
	
	private String toValidate;
	public String getToValidate() {
		return toValidate;
	}
	public void setToValidate(String toValidate) {
		this.toValidate = toValidate;
	}
	
	/*
	 * For delete
	 */
	private String comments;
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	
 
}

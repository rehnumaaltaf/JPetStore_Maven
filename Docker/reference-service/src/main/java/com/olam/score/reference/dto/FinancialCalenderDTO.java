
package com.olam.score.reference.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "fiscalYear",
    "startYear",
    "startMonth",
    "endYear",
    "endMonth",
    "monthName",
    "startDate",
    "endDate",
    "CTRMStatus",
    "ERPStatus",
    "action",
    "statusName",
    "pkFinCalId",
    "tenorFinancialArray"
})
public class FinancialCalenderDTO {

    @JsonProperty("fiscalYear")
    private String fiscalYear;
    @JsonProperty("startYear")
    private String startYear;
    @JsonProperty("startMonth")
    private String startMonth;
    @JsonProperty("endYear")
    private String endYear;
    @JsonProperty("endMonth")
    private String endMonth;
    @JsonProperty("monthName")
    private Object monthName;
    @JsonProperty("startDate")
    private Object startDate;
    @JsonProperty("endDate")
    private Object endDate;
    @JsonProperty("CTRMStatus")
    private Object cTRMStatus;
    @JsonProperty("ERPStatus")
    private Object eRPStatus;
    @JsonProperty("action")
    private String action;
    @JsonProperty("statusName")
    private String statusName;
    @JsonProperty("pkFinCalId")
    private String pkFinCalId;
    @JsonProperty("tenorFinancialArray")
    private List<TenorFinancialArray> tenorFinancialArray = null;
	public String getFiscalYear() {
		return fiscalYear;
	}
	public void setFiscalYear(String fiscalYear) {
		this.fiscalYear = fiscalYear;
	}
	public String getStartYear() {
		return startYear;
	}
	public void setStartYear(String startYear) {
		this.startYear = startYear;
	}
	public String getStartMonth() {
		return startMonth;
	}
	public void setStartMonth(String startMonth) {
		this.startMonth = startMonth;
	}
	public String getEndYear() {
		return endYear;
	}
	public void setEndYear(String endYear) {
		this.endYear = endYear;
	}
	public String getEndMonth() {
		return endMonth;
	}
	public void setEndMonth(String endMonth) {
		this.endMonth = endMonth;
	}
	public Object getMonthName() {
		return monthName;
	}
	public void setMonthName(Object monthName) {
		this.monthName = monthName;
	}
	public Object getStartDate() {
		return startDate;
	}
	public void setStartDate(Object startDate) {
		this.startDate = startDate;
	}
	public Object getEndDate() {
		return endDate;
	}
	public void setEndDate(Object endDate) {
		this.endDate = endDate;
	}
	public Object getcTRMStatus() {
		return cTRMStatus;
	}
	public void setcTRMStatus(Object cTRMStatus) {
		this.cTRMStatus = cTRMStatus;
	}
	public Object geteRPStatus() {
		return eRPStatus;
	}
	public void seteRPStatus(Object eRPStatus) {
		this.eRPStatus = eRPStatus;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public String getPkFinCalId() {
		return pkFinCalId;
	}
	public void setPkFinCalId(String pkFinCalId) {
		this.pkFinCalId = pkFinCalId;
	}
	public List<TenorFinancialArray> getTenorFinancialArray() {
		return tenorFinancialArray;
	}
	public void setTenorFinancialArray(List<TenorFinancialArray> tenorFinancialArray) {
		this.tenorFinancialArray = tenorFinancialArray;
	}
    
    
}

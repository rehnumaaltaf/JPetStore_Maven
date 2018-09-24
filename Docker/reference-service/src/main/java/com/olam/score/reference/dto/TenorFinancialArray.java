
 package com.olam.score.reference.dto;


import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
	"productId",
    "monthShortCode",
    "startDate",
    "endDate",
    "ctrmStatus",
    "erpStatus",
    "pkFinCalMappindId"
})
public class TenorFinancialArray {

	@JsonProperty("productId")
	private List<MasterProductDto> productId;
    @JsonProperty("monthShortCode")
    private String monthShortCode;
    @JsonProperty("startDate")
    private Date startDate;
    @JsonProperty("endDate")
    private Date endDate;
    @JsonProperty("ctrmStatus")
    private String ctrmStatus;
    @JsonProperty("erpStatus")
    private String erpStatus;
    @JsonProperty("pkFinCalMappindId")
    private String pkFinCalMappindId;
    @JsonProperty("startDateStr")
    private String startDateStr;
    @JsonProperty("endDateStr")
    private String endDateStr;
    
    private List<MasterProductDto> masterProductDto;

	
	public List<MasterProductDto> getProductId() {
		return productId;
	}
	public void setProductId(List<MasterProductDto> productId) {
		this.productId = productId;
	}
	public String getMonthShortCode() {
		return monthShortCode;
	}
	public void setMonthShortCode(String monthShortCode) {
		this.monthShortCode = monthShortCode;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getCtrmStatus() {
		return ctrmStatus;
	}
	public void setCtrmStatus(String ctrmStatus) {
		this.ctrmStatus = ctrmStatus;
	}
	public String getErpStatus() {
		return erpStatus;
	}
	public void setErpStatus(String erpStatus) {
		this.erpStatus = erpStatus;
	}
	public String getPkFinCalMappindId() {
		return pkFinCalMappindId;
	}
	public void setPkFinCalMappindId(String pkFinCalMappindId) {
		this.pkFinCalMappindId = pkFinCalMappindId;
	}
	public List<MasterProductDto> getMasterProductDto() {
		return masterProductDto;
	}
	public void setMasterProductDto(List<MasterProductDto> masterProductDto) {
		this.masterProductDto = masterProductDto;
	}
	public String getStartDateStr() {
		return startDateStr;
	}
	public void setStartDateStr(String startDateStr) {
		this.startDateStr = startDateStr;
	}
	public String getEndDateStr() {
		return endDateStr;
	}
	public void setEndDateStr(String endDateStr) {
		this.endDateStr = endDateStr;
	}
}

package com.olam.score.cost.dto;


import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@JsonPropertyOrder({
    "costName",
    "rateBasis",
    "rateUom",
    "rateCurrency",
    "rate",
    "timebasis"
})
public class WhCostDetailArray {

    
    private Integer costName;
    private String costNameValue;
    
    public String getCostNameValue() {
		return costNameValue;
	}

	public void setCostNameValue(String costNameValue) {
		this.costNameValue = costNameValue;
	}

	private Integer rateBasis;
   
    public Integer getRateUOM() {
		return rateUOM;
	}

	public void setRateUOM(Integer rateUOM) {
		this.rateUOM = rateUOM;
	}

	private Integer rateUOM;
    private Integer ratePrimaryPacking;
    private Integer rateSecondaryPacking;
    
    private String fkCurrencyName;
    private String fkUomName;
    private String fkRateBasisName;
    private String fkTimeBaisisName;
    
    
    public Integer getWhfkDefaultValueUomId() {
		return whfkDefaultValueUomId;
	}

	public void setWhfkDefaultValueUomId(Integer whfkDefaultValueUomId) {
		this.whfkDefaultValueUomId = whfkDefaultValueUomId;
	}

	public String getWhfkDefaultValueUomName() {
		return whfkDefaultValueUomName;
	}

	public void setWhfkDefaultValueUomName(String whfkDefaultValueUomName) {
		this.whfkDefaultValueUomName = whfkDefaultValueUomName;
	}

	public Integer getWhfkDefValPrimaryPackingId() {
		return whfkDefValPrimaryPackingId;
	}

	public void setWhfkDefValPrimaryPackingId(Integer whfkDefValPrimaryPackingId) {
		this.whfkDefValPrimaryPackingId = whfkDefValPrimaryPackingId;
	}

	public String getWhfkDefValPrimaryPackingName() {
		return whfkDefValPrimaryPackingName;
	}

	public void setWhfkDefValPrimaryPackingName(String whfkDefValPrimaryPackingName) {
		this.whfkDefValPrimaryPackingName = whfkDefValPrimaryPackingName;
	}

	public Integer getWhfkDefValSecondaryPackingId() {
		return whfkDefValSecondaryPackingId;
	}

	public void setWhfkDefValSecondaryPackingId(Integer whfkDefValSecondaryPackingId) {
		this.whfkDefValSecondaryPackingId = whfkDefValSecondaryPackingId;
	}

	public String getWhfkDefValSecondaryPackingName() {
		return whfkDefValSecondaryPackingName;
	}

	public void setWhfkDefValSecondaryPackingName(String whfkDefValSecondaryPackingName) {
		this.whfkDefValSecondaryPackingName = whfkDefValSecondaryPackingName;
	}

	private Integer  whfkDefaultValueUomId;
    private String whfkDefaultValueUomName;
    private Integer   whfkDefValPrimaryPackingId;
    private String  whfkDefValPrimaryPackingName;
    private Integer   whfkDefValSecondaryPackingId;
    private String  whfkDefValSecondaryPackingName;
   
    public String getFkCurrencyName() {
		return fkCurrencyName;
	}

	public void setFkCurrencyName(String fkCurrencyName) {
		this.fkCurrencyName = fkCurrencyName;
	}

	public String getFkUomName() {
		return fkUomName;
	}

	public void setFkUomName(String fkUomName) {
		this.fkUomName = fkUomName;
	}

	public String getFkRateBasisName() {
		return fkRateBasisName;
	}

	public void setFkRateBasisName(String fkRateBasisName) {
		this.fkRateBasisName = fkRateBasisName;
	}

	public String getFkTimeBaisisName() {
		return fkTimeBaisisName;
	}

	public void setFkTimeBaisisName(String fkTimeBaisisName) {
		this.fkTimeBaisisName = fkTimeBaisisName;
	}

	public Integer getRatePrimaryPacking() {
		return ratePrimaryPacking;
	}

	public void setRatePrimaryPacking(Integer ratePrimaryPacking) {
		this.ratePrimaryPacking = ratePrimaryPacking;
	}

	public Integer getRateSecondaryPacking() {
		return rateSecondaryPacking;
	}

	public void setRateSecondaryPacking(Integer rateSecondaryPacking) {
		this.rateSecondaryPacking = rateSecondaryPacking;
	}

	public void setTimeBasis(Integer timeBasis) {
		this.timeBasis = timeBasis;
	}

	private Integer rateCurrency;
   
    private BigDecimal rate;
 
    public Integer getCostName() {
		return costName;
	}

	public void setCostName(Integer costName) {
		this.costName = costName;
	}

	public Integer getRateBasis() {
		return rateBasis;
	}

	public void setRateBasis(Integer rateBasis) {
		this.rateBasis = rateBasis;
	}

	
	public Integer getRateCurrency() {
		return rateCurrency;
	}

	public void setRateCurrency(Integer rateCurrency) {
		this.rateCurrency = rateCurrency;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public Integer getTimeBasis() {
		return timeBasis;
	}

	public void setTimebasis(Integer timeBasis) {
		this.timeBasis = timeBasis;
	}

	public Integer getPkWhDetailId() {
		return pkWhDetailId;
	}

	public void setPkWhDetailId(Integer pkWhDetailId) {
		this.pkWhDetailId = pkWhDetailId;
	}

	private Integer timeBasis;
    private Integer pkWhDetailId;
    

	

}

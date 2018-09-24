package com.olam.score.cost.dto;

import java.math.BigDecimal;

public class MasterCnfCostDetailDto {
	private Integer pkCnfCostDetailId;
	public int fkFromContractTermsId;
	public String fkFromContractTermsName;
	public int fkToContractTermsId;
	public String fkToContractTermsName;
	public int fkToCountryId;
	public String fkToCountryName;
	public int fkToLocationId;
	public String fkToLocationName;
	public int fkRateBasisRefId;
	public String fkRateBasisRefName;
	public int fkRateUomId;
	public String fkRateUomName;
	public int fkRatePrimaryPackingId;
	public String fkRatePrimaryPackingName;
	public int fkRateSecondaryPacking;
	public String fkRateSecondaryPackingName;
	public BigDecimal rate;
	public int fkRateCurrencyId;
	public String fkRateCurrencyName;
	public int transitDays;
	public Integer fkCnfCostId;
	public Integer getFkCnfCostId() {
		return fkCnfCostId;
	}
	public void setFkCnfCostId(Integer fkCnfCostId) {
		this.fkCnfCostId = fkCnfCostId;
	}
	public Integer getPkCnfCostDetailId() {
		return pkCnfCostDetailId;
	}
	public void setPkCnfCostDetailId(Integer pkCnfCostDetailId) {
		this.pkCnfCostDetailId = pkCnfCostDetailId;
	}
	public int getFkFromContractTermsId() {
		return fkFromContractTermsId;
	}
	public void setFkFromContractTermsId(int fkFromContractTermsId) {
		this.fkFromContractTermsId = fkFromContractTermsId;
	}
	public int getFkToContractTermsId() {
		return fkToContractTermsId;
	}
	public void setFkToContractTermsId(int fkToContractTermsId) {
		this.fkToContractTermsId = fkToContractTermsId;
	}
	public int getFkToCountryId() {
		return fkToCountryId;
	}
	public void setFkToCountryId(int fkToCountryId) {
		this.fkToCountryId = fkToCountryId;
	}
	public int getFkToLocationId() {
		return fkToLocationId;
	}
	public void setFkToLocationId(int fkToLocationId) {
		this.fkToLocationId = fkToLocationId;
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
	public int getTransitDays() {
		return transitDays;
	}
	public void setTransitDays(int transitDays) {
		this.transitDays = transitDays;
	}
	public String getFkFromContractTermsName() {
		return fkFromContractTermsName;
	}
	public void setFkFromContractTermsName(String fkFromContractTermsName) {
		this.fkFromContractTermsName = fkFromContractTermsName;
	}
	public String getFkToContractTermsName() {
		return fkToContractTermsName;
	}
	public void setFkToContractTermsName(String fkToContractTermsName) {
		this.fkToContractTermsName = fkToContractTermsName;
	}
	public String getFkToCountryName() {
		return fkToCountryName;
	}
	public void setFkToCountryName(String fkToCountryName) {
		this.fkToCountryName = fkToCountryName;
	}
	public String getFkToLocationName() {
		return fkToLocationName;
	}
	public void setFkToLocationName(String fkToLocationName) {
		this.fkToLocationName = fkToLocationName;
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
	public String getFkRateCurrencyName() {
		return fkRateCurrencyName;
	}
	public void setFkRateCurrencyName(String fkRateCurrencyName) {
		this.fkRateCurrencyName = fkRateCurrencyName;
	}
	
	
	
}

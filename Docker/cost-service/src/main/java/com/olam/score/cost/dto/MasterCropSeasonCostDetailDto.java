package com.olam.score.cost.dto;

import java.math.BigDecimal;

public class MasterCropSeasonCostDetailDto {
	private int pkCropSeasonCostDetailId;
	private BigDecimal premiumDiscountPercentage;
	private Integer fkCropSeasonOffsetRefId;
	public int getPkCropSeasonCostDetailId() {
		return pkCropSeasonCostDetailId;
	}
	public void setPkCropSeasonCostDetailId(int pkCropSeasonCostDetailId) {
		this.pkCropSeasonCostDetailId = pkCropSeasonCostDetailId;
	}
	public BigDecimal getPremiumDiscountPercentage() {
		return premiumDiscountPercentage;
	}
	public void setPremiumDiscountPercentage(BigDecimal premiumDiscountPercentage) {
		this.premiumDiscountPercentage = premiumDiscountPercentage;
	}
	public Integer getFkCropSeasonOffsetRefId() {
		return fkCropSeasonOffsetRefId;
	}
	public void setFkCropSeasonOffsetRefId(Integer fkCropSeasonOffsetRefId) {
		this.fkCropSeasonOffsetRefId = fkCropSeasonOffsetRefId;
	}
	

}

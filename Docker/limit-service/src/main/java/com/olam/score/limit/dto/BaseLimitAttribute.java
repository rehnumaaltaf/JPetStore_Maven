package com.olam.score.limit.dto;

import java.math.BigDecimal;

public class BaseLimitAttribute {

	private Integer limitAttributeRefId;
	private Integer limitVolumeValueRefId;
	private String limitVolumeValueRefCode;
	private String limitVolumeValueRefName;
	private BigDecimal limit;
	private Integer limitCurrencyId;
	private String limitCurrencyCode;
	private String limitCurrencyName;
	private Integer limitVolumeUomId;
	private String limitVolumeUomCode;
	private String limitVolumeUomName;

	public Integer getLimitAttributeRefId() {
		return limitAttributeRefId;
	}

	public void setLimitAttributeRefId(Integer limitAttributeRefId) {
		this.limitAttributeRefId = limitAttributeRefId;
	}

	public Integer getLimitVolumeValueRefId() {
		return limitVolumeValueRefId;
	}

	public void setLimitVolumeValueRefId(Integer limitVolumeValueRefId) {
		this.limitVolumeValueRefId = limitVolumeValueRefId;
	}

	public String getLimitVolumeValueRefCode() {
		return limitVolumeValueRefCode;
	}

	public void setLimitVolumeValueRefCode(String limitVolumeValueRefCode) {
		this.limitVolumeValueRefCode = limitVolumeValueRefCode;
	}

	public String getLimitVolumeValueRefName() {
		return limitVolumeValueRefName;
	}

	public void setLimitVolumeValueRefName(String limitVolumeValueRefName) {
		this.limitVolumeValueRefName = limitVolumeValueRefName;
	}

	public BigDecimal getLimit() {
		return limit;
	}

	public void setLimit(BigDecimal limit) {
		this.limit = limit;
	}

	public Integer getLimitCurrencyId() {
		return limitCurrencyId;
	}

	public void setLimitCurrencyId(Integer limitCurrencyId) {
		this.limitCurrencyId = limitCurrencyId;
	}

	public String getLimitCurrencyCode() {
		return limitCurrencyCode;
	}

	public void setLimitCurrencyCode(String limitCurrencyCode) {
		this.limitCurrencyCode = limitCurrencyCode;
	}

	public String getLimitCurrencyName() {
		return limitCurrencyName;
	}

	public void setLimitCurrencyName(String limitCurrencyName) {
		this.limitCurrencyName = limitCurrencyName;
	}

	public Integer getLimitVolumeUomId() {
		return limitVolumeUomId;
	}

	public void setLimitVolumeUomId(Integer limitVolumeUomId) {
		this.limitVolumeUomId = limitVolumeUomId;
	}

	public String getLimitVolumeUomCode() {
		return limitVolumeUomCode;
	}

	public void setLimitVolumeUomCode(String limitVolumeUomCode) {
		this.limitVolumeUomCode = limitVolumeUomCode;
	}

	public String getLimitVolumeUomName() {
		return limitVolumeUomName;
	}

	public void setLimitVolumeUomName(String limitVolumeUomName) {
		this.limitVolumeUomName = limitVolumeUomName;
	}

}

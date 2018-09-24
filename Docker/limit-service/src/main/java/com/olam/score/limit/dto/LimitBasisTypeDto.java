package com.olam.score.limit.dto;

public class LimitBasisTypeDto {

	private Integer limitBasisTypeId;
	private String limitBasisTypeCode;
	private String limitBasisTypeName;
	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getLimitBasisTypeId() {
		return limitBasisTypeId;
	}

	public void setLimitBasisTypeId(Integer limitBasisTypeId) {
		this.limitBasisTypeId = limitBasisTypeId;
	}

	public String getLimitBasisTypeCode() {
		return limitBasisTypeCode;
	}

	public void setLimitBasisTypeCode(String limitBasisTypeCode) {
		this.limitBasisTypeCode = limitBasisTypeCode;
	}

	public String getLimitBasisTypeName() {
		return limitBasisTypeName;
	}

	public void setLimitBasisTypeName(String limitBasisTypeName) {
		this.limitBasisTypeName = limitBasisTypeName;
	}

}

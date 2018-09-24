package com.olam.score.cost.dto;


public class IntlCodeTypeRefDto {
	
	public Integer getTypeRefId() {
		return typeRefId;
	}
	public void setTypeRefId(Integer typeRefId) {
		this.typeRefId = typeRefId;
	}
	public String getIntlCodeTypeCode() {
		return intlCodeTypeCode;
	}
	public void setIntlCodeTypeCode(String intlCodeTypeCode) {
		this.intlCodeTypeCode = intlCodeTypeCode;
	}
	public String getIntlCodeTypeName() {
		return intlCodeTypeName;
	}
	public void setIntlCodeTypeName(String intlCodeTypeName) {
		this.intlCodeTypeName = intlCodeTypeName;
	}
	private Integer typeRefId;
	private String intlCodeTypeCode;
	private String intlCodeTypeName;

}

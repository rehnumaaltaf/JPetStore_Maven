package com.olam.score.cost.dto;

import com.fasterxml.jackson.annotation.JsonProperty;


public class InternationalGradeCodeMappingDto {
	
	public Integer getTableMappingId() {
		return tableMappingId;
	}
	public void setTableMappingId(Integer tableMappingId) {
		this.tableMappingId = tableMappingId;
	}
	public Integer getCodeTypeId() {
		return codeTypeId;
	}
	public void setCodeTypeId(Integer codeTypeId) {
		this.codeTypeId = codeTypeId;
	}
	public String getCodeType() {
		return codeType;
	}
	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@JsonProperty("gradeMappingId")
	private Integer tableMappingId;
	private Integer codeTypeId;
	private String codeType;
	private String code;
	@JsonProperty("codedescription")
	private String description;

}

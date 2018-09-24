package com.olam.score.cost.dto;

import com.olam.score.common.dto.BaseDto;


public class ProcessSubTypeRefDto extends BaseDto {
	
	public Integer getProcessSubTypeRefId() {
		return processSubTypeRefId;
	}
	public void setProcessSubTypeRefId(Integer processSubTypeRefId) {
		this.processSubTypeRefId = processSubTypeRefId;
	}
	public String getSubTypeCode() {
		return subTypeCode;
	}
	public void setSubTypeCode(String subTypeCode) {
		this.subTypeCode = subTypeCode;
	}
	public String getSubTypeName() {
		return subTypeName;
	}
	public void setSubTypeName(String subTypeName) {
		this.subTypeName = subTypeName;
	}
	private Integer processSubTypeRefId;
	private String subTypeCode;
	private String subTypeName;

}

package com.olam.score.finance.dto;

import com.olam.score.common.dto.BaseDto;

public class ExternalSystemRefDto extends BaseDto{

	private Integer externalSystemRefId;
	private String externalSystemRefName;
	private String externalSystemRefCode;
	
	
	public Integer getExternalSystemRefId() {
		return externalSystemRefId;
	}
	public void setExternalSystemRefId(Integer externalSystemRefId) {
		this.externalSystemRefId = externalSystemRefId;
	}
	public String getExternalSystemRefName() {
		return externalSystemRefName;
	}
	public void setExternalSystemRefName(String externalSystemRefName) {
		this.externalSystemRefName = externalSystemRefName;
	}
	public String getExternalSystemRefCode() {
		return externalSystemRefCode;
	}
	public void setExternalSystemRefCode(String externalSystemRefCode) {
		this.externalSystemRefCode = externalSystemRefCode;
	}
}

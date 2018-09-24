package com.olam.score.finance.dto;

import com.olam.score.common.dto.BaseDto;

public class GlTypeRef extends BaseDto{

	private Integer glTypeRefId;
	private String glTypeRefName;
	
	public Integer getGlTypeRefId() {
		return glTypeRefId;
	}
	public void setGlTypeRefId(Integer glTypeRefId) {
		this.glTypeRefId = glTypeRefId;
	}
	public String getGlTypeRefName() {
		return glTypeRefName;
	}
	public void setGlTypeRefName(String glTypeRefName) {
		this.glTypeRefName = glTypeRefName;
	}
}

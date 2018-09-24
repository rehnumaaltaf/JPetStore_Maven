package com.olam.score.product.dto;

import com.olam.score.common.dto.BaseDto;

public class OriginGradeDto extends BaseDto{

	private Integer originId;
	private Integer originMappingId;
	private String originCode;
	private String originName;
	public Integer getOriginId() {
		return originId;
	}
	public void setOriginId(Integer originId) {
		this.originId = originId;
	}
	public Integer getOriginMappingId() {
		return originMappingId;
	}
	public void setOriginMappingId(Integer originMappingId) {
		this.originMappingId = originMappingId;
	}
	public String getOriginCode() {
		return originCode;
	}
	public void setOriginCode(String originCode) {
		this.originCode = originCode;
	}
	public String getOriginName() {
		return originName;
	}
	public void setOriginName(String originName) {
		this.originName = originName;
	}
	
	
}

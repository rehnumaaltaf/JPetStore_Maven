package com.olam.score.product.dto;

import com.olam.score.common.dto.BaseDto;

public class ProcessTypeDto extends BaseDto {

	private Integer pkProductProcessId;
	private String processingCode;
	private String processingName;
	public Integer getPkProductProcessId() {
		return pkProductProcessId;
	}
	public void setPkProductProcessId(Integer pkProductProcessId) {
		this.pkProductProcessId = pkProductProcessId;
	}
	public String getProcessingCode() {
		return processingCode;
	}
	public void setProcessingCode(String processingCode) {
		this.processingCode = processingCode;
	}
	public String getProcessingName() {
		return processingName;
	}
	public void setProcessingName(String processingName) {
		this.processingName = processingName;
	}

}

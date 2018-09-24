package com.olam.score.forex.dto;

import java.math.BigDecimal;

public class ForexForwardTenorDto {
	
	private String ticketerCode;
	private String tenorType;
	private String tenorDurationType;
	private String tenorDayType;
	private BigDecimal span;
	private String forexForwardTenorId;
	public String getForexForwardTenorId() {
		return forexForwardTenorId;
	}
	public void setForexForwardTenorId(String forexForwardTenorId) {
		this.forexForwardTenorId = forexForwardTenorId;
	}
	public String getTicketerCode() {
		return ticketerCode;
	}
	public void setTicketerCode(String ticketCode) {
		this.ticketerCode = ticketCode;
	}
	
	public String getTenorType() {
		return tenorType;
	}
	public void setTenorType(String tenorType) {
		this.tenorType = tenorType;
	}
	public String getTenorDurationType() {
		return tenorDurationType;
	}
	public void setTenorDurationType(String tenorDurationType) {
		this.tenorDurationType = tenorDurationType;
	}
	public String getTenorDayType() {
		return tenorDayType;
	}
	public void setTenorDayType(String tenorDayType) {
		this.tenorDayType = tenorDayType;
	}
	public BigDecimal getSpan() {
		return span;
	}
	public void setSpan(BigDecimal span) {
		this.span = span;
	}
	
	
	

}

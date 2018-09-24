package com.olam.score.terms.dto;

import java.util.List;

import com.olam.score.common.dto.BaseDto;

public class BasePaymentDto extends BaseDto{
	private Integer baseTermId;
	private String baseTermName;
	private String baseTermCode;
	private String baseTermDescription;
	private String baseTermCreditRisk;
	private String baseTermLCBased;
	private List<NegotiationDto> baseNegotiationTerm;
	private Integer[] deletedNego;
	private String statusName;
	public Integer getBaseTermId() {
		return baseTermId;
	}
	public void setBaseTermId(Integer baseTermId) {
		this.baseTermId = baseTermId;
	}
	public String getBaseTermName() {
		return baseTermName;
	}
	public void setBaseTermName(String baseTermName) {
		this.baseTermName = baseTermName;
	}
	public String getBaseTermCode() {
		return baseTermCode;
	}
	public void setBaseTermCode(String baseTermCode) {
		this.baseTermCode = baseTermCode;
	}
	public String getBaseTermDescription() {
		return baseTermDescription;
	}
	public void setBaseTermDescription(String baseTermDescription) {
		this.baseTermDescription = baseTermDescription;
	}
	public String getBaseTermCreditRisk() {
		return baseTermCreditRisk;
	}
	public void setBaseTermCreditRisk(String baseTermCreditRisk) {
		this.baseTermCreditRisk = baseTermCreditRisk;
	}
	public String getBaseTermLCBased() {
		return baseTermLCBased;
	}
	public void setBaseTermLCBased(String baseTermLCBased) {
		this.baseTermLCBased = baseTermLCBased;
	}
	public List<NegotiationDto> getBaseNagotiationTerm() {
		return baseNegotiationTerm;
	}
	public void setBaseNagotiationTerm(List<NegotiationDto> baseNegotiationTerm) {
		this.baseNegotiationTerm = baseNegotiationTerm;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public Integer[] getDeletedNego() {
		return deletedNego;
	}
	public void setDeletedNego(Integer[] deletedNego) {
		this.deletedNego = deletedNego;
	}
	

}

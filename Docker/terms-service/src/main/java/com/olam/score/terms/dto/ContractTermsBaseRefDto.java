package com.olam.score.terms.dto;

import com.olam.score.common.dto.BaseDto;

public class ContractTermsBaseRefDto extends BaseDto{

	private Integer contractTermsBaseRefId;
	private String contractTermsBaseCode;
	private String contractTermsBaseName;
	private String contractTermsBaseDesc;
	private Integer statusId;
	private String statusName;
	public Integer getContractTermsBaseRefId() {
		return contractTermsBaseRefId;
	}
	public void setContractTermsBaseRefId(Integer contractTermsBaseRefId) {
		this.contractTermsBaseRefId = contractTermsBaseRefId;
	}
	public String getContractTermsBaseCode() {
		return contractTermsBaseCode;
	}
	public void setContractTermsBaseCode(String contractTermsBaseCode) {
		this.contractTermsBaseCode = contractTermsBaseCode;
	}
	public String getContractTermsBaseName() {
		return contractTermsBaseName;
	}
	public void setContractTermsBaseName(String contractTermsBaseName) {
		this.contractTermsBaseName = contractTermsBaseName;
	}
	public String getContractTermsBaseDesc() {
		return contractTermsBaseDesc;
	}
	public void setContractTermsBaseDesc(String contractTermsBaseDesc) {
		this.contractTermsBaseDesc = contractTermsBaseDesc;
	}
	public Integer getStatusId() {
		return statusId;
	}
	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	
	
}

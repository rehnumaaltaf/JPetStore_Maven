package com.olam.score.terms.dto;

import com.olam.score.common.dto.BaseDto;

public class ContractTermsBasisRefDto extends BaseDto{

	private Integer contractTermsBasisRefId;
	private String contractTermsBasisCode;
	private String contractTermsBasisName;
	private String contractTermsBasisDesc;
	private Integer statusId;
	private String statusName;
	public Integer getContractTermsBasisRefId() {
		return contractTermsBasisRefId;
	}
	public void setContractTermsBasisRefId(Integer contractTermsBasisRefId) {
		this.contractTermsBasisRefId = contractTermsBasisRefId;
	}
	public String getContractTermsBasisCode() {
		return contractTermsBasisCode;
	}
	public void setContractTermsBasisCode(String contractTermsBasisCode) {
		this.contractTermsBasisCode = contractTermsBasisCode;
	}
	public String getContractTermsBasisName() {
		return contractTermsBasisName;
	}
	public void setContractTermsBasisName(String contractTermsBasisName) {
		this.contractTermsBasisName = contractTermsBasisName;
	}
	public String getContractTermsBasisDesc() {
		return contractTermsBasisDesc;
	}
	public void setContractTermsBasisDesc(String contractTermsBasisDesc) {
		this.contractTermsBasisDesc = contractTermsBasisDesc;
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

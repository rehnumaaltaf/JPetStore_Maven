package com.olam.score.terms.dto;

import com.olam.score.common.dto.BaseDto;

public class ContrTrmsAddreduceRefDto extends BaseDto{

	private Integer contrTrmsAddreduceRefId;
	private String addReduceCode;
	private String addReduceName;
	private Integer statusId;
	private String statusName;
	public Integer getContrTrmsAddreduceRefId() {
		return contrTrmsAddreduceRefId;
	}
	public void setContrTrmsAddreduceRefId(Integer contrTrmsAddreduceRefId) {
		this.contrTrmsAddreduceRefId = contrTrmsAddreduceRefId;
	}
	public String getAddReduceCode() {
		return addReduceCode;
	}
	public void setAddReduceCode(String addReduceCode) {
		this.addReduceCode = addReduceCode;
	}
	public String getAddReduceName() {
		return addReduceName;
	}
	public void setAddReduceName(String addReduceName) {
		this.addReduceName = addReduceName;
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

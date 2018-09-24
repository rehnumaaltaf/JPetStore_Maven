package com.olam.score.cost.dto;

import java.util.List;

public class CostGroupDto {

	private Integer costGroupId;
	private String costGroupName;
	private String costGroupCode;
	private String costGroupDesc;
	private String matrixDisplayName;
	private String matrixTableName;
	private Integer statusId;
	private String statusName;
	
	
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
	public Integer getCostGroupId() {
		return costGroupId;
	}
	public void setCostGroupId(Integer costGroupId) {
		this.costGroupId = costGroupId;
	}
	public String getCostGroupName() {
		return costGroupName;
	}
	public void setCostGroupName(String costGroupName) {
		this.costGroupName = costGroupName;
	}
	public String getCostGroupCode() {
		return costGroupCode;
	}
	public void setCostGroupCode(String costGroupCode) {
		this.costGroupCode = costGroupCode;
	}
	public String getCostGroupDesc() {
		return costGroupDesc;
	}
	public void setCostGroupDesc(String costGroupDesc) {
		this.costGroupDesc = costGroupDesc;
	}
	public String getMatrixDisplayName() {
		return matrixDisplayName;
	}
	public void setMatrixDisplayName(String matrixDisplayName) {
		this.matrixDisplayName = matrixDisplayName;
	}
	public String getMatrixTableName() {
		return matrixTableName;
	}
	public void setMatrixTableName(String matrixTableName) {
		this.matrixTableName = matrixTableName;
	}
	
	
	
	
}

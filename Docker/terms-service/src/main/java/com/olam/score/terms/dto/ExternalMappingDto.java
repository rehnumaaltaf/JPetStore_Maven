package com.olam.score.terms.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExternalMappingDto {
	
	@JsonProperty("mappingId")
	private Integer tableMappingId;
	//DB field fkpaymenttermid
	private Integer paymentTermId;
	private String paymentTerm;
	
	//DB field fkexternalsystemrefid:Get the name from finance service
	private Integer externalSystemRefId;
	private String externalSystemName;
	
	private String mappingType;
	private String externalCode;
	
	private String createdBy;
	private Date createdDate;
	private String modifiedBy;
	private Date modifiedDate;
	
	//DB field fkStatusId
	private Integer statusId;
	private String status;
	
	@JsonProperty("destinationSystemId")
	private String attribute1;
	
	@JsonProperty("type")
	private String attribute2;
	
	@JsonProperty("mapping")
	private String attribute3;
	
	private String attribute4;
	private String attribute5;
	private Integer attribute6;
	private Integer attribute7;
	private Integer attribute8;
	
	public Integer getTableMappingId() {
		return tableMappingId;
	}
	public void setTableMappingId(Integer tableMappingId) {
		this.tableMappingId = tableMappingId;
	}
	public Integer getPaymentTermId() {
		return paymentTermId;
	}
	public void setPaymentTermId(Integer paymentTermId) {
		this.paymentTermId = paymentTermId;
	}
	public String getPaymentTerm() {
		return paymentTerm;
	}
	public void setPaymentTerm(String paymentTerm) {
		this.paymentTerm = paymentTerm;
	}
	public Integer getExternalSystemRefId() {
		return externalSystemRefId;
	}
	public void setExternalSystemRefId(Integer externalSystemRefId) {
		this.externalSystemRefId = externalSystemRefId;
	}
	public String getExternalSystemName() {
		return externalSystemName;
	}
	public void setExternalSystemName(String externalSystemName) {
		this.externalSystemName = externalSystemName;
	}
	public String getMappingType() {
		return mappingType;
	}
	public void setMappingType(String mappingType) {
		this.mappingType = mappingType;
	}
	public String getExternalCode() {
		return externalCode;
	}
	public void setExternalCode(String externalCode) {
		this.externalCode = externalCode;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public Integer getStatusId() {
		return statusId;
	}
	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAttribute1() {
		return attribute1;
	}
	public void setAttribute1(String attribute1) {
		this.attribute1 = attribute1;
	}
	public String getAttribute2() {
		return attribute2;
	}
	public void setAttribute2(String attribute2) {
		this.attribute2 = attribute2;
	}
	public String getAttribute3() {
		return attribute3;
	}
	public void setAttribute3(String attribute3) {
		this.attribute3 = attribute3;
	}
	public String getAttribute4() {
		return attribute4;
	}
	public void setAttribute4(String attribute4) {
		this.attribute4 = attribute4;
	}
	public String getAttribute5() {
		return attribute5;
	}
	public void setAttribute5(String attribute5) {
		this.attribute5 = attribute5;
	}
	public Integer getAttribute6() {
		return attribute6;
	}
	public void setAttribute6(Integer attribute6) {
		this.attribute6 = attribute6;
	}
	public Integer getAttribute7() {
		return attribute7;
	}
	public void setAttribute7(Integer attribute7) {
		this.attribute7 = attribute7;
	}
	public Integer getAttribute8() {
		return attribute8;
	}
	public void setAttribute8(Integer attribute8) {
		this.attribute8 = attribute8;
	}
}

package com.olam.score.product.dto;

import com.olam.score.common.dto.BaseDto;

public class BlendInputCertificationDto extends BaseDto{
	
	private Integer pkBlendInputCertificationId;
	private Integer fkBlendTemplateId;
	private Integer fkProdCertId;
	private String fkProdCertName;
	private Float certificationPercentage;
	private Integer fkBlendInputId;
	private Integer fkStatusId;
	private String statusName;
	
	public String getFkProdCertName() {
		return fkProdCertName;
	}
	public void setFkProdCertName(String fkProdCertName) {
		this.fkProdCertName = fkProdCertName;
	}
	public Integer getPkBlendInputCertificationId() {
		return pkBlendInputCertificationId;
	}
	public void setPkBlendInputCertificationId(Integer pkBlendInputCertificationId) {
		this.pkBlendInputCertificationId = pkBlendInputCertificationId;
	}
	public Integer getFkBlendTemplateId() {
		return fkBlendTemplateId;
	}
	public void setFkBlendTemplateId(Integer fkBlendTemplateId) {
		this.fkBlendTemplateId = fkBlendTemplateId;
	}
	public Integer getFkProdCertId() {
		return fkProdCertId;
	}
	public void setFkProdCertId(Integer fkProdCertId) {
		this.fkProdCertId = fkProdCertId;
	}
	public Float getCertificationPercentage() {
		return certificationPercentage;
	}
	public void setCertificationPercentage(Float certificationPercentage) {
		this.certificationPercentage = certificationPercentage;
	}
	public Integer getFkBlendInputId() {
		return fkBlendInputId;
	}
	public void setFkBlendInputId(Integer fkBlendInputId) {
		this.fkBlendInputId = fkBlendInputId;
	}
	public Integer getFkStatusId() {
		return fkStatusId;
	}
	public void setFkStatusId(Integer fkStatusId) {
		this.fkStatusId = fkStatusId;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	
	
	
}

package com.olam.score.product.dto;

import com.olam.score.common.dto.BaseDto;

public class BlendInputDto extends BaseDto {
	
	private Integer pkBlendInputId;
	private Double quantityPercentage;
	private Double certificationPercentage;
	private Integer fkGradeId;
	private Integer fkOriginId;
	private Integer fkProdId;
	private Integer fkProdCertId;
	private String fkGradeName;
	private String fkOriginName;
	private String fkProdName;
	private String fkProdCertName;
	private Integer fkStatusId;
	private String statusName;
	public String getFkGradeName() {
		return fkGradeName;
	}
	public void setFkGradeName(String fkGradeName) {
		this.fkGradeName = fkGradeName;
	}
	public String getFkOriginName() {
		return fkOriginName;
	}
	public void setFkOriginName(String fkOriginName) {
		this.fkOriginName = fkOriginName;
	}
	public String getFkProdName() {
		return fkProdName;
	}
	public void setFkProdName(String fkProdName) {
		this.fkProdName = fkProdName;
	}
	public String getFkProdCertName() {
		return fkProdCertName;
	}
	public void setFkProdCertName(String fkProdCertName) {
		this.fkProdCertName = fkProdCertName;
	}
	public Integer getPkBlendInputId() {
		return pkBlendInputId;
	}
	public void setPkBlendInputId(Integer pkBlendInputId) {
		this.pkBlendInputId = pkBlendInputId;
	}
	public Double getQuantityPercentage() {
		return quantityPercentage;
	}
	public void setQuantityPercentage(Double quantityPercentage) {
		this.quantityPercentage = quantityPercentage;
	}
	public Double getCertificationPercentage() {
		return certificationPercentage;
	}
	public void setCertificationPercentage(Double certificationPercentage) {
		this.certificationPercentage = certificationPercentage;
	}
	public Integer getFkGradeId() {
		return fkGradeId;
	}
	public void setFkGradeId(Integer fkGradeId) {
		this.fkGradeId = fkGradeId;
	}
	public Integer getFkOriginId() {
		return fkOriginId;
	}
	public void setFkOriginId(Integer fkOriginId) {
		this.fkOriginId = fkOriginId;
	}
	public Integer getFkProdId() {
		return fkProdId;
	}
	public void setFkProdId(Integer fkProdId) {
		this.fkProdId = fkProdId;
	}
	public Integer getFkProdCertId() {
		return fkProdCertId;
	}
	public void setFkProdCertId(Integer fkProdCertId) {
		this.fkProdCertId = fkProdCertId;
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

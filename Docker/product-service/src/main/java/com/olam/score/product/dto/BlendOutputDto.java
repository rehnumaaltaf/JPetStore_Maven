package com.olam.score.product.dto;

import com.olam.score.common.dto.BaseDto;

public class BlendOutputDto extends BaseDto {
	
	private Integer pkBlendOutputId;
	private String brandName;
	private Double quantityPercentage;
	private Double abilityToBearRatio;
	private Double certificationPercentage;
	private Integer fkBrandId;
	private Integer fkGradeId;
	private Integer fkOriginId;
	private Integer fkProdId;
	private Integer fkProdCertId;
	private Integer fkStatusId;
	private String fkGradeName;
	private String fkOriginName;
	private String fkProdName;
	private String fkProdCertName;

	private String statusName;
	private Integer fkBlendTemplateId;
	
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
	public Integer getPkBlendOutputId() {
		return pkBlendOutputId;
	}
	public void setPkBlendOutputId(Integer pkBlendOutputId) {
		this.pkBlendOutputId = pkBlendOutputId;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public Double getQuantityPercentage() {
		return quantityPercentage;
	}
	public void setQuantityPercentage(Double quantityPercentage) {
		this.quantityPercentage = quantityPercentage;
	}
	public Double getAbilityToBearRatio() {
		return abilityToBearRatio;
	}
	public void setAbilityToBearRatio(Double abilityToBearRatio) {
		this.abilityToBearRatio = abilityToBearRatio;
	}
	public Double getCertificationPercentage() {
		return certificationPercentage;
	}
	public void setCertificationPercentage(Double certificationPercentage) {
		this.certificationPercentage = certificationPercentage;
	}
	public Integer getFkBrandId() {
		return fkBrandId;
	}
	public void setFkBrandId(Integer fkBrandId) {
		this.fkBrandId = fkBrandId;
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
	public Integer getFkBlendTemplateId() {
		return fkBlendTemplateId;
	}
	public void setFkBlendTemplateId(Integer fkBlendTemplateId) {
		this.fkBlendTemplateId = fkBlendTemplateId;
	}
	
	

}

package com.olam.score.product.dto;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.olam.score.common.dto.BaseDto;

public class OutturnRatioDto extends BaseDto{
	
	private Integer outturnRatioId;
	
	private Integer outturnRawGradeId;
	@NotNull(message = "notNull_finishedGradeId")
	private Integer finishedGradeId;
	private String finishedGradeName;
	private String finishedGradeCode;
	@NotNull(message = "notNull_quantityRatio")
	private Double quantityRatio;
	@NotNull(message = "notNull_abilityToBearRatio")
	private Double abilityToBearRatio;
	
	private Integer statusId;
	
	private String statusName;

	public Integer getOutturnRatioId() {
		return outturnRatioId;
	}

	public void setOutturnRatioId(Integer outturnRatioId) {
		this.outturnRatioId = outturnRatioId;
	}

	public Integer getOutturnRawGradeId() {
		return outturnRawGradeId;
	}

	public void setOutturnRawGradeId(Integer outturnRawGradeId) {
		this.outturnRawGradeId = outturnRawGradeId;
	}

	

	/*public Integer getGradeId() {
		return gradeId;
	}

	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}*/

	public Integer getFinishedGradeId() {
		return finishedGradeId;
	}

	public void setFinishedGradeId(Integer finishedGradeId) {
		this.finishedGradeId = finishedGradeId;
	}

	public Double getQuantityRatio() {
		return quantityRatio;
	}

	public void setQuantityRatio(Double quantityRatio) {
		this.quantityRatio = quantityRatio;
	}

	public Double getAbilityToBearRatio() {
		return abilityToBearRatio;
	}

	public void setAbilityToBearRatio(Double abilityToBearRatio) {
		this.abilityToBearRatio = abilityToBearRatio;
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

	public String getFinishedGradeName() {
		return finishedGradeName;
	}

	public void setFinishedGradeName(String finishedGradeName) {
		this.finishedGradeName = finishedGradeName;
	}

	public String getFinishedGradeCode() {
		return finishedGradeCode;
	}

	public void setFinishedGradeCode(String finishedGradeCode) {
		this.finishedGradeCode = finishedGradeCode;
	}

	
	
	
	

}

package com.olam.score.terms.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.olam.score.terms.domain.MasterFranchiseValueUnit;
import com.olam.score.terms.domain.MasterToleranceValueUnit;

import io.swagger.annotations.ApiModelProperty;

public class WeightTermDto {

	// @NotBlank, @Digits, @Email, @Max, @Min validations can also be used
	@ApiModelProperty(notes = "The database generated UOM ID")

	private Integer weightTermsId;

	@NotNull(message = "notNull_weightTermsCode")
	@NotEmpty(message = "mandatory_weightTermsCode")
	@ApiModelProperty(notes = "Weight Terms Code", required = true)
	private String weightTermsCode;

	@NotNull(message = "notNull_weightTermsName")
	@NotEmpty(message = "mandatory_weightTermsName")
	@ApiModelProperty(notes = "Weight Terms Name", required = true)
	private String weightTermsName;
	private String weightTermsDesc;

	private Double weightTermsDefaultFranchiseValue;
	private Double weightTermsDefaultToleranceValue;

	private Integer fkStatusId;
	private String statusName;

	/*// for Dto
	private FranchiseDto franchiseDto;

	private ToleranceDto toleranceDto;*/

	// Fields for dropbox
	private String weightTermsIsFranchiseApplicable;
	// private MasterFranchiseValueUnit fkFranchiseValueUnitId;
	private Integer franchiseValueUnitId;
	private String weightTermsIsToleranceApplicable;
	// private MasterToleranceValueUnit fkToleranceValueUnitId;
	private Integer toleranceValueUnitId;

	private String toleranceValueUnitName;
	private String franchiseValueUnitName;

	private String franchiseValueUnitCode;
	private String toleranceValueUnitCode;
	List<Integer> deletedIds;

	public String getFranchiseValueUnitCode() {
		return franchiseValueUnitCode;
	}

	public void setFranchiseValueUnitCode(String franchiseValueUnitCode) {
		this.franchiseValueUnitCode = franchiseValueUnitCode;
	}

	public String getToleranceValueUnitCode() {
		return toleranceValueUnitCode;
	}

	public void setToleranceValueUnitCode(String toleranceValueUnitCode) {
		this.toleranceValueUnitCode = toleranceValueUnitCode;
	}

	/*public ToleranceDto getToleranceDto() {
		return toleranceDto;
	}

	public void setToleranceDto(ToleranceDto toleranceDto) {
		this.toleranceDto = toleranceDto;
	}

	public FranchiseDto getFranchiseDto() {
		return franchiseDto;
	}

	public void setFranchiseDto(FranchiseDto franchiseDto) {
		this.franchiseDto = franchiseDto;
	}*/

	public List<Integer> getDeletedIds() {
		return deletedIds;
	}

	public void setDeletedIds(List<Integer> deletedIds) {
		this.deletedIds = deletedIds;
	}

	/*
	 * public MasterToleranceValueUnit getToleranceValueUnitName() { return
	 * toleranceValueUnitName; } public void
	 * setToleranceValueUnitName(MasterToleranceValueUnit toleranceValueUnitName) {
	 * this.toleranceValueUnitName = toleranceValueUnitName; } public
	 * MasterFranchiseValueUnit getFranchiseValueUnitName() { return
	 * franchiseValueUnitName; } public void
	 * setFranchiseValueUnitName(MasterFranchiseValueUnit franchiseValueUnitName) {
	 * this.franchiseValueUnitName = franchiseValueUnitName; }
	 */

	public String getFranchiseValueUnitName() {
		return franchiseValueUnitName;
	}

	public void setFranchiseValueUnitName(String franchiseValueUnitName) {
		this.franchiseValueUnitName = franchiseValueUnitName;
	}

	public String getToleranceValueUnitName() {
		return toleranceValueUnitName;
	}

	public void setToleranceValueUnitName(String toleranceValueUnitName) {
		this.toleranceValueUnitName = toleranceValueUnitName;
	}

	public String getWeightTermsIsFranchiseApplicable() {
		return weightTermsIsFranchiseApplicable;
	}

	public void setWeightTermsIsFranchiseApplicable(String weightTermsIsFranchiseApplicable) {
		this.weightTermsIsFranchiseApplicable = weightTermsIsFranchiseApplicable;
	}
	/*
	 * public MasterFranchiseValueUnit getFkFranchiseValueUnitId() { return
	 * fkFranchiseValueUnitId; } public void
	 * setFkFranchiseValueUnitId(MasterFranchiseValueUnit fkFranchiseValueUnitId) {
	 * this.fkFranchiseValueUnitId = fkFranchiseValueUnitId; }
	 */

	public String getWeightTermsIsToleranceApplicable() {
		return weightTermsIsToleranceApplicable;
	}

	public Integer getFranchiseValueUnitId() {
		return franchiseValueUnitId;
	}

	public void setFranchiseValueUnitId(Integer franchiseValueUnitId) {
		this.franchiseValueUnitId = franchiseValueUnitId;
	}

	public Integer getToleranceValueUnitId() {
		return toleranceValueUnitId;
	}

	public void setToleranceValueUnitId(Integer toleranceValueUnitId) {
		this.toleranceValueUnitId = toleranceValueUnitId;
	}

	public void setWeightTermsIsToleranceApplicable(String weightTermsIsToleranceApplicable) {
		this.weightTermsIsToleranceApplicable = weightTermsIsToleranceApplicable;
	}
	/*
	 * public MasterToleranceValueUnit getFkToleranceValueUnitId() { return
	 * fkToleranceValueUnitId; } public void
	 * setFkToleranceValueUnitId(MasterToleranceValueUnit fkToleranceValueUnitId) {
	 * this.fkToleranceValueUnitId = fkToleranceValueUnitId; }
	 */

	public Integer getWeightTermsId() {
		return weightTermsId;
	}

	public void setWeightTermsId(Integer weightTermsId) {
		this.weightTermsId = weightTermsId;
	}

	public String getWeightTermsCode() {
		return weightTermsCode;
	}

	public void setWeightTermsCode(String weightTermsCode) {
		this.weightTermsCode = weightTermsCode;
	}

	public String getWeightTermsName() {
		return weightTermsName;
	}

	public void setWeightTermsName(String weightTermsName) {
		this.weightTermsName = weightTermsName;
	}

	public String getWeightTermsDesc() {
		return weightTermsDesc;
	}

	public void setWeightTermsDesc(String weightTermsDesc) {
		this.weightTermsDesc = weightTermsDesc;
	}

	public Double getWeightTermsDefaultFranchiseValue() {
		return weightTermsDefaultFranchiseValue;
	}

	public void setWeightTermsDefaultFranchiseValue(Double weightTermsDefaultFranchiseValue) {
		this.weightTermsDefaultFranchiseValue = weightTermsDefaultFranchiseValue;
	}

	public Double getWeightTermsDefaultToleranceValue() {
		return weightTermsDefaultToleranceValue;
	}

	public void setWeightTermsDefaultToleranceValue(Double weightTermsDefaultToleranceValue) {
		this.weightTermsDefaultToleranceValue = weightTermsDefaultToleranceValue;
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

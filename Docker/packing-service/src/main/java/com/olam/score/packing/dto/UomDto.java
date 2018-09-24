package com.olam.score.packing.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.olam.score.common.dto.BaseDto;

import io.swagger.annotations.ApiModelProperty;

public class UomDto extends BaseDto{

	// @NotBlank, @Digits, @Email, @Max, @Min validations can also be used
	@ApiModelProperty(notes = "The database generated UOM ID")
	private Integer uomId;
	@ApiModelProperty(notes = "Status of UOM")
	private String statusName;
	@JsonIgnore
	private UomDto fkBaseUomId;
	private Integer parentUomId;
	@ApiModelProperty(notes = "Parent UOM")
	private String baseUomCode;
	@NotNull(message = "notNull_uomCode") 	@ApiModelProperty(notes = "UOM Code",required=true)
	private String uomCode;
	private BigDecimal uomConversionFactor;
	@ApiModelProperty(notes = "UOM Full name")
	private String uomFullname;
	@NotNull(message = "notNull_uomName") @ApiModelProperty(notes = "UOM name",required=true)
	private String uomName;
	private String uomBasis;
	public Integer getUomId() {
		return uomId;
	}

	public void setUomId(Integer uomId) {
		this.uomId = uomId;
	}

	public UomDto getFkBaseUomId() {
		return fkBaseUomId;
	}

	public void setFkBaseUomId(UomDto fkBaseUomId) {
		this.fkBaseUomId = fkBaseUomId;
	}

	public String getUomCode() {
		return uomCode;
	}

	public void setUomCode(String uomCode) {
		this.uomCode = uomCode;
	}

	public BigDecimal getUomConversionFactor() {
		return uomConversionFactor;
	}

	public void setUomConversionFactor(BigDecimal uomConversionFactor) {
		this.uomConversionFactor = uomConversionFactor;
	}

	public String getUomFullname() {
		return uomFullname;
	}

	public void setUomFullname(String uomFullname) {
		this.uomFullname = uomFullname;
	}

	public String getUomName() {
		return uomName;
	}

	public void setUomName(String uomName) {
		this.uomName = uomName;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public Integer getParentUomId() {
		return parentUomId;
	}

	public void setParentUomId(Integer parentUomId) {
		this.parentUomId = parentUomId;
	}
	public String getBaseUomCode() {
		return baseUomCode;
	}

	public void setBaseUomCode(String baseUomCode) {
		this.baseUomCode = baseUomCode;
	}

	public String getUomBasis() {
		return uomBasis;
	}

	public void setUomBasis(String uomBasis) {
		this.uomBasis = uomBasis;
	}

}


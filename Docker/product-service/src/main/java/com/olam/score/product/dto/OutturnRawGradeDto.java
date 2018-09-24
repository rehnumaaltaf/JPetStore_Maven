package com.olam.score.product.dto;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.olam.score.common.dto.BaseDto;

public class OutturnRawGradeDto extends BaseDto {

	private Integer outturnRawGradeId;
	private String rawGradeCode;
	private String rawGradeName;
	private String rawGradeFullname;
	@NotNull(message = "notNull_prodId")
	private Integer prodId;
	private String prodCode;
	private String prodName;
	@NotNull(message = "notNull_originId")
	private Integer originId;
	private String originCode;
	private String originName;
	@NotNull(message = "notNull_gradeId")
	private Integer gradeId;
	private String gradeName;
	private Integer statusId;

	public Integer getOutturnRawGradeId() {
		return outturnRawGradeId;
	}

	public void setOutturnRawGradeId(Integer outturnRawGradeId) {
		this.outturnRawGradeId = outturnRawGradeId;
	}

	public String getRawGradeCode() {
		return rawGradeCode;
	}

	public void setRawGradeCode(String rawGradeCode) {
		this.rawGradeCode = rawGradeCode;
	}

	public String getRawGradeName() {
		return rawGradeName;
	}

	public void setRawGradeName(String rawGradeName) {
		this.rawGradeName = rawGradeName;
	}

	public String getRawGradeFullname() {
		return rawGradeFullname;
	}

	public void setRawGradeFullname(String rawGradeFullname) {
		this.rawGradeFullname = rawGradeFullname;
	}

	public Integer getProdId() {
		return prodId;
	}

	public void setProdId(Integer prodId) {
		this.prodId = prodId;
	}

	public String getProdCode() {
		return prodCode;
	}

	public void setProdCode(String prodCode) {
		this.prodCode = prodCode;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public Integer getOriginId() {
		return originId;
	}

	public void setOriginId(Integer originId) {
		this.originId = originId;
	}

	public String getOriginCode() {
		return originCode;
	}

	public void setOriginCode(String originCode) {
		this.originCode = originCode;
	}

	public String getOriginName() {
		return originName;
	}

	public void setOriginName(String originName) {
		this.originName = originName;
	}

	public Integer getGradeId() {
		return gradeId;
	}

	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	public Integer getStatusId() {
		return statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

}

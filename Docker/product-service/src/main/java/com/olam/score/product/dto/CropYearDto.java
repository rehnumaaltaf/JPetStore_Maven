package com.olam.score.product.dto;

import java.sql.Timestamp;
import java.util.List;

import com.olam.score.common.constants.Constants;
import com.olam.score.common.dto.BaseDto;
import com.olam.score.product.domain.MasterCropYear;

public class CropYearDto extends BaseDto {

	private Integer cropYearId;
	private String cropYearName;
	private String cropYearCode;
	private String cropYearDescription;
	private List<CropYearProductDto> cropYearProductDto;
	private Integer statusId;
	private String statusName;

	public Integer getCropYearId() {
		return cropYearId;
	}

	public void setCropYearId(Integer cropYearId) {
		this.cropYearId = cropYearId;
	}

	public String getCropYearName() {
		return cropYearName;
	}

	public void setCropYearName(String cropYearName) {
		this.cropYearName = cropYearName;
	}

	public String getCropYearCode() {
		return cropYearCode;
	}

	public void setCropYearCode(String cropYearCode) {
		this.cropYearCode = cropYearCode;
	}

	public String getCropYearDescription() {
		return cropYearDescription;
	}

	public void setCropYearDescription(String cropYearDescription) {
		this.cropYearDescription = cropYearDescription;
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

	public List<CropYearProductDto> getCropYearProductDto() {
		return cropYearProductDto;
	}

	public void setCropYearProductDto(List<CropYearProductDto> cropYearProductDto) {
		this.cropYearProductDto = cropYearProductDto;
	}

	public MasterCropYear cropYearDtoToModel(Integer statusId, String action) {

		MasterCropYear masterCropYear = new MasterCropYear();
		masterCropYear.setCropYearCode(getCropYearCode());
		masterCropYear.setCropYearName(getCropYearName());
		masterCropYear.setCropYearDesc(getCropYearDescription());
		masterCropYear.setFkStatusId(statusId);

		if (Constants.CREATE.equalsIgnoreCase(action)) {
			masterCropYear.setCreatedBy("Test");// TODO: Hard Coding needs
												// changes
			masterCropYear.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		} else if (Constants.UPDATE.equalsIgnoreCase(action)) {
			masterCropYear.setCreatedBy("Test");// TODO: Hard Coding needs
												// changes
			masterCropYear.setCreatedDate(new Timestamp(System.currentTimeMillis()));
			masterCropYear.setPkCropYearId(getCropYearId());
			masterCropYear.setModifiedBy("Test");// TODO: Hard Coding needs
													// changes
			masterCropYear.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		}
		return masterCropYear;
	}

}

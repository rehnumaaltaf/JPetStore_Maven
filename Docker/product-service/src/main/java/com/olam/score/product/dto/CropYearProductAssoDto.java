package com.olam.score.product.dto;

public class CropYearProductAssoDto {

	private Integer cropYearProductAssoId;
	private CropYearDto cropYearDto;
	private CropYearProductDto productDto;
	private Integer statusId;
	private String statusName;

	public Integer getCropYearProductAssoId() {
		return cropYearProductAssoId;
	}

	public void setCropYearProductAssoId(Integer cropYearProductAssoId) {
		this.cropYearProductAssoId = cropYearProductAssoId;
	}

	public CropYearDto getCropYearDto() {
		return cropYearDto;
	}

	public void setCropYearDto(CropYearDto cropYearDto) {
		this.cropYearDto = cropYearDto;
	}

	public CropYearProductDto getProductDto() {
		return productDto;
	}

	public void setProductDto(CropYearProductDto productDto) {
		this.productDto = productDto;
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

}

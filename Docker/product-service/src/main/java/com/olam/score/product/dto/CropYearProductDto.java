package com.olam.score.product.dto;

public class CropYearProductDto {

	private Integer cropYearProductAssoId;
	private Integer prodId;
	private String prodCode;
	private String prodName;

	public Integer getCropYearProductAssoId() {
		return cropYearProductAssoId;
	}

	public void setCropYearProductAssoId(Integer cropYearProductAssoId) {
		this.cropYearProductAssoId = cropYearProductAssoId;
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

}

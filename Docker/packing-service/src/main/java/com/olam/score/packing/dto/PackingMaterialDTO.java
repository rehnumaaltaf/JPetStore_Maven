package com.olam.score.packing.dto;

import java.math.BigDecimal;

public class PackingMaterialDTO {

	private Integer pkPackingMaterialId;
	private String packingMaterialCode;
	private String packingMaterialName;
	private String packingMaterialDesc;
	private BigDecimal packingMaterialWeight;
	private String packingMaterialIsBulk;
	private String uomValue;
	private String uomName;
	private String statusName;
	private String action;
	private String toValidate;

	public String getUomName() {
		return uomName;
	}

	public void setUomName(String uomName) {
		this.uomName = uomName;
	}

	public String getToValidate() {
		return toValidate;
	}

	public void setToValidate(String toValidate) {
		this.toValidate = toValidate;
	}

	public String getPackingMaterialIsBulk() {
		return packingMaterialIsBulk;
	}

	public void setPackingMaterialIsBulk(String packingMaterialIsBulk) {
		this.packingMaterialIsBulk = packingMaterialIsBulk;
	}

	public Integer getPkPackingMaterialId() {
		return pkPackingMaterialId;
	}

	public void setPkPackingMaterialId(Integer pkPackingMaterialId) {
		this.pkPackingMaterialId = pkPackingMaterialId;
	}

	public String getPackingMaterialCode() {
		return packingMaterialCode;
	}

	public void setPackingMaterialCode(String packingMaterialCode) {
		this.packingMaterialCode = packingMaterialCode;
	}

	public String getPackingMaterialName() {
		return packingMaterialName;
	}

	public void setPackingMaterialName(String packingMaterialName) {
		this.packingMaterialName = packingMaterialName;
	}

	public String getPackingMaterialDesc() {
		return packingMaterialDesc;
	}

	public void setPackingMaterialDesc(String packingMaterialDesc) {
		this.packingMaterialDesc = packingMaterialDesc;
	}

	public BigDecimal getPackingMaterialWeight() {
		return packingMaterialWeight;
	}

	public void setPackingMaterialWeight(BigDecimal packingMaterialWeight) {
		this.packingMaterialWeight = packingMaterialWeight;
	}

	public String getUomValue() {
		return uomValue;
	}

	public void setUomValue(String uomValue) {
		this.uomValue = uomValue;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

}

package com.olam.score.product.dto;

import java.sql.Timestamp;

public class OriginDto {

	private int pkOriginId;

	private String originFullName;

	private String originName;

	private String geoName;

	private int fkGeoId;

	private String originCode;

	private String createdBy;

	private Timestamp createdDate;

	private String modifiedBy;

	private Timestamp modifiedDate;

	private int fkStatusId;

	private String statusName;

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getGeoName() {
		return geoName;
	}

	public void setGeoName(String geoName) {
		this.geoName = geoName;
	}

	public int getPkOriginId() {
		return pkOriginId;
	}

	public void setPkOriginId(int pkOriginId) {
		this.pkOriginId = pkOriginId;
	}

	public String getOriginFullName() {
		return originFullName;
	}

	public void setOriginFullName(String originFullName) {
		this.originFullName = originFullName;
	}

	public String getOriginName() {
		return originName;
	}

	public void setOriginName(String originName) {
		this.originName = originName;
	}

	public int getFkGeoId() {
		return fkGeoId;
	}

	public void setFkGeoId(int fkGeoId) {
		this.fkGeoId = fkGeoId;
	}

	public String getOriginCode() {
		return originCode;
	}

	public void setOriginCode(String originCode) {
		this.originCode = originCode;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Timestamp getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public int getFkStatusId() {
		return fkStatusId;
	}

	public void setFkStatusId(int fkStatusId) {
		this.fkStatusId = fkStatusId;
	}

}
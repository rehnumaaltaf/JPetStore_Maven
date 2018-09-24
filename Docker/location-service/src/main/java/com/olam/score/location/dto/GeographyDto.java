package com.olam.score.location.dto;

import java.sql.Timestamp;

import javax.validation.constraints.NotNull;

import com.olam.score.common.constants.Constants;
import com.olam.score.common.dto.BaseDto;
import com.olam.score.location.domain.MasterGeography;
import com.olam.score.location.domain.MasterGeographyLevel;

public class GeographyDto extends BaseDto {

	private Integer pkGeoId;
	@NotNull
	private String geoName;
	@NotNull
	private String geoCode;
	private String geoDescription;
	@NotNull
	private Integer geoTypeId;
	private String geoTypeCode;
	private String geoTypeName;
	private Integer marketDeskId;
	private String marketDeskCode;
	private String marketDeskName;
	private Integer geoParentId;
	private String geoParentCode;
	private String geoParentName;
	private Integer statusId;
	private String statusName;

	public Integer getPkGeoId() {
		return pkGeoId;
	}

	public void setPkGeoId(Integer pkGeoId) {
		this.pkGeoId = pkGeoId;
	}

	public String getGeoName() {
		return geoName;
	}

	public void setGeoName(String geoName) {
		this.geoName = geoName;
	}

	public String getGeoCode() {
		return geoCode;
	}

	public void setGeoCode(String geoCode) {
		this.geoCode = geoCode;
	}

	public String getGeoDescription() {
		return geoDescription;
	}

	public void setGeoDescription(String geoDescription) {
		this.geoDescription = geoDescription;
	}

	public Integer getGeoTypeId() {
		return geoTypeId;
	}

	public void setGeoTypeId(Integer geoTypeId) {
		this.geoTypeId = geoTypeId;
	}

	public String getGeoTypeCode() {
		return geoTypeCode;
	}

	public void setGeoTypeCode(String geoTypeCode) {
		this.geoTypeCode = geoTypeCode;
	}

	public String getGeoTypeName() {
		return geoTypeName;
	}

	public void setGeoTypeName(String geoTypeName) {
		this.geoTypeName = geoTypeName;
	}

	public Integer getMarketDeskId() {
		return marketDeskId;
	}

	public void setMarketDeskId(Integer marketDeskId) {
		this.marketDeskId = marketDeskId;
	}

	public String getMarketDeskCode() {
		return marketDeskCode;
	}

	public void setMarketDeskCode(String marketDeskCode) {
		this.marketDeskCode = marketDeskCode;
	}

	public String getMarketDeskName() {
		return marketDeskName;
	}

	public void setMarketDeskName(String marketDeskName) {
		this.marketDeskName = marketDeskName;
	}

	public String getGeoParentCode() {
		return geoParentCode;
	}

	public void setGeoParentCode(String geoParentCode) {
		this.geoParentCode = geoParentCode;
	}

	public Integer getGeoParentId() {
		return geoParentId;
	}

	public void setGeoParentId(Integer geoParentId) {
		this.geoParentId = geoParentId;
	}

	public String getGeoParentName() {
		return geoParentName;
	}

	public void setGeoParentName(String geoParentName) {
		this.geoParentName = geoParentName;
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

	public MasterGeography geographyDtoToModel(String action, Integer statusId, MasterGeographyLevel geoType, MasterGeography parentGeo) {
	
		MasterGeography masterGeography = new MasterGeography();
		masterGeography.setGeoCode(geoCode);
		masterGeography.setGeoName(geoName);
		masterGeography.setGeoFullname(geoDescription);
		masterGeography.setFkMarketDeskId(marketDeskId);
		masterGeography.setFkStatusId(statusId);
		masterGeography.setFkGeoLevelId(geoType);
		if(parentGeo!=null){
		masterGeography.setFkParentGeoId(parentGeo);
		}
		if (Constants.CREATE.equalsIgnoreCase(action)) {
			masterGeography.setCreatedBy("Test");// TODO: Hard Coding needs
			masterGeography.setCreatedDate(new Timestamp(System.currentTimeMillis()));
			masterGeography.setModifiedBy("Test");// TODO: Hard Coding needs
			masterGeography.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		} else if (Constants.UPDATE.equalsIgnoreCase(action)) {
			masterGeography.setPkGeoId(pkGeoId);
			masterGeography.setModifiedBy("Test");// TODO: Hard Coding needs
			masterGeography.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		}
		return masterGeography;
	}

}

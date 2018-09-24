package com.olam.score.location.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.olam.score.common.domain.DropDownModel;
import com.olam.score.common.dto.BaseDto;;

public class LocationDTO extends BaseDto{

	private Integer pkLocId; 
	private Integer pkLocLocTypeAssocId;
	private String locName;
	private String locCode;
	private String locFullName;
	private int fkGeoId;
	private String fkGeoName;
	private Integer fkLocTypeId;
	private String fkLocTypeName;
	private int fkStatusId;
	private String statusName;
	private List<DropDownModel> locCountryList = new ArrayList<>();
	private List<DropDownModel> locationTypeList = new ArrayList<>();
	private float rate;
	private Integer fkCurrencyId;
	private Integer fkRateBasisRefId;
	private Integer rateBasisUomId;
	private Integer rateBasisPrimaryPackingId;
	private Integer rateBasisSecondaryPackingId;
	private String fkCurrencyName;
	private Integer rateBasis;
	private String rateBasisUom;
	private String rateBasisPrimaryPacking;
	private String rateBasisSecondaryPacking;
	private String fkRateBasisRefName ;

	public Integer getRateBasis() {
		return rateBasis;
	}

	public void setRateBasis(Integer rateBasis) {
		this.rateBasis = rateBasis;
	}

	public String getFkLocTypeName() {
		return fkLocTypeName;
	}

	public void setFkLocTypeName(String fkLocTypeName) {
		this.fkLocTypeName = fkLocTypeName;
	}

	public Integer getFkLocTypeId() {
		return fkLocTypeId;
	}

	public void setFkLocTypeId(Integer fkLocTypeId) {
		this.fkLocTypeId = fkLocTypeId;
	}

	public Integer getPkLocId() {
		return pkLocId;
	}

	public void setPkLocId(Integer pkLocId) {
		this.pkLocId = pkLocId;
	}

	public String getLocName() {
		return locName;
	}

	public void setLocName(String locName) {
		this.locName = locName;
	}

	public String getLocCode() {
		return locCode;
	}

	public void setLocCode(String locCode) {
		this.locCode = locCode;
	}

	public String getLocFullName() {
		return locFullName;
	}

	public void setLocFullname(String locFullName) {
		this.locFullName = locFullName;
	}

	public int getFkGeoId() {
		return fkGeoId;
	}

	public void setFkGeoId(int fkGeoId) {
		this.fkGeoId = fkGeoId;
	}

	

	public int getFkStatusId() {
		return fkStatusId;
	}

	public void setFkStatusId(int fkStatusId) {
		this.fkStatusId = fkStatusId;
	}

	public List<DropDownModel> getLocCountryList() {
		return locCountryList;
	}

	public void setLocCountryList(List<DropDownModel> locCountryList) {
		this.locCountryList = locCountryList;
	}

	public List<DropDownModel> getLocationTypeList() {
		return locationTypeList;
	}

	public void setLocationTypeList(List<DropDownModel> locationTypeList) {
		this.locationTypeList = locationTypeList;
	}

	public String getFkGeoName() {
		return fkGeoName;
	}

	public void setFkGeoName(String fkGeoName) {
		this.fkGeoName = fkGeoName;
	}

	

	public Integer getPkLocLocTypeAssocId() {
		return pkLocLocTypeAssocId;
	}

	public void setPkLocLocTypeAssocId(Integer pkLocLocTypeAssocId) {
		this.pkLocLocTypeAssocId = pkLocLocTypeAssocId;
	}
	
	public Float getRate() {
		return rate;
	}

	public void setRate(Float rate) {
		this.rate = rate;
	}

	public Integer getFkCurrencyId() {
		return fkCurrencyId;
	}

	public void setFkCurrencyId(Integer fkCurrencyId) {
		this.fkCurrencyId = fkCurrencyId;
	}

	public Integer getFkRateBasisRefId() {
		return fkRateBasisRefId;
	}

	public void setFkRateBasisRefId(Integer fkRateBasisRefId) {
		this.fkRateBasisRefId = fkRateBasisRefId;
	}

	public Integer getRateBasisUomId() {
		return rateBasisUomId;
	}

	public void setRateBasisUomId(Integer rateBasisUomId) {
		this.rateBasisUomId = rateBasisUomId;
	}

	public Integer getRateBasisPrimaryPackingId() {
		return rateBasisPrimaryPackingId;
	}

	public void setRateBasisPrimaryPackingId(Integer rateBasisPrimaryPackingId) {
		this.rateBasisPrimaryPackingId = rateBasisPrimaryPackingId;
	}

	public Integer getRateBasisSecondaryPackingId() {
		return rateBasisSecondaryPackingId;
	}

	public void setRateBasisSecondaryPackingId(Integer rateBasisSecondaryPackingId) {
		this.rateBasisSecondaryPackingId = rateBasisSecondaryPackingId;
	}

	public String getFkCurrencyName() {
		return fkCurrencyName;
	}

	public void setFkCurrencyName(String fkCurrencyName) {
		this.fkCurrencyName = fkCurrencyName;
	}

	

	public String getRateBasisUom() {
		return rateBasisUom;
	}

	public void setRateBasisUom(String rateBasisUom) {
		this.rateBasisUom = rateBasisUom;
	}

	public String getRateBasisPrimaryPacking() {
		return rateBasisPrimaryPacking;
	}

	public void setRateBasisPrimaryPacking(String rateBasisPrimaryPacking) {
		this.rateBasisPrimaryPacking = rateBasisPrimaryPacking;
	}

	public String getRateBasisSecondaryPacking() {
		return rateBasisSecondaryPacking;
	}

	public void setRateBasisSecondaryPacking(String rateBasisSecondaryPacking) {
		this.rateBasisSecondaryPacking = rateBasisSecondaryPacking;
	}

	public String getFkRateBasisRefName() {
		return fkRateBasisRefName;
	}

	public void setFkRateBasisRefName(String fkRateBasisRefName) {
		this.fkRateBasisRefName = fkRateBasisRefName;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public void setRate(float rate) {
		this.rate = rate;
	}
	
	
	
	
   
}

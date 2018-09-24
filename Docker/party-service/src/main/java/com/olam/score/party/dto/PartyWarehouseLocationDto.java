package com.olam.score.party.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

public @Data class PartyWarehouseLocationDto{
	
	@JsonProperty("uniqueMapping")
	private Integer uniqueMapping;
	private Integer fkLocId;
	@JsonProperty("warLocationCode")
	private String portLocationCode;
	@JsonProperty("wareLocationName")
	private String whStockLocName;
	@JsonProperty("wareLocationFullName")
	private String whStockLocFullname;
	@JsonProperty("wareLocationTaxRegNo")
	private String taxRegNo;
	@JsonProperty("wareLocationTaxRegDate")
	private Date taxRegDate;
	private String isExchangeDesignated;
	@JsonProperty("isBrandedWareHouse")
	private String isBondedWarehouse;
	//private AddressDto address = new AddressDto();
	private String addressName;
	@JsonProperty("wareAddress1")
	private String whStockLocAddr1;
	@JsonProperty("wareAddress2")
	private String whStockLocAddr2;
	@JsonProperty("wareZip")
	private String whStockLocZipcode;
	
	@JsonProperty("countryWareHOuse")
	private Integer fkGeoId;
	private String country;
	
	private Integer fkStatusId;
	private String status;
	@JsonProperty("warePhone")
	private String whStockLocPhone;
	@JsonProperty("wareEmail")
	private String whStockLocEmail;
	@JsonProperty("wareFax")
	private String whStockLocFax;
	@JsonProperty("wareWebsite")
	private String whStockLocWebsite;

}

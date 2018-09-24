package com.olam.score.party.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

public @Data class PartyPlantDetailsDto {

	
	@JsonProperty("uniqueMapping")
	private Integer uniqueMapping;
	private String plantCode;
	private String plantName;
	private String plantFullName;
	@JsonProperty("taxRegistrationNumber")
	private String taxRegNo;
	@JsonProperty("taxRegistrationDate")
	private Date taxRegDate;
	//private AddressDto address = new AddressDto();//Need to check this
	@JsonProperty("addressName")
	private String addressName;
	@JsonProperty("address1")
	private String plantAddr1;
	@JsonProperty("address2")
	private String plantAddr2;
	@JsonProperty("zipCode")
	private String plantZipcode;
	
	@JsonProperty("countryDefault")
	private Integer fkGeoId;
	private String country;
	//Ignore status here
	@JsonProperty("statusId")
	private Integer fkStatusId;
	private String status;
	
	@JsonProperty("phoneDefault")
	private String plantPhone;
	@JsonProperty("emailDefault")
	private String plantEmail;
	@JsonProperty("faxDefault")
	private String plantFax;
	@JsonProperty("websiteDefault")
	private String plantWebsite;
}

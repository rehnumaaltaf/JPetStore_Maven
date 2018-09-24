package com.olam.score.party.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

public @Data class AddressDto {
	
	@JsonProperty("uniqueMapping")
	private Integer uniqueMapping;
	@JsonProperty("isDefaultAddress")
	private String partyAddrIsDefault;
	@JsonProperty("addressTypeId")
	private Integer fkAddrTypeRefId;
	private String addressType;
	
	@JsonProperty("name")
	private String partyAddrName;
	@JsonProperty("addressOne")
	private String partyAddressOne;
	@JsonProperty("addressTwo")
	private String partyAddressTwo;
	@JsonProperty("zipCode")
	private String partyAddrZipcode;
	
	@JsonProperty("countryId")
	private Integer fkGeoCountryId;
	private String country;
	
	@JsonProperty("phone")
	private String partyAddrPhone;
	@JsonProperty("email")
	private String partyAddrEmail;
	@JsonProperty("fax")
	private String partyAddrFax;
	@JsonProperty("website")
	private String partyAddrWebsite;
	
	//private Integer fkStatusId;
	//private String statusName;
	
}

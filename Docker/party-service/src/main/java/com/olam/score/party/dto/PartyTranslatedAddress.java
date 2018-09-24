package com.olam.score.party.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

public @Data class PartyTranslatedAddress {
	
	
	@JsonProperty("uniqueMapping")
	private Integer uniqueMapping;
	private Integer addressId;//Get these values from MasterPartyAddress object
	private String addressName;
	
	@JsonProperty("addressLanguage")
	private String languageId;
	private String language;
	
	//private AddressDto address = new AddressDto();
	
	@JsonProperty("addressAltAddress1")
	private String partyAddressOne;
	@JsonProperty("addressAltAddress2")
	private String partyAddressTwo;
	@JsonProperty("addressAltZipCode")
	private String partyAddrZipcode;
	
	@JsonProperty("addressAltCountry")
	private Integer fkGeoCountryId;
	private String country;
		
}

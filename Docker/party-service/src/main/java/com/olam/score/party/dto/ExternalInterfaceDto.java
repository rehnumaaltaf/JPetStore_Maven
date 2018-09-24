package com.olam.score.party.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

public @Data class ExternalInterfaceDto {

	private String type;//ex : V or C
	private String group;//ex : OLAM group or Nestle
	private String block;//X : means deactivated , if empty draft
	private String internal;//ex : Y
	@JsonProperty("name")
	private String partyName;
	private String customerCode;
	private String vendorCode;
	private String street1;
	private String street2;
	private String country;
	private String zipCode;

}

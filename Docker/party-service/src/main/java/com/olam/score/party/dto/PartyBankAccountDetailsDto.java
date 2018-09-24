package com.olam.score.party.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

public @Data class PartyBankAccountDetailsDto {
	
	@JsonProperty("uniqueMapping")
	private Integer uniqueMapping;
	@JsonProperty("isDefault")
	private String partyBankAccountIsDefault;
	@JsonProperty("partyAccountName")
	private String partyBankGroupName;
	private String partyBankBranch;
	
	@JsonProperty("partyAccountType")
	private Integer actId;//Get from masteraccounttype class
	private String accountTypeCode;
	private String accountTypeName;
	private String partyBankAccountNo;
	private String partyBankBicCode;
	private String partyBankSwiftCode;
	private String partyBankRouting;
	private String partyBankAddr1;
	private String partyBankAddr2;
	private String partyBankZipCode;
	
	@JsonProperty("countryId")
	private Integer fkBankBranchCountryGeoId;
	private String country;
	
	@JsonProperty("statusId")
	private Integer fkStatusId;
	private String status;
	
	private String partyBankPhone;
	private String partyBankEmail;
	private String partyBankFax;
	private String partyBankWebsite;
	//private AddressDto address  = new AddressDto();//Need to set a constructor???
	
	
	@JsonProperty("addressOne")
	private String partyAddressOne;
	@JsonProperty("addressTwo")
	private String partyAddressTwo;
	@JsonProperty("zipCode")
	private String partyAddrZipcode;
	@JsonProperty("phone")
	private String partyAddrPhone;
	@JsonProperty("email")
	private String partyAddrEmail;
	@JsonProperty("fax")
	private String partyAddrFax;
	@JsonProperty("website")
	private String partyAddrWebsite;
	
}

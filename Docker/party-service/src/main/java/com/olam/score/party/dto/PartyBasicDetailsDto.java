package com.olam.score.party.dto;


import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

public @Data class PartyBasicDetailsDto {

	@JsonProperty("partyInternalExternal")
	private String partyInternalFlag;
	private String partyCode;
	private String partyName;
	private String partyFullName;
	@JsonProperty("isGroupCompany")
	private String isGroupParty;
	
	@JsonProperty("selectGroup")
	private Integer fkParentPartyId;//which value does it map to?
	private String parentParty;
	
	@JsonProperty("geoCountry")
	private Integer fkGeoCountryId;
	@JsonProperty("country")
	private String geoCName;
	
	@JsonProperty("marketingDesk")
	private Integer fkMarketingDeskId;
	@JsonProperty("marketingDeskName")
	private String marketingDesk;
	
	@JsonProperty("creditAllowed")
	private String partyAllowCredit;
	
	@JsonProperty("partyRatingKey")
	private Integer partyRKey;
	private String partyRShort;//Get the value from MasterPartyRatingRef class
	@JsonProperty("partyRatingName")
	private String partyRating;
	private BigDecimal groupCreditLimit;
	private BigDecimal groupCreditTenorInDays;
	
	@JsonProperty("partyTypeIds")
	private List<Integer> partyTypeIds;
	private List<String> partyTypes;
	
	@JsonProperty("partyClassificationIds")
	private List<Integer> partyClassificationIds;
	
	private List<String> partyClassifications;
	@JsonProperty("companyRegistration")
	private String partyCompanyRegNo;
	@JsonProperty("companyRegistrationDate")
	private Date partyCompRegDate;
	private String partyTaxRegNo;
	private Date partyTaxRegDate;
	@JsonProperty("firsttransactionDate")
	private Date firstTransWithOlam;
	/** GTP is Global Trade Program */
	private String isGtpApplicableInd;
	
	@JsonProperty("functionalCurrencyId")
	private Integer fkFunctionalCurrencyId;
	private String functionalCurrency;
	
	@JsonProperty("operationalCurrencyId")
	private Integer fkOperationalCurrencyId;
	private String operationalCurrency;
	
	@JsonProperty("defaultReportingUOMId")
	private Integer fkDefaultReportingUOMId;
	private String defaultReportingUOM;
	
	private Integer fkStatusId;
	@JsonProperty("statusName")
	private String status;
	
}


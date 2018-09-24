package com.olam.score.party.dto;

import java.math.BigDecimal;
import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

public @Data class PartyCreditLimitDto {

	
	@JsonProperty("uniqueMapping")
	private Integer uniqueMapping;
	//private Integer fkInternalPartyId;//Get the values from MasterParty fkInternalPartyId
	@JsonProperty("fkInternalPartyId")
	private Integer shortIntPar;
	private String internalPartyCode;
	private String internalPartyName;
	
	@JsonProperty("creditLimitProduct")
	private Integer fkProd;
	private String product;
	
	private String creditLimitCode;
	private String creditLimitName;
	
	@JsonProperty("unit") // Check this for modelmapper
	private Integer shortUn;
	//private Integer fkUnitId;//Get the value from MasterParty fkPartyId
	private String unitName;
	
	@JsonProperty("limitAlertLevel")
	private Integer fkLimitAlertLevel;
	private String limitAlertLevelName;
	
	@JsonProperty("creditCurrency")
	private Integer fkCurrency;
	private String currency;
	
	@JsonProperty("Factoring")
	private BigDecimal creditLimitBgciFactoring;
	@JsonProperty("FactoringEndDate")
	private Date creditLimitBgciFactoringEndDate;
	@JsonProperty("netLimit")
	private BigDecimal creditNetLimit;
	@JsonProperty("validFrom")
	private Date creditLimitValidFrom;
	@JsonProperty("validTo")
	private Date creditLimitValidTo;
	
	//private Integer isTemporaryIndId;
	@JsonProperty("limitType")
	private String isTemporaryInd;

}

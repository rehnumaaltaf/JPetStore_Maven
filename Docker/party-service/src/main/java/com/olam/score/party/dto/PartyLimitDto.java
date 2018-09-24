package com.olam.score.party.dto;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.olam.score.common.dto.BaseDto;

import lombok.Data;

public @Data class PartyLimitDto extends BaseDto{

	@JsonProperty("uniqueMapping")
	private Integer uniqueMapping;
	@JsonProperty("internalPartyCodeOrg")
	private Integer internalPartyId;
	private String internalPartyCode;
	private String internalPartyNameOrg;
	
	@JsonProperty("partyProduct")
	private Integer fkProdId;
	
	private String product;
	
	@JsonProperty("partyUnit")
	private Integer unitId;
	@JsonProperty("partyUnitName")
	private String unit;
		
	@JsonProperty("limitAlertLevelParty")
	private Integer fkLimitAlertLevelKey;
	@JsonProperty("limitAlertLevelPartyName")
	private String limitAlertLevelName;
	
	@JsonProperty("partyCurrency")
	private Integer currUnique;
	
	@JsonProperty("partyCurrencyName")
	private String currency;
	
	@JsonProperty("partyUom")
	private Integer uomId;
	@JsonProperty("partyUomName")
	private String uom;
	
	@JsonProperty("partyFixedQtyLimit")
	private Integer counterPartyFixedQtyLimit;
	
	@JsonProperty("partyFixedVAlueLimit")
	private Integer counterPartyFixedValueLimit;
	
	@JsonProperty("fixedForwardTenor")
	private Integer counterPartyFixedForwardTenor;
	
	@JsonProperty("diffQtyLimit")
	private Integer counterPartyDiffQtyLimit;
	
	@JsonProperty("diffValueLimit")
	private Integer counterPartyDiffValueLimit;
	
	@JsonProperty("diffForwardTenor")
	private Integer counterPartyDiffForwardTenor;
	
	@JsonProperty("m2mLimit")
	private Integer counterPartyM2mLimit;
	
	@JsonProperty("varLimit")
	private Integer counterPartyVarLimit;
	
	@JsonProperty("totalQtyLimit")
	private Integer counterPartyTotalQtyLimit;
	
	@JsonProperty("totalValueLimit")
	private Integer counterPartyTotalValueLimit;
	
	@JsonProperty("limitOn")
	private Integer limitOnId;
	private String limitOnName;
	
	@JsonProperty("partyValidFrom")
	private Date counterPartyLimitValidFrom;
	
	@JsonProperty("partyValidTo")
	private Date counterPartyLimitValidTo;

}

package com.olam.score.party.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

public @Data class PartyBrokerDetailsDto {

	
	@JsonProperty("uniqueMapping")
	private Integer uniqueMapping;
	private String tradingAccountNumber;
	private BigDecimal commission;
	@JsonProperty("commissionUOM")
	private Integer fkCommissionUomId;
	@JsonProperty("commissionUOMName")
	private String commissionUOM;//Get value from UOM
	//Not required for now check
	private Integer fkStatusId;
	private String status;
}

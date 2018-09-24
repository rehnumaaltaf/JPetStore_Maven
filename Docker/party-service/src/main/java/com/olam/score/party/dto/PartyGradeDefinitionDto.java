package com.olam.score.party.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

public @Data class PartyGradeDefinitionDto {
	
	@JsonProperty("uniqueMapping")
	private Integer uniqueMapping;
	@JsonProperty("prodId")
	private Integer fkProdId;
	private String productName;
	
	private String partyGradeName;
	private String partyGradeDescription;

}

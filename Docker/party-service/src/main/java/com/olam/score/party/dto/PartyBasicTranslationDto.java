package com.olam.score.party.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

public @Data class PartyBasicTranslationDto {
	
	@JsonProperty("uniqueMapping")
	private Integer uniqueMapping;
	private Integer languageId;
	private String language;//get the value from call to reference service
	@JsonProperty("alternativeName")
	private String partyTranslName;
	@JsonProperty("alternativeFullName")
	private String partyTranslFullname;

}

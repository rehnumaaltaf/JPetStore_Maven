package com.olam.score.party.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

public @Data class ExternalSystemMappingDto {
	
	@JsonProperty("uniqueMapping")
	private Integer uniqueMapping;
	@JsonProperty("destinationSystem")
	private Integer fkExternalSystemRefId;
	private String externalSystemRefName;
	@JsonProperty("externalSystemMappingType")
	private String mappingType;
	@JsonProperty("externalSystemMappingVendor")
	private String vendorCode;
	@JsonProperty("externalSystemMappingCustomerCode")
	private String customerCode;
	private String externalCode;//Just a placeholder for a value in DB

}

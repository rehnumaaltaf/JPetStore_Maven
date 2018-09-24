package com.olam.score.product.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ProcessingSubTypeDto {
	
	@JsonProperty("processSubTypeRefId")
	private Integer processSubId;
	@JsonProperty("subTypeCode")
	private String processSubCode;
	@JsonProperty("subTypeName")
	private String processSubName;
	@JsonProperty("processSubTypeMappingId")
	private Integer mappingId;

}

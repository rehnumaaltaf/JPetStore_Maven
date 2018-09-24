package com.olam.score.reference.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ProductArbitrationDto {
	
	@JsonProperty("mappingId")
	private Integer tableMappingId;//The value of mapping stored in the MasterProductArbitrationMapping table
	private String arbitrationShortName;//Get the name from the arbitration name
	private String arbitrationFullName;
	private Integer arbitrationAgencyId;//Get the id from the arbitration table

}

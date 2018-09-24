package com.olam.score.party.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

public @Data class InternalGradeMappingDto {
	
	@JsonProperty("uniqueMapping")
	private Integer uniqueMapping;
	@JsonProperty("origin")
	private Integer fkOriginId;
	private String originName;
	
	@JsonProperty("gradeNameId")
	private Integer fkGradeId;//Need to get data from product.master_grade
	@JsonProperty("code")
	private String internalGradeName;//Same as above
	@JsonProperty("description")
	private String internalGradeDesc;//Same as above

}

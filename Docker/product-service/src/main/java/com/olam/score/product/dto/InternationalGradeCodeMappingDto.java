package com.olam.score.product.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class InternationalGradeCodeMappingDto {
	
	@JsonProperty("gradeMappingId")
	private Integer tableMappingId;
	private Integer codeTypeId;
	private String codeType;
	private String code;
	@JsonProperty("codedescription")
	private String description;

}

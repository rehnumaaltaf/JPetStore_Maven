package com.olam.score.party.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

public @Data class DocumentsDto {

	
	@JsonProperty("uniqueMapping")
	private Integer uniqueMapping;
	
	@JsonProperty("fkDocumentRefId")
	private Integer fkDocumentRefId;
	
	@JsonProperty("documentName")
	private String dName;//Get values from call to reference service
	
	@JsonProperty("documentType")
	private String documentTypeName;
	
	@JsonProperty("fkDocumentTypeRefId")
	private Integer fkDocumentTypeRefId;
	
	@JsonProperty("documentNumber")
	private Integer numberOfCopies;

}

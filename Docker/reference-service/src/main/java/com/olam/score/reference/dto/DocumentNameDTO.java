package com.olam.score.reference.dto;

import lombok.Data;

public @Data class DocumentNameDTO {

	private Integer documentId;
	private String documentCode;
	private String documentName;
	private String documentDesc;
	private Integer fkStatusId;
	private String statusName;

}
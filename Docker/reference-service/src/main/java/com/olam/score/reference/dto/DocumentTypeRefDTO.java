package com.olam.score.reference.dto;

import lombok.Data;

public @Data class DocumentTypeRefDTO {
	
	private Integer documentTypeId;
	private String documentTypeName;
	private String documentTypeCode;
	private String documentTypeDesc;
	private Integer fkStatusId;
	private String statusName;
	
}

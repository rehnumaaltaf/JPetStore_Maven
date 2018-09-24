package com.olam.score.limit.dto;

import lombok.Data;

public @Data class MasterCounterPartyLimitTypeDTO {

	private Integer pkCounterPartyLimitTypeId;
	private String counterPartyLimitTypeName;
	private String counterPartyLimitTypeCode;
	private String counterPartyLimitTypeDesc;
	private Integer fkStatusId;
	private String statusName;
	

}

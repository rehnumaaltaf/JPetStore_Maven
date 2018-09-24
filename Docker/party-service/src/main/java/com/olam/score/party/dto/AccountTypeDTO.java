package com.olam.score.party.dto;

import lombok.Data;

public @Data class AccountTypeDTO {
	
	    private Integer pkAccountTypeId;
	    private String accountTypeName;
	    private String accountTypeCode;
	    private Integer fkStatusId;
	    private String statusName;

}

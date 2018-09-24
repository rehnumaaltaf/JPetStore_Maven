package com.olam.score.party.dto;

import lombok.Data;

public @Data class PartyTypeDTO {
	
	    private Integer pkPartyTypeId;
	    private String partyTypeCode;
	    private String partyTypeName;
	    private String partyTypeFullname;
	    private Integer fkStatusId;
	    private String statusName;

}

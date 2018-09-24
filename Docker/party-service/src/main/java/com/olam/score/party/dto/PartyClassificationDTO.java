package com.olam.score.party.dto;

import lombok.Data;

public @Data class PartyClassificationDTO {
	
	    private Integer pkPartyClassId;
	    private String partyClassCode;
	    private String partyClassName;
	    private String partyClassType;
	    private Integer fkStatusId;
	    private String statusName;
}

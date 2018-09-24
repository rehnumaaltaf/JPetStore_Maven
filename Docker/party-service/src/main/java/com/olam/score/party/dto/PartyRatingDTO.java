package com.olam.score.party.dto;

import lombok.Data;

public @Data class PartyRatingDTO {
	
    private Integer pkPartyRateRefId;
    private String rCode;
    private String partyRatingName;
    private Integer fkStatusId;
    private String statusName;
}

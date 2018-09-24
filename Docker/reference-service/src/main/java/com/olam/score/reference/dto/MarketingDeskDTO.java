package com.olam.score.reference.dto;

import lombok.Data;

public @Data class MarketingDeskDTO {
	
    private Integer pkMarketDeskId;
    private String marketDeskCode;
    private String marketDeskName;
    private Integer fkStatusId;
    private String statusName;

}

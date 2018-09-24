package com.olam.score.location.dto;

import lombok.Data;

public @Data class MasterLocationDTO {

	private Integer pkLocId;
    private String locName;
    private String locCode;
    private String locFullname;
    private Integer fkStatusId;
    private String statusName;
    
}

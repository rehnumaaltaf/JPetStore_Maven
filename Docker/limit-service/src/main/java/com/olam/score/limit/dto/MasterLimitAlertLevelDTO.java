package com.olam.score.limit.dto;

import lombok.Data;

public @Data class MasterLimitAlertLevelDTO 
{
    private Integer pkLimitAlertLevelId;
    private String limitAlertLevelName;
    private String limitAlertLevelCode;
    private Integer fkStatusId;
    private String statusName;
	

}

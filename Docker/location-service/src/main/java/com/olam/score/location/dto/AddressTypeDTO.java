package com.olam.score.location.dto;

import lombok.Data;

public @Data class AddressTypeDTO {
	
    private Integer pkAddrTypeRefId;
    private String addrTypeRefName;
    private String addrTypeRefCode;
    private String addrTypeRefDesc;
    private Integer fkStatusId;
    private String statusName;

}

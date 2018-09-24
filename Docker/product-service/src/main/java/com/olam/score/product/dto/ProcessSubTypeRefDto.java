package com.olam.score.product.dto;

import com.olam.score.common.dto.BaseDto;

import lombok.Data;

@Data
public class ProcessSubTypeRefDto extends BaseDto {
	
	private Integer processSubTypeRefId;
	private String subTypeCode;
	private String subTypeName;

}

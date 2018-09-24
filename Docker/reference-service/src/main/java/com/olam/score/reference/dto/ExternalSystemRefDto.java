package com.olam.score.reference.dto;

import com.olam.score.common.dto.BaseDto;

import lombok.Data;

@Data
public class ExternalSystemRefDto extends BaseDto{

	private Integer externalSystemRefId;
	private String externalSystemRefName;
	private String externalSystemRefCode;
	
}

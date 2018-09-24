package com.olam.score.location.dto;

import com.olam.score.common.dto.BaseDto;

import lombok.Data;
@Data
public class GeoTypeDto extends BaseDto{

	private Integer geoTypeId;
	private String geoTypeName;
	private String geoTypeCode;

}

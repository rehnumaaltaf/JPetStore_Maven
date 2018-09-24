package com.olam.score.reference.dto;

import java.util.Date;

import lombok.Data;

@Data
public class CalendarTypeRefDto {
	private Integer calendarTypeRefId;
	private String calendarTypeCode;
	private String calendarTypeName;
	private String calendarTypeDesc;
	private String createdBy;
	private Date createdDate;
	private String modifiedBy;
	private Date modifiedDate;
	private Integer fkStatusId;
}

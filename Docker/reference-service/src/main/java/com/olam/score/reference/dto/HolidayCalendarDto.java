package com.olam.score.reference.dto;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.olam.score.common.dto.BaseDto;

public class HolidayCalendarDto extends BaseDto{

	private Integer holidayCalId;
	@NotNull(message = "notNull_holidayCalName")
    @NotEmpty(message = "mandatory_holidayCalName")
	private String holidayCalName;
	@NotNull(message = "notNull_holidayCalCode")
    @NotEmpty(message = "mandatory_holidayCalCode")
	private String holidayCalCode;
	private String holidayCalDesc;
	@NotNull(message = "notNull_geoId")
	private Integer geoId;
	private String geoName;
	private Integer statusId;
	private String statusName;
	
	
	
	@Valid
	private List<HolidayListDto> holidayListDto;
	
	private List<Integer> deletedIds;
	
	
	
	
	
	public List<Integer> getDeletedIds() {
		return deletedIds;
	}
	public void setDeletedIds(List<Integer> deletedIds) {
		this.deletedIds = deletedIds;
	}
	public String getGeoName() {
		return geoName;
	}
	public void setGeoName(String geoName) {
		this.geoName = geoName;
	}
	public Integer getHolidayCalId() {
		return holidayCalId;
	}
	public void setHolidayCalId(Integer holidayCalId) {
		this.holidayCalId = holidayCalId;
	}
	
	
	public String getHolidayCalName() {
		return holidayCalName;
	}
	public void setHolidayCalName(String holidayCalName) {
		this.holidayCalName = holidayCalName;
	}
	public String getHolidayCalDesc() {
		return holidayCalDesc;
	}
	public void setHolidayCalDesc(String holidayCalDesc) {
		this.holidayCalDesc = holidayCalDesc;
	}
	public String getHolidayCalCode() {
		return holidayCalCode;
	}
	public void setHolidayCalCode(String holidayCalCode) {
		this.holidayCalCode = holidayCalCode;
	}
	public Integer getGeoId() {
		return geoId;
	}
	public void setGeoId(Integer geoId) {
		this.geoId = geoId;
	}
	public Integer getStatusId() {
		return statusId;
	}
	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public List<HolidayListDto> getHolidayListDto() {
		return holidayListDto;
	}
	public void setHolidayListDto(List<HolidayListDto> holidayListDto) {
		this.holidayListDto = holidayListDto;
	}
	
}

package com.olam.score.reference.dto;

import java.sql.Timestamp;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class HolidayListDto {

	private Integer holidayListId;
	@NotNull(message = "notNull_holidayListName")
    @NotEmpty(message = "mandatory_holidayListName")
	private String holidayListName;
	@NotNull(message = "notNull_holidayListDate")
    /*@NotEmpty(message = "mandatory_holidayListDate")*/
	/*private String holidayListDate;*/
	private Timestamp holidayListDate;
	private Integer holidayCalId;
	private String statusName;
	
	private String isDeleted;
	
	
	
	
	
	public Timestamp getHolidayListDate() {
		return holidayListDate;
	}
	public void setHolidayListDate(Timestamp holidayListDate) {
		this.holidayListDate = holidayListDate;
	}
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	public Integer getHolidayListId() {
		return holidayListId;
	}
	public void setHolidayListId(Integer holidayListId) {
		this.holidayListId = holidayListId;
	}
	/*public String getHolidayListDate() {
		return holidayListDate;
	}
	public void setHolidayListDate(String holidayListDate) {
		this.holidayListDate = holidayListDate;
	}*/
	private Integer statusId;
	
	public String getHolidayListName() {
		return holidayListName;
	}
	public void setHolidayListName(String holidayListName) {
		this.holidayListName = holidayListName;
	}
	
	public Integer getHolidayCalId() {
		return holidayCalId;
	}
	public void setHolidayCalId(Integer holidayCalId) {
		this.holidayCalId = holidayCalId;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public Integer getStatusId() {
		return statusId;
	}
	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}
}

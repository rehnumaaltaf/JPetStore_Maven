package com.olam.score.party.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

public @Data class ContactsDto {
	
	@JsonProperty("uniqueMapping")
	private Integer uniqueMapping;
	@JsonProperty("partyDepartment")
	private Integer pkDeptId;//Get the value from MasterDepartment
	@JsonProperty("departmentCode")
	private String deptCode;
	@JsonProperty("product")
	private Integer prodId;
	private String productName;
	private String partyContactFullname;
	private String partyContactDesignation;
	private String partyContactEmail;
	private String partyContactWorkPhone;
	private String partyContactMobile;
	private String partyContactFax;
	private String partyContactIsDefault;

}

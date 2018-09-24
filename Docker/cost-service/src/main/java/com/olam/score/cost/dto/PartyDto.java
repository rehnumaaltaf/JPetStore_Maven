package com.olam.score.cost.dto;

import com.olam.score.common.dto.BaseDto;

public class PartyDto extends BaseDto{

	private Integer partyId;
	private String partyName;
	private String partyCode;
	
	public String getPartyCode() {
		return partyCode;
	}
	public void setPartyCode(String partyCode) {
		this.partyCode = partyCode;
	}
	public Integer getPartyId() {
		return partyId;
	}
	public void setPartyId(Integer partyId) {
		this.partyId = partyId;
	}
	public String getPartyName() {
		return partyName;
	}
	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}
}


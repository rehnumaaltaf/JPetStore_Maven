package com.olam.score.authorizationconfig.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.olam.score.common.dto.BaseDto;

public class PartyDto extends BaseDto {

	@JsonProperty("partyId")
	private Integer pkPartyId;
	private String partyCode;
	private String partyName;

	public Integer getPkPartyId() {
		return pkPartyId;
	}

	public void setPkPartyId(Integer pkPartyId) {
		this.pkPartyId = pkPartyId;
	}

	public String getPartyCode() {
		return partyCode;
	}

	public void setPartyCode(String partyCode) {
		this.partyCode = partyCode;
	}

	public String getPartyName() {
		return partyName;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

}

package com.olam.score.party.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
public @Data class ResponseDto {
	
	private String partyName;
	private String partyStatus;
	private String transactionStatus;
	private Integer transactionCode;
	private String errorMessage;
	
	public ResponseDto(String transactionStatus, Integer transactionCode, String errorMessage) {
		super();
		this.transactionStatus = transactionStatus;
		this.transactionCode = transactionCode;
		this.errorMessage = errorMessage;
	}

	public ResponseDto(String transactionStatus, Integer transactionCode,String partyName, String partyStatus) {
		super();
		this.partyName = partyName;
		this.partyStatus = partyStatus;
		this.transactionStatus = transactionStatus;
		this.transactionCode = transactionCode;
	}

	

}

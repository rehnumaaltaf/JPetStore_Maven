package com.olam.score.party.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

public @Data class PaymentTermsDto {

	@JsonProperty("uniqueMapping")
	private Integer uniqueMapping;
	private Integer paymentTermId;
	private String paymentTerm;
	private String paymentTermDesc;//On basis of paymentTermId , need to get data from terms service
	private String paymentTermCode;;

}

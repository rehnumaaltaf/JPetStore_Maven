package com.olam.score.party.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.olam.score.common.dto.BaseDto;

import lombok.Data;

public @Data class ExchangeDetailsDto extends BaseDto {
	
	
	@JsonProperty("uniqueMapping")
	private Integer uniqueMapping;
	
	@JsonProperty("partyId")
	private Integer partyNumber;
	
	@JsonProperty("exchangeProduct")
	private Integer productUnique;
	private String product;
	
	@JsonProperty("exchangeScreenName")
	private String xchngProdAssoScreenName;
	
	@JsonProperty("exchangeHubName")
	private String hubName;
	
	@JsonProperty("exchangeContractSymbol")
	private String contractSymbol;
	
	@JsonProperty("exchangeContractSize")
	private Integer lotOrContractSize;
	@JsonProperty("lotOrContractSize")
	private String lotOrContractSizeName;
	
	@JsonProperty("exchangeContractSizeUom")
	private Integer contractSize;//Check this in modelMapper
	private String cSizeName;
	
	//It is a multiselect .Check the possibility of change here //Change the service for holidayCalendar
	@JsonProperty("holidayCalendarId")
	private Integer holidayCalendar;
	@JsonProperty("holidayCalendar")
	private String holidayCalendarText;
	
	@JsonProperty("exchangeTradingCalender")
	private String tradingCalendar; // Send an object for this 1-Jan 2-Feb etc
	@JsonProperty("exchangePriceQuotation")
	private String priceQuotation;
	
	@JsonProperty("exchangePriceQuotationCurrency")
	private Integer priceQuotationCurrency;
	private Integer pqcName;
	@JsonProperty("priceQuotationCurrency")
	private String priceQuotationCurrencyValue;
	
	@JsonProperty("exchangePriceQuotationUom")
	private Integer priceQuotationUoM;
	@JsonProperty("exchangePriceQuotationUomName")
	private String priceQuotationUomKey;
	
	@JsonProperty("exchangeMinimmumPrice")
	private Integer minimumPriceMovement;
	
	@JsonProperty("exchangeMinimmumPriceCurrency")
	private Integer minimumPriceMovementCurrencyId;
	private String minimumPriceMovementCurrency;
	
	@JsonProperty("exchangeMinimmumPriceUom")
	private Integer minimumPriceMovementUoMId;
	private String minimumPriceMovementUoM;

}

package com.olam.score.marketdata.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExchangeProdAssocDto {
	
	@JsonProperty("exchangeProduct")
	private Integer myprdt;
	private String product;
	
	@JsonProperty("exchangeScreenName")
	private String xchngProdAssoScreenName;
	
	@JsonProperty("exchangeHubName")
	private String hub;
	
	@JsonProperty("exchangeContractSymbol")
	private String symbol;
	
	@JsonProperty("exchangeContractSize")
	private Integer size;
	
	@JsonProperty("exchangeContractSizeUom")
	private Integer lot;
	private String uomName;
	//Check this in modelMapper
	
	//It is a multiselect .Check the possibility of change here //Change the service for holidayCalendar
	@JsonProperty("holidayCalendarId")
	private Integer leave;
	@JsonProperty("holidayCalendar")
	private String hcn;
	
	
	@JsonProperty("exchangeTradingCalender")
	private String tradingCalendar;
	
	@JsonProperty("exchangePriceQuotation")
	private String priceQuotation;
	
	@JsonProperty("priceQuotationCurrencyId")
	private Integer money;
	@JsonProperty("priceQuotationCurrency")
	private String quCur;
	
	@JsonProperty("exchangePriceQuotationUom")
	private Integer price;
	@JsonProperty("quoteUom")
	private String qu;
	
	@JsonProperty("exchangeMinimmumPrice")
	private Integer minimumPriceMovement;
	
	@JsonProperty("exchangePriceQuotationCurrency")
	private Integer minimumPriceMovementCurrencyId;
	@JsonProperty("minimumPriceMovementCurrency")
	private String mpmc;
	
	private Integer minimumPriceMovementUoMId;
	@JsonProperty("minimumPriceMovementUoM")
	private String mpu;


}

package com.olam.score.reference.dto;

import java.util.List;

public class WrapperDto {

	private List<UomDto> uomList;
	private List<CurrencyDTO> currencyList;
	public List<UomDto> getUomList() {
		return uomList;
	}
	public void setUomList(List<UomDto> uomList) {
		this.uomList = uomList;
	}
	public List<CurrencyDTO> getCurrencyList() {
		return currencyList;
	}
	public void setCurrencyList(List<CurrencyDTO> currencyList) {
		this.currencyList = currencyList;
	}
}

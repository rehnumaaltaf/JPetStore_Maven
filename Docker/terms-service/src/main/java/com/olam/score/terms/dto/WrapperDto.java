package com.olam.score.terms.dto;

import java.util.List;

public class WrapperDto {
	private List<ContractTermsBaseRefDto> baseList;
	private List<ContractTermsBasisRefDto> basisList;
	private List<ContrTrmsAddreduceRefDto> addReduceList;
	public List<ContractTermsBaseRefDto> getBaseList() {
		return baseList;
	}
	public void setBaseList(List<ContractTermsBaseRefDto> baseList) {
		this.baseList = baseList;
	}
	public List<ContractTermsBasisRefDto> getBasisList() {
		return basisList;
	}
	public void setBasisList(List<ContractTermsBasisRefDto> basisList) {
		this.basisList = basisList;
	}
	public List<ContrTrmsAddreduceRefDto> getAddReduceList() {
		return addReduceList;
	}
	public void setAddReduceList(List<ContrTrmsAddreduceRefDto> addReduceList) {
		this.addReduceList = addReduceList;
	}
	
	

}

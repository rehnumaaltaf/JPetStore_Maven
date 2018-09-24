package com.olam.score.terms.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.olam.score.common.dto.BaseDto;

public class ContractTermsDto extends BaseDto{

	private Integer contractTermsId;
	@NotNull(message = "notNull_contractTermsCode")
    @NotEmpty(message = "mandatory_contractTermsCode")
	private String contractTermsCode;
	@NotNull(message = "notNull_contractTermsName")
    @NotEmpty(message = "mandatory_contractTermsName")
	private String contractTermsName;
	private String contractTermsDesc;
	private Integer statusId;
	private String statusName;
	private Integer prodId;
	private String prodName;
	private String prodCode;
	
	@NotNull(message = "notNull_contractTermsBaseRefId")
	private Integer contractTermsBaseRefId;
	private String contractTermsBaseCode;
	private String contractTermsBaseName;
	@NotNull(message = "notNull_contractTermsBasisRefId")
	private Integer contractTermsBasisRefId;
	private String contractTermsBasisCode;
	private String contractTermsBasisName;
	
	/*@Valid*/
	private List<ContractTermsPurchaseDto> contractTermsPurchaseDto;
	/*@Valid*/
	private List<ContractTermsSalesDto> contractTermsSalesDto;
	
	
	//deleted ids for Purchase
	private List<Integer> deletedPurchaseIds;
	//deleted ids for sales
	private List<Integer> deletedSalesIds;
	
	
	public Integer getContractTermsId() {
		return contractTermsId;
	}
	public void setContractTermsId(Integer contractTermsId) {
		this.contractTermsId = contractTermsId;
	}
	public String getContractTermsCode() {
		return contractTermsCode;
	}
	public void setContractTermsCode(String contractTermsCode) {
		this.contractTermsCode = contractTermsCode;
	}
	public String getContractTermsName() {
		return contractTermsName;
	}
	public void setContractTermsName(String contractTermsName) {
		this.contractTermsName = contractTermsName;
	}
	public String getContractTermsDesc() {
		return contractTermsDesc;
	}
	public void setContractTermsDesc(String contractTermsDesc) {
		this.contractTermsDesc = contractTermsDesc;
	}
	public Integer getStatusId() {
		return statusId;
	}
	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public Integer getContractTermsBaseRefId() {
		return contractTermsBaseRefId;
	}
	public void setContractTermsBaseRefId(Integer contractTermsBaseRefId) {
		this.contractTermsBaseRefId = contractTermsBaseRefId;
	}
	public Integer getContractTermsBasisRefId() {
		return contractTermsBasisRefId;
	}
	public void setContractTermsBasisRefId(Integer contractTermsBasisRefId) {
		this.contractTermsBasisRefId = contractTermsBasisRefId;
	}
	
	public Integer getProdId() {
		return prodId;
	}
	public void setProdId(Integer prodId) {
		this.prodId = prodId;
	}

	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public String getProdCode() {
		return prodCode;
	}
	public void setProdCode(String prodCode) {
		this.prodCode = prodCode;
	}
	public String getContractTermsBaseCode() {
		return contractTermsBaseCode;
	}
	public void setContractTermsBaseCode(String contractTermsBaseCode) {
		this.contractTermsBaseCode = contractTermsBaseCode;
	}
	public String getContractTermsBaseName() {
		return contractTermsBaseName;
	}
	public void setContractTermsBaseName(String contractTermsBaseName) {
		this.contractTermsBaseName = contractTermsBaseName;
	}
	public String getContractTermsBasisCode() {
		return contractTermsBasisCode;
	}
	public void setContractTermsBasisCode(String contractTermsBasisCode) {
		this.contractTermsBasisCode = contractTermsBasisCode;
	}
	public String getContractTermsBasisName() {
		return contractTermsBasisName;
	}
	public void setContractTermsBasisName(String contractTermsBasisName) {
		this.contractTermsBasisName = contractTermsBasisName;
	}
	public List<ContractTermsPurchaseDto> getContractTermsPurchaseDto() {
		return contractTermsPurchaseDto;
	}
	public void setContractTermsPurchaseDto(List<ContractTermsPurchaseDto> contractTermsPurchaseDto) {
		this.contractTermsPurchaseDto = contractTermsPurchaseDto;
	}
	public List<ContractTermsSalesDto> getContractTermsSalesDto() {
		return contractTermsSalesDto;
	}
	public void setContractTermsSalesDto(List<ContractTermsSalesDto> contractTermsSalesDto) {
		this.contractTermsSalesDto = contractTermsSalesDto;
	}
	public List<Integer> getDeletedPurchaseIds() {
		return deletedPurchaseIds;
	}
	public void setDeletedPurchaseIds(List<Integer> deletedPurchaseIds) {
		this.deletedPurchaseIds = deletedPurchaseIds;
	}
	public List<Integer> getDeletedSalesIds() {
		return deletedSalesIds;
	}
	public void setDeletedSalesIds(List<Integer> deletedSalesIds) {
		this.deletedSalesIds = deletedSalesIds;
	}
	
	
	
	
	
	
}

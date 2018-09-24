package com.olam.score.reference.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.olam.score.common.dto.BaseDto;



public class ProductDto extends BaseDto {
	
	public Integer getProdId() {
		return prodId;
	}

	public void setProdId(Integer prodId) {
		this.prodId = prodId;
	}

	public String getProdCode() {
		return prodCode;
	}

	public void setProdCode(String prodCode) {
		this.prodCode = prodCode;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public String getProdFullName() {
		return prodFullName;
	}

	public void setProdFullName(String prodFullName) {
		this.prodFullName = prodFullName;
	}

	public String getSpecialityApplicable() {
		return specialityApplicable;
	}

	public void setSpecialityApplicable(String specialityApplicable) {
		this.specialityApplicable = specialityApplicable;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public Integer getStatusId() {
		return statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	public String getProductLimit() {
		return productLimit;
	}

	public void setProductLimit(String productLimit) {
		this.productLimit = productLimit;
	}

	public String getIsBaseProduct() {
		return isBaseProduct;
	}

	public void setIsBaseProduct(String isBaseProduct) {
		this.isBaseProduct = isBaseProduct;
	}

	public String getParentProductName() {
		return parentProductName;
	}

	public void setParentProductName(String parentProductName) {
		this.parentProductName = parentProductName;
	}

	public Integer getParentProductId() {
		return parentProductId;
	}

	public void setParentProductId(Integer parentProductId) {
		this.parentProductId = parentProductId;
	}

	public Integer getProcessMappingId() {
		return processMappingId;
	}

	public void setProcessMappingId(Integer processMappingId) {
		this.processMappingId = processMappingId;
	}

	public String getProcessingCode() {
		return processingCode;
	}

	public void setProcessingCode(String processingCode) {
		this.processingCode = processingCode;
	}

	public String getProcessingName() {
		return processingName;
	}

	public void setProcessingName(String processingName) {
		this.processingName = processingName;
	}

	public Collection<ProcessingSubTypeDto> getProcessingSubType() {
		return processingSubType;
	}

	public void setProcessingSubType(Collection<ProcessingSubTypeDto> processingSubType) {
		this.processingSubType = processingSubType;
	}

	public Integer getProcessingSubTypeId() {
		return processingSubTypeId;
	}

	public void setProcessingSubTypeId(Integer processingSubTypeId) {
		this.processingSubTypeId = processingSubTypeId;
	}

	public String getIcoCode() {
		return icoCode;
	}

	public void setIcoCode(String icoCode) {
		this.icoCode = icoCode;
	}

	public String getIcoName() {
		return icoName;
	}

	public void setIcoName(String icoName) {
		this.icoName = icoName;
	}

	public Integer getIcoMappingId() {
		return icoMappingId;
	}

	public void setIcoMappingId(Integer icoMappingId) {
		this.icoMappingId = icoMappingId;
	}

	public Collection<ProductArbitrationDto> getProductArbitrationCollection() {
		return productArbitrationCollection;
	}

	public void setProductArbitrationCollection(Collection<ProductArbitrationDto> productArbitrationCollection) {
		this.productArbitrationCollection = productArbitrationCollection;
	}

	public Integer getDestinationSystemId() {
		return destinationSystemId;
	}

	public void setDestinationSystemId(Integer destinationSystemId) {
		this.destinationSystemId = destinationSystemId;
	}

	public String getDestinationName() {
		return destinationName;
	}

	public void setDestinationName(String destinationName) {
		this.destinationName = destinationName;
	}

	public String getAttribute1() {
		return attribute1;
	}

	public void setAttribute1(String attribute1) {
		this.attribute1 = attribute1;
	}

	public String getAttribute2() {
		return attribute2;
	}

	public void setAttribute2(String attribute2) {
		this.attribute2 = attribute2;
	}

	public Integer getExternalSystemMappingId() {
		return externalSystemMappingId;
	}

	public void setExternalSystemMappingId(Integer externalSystemMappingId) {
		this.externalSystemMappingId = externalSystemMappingId;
	}

	public Collection<InternationalGradeCodeMappingDto> getGradeCodeMappingCollection() {
		return gradeCodeMappingCollection;
	}

	public void setGradeCodeMappingCollection(Collection<InternationalGradeCodeMappingDto> gradeCodeMappingCollection) {
		this.gradeCodeMappingCollection = gradeCodeMappingCollection;
	}

	public List<ExternalSystemRefDto> getExternalSystemRefValues() {
		return externalSystemRefValues;
	}

	public void setExternalSystemRefValues(List<ExternalSystemRefDto> externalSystemRefValues) {
		this.externalSystemRefValues = externalSystemRefValues;
	}

	public List<ProcessSubTypeRefDto> getProcessSubTypeValues() {
		return processSubTypeValues;
	}

	public void setProcessSubTypeValues(List<ProcessSubTypeRefDto> processSubTypeValues) {
		this.processSubTypeValues = processSubTypeValues;
	}

	public List<PartyDto> getArbitrationAgencyValues() {
		return arbitrationAgencyValues;
	}

	public void setArbitrationAgencyValues(List<PartyDto> arbitrationAgencyValues) {
		this.arbitrationAgencyValues = arbitrationAgencyValues;
	}

	public List<ProductDto> getChildren() {
		return children;
	}

	public void setChildren(List<ProductDto> children) {
		this.children = children;
	}

	public List<IntlCodeTypeRefDto> getIntlCodeTypeRefValues() {
		return intlCodeTypeRefValues;
	}

	public void setIntlCodeTypeRefValues(List<IntlCodeTypeRefDto> intlCodeTypeRefValues) {
		this.intlCodeTypeRefValues = intlCodeTypeRefValues;
	}

	public List<Integer> getDeletedArbitrationMappingIds() {
		return deletedArbitrationMappingIds;
	}

	public void setDeletedArbitrationMappingIds(List<Integer> deletedArbitrationMappingIds) {
		this.deletedArbitrationMappingIds = deletedArbitrationMappingIds;
	}

	public List<Integer> getDeletedGradeMappingIds() {
		return deletedGradeMappingIds;
	}

	public void setDeletedGradeMappingIds(List<Integer> deletedGradeMappingIds) {
		this.deletedGradeMappingIds = deletedGradeMappingIds;
	}

	private Integer prodId;
	private String prodCode;
	private String prodName;
	private String prodFullName;
	private String specialityApplicable;
	private String statusName;
	private Integer statusId;
	private String productLimit;
	private String isBaseProduct;
	private String parentProductName;
	private Integer parentProductId;
	
	//Processing Type
	private Integer processMappingId;
	private String processingCode;
	private String processingName;
	private Collection<ProcessingSubTypeDto> processingSubType;
	//private String processingSubType;//Dropdown
	private Integer processingSubTypeId;//Fk from the dropdown table
	
	//ICO Index
	
	private String icoCode;
	private String icoName;
	private Integer icoMappingId;
	
	//Product & Arbitration Mapping
	
	private Collection<ProductArbitrationDto> productArbitrationCollection;
	
	//External Mapping
	//private Collection<ExternalMappingDto> externalMappingCollection;
	private Integer destinationSystemId;
	private String destinationName;
	private String attribute1;
	private String attribute2;
	private Integer externalSystemMappingId;
	
	//International Product /Grade Code mapping
	private Collection<InternationalGradeCodeMappingDto> gradeCodeMappingCollection; 
	
	//Dropdown values
	private List<ExternalSystemRefDto> externalSystemRefValues = new ArrayList<>(); 
	private List<ProcessSubTypeRefDto> processSubTypeValues = new ArrayList<>();
	private List<PartyDto> arbitrationAgencyValues = new ArrayList<>();
	private List<ProductDto> children;
	private List<IntlCodeTypeRefDto> intlCodeTypeRefValues = new ArrayList<>();
	
	//Deleted Ids for the accordions
	private List<Integer> deletedArbitrationMappingIds;
	private List<Integer> deletedGradeMappingIds;

	public void addChildren(ProductDto child) {
		if (this.children == null) {
			this.children = new ArrayList<>();
		}
		this.children.add(child);
	}
	
}

package com.olam.score.authorizationconfig.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.olam.score.common.dto.BaseDto;

import lombok.Data;

@Data
public class ProductDto extends BaseDto {
	
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

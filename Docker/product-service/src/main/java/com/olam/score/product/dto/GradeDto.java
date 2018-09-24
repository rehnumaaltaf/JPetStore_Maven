package com.olam.score.product.dto;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.olam.score.common.dto.BaseDto;

public class GradeDto extends BaseDto {

	private Integer gradeId;
	@NotNull(message = "notNull_gradeCode")
	private String gradeCode;
	@NotNull(message = "notNull_gradeName")
	private String gradeName;
	private String gradeFullName;
	@NotNull(message = "notNull_product")
	private ProductDto product;
	private List<OriginGradeDto> originList;
	private String isBlended;
	private String isBrand;
	private BrandDto brand;
	private String isBase;
	private Integer baseGradeId;
	private String baseGradeCode;
	private String baseGradeName;
	private ProcessTypeDto processingType;
	private ProcessSubTypeRefDto processingSubType;
	private Integer icoIndexId;
	private String icoIndexCode;
	private String icoIndexName;
	private Integer gradeGroupId;
	private String gradeGroupCode;
	private String gradeGroupName;
	private String gradeIsRaw;
	private String isTenderable;
	private BigDecimal hedgeRatio;
	private BigDecimal premiumOrDiscount;
	private Integer currencyId;
	private String currencyCode;
	private String currencyName;
	private Integer uomId;
	private String uomCode;
	private String uomName;
	private List<IntlGradeDto> intlGradeList;
	private List<ExternalSystemDto> externalSystemList;
	private Integer statusId;
	private String statusName;
	private List<Integer> deletedExternalMappings;
	private List<Integer> deletedIntMappings;

	public Integer getGradeId() {
		return gradeId;
	}

	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}

	public String getGradeCode() {
		return gradeCode;
	}

	public void setGradeCode(String gradeCode) {
		this.gradeCode = gradeCode;
	}

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	public String getGradeFullName() {
		return gradeFullName;
	}

	public void setGradeFullName(String gradeFullName) {
		this.gradeFullName = gradeFullName;
	}

	public List<OriginGradeDto> getOriginList() {
		return originList;
	}

	public void setOriginList(List<OriginGradeDto> originList) {
		this.originList = originList;
	}

	public String getIsBlended() {
		return isBlended;
	}

	public void setIsBlended(String isBlended) {
		this.isBlended = isBlended;
	}

	public String getIsBrand() {
		return isBrand;
	}

	public void setIsBrand(String isBrand) {
		this.isBrand = isBrand;
	}

	public String getIsBase() {
		return isBase;
	}

	public void setIsBase(String isBase) {
		this.isBase = isBase;
	}

	public Integer getBaseGradeId() {
		return baseGradeId;
	}

	public void setBaseGradeId(Integer baseGradeId) {
		this.baseGradeId = baseGradeId;
	}

	public String getBaseGradeCode() {
		return baseGradeCode;
	}

	public void setBaseGradeCode(String baseGradeCode) {
		this.baseGradeCode = baseGradeCode;
	}

	public String getBaseGradeName() {
		return baseGradeName;
	}

	public void setBaseGradeName(String baseGradeName) {
		this.baseGradeName = baseGradeName;
	}

	public Integer getIcoIndexId() {
		return icoIndexId;
	}

	public void setIcoIndexId(Integer icoIndexId) {
		this.icoIndexId = icoIndexId;
	}

	public String getIcoIndexCode() {
		return icoIndexCode;
	}

	public void setIcoIndexCode(String icoIndexCode) {
		this.icoIndexCode = icoIndexCode;
	}

	public String getIcoIndexName() {
		return icoIndexName;
	}

	public void setIcoIndexName(String icoIndexName) {
		this.icoIndexName = icoIndexName;
	}

	public Integer getGradeGroupId() {
		return gradeGroupId;
	}

	public void setGradeGroupId(Integer gradeGroupId) {
		this.gradeGroupId = gradeGroupId;
	}

	public String getGradeGroupCode() {
		return gradeGroupCode;
	}

	public void setGradeGroupCode(String gradeGroupCode) {
		this.gradeGroupCode = gradeGroupCode;
	}

	public String getGradeGroupName() {
		return gradeGroupName;
	}

	public void setGradeGroupName(String gradeGroupName) {
		this.gradeGroupName = gradeGroupName;
	}

	public String getGradeIsRaw() {
		return gradeIsRaw;
	}

	public void setGradeIsRaw(String gradeIsRaw) {
		this.gradeIsRaw = gradeIsRaw;
	}

	public String getIsTenderable() {
		return isTenderable;
	}

	public void setIsTenderable(String isTenderable) {
		this.isTenderable = isTenderable;
	}

	public BigDecimal getHedgeRatio() {
		return hedgeRatio;
	}

	public void setHedgeRatio(BigDecimal hedgeRatio) {
		this.hedgeRatio = hedgeRatio;
	}

	public BigDecimal getPremiumOrDiscount() {
		return premiumOrDiscount;
	}

	public void setPremiumOrDiscount(BigDecimal premiumOrDiscount) {
		this.premiumOrDiscount = premiumOrDiscount;
	}

	public Integer getCurrencyId() {
		return currencyId;
	}

	public void setCurrencyId(Integer currencyId) {
		this.currencyId = currencyId;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getCurrencyName() {
		return currencyName;
	}

	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}

	public Integer getUomId() {
		return uomId;
	}

	public void setUomId(Integer uomId) {
		this.uomId = uomId;
	}

	public String getUomCode() {
		return uomCode;
	}

	public void setUomCode(String uomCode) {
		this.uomCode = uomCode;
	}

	public String getUomName() {
		return uomName;
	}

	public void setUomName(String uomName) {
		this.uomName = uomName;
	}

	public List<IntlGradeDto> getIntlGradeList() {
		return intlGradeList;
	}

	public void setIntlGradeList(List<IntlGradeDto> intlGradeList) {
		this.intlGradeList = intlGradeList;
	}

	public List<ExternalSystemDto> getExternalSystemList() {
		return externalSystemList;
	}

	public void setExternalSystemList(List<ExternalSystemDto> externalSystemList) {
		this.externalSystemList = externalSystemList;
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

	public ProductDto getProduct() {
		return product;
	}

	public void setProduct(ProductDto product) {
		this.product = product;
	}

	public BrandDto getBrand() {
		return brand;
	}

	public void setBrand(BrandDto brand) {
		this.brand = brand;
	}

	public ProcessTypeDto getProcessingType() {
		return processingType;
	}

	public void setProcessingType(ProcessTypeDto processingType) {
		this.processingType = processingType;
	}

	public ProcessSubTypeRefDto getProcessingSubType() {
		return processingSubType;
	}

	public void setProcessingSubType(ProcessSubTypeRefDto processingSubType) {
		this.processingSubType = processingSubType;
	}

	public List<Integer> getDeletedIntMappings() {
		return deletedIntMappings;
	}

	public void setDeletedIntMappings(List<Integer> deletedIntMappings) {
		this.deletedIntMappings = deletedIntMappings;
	}

	public List<Integer> getDeletedExternalMappings() {
		return deletedExternalMappings;
	}

	public void setDeletedExternalMappings(List<Integer> deletedExternalMappings) {
		this.deletedExternalMappings = deletedExternalMappings;
	}
	
	

}

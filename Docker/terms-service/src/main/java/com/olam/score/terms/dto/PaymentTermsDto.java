package com.olam.score.terms.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.modelmapper.PropertyMap;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.olam.score.terms.domain.MasterPaymentTerm;

public class PaymentTermsDto {
	
	private Integer paymentTermId;
	private String payTermCode;
	private String payTermName;
	private String payTermDesc;
	
	private String payTermLcBased;
	private String payTermAdvanceBased;
	
	//Get this value on basis of db value of payTermLcBased or payTermAdvancedBased
	private String basePaymentTermCode;
	
	private String createdBy;
	private Date createdDate;
	private String modifiedBy;
	private Date modifiedDate;
	
	//DB field fkStatusId
	private Integer statusId;
	private String statusName;
	
	private BigDecimal dueDays;
	
	private BigDecimal contingencyDays;
	
	private String printDescription;
	
	//DB field fkPayBasisId
	private Integer paymentBasisRefId;
	private String paymentBasisRef;
	//DB field fkPaymentTermBaseId
	private Integer paymentTermBaseId;
	private String paymentTermBase;
	
	
	
	//DB field AT_SIGHT_USANCE_FLAG_ID
	private Integer atSightUsanceFlagId;
	private String atSightUsance;
	//DB field REVOCABLE_IRREVOCABLE_FLAG_ID
	private Integer revocableIrrevocableFlagId;
	private String revocableIrrevocableFlag;
	//DB field CONFIRMED_NON_CONFIRMED_FLAG_ID
	private Integer confirmedNonconfirmedFlagId;
	private String confirmedNonconfirmedFlag;
	
	private Collection<ExternalMappingDto> externalMappingCollection;
	
	
	//Fields for dropbox
	private List<PaymentBasisRefDto> paymentBasisRefValues;
	private List<DisplayValue> basePaymentTermValues;
	private List<DisplayValue> externalSystemRefValues;
	private List<DisplayValue> confirmedNonconfirmedValues;
	private List<DisplayValue> revocableIrrevocableValues;
	private List<DisplayValue> atSightUsanceValues;
	
	@JsonProperty("deletedMappings")
	private List<Integer> deletedExternalMappingId = new ArrayList<>();
	
	public List<Integer> getDeletedExternalMappingId() {
		return deletedExternalMappingId;
	}
	public void setDeletedExternalMappingId(List<Integer> deletedExternalMappingId) {
		this.deletedExternalMappingId = deletedExternalMappingId;
	}
	public List<DisplayValue> getConfirmedNonconfirmedValues() {
		return confirmedNonconfirmedValues;
	}
	public void setConfirmedNonconfirmedValues(List<DisplayValue> confirmedNonconfirmedValues) {
		this.confirmedNonconfirmedValues = confirmedNonconfirmedValues;
	}
	public List<DisplayValue> getRevocableIrrevocableValues() {
		return revocableIrrevocableValues;
	}
	public void setRevocableIrrevocableValues(List<DisplayValue> revocableIrrevocableValues) {
		this.revocableIrrevocableValues = revocableIrrevocableValues;
	}
	public List<DisplayValue> getAtSightUsanceValues() {
		return atSightUsanceValues;
	}
	public void setAtSightUsanceValues(List<DisplayValue> atSightUsanceValues) {
		this.atSightUsanceValues = atSightUsanceValues;
	}
	public List<DisplayValue> getExternalSystemRefValues() {
		return externalSystemRefValues;
	}
	public void setExternalSystemRefValues(List<DisplayValue> externalSystemRefValues) {
		this.externalSystemRefValues = externalSystemRefValues;
	}
	public List<PaymentBasisRefDto> getPaymentBasisRefValues() {
		return paymentBasisRefValues;
	}
	public void setPaymentBasisRefValues(List<PaymentBasisRefDto> paymentBasisRefValues) {
		this.paymentBasisRefValues = paymentBasisRefValues;
	}
	public List<DisplayValue> getBasePaymentTermValues() {
		return basePaymentTermValues;
	}
	public void setBasePaymentTermValues(List<DisplayValue> basePaymentTermValues) {
		this.basePaymentTermValues = basePaymentTermValues;
	}
	public Collection<ExternalMappingDto> getExternalMappingCollection() {
		return externalMappingCollection;
	}
	public void setExternalMappingCollection(Collection<ExternalMappingDto> externalMappingCollection) {
		this.externalMappingCollection = externalMappingCollection;
	}
	public String getPayTermCode() {
		return payTermCode;
	}
	public void setPayTermCode(String payTermCode) {
		this.payTermCode = payTermCode;
	}
	public String getPayTermName() {
		return payTermName;
	}
	public void setPayTermName(String payTermName) {
		this.payTermName = payTermName;
	}
	public String getPayTermDesc() {
		return payTermDesc;
	}
	public void setPayTermDesc(String payTermDesc) {
		this.payTermDesc = payTermDesc;
	}
	public String getPayTermLcBased() {
		return payTermLcBased;
	}
	public void setPayTermLcBased(String payTermLcBased) {
		this.payTermLcBased = payTermLcBased;
	}
	public String getPayTermAdvanceBased() {
		return payTermAdvanceBased;
	}
	public void setPayTermAdvanceBased(String payTermAdvanceBased) {
		this.payTermAdvanceBased = payTermAdvanceBased;
	}
	public Integer getPaymentTermId() {
		return paymentTermId;
	}
	public void setPaymentTermId(Integer paymentTermId) {
		this.paymentTermId = paymentTermId;
	}
	public BigDecimal getContingencyDays() {
		return contingencyDays;
	}
	public void setContingencyDays(BigDecimal contingencyDays) {
		this.contingencyDays = contingencyDays;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public BigDecimal getDueDays() {
		return dueDays;
	}
	public void setDueDays(BigDecimal dueDays) {
		this.dueDays = dueDays;
	}
	public Integer getPaymentBasisRefId() {
		return paymentBasisRefId;
	}
	public void setPaymentBasisRefId(Integer paymentBasisRefId) {
		this.paymentBasisRefId = paymentBasisRefId;
	}
	public String getPaymentBasisRef() {
		return paymentBasisRef;
	}
	public void setPaymentBasisRef(String paymentBasisRef) {
		this.paymentBasisRef = paymentBasisRef;
	}
	public Integer getPaymentTermBaseId() {
		return paymentTermBaseId;
	}
	public void setPaymentTermBaseId(Integer paymentTermBaseId) {
		this.paymentTermBaseId = paymentTermBaseId;
	}
	public String getPaymentTermBase() {
		return paymentTermBase;
	}
	public void setPaymentTermBase(String paymentTermBase) {
		this.paymentTermBase = paymentTermBase;
	}
	public Integer getAtSightUsanceFlagId() {
		return atSightUsanceFlagId;
	}
	public void setAtSightUsanceFlagId(Integer atSightUsanceFlagId) {
		this.atSightUsanceFlagId = atSightUsanceFlagId;
	}
	public Integer getRevocableIrrevocableFlagId() {
		return revocableIrrevocableFlagId;
	}
	public void setRevocableIrrevocableFlagId(Integer revocableIrrevocableFlagId) {
		this.revocableIrrevocableFlagId = revocableIrrevocableFlagId;
	}
	public String getRevocableIrrevocableFlag() {
		return revocableIrrevocableFlag;
	}
	public void setRevocableIrrevocableFlag(String revocableIrrevocableFlag) {
		this.revocableIrrevocableFlag = revocableIrrevocableFlag;
	}
	public Integer getConfirmedNonconfirmedFlagId() {
		return confirmedNonconfirmedFlagId;
	}
	public void setConfirmedNonconfirmedFlagId(Integer confirmedNonconfirmedFlagId) {
		this.confirmedNonconfirmedFlagId = confirmedNonconfirmedFlagId;
	}
	public String getConfirmedNonconfirmedFlag() {
		return confirmedNonconfirmedFlag;
	}
	public void setConfirmedNonconfirmedFlag(String confirmedNonconfirmedFlag) {
		this.confirmedNonconfirmedFlag = confirmedNonconfirmedFlag;
	}
	public String getBasePaymentTermCode() {
		return basePaymentTermCode;
	}
	public void setBasePaymentTermCode(String basePaymentTermCode) {
		this.basePaymentTermCode = basePaymentTermCode;
	}
	public String getAtSightUsance() {
		return atSightUsance;
	}
	public void setAtSightUsance(String atSightUsance) {
		this.atSightUsance = atSightUsance;
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
	public String getPrintDescription() {
		return printDescription;
	}
	public void setPrintDescription(String printDescription) {
		this.printDescription = printDescription;
	}

}


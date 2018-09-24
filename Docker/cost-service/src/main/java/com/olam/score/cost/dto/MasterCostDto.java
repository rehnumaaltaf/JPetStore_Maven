package com.olam.score.cost.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class MasterCostDto {

	private Integer pkCostId;
	private String createdBy;
	private Date createdDate;
	private String modifiedBy;
	private Date modifiedDate;
	private Integer fkStatusId;
	private String fkStatusName;
	private String costName;
	private String costCode;
	private String costFullName;
	private Integer fkCostGroupId;
	private String fkCostGroupName;
	private String costTypeIsPrimaryInd;
	private String matrixApplicableInd;
	private Integer fkMatrixEntityId;
	private String fkMatrixEntityName;
	private BigDecimal costDefaultValue;
	private Integer fkcostDefValueTypeId;
	private String fkcostDefValueTypeName;
	private Integer fkIndexUomId;
	private Integer fkCurrencyId;
	private String fkCurrencyName;
	private String nettedForMtmInd;
	private String realizedInd;
	private String capitalizeCostInd;
	private Integer fkRevenueGlId;
	private String fkRevenueGlName;
	private Integer fkExpenseGlId;
	private String fkExpenseGlName;
	private String  isApplicableToAllPartyInd;
	private Integer fkDefaultValueBasisRefId;
	private String fkDefaultValueBasisRefName;
	private Integer fkDefaultValueUomId;
	private String fkDefaultValueUomName;
	private Integer fkDefValPrimaryPackingId;
	private String fkDefValPrimaryPackingName;
	private Integer fkDefValSecondaryPackingId;
	private String fkDefValSecondaryPackingName;
	private String action;
	
	private List<MasterCostPartyAssocDto> fkPartyId;
	private List<MasterCostExternalMappingDto> exterPackAssoc;
	
	public List<MasterCnfCostDto> cnfCostDto;
	//added by p
	private List<MasterCnfCostDetailDto> masterCnfCostDetailDto;
public List<WareHouseCostDTO> wareHouseCostDto;
public List<MasterFreightCostMatrixDto> masterFreightCostMatrixDto;

	public int isMappedToMatrix;
	
	public int getIsMappedToMatrix() {
		return isMappedToMatrix;
	}
	public void setIsMappedToMatrix(int isMappedToMatrix) {
		this.isMappedToMatrix = isMappedToMatrix;
	}
	public List<MasterFreightCostMatrixDto> getMasterFreightCostMatrixDto() {
	return masterFreightCostMatrixDto;
}
public void setMasterFreightCostMatrixDto(List<MasterFreightCostMatrixDto> masterFreightCostMatrixDto) {
	this.masterFreightCostMatrixDto = masterFreightCostMatrixDto;
}
	public List<WareHouseCostDTO> getWareHouseCostDto() {
		return wareHouseCostDto;
	}
	public void setWareHouseCostDto(List<WareHouseCostDTO> wareHouseCostDto) {
		this.wareHouseCostDto = wareHouseCostDto;
	}
	public List<MasterCnfCostDetailDto> getMasterCnfCostDetailDto() {
		return masterCnfCostDetailDto;
	}
	public void setMasterCnfCostDetailDto(List<MasterCnfCostDetailDto> masterCnfCostDetailDto) {
		this.masterCnfCostDetailDto = masterCnfCostDetailDto;
	}
	public List<MasterCostExternalMappingDto> getExterPackAssoc() {
		return exterPackAssoc;
	}
	public void setExterPackAssoc(List<MasterCostExternalMappingDto> exterPackAssoc) {
		this.exterPackAssoc = exterPackAssoc;
	}
	public void setCostFullName(String costFullName) {
		this.costFullName = costFullName;
	}
	public Integer getPkCostId() {
		return pkCostId;
	}
	public void setPkCostId(Integer pkCostId) {
		this.pkCostId = pkCostId;
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
	public Integer getFkStatusId() {
		return fkStatusId;
	}
	public void setFkStatusId(Integer fkStatusId) {
		this.fkStatusId = fkStatusId;
	}
	
	public String getFkStatusName() {
		return fkStatusName;
	}
	public void setFkStatusName(String fkStatusName) {
		this.fkStatusName = fkStatusName;
	}
	public String getCostName() {
		return costName;
	}
	public void setCostName(String costName) {
		this.costName = costName;
	}
	public String getCostCode() {
		return costCode;
	}
	public void setCostCode(String costCode) {
		this.costCode = costCode;
	}
	public String getCostFullName() {
		return costFullName;
	}
	public Integer getFkCostGroupId() {
		return fkCostGroupId;
	}
	public void setFkCostGroupId(Integer fkCostGroupId) {
		this.fkCostGroupId = fkCostGroupId;
	}
	public String getFkCostGroupName() {
		return fkCostGroupName;
	}
	public void setFkCostGroupName(String fkCostGroupName) {
		this.fkCostGroupName = fkCostGroupName;
	}
	public String getCostTypeIsPrimaryInd() {
		return costTypeIsPrimaryInd;
	}
	public void setCostTypeIsPrimaryInd(String costTypeIsPrimaryInd) {
		this.costTypeIsPrimaryInd = costTypeIsPrimaryInd;
	}
	public String getMatrixApplicableInd() {
		return matrixApplicableInd;
	}
	public void setMatrixApplicableInd(String matrixApplicableInd) {
		this.matrixApplicableInd = matrixApplicableInd;
	}
	public Integer getFkMatrixEntityId() {
		return fkMatrixEntityId;
	}
	public void setFkMatrixEntityId(Integer fkMatrixEntityId) {
		this.fkMatrixEntityId = fkMatrixEntityId;
	}
	public String getFkMatrixEntityName() {
		return fkMatrixEntityName;
	}
	public void setFkMatrixEntityName(String fkMatrixEntityName) {
		this.fkMatrixEntityName = fkMatrixEntityName;
	}
	public BigDecimal getCostDefaultValue() {
		return costDefaultValue;
	}
	public void setCostDefaultValue(BigDecimal costDefaultValue) {
		this.costDefaultValue = costDefaultValue;
	}
	public Integer getFkcostDefValueTypeId() {
		return fkcostDefValueTypeId;
	}
	public void setFkcostDefValueTypeId(Integer fkcostDefValueTypeId) {
		this.fkcostDefValueTypeId = fkcostDefValueTypeId;
	}
	public String getFkcostDefValueTypeName() {
		return fkcostDefValueTypeName;
	}
	public void setFkcostDefValueTypeName(String fkcostDefValueTypeName) {
		this.fkcostDefValueTypeName = fkcostDefValueTypeName;
	}
	public Integer getFkIndexUomId() {
		return fkIndexUomId;
	}
	public void setFkIndexUomId(Integer fkIndexUomId) {
		this.fkIndexUomId = fkIndexUomId;
	}
	public Integer getFkCurrencyId() {
		return fkCurrencyId;
	}
	public void setFkCurrencyId(Integer fkCurrencyId) {
		this.fkCurrencyId = fkCurrencyId;
	}
	public String getFkCurrencyName() {
		return fkCurrencyName;
	}
	public void setFkCurrencyName(String fkCurrencyName) {
		this.fkCurrencyName = fkCurrencyName;
	}
	public String getNettedForMtmInd() {
		return nettedForMtmInd;
	}
	public void setNettedForMtmInd(String nettedForMtmInd) {
		this.nettedForMtmInd = nettedForMtmInd;
	}
	public String getRealizedInd() {
		return realizedInd;
	}
	public void setRealizedInd(String realizedInd) {
		this.realizedInd = realizedInd;
	}
	public String getCapitalizeCostInd() {
		return capitalizeCostInd;
	}
	public void setCapitalizeCostInd(String capitalizeCostInd) {
		this.capitalizeCostInd = capitalizeCostInd;
	}
	public Integer getFkRevenueGlId() {
		return fkRevenueGlId;
	}
	public void setFkRevenueGlId(Integer fkRevenueGlId) {
		this.fkRevenueGlId = fkRevenueGlId;
	}
	public String getFkRevenueGlName() {
		return fkRevenueGlName;
	}
	public void setFkRevenueGlName(String fkRevenueGlName) {
		this.fkRevenueGlName = fkRevenueGlName;
	}
	public Integer getFkExpenseGlId() {
		return fkExpenseGlId;
	}
	public void setFkExpenseGlId(Integer fkExpenseGlId) {
		this.fkExpenseGlId = fkExpenseGlId;
	}
	public String getFkExpenseGlName() {
		return fkExpenseGlName;
	}
	public void setFkExpenseGlName(String fkExpenseGlName) {
		this.fkExpenseGlName = fkExpenseGlName;
	}
	public Integer getFkDefaultValueBasisRefId() {
		return fkDefaultValueBasisRefId;
	}
	public void setFkDefaultValueBasisRefId(Integer fkDefaultValueBasisRefId) {
		this.fkDefaultValueBasisRefId = fkDefaultValueBasisRefId;
	}
	public String getFkDefaultValueBasisRefName() {
		return fkDefaultValueBasisRefName;
	}
	public void setFkDefaultValueBasisRefName(String fkDefaultValueBasisRefName) {
		this.fkDefaultValueBasisRefName = fkDefaultValueBasisRefName;
	}
	public Integer getFkDefaultValueUomId() {
		return fkDefaultValueUomId;
	}
	public void setFkDefaultValueUomId(Integer fkDefaultValueUomId) {
		this.fkDefaultValueUomId = fkDefaultValueUomId;
	}
	public String getFkDefaultValueUomName() {
		return fkDefaultValueUomName;
	}
	public void setFkDefaultValueUomName(String fkDefaultValueUomName) {
		this.fkDefaultValueUomName = fkDefaultValueUomName;
	}
	public Integer getFkDefValPrimaryPackingId() {
		return fkDefValPrimaryPackingId;
	}
	public void setFkDefValPrimaryPackingId(Integer fkDefValPrimaryPackingId) {
		this.fkDefValPrimaryPackingId = fkDefValPrimaryPackingId;
	}
	public String getFkDefValPrimaryPackingName() {
		return fkDefValPrimaryPackingName;
	}
	public void setFkDefValPrimaryPackingName(String fkDefValPrimaryPackingName) {
		this.fkDefValPrimaryPackingName = fkDefValPrimaryPackingName;
	}
	public Integer getFkDefValSecondaryPackingId() {
		return fkDefValSecondaryPackingId;
	}
	public void setFkDefValSecondaryPackingId(Integer fkDefValSecondaryPackingId) {
		this.fkDefValSecondaryPackingId = fkDefValSecondaryPackingId;
	}
	public String getFkDefValSecondaryPackingName() {
		return fkDefValSecondaryPackingName;
	}
	public void setFkDefValSecondaryPackingName(String fkDefValSecondaryPackingName) {
		this.fkDefValSecondaryPackingName = fkDefValSecondaryPackingName;
	}
	public String getIsApplicableToAllPartyInd() {
		return isApplicableToAllPartyInd;
	}
	public void setIsApplicableToAllPartyInd(String isApplicableToAllPartyInd) {
		this.isApplicableToAllPartyInd = isApplicableToAllPartyInd;
	}
	public List<MasterCostPartyAssocDto> getFkPartyId() {
		return fkPartyId;
	}
	public void setFkPartyId(List<MasterCostPartyAssocDto> fkPartyId) {
		this.fkPartyId = fkPartyId;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public List<MasterCnfCostDto> getCnfCostDto() {
		return cnfCostDto;
	}
	public void setCnfCostDto(List<MasterCnfCostDto> cnfCostDto) {
		this.cnfCostDto = cnfCostDto;
	}
    
	

}

package com.olam.score.cost.dto;

import java.util.Date;
import java.util.List;

import com.olam.score.common.domain.DropDownModel;

public class MasterCnfCostDto {
	private Integer pkCnfCostId;
	public List<MasterCnfCostPartyAssocDto> fkPartyId;
	public int isApplicableToAllPartyInd;
	public int fkProdId;
	public String fkProdName;
	public String costIsDetailedInd;
	public String costIsDetailedIndValue;
	public int fkWarehousePartyId;
	public String fkWarehousePartyName;
	public List<MasterCnfCostStockLocationAssocDto> fkPartyWhStockLocationId;
	public int isApplicableToAllStockLocInd;
	public String cnfCostContrRefNo;
	public Date cnfCostValidFrom;
	public Date cnfCostValidTo;
	public String cnfCostRemarks;
	public List<MasterCnfCostDetailDto> masterCnfCostDetail;
	 public List<MasterCostPartyAssocDto> getMasterCostPartyAssocDto() {
		return masterCostPartyAssocDto;
	}
	public void setMasterCostPartyAssocDto(List<MasterCostPartyAssocDto> masterCostPartyAssocDto) {
		this.masterCostPartyAssocDto = masterCostPartyAssocDto;
	}
	private List<MasterCostPartyAssocDto> masterCostPartyAssocDto;
	public Integer fkCostId;

	public Integer getFkCostId() {
		return fkCostId;
	}
	public void setFkCostId(Integer fkCostId) {
		this.fkCostId = fkCostId;
	}
	public int getIsApplicableToAllPartyInd() {
		return isApplicableToAllPartyInd;
	}
	public void setIsApplicableToAllPartyInd(int isApplicableToAllPartyInd) {
		this.isApplicableToAllPartyInd = isApplicableToAllPartyInd;
	}
	public String getFkProdName() {
		return fkProdName;
	}
	public void setFkProdName(String fkProdName) {
		this.fkProdName = fkProdName;
	}
	public String getCostIsDetailedIndValue() {
		return costIsDetailedIndValue;
	}
	public void setCostIsDetailedIndValue(String costIsDetailedIndValue) {
		this.costIsDetailedIndValue = costIsDetailedIndValue;
	}
	public String getFkWarehousePartyName() {
		return fkWarehousePartyName;
	}
	public void setFkWarehousePartyName(String fkWarehousePartyName) {
		this.fkWarehousePartyName = fkWarehousePartyName;
	}
	public Integer getPkCnfCostId() {
		return pkCnfCostId;
	}
	public void setPkCnfCostId(Integer pkCnfCostId) {
		this.pkCnfCostId = pkCnfCostId;
	}
	
	public int getFkProdId() {
		return fkProdId;
	}
	public void setFkProdId(int fkProdId) {
		this.fkProdId = fkProdId;
	}
	public String getCostIsDetailedInd() {
		return costIsDetailedInd;
	}
	public void setCostIsDetailedInd(String costIsDetailedInd) {
		this.costIsDetailedInd = costIsDetailedInd;
	}
	public int getFkWarehousePartyId() {
		return fkWarehousePartyId;
	}
	public void setFkWarehousePartyId(int fkWarehousePartyId) {
		this.fkWarehousePartyId = fkWarehousePartyId;
	}
	
	public List<MasterCnfCostPartyAssocDto> getFkPartyId() {
		return fkPartyId;
	}
	public void setFkPartyId(List<MasterCnfCostPartyAssocDto> fkPartyId) {
		this.fkPartyId = fkPartyId;
	}
	public List<MasterCnfCostStockLocationAssocDto> getFkPartyWhStockLocationId() {
		return fkPartyWhStockLocationId;
	}
	public void setFkPartyWhStockLocationId(List<MasterCnfCostStockLocationAssocDto> fkPartyWhStockLocationId) {
		this.fkPartyWhStockLocationId = fkPartyWhStockLocationId;
	}
	public String getCnfCostContrRefNo() {
		return cnfCostContrRefNo;
	}
	public void setCnfCostContrRefNo(String cnfCostContrRefNo) {
		this.cnfCostContrRefNo = cnfCostContrRefNo;
	}
	public Date getCnfCostValidFrom() {
		return cnfCostValidFrom;
	}
	public void setCnfCostValidFrom(Date cnfCostValidFrom) {
		this.cnfCostValidFrom = cnfCostValidFrom;
	}
	public Date getCnfCostValidTo() {
		return cnfCostValidTo;
	}
	public void setCnfCostValidTo(Date cnfCostValidTo) {
		this.cnfCostValidTo = cnfCostValidTo;
	}
	public String getCnfCostRemarks() {
		return cnfCostRemarks;
	}
	public void setCnfCostRemarks(String cnfCostRemarks) {
		this.cnfCostRemarks = cnfCostRemarks;
	}
	public List<MasterCnfCostDetailDto> getMasterCnfCostDetail() {
		return masterCnfCostDetail;
	}
	public void setMasterCnfCostDetail(List<MasterCnfCostDetailDto> masterCnfCostDetail) {
		this.masterCnfCostDetail = masterCnfCostDetail;
	}
	public int getIsApplicableToAllStockLocInd() {
		return isApplicableToAllStockLocInd;
	}
	public void setIsApplicableToAllStockLocInd(int isApplicableToAllStockLocInd) {
		this.isApplicableToAllStockLocInd = isApplicableToAllStockLocInd;
	}
	

}

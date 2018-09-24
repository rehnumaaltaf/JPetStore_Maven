package com.olam.score.cost.dto;

import java.util.List;

public class CostMatrixDto {
	public int pkCostMatrixId;
	public int getPkCostMatrixId() {
		return pkCostMatrixId;
	}
	public void setPkCostMatrixId(int pkCostMatrixId) {
		this.pkCostMatrixId = pkCostMatrixId;
	}
	public String matrixName;
	public String matrixCode;
	public String matrixDesc;
	public int fkCostId;
	public String fkCostName;
	public int matrixTypeId;
	public String matrixTypeName;
	public List<WareHouseCostDTO> wareHouseCostDto;
	public List<MasterCnfCostDto> cnfCostDto;
	public List<MasterFreightCostMatrixDto> freightCostMatrixDto;
	public List<MasterCropSeasonCostDto> cropSeasonCostDto;
	public String action;
	public int fkStatusId;
	public String fkStatusName;
	public String serviceProvider;
	
	public String getServiceProvider() {
		return serviceProvider;
	}
	public void setServiceProvider(String serviceProvider) {
		this.serviceProvider = serviceProvider;
	}
	public String getMatrixName() {
		return matrixName;
	}
	public void setMatrixName(String matrixName) {
		this.matrixName = matrixName;
	}
	public String getMatrixCode() {
		return matrixCode;
	}
	public void setMatrixCode(String matrixCode) {
		this.matrixCode = matrixCode;
	}
	public String getMatrixDesc() {
		return matrixDesc;
	}
	public void setMatrixDesc(String matrixDesc) {
		this.matrixDesc = matrixDesc;
	}
	public int getFkCostId() {
		return fkCostId;
	}
	public void setFkCostId(int fkCostId) {
		this.fkCostId = fkCostId;
	}
	public String getFkCostName() {
		return fkCostName;
	}
	public void setFkCostName(String fkCostName) {
		this.fkCostName = fkCostName;
	}
	public int getMatrixTypeId() {
		return matrixTypeId;
	}
	public void setMatrixTypeId(int matrixTypeId) {
		this.matrixTypeId = matrixTypeId;
	}
	public String getMatrixTypeName() {
		return matrixTypeName;
	}
	public void setMatrixTypeName(String matrixTypeName) {
		this.matrixTypeName = matrixTypeName;
	}
	
	public List<WareHouseCostDTO> getWareHouseCostDto() {
		return wareHouseCostDto;
	}
	public void setWareHouseCostDto(List<WareHouseCostDTO> wareHouseCostDto) {
		this.wareHouseCostDto = wareHouseCostDto;
	}
	public List<MasterCnfCostDto> getCnfCostDto() {
		return cnfCostDto;
	}
	public void setCnfCostDto(List<MasterCnfCostDto> cnfCostDto) {
		this.cnfCostDto = cnfCostDto;
	}
	public List<MasterFreightCostMatrixDto> getFreightCostMatrixDto() {
		return freightCostMatrixDto;
	}
	public void setFreightCostMatrixDto(List<MasterFreightCostMatrixDto> freightCostMatrixDto) {
		this.freightCostMatrixDto = freightCostMatrixDto;
	}
	public List<MasterCropSeasonCostDto> getCropSeasonCostDto() {
		return cropSeasonCostDto;
	}
	public void setCropSeasonCostDto(List<MasterCropSeasonCostDto> cropSeasonCostDto) {
		this.cropSeasonCostDto = cropSeasonCostDto;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public int getFkStatusId() {
		return fkStatusId;
	}
	public void setFkStatusId(int fkStatusId) {
		this.fkStatusId = fkStatusId;
	}
	public String getFkStatusName() {
		return fkStatusName;
	}
	public void setFkStatusName(String fkStatusName) {
		this.fkStatusName = fkStatusName;
	}
	

}


package com.olam.score.cost.dto;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.olam.score.common.domain.DropDownModel;
import com.olam.score.cost.domain.MasterWhCostPartyAssoc;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "pkWhCostId",
    "whCostFreePeriodType",
    "whCostNoOfFreeDays",
    "isApplicableToAllPartyInd",
    "fkProdId",
    "fkProdValue",
    "costIsDetailedInd",
    "wareHouse",
    "wareHouseValue",
    "WarehouseLocation",
    "whCostContrRefNo",
    "whCostValidTo",
    "whCostValidFrom",
    "whCostRemarks",
    "whCostDetailArray",
    "action"
})
public class WareHouseCostDTO {

    @JsonProperty("pkWhCostId")
    private Integer pkWhCostId;
    @JsonProperty("whCostFreePeriodType")
    private String whCostFreePeriodType;
    @JsonProperty("whCostNoOfFreeDays")
    private String whCostNoOfFreeDays;
    @JsonProperty("isApplicableToAllPartyInd")
    private String isApplicableToAllPartyInd;
    
    
    @JsonProperty("fkProdId")
    private Integer fkProdId;
    @JsonProperty("fkProdValue")
    private String fkProdValue;
    @JsonProperty("costIsDetailedInd")
    private String costIsDetailedInd;
    @JsonProperty("wareHouse")
    private Integer wareHouse;
    @JsonProperty("wareHouseValue")
    private String wareHouseValue;
    @JsonProperty("WarehouseLocation")
    private String warehouseLocation;
    @JsonProperty("whCostContrRefNo")
    private String whCostContrRefNo;
    @JsonProperty("whCostValidTo")
    private Date whCostValidTo;
    @JsonProperty("whCostValidFrom")
    private Date whCostValidFrom;
    @JsonProperty("whCostRemarks")
    private String whCostRemarks;
    @JsonProperty("action")
    private String action;
    private List<MasterCostWhPartyAssocDto> legalEntity;
    private List<MasterCostWhStockLocationAssocDto> wareHouseLocation;
    public int isApplicableToAllStockLocInd;
    private List<MasterCostWhPartyAssocDto> masterCostWhPartyAssocDto;
    
    
    public List<MasterCostWhPartyAssocDto> getMasterCostWhPartyAssocDto() {
		return masterCostWhPartyAssocDto;
	}

	public void setMasterCostWhPartyAssocDto(List<MasterCostWhPartyAssocDto> masterCostWhPartyAssocDto) {
		this.masterCostWhPartyAssocDto = masterCostWhPartyAssocDto;
	}

	public int getIsApplicableToAllStockLocInd() {
		return isApplicableToAllStockLocInd;
	}

	public void setIsApplicableToAllStockLocInd(int isApplicableToAllStockLocInd) {
		this.isApplicableToAllStockLocInd = isApplicableToAllStockLocInd;
	}

	

	public List<MasterCostWhPartyAssocDto> getLegalEntity() {
		return legalEntity;
	}

	public void setLegalEntity(List<MasterCostWhPartyAssocDto> legalEntity) {
		this.legalEntity = legalEntity;
	}

	public List<MasterCostWhStockLocationAssocDto> getWareHouseLocation() {
		return wareHouseLocation;
	}

	public void setWareHouseLocation(List<MasterCostWhStockLocationAssocDto> wareHouseLocation) {
		this.wareHouseLocation = wareHouseLocation;
	}

	public void setAdditionalProperties(Map<String, Object> additionalProperties) {
		this.additionalProperties = additionalProperties;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	@JsonProperty("whCostDetailArray")
    private List<WhCostDetailArray> whCostDetailArray = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("pkWhCostId")
    public Integer getPkWhCostId() {
        return pkWhCostId;
    }

    @JsonProperty("pkWhCostId")
    public void setPkWhCostId(Integer pkWhCostId) {
        this.pkWhCostId = pkWhCostId;
    }

    @JsonProperty("whCostFreePeriodType")
    public String getWhCostFreePeriodType() {
        return whCostFreePeriodType;
    }

    @JsonProperty("whCostFreePeriodType")
    public void setWhCostFreePeriodType(String whCostFreePeriodType) {
        this.whCostFreePeriodType = whCostFreePeriodType;
    }

    @JsonProperty("whCostNoOfFreeDays")
    public String getWhCostNoOfFreeDays() {
        return whCostNoOfFreeDays;
    }

    @JsonProperty("whCostNoOfFreeDays")
    public void setWhCostNoOfFreeDays(String whCostNoOfFreeDays) {
        this.whCostNoOfFreeDays = whCostNoOfFreeDays;
    }

    @JsonProperty("isApplicableToAllPartyInd")
    public String getIsApplicableToAllPartyInd() {
        return isApplicableToAllPartyInd;
    }

    @JsonProperty("isApplicableToAllPartyInd")
    public void setIsApplicableToAllPartyInd(String isApplicableToAllPartyInd) {
        this.isApplicableToAllPartyInd = isApplicableToAllPartyInd;
    }

    @JsonProperty("fkProdId")
    public Integer getFkProdId() {
        return fkProdId;
    }

    @JsonProperty("fkProdId")
    public void setFkProdId(Integer fkProdId) {
        this.fkProdId = fkProdId;
    }

    @JsonProperty("fkProdValue")
    public String getFkProdValue() {
        return fkProdValue;
    }

    @JsonProperty("fkProdValue")
    public void setFkProdValue(String fkProdValue) {
        this.fkProdValue = fkProdValue;
    }

    @JsonProperty("costIsDetailedInd")
    public String getCostIsDetailedInd() {
        return costIsDetailedInd;
    }

    @JsonProperty("costIsDetailedInd")
    public void setCostIsDetailedInd(String costIsDetailedInd) {
        this.costIsDetailedInd = costIsDetailedInd;
    }

    @JsonProperty("wareHouse")
    public Integer getWareHouse() {
        return wareHouse;
    }

    @JsonProperty("wareHouse")
    public void setWareHouse(Integer wareHouse) {
        this.wareHouse = wareHouse;
    }

    @JsonProperty("wareHouseValue")
    public String getWareHouseValue() {
        return wareHouseValue;
    }

    @JsonProperty("wareHouseValue")
    public void setWareHouseValue(String wareHouseValue) {
        this.wareHouseValue = wareHouseValue;
    }

    @JsonProperty("WarehouseLocation")
    public String getWarehouseLocation() {
        return warehouseLocation;
    }

    @JsonProperty("WarehouseLocation")
    public void setWarehouseLocation(String warehouseLocation) {
        this.warehouseLocation = warehouseLocation;
    }

    @JsonProperty("whCostContrRefNo")
    public String getWhCostContrRefNo() {
        return whCostContrRefNo;
    }

    @JsonProperty("whCostContrRefNo")
    public void setWhCostContrRefNo(String whCostContrRefNo) {
        this.whCostContrRefNo = whCostContrRefNo;
    }

    

    public Date getWhCostValidTo() {
		return whCostValidTo;
	}

	public void setWhCostValidTo(Date whCostValidTo) {
		this.whCostValidTo = whCostValidTo;
	}

	public Date getWhCostValidFrom() {
		return whCostValidFrom;
	}

	public void setWhCostValidFrom(Date whCostValidFrom) {
		this.whCostValidFrom = whCostValidFrom;
	}

	@JsonProperty("whCostRemarks")
    public String getWhCostRemarks() {
        return whCostRemarks;
    }

    @JsonProperty("whCostRemarks")
    public void setWhCostRemarks(String whCostRemarks) {
        this.whCostRemarks = whCostRemarks;
    }

    @JsonProperty("whCostDetailArray")
    public List<WhCostDetailArray> getWhCostDetailArray() {
        return whCostDetailArray;
    }

    @JsonProperty("whCostDetailArray")
    public void setWhCostDetailArray(List<WhCostDetailArray> whCostDetailArray) {
        this.whCostDetailArray = whCostDetailArray;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}

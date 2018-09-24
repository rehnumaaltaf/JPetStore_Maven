package com.olam.score.reference.dto;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.olam.score.common.dto.BaseDto;

public class ShipmentWeekDto extends BaseDto{

	
	private Integer shipmentWeekTimeframeId;
	@NotNull(message = "notNull_shipmentTimeframeName")
    @NotEmpty(message = "mandatory_shipmentTimeframeName")
	private String shipmentTimeframeName;
	@NotNull(message = "notNull_shipmentTimeframeCode")
    @NotEmpty(message = "mandatory_shipmentTimeframeCode")
	private String shipmentTimeframeCode;
	private String shipmentTimeframeDesc;
	private String printDescription;
	private String ruleText;
	private String statusName;
	private Integer statusId;
	@NotNull(message = "notNull_shipmentRuleId")
	private Integer shipmentRuleId;
	private String shipmentRuleName;
	
	
	public Integer getShipmentWeekTimeframeId() {
		return shipmentWeekTimeframeId;
	}
	public void setShipmentWeekTimeframeId(Integer shipmentWeekTimeframeId) {
		this.shipmentWeekTimeframeId = shipmentWeekTimeframeId;
	}
	public String getShipmentTimeframeName() {
		return shipmentTimeframeName;
	}
	public void setShipmentTimeframeName(String shipmentTimeframeName) {
		this.shipmentTimeframeName = shipmentTimeframeName;
	}
	public String getShipmentTimeframeCode() {
		return shipmentTimeframeCode;
	}
	public void setShipmentTimeframeCode(String shipmentTimeframeCode) {
		this.shipmentTimeframeCode = shipmentTimeframeCode;
	}
	public String getShipmentTimeframeDesc() {
		return shipmentTimeframeDesc;
	}
	public void setShipmentTimeframeDesc(String shipmentTimeframeDesc) {
		this.shipmentTimeframeDesc = shipmentTimeframeDesc;
	}
	public String getPrintDescription() {
		return printDescription;
	}
	public void setPrintDescription(String printDescription) {
		this.printDescription = printDescription;
	}
	public String getRuleText() {
		return ruleText;
	}
	public void setRuleText(String ruleText) {
		this.ruleText = ruleText;
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
	public Integer getShipmentRuleId() {
		return shipmentRuleId;
	}
	public void setShipmentRuleId(Integer shipmentRuleId) {
		this.shipmentRuleId = shipmentRuleId;
	}
	public String getShipmentRuleName() {
		return shipmentRuleName;
	}
	public void setShipmentRuleName(String shipmentRuleName) {
		this.shipmentRuleName = shipmentRuleName;
	}
	
	
}

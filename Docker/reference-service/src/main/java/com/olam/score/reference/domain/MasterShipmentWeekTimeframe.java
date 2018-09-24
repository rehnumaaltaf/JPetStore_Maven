/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olam.score.reference.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import com.olam.score.reference.dto.ShipmentWeekDto;

/**
 *
 * @author rashmi.kedlaya
 */
@Entity
@Table(name = "MASTER_SHIPMENT_WEEK_TIMEFRAME")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterShipmentWeekTimeframe.findAll", query = "SELECT m FROM MasterShipmentWeekTimeframe m")})
public class MasterShipmentWeekTimeframe implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @SequenceGenerator(name="MASTER_SHIPMENT_WEEK_TIMEFRAME_SHIPMENTWEEKTIMEFRAMEIDSEQ_GENERATOR", sequenceName="SHIPMENT_WEEK_TIMEFRAME_ID_SEQ",allocationSize=1)//, schema="reference")
	@GeneratedValue(generator="MASTER_SHIPMENT_WEEK_TIMEFRAME_SHIPMENTWEEKTIMEFRAMEIDSEQ_GENERATOR")
    @Column(name = "PK_SHIPMENT_WEEK_TIMEFRAME_ID")
    private Integer pkShipmentWeekTimeframeId;
    @Size(max = 200)
    @Column(name = "SHIPMENT_TIMEFRAME_NAME")
    private String shipmentTimeframeName;
    @Size(max = 20)
    @Column(name = "SHIPMENT_TIMEFRAME_CODE")
    private String shipmentTimeframeCode;
    @Size(max = 1000)
    @Column(name = "SHIPMENT_TIMEFRAME_DESC")
    private String shipmentTimeframeDesc;
    @Size(max = 100)
    @Column(name = "CREATED_BY", updatable=false)
    private String createdBy;
    @Column(name = "CREATED_DATE", updatable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Size(max = 100)
    @Column(name = "MODIFIED_BY")
    private String modifiedBy;
    @Column(name = "MODIFIED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;
    @Column(name = "FK_STATUS_ID")
    private Integer fkStatusId;
    @Size(max = 500)
    @Column(name = "RULE_TEXT")
    private String ruleText;
    @Size(max = 500)
    @Column(name = "PRINT_DESCRIPTION")
    private String printDescription;
    @Size(max = 500)
    @Column(name = "CUSTOM_ATTRIBUTE_1")
    private String customAttribute1;
    @Size(max = 500)
    @Column(name = "CUSTOM_ATTRIBUTE_2")
    private String customAttribute2;
    @Size(max = 500)
    @Column(name = "CUSTOM_ATTRIBUTE_3")
    private String customAttribute3;
    @Size(max = 500)
    @Column(name = "CUSTOM_ATTRIBUTE_4")
    private String customAttribute4;
    @Size(max = 500)
    @Column(name = "CUSTOM_ATTRIBUTE_5")
    private String customAttribute5;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "CUSTOM_ATTRIBUTE_6")
    private Double customAttribute6;
    @Column(name = "CUSTOM_ATTRIBUTE_7")
    private Double customAttribute7;
    @Column(name = "CUSTOM_ATTRIBUTE_8")
    private Double customAttribute8;
    @JoinColumn(name = "FK_SHIPMENT_RULE_ID", referencedColumnName = "PK_SHIPMENT_RULE_ID")
    @ManyToOne
    private MasterShipmentRule fkShipmentRuleId;

    public MasterShipmentWeekTimeframe() {
    }

    public MasterShipmentWeekTimeframe(Integer pkShipmentWeekTimeframeId) {
        this.pkShipmentWeekTimeframeId = pkShipmentWeekTimeframeId;
    }

    public Integer getPkShipmentWeekTimeframeId() {
        return pkShipmentWeekTimeframeId;
    }
    
    public MasterShipmentWeekTimeframe(Integer pkShipmentWeekTimeframeId, String shipmentTimeframeName, String shipmentTimeframeCode) {
		this.pkShipmentWeekTimeframeId = pkShipmentWeekTimeframeId;
		this.shipmentTimeframeName = shipmentTimeframeName;
		this.shipmentTimeframeCode = shipmentTimeframeCode;
	}

    public void setPkShipmentWeekTimeframeId(Integer pkShipmentWeekTimeframeId) {
        this.pkShipmentWeekTimeframeId = pkShipmentWeekTimeframeId;
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

    public String getRuleText() {
        return ruleText;
    }

    public void setRuleText(String ruleText) {
        this.ruleText = ruleText;
    }

    public String getPrintDescription() {
        return printDescription;
    }

    public void setPrintDescription(String printDescription) {
        this.printDescription = printDescription;
    }

    public String getCustomAttribute1() {
        return customAttribute1;
    }

    public void setCustomAttribute1(String customAttribute1) {
        this.customAttribute1 = customAttribute1;
    }

    public String getCustomAttribute2() {
        return customAttribute2;
    }

    public void setCustomAttribute2(String customAttribute2) {
        this.customAttribute2 = customAttribute2;
    }

    public String getCustomAttribute3() {
        return customAttribute3;
    }

    public void setCustomAttribute3(String customAttribute3) {
        this.customAttribute3 = customAttribute3;
    }

    public String getCustomAttribute4() {
        return customAttribute4;
    }

    public void setCustomAttribute4(String customAttribute4) {
        this.customAttribute4 = customAttribute4;
    }

    public String getCustomAttribute5() {
        return customAttribute5;
    }

    public void setCustomAttribute5(String customAttribute5) {
        this.customAttribute5 = customAttribute5;
    }

    public Double getCustomAttribute6() {
        return customAttribute6;
    }

    public void setCustomAttribute6(Double customAttribute6) {
        this.customAttribute6 = customAttribute6;
    }

    public Double getCustomAttribute7() {
        return customAttribute7;
    }

    public void setCustomAttribute7(Double customAttribute7) {
        this.customAttribute7 = customAttribute7;
    }

    public Double getCustomAttribute8() {
        return customAttribute8;
    }

    public void setCustomAttribute8(Double customAttribute8) {
        this.customAttribute8 = customAttribute8;
    }

    public MasterShipmentRule getFkShipmentRuleId() {
        return fkShipmentRuleId;
    }

    public void setFkShipmentRuleId(MasterShipmentRule fkShipmentRuleId) {
        this.fkShipmentRuleId = fkShipmentRuleId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkShipmentWeekTimeframeId != null ? pkShipmentWeekTimeframeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterShipmentWeekTimeframe)) {
            return false;
        }
        MasterShipmentWeekTimeframe other = (MasterShipmentWeekTimeframe) object;
        if ((this.pkShipmentWeekTimeframeId == null && other.pkShipmentWeekTimeframeId != null) || (this.pkShipmentWeekTimeframeId != null && !this.pkShipmentWeekTimeframeId.equals(other.pkShipmentWeekTimeframeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.reference.domain.MasterShipmentWeekTimeframe[ pkShipmentWeekTimeframeId=" + pkShipmentWeekTimeframeId + " ]";
    }
    
    
    public ShipmentWeekDto convertToShipmentWeekDto(Map<Integer, String> statusMap) 
	{
		ShipmentWeekDto dto;
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		dto = (modelMapper.map(this, ShipmentWeekDto.class));
		dto.setShipmentRuleId(this.getFkShipmentRuleId().getPkShipmentRuleId());
		dto.setShipmentRuleName(this.getFkShipmentRuleId().getShipmentRuleName());
		dto.setShipmentWeekTimeframeId(this.getPkShipmentWeekTimeframeId());
		dto.setStatusName(statusMap.get(getFkStatusId()));
		return dto;
	}
}

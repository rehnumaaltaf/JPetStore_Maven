package com.olam.score.cost.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "MASTER_FREIGHT_COST_MATRIX_DTL", schema = "cost")
@XmlRootElement
public class MasterFreightCostMatrixDtl implements Serializable {
	@Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="FREIGHT_COST_MATRIX_DTL_ID_SEQ")
   	@SequenceGenerator(name="FREIGHT_COST_MATRIX_DTL_ID_SEQ", sequenceName="FREIGHT_COST_MATRIX_DTL_ID_SEQ",initialValue=1,allocationSize=1)
    @Column(name = "PK_FREIGHT_COST_MATRIX_DTL_ID")
    private Integer pkFreightCostMatrixDtlId;
	@JoinColumn(name = "FK_FREIGHT_COST_MATRIX_ID")
	@ManyToOne
	private	MasterFreightCostMatrix fkFreightCostMatrixId;
	@Column(name = "FK_SOURCE_LOCATION_ID")
    private Integer fkSourceLocationId;
    @Column(name = "FK_DESTINATION_LOCATION_ID")
    private Integer fkDestinationLocationId;
    
    @Column(name = "FK_SOURCE_GEO_ID")
    private Integer fkSourceGeoId;
    @Column(name = "FK_DESTINATION_GEO_ID")
    private Integer fkDestinationGeoId;
    
    @Column(name="RATE")
	private BigDecimal rate;
	@Column(name="FK_RATE_BASIS_REF_ID")
	private int fkRateBasisRefId;
	@Column(name="FK_RATE_UOM_ID")
	private int fkRateUomId;
	@Column(name="FK_RATE_PRIMARY_PACKING_ID")
	private int fkRatePrimaryPackingId;
	@Column(name="FK_RATE_SECONDARY_PACKING_ID")
	private int fkRateSecondaryPacking;
	@Column(name="TRANSIT_DAYS")
	private int transitDays;
	@Column(name="REMARKS")
	private String remarks;
	@Size(max = 100)
    @Column(name = "CREATED_BY")
    private String createdBy;
    @Column(name = "CREATED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Size(max = 100)
    @Column(name = "MODIFIED_BY")
    private String modifiedBy;
    @Column(name = "MODIFIED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;
    @Size(max = 500)
    @Column(name = "CUSTOM_ATTRIBUTE_1", length = 500)
    private String customAttribute1;
    @Size(max = 500)
    @Column(name = "CUSTOM_ATTRIBUTE_2", length = 500)
    private String customAttribute2;
    @Size(max = 500)
    @Column(name = "CUSTOM_ATTRIBUTE_3", length = 500)
    private String customAttribute3;
    @Size(max = 500)
    @Column(name = "CUSTOM_ATTRIBUTE_4", length = 500)
    private String customAttribute4; 
    @Size(max = 500)
    @Column(name = "CUSTOM_ATTRIBUTE_5", length = 500)
    private String customAttribute5;
    @Column(name = "CUSTOM_ATTRIBUTE_6", precision = 53)
    private Double customAttribute6;
    @Column(name = "CUSTOM_ATTRIBUTE_7", precision = 53)
    private Double customAttribute7;
    @Column(name = "CUSTOM_ATTRIBUTE_8", precision = 53)
    private Double customAttribute8;
    @Column(name="FREE_DAYS_AT_LOAD_PORT")
    private Integer freeDaysAtLoadPort;
    @Column(name="FREE_DAYS_AT_DESTINATION_PORT")
    private Integer freeDaysAtDestinationPort;
    
    @Column(name="FK_CURRENCY_ID")
	private int fkCurrencyId;
    
	public int getFkCurrencyId() {
		return fkCurrencyId;
	}
	public void setFkCurrencyId(int fkCurrencyId) {
		this.fkCurrencyId = fkCurrencyId;
	}
	public Integer getPkFreightCostMatrixDtlId() {
		return pkFreightCostMatrixDtlId;
	}
	public void setPkFreightCostMatrixDtlId(Integer pkFreightCostMatrixDtlId) {
		this.pkFreightCostMatrixDtlId = pkFreightCostMatrixDtlId;
	}
	public MasterFreightCostMatrix getFkFreightCostMatrixId() {
		return fkFreightCostMatrixId;
	}
	public void setFkFreightCostMatrixId(MasterFreightCostMatrix fkFreightCostMatrixId) {
		this.fkFreightCostMatrixId = fkFreightCostMatrixId;
	}
	public Integer getFkSourceLocationId() {
		return fkSourceLocationId;
	}
	public void setFkSourceLocationId(Integer fkSourceLocationId) {
		this.fkSourceLocationId = fkSourceLocationId;
	}
	public Integer getFkDestinationLocationId() {
		return fkDestinationLocationId;
	}
	public void setFkDestinationLocationId(Integer fkDestinationLocationId) {
		this.fkDestinationLocationId = fkDestinationLocationId;
	}
	public Integer getFkSourceGeoId() {
		return fkSourceGeoId;
	}
	public void setFkSourceGeoId(Integer fkSourceGeoId) {
		this.fkSourceGeoId = fkSourceGeoId;
	}
	public Integer getFkDestinationGeoId() {
		return fkDestinationGeoId;
	}
	public void setFkDestinationGeoId(Integer fkDestinationGeoId) {
		this.fkDestinationGeoId = fkDestinationGeoId;
	}
	public BigDecimal getRate() {
		return rate;
	}
	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}
	public int getFkRateBasisRefId() {
		return fkRateBasisRefId;
	}
	public void setFkRateBasisRefId(int fkRateBasisRefId) {
		this.fkRateBasisRefId = fkRateBasisRefId;
	}
	public int getFkRateUomId() {
		return fkRateUomId;
	}
	public void setFkRateUomId(int fkRateUomId) {
		this.fkRateUomId = fkRateUomId;
	}
	public int getFkRatePrimaryPackingId() {
		return fkRatePrimaryPackingId;
	}
	public void setFkRatePrimaryPackingId(int fkRatePrimaryPackingId) {
		this.fkRatePrimaryPackingId = fkRatePrimaryPackingId;
	}
	public int getFkRateSecondaryPacking() {
		return fkRateSecondaryPacking;
	}
	public void setFkRateSecondaryPacking(int fkRateSecondaryPacking) {
		this.fkRateSecondaryPacking = fkRateSecondaryPacking;
	}
	public int getTransitDays() {
		return transitDays;
	}
	public void setTransitDays(int transitDays) {
		this.transitDays = transitDays;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
	public Integer getFreeDaysAtLoadPort() {
		return freeDaysAtLoadPort;
	}
	public void setFreeDaysAtLoadPort(Integer freeDaysAtLoadPort) {
		this.freeDaysAtLoadPort = freeDaysAtLoadPort;
	}
	public Integer getFreeDaysAtDestinationPort() {
		return freeDaysAtDestinationPort;
	}
	public void setFreeDaysAtDestinationPort(Integer freeDaysAtDestinationPort) {
		this.freeDaysAtDestinationPort = freeDaysAtDestinationPort;
	}
    
}

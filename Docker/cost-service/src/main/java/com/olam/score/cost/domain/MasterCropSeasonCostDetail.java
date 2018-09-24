package com.olam.score.cost.domain;

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
@Table(name = "MASTER_CROP_SEASON_COST_DETAIL")
@XmlRootElement
public class MasterCropSeasonCostDetail {
	
	@Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CROP_SEASON_COST_DETAIL_ID_SEQ")
   	@SequenceGenerator(name="CROP_SEASON_COST_DETAIL_ID_SEQ", sequenceName="cost.CROP_SEASON_COST_DETAIL_ID_SEQ",initialValue=1,allocationSize=1)
    @Column(name = "PK_CROP_SEASON_COST_DETAIL_ID")
    private Integer pkCropSeasonCostDetailId;
	
	@JoinColumn(name="FK_CROP_SEASON_COST_ID")
	@ManyToOne
	private MasterCropSeasonCost fkCropSeasonCostId;
	
	
	@Column(name="PREMIUM_DISCOUNT_PERCENTAGE")
	private BigDecimal premiumDiscountPercentage;
	
	
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
    @Column(name = "FK_STATUS_ID")
    private Integer fkStatusId;
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
    @JoinColumn(name = "FK_CROP_SEASON_OFFSET_REF_ID")
	@ManyToOne
	private	MasterCropSeasonOffsetRef fkCropSeasonOffsetRefId;
	public Integer getPkCropSeasonCostDetailId() {
		return pkCropSeasonCostDetailId;
	}
	public void setPkCropSeasonCostDetailId(Integer pkCropSeasonCostDetailId) {
		this.pkCropSeasonCostDetailId = pkCropSeasonCostDetailId;
	}
	public MasterCropSeasonCost getFkCropSeasonCostId() {
		return fkCropSeasonCostId;
	}
	public void setFkCropSeasonCostId(MasterCropSeasonCost fkCropSeasonCostId) {
		this.fkCropSeasonCostId = fkCropSeasonCostId;
	}
	public BigDecimal getPremiumDiscountPercentage() {
		return premiumDiscountPercentage;
	}
	public void setPremiumDiscountPercentage(BigDecimal premiumDiscountPercentage) {
		this.premiumDiscountPercentage = premiumDiscountPercentage;
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
	public MasterCropSeasonOffsetRef getFkCropSeasonOffsetRefId() {
		return fkCropSeasonOffsetRefId;
	}
	public void setFkCropSeasonOffsetRefId(MasterCropSeasonOffsetRef fkCropSeasonOffsetRefId) {
		this.fkCropSeasonOffsetRefId = fkCropSeasonOffsetRefId;
	}
    
}

package com.olam.score.cost.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
*
* @author Prabhakar
*/
@Entity
@Table(name = "MASTER_CROP_SEASON_COST")
@XmlRootElement


public class MasterCropSeasonCost {
	
	@Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_CROP_SEASON_COST_ID")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CROP_SEASON_COST_ID_SEQ")
   	@SequenceGenerator(name="CROP_SEASON_COST_ID_SEQ", sequenceName="CROP_SEASON_COST_ID_SEQ",allocationSize=1)
    private Integer pkCropSeasonCostId;
	
	   @Column(name="FK_PROD_ID")
	    private Integer fkProdId;
	   @JoinColumn(name="FK_COST_ID")
	   @ManyToOne
	   private MasterCost fkcostId;
	   
	   @Column(name=" FK_ORIGIN_ID") 
	    private Integer fkOriginId;
	   
	   
	   @Column(name="FK_BASE_CROP_SEASON_ID") 
	    private Integer fkBaseCropSeasonId;
	   @Column(name = "FK_STATUS_ID")
	    private Integer fkStatusId;
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
	    @Column(name="IS_APPLICABLE_TO_ALL_PARTY_IND")
	    private int isApplicableToAllPartyInd;
	    @Column(name="IS_APPLICABLE_TO_ALL_UNIT_IND")
	    private int isApplicableToAllUnitInd;
	    public int getIsApplicableToAllUnitInd() {
			return isApplicableToAllUnitInd;
		}
		public void setIsApplicableToAllUnitInd(int isApplicableToAllUnitInd) {
			this.isApplicableToAllUnitInd = isApplicableToAllUnitInd;
		}
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
	    @OneToMany(cascade = CascadeType.ALL,mappedBy = "fkCropSeasonCostId")
	    private List<MasterCropSeasonCostDetail> masterCropSeasonCostDetailList;
	    @OneToMany(cascade = CascadeType.ALL,mappedBy = "fkCropSeasonCostId")
	    private List<MasterCropSeasonCostParty> masterCropSeasonCostPartyList;
	    @OneToMany(cascade = CascadeType.ALL,mappedBy = "fkCropSeasonCostId")
	    private List<MasterCropSeasonCostUnit> masterCropSeasonCostUnitList;
		public Integer getPkCropSeasonCostId() {
			return pkCropSeasonCostId;
		}
		public void setPkCropSeasonCostId(Integer pkCropSeasonCostId) {
			this.pkCropSeasonCostId = pkCropSeasonCostId;
		}
		public Integer getFkProdId() {
			return fkProdId;
		}
		public void setFkProdId(Integer fkProdId) {
			this.fkProdId = fkProdId;
		}
		public MasterCost getFkcostId() {
			return fkcostId;
		}
		public void setFkcostId(MasterCost fkcostId) {
			this.fkcostId = fkcostId;
		}
		public Integer getFkOriginId() {
			return fkOriginId;
		}
		public void setFkOriginId(Integer fkOriginId) {
			this.fkOriginId = fkOriginId;
		}
		public Integer getFkBaseCropSeasonId() {
			return fkBaseCropSeasonId;
		}
		public void setFkBaseCropSeasonId(Integer fkBaseCropSeasonId) {
			this.fkBaseCropSeasonId = fkBaseCropSeasonId;
		}
		public Integer getFkStatusId() {
			return fkStatusId;
		}
		public void setFkStatusId(Integer fkStatusId) {
			this.fkStatusId = fkStatusId;
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
		public int getIsApplicableToAllPartyInd() {
			return isApplicableToAllPartyInd;
		}
		public void setIsApplicableToAllPartyInd(int isApplicableToAllPartyInd) {
			this.isApplicableToAllPartyInd = isApplicableToAllPartyInd;
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
		public List<MasterCropSeasonCostDetail> getMasterCropSeasonCostDetailList() {
			return masterCropSeasonCostDetailList;
		}
		public void setMasterCropSeasonCostDetailList(List<MasterCropSeasonCostDetail> masterCropSeasonCostDetailList) {
			this.masterCropSeasonCostDetailList = masterCropSeasonCostDetailList;
		}
		public List<MasterCropSeasonCostParty> getMasterCropSeasonCostPartyList() {
			return masterCropSeasonCostPartyList;
		}
		public void setMasterCropSeasonCostPartyList(List<MasterCropSeasonCostParty> masterCropSeasonCostPartyList) {
			this.masterCropSeasonCostPartyList = masterCropSeasonCostPartyList;
		}
		public List<MasterCropSeasonCostUnit> getMasterCropSeasonCostUnitList() {
			return masterCropSeasonCostUnitList;
		}
		public void setMasterCropSeasonCostUnitList(List<MasterCropSeasonCostUnit> masterCropSeasonCostUnitList) {
			this.masterCropSeasonCostUnitList = masterCropSeasonCostUnitList;
		}
	    
	    
}

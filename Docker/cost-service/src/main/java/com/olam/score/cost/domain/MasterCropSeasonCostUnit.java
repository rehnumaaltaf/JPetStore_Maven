package com.olam.score.cost.domain;

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
@Table(name = "MASTER_CROP_SEASON_COST_UNIT")
@XmlRootElement
public class MasterCropSeasonCostUnit {
	@Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CROP_SEASON_COST_UNIT_ID_SEQ")
   	@SequenceGenerator(name="CROP_SEASON_COST_UNIT_ID_SEQ", sequenceName="cost.CROP_SEASON_COST_UNIT_ID_SEQ",initialValue=1,allocationSize=1)
    @Column(name = "PK_CROP_SEASON_COST_UNIT_ID")
    private Integer pkCropSeasonCostUnitId;
	@JoinColumn(name="FK_CROP_SEASON_COST_ID")
	@ManyToOne
	private MasterCropSeasonCost fkCropSeasonCostId;
	
	@Column(name="FK_PARTY_ID")
	private int fkPartyId;
	
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
	public Integer getPkCropSeasonCostUnitId() {
		return pkCropSeasonCostUnitId;
	}
	public void setPkCropSeasonCostUnitId(Integer pkCropSeasonCostUnitId) {
		this.pkCropSeasonCostUnitId = pkCropSeasonCostUnitId;
	}
	public MasterCropSeasonCost getFkCropSeasonCostId() {
		return fkCropSeasonCostId;
	}
	public void setFkCropSeasonCostId(MasterCropSeasonCost fkCropSeasonCostId) {
		this.fkCropSeasonCostId = fkCropSeasonCostId;
	}
	public int getFkPartyId() {
		return fkPartyId;
	}
	public void setFkPartyId(int fkPartyId) {
		this.fkPartyId = fkPartyId;
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
    
}

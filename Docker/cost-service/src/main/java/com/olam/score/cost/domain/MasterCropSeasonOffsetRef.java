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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "MASTER_CROP_SEASON_OFFSET_REF")
@XmlRootElement
public class MasterCropSeasonOffsetRef {
	@Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CROP_SEASON_OFFSET_REF_ID_SEQ")
   	@SequenceGenerator(name="CROP_SEASON_OFFSET_REF_ID_SEQ", sequenceName="cost.CROP_SEASON_OFFSET_REF_ID_SEQ",initialValue=1,allocationSize=1)
    @Column(name = "PK_CROP_SEASON_OFFSET_REF_ID")
    private Integer pkCropSeasonOffsetRefId;
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
    @Column(name="CROP_SEASON_OFFSET_CODE")
    private String cropSeasonOffsetCode;
    @Column(name="CROP_SEASON_OFFSET_NAME")
    private String cropSeasonOffsetName;
	public Integer getPkCropSeasonOffsetRefId() {
		return pkCropSeasonOffsetRefId;
	}
	public void setPkCropSeasonOffsetRefId(Integer pkCropSeasonOffsetRefId) {
		this.pkCropSeasonOffsetRefId = pkCropSeasonOffsetRefId;
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
	public String getCropSeasonOffsetCode() {
		return cropSeasonOffsetCode;
	}
	public void setCropSeasonOffsetCode(String cropSeasonOffsetCode) {
		this.cropSeasonOffsetCode = cropSeasonOffsetCode;
	}
	public String getCropSeasonOffsetName() {
		return cropSeasonOffsetName;
	}
	public void setCropSeasonOffsetName(String cropSeasonOffsetName) {
		this.cropSeasonOffsetName = cropSeasonOffsetName;
	}
    
}

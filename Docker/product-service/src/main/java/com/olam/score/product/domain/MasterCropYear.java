/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olam.score.product.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ramachandran_n02
 */
@Entity
@Table(name = "MASTER_CROP_YEAR",  schema = "product")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "MasterCropYear.findAll", query = "SELECT m FROM MasterCropYear m") })
public class MasterCropYear implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MASTER_CROPYEARIDSEQ_GENERATOR")
	@SequenceGenerator(name = "MASTER_CROPYEARIDSEQ_GENERATOR", sequenceName = "CROP_YEAR_ID_SEQ", allocationSize = 1, schema = "product")
	@Basic(optional = false)
	@NotNull
	@Column(name = "PK_CROP_YEAR_ID")
	private Integer pkCropYearId;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 200)
	@Column(name = "CROP_YEAR_NAME")
	private String cropYearName;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 20)
	@Column(name = "CROP_YEAR_CODE")
	private String cropYearCode;
	@Size(max = 100)
	@Column(name = "CREATED_BY")
	private String createdBy;
	@Column(name = "CREATED_DATE")
	private Timestamp createdDate;
	@Size(max = 100)
	@Column(name = "MODIFIED_BY")
	private String modifiedBy;
	@Column(name = "MODIFIED_DATE")
	private Timestamp modifiedDate;
	@Column(name = "FK_STATUS_ID")
	private Integer fkStatusId;
	@Column(name = "CROP_YEAR_DESC")
	private String cropYearDesc;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "masterCropYear")
    private Collection<MasterCropYearProductAsso> cropYearProductAssos;

	public String getCropYearDesc() {
		return cropYearDesc;
	}

	public void setCropYearDesc(String cropYearDesc) {
		this.cropYearDesc = cropYearDesc;
	}

	public MasterCropYear() {
	}

	public MasterCropYear(Integer pkCropYearId, String cropYearName, String cropYearCode) {
		this.pkCropYearId = pkCropYearId;
		this.cropYearName = cropYearName;
		this.cropYearCode = cropYearCode;
	}

	public Integer getPkCropYearId() {
		return pkCropYearId;
	}

	public void setPkCropYearId(Integer pkCropYearId) {
		this.pkCropYearId = pkCropYearId;
	}

	public String getCropYearName() {
		return cropYearName;
	}

	public void setCropYearName(String cropYearName) {
		this.cropYearName = cropYearName;
	}

	public String getCropYearCode() {
		return cropYearCode;
	}

	public void setCropYearCode(String cropYearCode) {
		this.cropYearCode = cropYearCode;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Timestamp getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Integer getFkStatusId() {
		return fkStatusId;
	}

	public void setFkStatusId(Integer fkStatusId) {
		this.fkStatusId = fkStatusId;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (pkCropYearId != null ? pkCropYearId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof MasterCropYear)) {
			return false;
		}
		MasterCropYear other = (MasterCropYear) object;
		if ((this.pkCropYearId == null && other.pkCropYearId != null)
				|| (this.pkCropYearId != null && !this.pkCropYearId.equals(other.pkCropYearId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.olam.score.product.domain.MasterCropYear[ pkCropYearId=" + pkCropYearId + " ]";
	}

}

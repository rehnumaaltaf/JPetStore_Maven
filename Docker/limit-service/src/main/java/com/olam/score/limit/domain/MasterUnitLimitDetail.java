/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olam.score.limit.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ramachandran_n02
 */
@Entity
@Table(name = "MASTER_UNIT_LIMIT_DETAIL",  schema = "limit")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterUnitLimitDetail.findAll", query = "SELECT m FROM MasterUnitLimitDetail m")})
public class MasterUnitLimitDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_UNIT_LIMIT_DETAIL_ID")
    private Integer pkUnitLimitDetailId;
    @Column(name = "FK_UNIT_ID")
    private Integer fkUnitId;
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
    @Column(name = "UNIT_BASIS_LIMIT")
    private Integer unitBasisLimit;
    @Column(name = "UNIT_STRUCTURE_LIMIT")
    private Integer unitStructureLimit;
    @Column(name = "UNIT_FIXED_FORWARD_TENOR")
    private Integer unitFixedForwardTenor;
    @Column(name = "UNIT_VAR_LIMIT")
    private Integer unitVarLimit;
    @Column(name = "UNIT_M2M_LIMIT")
    private Integer unitM2mLimit;
    @Column(name = "UNIT_LIMIT_VALID_FROM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date unitLimitValidFrom;
    @Column(name = "UNIT_LIMIT_VALID_TO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date unitLimitValidTo;
    @Column(name = "FK_STATUS_ID")
    private Integer fkStatusId;
    @JoinColumn(name = "FK_UNIT_LIMIT_ID", referencedColumnName = "PK_UNIT_LIMIT_ID")
    @ManyToOne
    private MasterUnitLimit fkUnitLimitId;

    public MasterUnitLimitDetail() {
    }

    public MasterUnitLimitDetail(Integer pkUnitLimitDetailId) {
        this.pkUnitLimitDetailId = pkUnitLimitDetailId;
    }

    public Integer getPkUnitLimitDetailId() {
        return pkUnitLimitDetailId;
    }

    public void setPkUnitLimitDetailId(Integer pkUnitLimitDetailId) {
        this.pkUnitLimitDetailId = pkUnitLimitDetailId;
    }

    public Integer getFkUnitId() {
        return fkUnitId;
    }

    public void setFkUnitId(Integer fkUnitId) {
        this.fkUnitId = fkUnitId;
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

    public Integer getUnitBasisLimit() {
        return unitBasisLimit;
    }

    public void setUnitBasisLimit(Integer unitBasisLimit) {
        this.unitBasisLimit = unitBasisLimit;
    }

    public Integer getUnitStructureLimit() {
        return unitStructureLimit;
    }

    public void setUnitStructureLimit(Integer unitStructureLimit) {
        this.unitStructureLimit = unitStructureLimit;
    }

    public Integer getUnitFixedForwardTenor() {
        return unitFixedForwardTenor;
    }

    public void setUnitFixedForwardTenor(Integer unitFixedForwardTenor) {
        this.unitFixedForwardTenor = unitFixedForwardTenor;
    }

    public Integer getUnitVarLimit() {
        return unitVarLimit;
    }

    public void setUnitVarLimit(Integer unitVarLimit) {
        this.unitVarLimit = unitVarLimit;
    }

    public Integer getUnitM2mLimit() {
        return unitM2mLimit;
    }

    public void setUnitM2mLimit(Integer unitM2mLimit) {
        this.unitM2mLimit = unitM2mLimit;
    }

    public Date getUnitLimitValidFrom() {
        return unitLimitValidFrom;
    }

    public void setUnitLimitValidFrom(Date unitLimitValidFrom) {
        this.unitLimitValidFrom = unitLimitValidFrom;
    }

    public Date getUnitLimitValidTo() {
        return unitLimitValidTo;
    }

    public void setUnitLimitValidTo(Date unitLimitValidTo) {
        this.unitLimitValidTo = unitLimitValidTo;
    }

    public Integer getFkStatusId() {
        return fkStatusId;
    }

    public void setFkStatusId(Integer fkStatusId) {
        this.fkStatusId = fkStatusId;
    }

    public MasterUnitLimit getFkUnitLimitId() {
        return fkUnitLimitId;
    }

    public void setFkUnitLimitId(MasterUnitLimit fkUnitLimitId) {
        this.fkUnitLimitId = fkUnitLimitId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkUnitLimitDetailId != null ? pkUnitLimitDetailId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterUnitLimitDetail)) {
            return false;
        }
        MasterUnitLimitDetail other = (MasterUnitLimitDetail) object;
        if ((this.pkUnitLimitDetailId == null && other.pkUnitLimitDetailId != null) || (this.pkUnitLimitDetailId != null && !this.pkUnitLimitDetailId.equals(other.pkUnitLimitDetailId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.limit.domain.MasterUnitLimitDetail[ pkUnitLimitDetailId=" + pkUnitLimitDetailId + " ]";
    }
    
}

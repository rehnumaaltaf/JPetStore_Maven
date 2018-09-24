/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olam.score.terms.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ramachandran_N02
 */
@Entity
@Table(name = "MASTER_TOLERANCE_VALUE_UNIT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterToleranceValueUnit.findAll", query = "SELECT m FROM MasterToleranceValueUnit m")})
public class MasterToleranceValueUnit implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_TOLERANCE_VALUE_UNIT_ID")
    private Integer pkToleranceValueUnitId;
    @Size(max = 200)
    @Column(name = "TOLERANCE_VALUE_UNIT_NAME")
    private String toleranceValueUnitName;
    @Size(max = 20)
    @Column(name = "TOLERANCE_VALUE_UNIT_CODE")
    private String toleranceValueUnitCode;
    @Size(max = 1000)
    @Column(name = "TOLERANCE_VALUE_UNIT_DESC")
    private String toleranceValueUnitDesc;
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
    @OneToMany(mappedBy = "fkToleranceValueUnitId")
    private Collection<MasterWeightTerms> masterWeightTermsCollection;

    public MasterToleranceValueUnit() {
    }

    public MasterToleranceValueUnit(Integer pkToleranceValueUnitId) {
        this.pkToleranceValueUnitId = pkToleranceValueUnitId;
    }

    public Integer getPkToleranceValueUnitId() {
        return pkToleranceValueUnitId;
    }

    public void setPkToleranceValueUnitId(Integer pkToleranceValueUnitId) {
        this.pkToleranceValueUnitId = pkToleranceValueUnitId;
    }

    public String getToleranceValueUnitName() {
        return toleranceValueUnitName;
    }

    public void setToleranceValueUnitName(String toleranceValueUnitName) {
        this.toleranceValueUnitName = toleranceValueUnitName;
    }

    public String getToleranceValueUnitCode() {
        return toleranceValueUnitCode;
    }

    public void setToleranceValueUnitCode(String toleranceValueUnitCode) {
        this.toleranceValueUnitCode = toleranceValueUnitCode;
    }

    public String getToleranceValueUnitDesc() {
        return toleranceValueUnitDesc;
    }

    public void setToleranceValueUnitDesc(String toleranceValueUnitDesc) {
        this.toleranceValueUnitDesc = toleranceValueUnitDesc;
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

    @XmlTransient
    public Collection<MasterWeightTerms> getMasterWeightTermsCollection() {
        return masterWeightTermsCollection;
    }

    public void setMasterWeightTermsCollection(Collection<MasterWeightTerms> masterWeightTermsCollection) {
        this.masterWeightTermsCollection = masterWeightTermsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkToleranceValueUnitId != null ? pkToleranceValueUnitId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterToleranceValueUnit)) {
            return false;
        }
        MasterToleranceValueUnit other = (MasterToleranceValueUnit) object;
        if ((this.pkToleranceValueUnitId == null && other.pkToleranceValueUnitId != null) || (this.pkToleranceValueUnitId != null && !this.pkToleranceValueUnitId.equals(other.pkToleranceValueUnitId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.terms.domain.MasterToleranceValueUnit[ pkToleranceValueUnitId=" + pkToleranceValueUnitId + " ]";
    }
    
}

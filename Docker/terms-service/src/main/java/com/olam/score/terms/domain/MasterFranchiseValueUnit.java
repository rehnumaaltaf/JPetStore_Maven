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
@Table(name = "MASTER_FRANCHISE_VALUE_UNIT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterFranchiseValueUnit.findAll", query = "SELECT m FROM MasterFranchiseValueUnit m")})
public class MasterFranchiseValueUnit implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_FRANCHISE_VALUE_UNIT_ID")
    private Integer pkFranchiseValueUnitId;
    @Size(max = 200)
    @Column(name = "FRANCHISE_VALUE_UNIT_NAME")
    private String franchiseValueUnitName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "FRANCHISE_VALUE_UNIT_CODE")
    private String franchiseValueUnitCode;
    @Size(max = 1000)
    @Column(name = "FRANCHISE_VALUE_UNIT_DESC")
    private String franchiseValueUnitDesc;
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
    @OneToMany(mappedBy = "fkFranchiseValueUnitId")
    private Collection<MasterWeightTerms> masterWeightTermsCollection;

    public MasterFranchiseValueUnit() {
    }

    public MasterFranchiseValueUnit(Integer pkFranchiseValueUnitId) {
        this.pkFranchiseValueUnitId = pkFranchiseValueUnitId;
    }

    public MasterFranchiseValueUnit(Integer pkFranchiseValueUnitId, String franchiseValueUnitCode) {
        this.pkFranchiseValueUnitId = pkFranchiseValueUnitId;
        this.franchiseValueUnitCode = franchiseValueUnitCode;
    }

    public Integer getPkFranchiseValueUnitId() {
        return pkFranchiseValueUnitId;
    }

    public void setPkFranchiseValueUnitId(Integer pkFranchiseValueUnitId) {
        this.pkFranchiseValueUnitId = pkFranchiseValueUnitId;
    }

    public String getFranchiseValueUnitName() {
        return franchiseValueUnitName;
    }

    public void setFranchiseValueUnitName(String franchiseValueUnitName) {
        this.franchiseValueUnitName = franchiseValueUnitName;
    }

    public String getFranchiseValueUnitCode() {
        return franchiseValueUnitCode;
    }

    public void setFranchiseValueUnitCode(String franchiseValueUnitCode) {
        this.franchiseValueUnitCode = franchiseValueUnitCode;
    }

    public String getFranchiseValueUnitDesc() {
        return franchiseValueUnitDesc;
    }

    public void setFranchiseValueUnitDesc(String franchiseValueUnitDesc) {
        this.franchiseValueUnitDesc = franchiseValueUnitDesc;
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
        hash += (pkFranchiseValueUnitId != null ? pkFranchiseValueUnitId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterFranchiseValueUnit)) {
            return false;
        }
        MasterFranchiseValueUnit other = (MasterFranchiseValueUnit) object;
        if ((this.pkFranchiseValueUnitId == null && other.pkFranchiseValueUnitId != null) || (this.pkFranchiseValueUnitId != null && !this.pkFranchiseValueUnitId.equals(other.pkFranchiseValueUnitId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.terms.domain.MasterFranchiseValueUnit[ pkFranchiseValueUnitId=" + pkFranchiseValueUnitId + " ]";
    }
    
}

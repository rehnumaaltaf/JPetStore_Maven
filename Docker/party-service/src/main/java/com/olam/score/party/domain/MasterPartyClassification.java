/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olam.score.party.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Ramachandran_N02
 */
@Entity
@Table(name = "MASTER_PARTY_CLASSIFICATION")
@NamedQueries({
    @NamedQuery(name = "MasterPartyClassification.findAll", query = "SELECT m FROM MasterPartyClassification m")})
public class MasterPartyClassification implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_PARTY_CLASS_ID")
    private Integer pkPartyClassId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "PARTY_CLASS_CODE")
    private String partyClassCode;
    @Size(max = 200)
    @Column(name = "PARTY_CLASS_NAME")
    private String partyClassName;
    @Size(max = 20)
    @Column(name = "PARTY_CLASS_TYPE")
    private String partyClassType;
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
    @JoinColumn(name = "FK_PARTY_TYPE_ID", referencedColumnName = "PK_PARTY_TYPE_ID")
    @ManyToOne
    private MasterPartyType fkPartyTypeId;
    @OneToMany(mappedBy = "fkPartyClassId")
    private Collection<MasterPartyPartyClass> masterPartyPartyClassCollection;

    public MasterPartyClassification() {
    }

    public MasterPartyClassification(Integer pkPartyClassId) {
        this.pkPartyClassId = pkPartyClassId;
    }

    public MasterPartyClassification(Integer pkPartyClassId, String partyClassCode) {
        this.pkPartyClassId = pkPartyClassId;
        this.partyClassCode = partyClassCode;
    }

    public Integer getPkPartyClassId() {
        return pkPartyClassId;
    }

    public void setPkPartyClassId(Integer pkPartyClassId) {
        this.pkPartyClassId = pkPartyClassId;
    }

    public String getPartyClassCode() {
        return partyClassCode;
    }

    public void setPartyClassCode(String partyClassCode) {
        this.partyClassCode = partyClassCode;
    }

    public String getPartyClassName() {
        return partyClassName;
    }

    public void setPartyClassName(String partyClassName) {
        this.partyClassName = partyClassName;
    }

    public String getPartyClassType() {
        return partyClassType;
    }

    public void setPartyClassType(String partyClassType) {
        this.partyClassType = partyClassType;
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

    public MasterPartyType getFkPartyTypeId() {
        return fkPartyTypeId;
    }

    public void setFkPartyTypeId(MasterPartyType fkPartyTypeId) {
        this.fkPartyTypeId = fkPartyTypeId;
    }

    public Collection<MasterPartyPartyClass> getMasterPartyPartyClassCollection() {
        return masterPartyPartyClassCollection;
    }

    public void setMasterPartyPartyClassCollection(Collection<MasterPartyPartyClass> masterPartyPartyClassCollection) {
        this.masterPartyPartyClassCollection = masterPartyPartyClassCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkPartyClassId != null ? pkPartyClassId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterPartyClassification)) {
            return false;
        }
        MasterPartyClassification other = (MasterPartyClassification) object;
        if ((this.pkPartyClassId == null && other.pkPartyClassId != null) || (this.pkPartyClassId != null && !this.pkPartyClassId.equals(other.pkPartyClassId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.party.domain.MasterPartyClassification[ pkPartyClassId=" + pkPartyClassId + " ]";
    }
    
}

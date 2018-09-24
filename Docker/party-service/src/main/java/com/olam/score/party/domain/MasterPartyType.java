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
@Table(name = "MASTER_PARTY_TYPE")
@NamedQueries({
    @NamedQuery(name = "MasterPartyType.findAll", query = "SELECT m FROM MasterPartyType m")})
public class MasterPartyType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_PARTY_TYPE_ID")
    private Integer pkPartyTypeId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "PARTY_TYPE_CODE")
    private String partyTypeCode;
    @Size(max = 200)
    @Column(name = "PARTY_TYPE_NAME")
    private String partyTypeName;
    @Size(max = 200)
    @Column(name = "PARTY_TYPE_FULLNAME")
    private String partyTypeFullname;
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
    @OneToMany(mappedBy = "fkPartyTypeId")
    private Collection<MasterPartyClassification> masterPartyClassificationCollection;
    @OneToMany(mappedBy = "fkPartyTypeId")
    private Collection<MasterPartyPartyType> masterPartyPartyTypeCollection;

    public MasterPartyType() {
    }

    public MasterPartyType(Integer pkPartyTypeId) {
        this.pkPartyTypeId = pkPartyTypeId;
    }

    public MasterPartyType(Integer pkPartyTypeId, String partyTypeCode) {
        this.pkPartyTypeId = pkPartyTypeId;
        this.partyTypeCode = partyTypeCode;
    }

    public Integer getPkPartyTypeId() {
        return pkPartyTypeId;
    }

    public void setPkPartyTypeId(Integer pkPartyTypeId) {
        this.pkPartyTypeId = pkPartyTypeId;
    }

    public String getPartyTypeCode() {
        return partyTypeCode;
    }

    public void setPartyTypeCode(String partyTypeCode) {
        this.partyTypeCode = partyTypeCode;
    }

    public String getPartyTypeName() {
        return partyTypeName;
    }

    public void setPartyTypeName(String partyTypeName) {
        this.partyTypeName = partyTypeName;
    }

    public String getPartyTypeFullname() {
        return partyTypeFullname;
    }

    public void setPartyTypeFullname(String partyTypeFullname) {
        this.partyTypeFullname = partyTypeFullname;
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

    public Collection<MasterPartyClassification> getMasterPartyClassificationCollection() {
        return masterPartyClassificationCollection;
    }

    public void setMasterPartyClassificationCollection(Collection<MasterPartyClassification> masterPartyClassificationCollection) {
        this.masterPartyClassificationCollection = masterPartyClassificationCollection;
    }

    public Collection<MasterPartyPartyType> getMasterPartyPartyTypeCollection() {
        return masterPartyPartyTypeCollection;
    }

    public void setMasterPartyPartyTypeCollection(Collection<MasterPartyPartyType> masterPartyPartyTypeCollection) {
        this.masterPartyPartyTypeCollection = masterPartyPartyTypeCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkPartyTypeId != null ? pkPartyTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterPartyType)) {
            return false;
        }
        MasterPartyType other = (MasterPartyType) object;
        if ((this.pkPartyTypeId == null && other.pkPartyTypeId != null) || (this.pkPartyTypeId != null && !this.pkPartyTypeId.equals(other.pkPartyTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.party.domain.MasterPartyType[ pkPartyTypeId=" + pkPartyTypeId + " ]";
    }
    
}

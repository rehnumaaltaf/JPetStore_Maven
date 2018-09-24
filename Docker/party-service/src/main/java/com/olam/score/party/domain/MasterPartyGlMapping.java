/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olam.score.party.domain;

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

/**
 *
 * @author Ramachandran_N02
 */
@Entity
@Table(name = "MASTER_PARTY_GL_MAPPING")
@NamedQueries({
    @NamedQuery(name = "MasterPartyGlMapping.findAll", query = "SELECT m FROM MasterPartyGlMapping m")})
public class MasterPartyGlMapping implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_PARTY_GL_MAPPING_ID")
    private Integer pkPartyGlMappingId;
    @Size(max = 200)
    @Column(name = "PARTY_GL_MAPPING_NAME")
    private String partyGlMappingName;
    @Size(max = 20)
    @Column(name = "PARTY_GL_MAPPING_CODE")
    private String partyGlMappingCode;
    @Size(max = 100)
    @Column(name = "CREATED_BY")
    private String createdBy;
    @Column(name = "CREATED_DATE",  updatable = false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Size(max = 100)
    @Column(name = "MODIFIED_BY")
    private String modifiedBy;
    @Column(name = "MODIFIED_DATE",  updatable = true, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;
    @Column(name = "FK_AR_GL_ID")
    private Integer fkArGlId;
    @Column(name = "FK_AP_GL_ID")
    private Integer fkApGlId;
    @Column(name = "FK_STATUS_ID")
    private Integer fkStatusId;
    @JoinColumn(name = "FK_PARTY_ID", referencedColumnName = "PK_PARTY_ID")
    @ManyToOne
    private MasterParty fkPartyId;

    public MasterPartyGlMapping() {
    }

    public MasterPartyGlMapping(Integer pkPartyGlMappingId) {
        this.pkPartyGlMappingId = pkPartyGlMappingId;
    }

    public Integer getPkPartyGlMappingId() {
        return pkPartyGlMappingId;
    }

    public void setPkPartyGlMappingId(Integer pkPartyGlMappingId) {
        this.pkPartyGlMappingId = pkPartyGlMappingId;
    }

    public String getPartyGlMappingName() {
        return partyGlMappingName;
    }

    public void setPartyGlMappingName(String partyGlMappingName) {
        this.partyGlMappingName = partyGlMappingName;
    }

    public String getPartyGlMappingCode() {
        return partyGlMappingCode;
    }

    public void setPartyGlMappingCode(String partyGlMappingCode) {
        this.partyGlMappingCode = partyGlMappingCode;
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

    public Integer getFkArGlId() {
        return fkArGlId;
    }

    public void setFkArGlId(Integer fkArGlId) {
        this.fkArGlId = fkArGlId;
    }

    public Integer getFkApGlId() {
        return fkApGlId;
    }

    public void setFkApGlId(Integer fkApGlId) {
        this.fkApGlId = fkApGlId;
    }

    public Integer getFkStatusId() {
        return fkStatusId;
    }

    public void setFkStatusId(Integer fkStatusId) {
        this.fkStatusId = fkStatusId;
    }

    public MasterParty getFkPartyId() {
        return fkPartyId;
    }

    public void setFkPartyId(MasterParty fkPartyId) {
        this.fkPartyId = fkPartyId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkPartyGlMappingId != null ? pkPartyGlMappingId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterPartyGlMapping)) {
            return false;
        }
        MasterPartyGlMapping other = (MasterPartyGlMapping) object;
        if ((this.pkPartyGlMappingId == null && other.pkPartyGlMappingId != null) || (this.pkPartyGlMappingId != null && !this.pkPartyGlMappingId.equals(other.pkPartyGlMappingId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.party.domain.MasterPartyGlMapping[ pkPartyGlMappingId=" + pkPartyGlMappingId + " ]";
    }
    
}

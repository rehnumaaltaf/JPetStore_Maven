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
@Table(name = "MASTER_UNIT_GL_MAPPING")
@NamedQueries({
    @NamedQuery(name = "MasterUnitGlMapping.findAll", query = "SELECT m FROM MasterUnitGlMapping m")})
public class MasterUnitGlMapping implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_UNIT_GL_MAPPING_ID")
    private Integer pkUnitGlMappingId;
    @Size(max = 200)
    @Column(name = "UNIT_GL_MAPPING_NAME")
    private String unitGlMappingName;
    @Size(max = 20)
    @Column(name = "UNIT_GL_MAPPING_CODE")
    private String unitGlMappingCode;
    @Size(max = 100)
    @Column(name = "CREATED_BY")
    private String createdBy;
    @Column(name = "CREATED_DATE", updatable = false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Size(max = 100)
    @Column(name = "MODIFIED_BY")
    private String modifiedBy;
    @Column(name = "MODIFIED_DATE",  updatable = true, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;
    @Column(name = "FK_STATUS_ID")
    private Integer fkStatusId;
    @Column(name = "FK_GL_ID")
    private Integer fkGlId;
    @JoinColumn(name = "FK_PARTY_ID", referencedColumnName = "PK_PARTY_ID")
    @ManyToOne
    private MasterParty fkPartyId;
    @JoinColumn(name = "FK_UNIT_ID", referencedColumnName = "PK_UNIT_ID")
    @ManyToOne
    private MasterUnit fkUnitId;

    public MasterUnitGlMapping() {
    }

    public MasterUnitGlMapping(Integer pkUnitGlMappingId) {
        this.pkUnitGlMappingId = pkUnitGlMappingId;
    }

    public Integer getPkUnitGlMappingId() {
        return pkUnitGlMappingId;
    }

    public void setPkUnitGlMappingId(Integer pkUnitGlMappingId) {
        this.pkUnitGlMappingId = pkUnitGlMappingId;
    }

    public String getUnitGlMappingName() {
        return unitGlMappingName;
    }

    public void setUnitGlMappingName(String unitGlMappingName) {
        this.unitGlMappingName = unitGlMappingName;
    }

    public String getUnitGlMappingCode() {
        return unitGlMappingCode;
    }

    public void setUnitGlMappingCode(String unitGlMappingCode) {
        this.unitGlMappingCode = unitGlMappingCode;
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

    public Integer getFkGlId() {
        return fkGlId;
    }

    public void setFkGlId(Integer fkGlId) {
        this.fkGlId = fkGlId;
    }

    public MasterParty getFkPartyId() {
        return fkPartyId;
    }

    public void setFkPartyId(MasterParty fkPartyId) {
        this.fkPartyId = fkPartyId;
    }

    public MasterUnit getFkUnitId() {
        return fkUnitId;
    }

    public void setFkUnitId(MasterUnit fkUnitId) {
        this.fkUnitId = fkUnitId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkUnitGlMappingId != null ? pkUnitGlMappingId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterUnitGlMapping)) {
            return false;
        }
        MasterUnitGlMapping other = (MasterUnitGlMapping) object;
        if ((this.pkUnitGlMappingId == null && other.pkUnitGlMappingId != null) || (this.pkUnitGlMappingId != null && !this.pkUnitGlMappingId.equals(other.pkUnitGlMappingId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.party.domain.MasterUnitGlMapping[ pkUnitGlMappingId=" + pkUnitGlMappingId + " ]";
    }
    
}

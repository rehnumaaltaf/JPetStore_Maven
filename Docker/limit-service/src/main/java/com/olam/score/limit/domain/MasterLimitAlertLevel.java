/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olam.score.limit.domain;

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
 * @author rashmi.kedlaya
 */
@Entity
@Table(name = "MASTER_LIMIT_ALERT_LEVEL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterLimitAlertLevel.findAll", query = "SELECT m FROM MasterLimitAlertLevel m")})
public class MasterLimitAlertLevel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_LIMIT_ALERT_LEVEL_ID")
    private Integer pkLimitAlertLevelId;
    @Size(max = 200)
    @Column(name = "LIMIT_ALERT_LEVEL_NAME")
    private String limitAlertLevelName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "LIMIT_ALERT_LEVEL_CODE")
    private String limitAlertLevelCode;
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
    @OneToMany(mappedBy = "fkLimitAlertLevelId")
    private Collection<MasterProductLimit> masterProductLimitCollection;
    @OneToMany(mappedBy = "fkLimitAlertLevelId")
    private Collection<MasterTraderLimit> masterTraderLimitCollection;
    @OneToMany(mappedBy = "fkLimitAlertLevelKey")
    private Collection<MasterCounterPartyLimit> masterCounterPartyLimitCollection;
    @OneToMany(mappedBy = "fkLimitAlertLevelId")
    private Collection<MasterLegalEntityLimit> masterLegalEntityLimitCollection;
    @OneToMany(mappedBy = "fkLimitAlertLevelId")
    private Collection<MasterUnitLimit> masterUnitLimitCollection;
    @OneToMany(mappedBy = "fkLimitAlertLevelId")
    private Collection<MasterLimit> masterLimitCollection;

    public MasterLimitAlertLevel() {
    }

    public MasterLimitAlertLevel(Integer pkLimitAlertLevelId) {
        this.pkLimitAlertLevelId = pkLimitAlertLevelId;
    }

    public MasterLimitAlertLevel(Integer pkLimitAlertLevelId, String limitAlertLevelCode) {
        this.pkLimitAlertLevelId = pkLimitAlertLevelId;
        this.limitAlertLevelCode = limitAlertLevelCode;
    }

    public Integer getPkLimitAlertLevelId() {
        return pkLimitAlertLevelId;
    }

    public void setPkLimitAlertLevelId(Integer pkLimitAlertLevelId) {
        this.pkLimitAlertLevelId = pkLimitAlertLevelId;
    }

    public String getLimitAlertLevelName() {
        return limitAlertLevelName;
    }

    public void setLimitAlertLevelName(String limitAlertLevelName) {
        this.limitAlertLevelName = limitAlertLevelName;
    }

    public String getLimitAlertLevelCode() {
        return limitAlertLevelCode;
    }

    public void setLimitAlertLevelCode(String limitAlertLevelCode) {
        this.limitAlertLevelCode = limitAlertLevelCode;
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
    public Collection<MasterProductLimit> getMasterProductLimitCollection() {
        return masterProductLimitCollection;
    }

    public void setMasterProductLimitCollection(Collection<MasterProductLimit> masterProductLimitCollection) {
        this.masterProductLimitCollection = masterProductLimitCollection;
    }

    @XmlTransient
    public Collection<MasterTraderLimit> getMasterTraderLimitCollection() {
        return masterTraderLimitCollection;
    }

    public void setMasterTraderLimitCollection(Collection<MasterTraderLimit> masterTraderLimitCollection) {
        this.masterTraderLimitCollection = masterTraderLimitCollection;
    }

    @XmlTransient
    public Collection<MasterCounterPartyLimit> getMasterCounterPartyLimitCollection() {
        return masterCounterPartyLimitCollection;
    }

    public void setMasterCounterPartyLimitCollection(Collection<MasterCounterPartyLimit> masterCounterPartyLimitCollection) {
        this.masterCounterPartyLimitCollection = masterCounterPartyLimitCollection;
    }

    @XmlTransient
    public Collection<MasterLegalEntityLimit> getMasterLegalEntityLimitCollection() {
        return masterLegalEntityLimitCollection;
    }

    public void setMasterLegalEntityLimitCollection(Collection<MasterLegalEntityLimit> masterLegalEntityLimitCollection) {
        this.masterLegalEntityLimitCollection = masterLegalEntityLimitCollection;
    }

    @XmlTransient
    public Collection<MasterUnitLimit> getMasterUnitLimitCollection() {
        return masterUnitLimitCollection;
    }

    public void setMasterUnitLimitCollection(Collection<MasterUnitLimit> masterUnitLimitCollection) {
        this.masterUnitLimitCollection = masterUnitLimitCollection;
    }

    @XmlTransient
    public Collection<MasterLimit> getMasterLimitCollection() {
        return masterLimitCollection;
    }

    public void setMasterLimitCollection(Collection<MasterLimit> masterLimitCollection) {
        this.masterLimitCollection = masterLimitCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkLimitAlertLevelId != null ? pkLimitAlertLevelId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterLimitAlertLevel)) {
            return false;
        }
        MasterLimitAlertLevel other = (MasterLimitAlertLevel) object;
        if ((this.pkLimitAlertLevelId == null && other.pkLimitAlertLevelId != null) || (this.pkLimitAlertLevelId != null && !this.pkLimitAlertLevelId.equals(other.pkLimitAlertLevelId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.terms.domain.MasterLimitAlertLevel[ pkLimitAlertLevelId=" + pkLimitAlertLevelId + " ]";
    }
    
}

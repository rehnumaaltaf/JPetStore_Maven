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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ramachandran_n02
 */
@Entity
@Table(name = "MASTER_UNIT_LIMIT",  schema = "limit")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterUnitLimit.findAll", query = "SELECT m FROM MasterUnitLimit m")})
public class MasterUnitLimit implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_UNIT_LIMIT_ID")
    private Integer pkUnitLimitId;
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
    @Column(name = "FK_PARTY_ID")
    private Integer fkPartyId;
    @Column(name = "FK_PROD_ID")
    private Integer fkProdId;
    @Column(name = "FK_UOM_ID")
    private Integer fkUomId;
    @Column(name = "FK_CURRENCY_ID")
    private Integer fkCurrencyId;
    @OneToMany(mappedBy = "fkUnitLimitId")
    private Collection<MasterUnitLimitDetail> masterUnitLimitDetailCollection;
    @JoinColumn(name = "FK_LIMIT_ALERT_LEVEL_ID", referencedColumnName = "PK_LIMIT_ALERT_LEVEL_ID")
    @ManyToOne
    private MasterLimitAlertLevel fkLimitAlertLevelId;

    public MasterUnitLimit() {
    }

    public MasterUnitLimit(Integer pkUnitLimitId) {
        this.pkUnitLimitId = pkUnitLimitId;
    }

    public Integer getPkUnitLimitId() {
        return pkUnitLimitId;
    }

    public void setPkUnitLimitId(Integer pkUnitLimitId) {
        this.pkUnitLimitId = pkUnitLimitId;
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

    public Integer getFkPartyId() {
        return fkPartyId;
    }

    public void setFkPartyId(Integer fkPartyId) {
        this.fkPartyId = fkPartyId;
    }

    public Integer getFkProdId() {
        return fkProdId;
    }

    public void setFkProdId(Integer fkProdId) {
        this.fkProdId = fkProdId;
    }

    public Integer getFkUomId() {
        return fkUomId;
    }

    public void setFkUomId(Integer fkUomId) {
        this.fkUomId = fkUomId;
    }

    public Integer getFkCurrencyId() {
        return fkCurrencyId;
    }

    public void setFkCurrencyId(Integer fkCurrencyId) {
        this.fkCurrencyId = fkCurrencyId;
    }

    @XmlTransient
    public Collection<MasterUnitLimitDetail> getMasterUnitLimitDetailCollection() {
        return masterUnitLimitDetailCollection;
    }

    public void setMasterUnitLimitDetailCollection(Collection<MasterUnitLimitDetail> masterUnitLimitDetailCollection) {
        this.masterUnitLimitDetailCollection = masterUnitLimitDetailCollection;
    }

    public MasterLimitAlertLevel getFkLimitAlertLevelId() {
        return fkLimitAlertLevelId;
    }

    public void setFkLimitAlertLevelId(MasterLimitAlertLevel fkLimitAlertLevelId) {
        this.fkLimitAlertLevelId = fkLimitAlertLevelId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkUnitLimitId != null ? pkUnitLimitId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterUnitLimit)) {
            return false;
        }
        MasterUnitLimit other = (MasterUnitLimit) object;
        if ((this.pkUnitLimitId == null && other.pkUnitLimitId != null) || (this.pkUnitLimitId != null && !this.pkUnitLimitId.equals(other.pkUnitLimitId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.limit.domain.MasterUnitLimit[ pkUnitLimitId=" + pkUnitLimitId + " ]";
    }
    
}

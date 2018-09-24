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
@Table(name = "MASTER_TRADER_LIMIT",  schema = "limit")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterTraderLimit.findAll", query = "SELECT m FROM MasterTraderLimit m")})
public class MasterTraderLimit implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_TRADER_LIMIT_ID")
    private Integer pkTraderLimitId;
    @Column(name = "FK_PARTY_ID")
    private Integer fkPartyId;
    @Column(name = "FK_PROD_ID")
    private Integer fkProdId;
    @Column(name = "FK_UNIT_ID")
    private Integer fkUnitId;
    @Column(name = "FK_UOM_ID")
    private Integer fkUomId;
    @Column(name = "FK_CURRENCY_ID")
    private Integer fkCurrencyId;
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
    @JoinColumn(name = "FK_LIMIT_ALERT_LEVEL_ID", referencedColumnName = "PK_LIMIT_ALERT_LEVEL_ID")
    @ManyToOne
    private MasterLimitAlertLevel fkLimitAlertLevelId;
    @OneToMany(mappedBy = "fkTraderLimitId")
    private Collection<MasterTraderLimitDetail> masterTraderLimitDetailCollection;

    public MasterTraderLimit() {
    }

    public MasterTraderLimit(Integer pkTraderLimitId) {
        this.pkTraderLimitId = pkTraderLimitId;
    }

    public Integer getPkTraderLimitId() {
        return pkTraderLimitId;
    }

    public void setPkTraderLimitId(Integer pkTraderLimitId) {
        this.pkTraderLimitId = pkTraderLimitId;
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

    public Integer getFkUnitId() {
        return fkUnitId;
    }

    public void setFkUnitId(Integer fkUnitId) {
        this.fkUnitId = fkUnitId;
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

    public MasterLimitAlertLevel getFkLimitAlertLevelId() {
        return fkLimitAlertLevelId;
    }

    public void setFkLimitAlertLevelId(MasterLimitAlertLevel fkLimitAlertLevelId) {
        this.fkLimitAlertLevelId = fkLimitAlertLevelId;
    }

    @XmlTransient
    public Collection<MasterTraderLimitDetail> getMasterTraderLimitDetailCollection() {
        return masterTraderLimitDetailCollection;
    }

    public void setMasterTraderLimitDetailCollection(Collection<MasterTraderLimitDetail> masterTraderLimitDetailCollection) {
        this.masterTraderLimitDetailCollection = masterTraderLimitDetailCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkTraderLimitId != null ? pkTraderLimitId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterTraderLimit)) {
            return false;
        }
        MasterTraderLimit other = (MasterTraderLimit) object;
        if ((this.pkTraderLimitId == null && other.pkTraderLimitId != null) || (this.pkTraderLimitId != null && !this.pkTraderLimitId.equals(other.pkTraderLimitId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.limit.domain.MasterTraderLimit[ pkTraderLimitId=" + pkTraderLimitId + " ]";
    }
    
}

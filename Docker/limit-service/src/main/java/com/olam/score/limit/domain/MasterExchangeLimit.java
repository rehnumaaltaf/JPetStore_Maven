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
 * @author ramachandran_n02
 */
@Entity
@Table(name = "MASTER_EXCHANGE_LIMIT",  schema = "limit")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterExchangeLimit.findAll", query = "SELECT m FROM MasterExchangeLimit m")})
public class MasterExchangeLimit implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "FK_PARTY_ID")
    private Integer fkPartyId;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_EXCHANGE_LIMIT_ID")
    private Integer pkExchangeLimitId;
    @Column(name = "FK_PROD_ID")
    private Integer fkProdId;
    @Column(name = "FK_LIMIT_ALERT_LEVEL_ID")
    private Integer fkLimitAlertLevelId;
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
    @OneToMany(mappedBy = "fkExchangeLimitId")
    private Collection<MasterExchangeLimitMapping> masterExchangeLimitMappingCollection;

    public MasterExchangeLimit() {
    }

    public MasterExchangeLimit(Integer pkExchangeLimitId) {
        this.pkExchangeLimitId = pkExchangeLimitId;
    }

    public Integer getFkPartyId() {
        return fkPartyId;
    }

    public void setFkPartyId(Integer fkPartyId) {
        this.fkPartyId = fkPartyId;
    }

    public Integer getPkExchangeLimitId() {
        return pkExchangeLimitId;
    }

    public void setPkExchangeLimitId(Integer pkExchangeLimitId) {
        this.pkExchangeLimitId = pkExchangeLimitId;
    }

    public Integer getFkProdId() {
        return fkProdId;
    }

    public void setFkProdId(Integer fkProdId) {
        this.fkProdId = fkProdId;
    }

    public Integer getFkLimitAlertLevelId() {
        return fkLimitAlertLevelId;
    }

    public void setFkLimitAlertLevelId(Integer fkLimitAlertLevelId) {
        this.fkLimitAlertLevelId = fkLimitAlertLevelId;
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

    @XmlTransient
    public Collection<MasterExchangeLimitMapping> getMasterExchangeLimitMappingCollection() {
        return masterExchangeLimitMappingCollection;
    }

    public void setMasterExchangeLimitMappingCollection(Collection<MasterExchangeLimitMapping> masterExchangeLimitMappingCollection) {
        this.masterExchangeLimitMappingCollection = masterExchangeLimitMappingCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkExchangeLimitId != null ? pkExchangeLimitId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterExchangeLimit)) {
            return false;
        }
        MasterExchangeLimit other = (MasterExchangeLimit) object;
        if ((this.pkExchangeLimitId == null && other.pkExchangeLimitId != null) || (this.pkExchangeLimitId != null && !this.pkExchangeLimitId.equals(other.pkExchangeLimitId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.limit.domain.MasterExchangeLimit[ pkExchangeLimitId=" + pkExchangeLimitId + " ]";
    }
    
}

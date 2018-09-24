/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olam.score.forex.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ramachandran_n02
 */
@Entity
@Table(name = "MASTER_FOREX_LIMIT",  schema = "forex")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterForexLimit.findAll", query = "SELECT m FROM MasterForexLimit m")})
public class MasterForexLimit implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_FOREX_LIMIT_ID")
    private Integer pkForexLimitId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FK_PARTY_ID")
    private int fkPartyId;
    @Column(name = "FK_PROD_ID")
    private Integer fkProdId;
    @Column(name = "FK_UNIT_ID")
    private Integer fkUnitId;
    @Column(name = "FK_LIMIT_ALERT_LEVEL_ID")
    private Integer fkLimitAlertLevelId;
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

    public MasterForexLimit() {
    }

    public MasterForexLimit(Integer pkForexLimitId) {
        this.pkForexLimitId = pkForexLimitId;
    }

    public MasterForexLimit(Integer pkForexLimitId, int fkPartyId) {
        this.pkForexLimitId = pkForexLimitId;
        this.fkPartyId = fkPartyId;
    }

    public Integer getPkForexLimitId() {
        return pkForexLimitId;
    }

    public void setPkForexLimitId(Integer pkForexLimitId) {
        this.pkForexLimitId = pkForexLimitId;
    }

    public int getFkPartyId() {
        return fkPartyId;
    }

    public void setFkPartyId(int fkPartyId) {
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

    public Integer getFkLimitAlertLevelId() {
        return fkLimitAlertLevelId;
    }

    public void setFkLimitAlertLevelId(Integer fkLimitAlertLevelId) {
        this.fkLimitAlertLevelId = fkLimitAlertLevelId;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkForexLimitId != null ? pkForexLimitId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterForexLimit)) {
            return false;
        }
        MasterForexLimit other = (MasterForexLimit) object;
        if ((this.pkForexLimitId == null && other.pkForexLimitId != null) || (this.pkForexLimitId != null && !this.pkForexLimitId.equals(other.pkForexLimitId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.forex.domain.MasterForexLimit[ pkForexLimitId=" + pkForexLimitId + " ]";
    }
    
}

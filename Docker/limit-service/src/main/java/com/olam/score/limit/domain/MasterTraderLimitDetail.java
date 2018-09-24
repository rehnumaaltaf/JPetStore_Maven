/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olam.score.limit.domain;

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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ramachandran_n02
 */
@Entity
@Table(name = "MASTER_TRADER_LIMIT_DETAIL",  schema = "limit")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterTraderLimitDetail.findAll", query = "SELECT m FROM MasterTraderLimitDetail m")})
public class MasterTraderLimitDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_TRADER_LIMIT_DETAIL_ID")
    private Integer pkTraderLimitDetailId;
    @Column(name = "TRADER_BASIS_LIMIT")
    private Integer traderBasisLimit;
    @Column(name = "TRADER_STRUCTURE_LIMIT")
    private Integer traderStructureLimit;
    @Column(name = "TRADER_VAR_LIMIT")
    private Integer traderVarLimit;
    @Column(name = "TRADER_M2M_LIMIT")
    private Integer traderM2mLimit;
    @Column(name = "TRADER_LIMIT_VALID_FROM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date traderLimitValidFrom;
    @Column(name = "TRADER_LIMIT_VALID_TO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date traderLimitValidTo;
    @Column(name = "FK_APP_USER_ID")
    private Integer fkAppUserId;
    @Column(name = "TRADER_OUTRIGHT_LIMIT")
    private Integer traderOutrightLimit;
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
    @JoinColumn(name = "FK_TRADER_LIMIT_ID", referencedColumnName = "PK_TRADER_LIMIT_ID")
    @ManyToOne
    private MasterTraderLimit fkTraderLimitId;

    public MasterTraderLimitDetail() {
    }

    public MasterTraderLimitDetail(Integer pkTraderLimitDetailId) {
        this.pkTraderLimitDetailId = pkTraderLimitDetailId;
    }

    public Integer getPkTraderLimitDetailId() {
        return pkTraderLimitDetailId;
    }

    public void setPkTraderLimitDetailId(Integer pkTraderLimitDetailId) {
        this.pkTraderLimitDetailId = pkTraderLimitDetailId;
    }

    public Integer getTraderBasisLimit() {
        return traderBasisLimit;
    }

    public void setTraderBasisLimit(Integer traderBasisLimit) {
        this.traderBasisLimit = traderBasisLimit;
    }

    public Integer getTraderStructureLimit() {
        return traderStructureLimit;
    }

    public void setTraderStructureLimit(Integer traderStructureLimit) {
        this.traderStructureLimit = traderStructureLimit;
    }

    public Integer getTraderVarLimit() {
        return traderVarLimit;
    }

    public void setTraderVarLimit(Integer traderVarLimit) {
        this.traderVarLimit = traderVarLimit;
    }

    public Integer getTraderM2mLimit() {
        return traderM2mLimit;
    }

    public void setTraderM2mLimit(Integer traderM2mLimit) {
        this.traderM2mLimit = traderM2mLimit;
    }

    public Date getTraderLimitValidFrom() {
        return traderLimitValidFrom;
    }

    public void setTraderLimitValidFrom(Date traderLimitValidFrom) {
        this.traderLimitValidFrom = traderLimitValidFrom;
    }

    public Date getTraderLimitValidTo() {
        return traderLimitValidTo;
    }

    public void setTraderLimitValidTo(Date traderLimitValidTo) {
        this.traderLimitValidTo = traderLimitValidTo;
    }

    public Integer getFkAppUserId() {
        return fkAppUserId;
    }

    public void setFkAppUserId(Integer fkAppUserId) {
        this.fkAppUserId = fkAppUserId;
    }

    public Integer getTraderOutrightLimit() {
        return traderOutrightLimit;
    }

    public void setTraderOutrightLimit(Integer traderOutrightLimit) {
        this.traderOutrightLimit = traderOutrightLimit;
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

    public MasterTraderLimit getFkTraderLimitId() {
        return fkTraderLimitId;
    }

    public void setFkTraderLimitId(MasterTraderLimit fkTraderLimitId) {
        this.fkTraderLimitId = fkTraderLimitId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkTraderLimitDetailId != null ? pkTraderLimitDetailId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterTraderLimitDetail)) {
            return false;
        }
        MasterTraderLimitDetail other = (MasterTraderLimitDetail) object;
        if ((this.pkTraderLimitDetailId == null && other.pkTraderLimitDetailId != null) || (this.pkTraderLimitDetailId != null && !this.pkTraderLimitDetailId.equals(other.pkTraderLimitDetailId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.limit.domain.MasterTraderLimitDetail[ pkTraderLimitDetailId=" + pkTraderLimitDetailId + " ]";
    }
    
}

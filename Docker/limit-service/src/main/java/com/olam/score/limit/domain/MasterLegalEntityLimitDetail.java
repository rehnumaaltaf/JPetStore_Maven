/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olam.score.limit.domain;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "MASTER_LEGAL_ENTITY_LIMIT_DETAIL",  schema = "limit")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterLegalEntityLimitDetail.findAll", query = "SELECT m FROM MasterLegalEntityLimitDetail m")})
public class MasterLegalEntityLimitDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_LEGAL_ENTITY_LIMIT_DETAIL_ID")
    private Integer pkLegalEntityLimitDetailId;
    @Column(name = "FK_STATUS_ID")
    private Integer fkStatusId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FK_PARTY_ID")
    private int fkPartyId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "LEGAL_ENTITY_BASIS_LIMIT")
    private BigDecimal legalEntityBasisLimit;
    @Column(name = "LEGAL_ENTITY_STRUCTURE_LIMIT")
    private BigDecimal legalEntityStructureLimit;
    @Column(name = "LEGAL_ENTITY_VAR_LIMIT")
    private BigDecimal legalEntityVarLimit;
    @Column(name = "LEGAL_ENTITY_M2M_LIMIT")
    private BigDecimal legalEntityM2mLimit;
    @Column(name = "LEGAL_ENTITY_LIMIT_VALID_FROM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date legalEntityLimitValidFrom;
    @Column(name = "LEGAL_ENTITY_LIMIT_VALID_TO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date legalEntityLimitValidTo;
    @Column(name = "LEGAL_ENTITY_OUTRIGHT_LIMIT")
    private BigDecimal legalEntityOutrightLimit;
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
    @JoinColumn(name = "FK_LEGAL_ENTITY_LIMIT_ID", referencedColumnName = "PK_LEGAL_ENTITY_LIMIT_ID")
    @ManyToOne(optional = false)
    private MasterLegalEntityLimit fkLegalEntityLimitId;

    public MasterLegalEntityLimitDetail() {
    }

    public MasterLegalEntityLimitDetail(Integer pkLegalEntityLimitDetailId) {
        this.pkLegalEntityLimitDetailId = pkLegalEntityLimitDetailId;
    }

    public MasterLegalEntityLimitDetail(Integer pkLegalEntityLimitDetailId, int fkPartyId) {
        this.pkLegalEntityLimitDetailId = pkLegalEntityLimitDetailId;
        this.fkPartyId = fkPartyId;
    }

    public Integer getPkLegalEntityLimitDetailId() {
        return pkLegalEntityLimitDetailId;
    }

    public void setPkLegalEntityLimitDetailId(Integer pkLegalEntityLimitDetailId) {
        this.pkLegalEntityLimitDetailId = pkLegalEntityLimitDetailId;
    }

    public Integer getFkStatusId() {
        return fkStatusId;
    }

    public void setFkStatusId(Integer fkStatusId) {
        this.fkStatusId = fkStatusId;
    }

    public int getFkPartyId() {
        return fkPartyId;
    }

    public void setFkPartyId(int fkPartyId) {
        this.fkPartyId = fkPartyId;
    }

    public BigDecimal getLegalEntityBasisLimit() {
        return legalEntityBasisLimit;
    }

    public void setLegalEntityBasisLimit(BigDecimal legalEntityBasisLimit) {
        this.legalEntityBasisLimit = legalEntityBasisLimit;
    }

    public BigDecimal getLegalEntityStructureLimit() {
        return legalEntityStructureLimit;
    }

    public void setLegalEntityStructureLimit(BigDecimal legalEntityStructureLimit) {
        this.legalEntityStructureLimit = legalEntityStructureLimit;
    }

    public BigDecimal getLegalEntityVarLimit() {
        return legalEntityVarLimit;
    }

    public void setLegalEntityVarLimit(BigDecimal legalEntityVarLimit) {
        this.legalEntityVarLimit = legalEntityVarLimit;
    }

    public BigDecimal getLegalEntityM2mLimit() {
        return legalEntityM2mLimit;
    }

    public void setLegalEntityM2mLimit(BigDecimal legalEntityM2mLimit) {
        this.legalEntityM2mLimit = legalEntityM2mLimit;
    }

    public Date getLegalEntityLimitValidFrom() {
        return legalEntityLimitValidFrom;
    }

    public void setLegalEntityLimitValidFrom(Date legalEntityLimitValidFrom) {
        this.legalEntityLimitValidFrom = legalEntityLimitValidFrom;
    }

    public Date getLegalEntityLimitValidTo() {
        return legalEntityLimitValidTo;
    }

    public void setLegalEntityLimitValidTo(Date legalEntityLimitValidTo) {
        this.legalEntityLimitValidTo = legalEntityLimitValidTo;
    }

    public BigDecimal getLegalEntityOutrightLimit() {
        return legalEntityOutrightLimit;
    }

    public void setLegalEntityOutrightLimit(BigDecimal legalEntityOutrightLimit) {
        this.legalEntityOutrightLimit = legalEntityOutrightLimit;
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

    public MasterLegalEntityLimit getFkLegalEntityLimitId() {
        return fkLegalEntityLimitId;
    }

    public void setFkLegalEntityLimitId(MasterLegalEntityLimit fkLegalEntityLimitId) {
        this.fkLegalEntityLimitId = fkLegalEntityLimitId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkLegalEntityLimitDetailId != null ? pkLegalEntityLimitDetailId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterLegalEntityLimitDetail)) {
            return false;
        }
        MasterLegalEntityLimitDetail other = (MasterLegalEntityLimitDetail) object;
        if ((this.pkLegalEntityLimitDetailId == null && other.pkLegalEntityLimitDetailId != null) || (this.pkLegalEntityLimitDetailId != null && !this.pkLegalEntityLimitDetailId.equals(other.pkLegalEntityLimitDetailId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.limit.domain.MasterLegalEntityLimitDetail[ pkLegalEntityLimitDetailId=" + pkLegalEntityLimitDetailId + " ]";
    }
    
}

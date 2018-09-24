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
@Table(name = "MASTER_PRODUCT_LIMIT_DETAIL",  schema = "limit")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterProductLimitDetail.findAll", query = "SELECT m FROM MasterProductLimitDetail m")})
public class MasterProductLimitDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_PROD_LIMIT_DETAIL_ID")
    private Integer pkProdLimitDetailId;
    @Column(name = "FK_PROD_ID")
    private Integer fkProdId;
    @Column(name = "PROD_BASIS_LIMIT")
    private Integer prodBasisLimit;
    @Column(name = "PROD_STRUCTURE_LIMIT")
    private Integer prodStructureLimit;
    @Size(max = 200)
    @Column(name = "PROD_FIXED_FORWARD_TENOR")
    private String prodFixedForwardTenor;
    @Column(name = "PROD_VAR_LIMIT")
    private Integer prodVarLimit;
    @Column(name = "PROD_M2M_LIMIT")
    private Integer prodM2mLimit;
    @Column(name = "PROD_LIMIT_VALID_FROM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date prodLimitValidFrom;
    @Column(name = "PROD_LIMIT_VALID_TO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date prodLimitValidTo;
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
    @JoinColumn(name = "FK_PROD_LIMIT_ID", referencedColumnName = "PK_PARTY_PROD_LIMIT_ID")
    @ManyToOne
    private MasterProductLimit fkProdLimitId;

    public MasterProductLimitDetail() {
    }

    public MasterProductLimitDetail(Integer pkProdLimitDetailId) {
        this.pkProdLimitDetailId = pkProdLimitDetailId;
    }

    public Integer getPkProdLimitDetailId() {
        return pkProdLimitDetailId;
    }

    public void setPkProdLimitDetailId(Integer pkProdLimitDetailId) {
        this.pkProdLimitDetailId = pkProdLimitDetailId;
    }

    public Integer getFkProdId() {
        return fkProdId;
    }

    public void setFkProdId(Integer fkProdId) {
        this.fkProdId = fkProdId;
    }

    public Integer getProdBasisLimit() {
        return prodBasisLimit;
    }

    public void setProdBasisLimit(Integer prodBasisLimit) {
        this.prodBasisLimit = prodBasisLimit;
    }

    public Integer getProdStructureLimit() {
        return prodStructureLimit;
    }

    public void setProdStructureLimit(Integer prodStructureLimit) {
        this.prodStructureLimit = prodStructureLimit;
    }

    public String getProdFixedForwardTenor() {
        return prodFixedForwardTenor;
    }

    public void setProdFixedForwardTenor(String prodFixedForwardTenor) {
        this.prodFixedForwardTenor = prodFixedForwardTenor;
    }

    public Integer getProdVarLimit() {
        return prodVarLimit;
    }

    public void setProdVarLimit(Integer prodVarLimit) {
        this.prodVarLimit = prodVarLimit;
    }

    public Integer getProdM2mLimit() {
        return prodM2mLimit;
    }

    public void setProdM2mLimit(Integer prodM2mLimit) {
        this.prodM2mLimit = prodM2mLimit;
    }

    public Date getProdLimitValidFrom() {
        return prodLimitValidFrom;
    }

    public void setProdLimitValidFrom(Date prodLimitValidFrom) {
        this.prodLimitValidFrom = prodLimitValidFrom;
    }

    public Date getProdLimitValidTo() {
        return prodLimitValidTo;
    }

    public void setProdLimitValidTo(Date prodLimitValidTo) {
        this.prodLimitValidTo = prodLimitValidTo;
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

    public MasterProductLimit getFkProdLimitId() {
        return fkProdLimitId;
    }

    public void setFkProdLimitId(MasterProductLimit fkProdLimitId) {
        this.fkProdLimitId = fkProdLimitId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkProdLimitDetailId != null ? pkProdLimitDetailId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterProductLimitDetail)) {
            return false;
        }
        MasterProductLimitDetail other = (MasterProductLimitDetail) object;
        if ((this.pkProdLimitDetailId == null && other.pkProdLimitDetailId != null) || (this.pkProdLimitDetailId != null && !this.pkProdLimitDetailId.equals(other.pkProdLimitDetailId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.limit.domain.MasterProductLimitDetail[ pkProdLimitDetailId=" + pkProdLimitDetailId + " ]";
    }
    
}

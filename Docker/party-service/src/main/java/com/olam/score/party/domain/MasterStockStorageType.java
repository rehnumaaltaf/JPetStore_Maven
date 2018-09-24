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
@Table(name = "MASTER_STOCK_STORAGE_TYPE")
@NamedQueries({
    @NamedQuery(name = "MasterStockStorageType.findAll", query = "SELECT m FROM MasterStockStorageType m")})
public class MasterStockStorageType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_STOCK_STORAGE_TYPE_ID")
    private Integer pkStockStorageTypeId;
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
    @Size(max = 20)
    @Column(name = "STOCK_STORAGE_TYPE_CODE")
    private String stockStorageTypeCode;
    @Size(max = 200)
    @Column(name = "STOCK_STORAGE_TYPE_NAME")
    private String stockStorageTypeName;
    @Column(name = "FK_STATUS_ID")
    private Integer fkStatusId;

    public MasterStockStorageType() {
    }

    public MasterStockStorageType(Integer pkStockStorageTypeId) {
        this.pkStockStorageTypeId = pkStockStorageTypeId;
    }

    public Integer getPkStockStorageTypeId() {
        return pkStockStorageTypeId;
    }

    public void setPkStockStorageTypeId(Integer pkStockStorageTypeId) {
        this.pkStockStorageTypeId = pkStockStorageTypeId;
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

    public String getStockStorageTypeCode() {
        return stockStorageTypeCode;
    }

    public void setStockStorageTypeCode(String stockStorageTypeCode) {
        this.stockStorageTypeCode = stockStorageTypeCode;
    }

    public String getStockStorageTypeName() {
        return stockStorageTypeName;
    }

    public void setStockStorageTypeName(String stockStorageTypeName) {
        this.stockStorageTypeName = stockStorageTypeName;
    }

    public Integer getFkStatusId() {
        return fkStatusId;
    }

    public void setFkStatusId(Integer fkStatusId) {
        this.fkStatusId = fkStatusId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkStockStorageTypeId != null ? pkStockStorageTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterStockStorageType)) {
            return false;
        }
        MasterStockStorageType other = (MasterStockStorageType) object;
        if ((this.pkStockStorageTypeId == null && other.pkStockStorageTypeId != null) || (this.pkStockStorageTypeId != null && !this.pkStockStorageTypeId.equals(other.pkStockStorageTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.party.domain.MasterStockStorageType[ pkStockStorageTypeId=" + pkStockStorageTypeId + " ]";
    }
    
}

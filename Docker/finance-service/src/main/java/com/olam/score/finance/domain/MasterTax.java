/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olam.score.finance.domain;

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
@Table(name = "MASTER_TAX",  schema = "finance")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterTax.findAll", query = "SELECT m FROM MasterTax m")})
public class MasterTax implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_TAX_ID")
    private Integer pkTaxId;
    @Size(max = 20)
    @Column(name = "TAX_CODE")
    private String taxCode;
    @Size(max = 20)
    @Column(name = "TAX_CATEGORY")
    private String taxCategory;
    @Column(name = "FK_GEO_ID")
    private Integer fkGeoId;
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

    public MasterTax() {
    }

    public MasterTax(Integer pkTaxId) {
        this.pkTaxId = pkTaxId;
    }

    public Integer getPkTaxId() {
        return pkTaxId;
    }

    public void setPkTaxId(Integer pkTaxId) {
        this.pkTaxId = pkTaxId;
    }

    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    public String getTaxCategory() {
        return taxCategory;
    }

    public void setTaxCategory(String taxCategory) {
        this.taxCategory = taxCategory;
    }

    public Integer getFkGeoId() {
        return fkGeoId;
    }

    public void setFkGeoId(Integer fkGeoId) {
        this.fkGeoId = fkGeoId;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkTaxId != null ? pkTaxId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterTax)) {
            return false;
        }
        MasterTax other = (MasterTax) object;
        if ((this.pkTaxId == null && other.pkTaxId != null) || (this.pkTaxId != null && !this.pkTaxId.equals(other.pkTaxId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.finance.domain.MasterTax[ pkTaxId=" + pkTaxId + " ]";
    }
    
}

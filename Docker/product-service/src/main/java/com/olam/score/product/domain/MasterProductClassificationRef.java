/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olam.score.product.domain;

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
@Table(name = "MASTER_PRODUCT_CLASSIFICATION_REF",  schema = "product")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterProductClassificationRef.findAll", query = "SELECT m FROM MasterProductClassificationRef m")})
public class MasterProductClassificationRef implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_PRODUCT_CLASSIFICATION_REF_ID")
    private Integer pkProductClassificationRefId;
    @Size(max = 200)
    @Column(name = "PROD_CLASS_REF_NAME")
    private String prodClassRefName;
    @Size(max = 1000)
    @Column(name = "PROD_CLASS_REF_DESC")
    private String prodClassRefDesc;
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
    @Column(name = "PROD_CLASS_REF_CODE")
    private String prodClassRefCode;
    @Column(name = "FK_STATUS_ID")
    private Integer fkStatusId;

    public MasterProductClassificationRef() {
    }

    public MasterProductClassificationRef(Integer pkProductClassificationRefId) {
        this.pkProductClassificationRefId = pkProductClassificationRefId;
    }

    public Integer getPkProductClassificationRefId() {
        return pkProductClassificationRefId;
    }

    public void setPkProductClassificationRefId(Integer pkProductClassificationRefId) {
        this.pkProductClassificationRefId = pkProductClassificationRefId;
    }

    public String getProdClassRefName() {
        return prodClassRefName;
    }

    public void setProdClassRefName(String prodClassRefName) {
        this.prodClassRefName = prodClassRefName;
    }

    public String getProdClassRefDesc() {
        return prodClassRefDesc;
    }

    public void setProdClassRefDesc(String prodClassRefDesc) {
        this.prodClassRefDesc = prodClassRefDesc;
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

    public String getProdClassRefCode() {
        return prodClassRefCode;
    }

    public void setProdClassRefCode(String prodClassRefCode) {
        this.prodClassRefCode = prodClassRefCode;
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
        hash += (pkProductClassificationRefId != null ? pkProductClassificationRefId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterProductClassificationRef)) {
            return false;
        }
        MasterProductClassificationRef other = (MasterProductClassificationRef) object;
        if ((this.pkProductClassificationRefId == null && other.pkProductClassificationRefId != null) || (this.pkProductClassificationRefId != null && !this.pkProductClassificationRefId.equals(other.pkProductClassificationRefId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.product.domain.MasterProductClassificationRef[ pkProductClassificationRefId=" + pkProductClassificationRefId + " ]";
    }
    
}

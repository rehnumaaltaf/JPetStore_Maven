/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olam.score.terms.domain;

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
 * @author Ramachandran_N02
 */
@Entity
@Table(name = "MASTER_WEIGHT_TERM_TYPE_REF")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterWeightTermTypeRef.findAll", query = "SELECT m FROM MasterWeightTermTypeRef m")})
public class MasterWeightTermTypeRef implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_WEIGHT_TERM_TYPE_REF_ID")
    private Integer pkWeightTermTypeRefId;
    @Size(max = 200)
    @Column(name = "WEIGHT_TERM_TYPE_NAME")
    private String weightTermTypeName;
    @Size(max = 20)
    @Column(name = "WEIGHT_TERM_TYPE_CODE")
    private String weightTermTypeCode;
    @Size(max = 1000)
    @Column(name = "WEIGHT_TERM_TYPE_DESC")
    private String weightTermTypeDesc;
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
    @OneToMany(mappedBy = "fkWeightTermTypeRefId")
    private Collection<MasterWeightTerms> masterWeightTermsCollection;

    public MasterWeightTermTypeRef() {
    }

    public MasterWeightTermTypeRef(Integer pkWeightTermTypeRefId) {
        this.pkWeightTermTypeRefId = pkWeightTermTypeRefId;
    }

    public Integer getPkWeightTermTypeRefId() {
        return pkWeightTermTypeRefId;
    }

    public void setPkWeightTermTypeRefId(Integer pkWeightTermTypeRefId) {
        this.pkWeightTermTypeRefId = pkWeightTermTypeRefId;
    }

    public String getWeightTermTypeName() {
        return weightTermTypeName;
    }

    public void setWeightTermTypeName(String weightTermTypeName) {
        this.weightTermTypeName = weightTermTypeName;
    }

    public String getWeightTermTypeCode() {
        return weightTermTypeCode;
    }

    public void setWeightTermTypeCode(String weightTermTypeCode) {
        this.weightTermTypeCode = weightTermTypeCode;
    }

    public String getWeightTermTypeDesc() {
        return weightTermTypeDesc;
    }

    public void setWeightTermTypeDesc(String weightTermTypeDesc) {
        this.weightTermTypeDesc = weightTermTypeDesc;
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

    @XmlTransient
    public Collection<MasterWeightTerms> getMasterWeightTermsCollection() {
        return masterWeightTermsCollection;
    }

    public void setMasterWeightTermsCollection(Collection<MasterWeightTerms> masterWeightTermsCollection) {
        this.masterWeightTermsCollection = masterWeightTermsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkWeightTermTypeRefId != null ? pkWeightTermTypeRefId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterWeightTermTypeRef)) {
            return false;
        }
        MasterWeightTermTypeRef other = (MasterWeightTermTypeRef) object;
        if ((this.pkWeightTermTypeRefId == null && other.pkWeightTermTypeRefId != null) || (this.pkWeightTermTypeRefId != null && !this.pkWeightTermTypeRefId.equals(other.pkWeightTermTypeRefId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.terms.domain.MasterWeightTermTypeRef[ pkWeightTermTypeRefId=" + pkWeightTermTypeRefId + " ]";
    }
    
}

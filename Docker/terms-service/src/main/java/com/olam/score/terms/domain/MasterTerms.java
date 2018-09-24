/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olam.score.terms.domain;

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
 * @author Ramachandran_N02
 */
@Entity
@Table(name = "MASTER_TERMS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterTerms.findAll", query = "SELECT m FROM MasterTerms m")})
public class MasterTerms implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_TERMS_ID")
    private Integer pkTermsId;
    @Size(max = 1000)
    @Column(name = "TERMS_DETAILS")
    private String termsDetails;
    @Size(max = 20)
    @Column(name = "TERMS_ASSOCIATED_WITH")
    private String termsAssociatedWith;
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

    public MasterTerms() {
    }

    public MasterTerms(Integer pkTermsId) {
        this.pkTermsId = pkTermsId;
    }

    public Integer getPkTermsId() {
        return pkTermsId;
    }

    public void setPkTermsId(Integer pkTermsId) {
        this.pkTermsId = pkTermsId;
    }

    public String getTermsDetails() {
        return termsDetails;
    }

    public void setTermsDetails(String termsDetails) {
        this.termsDetails = termsDetails;
    }

    public String getTermsAssociatedWith() {
        return termsAssociatedWith;
    }

    public void setTermsAssociatedWith(String termsAssociatedWith) {
        this.termsAssociatedWith = termsAssociatedWith;
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
        hash += (pkTermsId != null ? pkTermsId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterTerms)) {
            return false;
        }
        MasterTerms other = (MasterTerms) object;
        if ((this.pkTermsId == null && other.pkTermsId != null) || (this.pkTermsId != null && !this.pkTermsId.equals(other.pkTermsId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.terms.domain.MasterTerms[ pkTermsId=" + pkTermsId + " ]";
    }
    
}

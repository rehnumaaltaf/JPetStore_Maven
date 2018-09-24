/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olam.score.product.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "MASTER_INTL_CODE_TYPE_REF")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterIntlCodeTypeRef.findAll", query = "SELECT m FROM MasterIntlCodeTypeRef m")})
public class MasterIntlCodeTypeRef implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_INTL_CODE_TYPE_REF_ID")
    private Integer pkIntlCodeTypeRefId;
    @Size(max = 20)
    @Column(name = "INTL_CODE_TYPE_CODE")
    private String intlCodeTypeCode;
    @Size(max = 200)
    @Column(name = "INTL_CODE_TYPE_NAME")
    private String intlCodeTypeName;
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
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "fkIntlCodeTypeRefId")
    private Collection<MasterProductIntlCode> masterProductIntlCodeCollection;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "fkIntlCodeTypeRefId")
    private Collection<MasterGradeIntlCode> masterGradeIntlCodeCollection;

    public MasterIntlCodeTypeRef() {
    }

    public MasterIntlCodeTypeRef(Integer pkIntlCodeTypeRefId) {
        this.pkIntlCodeTypeRefId = pkIntlCodeTypeRefId;
    }

    public Integer getPkIntlCodeTypeRefId() {
        return pkIntlCodeTypeRefId;
    }

    public void setPkIntlCodeTypeRefId(Integer pkIntlCodeTypeRefId) {
        this.pkIntlCodeTypeRefId = pkIntlCodeTypeRefId;
    }

    public String getIntlCodeTypeCode() {
        return intlCodeTypeCode;
    }

    public void setIntlCodeTypeCode(String intlCodeTypeCode) {
        this.intlCodeTypeCode = intlCodeTypeCode;
    }

    public String getIntlCodeTypeName() {
        return intlCodeTypeName;
    }

    public void setIntlCodeTypeName(String intlCodeTypeName) {
        this.intlCodeTypeName = intlCodeTypeName;
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
    public Collection<MasterProductIntlCode> getMasterProductIntlCodeCollection() {
        return masterProductIntlCodeCollection;
    }

    public void setMasterProductIntlCodeCollection(Collection<MasterProductIntlCode> masterProductIntlCodeCollection) {
        this.masterProductIntlCodeCollection = masterProductIntlCodeCollection;
    }

    @XmlTransient
    public Collection<MasterGradeIntlCode> getMasterGradeIntlCodeCollection() {
        return masterGradeIntlCodeCollection;
    }

    public void setMasterGradeIntlCodeCollection(Collection<MasterGradeIntlCode> masterGradeIntlCodeCollection) {
        this.masterGradeIntlCodeCollection = masterGradeIntlCodeCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkIntlCodeTypeRefId != null ? pkIntlCodeTypeRefId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterIntlCodeTypeRef)) {
            return false;
        }
        MasterIntlCodeTypeRef other = (MasterIntlCodeTypeRef) object;
        if ((this.pkIntlCodeTypeRefId == null && other.pkIntlCodeTypeRefId != null) || (this.pkIntlCodeTypeRefId != null && !this.pkIntlCodeTypeRefId.equals(other.pkIntlCodeTypeRefId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.product.domain.MasterIntlCodeTypeRef[ pkIntlCodeTypeRefId=" + pkIntlCodeTypeRefId + " ]";
    }
    
}

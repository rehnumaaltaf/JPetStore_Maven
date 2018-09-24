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
 * @author ramachandran_n02
 */
@Entity
@Table(name = "MASTER_QUALITY_NAME_REF",  schema = "product")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterQualityNameRef.findAll", query = "SELECT m FROM MasterQualityNameRef m")})
public class MasterQualityNameRef implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_QLTY_NAME_REF_ID")
    private Integer pkQltyNameRefId;
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
    @Size(max = 200)
    @Column(name = "QLTY_NAME_REF_NAME")
    private String qltyNameRefName;
    @Size(max = 20)
    @Column(name = "QLTY_NAME_REF_CODE")
    private String qltyNameRefCode;
    @Column(name = "FK_STATUS_ID")
    private Integer fkStatusId;
    @OneToMany(mappedBy = "fkQltyNameRefId")
    private Collection<MasterQualityGradeSpecification> masterQualityGradeSpecificationCollection;

    public MasterQualityNameRef() {
    }

    public MasterQualityNameRef(Integer pkQltyNameRefId) {
        this.pkQltyNameRefId = pkQltyNameRefId;
    }

    public Integer getPkQltyNameRefId() {
        return pkQltyNameRefId;
    }

    public void setPkQltyNameRefId(Integer pkQltyNameRefId) {
        this.pkQltyNameRefId = pkQltyNameRefId;
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

    public String getQltyNameRefName() {
        return qltyNameRefName;
    }

    public void setQltyNameRefName(String qltyNameRefName) {
        this.qltyNameRefName = qltyNameRefName;
    }

    public String getQltyNameRefCode() {
        return qltyNameRefCode;
    }

    public void setQltyNameRefCode(String qltyNameRefCode) {
        this.qltyNameRefCode = qltyNameRefCode;
    }

    public Integer getFkStatusId() {
        return fkStatusId;
    }

    public void setFkStatusId(Integer fkStatusId) {
        this.fkStatusId = fkStatusId;
    }

    @XmlTransient
    public Collection<MasterQualityGradeSpecification> getMasterQualityGradeSpecificationCollection() {
        return masterQualityGradeSpecificationCollection;
    }

    public void setMasterQualityGradeSpecificationCollection(Collection<MasterQualityGradeSpecification> masterQualityGradeSpecificationCollection) {
        this.masterQualityGradeSpecificationCollection = masterQualityGradeSpecificationCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkQltyNameRefId != null ? pkQltyNameRefId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterQualityNameRef)) {
            return false;
        }
        MasterQualityNameRef other = (MasterQualityNameRef) object;
        if ((this.pkQltyNameRefId == null && other.pkQltyNameRefId != null) || (this.pkQltyNameRefId != null && !this.pkQltyNameRefId.equals(other.pkQltyNameRefId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.product.domain.MasterQualityNameRef[ pkQltyNameRefId=" + pkQltyNameRefId + " ]";
    }
    
}

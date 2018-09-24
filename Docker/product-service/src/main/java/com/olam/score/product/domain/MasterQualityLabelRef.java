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
@Table(name = "MASTER_QUALITY_LABEL_REF",  schema = "product")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterQualityLabelRef.findAll", query = "SELECT m FROM MasterQualityLabelRef m")})
public class MasterQualityLabelRef implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_QLTY_LABEL_REF_ID")
    private Integer pkQltyLabelRefId;
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
    @Column(name = "QLTY_LABEL_REF_NAME")
    private String qltyLabelRefName;
    @Size(max = 20)
    @Column(name = "QLTY_LABEL_REF_CODE")
    private String qltyLabelRefCode;
    @Column(name = "FK_STATUS_ID")
    private Integer fkStatusId;
    @OneToMany(mappedBy = "fkQltyLabelRefId")
    private Collection<MasterQualityGradeSpecification> masterQualityGradeSpecificationCollection;

    public MasterQualityLabelRef() {
    }

    public MasterQualityLabelRef(Integer pkQltyLabelRefId) {
        this.pkQltyLabelRefId = pkQltyLabelRefId;
    }

    public Integer getPkQltyLabelRefId() {
        return pkQltyLabelRefId;
    }

    public void setPkQltyLabelRefId(Integer pkQltyLabelRefId) {
        this.pkQltyLabelRefId = pkQltyLabelRefId;
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

    public String getQltyLabelRefName() {
        return qltyLabelRefName;
    }

    public void setQltyLabelRefName(String qltyLabelRefName) {
        this.qltyLabelRefName = qltyLabelRefName;
    }

    public String getQltyLabelRefCode() {
        return qltyLabelRefCode;
    }

    public void setQltyLabelRefCode(String qltyLabelRefCode) {
        this.qltyLabelRefCode = qltyLabelRefCode;
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
        hash += (pkQltyLabelRefId != null ? pkQltyLabelRefId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterQualityLabelRef)) {
            return false;
        }
        MasterQualityLabelRef other = (MasterQualityLabelRef) object;
        if ((this.pkQltyLabelRefId == null && other.pkQltyLabelRefId != null) || (this.pkQltyLabelRefId != null && !this.pkQltyLabelRefId.equals(other.pkQltyLabelRefId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.product.domain.MasterQualityLabelRef[ pkQltyLabelRefId=" + pkQltyLabelRefId + " ]";
    }
    
}

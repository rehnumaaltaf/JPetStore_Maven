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
 * @author Ramachandran_N02
 */
@Entity
@Table(name = "MASTER_GRADE_SPEC_TYPE_REF")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterGradeSpecTypeRef.findAll", query = "SELECT m FROM MasterGradeSpecTypeRef m")})
public class MasterGradeSpecTypeRef implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_GRADE_SPEC_TYPE_REF_ID")
    private Integer pkGradeSpecTypeRefId;
    @Size(max = 20)
    @Column(name = "GRADE_SPEC_TYPE_CODE")
    private String gradeSpecTypeCode;
    @Size(max = 200)
    @Column(name = "GRADE_SPEC_TYPE_NAME")
    private String gradeSpecTypeName;
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
    @OneToMany(mappedBy = "fkGradeSpecTypeId")
    private Collection<MasterGradeSpec> masterGradeSpecCollection;

    public MasterGradeSpecTypeRef() {
    }

    public MasterGradeSpecTypeRef(Integer pkGradeSpecTypeRefId) {
        this.pkGradeSpecTypeRefId = pkGradeSpecTypeRefId;
    }

    public Integer getPkGradeSpecTypeRefId() {
        return pkGradeSpecTypeRefId;
    }

    public void setPkGradeSpecTypeRefId(Integer pkGradeSpecTypeRefId) {
        this.pkGradeSpecTypeRefId = pkGradeSpecTypeRefId;
    }

    public String getGradeSpecTypeCode() {
        return gradeSpecTypeCode;
    }

    public void setGradeSpecTypeCode(String gradeSpecTypeCode) {
        this.gradeSpecTypeCode = gradeSpecTypeCode;
    }

    public String getGradeSpecTypeName() {
        return gradeSpecTypeName;
    }

    public void setGradeSpecTypeName(String gradeSpecTypeName) {
        this.gradeSpecTypeName = gradeSpecTypeName;
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

    @XmlTransient
    public Collection<MasterGradeSpec> getMasterGradeSpecCollection() {
        return masterGradeSpecCollection;
    }

    public void setMasterGradeSpecCollection(Collection<MasterGradeSpec> masterGradeSpecCollection) {
        this.masterGradeSpecCollection = masterGradeSpecCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkGradeSpecTypeRefId != null ? pkGradeSpecTypeRefId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterGradeSpecTypeRef)) {
            return false;
        }
        MasterGradeSpecTypeRef other = (MasterGradeSpecTypeRef) object;
        if ((this.pkGradeSpecTypeRefId == null && other.pkGradeSpecTypeRefId != null) || (this.pkGradeSpecTypeRefId != null && !this.pkGradeSpecTypeRefId.equals(other.pkGradeSpecTypeRefId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.product.domain.MasterGradeSpecTypeRef[ pkGradeSpecTypeRefId=" + pkGradeSpecTypeRefId + " ]";
    }
    
}

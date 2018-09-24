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
@Table(name = "MASTER_GRADE_GROUP_REF")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterGradeGroupRef.findAll", query = "SELECT m FROM MasterGradeGroupRef m")})
public class MasterGradeGroupRef implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_GRADE_GROUP_REF_ID")
    private Integer pkGradeGroupRefId;
    @Size(max = 20)
    @Column(name = "GRADE_GROUP_CODE")
    private String gradeGroupCode;
    @Size(max = 200)
    @Column(name = "GRADE_GROUP_NAME")
    private String gradeGroupName;
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
    @OneToMany(mappedBy = "fkGradeGroupRefId")
    private Collection<MasterOriginGradeGroup> masterOriginGradeGroupCollection;

    public MasterGradeGroupRef() {
    }

    public MasterGradeGroupRef(Integer pkGradeGroupRefId) {
        this.pkGradeGroupRefId = pkGradeGroupRefId;
    }

    public Integer getPkGradeGroupRefId() {
        return pkGradeGroupRefId;
    }

    public void setPkGradeGroupRefId(Integer pkGradeGroupRefId) {
        this.pkGradeGroupRefId = pkGradeGroupRefId;
    }

    public String getGradeGroupCode() {
        return gradeGroupCode;
    }

    public void setGradeGroupCode(String gradeGroupCode) {
        this.gradeGroupCode = gradeGroupCode;
    }

    public String getGradeGroupName() {
        return gradeGroupName;
    }

    public void setGradeGroupName(String gradeGroupName) {
        this.gradeGroupName = gradeGroupName;
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
    public Collection<MasterOriginGradeGroup> getMasterOriginGradeGroupCollection() {
        return masterOriginGradeGroupCollection;
    }

    public void setMasterOriginGradeGroupCollection(Collection<MasterOriginGradeGroup> masterOriginGradeGroupCollection) {
        this.masterOriginGradeGroupCollection = masterOriginGradeGroupCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkGradeGroupRefId != null ? pkGradeGroupRefId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterGradeGroupRef)) {
            return false;
        }
        MasterGradeGroupRef other = (MasterGradeGroupRef) object;
        if ((this.pkGradeGroupRefId == null && other.pkGradeGroupRefId != null) || (this.pkGradeGroupRefId != null && !this.pkGradeGroupRefId.equals(other.pkGradeGroupRefId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.product.domain.MasterGradeGroupRef[ pkGradeGroupRefId=" + pkGradeGroupRefId + " ]";
    }
    
}

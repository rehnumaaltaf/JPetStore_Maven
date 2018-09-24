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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "MASTER_GRADE_ORIGIN_REG_ASSOC",  schema = "product")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterGradeOriginRegAssoc.findAll", query = "SELECT m FROM MasterGradeOriginRegAssoc m")})
public class MasterGradeOriginRegAssoc implements Serializable {

    private static final long serialVersionUID = 1L;
    @Size(max = 20)
    @Column(name = "CREATED_BY")
    private String createdBy;
    @Column(name = "CREATED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Size(max = 20)
    @Column(name = "MODIFIED_BY")
    private String modifiedBy;
    @Column(name = "MODIFIED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_GRADE_ORIGIN_REG_ASSOC_ID")
    private Integer gradeOriginRegAssocId;
    @JoinColumn(name = "FK_GRADE_ID", referencedColumnName = "PK_GRADE_ID")
    @ManyToOne(optional = false)
    private MasterGrade fkGradeId;
    @JoinColumn(name = "FK_ORIGIN_REGION_ID", referencedColumnName = "PK_ORIGIN_REGION_ID")
    @ManyToOne
    private MasterOriginRegion fkOriginRegionId;

    public MasterGradeOriginRegAssoc() {
    }

    public MasterGradeOriginRegAssoc(Integer gradeOriginRegAssocId) {
        this.gradeOriginRegAssocId = gradeOriginRegAssocId;
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

    public Integer getGradeOriginRegAssocId() {
        return gradeOriginRegAssocId;
    }

    public void setGradeOriginRegAssocId(Integer gradeOriginRegAssocId) {
        this.gradeOriginRegAssocId = gradeOriginRegAssocId;
    }

    public MasterGrade getFkGradeId() {
        return fkGradeId;
    }

    public void setFkGradeId(MasterGrade fkGradeId) {
        this.fkGradeId = fkGradeId;
    }

    public MasterOriginRegion getFkOriginRegionId() {
        return fkOriginRegionId;
    }

    public void setFkOriginRegionId(MasterOriginRegion fkOriginRegionId) {
        this.fkOriginRegionId = fkOriginRegionId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (gradeOriginRegAssocId != null ? gradeOriginRegAssocId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterGradeOriginRegAssoc)) {
            return false;
        }
        MasterGradeOriginRegAssoc other = (MasterGradeOriginRegAssoc) object;
        if ((this.gradeOriginRegAssocId == null && other.gradeOriginRegAssocId != null) || (this.gradeOriginRegAssocId != null && !this.gradeOriginRegAssocId.equals(other.gradeOriginRegAssocId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.product.domain.MasterGradeOriginRegAssoc[ gradeOriginRegAssocId=" + gradeOriginRegAssocId + " ]";
    }
    
}

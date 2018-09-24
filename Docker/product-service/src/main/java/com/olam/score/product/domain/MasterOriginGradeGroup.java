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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
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
@Table(name = "MASTER_ORIGIN_GRADE_GROUP")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterOriginGradeGroup.findAll", query = "SELECT m FROM MasterOriginGradeGroup m")})
public class MasterOriginGradeGroup implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRODUCT_ORIGINORIGINGRADEGROUPIDSEQ_GENERATOR")
 	@SequenceGenerator(name = "PRODUCT_ORIGINORIGINGRADEGROUPIDSEQ_GENERATOR", sequenceName = "ORIGIN_GRADE_GROUP_ID_SEQ", initialValue = 1, allocationSize = 1)
    @Column(name = "PK_ORIGIN_GRADE_GROUP_ID")
    private Integer pkOriginGradeGroupId;
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
    /*@OneToMany(mappedBy = "fkOriginGradeGroupId")
    private Collection<MasterGrade> masterGradeCollection;*/
    @JoinColumn(name = "FK_GRADE_GROUP_REF_ID", referencedColumnName = "PK_GRADE_GROUP_REF_ID")
    @ManyToOne
    private MasterGradeGroupRef fkGradeGroupRefId;
    @JoinColumn(name = "FK_ORIGIN_ID", referencedColumnName = "PK_ORIGIN_ID")
    @ManyToOne
    private MasterOrigin fkOriginId;

    public MasterOriginGradeGroup() {
    }

    public MasterOriginGradeGroup(Integer pkOriginGradeGroupId) {
        this.pkOriginGradeGroupId = pkOriginGradeGroupId;
    }

    public Integer getPkOriginGradeGroupId() {
        return pkOriginGradeGroupId;
    }

    public void setPkOriginGradeGroupId(Integer pkOriginGradeGroupId) {
        this.pkOriginGradeGroupId = pkOriginGradeGroupId;
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

   /* @XmlTransient
    public Collection<MasterGrade> getMasterGradeCollection() {
        return masterGradeCollection;
    }

    public void setMasterGradeCollection(Collection<MasterGrade> masterGradeCollection) {
        this.masterGradeCollection = masterGradeCollection;
    }*/

    public MasterGradeGroupRef getFkGradeGroupRefId() {
        return fkGradeGroupRefId;
    }

    public void setFkGradeGroupRefId(MasterGradeGroupRef fkGradeGroupRefId) {
        this.fkGradeGroupRefId = fkGradeGroupRefId;
    }

    public MasterOrigin getFkOriginId() {
        return fkOriginId;
    }

    public void setFkOriginId(MasterOrigin fkOriginId) {
        this.fkOriginId = fkOriginId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkOriginGradeGroupId != null ? pkOriginGradeGroupId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterOriginGradeGroup)) {
            return false;
        }
        MasterOriginGradeGroup other = (MasterOriginGradeGroup) object;
        if ((this.pkOriginGradeGroupId == null && other.pkOriginGradeGroupId != null) || (this.pkOriginGradeGroupId != null && !this.pkOriginGradeGroupId.equals(other.pkOriginGradeGroupId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.product.domain.MasterOriginGradeGroup[ pkOriginGradeGroupId=" + pkOriginGradeGroupId + " ]";
    }
    
}

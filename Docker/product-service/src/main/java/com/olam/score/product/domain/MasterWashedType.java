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
@Table(name = "MASTER_WASHED_TYPE",  schema = "product")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterWashedType.findAll", query = "SELECT m FROM MasterWashedType m")})
public class MasterWashedType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_WASHED_TYPE_ID")
    private Integer pkWashedTypeId;
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
    @Size(max = 200)
    @Column(name = "WASHED_NAME")
    private String washedName;
    @Size(max = 20)
    @Column(name = "WASHED_CODE")
    private String washedCode;
    @Column(name = "FK_STATUS_ID")
    private Integer fkStatusId;
    @OneToMany(mappedBy = "fkWashedTypeId")
    private Collection<MasterGrade> masterGradeCollection;

    public MasterWashedType() {
    }

    public MasterWashedType(Integer pkWashedTypeId) {
        this.pkWashedTypeId = pkWashedTypeId;
    }

    public Integer getPkWashedTypeId() {
        return pkWashedTypeId;
    }

    public void setPkWashedTypeId(Integer pkWashedTypeId) {
        this.pkWashedTypeId = pkWashedTypeId;
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

    public String getWashedName() {
        return washedName;
    }

    public void setWashedName(String washedName) {
        this.washedName = washedName;
    }

    public String getWashedCode() {
        return washedCode;
    }

    public void setWashedCode(String washedCode) {
        this.washedCode = washedCode;
    }

    public Integer getFkStatusId() {
        return fkStatusId;
    }

    public void setFkStatusId(Integer fkStatusId) {
        this.fkStatusId = fkStatusId;
    }

    @XmlTransient
    public Collection<MasterGrade> getMasterGradeCollection() {
        return masterGradeCollection;
    }

    public void setMasterGradeCollection(Collection<MasterGrade> masterGradeCollection) {
        this.masterGradeCollection = masterGradeCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkWashedTypeId != null ? pkWashedTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterWashedType)) {
            return false;
        }
        MasterWashedType other = (MasterWashedType) object;
        if ((this.pkWashedTypeId == null && other.pkWashedTypeId != null) || (this.pkWashedTypeId != null && !this.pkWashedTypeId.equals(other.pkWashedTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.product.domain.MasterWashedType[ pkWashedTypeId=" + pkWashedTypeId + " ]";
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olam.score.forex.domain;

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
@Table(name = "MASTER_TENOR_DURATION_TYPE",  schema = "forex")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterTenorDurationType.findAll", query = "SELECT m FROM MasterTenorDurationType m")})
public class MasterTenorDurationType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_TENOR_DURATION_TYPE_ID")
    private Integer pkTenorDurationTypeId;
    @Size(max = 200)
    @Column(name = "TENOR_DURATION_TYPE_NAME")
    private String tenorDurationTypeName;
    @Size(max = 20)
    @Column(name = "TENOR_DURATION_TYPE_CODE")
    private String tenorDurationTypeCode;
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
    @Size(max = 200)
    @Column(name = "TENOR_DURATION_TYPE_FULLNAME")
    private String tenorDurationTypeFullname;
    @OneToMany(mappedBy = "fkTenorDurationTypeId")
    private Collection<MasterForexForwardTenor> masterForexForwardTenorCollection;

    public MasterTenorDurationType() {
    }

    public MasterTenorDurationType(Integer pkTenorDurationTypeId) {
        this.pkTenorDurationTypeId = pkTenorDurationTypeId;
    }

    public Integer getPkTenorDurationTypeId() {
        return pkTenorDurationTypeId;
    }

    public void setPkTenorDurationTypeId(Integer pkTenorDurationTypeId) {
        this.pkTenorDurationTypeId = pkTenorDurationTypeId;
    }

    public String getTenorDurationTypeName() {
        return tenorDurationTypeName;
    }

    public void setTenorDurationTypeName(String tenorDurationTypeName) {
        this.tenorDurationTypeName = tenorDurationTypeName;
    }

    public String getTenorDurationTypeCode() {
        return tenorDurationTypeCode;
    }

    public void setTenorDurationTypeCode(String tenorDurationTypeCode) {
        this.tenorDurationTypeCode = tenorDurationTypeCode;
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

    public String getTenorDurationTypeFullname() {
        return tenorDurationTypeFullname;
    }

    public void setTenorDurationTypeFullname(String tenorDurationTypeFullname) {
        this.tenorDurationTypeFullname = tenorDurationTypeFullname;
    }

    @XmlTransient
    public Collection<MasterForexForwardTenor> getMasterForexForwardTenorCollection() {
        return masterForexForwardTenorCollection;
    }

    public void setMasterForexForwardTenorCollection(Collection<MasterForexForwardTenor> masterForexForwardTenorCollection) {
        this.masterForexForwardTenorCollection = masterForexForwardTenorCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkTenorDurationTypeId != null ? pkTenorDurationTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterTenorDurationType)) {
            return false;
        }
        MasterTenorDurationType other = (MasterTenorDurationType) object;
        if ((this.pkTenorDurationTypeId == null && other.pkTenorDurationTypeId != null) || (this.pkTenorDurationTypeId != null && !this.pkTenorDurationTypeId.equals(other.pkTenorDurationTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.forex.domain.MasterTenorDurationType[ pkTenorDurationTypeId=" + pkTenorDurationTypeId + " ]";
    }
    
}

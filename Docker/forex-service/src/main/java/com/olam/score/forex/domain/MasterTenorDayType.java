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
@Table(name = "MASTER_TENOR_DAY_TYPE",  schema = "forex")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterTenorDayType.findAll", query = "SELECT m FROM MasterTenorDayType m")})
public class MasterTenorDayType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_TENOR_DAY_TYPE_ID")
    private Integer pkTenorDayTypeId;
    @Size(max = 200)
    @Column(name = "TENOR_DAY_TYPE_NAME")
    private String tenorDayTypeName;
    @Size(max = 20)
    @Column(name = "TENOR_DAY_TYPE_CODE")
    private String tenorDayTypeCode;
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
    @Column(name = "TENOR_DAY_TYPE_FULLNAME")
    private String tenorDayTypeFullname;
    @OneToMany(mappedBy = "fkTenorDayTypeId")
    private Collection<MasterForexForwardTenor> masterForexForwardTenorCollection;

    public MasterTenorDayType() {
    }

    public MasterTenorDayType(Integer pkTenorDayTypeId) {
        this.pkTenorDayTypeId = pkTenorDayTypeId;
    }

    public Integer getPkTenorDayTypeId() {
        return pkTenorDayTypeId;
    }

    public void setPkTenorDayTypeId(Integer pkTenorDayTypeId) {
        this.pkTenorDayTypeId = pkTenorDayTypeId;
    }

    public String getTenorDayTypeName() {
        return tenorDayTypeName;
    }

    public void setTenorDayTypeName(String tenorDayTypeName) {
        this.tenorDayTypeName = tenorDayTypeName;
    }

    public String getTenorDayTypeCode() {
        return tenorDayTypeCode;
    }

    public void setTenorDayTypeCode(String tenorDayTypeCode) {
        this.tenorDayTypeCode = tenorDayTypeCode;
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

    public String getTenorDayTypeFullname() {
        return tenorDayTypeFullname;
    }

    public void setTenorDayTypeFullname(String tenorDayTypeFullname) {
        this.tenorDayTypeFullname = tenorDayTypeFullname;
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
        hash += (pkTenorDayTypeId != null ? pkTenorDayTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterTenorDayType)) {
            return false;
        }
        MasterTenorDayType other = (MasterTenorDayType) object;
        if ((this.pkTenorDayTypeId == null && other.pkTenorDayTypeId != null) || (this.pkTenorDayTypeId != null && !this.pkTenorDayTypeId.equals(other.pkTenorDayTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.forex.domain.MasterTenorDayType[ pkTenorDayTypeId=" + pkTenorDayTypeId + " ]";
    }
    
}

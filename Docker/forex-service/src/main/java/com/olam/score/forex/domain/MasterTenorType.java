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
 * @author ramachandran_n02
 */
@Entity
@Table(name = "MASTER_TENOR_TYPE",  schema = "forex")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterTenorType.findAll", query = "SELECT m FROM MasterTenorType m")})
public class MasterTenorType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_TENOR_TYPE_ID")
    private Integer pkTenorTypeId;
    @Size(max = 200)
    @Column(name = "TENOR_TYPE_NAME")
    private String tenorTypeName;
    @Size(max = 20)
    @Column(name = "TENOR_TYPE_CODE")
    private String tenorTypeCode;
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
    @Column(name = "TENOR_TYPE_FULLNAME")
    private String tenorTypeFullname;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkTenorTypeId")
    private Collection<MasterForexForwardTenor> masterForexForwardTenorCollection;

    public MasterTenorType() {
    }

    public MasterTenorType(Integer pkTenorTypeId) {
        this.pkTenorTypeId = pkTenorTypeId;
    }

    public Integer getPkTenorTypeId() {
        return pkTenorTypeId;
    }

    public void setPkTenorTypeId(Integer pkTenorTypeId) {
        this.pkTenorTypeId = pkTenorTypeId;
    }

    public String getTenorTypeName() {
        return tenorTypeName;
    }

    public void setTenorTypeName(String tenorTypeName) {
        this.tenorTypeName = tenorTypeName;
    }

    public String getTenorTypeCode() {
        return tenorTypeCode;
    }

    public void setTenorTypeCode(String tenorTypeCode) {
        this.tenorTypeCode = tenorTypeCode;
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

    public String getTenorTypeFullname() {
        return tenorTypeFullname;
    }

    public void setTenorTypeFullname(String tenorTypeFullname) {
        this.tenorTypeFullname = tenorTypeFullname;
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
        hash += (pkTenorTypeId != null ? pkTenorTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterTenorType)) {
            return false;
        }
        MasterTenorType other = (MasterTenorType) object;
        if ((this.pkTenorTypeId == null && other.pkTenorTypeId != null) || (this.pkTenorTypeId != null && !this.pkTenorTypeId.equals(other.pkTenorTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.forex.domain.MasterTenorType[ pkTenorTypeId=" + pkTenorTypeId + " ]";
    }
    
}

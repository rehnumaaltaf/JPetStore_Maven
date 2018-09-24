/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olam.score.location.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "MASTER_ADDRESS_TYPE_REF",  schema = "location")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterAddressTypeRef.findAll", query = "SELECT m FROM MasterAddressTypeRef m")})
public class MasterAddressTypeRef implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_ADDR_TYPE_REF_ID")
    private Integer pkAddrTypeRefId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "ADDR_TYPE_REF_NAME")
    private String addrTypeRefName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "ADDR_TYPE_REF_CODE")
    private String addrTypeRefCode;
    @Size(max = 1000)
    @Column(name = "ADDR_TYPE_REF_DESC")
    private String addrTypeRefDesc;
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

    public MasterAddressTypeRef() {
    }

    public MasterAddressTypeRef(Integer pkAddrTypeRefId) {
        this.pkAddrTypeRefId = pkAddrTypeRefId;
    }

    public MasterAddressTypeRef(Integer pkAddrTypeRefId, String addrTypeRefName, String addrTypeRefCode) {
        this.pkAddrTypeRefId = pkAddrTypeRefId;
        this.addrTypeRefName = addrTypeRefName;
        this.addrTypeRefCode = addrTypeRefCode;
    }

    public Integer getPkAddrTypeRefId() {
        return pkAddrTypeRefId;
    }

    public void setPkAddrTypeRefId(Integer pkAddrTypeRefId) {
        this.pkAddrTypeRefId = pkAddrTypeRefId;
    }

    public String getAddrTypeRefName() {
        return addrTypeRefName;
    }

    public void setAddrTypeRefName(String addrTypeRefName) {
        this.addrTypeRefName = addrTypeRefName;
    }

    public String getAddrTypeRefCode() {
        return addrTypeRefCode;
    }

    public void setAddrTypeRefCode(String addrTypeRefCode) {
        this.addrTypeRefCode = addrTypeRefCode;
    }

    public String getAddrTypeRefDesc() {
        return addrTypeRefDesc;
    }

    public void setAddrTypeRefDesc(String addrTypeRefDesc) {
        this.addrTypeRefDesc = addrTypeRefDesc;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkAddrTypeRefId != null ? pkAddrTypeRefId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterAddressTypeRef)) {
            return false;
        }
        MasterAddressTypeRef other = (MasterAddressTypeRef) object;
        if ((this.pkAddrTypeRefId == null && other.pkAddrTypeRefId != null) || (this.pkAddrTypeRefId != null && !this.pkAddrTypeRefId.equals(other.pkAddrTypeRefId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.location.domain.MasterAddressTypeRef[ pkAddrTypeRefId=" + pkAddrTypeRefId + " ]";
    }
    
}

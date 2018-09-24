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
@Table(name = "MASTER_PROCESS_SUB_TYPE_REF")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterProcessSubTypeRef.findAll", query = "SELECT m FROM MasterProcessSubTypeRef m")})
public class MasterProcessSubTypeRef implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_PROCESS_SUB_TYPE_REF_ID")
    private Integer pkProcessSubTypeRefId;
    @Size(max = 20)
    @Column(name = "PROCESS_SUB_TYPE_CODE")
    private String processSubTypeCode;
    @Size(max = 200)
    @Column(name = "PROCESS_SUB_TYPE_NAME")
    private String processSubTypeName;
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
    @OneToMany(mappedBy = "fkProcessSubTypeRefId")
    private Collection<MasterProductProcessSub> masterProductProcessSubCollection;

    public MasterProcessSubTypeRef() {
    }

    public MasterProcessSubTypeRef(Integer pkProcessSubTypeRefId) {
        this.pkProcessSubTypeRefId = pkProcessSubTypeRefId;
    }

    public Integer getPkProcessSubTypeRefId() {
        return pkProcessSubTypeRefId;
    }

    public void setPkProcessSubTypeRefId(Integer pkProcessSubTypeRefId) {
        this.pkProcessSubTypeRefId = pkProcessSubTypeRefId;
    }

    public String getProcessSubTypeCode() {
        return processSubTypeCode;
    }

    public void setProcessSubTypeCode(String processSubTypeCode) {
        this.processSubTypeCode = processSubTypeCode;
    }

    public String getProcessSubTypeName() {
        return processSubTypeName;
    }

    public void setProcessSubTypeName(String processSubTypeName) {
        this.processSubTypeName = processSubTypeName;
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
    public Collection<MasterProductProcessSub> getMasterProductProcessSubCollection() {
        return masterProductProcessSubCollection;
    }

    public void setMasterProductProcessSubCollection(Collection<MasterProductProcessSub> masterProductProcessSubCollection) {
        this.masterProductProcessSubCollection = masterProductProcessSubCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkProcessSubTypeRefId != null ? pkProcessSubTypeRefId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterProcessSubTypeRef)) {
            return false;
        }
        MasterProcessSubTypeRef other = (MasterProcessSubTypeRef) object;
        if ((this.pkProcessSubTypeRefId == null && other.pkProcessSubTypeRefId != null) || (this.pkProcessSubTypeRefId != null && !this.pkProcessSubTypeRefId.equals(other.pkProcessSubTypeRefId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.product.domain.MasterProcessSubTypeRef[ pkProcessSubTypeRefId=" + pkProcessSubTypeRefId + " ]";
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olam.score.limit.domain;

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
 * @author rashmi.kedlaya
 */
@Entity
@Table(name = "MASTER_LIMIT_BASIS_TYPE_REF")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterLimitBasisTypeRef.findAll", query = "SELECT m FROM MasterLimitBasisTypeRef m")})
public class MasterLimitBasisTypeRef implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_LIMIT_BASIS_TYPE_REF_ID")
    private Integer pkLimitBasisTypeRefId;
    @Size(max = 20)
    @Column(name = "LIMIT_BASIS_TYPE_CODE")
    private String limitBasisTypeCode;
    @Size(max = 200)
    @Column(name = "LIMIT_BASIS_TYPE_NAME")
    private String limitBasisTypeName;
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
    @OneToMany(mappedBy = "fkPrimaryLimitBasisTypeId")
    private Collection<MasterLimit> masterLimitCollection;
    @OneToMany(mappedBy = "fkAdditionalLmtBasisTypeId")
    private Collection<MasterLimit> masterLimitCollection1;

    public MasterLimitBasisTypeRef() {
    }

    public MasterLimitBasisTypeRef(Integer pkLimitBasisTypeRefId) {
        this.pkLimitBasisTypeRefId = pkLimitBasisTypeRefId;
    }

    public Integer getPkLimitBasisTypeRefId() {
        return pkLimitBasisTypeRefId;
    }

    public void setPkLimitBasisTypeRefId(Integer pkLimitBasisTypeRefId) {
        this.pkLimitBasisTypeRefId = pkLimitBasisTypeRefId;
    }

    public String getLimitBasisTypeCode() {
        return limitBasisTypeCode;
    }

    public void setLimitBasisTypeCode(String limitBasisTypeCode) {
        this.limitBasisTypeCode = limitBasisTypeCode;
    }

    public String getLimitBasisTypeName() {
        return limitBasisTypeName;
    }

    public void setLimitBasisTypeName(String limitBasisTypeName) {
        this.limitBasisTypeName = limitBasisTypeName;
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

    @XmlTransient
    public Collection<MasterLimit> getMasterLimitCollection() {
        return masterLimitCollection;
    }

    public void setMasterLimitCollection(Collection<MasterLimit> masterLimitCollection) {
        this.masterLimitCollection = masterLimitCollection;
    }

    @XmlTransient
    public Collection<MasterLimit> getMasterLimitCollection1() {
        return masterLimitCollection1;
    }

    public void setMasterLimitCollection1(Collection<MasterLimit> masterLimitCollection1) {
        this.masterLimitCollection1 = masterLimitCollection1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkLimitBasisTypeRefId != null ? pkLimitBasisTypeRefId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterLimitBasisTypeRef)) {
            return false;
        }
        MasterLimitBasisTypeRef other = (MasterLimitBasisTypeRef) object;
        if ((this.pkLimitBasisTypeRefId == null && other.pkLimitBasisTypeRefId != null) || (this.pkLimitBasisTypeRefId != null && !this.pkLimitBasisTypeRefId.equals(other.pkLimitBasisTypeRefId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.terms.domain.MasterLimitBasisTypeRef[ pkLimitBasisTypeRefId=" + pkLimitBasisTypeRefId + " ]";
    }
    
}

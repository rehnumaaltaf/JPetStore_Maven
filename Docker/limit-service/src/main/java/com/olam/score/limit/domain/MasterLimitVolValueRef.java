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
@Table(name = "MASTER_LIMIT_VOL_VALUE_REF")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterLimitVolValueRef.findAll", query = "SELECT m FROM MasterLimitVolValueRef m")})
public class MasterLimitVolValueRef implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_LIMIT_VOL_VALUE_REF_ID")
    private Integer pkLimitVolValueRefId;
    @Size(max = 20)
    @Column(name = "LIMIT_VOL_VALUE_CODE")
    private String limitVolValueCode;
    @Size(max = 200)
    @Column(name = "LIMIT_VOL_VALUE_NAME")
    private String limitVolValueName;
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
    @OneToMany(mappedBy = "fkLimitVolValueRefId")
    private Collection<MasterLimitDetailAttribute> masterLimitDetailAttributeCollection;

    public MasterLimitVolValueRef() {
    }

    public MasterLimitVolValueRef(Integer pkLimitVolValueRefId) {
        this.pkLimitVolValueRefId = pkLimitVolValueRefId;
    }

    public Integer getPkLimitVolValueRefId() {
        return pkLimitVolValueRefId;
    }

    public void setPkLimitVolValueRefId(Integer pkLimitVolValueRefId) {
        this.pkLimitVolValueRefId = pkLimitVolValueRefId;
    }

    public String getLimitVolValueCode() {
        return limitVolValueCode;
    }

    public void setLimitVolValueCode(String limitVolValueCode) {
        this.limitVolValueCode = limitVolValueCode;
    }

    public String getLimitVolValueName() {
        return limitVolValueName;
    }

    public void setLimitVolValueName(String limitVolValueName) {
        this.limitVolValueName = limitVolValueName;
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
    public Collection<MasterLimitDetailAttribute> getMasterLimitDetailAttributeCollection() {
        return masterLimitDetailAttributeCollection;
    }

    public void setMasterLimitDetailAttributeCollection(Collection<MasterLimitDetailAttribute> masterLimitDetailAttributeCollection) {
        this.masterLimitDetailAttributeCollection = masterLimitDetailAttributeCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkLimitVolValueRefId != null ? pkLimitVolValueRefId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterLimitVolValueRef)) {
            return false;
        }
        MasterLimitVolValueRef other = (MasterLimitVolValueRef) object;
        if ((this.pkLimitVolValueRefId == null && other.pkLimitVolValueRefId != null) || (this.pkLimitVolValueRefId != null && !this.pkLimitVolValueRefId.equals(other.pkLimitVolValueRefId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.terms.domain.MasterLimitVolValueRef[ pkLimitVolValueRefId=" + pkLimitVolValueRefId + " ]";
    }
    
}

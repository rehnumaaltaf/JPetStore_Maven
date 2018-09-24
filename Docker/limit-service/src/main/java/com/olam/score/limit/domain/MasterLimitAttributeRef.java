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
@Table(name = "MASTER_LIMIT_ATTRIBUTE_REF")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterLimitAttributeRef.findAll", query = "SELECT m FROM MasterLimitAttributeRef m")})
public class MasterLimitAttributeRef implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_LIMIT_ATTRIBUTE_REF_ID")
    private Integer pkLimitAttributeRefId;
    @Size(max = 20)
    @Column(name = "LIMIT_ATTRIBUTE_CODE")
    private String limitAttributeCode;
    @Size(max = 200)
    @Column(name = "LIMIT_ATTRIBUTE_NAME")
    private String limitAttributeName;
    @Size(max = 1000)
    @Column(name = "LIMIT_ATTRIBUTE_DESC")
    private String limitAttributeDesc;
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
    @OneToMany(mappedBy = "fkLimitAttributeRefId")
    private Collection<MasterLimitDetailAttribute> masterLimitDetailAttributeCollection;

    public MasterLimitAttributeRef() {
    }

    public MasterLimitAttributeRef(Integer pkLimitAttributeRefId) {
        this.pkLimitAttributeRefId = pkLimitAttributeRefId;
    }

    public Integer getPkLimitAttributeRefId() {
        return pkLimitAttributeRefId;
    }

    public void setPkLimitAttributeRefId(Integer pkLimitAttributeRefId) {
        this.pkLimitAttributeRefId = pkLimitAttributeRefId;
    }

    public String getLimitAttributeCode() {
        return limitAttributeCode;
    }

    public void setLimitAttributeCode(String limitAttributeCode) {
        this.limitAttributeCode = limitAttributeCode;
    }

    public String getLimitAttributeName() {
        return limitAttributeName;
    }

    public void setLimitAttributeName(String limitAttributeName) {
        this.limitAttributeName = limitAttributeName;
    }

    public String getLimitAttributeDesc() {
        return limitAttributeDesc;
    }

    public void setLimitAttributeDesc(String limitAttributeDesc) {
        this.limitAttributeDesc = limitAttributeDesc;
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
    public Collection<MasterLimitDetailAttribute> getMasterLimitDetailAttributeCollection() {
        return masterLimitDetailAttributeCollection;
    }

    public void setMasterLimitDetailAttributeCollection(Collection<MasterLimitDetailAttribute> masterLimitDetailAttributeCollection) {
        this.masterLimitDetailAttributeCollection = masterLimitDetailAttributeCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkLimitAttributeRefId != null ? pkLimitAttributeRefId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterLimitAttributeRef)) {
            return false;
        }
        MasterLimitAttributeRef other = (MasterLimitAttributeRef) object;
        if ((this.pkLimitAttributeRefId == null && other.pkLimitAttributeRefId != null) || (this.pkLimitAttributeRefId != null && !this.pkLimitAttributeRefId.equals(other.pkLimitAttributeRefId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.terms.domain.MasterLimitAttributeRef[ pkLimitAttributeRefId=" + pkLimitAttributeRefId + " ]";
    }
    
}

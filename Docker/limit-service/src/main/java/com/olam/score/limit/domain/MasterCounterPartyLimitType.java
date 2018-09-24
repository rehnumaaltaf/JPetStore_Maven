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
 * @author ramachandran_n02
 */
@Entity
@Table(name = "MASTER_COUNTER_PARTY_LIMIT_TYPE",  schema = "limit")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterCounterPartyLimitType.findAll", query = "SELECT m FROM MasterCounterPartyLimitType m")})
public class MasterCounterPartyLimitType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_COUNTER_PARTY_LIMIT_TYPE_ID")
    private Integer pkCounterPartyLimitTypeId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "COUNTER_PARTY_LIMIT_TYPE_NAME")
    private String counterPartyLimitTypeName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "COUNTER_PARTY_LIMIT_TYPE_CODE")
    private String counterPartyLimitTypeCode;
    @Size(max = 1000)
    @Column(name = "COUNTER_PARTY_LIMIT_TYPE_DESC")
    private String counterPartyLimitTypeDesc;
    @Column(name = "FK_STATUS_ID")
    private Integer fkStatusId;
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
    @OneToMany(mappedBy = "fkCounterPartyLimitTypeId")
    private Collection<MasterCounterPartyLimit> masterCounterPartyLimitCollection;

    public MasterCounterPartyLimitType() {
    }

    public MasterCounterPartyLimitType(Integer pkCounterPartyLimitTypeId) {
        this.pkCounterPartyLimitTypeId = pkCounterPartyLimitTypeId;
    }

    public MasterCounterPartyLimitType(Integer pkCounterPartyLimitTypeId, String counterPartyLimitTypeName, String counterPartyLimitTypeCode) {
        this.pkCounterPartyLimitTypeId = pkCounterPartyLimitTypeId;
        this.counterPartyLimitTypeName = counterPartyLimitTypeName;
        this.counterPartyLimitTypeCode = counterPartyLimitTypeCode;
    }

    public Integer getPkCounterPartyLimitTypeId() {
        return pkCounterPartyLimitTypeId;
    }

    public void setPkCounterPartyLimitTypeId(Integer pkCounterPartyLimitTypeId) {
        this.pkCounterPartyLimitTypeId = pkCounterPartyLimitTypeId;
    }

    public String getCounterPartyLimitTypeName() {
        return counterPartyLimitTypeName;
    }

    public void setCounterPartyLimitTypeName(String counterPartyLimitTypeName) {
        this.counterPartyLimitTypeName = counterPartyLimitTypeName;
    }

    public String getCounterPartyLimitTypeCode() {
        return counterPartyLimitTypeCode;
    }

    public void setCounterPartyLimitTypeCode(String counterPartyLimitTypeCode) {
        this.counterPartyLimitTypeCode = counterPartyLimitTypeCode;
    }

    public String getCounterPartyLimitTypeDesc() {
        return counterPartyLimitTypeDesc;
    }

    public void setCounterPartyLimitTypeDesc(String counterPartyLimitTypeDesc) {
        this.counterPartyLimitTypeDesc = counterPartyLimitTypeDesc;
    }

    public Integer getFkStatusId() {
        return fkStatusId;
    }

    public void setFkStatusId(Integer fkStatusId) {
        this.fkStatusId = fkStatusId;
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
    public Collection<MasterCounterPartyLimit> getMasterCounterPartyLimitCollection() {
        return masterCounterPartyLimitCollection;
    }

    public void setMasterCounterPartyLimitCollection(Collection<MasterCounterPartyLimit> masterCounterPartyLimitCollection) {
        this.masterCounterPartyLimitCollection = masterCounterPartyLimitCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkCounterPartyLimitTypeId != null ? pkCounterPartyLimitTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterCounterPartyLimitType)) {
            return false;
        }
        MasterCounterPartyLimitType other = (MasterCounterPartyLimitType) object;
        if ((this.pkCounterPartyLimitTypeId == null && other.pkCounterPartyLimitTypeId != null) || (this.pkCounterPartyLimitTypeId != null && !this.pkCounterPartyLimitTypeId.equals(other.pkCounterPartyLimitTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.limit.domain.MasterCounterPartyLimitType[ pkCounterPartyLimitTypeId=" + pkCounterPartyLimitTypeId + " ]";
    }
    
}

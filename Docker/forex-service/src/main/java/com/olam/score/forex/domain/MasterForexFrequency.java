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
@Table(name = "MASTER_FOREX_FREQUENCY",  schema = "forex")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterForexFrequency.findAll", query = "SELECT m FROM MasterForexFrequency m")})
public class MasterForexFrequency implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_FOREX_FREQUENCY_ID")
    private Integer pkForexFrequencyId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "FOREX_FREQUENCY_NAME")
    private String forexFrequencyName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "FOREX_FREQUENCY_CODE")
    private String forexFrequencyCode;
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
    @Size(max = 1000)
    @Column(name = "FOREX_FREQUENCY_DESC")
    private String forexFrequencyDesc;
    @OneToMany(mappedBy = "fkForexFrequencyId")
    private Collection<MasterForex> masterForexCollection;

    public MasterForexFrequency() {
    }

    public MasterForexFrequency(Integer pkForexFrequencyId) {
        this.pkForexFrequencyId = pkForexFrequencyId;
    }

    public MasterForexFrequency(Integer pkForexFrequencyId, String forexFrequencyName, String forexFrequencyCode) {
        this.pkForexFrequencyId = pkForexFrequencyId;
        this.forexFrequencyName = forexFrequencyName;
        this.forexFrequencyCode = forexFrequencyCode;
    }

    public Integer getPkForexFrequencyId() {
        return pkForexFrequencyId;
    }

    public void setPkForexFrequencyId(Integer pkForexFrequencyId) {
        this.pkForexFrequencyId = pkForexFrequencyId;
    }

    public String getForexFrequencyName() {
        return forexFrequencyName;
    }

    public void setForexFrequencyName(String forexFrequencyName) {
        this.forexFrequencyName = forexFrequencyName;
    }

    public String getForexFrequencyCode() {
        return forexFrequencyCode;
    }

    public void setForexFrequencyCode(String forexFrequencyCode) {
        this.forexFrequencyCode = forexFrequencyCode;
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

    public String getForexFrequencyDesc() {
        return forexFrequencyDesc;
    }

    public void setForexFrequencyDesc(String forexFrequencyDesc) {
        this.forexFrequencyDesc = forexFrequencyDesc;
    }

    @XmlTransient
    public Collection<MasterForex> getMasterForexCollection() {
        return masterForexCollection;
    }

    public void setMasterForexCollection(Collection<MasterForex> masterForexCollection) {
        this.masterForexCollection = masterForexCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkForexFrequencyId != null ? pkForexFrequencyId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterForexFrequency)) {
            return false;
        }
        MasterForexFrequency other = (MasterForexFrequency) object;
        if ((this.pkForexFrequencyId == null && other.pkForexFrequencyId != null) || (this.pkForexFrequencyId != null && !this.pkForexFrequencyId.equals(other.pkForexFrequencyId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.forex.domain.MasterForexFrequency[ pkForexFrequencyId=" + pkForexFrequencyId + " ]";
    }
    
}

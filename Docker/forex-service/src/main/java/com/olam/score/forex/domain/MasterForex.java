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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
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
@Table(name = "MASTER_FOREX",  schema = "forex")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterForex.findAll", query = "SELECT m FROM MasterForex m")})
public class MasterForex implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_FOREX_ID")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="FOREX_ID_SEQ")
   	@SequenceGenerator(name="FOREX_ID_SEQ", sequenceName="forex.FOREX_ID_SEQ",initialValue=1,allocationSize=1)
    private Integer pkForexId;
    @Size(max = 1000)
    @Column(name = "FOREX_DESC")
    private String forexDesc;
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
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "FOREX_NAME")
    private String forexName;
    @Size(max = 20)
    @Column(name = "FOREX_CODE")
    private String forexCode;
    @Column(name = "FK_BASE_CURRENCY_ID")
    private Integer fkBaseCurrencyId;
    @Column(name = "FK_COUNTER_CURRENCY_ID")
    private Integer fkCounterCurrencyId;
    @OneToMany(mappedBy = "fkForexId")
    private Collection<MasterForexForwardTenor> masterForexForwardTenorCollection;
    @JoinColumn(name = "FK_FOREX_FREQUENCY_ID", referencedColumnName = "PK_FOREX_FREQUENCY_ID")
    @ManyToOne
    private MasterForexFrequency fkForexFrequencyId;

    public MasterForex() {
    }

    public MasterForex(Integer pkForexId) {
        this.pkForexId = pkForexId;
    }

    public MasterForex(Integer pkForexId, String forexName) {
        this.pkForexId = pkForexId;
        this.forexName = forexName;
    }

    public Integer getPkForexId() {
        return pkForexId;
    }

    public void setPkForexId(Integer pkForexId) {
        this.pkForexId = pkForexId;
    }

    public String getForexDesc() {
        return forexDesc;
    }

    public void setForexDesc(String forexDesc) {
        this.forexDesc = forexDesc;
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

    public String getForexName() {
        return forexName;
    }

    public void setForexName(String forexName) {
        this.forexName = forexName;
    }

    public String getForexCode() {
        return forexCode;
    }

    public void setForexCode(String forexCode) {
        this.forexCode = forexCode;
    }

    public Integer getFkBaseCurrencyId() {
        return fkBaseCurrencyId;
    }

    public void setFkBaseCurrencyId(Integer fkBaseCurrencyId) {
        this.fkBaseCurrencyId = fkBaseCurrencyId;
    }

    public Integer getFkCounterCurrencyId() {
        return fkCounterCurrencyId;
    }

    public void setFkCounterCurrencyId(Integer fkCounterCurrencyId) {
        this.fkCounterCurrencyId = fkCounterCurrencyId;
    }

    @XmlTransient
    public Collection<MasterForexForwardTenor> getMasterForexForwardTenorCollection() {
        return masterForexForwardTenorCollection;
    }

    public void setMasterForexForwardTenorCollection(Collection<MasterForexForwardTenor> masterForexForwardTenorCollection) {
        this.masterForexForwardTenorCollection = masterForexForwardTenorCollection;
    }

    public MasterForexFrequency getFkForexFrequencyId() {
        return fkForexFrequencyId;
    }

    public void setFkForexFrequencyId(MasterForexFrequency fkForexFrequencyId) {
        this.fkForexFrequencyId = fkForexFrequencyId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkForexId != null ? pkForexId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterForex)) {
            return false;
        }
        MasterForex other = (MasterForex) object;
        if ((this.pkForexId == null && other.pkForexId != null) || (this.pkForexId != null && !this.pkForexId.equals(other.pkForexId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.forex.domain.MasterForex[ pkForexId=" + pkForexId + " ]";
    }
    
}

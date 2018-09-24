/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olam.score.reference.domain;

import java.io.Serializable;
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
import javax.persistence.SequenceGenerator;
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
@Table(name = "MASTER_CURRENCY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterCurrency.findAll", query = "SELECT m FROM MasterCurrency m")})
public class MasterCurrency implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_CURRENCY_ID")
    @SequenceGenerator(name="MASTER_CURRENCY_PKCURRENCYID_GENERATOR", sequenceName="CURRENCY_ID_SEQ",allocationSize = 1)
   	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MASTER_CURRENCY_PKCURRENCYID_GENERATOR")
    private Integer pkCurrencyId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "CURRENCY_NAME")
    private String currencyName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "CURRENCY_CODE")
    private String currencyCode;
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
    @Size(max = 1000)
    @Column(name = "CURRENCY_DESC")
    private String currencyDesc;
    @Size(max = 20)
    @Column(name = "CURRENCY_SYMBOL")
    private String currencySymbol;
    @Column(name = "FK_STATUS_ID")
    private Integer fkStatusId;

    public MasterCurrency() {
    }

    public MasterCurrency(Integer pkCurrencyId) {
        this.pkCurrencyId = pkCurrencyId;
    }

    public MasterCurrency(Integer pkCurrencyId, String currencyName, String currencyCode) {
        this.pkCurrencyId = pkCurrencyId;
        this.currencyName = currencyName;
        this.currencyCode = currencyCode;
    }

    public Integer getPkCurrencyId() {
        return pkCurrencyId;
    }

    public void setPkCurrencyId(Integer pkCurrencyId) {
        this.pkCurrencyId = pkCurrencyId;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
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

    public String getCurrencyDesc() {
        return currencyDesc;
    }

    public void setCurrencyDesc(String currencyDesc) {
        this.currencyDesc = currencyDesc;
    }

    public String getCurrencySymbol() {
        return currencySymbol;
    }

    public void setCurrencySymbol(String currencySymbol) {
        this.currencySymbol = currencySymbol;
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
        hash += (pkCurrencyId != null ? pkCurrencyId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterCurrency)) {
            return false;
        }
        MasterCurrency other = (MasterCurrency) object;
        if ((this.pkCurrencyId == null && other.pkCurrencyId != null) || (this.pkCurrencyId != null && !this.pkCurrencyId.equals(other.pkCurrencyId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.reference.domain.MasterCurrency[ pkCurrencyId=" + pkCurrencyId + " ]";
    }
    
}

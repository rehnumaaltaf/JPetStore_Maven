/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olam.score.terms.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
 * @author Ramachandran_N02
 */
@Entity
@Table(name = "MASTER_PAYMENT_TERM_FLAG")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterPaymentTermFlag.findAll", query = "SELECT m FROM MasterPaymentTermFlag m")})
public class MasterPaymentTermFlag implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MASTER_PAYMENT_TERM_FLAG_GENERATOR")
	@SequenceGenerator(name="MASTER_PAYMENT_TERM_FLAG_GENERATOR", sequenceName="terms.PAY_TERM_ID_SEQ",allocationSize=1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_PAYMENT_TERM_FLAG_ID")
    private Integer pkPaymentTermFlagId;
    @Size(max = 20)
    @Column(name = "FLAG_CODE")
    private String flagCode;
    @Size(max = 50)
    @Column(name = "FLAG_TYPE")
    private String flagType;
    @Size(max = 200)
    @Column(name = "FLAG_NAME")
    private String flagName;
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
    @OneToMany(mappedBy = "atSightUsanceFlagId")
    private Collection<MasterPaymentTerm> masterPaymentTermCollection;
    @OneToMany(mappedBy = "revocableIrrevocableFlagId")
    private Collection<MasterPaymentTerm> masterPaymentTermCollection1;
    @OneToMany(mappedBy = "confirmedNonConfirmedFlagId")
    private Collection<MasterPaymentTerm> masterPaymentTermCollection2;

    public MasterPaymentTermFlag() {
    }

    public MasterPaymentTermFlag(Integer pkPaymentTermFlagId) {
        this.pkPaymentTermFlagId = pkPaymentTermFlagId;
    }

    public Integer getPkPaymentTermFlagId() {
        return pkPaymentTermFlagId;
    }

    public void setPkPaymentTermFlagId(Integer pkPaymentTermFlagId) {
        this.pkPaymentTermFlagId = pkPaymentTermFlagId;
    }

    public String getFlagCode() {
        return flagCode;
    }

    public void setFlagCode(String flagCode) {
        this.flagCode = flagCode;
    }

    public String getFlagType() {
        return flagType;
    }

    public void setFlagType(String flagType) {
        this.flagType = flagType;
    }

    public String getFlagName() {
        return flagName;
    }

    public void setFlagName(String flagName) {
        this.flagName = flagName;
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
    public Collection<MasterPaymentTerm> getMasterPaymentTermCollection() {
        return masterPaymentTermCollection;
    }

    public void setMasterPaymentTermCollection(Collection<MasterPaymentTerm> masterPaymentTermCollection) {
        this.masterPaymentTermCollection = masterPaymentTermCollection;
    }

    @XmlTransient
    public Collection<MasterPaymentTerm> getMasterPaymentTermCollection1() {
        return masterPaymentTermCollection1;
    }

    public void setMasterPaymentTermCollection1(Collection<MasterPaymentTerm> masterPaymentTermCollection1) {
        this.masterPaymentTermCollection1 = masterPaymentTermCollection1;
    }

    @XmlTransient
    public Collection<MasterPaymentTerm> getMasterPaymentTermCollection2() {
        return masterPaymentTermCollection2;
    }

    public void setMasterPaymentTermCollection2(Collection<MasterPaymentTerm> masterPaymentTermCollection2) {
        this.masterPaymentTermCollection2 = masterPaymentTermCollection2;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkPaymentTermFlagId != null ? pkPaymentTermFlagId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterPaymentTermFlag)) {
            return false;
        }
        MasterPaymentTermFlag other = (MasterPaymentTermFlag) object;
        if ((this.pkPaymentTermFlagId == null && other.pkPaymentTermFlagId != null) || (this.pkPaymentTermFlagId != null && !this.pkPaymentTermFlagId.equals(other.pkPaymentTermFlagId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.terms.domain.MasterPaymentTermFlag[ pkPaymentTermFlagId=" + pkPaymentTermFlagId + " ]";
    }
    
}

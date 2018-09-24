/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olam.score.party.domain;

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

/**
 *
 * @author Ramachandran_N02
 */
@Entity
@Table(name = "MASTER_ACCOUNT_TYPE")
@NamedQueries({
    @NamedQuery(name = "MasterAccountType.findAll", query = "SELECT m FROM MasterAccountType m")})
public class MasterAccountType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_ACCOUNT_TYPE_ID")
    private Integer pkAccountTypeId;
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
    @Size(max = 200)
    @Column(name = "ACCOUNT_TYPE_NAME")
    private String accountTypeName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "ACCOUNT_TYPE_CODE")
    private String accountTypeCode;
    @Column(name = "FK_STATUS_ID")
    private Integer fkStatusId;
    @OneToMany(mappedBy = "fkAccountTypeId")
    private Collection<MasterPartyBank> masterPartyBankCollection;

    public MasterAccountType() {
    }

    public MasterAccountType(Integer pkAccountTypeId) {
        this.pkAccountTypeId = pkAccountTypeId;
    }

    public MasterAccountType(Integer pkAccountTypeId, String accountTypeCode) {
        this.pkAccountTypeId = pkAccountTypeId;
        this.accountTypeCode = accountTypeCode;
    }

    public Integer getPkAccountTypeId() {
        return pkAccountTypeId;
    }

    public void setPkAccountTypeId(Integer pkAccountTypeId) {
        this.pkAccountTypeId = pkAccountTypeId;
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

    public String getAccountTypeName() {
        return accountTypeName;
    }

    public void setAccountTypeName(String accountTypeName) {
        this.accountTypeName = accountTypeName;
    }

    public String getAccountTypeCode() {
        return accountTypeCode;
    }

    public void setAccountTypeCode(String accountTypeCode) {
        this.accountTypeCode = accountTypeCode;
    }

    public Integer getFkStatusId() {
        return fkStatusId;
    }

    public void setFkStatusId(Integer fkStatusId) {
        this.fkStatusId = fkStatusId;
    }

    public Collection<MasterPartyBank> getMasterPartyBankCollection() {
        return masterPartyBankCollection;
    }

    public void setMasterPartyBankCollection(Collection<MasterPartyBank> masterPartyBankCollection) {
        this.masterPartyBankCollection = masterPartyBankCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkAccountTypeId != null ? pkAccountTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterAccountType)) {
            return false;
        }
        MasterAccountType other = (MasterAccountType) object;
        if ((this.pkAccountTypeId == null && other.pkAccountTypeId != null) || (this.pkAccountTypeId != null && !this.pkAccountTypeId.equals(other.pkAccountTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.party.domain.MasterAccountType[ pkAccountTypeId=" + pkAccountTypeId + " ]";
    }
    
}

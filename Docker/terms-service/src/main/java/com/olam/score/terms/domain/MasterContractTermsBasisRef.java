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
 * @author rashmi.kedlaya
 */
@Entity
@Table(name = "MASTER_CONTRACT_TERMS_BASIS_REF")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterContractTermsBasisRef.findAll", query = "SELECT m FROM MasterContractTermsBasisRef m")})
public class MasterContractTermsBasisRef implements Serializable {

    private static final long serialVersionUID = 1L;
    @SequenceGenerator(name="MASTER_CONTRACT_TERMS_BASIS_REF_CONTRACTTERMSBASISREFIDSEQ_GENERATOR", sequenceName="CONTRACT_TERMS_BASIS_REF_ID_SEQ",allocationSize=1)
	@GeneratedValue(generator="MASTER_CONTRACT_TERMS_BASIS_REF_CONTRACTTERMSBASISREFIDSEQ_GENERATOR")
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_CONTRACT_TERMS_BASIS_REF_ID")
    private Integer pkContractTermsBasisRefId;
    @Size(max = 20)
    @Column(name = "CONTRACT_TERMS_BASIS_CODE")
    private String contractTermsBasisCode;
    @Size(max = 200)
    @Column(name = "CONTRACT_TERMS_BASIS_NAME")
    private String contractTermsBasisName;
    @Size(max = 1000)
    @Column(name = "CONTRACT_TERMS_BASIS_DESC")
    private String contractTermsBasisDesc;
    @Column(name = "FK_STATUS_ID")
    private Integer fkStatusId;
    @Size(max = 100)
    @Column(name = "CREATED_BY", updatable=false)
    private String createdBy;
    @Column(name = "CREATED_DATE", updatable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Size(max = 100)
    @Column(name = "MODIFIED_BY")
    private String modifiedBy;
    @Column(name = "MODIFIED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;
    @OneToMany(mappedBy = "fkContractTermsBasisRefId")
    private Collection<MasterContractTerms> masterContractTermsCollection;

    public MasterContractTermsBasisRef() {
    }

    public MasterContractTermsBasisRef(Integer pkContractTermsBasisRefId) {
        this.pkContractTermsBasisRefId = pkContractTermsBasisRefId;
    }

    public Integer getPkContractTermsBasisRefId() {
        return pkContractTermsBasisRefId;
    }

    public void setPkContractTermsBasisRefId(Integer pkContractTermsBasisRefId) {
        this.pkContractTermsBasisRefId = pkContractTermsBasisRefId;
    }

    public String getContractTermsBasisCode() {
        return contractTermsBasisCode;
    }

    public void setContractTermsBasisCode(String contractTermsBasisCode) {
        this.contractTermsBasisCode = contractTermsBasisCode;
    }

    public String getContractTermsBasisName() {
        return contractTermsBasisName;
    }

    public void setContractTermsBasisName(String contractTermsBasisName) {
        this.contractTermsBasisName = contractTermsBasisName;
    }

    public String getContractTermsBasisDesc() {
        return contractTermsBasisDesc;
    }

    public void setContractTermsBasisDesc(String contractTermsBasisDesc) {
        this.contractTermsBasisDesc = contractTermsBasisDesc;
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
    public Collection<MasterContractTerms> getMasterContractTermsCollection() {
        return masterContractTermsCollection;
    }

    public void setMasterContractTermsCollection(Collection<MasterContractTerms> masterContractTermsCollection) {
        this.masterContractTermsCollection = masterContractTermsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkContractTermsBasisRefId != null ? pkContractTermsBasisRefId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterContractTermsBasisRef)) {
            return false;
        }
        MasterContractTermsBasisRef other = (MasterContractTermsBasisRef) object;
        if ((this.pkContractTermsBasisRefId == null && other.pkContractTermsBasisRefId != null) || (this.pkContractTermsBasisRefId != null && !this.pkContractTermsBasisRefId.equals(other.pkContractTermsBasisRefId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.terms.domain.MasterContractTermsBasisRef[ pkContractTermsBasisRefId=" + pkContractTermsBasisRefId + " ]";
    }
    
}

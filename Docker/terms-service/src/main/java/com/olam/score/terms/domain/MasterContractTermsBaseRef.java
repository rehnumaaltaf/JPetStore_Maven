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
@Table(name = "MASTER_CONTRACT_TERMS_BASE_REF")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterContractTermsBaseRef.findAll", query = "SELECT m FROM MasterContractTermsBaseRef m")})
public class MasterContractTermsBaseRef implements Serializable {

    private static final long serialVersionUID = 1L;
    @SequenceGenerator(name="MASTER_CONTRACT_TERMS_BASE_REF_CONTRACTTERMSBASEREFIDSEQ_GENERATOR", sequenceName="CONTRACT_TERMS_BASE_REF_ID_SEQ",allocationSize=1)
	@GeneratedValue(generator="MASTER_CONTRACT_TERMS_BASE_REF_CONTRACTTERMSBASEREFIDSEQ_GENERATOR")
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_CONTRACT_TERMS_BASE_REF_ID")
    private Integer pkContractTermsBaseRefId;
    @Size(max = 20)
    @Column(name = "CONTRACT_TERMS_BASE_CODE")
    private String contractTermsBaseCode;
    @Size(max = 200)
    @Column(name = "CONTRACT_TERMS_BASE_NAME")
    private String contractTermsBaseName;
    @Size(max = 1000)
    @Column(name = "CONTRACT_TERMS_BASE_DESC")
    private String contractTermsBaseDesc;
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
    @OneToMany(mappedBy = "fkContractTermsBaseRefId")
    private Collection<MasterContractTerms> masterContractTermsCollection;

    public MasterContractTermsBaseRef() {
    }

    public MasterContractTermsBaseRef(Integer pkContractTermsBaseRefId) {
        this.pkContractTermsBaseRefId = pkContractTermsBaseRefId;
    }

    public Integer getPkContractTermsBaseRefId() {
        return pkContractTermsBaseRefId;
    }

    public void setPkContractTermsBaseRefId(Integer pkContractTermsBaseRefId) {
        this.pkContractTermsBaseRefId = pkContractTermsBaseRefId;
    }

    public String getContractTermsBaseCode() {
        return contractTermsBaseCode;
    }

    public void setContractTermsBaseCode(String contractTermsBaseCode) {
        this.contractTermsBaseCode = contractTermsBaseCode;
    }

    public String getContractTermsBaseName() {
        return contractTermsBaseName;
    }

    public void setContractTermsBaseName(String contractTermsBaseName) {
        this.contractTermsBaseName = contractTermsBaseName;
    }

    public String getContractTermsBaseDesc() {
        return contractTermsBaseDesc;
    }

    public void setContractTermsBaseDesc(String contractTermsBaseDesc) {
        this.contractTermsBaseDesc = contractTermsBaseDesc;
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
        hash += (pkContractTermsBaseRefId != null ? pkContractTermsBaseRefId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterContractTermsBaseRef)) {
            return false;
        }
        MasterContractTermsBaseRef other = (MasterContractTermsBaseRef) object;
        if ((this.pkContractTermsBaseRefId == null && other.pkContractTermsBaseRefId != null) || (this.pkContractTermsBaseRefId != null && !this.pkContractTermsBaseRefId.equals(other.pkContractTermsBaseRefId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.terms.domain.MasterContractTermsBaseRef[ pkContractTermsBaseRefId=" + pkContractTermsBaseRefId + " ]";
    }
    
}

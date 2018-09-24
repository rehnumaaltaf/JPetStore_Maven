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
@Table(name = "MASTER_CONTR_TRMS_ADDREDUCE_REF")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterContrTrmsAddreduceRef.findAll", query = "SELECT m FROM MasterContrTrmsAddreduceRef m")})
public class MasterContrTrmsAddreduceRef implements Serializable {

    private static final long serialVersionUID = 1L;
    @SequenceGenerator(name="MASTER_CONTR_TRMS_ADDREDUCE_CONTRTRMSADDREDUCEREFIDSEQ_GENERATOR", sequenceName="CONTR_TRMS_ADDREDUCE_REF_ID_SEQ",allocationSize=1)
	@GeneratedValue(generator="MASTER_CONTR_TRMS_ADDREDUCE_CONTRTRMSADDREDUCEREFIDSEQ_GENERATOR")
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_CONTR_TRMS_ADDREDUCE_REF_ID")
    private Integer pkContrTrmsAddreduceRefId;
    @Size(max = 20)
    @Column(name = "ADD_REDUCE_CODE")
    private String addReduceCode;
    @Size(max = 200)
    @Column(name = "ADD_REDUCE_NAME")
    private String addReduceName;
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
    @OneToMany(mappedBy = "fkContrTrmsAddreduceRefId")
    private Collection<MasterContractTermsSales> masterContractTermsSalesCollection;
    @OneToMany(mappedBy = "fkContrTrmsAddreduceRefId")
    private Collection<MasterContractTermsPurchase> masterContractTermsPurchaseCollection;

    public MasterContrTrmsAddreduceRef() {
    }

    public MasterContrTrmsAddreduceRef(Integer pkContrTrmsAddreduceRefId) {
        this.pkContrTrmsAddreduceRefId = pkContrTrmsAddreduceRefId;
    }

    public Integer getPkContrTrmsAddreduceRefId() {
        return pkContrTrmsAddreduceRefId;
    }

    public void setPkContrTrmsAddreduceRefId(Integer pkContrTrmsAddreduceRefId) {
        this.pkContrTrmsAddreduceRefId = pkContrTrmsAddreduceRefId;
    }

    public String getAddReduceCode() {
        return addReduceCode;
    }

    public void setAddReduceCode(String addReduceCode) {
        this.addReduceCode = addReduceCode;
    }

    public String getAddReduceName() {
        return addReduceName;
    }

    public void setAddReduceName(String addReduceName) {
        this.addReduceName = addReduceName;
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
    public Collection<MasterContractTermsSales> getMasterContractTermsSalesCollection() {
        return masterContractTermsSalesCollection;
    }

    public void setMasterContractTermsSalesCollection(Collection<MasterContractTermsSales> masterContractTermsSalesCollection) {
        this.masterContractTermsSalesCollection = masterContractTermsSalesCollection;
    }

    @XmlTransient
    public Collection<MasterContractTermsPurchase> getMasterContractTermsPurchaseCollection() {
        return masterContractTermsPurchaseCollection;
    }

    public void setMasterContractTermsPurchaseCollection(Collection<MasterContractTermsPurchase> masterContractTermsPurchaseCollection) {
        this.masterContractTermsPurchaseCollection = masterContractTermsPurchaseCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkContrTrmsAddreduceRefId != null ? pkContrTrmsAddreduceRefId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterContrTrmsAddreduceRef)) {
            return false;
        }
        MasterContrTrmsAddreduceRef other = (MasterContrTrmsAddreduceRef) object;
        if ((this.pkContrTrmsAddreduceRefId == null && other.pkContrTrmsAddreduceRefId != null) || (this.pkContrTrmsAddreduceRefId != null && !this.pkContrTrmsAddreduceRefId.equals(other.pkContrTrmsAddreduceRefId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.terms.domain.MasterContrTrmsAddreduceRef[ pkContrTrmsAddreduceRefId=" + pkContrTrmsAddreduceRefId + " ]";
    }
    
}

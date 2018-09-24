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
@Table(schema="terms",name = "MASTER_PAYMENT_BASIS_REF")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterPaymentBasisRef.findAll", query = "SELECT m FROM MasterPaymentBasisRef m")})
public class MasterPaymentBasisRef implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MASTER_PAYMENT_BASIS_REF_GENERATOR")
	@SequenceGenerator(name="MASTER_PAYMENT_BASIS_REF_GENERATOR", sequenceName="terms.PAY_TERM_ID_SEQ",allocationSize=1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_PAYMENT_BASIS_REF_ID")
    private Integer pkPaymentBasisRefId;
    @Size(max = 20)
    @Column(name = "PAYMENT_BASIS_CODE")
    private String paymentBasisCode;
    @Size(max = 200)
    @Column(name = "PAYMENT_BASIS_NAME")
    private String paymentBasisName;
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
    @OneToMany(mappedBy = "fkPaymentBasisRefId")
    private Collection<MasterPaymentTerm> masterPaymentTermCollection;

    public MasterPaymentBasisRef() {
    }

    public MasterPaymentBasisRef(Integer pkPaymentBasisRefId) {
        this.pkPaymentBasisRefId = pkPaymentBasisRefId;
    }

    public Integer getPkPaymentBasisRefId() {
        return pkPaymentBasisRefId;
    }

    public void setPkPaymentBasisRefId(Integer pkPaymentBasisRefId) {
        this.pkPaymentBasisRefId = pkPaymentBasisRefId;
    }

    public String getPaymentBasisCode() {
        return paymentBasisCode;
    }

    public void setPaymentBasisCode(String paymentBasisCode) {
        this.paymentBasisCode = paymentBasisCode;
    }

    public String getPaymentBasisName() {
        return paymentBasisName;
    }

    public void setPaymentBasisName(String paymentBasisName) {
        this.paymentBasisName = paymentBasisName;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkPaymentBasisRefId != null ? pkPaymentBasisRefId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterPaymentBasisRef)) {
            return false;
        }
        MasterPaymentBasisRef other = (MasterPaymentBasisRef) object;
        if ((this.pkPaymentBasisRefId == null && other.pkPaymentBasisRefId != null) || (this.pkPaymentBasisRefId != null && !this.pkPaymentBasisRefId.equals(other.pkPaymentBasisRefId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.terms.domain.MasterPaymentBasisRef[ pkPaymentBasisRefId=" + pkPaymentBasisRefId + " ]";
    }
    
}

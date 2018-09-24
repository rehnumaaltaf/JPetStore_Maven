/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olam.score.reference.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "MASTER_PAYMENT_NEGOTIATION_TYPE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterPaymentNegotiationType.findAll", query = "SELECT m FROM MasterPaymentNegotiationType m")})
public class MasterPaymentNegotiationType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_PAY_NEGO_TYPE_ID")
    private Integer pkPayNegoTypeId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "PAY_NEGO_TYPE_CODE")
    private String payNegoTypeCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "PAY_NEGO_TYPE_NAME")
    private String payNegoTypeName;
    @Size(max = 1000)
    @Column(name = "PAY_NEGO_TYPE_DESC")
    private String payNegoTypeDesc;
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
    @OneToMany(mappedBy = "fkPayNegoTypeId")
    private Collection<MasterPaymentNegotiation> masterPaymentNegotiationCollection;
    @Column(name = "FK_STATUS_ID")
    private int fkStatusId;

    public MasterPaymentNegotiationType() {
    }

    public MasterPaymentNegotiationType(Integer pkPayNegoTypeId) {
        this.pkPayNegoTypeId = pkPayNegoTypeId;
    }

    public MasterPaymentNegotiationType(Integer pkPayNegoTypeId, String payNegoTypeCode, String payNegoTypeName) {
        this.pkPayNegoTypeId = pkPayNegoTypeId;
        this.payNegoTypeCode = payNegoTypeCode;
        this.payNegoTypeName = payNegoTypeName;
    }

    public Integer getPkPayNegoTypeId() {
        return pkPayNegoTypeId;
    }

    public void setPkPayNegoTypeId(Integer pkPayNegoTypeId) {
        this.pkPayNegoTypeId = pkPayNegoTypeId;
    }

    public String getPayNegoTypeCode() {
        return payNegoTypeCode;
    }

    public void setPayNegoTypeCode(String payNegoTypeCode) {
        this.payNegoTypeCode = payNegoTypeCode;
    }

    public String getPayNegoTypeName() {
        return payNegoTypeName;
    }

    public void setPayNegoTypeName(String payNegoTypeName) {
        this.payNegoTypeName = payNegoTypeName;
    }

    public String getPayNegoTypeDesc() {
        return payNegoTypeDesc;
    }

    public void setPayNegoTypeDesc(String payNegoTypeDesc) {
        this.payNegoTypeDesc = payNegoTypeDesc;
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
    public Collection<MasterPaymentNegotiation> getMasterPaymentNegotiationCollection() {
        return masterPaymentNegotiationCollection;
    }

    public void setMasterPaymentNegotiationCollection(Collection<MasterPaymentNegotiation> masterPaymentNegotiationCollection) {
        this.masterPaymentNegotiationCollection = masterPaymentNegotiationCollection;
    }

    public int getFkStatusId() {
        return fkStatusId;
    }

    public void setFkStatusId(int fkStatusId) {
        this.fkStatusId = fkStatusId;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkPayNegoTypeId != null ? pkPayNegoTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterPaymentNegotiationType)) {
            return false;
        }
        MasterPaymentNegotiationType other = (MasterPaymentNegotiationType) object;
        if ((this.pkPayNegoTypeId == null && other.pkPayNegoTypeId != null) || (this.pkPayNegoTypeId != null && !this.pkPayNegoTypeId.equals(other.pkPayNegoTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.reference.domain.MasterPaymentNegotiationType[ pkPayNegoTypeId=" + pkPayNegoTypeId + " ]";
    }
    
}

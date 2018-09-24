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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "MASTER_PAYMENT_NEGOTIATION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterPaymentNegotiation.findAll", query = "SELECT m FROM MasterPaymentNegotiation m")})
public class MasterPaymentNegotiation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_PAY_NEGO_ID")
    private Integer pkPayNegoId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "PAY_NEGO_CODE")
    private String payNegoCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "PAY_NEGO_NAME")
    private String payNegoName;
    @Size(max = 1000)
    @Column(name = "PAY_NEGO_DESC")
    private String payNegoDesc;
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
    @JoinColumn(name = "FK_PAY_NEGO_TYPE_ID", referencedColumnName = "PK_PAY_NEGO_TYPE_ID")
    @ManyToOne
    private MasterPaymentNegotiationType fkPayNegoTypeId;
    @Column(name = "FK_STATUS_ID")
   private int fkStatusId;

    public MasterPaymentNegotiation() {
    }

    public MasterPaymentNegotiation(Integer pkPayNegoId) {
        this.pkPayNegoId = pkPayNegoId;
    }

    public MasterPaymentNegotiation(Integer pkPayNegoId, String payNegoCode, String payNegoName) {
        this.pkPayNegoId = pkPayNegoId;
        this.payNegoCode = payNegoCode;
        this.payNegoName = payNegoName;
    }

    public Integer getPkPayNegoId() {
        return pkPayNegoId;
    }

    public void setPkPayNegoId(Integer pkPayNegoId) {
        this.pkPayNegoId = pkPayNegoId;
    }

    public String getPayNegoCode() {
        return payNegoCode;
    }

    public void setPayNegoCode(String payNegoCode) {
        this.payNegoCode = payNegoCode;
    }

    public String getPayNegoName() {
        return payNegoName;
    }

    public void setPayNegoName(String payNegoName) {
        this.payNegoName = payNegoName;
    }

    public String getPayNegoDesc() {
        return payNegoDesc;
    }

    public void setPayNegoDesc(String payNegoDesc) {
        this.payNegoDesc = payNegoDesc;
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

    public MasterPaymentNegotiationType getFkPayNegoTypeId() {
        return fkPayNegoTypeId;
    }

    public void setFkPayNegoTypeId(MasterPaymentNegotiationType fkPayNegoTypeId) {
        this.fkPayNegoTypeId = fkPayNegoTypeId;
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
        hash += (pkPayNegoId != null ? pkPayNegoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterPaymentNegotiation)) {
            return false;
        }
        MasterPaymentNegotiation other = (MasterPaymentNegotiation) object;
        if ((this.pkPayNegoId == null && other.pkPayNegoId != null) || (this.pkPayNegoId != null && !this.pkPayNegoId.equals(other.pkPayNegoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.reference.domain.MasterPaymentNegotiation[ pkPayNegoId=" + pkPayNegoId + " ]";
    }
    
}

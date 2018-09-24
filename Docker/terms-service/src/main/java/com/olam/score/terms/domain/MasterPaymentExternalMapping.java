/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olam.score.terms.domain;

import java.io.Serializable;
import java.math.BigInteger;
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
 * @author Ramachandran_N02
 */
@Entity
@Table(name = "MASTER_PAYMENT_EXTERNAL_MAPPING")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterPaymentExternalMapping.findAll", query = "SELECT m FROM MasterPaymentExternalMapping m")})
public class MasterPaymentExternalMapping implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MASTER_PAYMENT_EXTERNAL_TERM_GENERATOR")
	@SequenceGenerator(name="MASTER_PAYMENT_EXTERNAL_TERM_GENERATOR", sequenceName="terms.PAY_TERM_ID_SEQ",allocationSize=1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_PAYMENT_EXTERNAL_MAPPING_ID")
    private Integer pkPaymentExternalMappingId;
    @Column(name = "FK_EXTERNAL_SYSTEM_REF_ID")
    private Integer fkExternalSystemRefId;
    @Size(max = 50)
    @Column(name = "MAPPING_TYPE")
    private String mappingType;
    @Size(max = 50)
    @Column(name = "EXTERNAL_CODE")
    private String externalCode;
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
    @Size(max = 500)
    @Column(name = "CUSTOM_ATTRIBUTE_1")
    private String customAttribute1;
    @Size(max = 500)
    @Column(name = "CUSTOM_ATTRIBUTE_2")
    private String customAttribute2;
    @Size(max = 500)
    @Column(name = "CUSTOM_ATTRIBUTE_3")
    private String customAttribute3;
    @Size(max = 500)
    @Column(name = "CUSTOM_ATTRIBUTE_4")
    private String customAttribute4;
    @Size(max = 500)
    @Column(name = "CUSTOM_ATTRIBUTE_5")
    private String customAttribute5;
    @Column(name = "CUSTOM_ATTRIBUTE_6")
    private BigInteger customAttribute6;
    @Column(name = "CUSTOM_ATTRIBUTE_7")
    private BigInteger customAttribute7;
    @Column(name = "CUSTOM_ATTRIBUTE_8")
    private BigInteger customAttribute8;
    @Column(name = "FK_STATUS_ID")
    private Integer fkStatusId;
    @JoinColumn(name = "FK_PAYMENT_TERM_ID", referencedColumnName = "PK_PAYMENT_TERM_ID")
    @ManyToOne
    private MasterPaymentTerm fkPaymentTermId;

    public MasterPaymentExternalMapping() {
    }

    public MasterPaymentExternalMapping(Integer pkPaymentExternalMappingId) {
        this.pkPaymentExternalMappingId = pkPaymentExternalMappingId;
    }

    public Integer getPkPaymentExternalMappingId() {
        return pkPaymentExternalMappingId;
    }

    public void setPkPaymentExternalMappingId(Integer pkPaymentExternalMappingId) {
        this.pkPaymentExternalMappingId = pkPaymentExternalMappingId;
    }

    public Integer getFkExternalSystemRefId() {
        return fkExternalSystemRefId;
    }

    public void setFkExternalSystemRefId(Integer fkExternalSystemRefId) {
        this.fkExternalSystemRefId = fkExternalSystemRefId;
    }

    public String getMappingType() {
        return mappingType;
    }

    public void setMappingType(String mappingType) {
        this.mappingType = mappingType;
    }

    public String getExternalCode() {
        return externalCode;
    }

    public void setExternalCode(String externalCode) {
        this.externalCode = externalCode;
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

    public String getCustomAttribute1() {
        return customAttribute1;
    }

    public void setCustomAttribute1(String customAttribute1) {
        this.customAttribute1 = customAttribute1;
    }

    public String getCustomAttribute2() {
        return customAttribute2;
    }

    public void setCustomAttribute2(String customAttribute2) {
        this.customAttribute2 = customAttribute2;
    }

    public String getCustomAttribute3() {
        return customAttribute3;
    }

    public void setCustomAttribute3(String customAttribute3) {
        this.customAttribute3 = customAttribute3;
    }

    public String getCustomAttribute4() {
        return customAttribute4;
    }

    public void setCustomAttribute4(String customAttribute4) {
        this.customAttribute4 = customAttribute4;
    }

    public String getCustomAttribute5() {
        return customAttribute5;
    }

    public void setCustomAttribute5(String customAttribute5) {
        this.customAttribute5 = customAttribute5;
    }

    public BigInteger getCustomAttribute6() {
        return customAttribute6;
    }

    public void setCustomAttribute6(BigInteger customAttribute6) {
        this.customAttribute6 = customAttribute6;
    }

    public BigInteger getCustomAttribute7() {
        return customAttribute7;
    }

    public void setCustomAttribute7(BigInteger customAttribute7) {
        this.customAttribute7 = customAttribute7;
    }

    public BigInteger getCustomAttribute8() {
        return customAttribute8;
    }

    public void setCustomAttribute8(BigInteger customAttribute8) {
        this.customAttribute8 = customAttribute8;
    }

    public Integer getFkStatusId() {
        return fkStatusId;
    }

    public void setFkStatusId(Integer fkStatusId) {
        this.fkStatusId = fkStatusId;
    }

    public MasterPaymentTerm getFkPaymentTermId() {
        return fkPaymentTermId;
    }

    public void setFkPaymentTermId(MasterPaymentTerm fkPaymentTermId) {
        this.fkPaymentTermId = fkPaymentTermId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkPaymentExternalMappingId != null ? pkPaymentExternalMappingId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterPaymentExternalMapping)) {
            return false;
        }
        MasterPaymentExternalMapping other = (MasterPaymentExternalMapping) object;
        if ((this.pkPaymentExternalMappingId == null && other.pkPaymentExternalMappingId != null) || (this.pkPaymentExternalMappingId != null && !this.pkPaymentExternalMappingId.equals(other.pkPaymentExternalMappingId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.terms.domain.MasterPaymentExternalMapping[ pkPaymentExternalMappingId=" + pkPaymentExternalMappingId + " ]";
    }
    
}

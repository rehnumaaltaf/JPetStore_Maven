/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olam.score.terms.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(schema="terms",name = "MASTER_PAYMENT_TERM")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterPaymentTerm.findAll", query = "SELECT m FROM MasterPaymentTerm m")})
public class MasterPaymentTerm implements Serializable {

    private static final long serialVersionUID = 1L;
    
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MASTER_PAYMENT_TERM_GENERATOR")
	@SequenceGenerator(name="MASTER_PAYMENT_TERM_GENERATOR", sequenceName="terms.PAY_TERM_ID_SEQ",allocationSize=1)
    @Column(name = "PK_PAYMENT_TERM_ID")
    private Integer pkPaymentTermId;
    
    @NotNull
    @Column(name = "PAY_TERM_CODE")
    private String payTermCode;
    
    @Column(name = "PAY_TERM_NAME")
    private String payTermName;
    
    @Column(name = "PAY_TERM_DESC")
    private String payTermDesc;
    
    @Column(name = "PAY_TERM_LC_BASED")
    private String payTermLcBased;
    
    @Column(name = "PAY_TERM_ADVANCE_BASED")
    private String payTermAdvanceBased;
    
    @Column(name = "CREATED_BY")
    private String createdBy;
    
    @Column(name = "CREATED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    
    @Column(name = "MODIFIED_BY")
    private String modifiedBy;
    
    @Column(name = "MODIFIED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;
    
    @Column(name = "FK_STATUS_ID")
    private Integer fkStatusId;
    
    @Column(name = "PAY_TERM_DUE_DAYS")
    private BigDecimal payTermDueDays;
    
    @Column(name = "PAY_TERM_CONTINGENCY_DAYS")
    private BigDecimal payTermContingencyDays;
    
    @Column(name = "PRINT_DESC")
    private String printDesc;
    
    /*@JoinColumn(name = "FK_PAYMENT_BASIS_REF_ID", referencedColumnName = "PK_PAYMENT_BASIS_REF_ID")
    @ManyToOne
    private MasterPaymentBasisRef fkPaymentBasisRefId;
    
    @JoinColumn(name = "FK_PAYMENT_TERM_BASE_ID", referencedColumnName = "PK_PAYMENT_TERM_BASE_ID")
    @ManyToOne
    private MasterPaymentTermBase fkPaymentTermBaseId;
    
    @JoinColumn(name = "AT_SIGHT_USANCE_FLAG_ID", referencedColumnName = "PK_PAYMENT_TERM_FLAG_ID")
    @ManyToOne
    private MasterPaymentTermFlag atSightUsanceFlagId;
    
    @JoinColumn(name = "REVOCABLE_IRREVOCABLE_FLAG_ID", referencedColumnName = "PK_PAYMENT_TERM_FLAG_ID")
    @ManyToOne
    private MasterPaymentTermFlag revocableIrrevocableFlagId;
    
    @JoinColumn(name = "CONFIRMED_NON_CONFIRMED_FLAG_ID", referencedColumnName = "PK_PAYMENT_TERM_FLAG_ID")
    @ManyToOne
    private MasterPaymentTermFlag confirmedNonConfirmedFlagId;*/
    
    @Column(name = "FK_PAYMENT_BASIS_REF_ID")
    private Integer fkPaymentBasisRefId;
    
    @Column(name = "FK_PAYMENT_TERM_BASE_ID")
    private Integer fkPaymentTermBaseId;
    
    @Column(name = "AT_SIGHT_USANCE_FLAG_ID")
    private Integer atSightUsanceFlagId;
    
    @Column(name = "REVOCABLE_IRREVOCABLE_FLAG_ID")
    private Integer revocableIrrevocableFlagId;
    
    @Column(name = "CONFIRMED_NON_CONFIRMED_FLAG_ID")
    private Integer confirmedNonConfirmedFlagId;
    
    
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "pkPaymentTermId")
    private Collection<MasterPaymentTermComment> masterPaymentTermCommentCollection;
    
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "fkPaymentTermId")
    private Collection<MasterPaymentExternalMapping> masterPaymentExternalMappingCollection;

    public MasterPaymentTerm() {
    }

    public MasterPaymentTerm(Integer pkPaymentTermId) {
        this.pkPaymentTermId = pkPaymentTermId;
    }

    public MasterPaymentTerm(Integer pkPaymentTermId, String payTermCode) {
        this.pkPaymentTermId = pkPaymentTermId;
        this.payTermCode = payTermCode;
    }

    public Integer getPkPaymentTermId() {
        return pkPaymentTermId;
    }

    public void setPkPaymentTermId(Integer pkPaymentTermId) {
        this.pkPaymentTermId = pkPaymentTermId;
    }

    public String getPayTermCode() {
        return payTermCode;
    }

    public void setPayTermCode(String payTermCode) {
        this.payTermCode = payTermCode;
    }

    public String getPayTermName() {
        return payTermName;
    }

    public void setPayTermName(String payTermName) {
        this.payTermName = payTermName;
    }

    public String getPayTermDesc() {
        return payTermDesc;
    }

    public void setPayTermDesc(String payTermDesc) {
        this.payTermDesc = payTermDesc;
    }

    public String getPayTermLcBased() {
        return payTermLcBased;
    }

    public void setPayTermLcBased(String payTermLcBased) {
        this.payTermLcBased = payTermLcBased;
    }

    public String getPayTermAdvanceBased() {
        return payTermAdvanceBased;
    }

    public void setPayTermAdvanceBased(String payTermAdvanceBased) {
        this.payTermAdvanceBased = payTermAdvanceBased;
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

    public Integer getFkStatusId() {
        return fkStatusId;
    }

    public void setFkStatusId(Integer fkStatusId) {
        this.fkStatusId = fkStatusId;
    }

    public BigDecimal getPayTermDueDays() {
        return payTermDueDays;
    }

    public void setPayTermDueDays(BigDecimal payTermDueDays) {
        this.payTermDueDays = payTermDueDays;
    }

    public BigDecimal getPayTermContingencyDays() {
        return payTermContingencyDays;
    }

    public void setPayTermContingencyDays(BigDecimal payTermContingencyDays) {
        this.payTermContingencyDays = payTermContingencyDays;
    }

    public String getPrintDesc() {
        return printDesc;
    }

    public void setPrintDesc(String printDesc) {
        this.printDesc = printDesc;
    }

    public Integer getFkPaymentBasisRefId() {
		return fkPaymentBasisRefId;
	}

	public void setFkPaymentBasisRefId(Integer fkPaymentBasisRefId) {
		this.fkPaymentBasisRefId = fkPaymentBasisRefId;
	}

	public Integer getFkPaymentTermBaseId() {
		return fkPaymentTermBaseId;
	}

	public void setFkPaymentTermBaseId(Integer fkPaymentTermBaseId) {
		this.fkPaymentTermBaseId = fkPaymentTermBaseId;
	}

	public Integer getAtSightUsanceFlagId() {
		return atSightUsanceFlagId;
	}

	public void setAtSightUsanceFlagId(Integer atSightUsanceFlagId) {
		this.atSightUsanceFlagId = atSightUsanceFlagId;
	}

	public Integer getRevocableIrrevocableFlagId() {
		return revocableIrrevocableFlagId;
	}

	public void setRevocableIrrevocableFlagId(Integer revocableIrrevocableFlagId) {
		this.revocableIrrevocableFlagId = revocableIrrevocableFlagId;
	}

	public Integer getConfirmedNonConfirmedFlagId() {
		return confirmedNonConfirmedFlagId;
	}

	public void setConfirmedNonConfirmedFlagId(Integer confirmedNonConfirmedFlagId) {
		this.confirmedNonConfirmedFlagId = confirmedNonConfirmedFlagId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@XmlTransient
    public Collection<MasterPaymentTermComment> getMasterPaymentTermCommentCollection() {
        return masterPaymentTermCommentCollection;
    }

    public void setMasterPaymentTermCommentCollection(Collection<MasterPaymentTermComment> masterPaymentTermCommentCollection) {
        this.masterPaymentTermCommentCollection = masterPaymentTermCommentCollection;
    }

    @XmlTransient
    public Collection<MasterPaymentExternalMapping> getMasterPaymentExternalMappingCollection() {
        return masterPaymentExternalMappingCollection;
    }

    public void setMasterPaymentExternalMappingCollection(Collection<MasterPaymentExternalMapping> masterPaymentExternalMappingCollection) {
        this.masterPaymentExternalMappingCollection = masterPaymentExternalMappingCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkPaymentTermId != null ? pkPaymentTermId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterPaymentTerm)) {
            return false;
        }
        MasterPaymentTerm other = (MasterPaymentTerm) object;
        if ((this.pkPaymentTermId == null && other.pkPaymentTermId != null) || (this.pkPaymentTermId != null && !this.pkPaymentTermId.equals(other.pkPaymentTermId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.terms.domain.MasterPaymentTerm[ pkPaymentTermId=" + pkPaymentTermId + " ]";
    }
    
}

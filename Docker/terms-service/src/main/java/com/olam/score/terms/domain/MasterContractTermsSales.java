/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olam.score.terms.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
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
 * @author rashmi.kedlaya
 */
@Entity
@Table(name = "MASTER_CONTRACT_TERMS_SALES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterContractTermsSales.findAll", query = "SELECT m FROM MasterContractTermsSales m")})
public class MasterContractTermsSales implements Serializable {

    private static final long serialVersionUID = 1L;
    @SequenceGenerator(name="MASTER_CONTRACT_TERMS_SALES_CONTRACTTERMSSALESIDSEQ_GENERATOR", sequenceName="CONTRACT_TERMS_SALES_ID_SEQ",allocationSize=1)
	@GeneratedValue(generator="MASTER_CONTRACT_TERMS_SALES_CONTRACTTERMSSALESIDSEQ_GENERATOR")
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_CONTRACT_TERMS_SALES_ID")
    private Integer pkContractTermsSalesId;
    @Column(name = "FK_COST_GROUP_ID")
    private Integer fkCostGroupId;
    @Size(max = 1)
    @Column(name = "BUDGET_IND")
    private String budgetInd;
    @Column(name = "FK_COST_ID")
    private Integer fkCostId;
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
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "CUSTOM_ATTRIBUTE_6")
    private Double customAttribute6;
    @Column(name = "CUSTOM_ATTRIBUTE_7")
    private Double customAttribute7;
    @Column(name = "CUSTOM_ATTRIBUTE_8")
    private Double customAttribute8;
    @JoinColumn(name = "FK_CONTR_TRMS_ADDREDUCE_REF_ID", referencedColumnName = "PK_CONTR_TRMS_ADDREDUCE_REF_ID")
    @ManyToOne
    private MasterContrTrmsAddreduceRef fkContrTrmsAddreduceRefId;
    @JoinColumn(name = "FK_CONTRACT_TERMS_ID", referencedColumnName = "PK_CONTRACT_TERMS_ID")
    @ManyToOne
    private MasterContractTerms fkContractTermsId;

    public MasterContractTermsSales() {
    }

    public MasterContractTermsSales(Integer pkContractTermsSalesId) {
        this.pkContractTermsSalesId = pkContractTermsSalesId;
    }

    public Integer getPkContractTermsSalesId() {
        return pkContractTermsSalesId;
    }

    public void setPkContractTermsSalesId(Integer pkContractTermsSalesId) {
        this.pkContractTermsSalesId = pkContractTermsSalesId;
    }

    public Integer getFkCostGroupId() {
        return fkCostGroupId;
    }

    public void setFkCostGroupId(Integer fkCostGroupId) {
        this.fkCostGroupId = fkCostGroupId;
    }

    public String getBudgetInd() {
        return budgetInd;
    }

    public void setBudgetInd(String budgetInd) {
        this.budgetInd = budgetInd;
    }

    public Integer getFkCostId() {
        return fkCostId;
    }

    public void setFkCostId(Integer fkCostId) {
        this.fkCostId = fkCostId;
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

    public Double getCustomAttribute6() {
        return customAttribute6;
    }

    public void setCustomAttribute6(Double customAttribute6) {
        this.customAttribute6 = customAttribute6;
    }

    public Double getCustomAttribute7() {
        return customAttribute7;
    }

    public void setCustomAttribute7(Double customAttribute7) {
        this.customAttribute7 = customAttribute7;
    }

    public Double getCustomAttribute8() {
        return customAttribute8;
    }

    public void setCustomAttribute8(Double customAttribute8) {
        this.customAttribute8 = customAttribute8;
    }

    public MasterContrTrmsAddreduceRef getFkContrTrmsAddreduceRefId() {
        return fkContrTrmsAddreduceRefId;
    }

    public void setFkContrTrmsAddreduceRefId(MasterContrTrmsAddreduceRef fkContrTrmsAddreduceRefId) {
        this.fkContrTrmsAddreduceRefId = fkContrTrmsAddreduceRefId;
    }

    public MasterContractTerms getFkContractTermsId() {
        return fkContractTermsId;
    }

    public void setFkContractTermsId(MasterContractTerms fkContractTermsId) {
        this.fkContractTermsId = fkContractTermsId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkContractTermsSalesId != null ? pkContractTermsSalesId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterContractTermsSales)) {
            return false;
        }
        MasterContractTermsSales other = (MasterContractTermsSales) object;
        if ((this.pkContractTermsSalesId == null && other.pkContractTermsSalesId != null) || (this.pkContractTermsSalesId != null && !this.pkContractTermsSalesId.equals(other.pkContractTermsSalesId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.terms.domain.MasterContractTermsSales[ pkContractTermsSalesId=" + pkContractTermsSalesId + " ]";
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olam.score.cost.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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

/**
 *
 * @author ramachandran_n02
 */
@Entity
@Table(name = "MASTER_FREIGHT_COST_MATRIX", catalog = "SCORE_DEV_002", schema = "cost")
@XmlRootElement
public class MasterFreightCostMatrix implements Serializable {

    
    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="FREIGHT_COST_MATRIX_ID_SEQ")
   	@SequenceGenerator(name="FREIGHT_COST_MATRIX_ID_SEQ", sequenceName="FREIGHT_COST_MATRIX_ID_SEQ",initialValue=1,allocationSize=1)
    @Column(name = "PK_FREIGHT_COST_MATRIX_ID")
    private Integer pkFreightCostMatrixId;
    @Column(name = "FK_SERVICE_PROVIDER_PARTY_ID")
    private Integer fkServiceProviderPartyId;
    @Column(name="FK_PROD_ID")
    private Integer fkProdId;
    @JoinColumn(name = "FK_COST_ID")
	@ManyToOne
	private	MasterCost fkCostId;
    @Column(name = "FREIGHT_COST_VALID_FROM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date freightCostValidFrom;
    @Column(name = "FREIGHT_COST_VALID_TO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date freightCostValidTo;
    @Size(max = 200)
    @Column(name = "FREIGHT_COST_CONTR_REF_NO")
    private String freightCostContrRefNo;
    @Size(max = 1000)
    @Column(name = "FREIGHT_COST_REMARKS")
    private String freightCostRemarks;
    @Column(name = "FK_STATUS_ID")
    private Integer fkStatusId;
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
    @Column(name="IS_APPLICABLE_TO_ALL_PARTY_IND")
    private int isApplicableToAllPartyInd;
    
    @Column(name="COST_IS_DETAILED_IND")
    private int costIsDetailedInd;
    
   
    
    public int getCostIsDetailedInd() {
		return costIsDetailedInd;
	}

	public void setCostIsDetailedInd(int costIsDetailedInd) {
		this.costIsDetailedInd = costIsDetailedInd;
	}

	@OneToMany(cascade = CascadeType.ALL,mappedBy = "fkFreightCostMatrixId")
    private List<MasterFreightCostMatrixDtl> masterFreightCostMatrixDtl;
    
    public int getIsApplicableToAllPartyInd() {
		return isApplicableToAllPartyInd;
	}

	public void setIsApplicableToAllPartyInd(int isApplicableToAllPartyInd) {
		this.isApplicableToAllPartyInd = isApplicableToAllPartyInd;
	}

	@OneToMany(cascade = CascadeType.ALL,mappedBy = "fkFreightCostMatrixId")
    private List<MasterFreightCostMatricPartyAssoc> masterFreightCostMatricPartyAssoc;
    
    
    
    public List<MasterFreightCostMatricPartyAssoc> getMasterFreightCostMatricPartyAssoc() {
		return masterFreightCostMatricPartyAssoc;
	}

	public void setMasterFreightCostMatricPartyAssoc(
			List<MasterFreightCostMatricPartyAssoc> masterFreightCostMatricPartyAssoc) {
		this.masterFreightCostMatricPartyAssoc = masterFreightCostMatricPartyAssoc;
	}

	public MasterCost getFkCostId() {
		return fkCostId;
	}

	public void setFkCostId(MasterCost fkCostId) {
		this.fkCostId = fkCostId;
	}

	public List<MasterFreightCostMatrixDtl> getMasterFreightCostMatrixDtl() {
		return masterFreightCostMatrixDtl;
	}

	public void setMasterFreightCostMatrixDtl(List<MasterFreightCostMatrixDtl> masterFreightCostMatrixDtl) {
		this.masterFreightCostMatrixDtl = masterFreightCostMatrixDtl;
	}

	public MasterFreightCostMatrix() {
    }

    public MasterFreightCostMatrix(Integer pkFreightCostMatrixId) {
        this.pkFreightCostMatrixId = pkFreightCostMatrixId;
    }

    public Integer getPkFreightCostMatrixId() {
        return pkFreightCostMatrixId;
    }

    public void setPkFreightCostMatrixId(Integer pkFreightCostMatrixId) {
        this.pkFreightCostMatrixId = pkFreightCostMatrixId;
    }

    public Integer getFkServiceProviderPartyId() {
        return fkServiceProviderPartyId;
    }

    public void setFkServiceProviderPartyId(Integer fkServiceProviderPartyId) {
        this.fkServiceProviderPartyId = fkServiceProviderPartyId;
    }

    

    public Date getFreightCostValidFrom() {
        return freightCostValidFrom;
    }

    public void setFreightCostValidFrom(Date freightCostValidFrom) {
        this.freightCostValidFrom = freightCostValidFrom;
    }

    public Date getFreightCostValidTo() {
        return freightCostValidTo;
    }

    public void setFreightCostValidTo(Date freightCostValidTo) {
        this.freightCostValidTo = freightCostValidTo;
    }

    public String getFreightCostContrRefNo() {
        return freightCostContrRefNo;
    }

    public void setFreightCostContrRefNo(String freightCostContrRefNo) {
        this.freightCostContrRefNo = freightCostContrRefNo;
    }

    public String getFreightCostRemarks() {
        return freightCostRemarks;
    }

    public void setFreightCostRemarks(String freightCostRemarks) {
        this.freightCostRemarks = freightCostRemarks;
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
    

    public Integer getFkProdId() {
		return fkProdId;
	}

	public void setFkProdId(Integer fkProdId) {
		this.fkProdId = fkProdId;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (pkFreightCostMatrixId != null ? pkFreightCostMatrixId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterFreightCostMatrix)) {
            return false;
        }
        MasterFreightCostMatrix other = (MasterFreightCostMatrix) object;
        if ((this.pkFreightCostMatrixId == null && other.pkFreightCostMatrixId != null) || (this.pkFreightCostMatrixId != null && !this.pkFreightCostMatrixId.equals(other.pkFreightCostMatrixId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.cost.domain.MasterFreightCostMatrix[ pkFreightCostMatrixId=" + pkFreightCostMatrixId + " ]";
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olam.score.cost.domain;

import java.io.Serializable;
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
@Table(name = "MASTER_WH_COST", catalog = "SCORE_DEV_002", schema = "cost")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterWhCost.findAll", query = "SELECT m FROM MasterWhCost m")})
public class MasterWhCost implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="WH_COST_ID_SEQ")
   	@SequenceGenerator(name="WH_COST_ID_SEQ", sequenceName="cost.WH_COST_ID_SEQ",initialValue=1,allocationSize=1)
    @Column(name = "PK_WH_COST_ID")
    private Integer pkWhCostId;
    
    @Column(name="WH_COST_NAME")
    private String whCostName;
    
    @Column(name="WH_COST_CODE")
    private String whCostCode;
    
    @Column(name="WH_COST_DESC")
    private String whCostDesc;
    public String getWhCostName() {
		return whCostName;
	}

	public void setWhCostName(String whCostName) {
		this.whCostName = whCostName;
	}

	public String getWhCostCode() {
		return whCostCode;
	}

	public void setWhCostCode(String whCostCode) {
		this.whCostCode = whCostCode;
	}

	public String getWhCostDesc() {
		return whCostDesc;
	}

	public void setWhCostDesc(String whCostDesc) {
		this.whCostDesc = whCostDesc;
	}
	@Size(max = 20)
    @Column(name = "WH_COST_FREE_PERIOD_TYPE")
    private String whCostFreePeriodType;
    @Column(name = "WH_COST_NO_OF_FREE_DAYS")
    private Integer whCostNoOfFreeDays;
    
    
    @Column(name = "WH_COST_VALID_FROM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date whCostValidFrom;
    @Column(name = "WH_COST_VALID_TO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date whCostValidTo;
    @Size(max = 20)
    @Column(name = "WH_COST_CONTR_REF_NO")
    private String whCostContrRefNo;
    @Size(max = 1000)
    @Column(name = "WH_COST_REMARKS")
    private String whCostRemarks;
   
    
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
    @Column(name = "FK_STATUS_ID")
    private Integer fkStatusId;
    
    
    @Column(name="IS_APPLICABLE_TO_ALL_PARTY_IND")
    private Integer isApplicableToAllPartyInd;
    @Column(name="COST_IS_DETAILED_IND")
    private Integer costIsDetailedInd;
    @Column(name="FK_PROD_ID")
    private Integer fkProdId;
    @Column(name = "IS_APPLICABLE_TO_ALL_STOCK_LOC_IND")
    private Integer isApplicableToAllStockLocInd;
    @Column(name="FK_WAREHOUSE_PARTY_ID")
    private Integer fkWarehousePartyId;

    public MasterWhCost() {
    }

    public MasterWhCost(Integer pkWhCostId) {
        this.pkWhCostId = pkWhCostId;
    }

    public Integer getPkWhCostId() {
        return pkWhCostId;
    }

    public void setPkWhCostId(Integer pkWhCostId) {
        this.pkWhCostId = pkWhCostId;
    }

    

    public String getWhCostFreePeriodType() {
        return whCostFreePeriodType;
    }

    public void setWhCostFreePeriodType(String whCostFreePeriodType) {
        this.whCostFreePeriodType = whCostFreePeriodType;
    }

    public Integer getWhCostNoOfFreeDays() {
        return whCostNoOfFreeDays;
    }

    public void setWhCostNoOfFreeDays(Integer whCostNoOfFreeDays) {
        this.whCostNoOfFreeDays = whCostNoOfFreeDays;
    }

    public Date getWhCostValidFrom() {
        return whCostValidFrom;
    }

    public void setWhCostValidFrom(Date whCostValidFrom) {
        this.whCostValidFrom = whCostValidFrom;
    }

    public Date getWhCostValidTo() {
        return whCostValidTo;
    }

    public void setWhCostValidTo(Date whCostValidTo) {
        this.whCostValidTo = whCostValidTo;
    }

    public String getWhCostContrRefNo() {
        return whCostContrRefNo;
    }

    public void setWhCostContrRefNo(String whCostContrRefNo) {
        this.whCostContrRefNo = whCostContrRefNo;
    }

    public String getWhCostRemarks() {
        return whCostRemarks;
    }

    public void setWhCostRemarks(String whCostRemarks) {
        this.whCostRemarks = whCostRemarks;
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

   

   

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkWhCostId != null ? pkWhCostId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterWhCost)) {
            return false;
        }
        MasterWhCost other = (MasterWhCost) object;
        if ((this.pkWhCostId == null && other.pkWhCostId != null) || (this.pkWhCostId != null && !this.pkWhCostId.equals(other.pkWhCostId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.cost.domain.MasterWhCost[ pkWhCostId=" + pkWhCostId + " ]";
    }

	public Integer getIsApplicableToAllPartyInd() {
		return isApplicableToAllPartyInd;
	}

	public void setIsApplicableToAllPartyInd(Integer isApplicableToAllPartyInd) {
		this.isApplicableToAllPartyInd = isApplicableToAllPartyInd;
	}

	public Integer getCostIsDetailedInd() {
		return costIsDetailedInd;
	}

	public void setCostIsDetailedInd(Integer costIsDetailedInd) {
		this.costIsDetailedInd = costIsDetailedInd;
	}

	public Integer getFkProdId() {
		return fkProdId;
	}

	public void setFkProdId(Integer fkProdId) {
		this.fkProdId = fkProdId;
	}

	public Integer getIsApplicableToAllStockLocInd() {
		return isApplicableToAllStockLocInd;
	}

	public void setIsApplicableToAllStockLocInd(Integer isApplicableToAllStockLocInd) {
		this.isApplicableToAllStockLocInd = isApplicableToAllStockLocInd;
	}

	public Integer getFkWarehousePartyId() {
		return fkWarehousePartyId;
	}

	public void setFkWarehousePartyId(Integer fkWarehousePartyId) {
		this.fkWarehousePartyId = fkWarehousePartyId;
	}
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "fkWhCostId")
    private List<MasterWhCostDetail> masterWhCostDetailList;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "fkWhCostId")
    private List<MasterWhCostStockLocAssoc> masterWhCostStockLocAssocList;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "fkWhCostId")
    private List<MasterWhCostPartyAssoc> masterWhCostPartyAssocList;

	public List<MasterWhCostDetail> getMasterWhCostDetailList() {
		return masterWhCostDetailList;
	}

	public void setMasterWhCostDetailList(List<MasterWhCostDetail> masterWhCostDetailList) {
		this.masterWhCostDetailList = masterWhCostDetailList;
	}

	

	public List<MasterWhCostStockLocAssoc> getMasterWhCostStockLocAssocList() {
		return masterWhCostStockLocAssocList;
	}

	public void setMasterWhCostStockLocAssocList(List<MasterWhCostStockLocAssoc> masterWhCostStockLocAssocList) {
		this.masterWhCostStockLocAssocList = masterWhCostStockLocAssocList;
	}

	public List<MasterWhCostPartyAssoc> getMasterWhCostPartyAssocList() {
		return masterWhCostPartyAssocList;
	}

	public void setMasterWhCostPartyAssocList(List<MasterWhCostPartyAssoc> masterWhCostPartyAssocList) {
		this.masterWhCostPartyAssocList = masterWhCostPartyAssocList;
	}
    
	@JoinColumn(name = "FK_COST_ID")
	@ManyToOne(cascade = CascadeType.ALL)
	private	MasterCost fkCostId;

	public MasterCost getFkCostId() {
		return fkCostId;
	}

	public void setFkCostId(MasterCost fkCostId) {
		this.fkCostId = fkCostId;
	}
	
	
}

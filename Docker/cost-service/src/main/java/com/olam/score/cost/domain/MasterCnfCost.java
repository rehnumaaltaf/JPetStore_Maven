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
@Table(name = "MASTER_CNF_COST", catalog = "SCORE_DEV_002", schema = "cost")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterCnfCost.findAll", query = "SELECT m FROM MasterCnfCost m")})
public class MasterCnfCost implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CNF_COST_ID_SEQ")
   	@SequenceGenerator(name="CNF_COST_ID_SEQ", sequenceName="cost.CNF_COST_ID_SEQ",initialValue=1,allocationSize=1)
    @Column(name = "PK_CNF_COST_ID")
    private Integer pkCnfCostId;
    @JoinColumn(name = "FK_COST_ID")
	@ManyToOne
	private	MasterCost fkCostId;
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
    @Column(name = "FK_LOC_ID")
    private Integer fkLocId;
    @Column(name = "FK_UOM_ID")
    private Integer fkUomId;
    @Column(name = "FK_CURRENCY_ID")
    private Integer fkCurrencyId;
    
    @Column(name = "CNF_COST_VALID_FROM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cnfCostMatrixValidFrom;
    @Column(name = "CNF_COST_VALID_TO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cnfCostMatrixValidTo;
    @Size(max = 200)
    @Column(name = "CNF_COST_CONTR_REF_NO")
    private String cnfCostMatrixContrRefNo;
    @Size(max = 500)
    @Column(name = "CNF_COST_REMARKS")
    private String cnfCostMatrixRemarks;
    
    @Column(name="IS_APPLICABLE_TO_ALL_PARTY_IND")
    private int isApplicableToAllPartyInd;
    @Column(name="COST_IS_DETAILED_IND")
    private int costIsDetailedInd;
    @Column(name="FK_PROD_ID")
    private Integer fkProdId;
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

	public Integer getFkWarehousePartyId() {
		return fkWarehousePartyId;
	}

	public void setFkWarehousePartyId(Integer fkWarehousePartyId) {
		this.fkWarehousePartyId = fkWarehousePartyId;
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

	@Column(name="FK_WAREHOUSE_PARTY_ID")
    private Integer fkWarehousePartyId;
    @Size(max = 500)
    @Column(name = "CUSTOM_ATTRIBUTE_1", length = 500)
    private String customAttribute1;
    @Size(max = 500)
    @Column(name = "CUSTOM_ATTRIBUTE_2", length = 500)
    private String customAttribute2;
    @Size(max = 500)
    @Column(name = "CUSTOM_ATTRIBUTE_3", length = 500)
    private String customAttribute3;
    @Size(max = 500)
    @Column(name = "CUSTOM_ATTRIBUTE_4", length = 500)
    private String customAttribute4; 
    @Size(max = 500)
    @Column(name = "CUSTOM_ATTRIBUTE_5", length = 500)
    private String customAttribute5;
    @Column(name = "CUSTOM_ATTRIBUTE_6", precision = 53)
    private Double customAttribute6;
    @Column(name = "CUSTOM_ATTRIBUTE_7", precision = 53)
    private Double customAttribute7;
    @Column(name = "CUSTOM_ATTRIBUTE_8", precision = 53)
    private Double customAttribute8;
    
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "fkCnfCostId")
    private List<MasterCnfCostPartyAssoc> masterCnfCostPartyAssocList;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "fkCnfCostId")
    private List<MasterCnfCostStockLocAssoc> masterCnfCostStockLocAssocList;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "fkCnfCostId")
    private List<MasterCnfCostDetail> masterCnfCostDetail;
    
    
    
    
	public MasterCost getFkCostId() {
		return fkCostId;
	}

	public void setFkCostId(MasterCost fkCostId) {
		this.fkCostId = fkCostId;
	}

	public List<MasterCnfCostPartyAssoc> getMasterCnfCostPartyAssocList() {
		return masterCnfCostPartyAssocList;
	}

	public void setMasterCnfCostPartyAssocList(List<MasterCnfCostPartyAssoc> masterCnfCostPartyAssocList) {
		this.masterCnfCostPartyAssocList = masterCnfCostPartyAssocList;
	}

	public List<MasterCnfCostStockLocAssoc> getMasterCnfCostStockLocAssocList() {
		return masterCnfCostStockLocAssocList;
	}

	public void setMasterCnfCostStockLocAssocList(List<MasterCnfCostStockLocAssoc> masterCnfCostStockLocAssocList) {
		this.masterCnfCostStockLocAssocList = masterCnfCostStockLocAssocList;
	}

	public List<MasterCnfCostDetail> getMasterCnfCostDetail() {
		return masterCnfCostDetail;
	}

	public void setMasterCnfCostDetail(List<MasterCnfCostDetail> masterCnfCostDetail) {
		this.masterCnfCostDetail = masterCnfCostDetail;
	}

	public MasterCnfCost() {
    }

    public MasterCnfCost(Integer pkCnfCostId) {
        this.pkCnfCostId = pkCnfCostId;
    }

    public Integer getPkCnfCostId() {
        return pkCnfCostId;
    }

    public void setPkCnfCostId(Integer pkCnfCostId) {
        this.pkCnfCostId = pkCnfCostId;
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

    public Integer getFkLocId() {
        return fkLocId;
    }

    public void setFkLocId(Integer fkLocId) {
        this.fkLocId = fkLocId;
    }

    public Integer getFkUomId() {
        return fkUomId;
    }

    public void setFkUomId(Integer fkUomId) {
        this.fkUomId = fkUomId;
    }

    public Integer getFkCurrencyId() {
        return fkCurrencyId;
    }

    public void setFkCurrencyId(Integer fkCurrencyId) {
        this.fkCurrencyId = fkCurrencyId;
    }

    public Date getCnfCostMatrixValidFrom() {
        return cnfCostMatrixValidFrom;
    }

    public void setCnfCostMatrixValidFrom(Date cnfCostMatrixValidFrom) {
        this.cnfCostMatrixValidFrom = cnfCostMatrixValidFrom;
    }

    public Date getCnfCostMatrixValidTo() {
        return cnfCostMatrixValidTo;
    }

    public void setCnfCostMatrixValidTo(Date cnfCostMatrixValidTo) {
        this.cnfCostMatrixValidTo = cnfCostMatrixValidTo;
    }

    public String getCnfCostMatrixContrRefNo() {
        return cnfCostMatrixContrRefNo;
    }

    public void setCnfCostMatrixContrRefNo(String cnfCostMatrixContrRefNo) {
        this.cnfCostMatrixContrRefNo = cnfCostMatrixContrRefNo;
    }

    public String getCnfCostMatrixRemarks() {
        return cnfCostMatrixRemarks;
    }

    public void setCnfCostMatrixRemarks(String cnfCostMatrixRemarks) {
        this.cnfCostMatrixRemarks = cnfCostMatrixRemarks;
    }
    
    @Column(name = "IS_APPLICABLE_TO_ALL_STOCK_LOC_IND")
    private Integer isApplicableToAllStockLocInd;
    
    
    public Integer getIsApplicableToAllStockLocInd() {
		return isApplicableToAllStockLocInd;
	}

	public void setIsApplicableToAllStockLocInd(Integer isApplicableToAllStockLocInd) {
		this.isApplicableToAllStockLocInd = isApplicableToAllStockLocInd;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (pkCnfCostId != null ? pkCnfCostId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof MasterCnfCost)) {
            return false;
        }
        MasterCnfCost other = (MasterCnfCost) object;
        if ((this.pkCnfCostId == null && other.pkCnfCostId != null) || (this.pkCnfCostId != null && !this.pkCnfCostId.equals(other.pkCnfCostId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.cost.domain.MasterCnfCost[ pkCnfCostId=" + pkCnfCostId + " ]";
    }
    
}

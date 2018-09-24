/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olam.score.cost.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
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
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ramachandran_n02
 */
@Entity
@Table(name = "MASTER_COST")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterCost.findAll", query = "SELECT m FROM MasterCost m")})
public class MasterCost implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_COST_ID")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="COST_ID_SEQ")
   	@SequenceGenerator(name="COST_ID_SEQ", sequenceName="COST_ID_SEQ",allocationSize=1)
    private Integer pkCostId;
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
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "COST_NAME")
    private String costName;
    @Size(max = 20)
    @Column(name = "COST_CODE")
    private String costCode;
    @Size(max = 200)
    @Column(name = "COST_FULLNAME")
    private String costFullname;
    @Size(max = 1)
    @Column(name = "MATRIX_APPLICABLE_IND")
    private String matrixApplicableInd;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "COST_DEFAULT_VALUE")
    private BigDecimal costDefaultValue;
    @Column(name = "FK_INDEX_UOM_ID")
    private Integer fkIndexUomId;
    @Column(name = "FK_CURRENCY_ID")
    private Integer fkCurrencyId;
    @Size(max = 1)
    @Column(name = "NETTED_FOR_MTM_IND")
    private String nettedForMtmInd;
    @Size(max = 1)
    @Column(name = "REALIZED_IND")
    private String realizedInd;
    @Column(name = "FK_STATUS_ID")
    private Integer fkStatusId;
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
    @JoinColumn(name = "FK_COST_DEF_VALUE_TYPE_ID", referencedColumnName = "PK_COST_DEF_VALUE_TYPE_ID")
    @ManyToOne
    private MasterCostDefValueTypeRef fkCostDefValueTypeId;
    @JoinColumn(name = "FK_COST_GROUP_ID", referencedColumnName = "PK_COST_GROUP_ID")
    @ManyToOne
    private MasterCostGroup fkCostGroupId;
    @OneToMany(mappedBy = "fkCostId")
    private Collection<MasterCostGlMapping> masterCostGlMappingCollection;
    
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "costExterMapAssoc")
    private List<MasterCostExternalMapping> masterPackingAssocList;
    
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "costPartyAssoc")
    private List<MasterCostPartyAssoc> costPartyAssoc;
    
    
    @Column(name = "FK_REVENUE_GL_ID")
    private Integer fkRevenueGlId;
    @Column(name = "FK_EXPENSE_GL_ID")
    private Integer fkExpenseGlId;
    
    @Column(name = "FK_MATRIX_ENTITY_ID")
    private Integer fkMatrixEntityId;
    @Column(name = "COST_TYPE_IS_PRIMARY_IND")
    private String costTypeIsPrimaryInd;
    
    @Column(name = "FK_DEFAULT_VALUE_BASIS_REF_ID")
    private Integer fkDefaultValueBasisRefId;
    @Column(name = "FK_DEF_VAL_PRIMARY_PACKING_ID")
    private Integer fkDefValPrimaryPackingId;
    @Column(name = "FK_DEF_VAL_SECONDARY_PACKING_ID")
    private Integer fkDefValSecondaryPackingId;
    @Column(name = "FK_DEFAULT_VALUE_UOM_ID")
    private Integer fkDefaultValueUomId;
   
    @Column(name = "CAPITALIZE_COST_IND")
    private String capitalizeCostInd;
    
    public MasterCost() {
    }

    public MasterCost(Integer pkCostId) {
        this.pkCostId = pkCostId;
    }

    public MasterCost(Integer pkCostId, String costName) {
        this.pkCostId = pkCostId;
        this.costName = costName;
    }

    public Integer getPkCostId() {
        return pkCostId;
    }

    public void setPkCostId(Integer pkCostId) {
        this.pkCostId = pkCostId;
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

    public String getCostName() {
        return costName;
    }

    public void setCostName(String costName) {
        this.costName = costName;
    }

    public String getCostCode() {
        return costCode;
    }

    public void setCostCode(String costCode) {
        this.costCode = costCode;
    }

    public String getCostFullname() {
        return costFullname;
    }

    public void setCostFullname(String costFullname) {
        this.costFullname = costFullname;
    }

    public String getMatrixApplicableInd() {
        return matrixApplicableInd;
    }

    public void setMatrixApplicableInd(String matrixApplicableInd) {
        this.matrixApplicableInd = matrixApplicableInd;
    }

    public BigDecimal getCostDefaultValue() {
        return costDefaultValue;
    }

    public void setCostDefaultValue(BigDecimal costDefaultValue) {
        this.costDefaultValue = costDefaultValue;
    }

    public Integer getFkIndexUomId() {
        return fkIndexUomId;
    }

    public void setFkIndexUomId(Integer fkIndexUomId) {
        this.fkIndexUomId = fkIndexUomId;
    }

    public Integer getFkCurrencyId() {
        return fkCurrencyId;
    }

    public void setFkCurrencyId(Integer fkCurrencyId) {
        this.fkCurrencyId = fkCurrencyId;
    }

    public String getNettedForMtmInd() {
        return nettedForMtmInd;
    }

    public void setNettedForMtmInd(String nettedForMtmInd) {
        this.nettedForMtmInd = nettedForMtmInd;
    }

    public String getRealizedInd() {
        return realizedInd;
    }

    public void setRealizedInd(String realizedInd) {
        this.realizedInd = realizedInd;
    }

    public Integer getFkStatusId() {
        return fkStatusId;
    }

    public void setFkStatusId(Integer fkStatusId) {
        this.fkStatusId = fkStatusId;
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

    public MasterCostDefValueTypeRef getFkCostDefValueTypeId() {
        return fkCostDefValueTypeId;
    }

    public void setFkCostDefValueTypeId(MasterCostDefValueTypeRef fkCostDefValueTypeId) {
        this.fkCostDefValueTypeId = fkCostDefValueTypeId;
    }

    public MasterCostGroup getFkCostGroupId() {
        return fkCostGroupId;
    }

    public void setFkCostGroupId(MasterCostGroup fkCostGroupId) {
        this.fkCostGroupId = fkCostGroupId;
    }

    @XmlTransient
    public Collection<MasterCostGlMapping> getMasterCostGlMappingCollection() {
        return masterCostGlMappingCollection;
    }

    public void setMasterCostGlMappingCollection(Collection<MasterCostGlMapping> masterCostGlMappingCollection) {
        this.masterCostGlMappingCollection = masterCostGlMappingCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkCostId != null ? pkCostId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterCost)) {
            return false;
        }
        MasterCost other = (MasterCost) object;
        if ((this.pkCostId == null && other.pkCostId != null) || (this.pkCostId != null && !this.pkCostId.equals(other.pkCostId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.cost.domain.MasterCost[ pkCostId=" + pkCostId + " ]";
    }

	public List<MasterCostExternalMapping> getMasterPackingAssocList() {
		return masterPackingAssocList;
	}

	public void setMasterPackingAssocList(List<MasterCostExternalMapping> masterPackingAssocList) {
		this.masterPackingAssocList = masterPackingAssocList;
	}

	public Integer getFkRevenueGlId() {
		return fkRevenueGlId;
	}

	public void setFkRevenueGlId(Integer fkRevenueGlId) {
		this.fkRevenueGlId = fkRevenueGlId;
	}

	public Integer getFkExpenseGlId() {
		return fkExpenseGlId;
	}

	public void setFkExpenseGlId(Integer fkExpenseGlId) {
		this.fkExpenseGlId = fkExpenseGlId;
	}

	public Integer getFkMatrixEntityId() {
		return fkMatrixEntityId;
	}

	public void setFkMatrixEntityId(Integer fkMatrixEntityId) {
		this.fkMatrixEntityId = fkMatrixEntityId;
	}

	

	public Integer getFkDefValPrimaryPackingId() {
		return fkDefValPrimaryPackingId;
	}

	public void setFkDefValPrimaryPackingId(Integer fkDefValPrimaryPackingId) {
		this.fkDefValPrimaryPackingId = fkDefValPrimaryPackingId;
	}

	public Integer getFkDefValSecondaryPackingId() {
		return fkDefValSecondaryPackingId;
	}

	public void setFkDefValSecondaryPackingId(Integer fkDefValSecondaryPackingId) {
		this.fkDefValSecondaryPackingId = fkDefValSecondaryPackingId;
	}

	public Integer getFkDefaultValueUomId() {
		return fkDefaultValueUomId;
	}

	public void setFkDefaultValueUomId(Integer fkDefaultValueUomId) {
		this.fkDefaultValueUomId = fkDefaultValueUomId;
	}

	public String getCostTypeIsPrimaryInd() {
		return costTypeIsPrimaryInd;
	}

	public void setCostTypeIsPrimaryInd(String costTypeIsPrimaryInd) {
		this.costTypeIsPrimaryInd = costTypeIsPrimaryInd;
	}

	public Integer getFkDefaultValueBasisRefId() {
		return fkDefaultValueBasisRefId;
	}

	public void setFkDefaultValueBasisRefId(Integer fkDefaultValueBasisRefId) {
		this.fkDefaultValueBasisRefId = fkDefaultValueBasisRefId;
	}

	public String getCapitalizeCostInd() {
		return capitalizeCostInd;
	}

	public void setCapitalizeCostInd(String capitalizeCostInd) {
		this.capitalizeCostInd = capitalizeCostInd;
	}
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "fkCostId")
    private List<MasterCnfCost> masterCnfCost;
	public List<MasterCnfCost> getMasterCnfCost() {
		return masterCnfCost;
	}

	public void setMasterCnfCost(List<MasterCnfCost> masterCnfCost) {
		this.masterCnfCost = masterCnfCost;
	}


	public List<MasterCostPartyAssoc> getCostPartyAssoc() {
		return costPartyAssoc;
	}

	public void setCostPartyAssoc(List<MasterCostPartyAssoc> costPartyAssoc) {
		this.costPartyAssoc = costPartyAssoc;
	}
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "fkCostId")
    private List<MasterWhCost> masterWhCost;
	public List<MasterWhCost> getMasterWhCost() {
		return masterWhCost;
	}

	public void setMasterWhCost(List<MasterWhCost> masterWhCost) {
		this.masterWhCost = masterWhCost;
	}
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "fkCostId")
    private List<MasterFreightCostMatrix> masterFreightCostMatrix;
	public List<MasterFreightCostMatrix> getMasterFreightCostMatrix() {
		return masterFreightCostMatrix;
	}

	public void setMasterFreightCostMatrix(List<MasterFreightCostMatrix> masterFreightCostMatrix) {
		this.masterFreightCostMatrix = masterFreightCostMatrix;
	}
	
}

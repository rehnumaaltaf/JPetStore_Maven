/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olam.score.terms.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
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

import org.modelmapper.ModelMapper;

import com.olam.score.terms.dto.ContractTermsDto;
import com.olam.score.terms.dto.ContractTermsPurchaseDto;
import com.olam.score.terms.dto.ContractTermsSalesDto;

/**
 *
 * @author rashmi.kedlaya
 */
@Entity
@Table(name = "MASTER_CONTRACT_TERMS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterContractTerms.findAll", query = "SELECT m FROM MasterContractTerms m")})
public class MasterContractTerms implements Serializable {

    private static final long serialVersionUID = 1L;
    @SequenceGenerator(name="MASTER_CONTRACT_TERMS_CONTRACTTERMSIDSEQ_GENERATOR", sequenceName="CONTRACT_TERMS_ID_SEQ",allocationSize=1)
	@GeneratedValue(generator="MASTER_CONTRACT_TERMS_CONTRACTTERMSIDSEQ_GENERATOR")
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_CONTRACT_TERMS_ID")
    private Integer pkContractTermsId;
    @Size(max = 20)
    @Column(name = "CONTRACT_TERMS_CODE")
    private String contractTermsCode;
    @Size(max = 200)
    @Column(name = "CONTRACT_TERMS_NAME")
    private String contractTermsName;
    @Size(max = 1000)
    @Column(name = "CONTRACT_TERMS_DESC")
    private String contractTermsDesc;
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
    @Column(name = "FK_PROD_ID")
    private Integer fkProdId;
    @OneToMany(mappedBy = "fkContractTermsId", cascade= CascadeType.ALL)
    private Collection<MasterContractTermsSales> masterContractTermsSalesCollection;
    @JoinColumn(name = "FK_CONTRACT_TERMS_BASE_REF_ID", referencedColumnName = "PK_CONTRACT_TERMS_BASE_REF_ID")
    @ManyToOne
    private MasterContractTermsBaseRef fkContractTermsBaseRefId;
    @JoinColumn(name = "FK_CONTRACT_TERMS_BASIS_REF_ID", referencedColumnName = "PK_CONTRACT_TERMS_BASIS_REF_ID")
    @ManyToOne
    private MasterContractTermsBasisRef fkContractTermsBasisRefId;
    @OneToMany(mappedBy = "fkContractTermsId",cascade= CascadeType.ALL)
    private Collection<MasterContractTermsPurchase> masterContractTermsPurchaseCollection;

    public MasterContractTerms() {
    }

    public MasterContractTerms(Integer pkContractTermsId) {
        this.pkContractTermsId = pkContractTermsId;
    }
    
    public MasterContractTerms(Integer pkContractTermsId, String contractTermsName, String contractTermsCode) {
        this.pkContractTermsId = pkContractTermsId;
        this.contractTermsName = contractTermsName;
        this.contractTermsCode = contractTermsCode;
    }

    public Integer getPkContractTermsId() {
        return pkContractTermsId;
    }

    public void setPkContractTermsId(Integer pkContractTermsId) {
        this.pkContractTermsId = pkContractTermsId;
    }

    public String getContractTermsCode() {
        return contractTermsCode;
    }

    public void setContractTermsCode(String contractTermsCode) {
        this.contractTermsCode = contractTermsCode;
    }

    public String getContractTermsName() {
        return contractTermsName;
    }

    public void setContractTermsName(String contractTermsName) {
        this.contractTermsName = contractTermsName;
    }

    public String getContractTermsDesc() {
        return contractTermsDesc;
    }

    public void setContractTermsDesc(String contractTermsDesc) {
        this.contractTermsDesc = contractTermsDesc;
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

    public Integer getFkProdId() {
        return fkProdId;
    }

    public void setFkProdId(Integer fkProdId) {
        this.fkProdId = fkProdId;
    }

    @XmlTransient
    public Collection<MasterContractTermsSales> getMasterContractTermsSalesCollection() {
        return masterContractTermsSalesCollection;
    }

    public void setMasterContractTermsSalesCollection(Collection<MasterContractTermsSales> masterContractTermsSalesCollection) {
        this.masterContractTermsSalesCollection = masterContractTermsSalesCollection;
    }

    public MasterContractTermsBaseRef getFkContractTermsBaseRefId() {
        return fkContractTermsBaseRefId;
    }

    public void setFkContractTermsBaseRefId(MasterContractTermsBaseRef fkContractTermsBaseRefId) {
        this.fkContractTermsBaseRefId = fkContractTermsBaseRefId;
    }

    public MasterContractTermsBasisRef getFkContractTermsBasisRefId() {
        return fkContractTermsBasisRefId;
    }

    public void setFkContractTermsBasisRefId(MasterContractTermsBasisRef fkContractTermsBasisRefId) {
        this.fkContractTermsBasisRefId = fkContractTermsBasisRefId;
    }

    @XmlTransient
    public Collection<MasterContractTermsPurchase> getMasterContractTermsPurchaseCollection() {
        return masterContractTermsPurchaseCollection;
    }

    public void setMasterContractTermsPurchaseCollection(Collection<MasterContractTermsPurchase> masterContractTermsPurchaseCollection) {
        this.masterContractTermsPurchaseCollection = masterContractTermsPurchaseCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkContractTermsId != null ? pkContractTermsId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterContractTerms)) {
            return false;
        }
        MasterContractTerms other = (MasterContractTerms) object;
        if ((this.pkContractTermsId == null && other.pkContractTermsId != null) || (this.pkContractTermsId != null && !this.pkContractTermsId.equals(other.pkContractTermsId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.terms.domain.MasterContractTerms[ pkContractTermsId=" + pkContractTermsId + " ]";
    }

	public ContractTermsDto convertToContractTermsDto(Map<Integer, String> statusMap, List<Map<Object, Object>> prodList, List<Map<Object, Object>> costList, List<Map<Object, Object>> costGroup) {
		ContractTermsDto dto;

		ModelMapper modelMapper = new ModelMapper();
		dto = modelMapper.map(this, ContractTermsDto.class);
		if(prodList!=null)
		{
			String prodName = null;
			String prodCode = null;
			 Integer prodId = null;
			 for (int x = 0; x < prodList.size(); x++) {
				prodId = (Integer) prodList.get(x).get("prodId");
				
				if (prodId != null && prodId.equals(this.getFkProdId())) {
					prodName = (String) prodList.get(x).get("prodName");
					prodCode = (String) prodList.get(x).get("prodCode");
					dto.setProdName(prodName);
					dto.setProdCode(prodCode);
					dto.setProdId(prodId);
					break;
				}
			}
		} 
		
		
		
		Collection<MasterContractTermsPurchase> termsPurchase = this.getMasterContractTermsPurchaseCollection();
		
		if(!termsPurchase.isEmpty()){
			List<ContractTermsPurchaseDto> contractTermsPurchaseDtoLst = new ArrayList<>();

			for (MasterContractTermsPurchase masterContractTermsPurchase : termsPurchase) {
				ContractTermsPurchaseDto contractTermsPurchaseDto = new ContractTermsPurchaseDto();
				contractTermsPurchaseDto.setContractTermsId(masterContractTermsPurchase.getFkContractTermsId().getPkContractTermsId());
				contractTermsPurchaseDto.setContractTermsPurchaseId(masterContractTermsPurchase.getPkContractTermsPurchaseId());
				contractTermsPurchaseDto.setStatusName(statusMap.get(getFkStatusId()));
				contractTermsPurchaseDto.setCostId(masterContractTermsPurchase.getFkCostId());
				contractTermsPurchaseDto.setBudgetInd(masterContractTermsPurchase.getBudgetInd());
				contractTermsPurchaseDto.setContrTrmsAddreduceRefId(masterContractTermsPurchase.getFkContrTrmsAddreduceRefId().getPkContrTrmsAddreduceRefId());
				contractTermsPurchaseDto.setAddReduceCode(masterContractTermsPurchase.getFkContrTrmsAddreduceRefId().getAddReduceCode());
				contractTermsPurchaseDto.setAddReduceName(masterContractTermsPurchase.getFkContrTrmsAddreduceRefId().getAddReduceName());
				contractTermsPurchaseDto.setCostGroupId(masterContractTermsPurchase.getFkCostGroupId());
				if(costList!=null)
				{
					String costName = null;
					String costCode = null;
					 Integer costId = null;
					 for (int x = 0; x < costList.size(); x++) {
						 costId = (Integer) costList.get(x).get("pkCostId");
						
						if (costId != null && costId.equals(masterContractTermsPurchase.getFkCostId())) {
							costName = (String) costList.get(x).get("costName");
							costCode = (String) costList.get(x).get("costCode");
							contractTermsPurchaseDto.setCostName(costName);
							contractTermsPurchaseDto.setCostCode(costCode);
							contractTermsPurchaseDto.setCostId(costId);
							break;
						}
					}
				}		
				//costgroup service call...
				if(costGroup!=null)
				{
					String costGroupName = null;
					String costGroupCode = null;
					 Integer costGroupId = null;
					 for (int x = 0; x < costGroup.size(); x++) {
						 costGroupId = (Integer) costGroup.get(x).get("costGroupId");
						if (costGroupId != null && costGroupId.equals(masterContractTermsPurchase.getFkCostGroupId())) {
							costGroupName = (String) costGroup.get(x).get("costGroupName");
							costGroupCode = (String) costGroup.get(x).get("costGroupCode");
							contractTermsPurchaseDto.setCostGroupName(costGroupName);
							contractTermsPurchaseDto.setCostGroupCode(costGroupCode);
							contractTermsPurchaseDto.setCostGroupId(costGroupId);
							break;
						}
					}
				}	
				contractTermsPurchaseDtoLst.add(contractTermsPurchaseDto);
			}
			dto.setContractTermsPurchaseDto(contractTermsPurchaseDtoLst);
		}
		
Collection<MasterContractTermsSales> termsSales = this.getMasterContractTermsSalesCollection();
		
		if(!termsSales.isEmpty()){
			List<ContractTermsSalesDto> contractTermsSalesDtoLst = new ArrayList<>();

			for (MasterContractTermsSales masterContractTermsSales : termsSales) {
				ContractTermsSalesDto contractTermsSalesDto = new ContractTermsSalesDto();
				contractTermsSalesDto.setContractTermsId(masterContractTermsSales.getFkContractTermsId().getPkContractTermsId());
				contractTermsSalesDto.setContractTermsSalesId(masterContractTermsSales.getPkContractTermsSalesId());
				contractTermsSalesDto.setStatusName(statusMap.get(getFkStatusId()));
				contractTermsSalesDto.setCostId(masterContractTermsSales.getFkCostId());
				contractTermsSalesDto.setBudgetInd(masterContractTermsSales.getBudgetInd());
				contractTermsSalesDto.setContrTrmsAddreduceRefId(masterContractTermsSales.getFkContrTrmsAddreduceRefId().getPkContrTrmsAddreduceRefId());
				contractTermsSalesDto.setAddReduceCode(masterContractTermsSales.getFkContrTrmsAddreduceRefId().getAddReduceCode());
				contractTermsSalesDto.setAddReduceName(masterContractTermsSales.getFkContrTrmsAddreduceRefId().getAddReduceName());
				contractTermsSalesDto.setCostGroupId(masterContractTermsSales.getFkCostGroupId());
				if(costList!=null)
				{
					String costName = null;
					String costCode = null;
					 Integer costId = null;
					 for (int x = 0; x < costList.size(); x++) {
						 costId = (Integer) costList.get(x).get("pkCostId");
						
						if (costId != null && costId.equals(masterContractTermsSales.getFkCostId())) {
							costName = (String) costList.get(x).get("costName");
							costCode = (String) costList.get(x).get("costCode");
							contractTermsSalesDto.setCostName(costName);
							contractTermsSalesDto.setCostCode(costCode);
							contractTermsSalesDto.setCostId(costId);
							break;
						}
					}
				}		
				
				//costgroup service call...
				if(costGroup!=null)
				{
					String costGroupName = null;
					String costGroupCode = null;
					 Integer costGroupId = null;
					 for (int x = 0; x < costGroup.size(); x++) {
						 costGroupId = (Integer) costGroup.get(x).get("costGroupId");
						
						if (costGroupId != null && costGroupId.equals(masterContractTermsSales.getFkCostGroupId())) {
							costGroupName = (String) costGroup.get(x).get("costGroupName");
							costGroupCode = (String) costGroup.get(x).get("costGroupCode");
							contractTermsSalesDto.setCostGroupName(costGroupName);
							contractTermsSalesDto.setCostGroupCode(costGroupCode);
							contractTermsSalesDto.setCostGroupId(costGroupId);
							break;
						}
					}
				}	
				contractTermsSalesDtoLst.add(contractTermsSalesDto);
			}
			dto.setContractTermsSalesDto(contractTermsSalesDtoLst);
		}
		
		dto.setStatusName(statusMap.get(getFkStatusId()));
		return dto;
	
	}
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olam.score.product.domain;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;
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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.olam.score.common.dto.ResponseData;
import com.olam.score.common.util.CommonUtil;
import com.olam.score.product.dto.ExternalMappingDto;
import com.olam.score.product.dto.ExternalSystemRefDto;
import com.olam.score.product.dto.InternationalGradeCodeMappingDto;
import com.olam.score.product.dto.PartyDto;
import com.olam.score.product.dto.ProcessingSubTypeDto;
import com.olam.score.product.dto.ProductArbitrationDto;
import com.olam.score.product.dto.ProductDto;
import com.olam.score.product.dto.InternationalGradeCodeMappingDto;

/**
 *
 * @author ramachandran_n02
 */
@Entity
@Table(name = "MASTER_PRODUCT",  schema = "product")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterProduct.findAll", query = "SELECT m FROM MasterProduct m")})
public class MasterProduct implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MASTER_PRODUCT_GENERATOR")
	@SequenceGenerator(name="MASTER_PRODUCT_GENERATOR", sequenceName="product.PRODUCT_CLASSIFICATION_REF_ID_SEQ",allocationSize=1)
    @NotNull
    @Column(name = "PK_PROD_ID")
    private Integer pkProdId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "PROD_CODE")
    private String prodCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "PROD_NAME")
    private String prodName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "PROD_FULLNAME")
    private String prodFullname;
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
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "PROD_IS_TRADED_PROD")
    private String prodIsTradedProd;
    @Size(max = 1)
    @Column(name = "PROD_SPECIALITY_APPLICABLE")
    private String prodSpecialityApplicable;
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
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "fkProdId")
    private Collection<MasterProductArbitrationMapping> masterProductArbitrationMappingCollection;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "fkProdId")
    private Collection<MasterGrade> masterGradeCollection;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "fkParentProdId")
    private Collection<MasterProduct> masterProductCollection;
    @JoinColumn(name = "FK_PARENT_PROD_ID", referencedColumnName = "PK_PROD_ID")
    @ManyToOne
    private MasterProduct fkParentProdId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkProdCode")
    private Collection<MasterProductParty> masterProductPartyCollection;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "fkProdId")
    private Collection<MasterProdExternalMapping> masterProdExternalMappingCollection;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "fkProdId")
    private Collection<MasterProductIcoIndex> masterProductIcoIndexCollection;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "fkProdId")
    private Collection<MasterProductProcess> masterProductProcessCollection;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "fkProdId")
    private Collection<MasterProductIntlCode> masterProductIntlCodeCollection;
    @Transient
    private Integer parentProdId;

    public Integer getParentProdId() {
		return parentProdId;
	}

	public void setParentProdId(Integer parentProdId) {
		this.parentProdId = parentProdId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public MasterProduct() {
    }

    public MasterProduct(Integer pkProdId) {
        this.pkProdId = pkProdId;
    }

    public MasterProduct(Integer pkProdId, String prodCode, String prodName, String prodFullname, String prodIsTradedProd) {
        this.pkProdId = pkProdId;
        this.prodCode = prodCode;
        this.prodName = prodName;
        this.prodFullname = prodFullname;
        this.prodIsTradedProd = prodIsTradedProd;
    }
    
    @XmlTransient
    public Collection<MasterProductIntlCode> getMasterProductIntlCodeCollection() {
        return masterProductIntlCodeCollection;
    }

    public void setMasterProductIntlCodeCollection(Collection<MasterProductIntlCode> masterProductIntlCodeCollection) {
        this.masterProductIntlCodeCollection = masterProductIntlCodeCollection;
    }
    
    @XmlTransient
    public Collection<MasterProductProcess> getMasterProductProcessCollection() {
        return masterProductProcessCollection;
    }

    public void setMasterProductProcessCollection(Collection<MasterProductProcess> masterProductProcessCollection) {
        this.masterProductProcessCollection = masterProductProcessCollection;
    }
    
    @XmlTransient
    public Collection<MasterProductIcoIndex> getMasterProductIcoIndexCollection() {
        return masterProductIcoIndexCollection;
    }

    public void setMasterProductIcoIndexCollection(Collection<MasterProductIcoIndex> masterProductIcoIndexCollection) {
        this.masterProductIcoIndexCollection = masterProductIcoIndexCollection;
    }
    
    @XmlTransient
    public Collection<MasterProdExternalMapping> getMasterProdExternalMappingCollection() {
        return masterProdExternalMappingCollection;
    }

    public void setMasterProdExternalMappingCollection(Collection<MasterProdExternalMapping> masterProdExternalMappingCollection) {
        this.masterProdExternalMappingCollection = masterProdExternalMappingCollection;
    }

    public Integer getPkProdId() {
        return pkProdId;
    }

    public void setPkProdId(Integer pkProdId) {
        this.pkProdId = pkProdId;
    }

    public String getProdCode() {
        return prodCode;
    }

    public void setProdCode(String prodCode) {
        this.prodCode = prodCode;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getProdFullname() {
        return prodFullname;
    }

    public void setProdFullname(String prodFullname) {
        this.prodFullname = prodFullname;
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

    public String getProdIsTradedProd() {
        return prodIsTradedProd;
    }

    public void setProdIsTradedProd(String prodIsTradedProd) {
        this.prodIsTradedProd = prodIsTradedProd;
    }

    public String getProdSpecialityApplicable() {
        return prodSpecialityApplicable;
    }

    public void setProdSpecialityApplicable(String prodSpecialityApplicable) {
        this.prodSpecialityApplicable = prodSpecialityApplicable;
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
    
  

    @XmlTransient
    public Collection<MasterProductArbitrationMapping> getMasterProductArbitrationMappingCollection() {
        return masterProductArbitrationMappingCollection;
    }

    public void setMasterProductArbitrationMappingCollection(Collection<MasterProductArbitrationMapping> masterProductArbitrationMappingCollection) {
        this.masterProductArbitrationMappingCollection = masterProductArbitrationMappingCollection;
    }

    @XmlTransient
    public Collection<MasterGrade> getMasterGradeCollection() {
        return masterGradeCollection;
    }

    public void setMasterGradeCollection(Collection<MasterGrade> masterGradeCollection) {
        this.masterGradeCollection = masterGradeCollection;
    }

    @XmlTransient
    public Collection<MasterProduct> getMasterProductCollection() {
        return masterProductCollection;
    }

    public void setMasterProductCollection(Collection<MasterProduct> masterProductCollection) {
        this.masterProductCollection = masterProductCollection;
    }

    public MasterProduct getFkParentProdId() {
        return fkParentProdId;
    }

    public void setFkParentProdId(MasterProduct fkParentProdId) {
        this.fkParentProdId = fkParentProdId;
    }

    @XmlTransient
    public Collection<MasterProductParty> getMasterProductPartyCollection() {
        return masterProductPartyCollection;
    }

    public void setMasterProductPartyCollection(Collection<MasterProductParty> masterProductPartyCollection) {
        this.masterProductPartyCollection = masterProductPartyCollection;
    }
    public ProductDto convertEntityToDto(Map<Integer, String> statusMap, List<Map<Object, Object>> partylist, List<Map<Object, Object>> financelist, List<MasterIntlCodeTypeRef> codeTypeRefList, List<MasterProcessSubTypeRef> subTypeRef) {
    	ProductDto dto= new ProductDto();
    	dto.setProdId(pkProdId);
    	dto.setStatusId(fkStatusId);
    	dto.setProdName(prodName);
    	dto.setProdFullName(prodFullname);
    	dto.setProdCode(prodCode);
    	dto.setCreatedBy(createdBy);
    	dto.setCreatedDate((Timestamp) createdDate);
    	dto.setSpecialityApplicable(prodSpecialityApplicable);
    	dto.setModifiedBy(modifiedBy);
    	dto.setModifiedDate((Timestamp) modifiedDate);
    	dto.setStatusName(statusMap.get(fkStatusId));
    	if(getFkParentProdId()!=null){
    	dto.setParentProductId(getFkParentProdId().pkProdId);
    	dto.setParentProductName(getFkParentProdId().prodName);
    	dto.setIsBaseProduct("N");
    	}else{
    	dto.setIsBaseProduct("Y");
    	}
    	Collection<MasterProductProcess> prodProcess = getMasterProductProcessCollection();
    	for(MasterProductProcess masterProductProcess :prodProcess){
    		dto.setProcessMappingId(masterProductProcess.getPkProductProcessId());
    		dto.setProcessingCode(masterProductProcess.getProcessingCode());
    		dto.setProcessingName(masterProductProcess.getProcessingName());
    		Collection<MasterProductProcessSub> processSubList=masterProductProcess.getMasterProductProcessSubCollection();
    		List<ProcessingSubTypeDto> processingSubTypeDtolist=new ArrayList<>();
    		for(MasterProductProcessSub subList: processSubList){
    			ProcessingSubTypeDto processingSubTypeDto=new ProcessingSubTypeDto();
    			processingSubTypeDto.setProcessSubId(subList.getFkProcessSubTypeRefId());
    			processingSubTypeDto.setMappingId(subList.getPkProductProcessSubId());
    			for(MasterProcessSubTypeRef masterProcessSubTypeRef : subTypeRef){
        			if(masterProcessSubTypeRef.getPkProcessSubTypeRefId().equals(subList.getFkProcessSubTypeRefId())){
        				processingSubTypeDto.setProcessSubCode(masterProcessSubTypeRef.getProcessSubTypeCode());
        	    		processingSubTypeDto.setProcessSubName(masterProcessSubTypeRef.getProcessSubTypeName());
        			}
        		}
    			processingSubTypeDtolist.add(processingSubTypeDto);
    		}
    		dto.setProcessingSubType(processingSubTypeDtolist);
    		
    	}
    	Collection<MasterProductIcoIndex> icoIndex=getMasterProductIcoIndexCollection();
    	for(MasterProductIcoIndex masterProductIcoIndex:icoIndex){
    		dto.setIcoMappingId(masterProductIcoIndex.getPkProductIcoIndex());
    		dto.setIcoCode(masterProductIcoIndex.getIcoCode());
    		dto.setIcoName(masterProductIcoIndex.getIcoName());
    	}
    	Collection<ProductArbitrationDto> productArbitrationCollection = new ArrayList<>();
    	Collection<MasterProductArbitrationMapping> arbiterationMapping=getMasterProductArbitrationMappingCollection();

    	for(MasterProductArbitrationMapping productArbitrationMapping:arbiterationMapping){
    		ProductArbitrationDto productArbitrationDto =new ProductArbitrationDto();
    		productArbitrationDto.setArbitrationAgencyId(productArbitrationMapping.getFkArbitrationAgencyId());
    		productArbitrationDto.setTableMappingId(productArbitrationMapping.getPkProdArbitrationMappingId());
    		for (int i = 0; i < partylist.size(); i++) {
    		Integer id = (Integer)partylist.get(i).get("partyId");
    		if(id.equals(productArbitrationMapping.getFkArbitrationAgencyId())){
    		productArbitrationDto.setArbitrationFullName((String)partylist.get(i).get("partyName"));
    		productArbitrationDto.setArbitrationShortName((String)partylist.get(i).get("partyCode"));
    		}
    		}
    		productArbitrationCollection.add(productArbitrationDto); 
    	}
    	dto.setProductArbitrationCollection(productArbitrationCollection);
    	Collection<MasterProdExternalMapping> externalMapping=getMasterProdExternalMappingCollection();
    	for(MasterProdExternalMapping prodExternalMapping : externalMapping){
    		dto.setAttribute1(prodExternalMapping.getCustomAttribute1());
    		dto.setAttribute2(prodExternalMapping.getCustomAttribute2());
    		dto.setDestinationSystemId(prodExternalMapping.getFkExternalSystemRefId());
    		dto.setExternalSystemMappingId(prodExternalMapping.getPkProdExternalMappingId());
    		for (int i = 0; i < financelist.size(); i++) {
    		if(prodExternalMapping.getFkExternalSystemRefId()==financelist.get(i).get("externalSystemRefId")){
    			dto.setDestinationName((String)financelist.get(i).get("externalSystemRefName"));
    		}
    		}
	    	dto.setDestinationSystemId(prodExternalMapping.getFkExternalSystemRefId());
	    	Collection<MasterProductIntlCode> productGradeList=getMasterProductIntlCodeCollection();
	    	Collection<InternationalGradeCodeMappingDto> IntlCode=new ArrayList<>();
	    	for(MasterProductIntlCode productIntlCode: productGradeList){
	    		InternationalGradeCodeMappingDto gradeCodeMappingdto=new InternationalGradeCodeMappingDto();
	    		gradeCodeMappingdto.setCode(productIntlCode.getIntlCode());
	    		gradeCodeMappingdto.setCodeTypeId(productIntlCode.getFkIntlCodeTypeRefId());
	    		gradeCodeMappingdto.setDescription(productIntlCode.getDesc());
	    		gradeCodeMappingdto.setTableMappingId(productIntlCode.getPkProductIntlCodeId());
	    		for(MasterIntlCodeTypeRef masterIntlCodeTypeRef:codeTypeRefList){
	    			if(productIntlCode.getFkIntlCodeTypeRefId()!=null&&productIntlCode.getFkIntlCodeTypeRefId().equals(masterIntlCodeTypeRef.getPkIntlCodeTypeRefId())){
	    		gradeCodeMappingdto.setCodeType(masterIntlCodeTypeRef.getIntlCodeTypeCode());
	    			}}
	    		IntlCode.add(gradeCodeMappingdto);
	    	}
	    	dto.setGradeCodeMappingCollection(IntlCode);

    	}
		return dto;
    	
    }
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkProdId != null ? pkProdId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterProduct)) {
            return false;
        }
        MasterProduct other = (MasterProduct) object;
        if ((this.pkProdId == null && other.pkProdId != null) || (this.pkProdId != null && !this.pkProdId.equals(other.pkProdId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.product.domain.MasterProduct[ pkProdId=" + pkProdId + " ]";
    }

	public ProductDto convertBasicEntityToDto() {
	   	ProductDto dto= new ProductDto();
    	dto.setProdId(pkProdId);
    	dto.setStatusId(fkStatusId);
    	dto.setProdName(prodName);
    	dto.setProdFullName(prodFullname);
    	dto.setProdCode(prodCode);
 		return dto;
	}
    
}

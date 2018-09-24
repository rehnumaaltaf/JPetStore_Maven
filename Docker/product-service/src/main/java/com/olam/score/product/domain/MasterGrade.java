/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olam.score.product.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
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

import com.olam.score.product.dto.BrandDto;
import com.olam.score.product.dto.GradeDto;
import com.olam.score.product.dto.IntlGradeDto;
import com.olam.score.product.dto.OriginGradeDto;
import com.olam.score.product.dto.ProcessSubTypeRefDto;
import com.olam.score.product.dto.ProcessTypeDto;
import com.olam.score.product.dto.ProductDto;

/**
 *
 * @author ramachandran_n02
 */
@Entity
@Table(name = "MASTER_GRADE",  schema = "product")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterGrade.findAll", query = "SELECT m FROM MasterGrade m")})
public class MasterGrade implements Serializable {

    private static final long serialVersionUID = 1L;
	@SequenceGenerator(name="MASTER_GRADE_GRADEIDSEQ_GENERATOR", sequenceName="GRADE_ID_SEQ",allocationSize=1)
	@GeneratedValue(generator="MASTER_GRADE_GRADEIDSEQ_GENERATOR")
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_GRADE_ID")
    private Integer pkGradeId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "GRADE_NAME")
    private String gradeName;
    @Size(max = 200)
    @Column(name = "GRADE_FULLNAME")
    private String gradeFullname;
    @Size(max = 20)
    @Column(name = "GRADE_CODE")
    private String gradeCode;
    @Size(max = 1)
    @Column(name = "GRADE_IS_TENDERABLE")
    private String gradeIsTenderable;
    @Size(max = 50)
    @Column(name = "GRADE_HEDGE_RATIO")
    private String gradeHedgeRatio;
    @Size(max = 20)
    @Column(name = "GRADE_IS_WASHED")
    private String gradeIsWashed;
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
    @Column(name = "FK_DEFAULT_GRADE_UOM_ID")
    private Integer fkDefaultGradeUomId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "GRADE_IS_BASE")
    private String gradeIsBase;
    @Column(name = "FK_STATUS_ID")
    private Integer fkStatusId;
    @Size(max = 1)
    @Column(name = "GRADE_IS_BRAND")
    private String gradeIsBrand;
    @Size(max = 20)
    @Column(name = "GRADE_BRAND_LOGO")
    private String gradeBrandLogo;
    @Size(max = 1)
    @Column(name = "GRADE_IS_BLENDED")
    private String gradeIsBlended;
    @Size(max = 1)
    @Column(name = "GRADE_INCLUDE_IN_POSITION")
    private String gradeIncludeInPosition;
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
    @Basic(optional = false)
    @Column(name = "FK_BRAND_ID")
    private Integer fkBrandId;
    @OneToMany(mappedBy = "fkParentGradeId")
    private Collection<MasterGrade> masterGradeCollection;
    @JoinColumn(name = "FK_PARENT_GRADE_ID", referencedColumnName = "PK_GRADE_ID")
    @ManyToOne
    private MasterGrade fkParentGradeId;
    @JoinColumn(name = "FK_ORIGIN_CUP_PROFILE_ID", referencedColumnName = "PK_ORIGIN_CUP_PROFILE_ID")
    @ManyToOne
    private MasterOriginCupProfile fkOriginCupProfileId;
    @JoinColumn(name = "FK_PROD_ID", referencedColumnName = "PK_PROD_ID")
    @ManyToOne
    private MasterProduct fkProdId;
    @JoinColumn(name = "FK_QLTY_TEMP_ID", referencedColumnName = "PK_QLTY_TEMP_ID")
    @ManyToOne
    private MasterQualityTemplate fkQltyTempId;
    @JoinColumn(name = "FK_WASHED_TYPE_ID", referencedColumnName = "PK_WASHED_TYPE_ID")
    @ManyToOne
    private MasterWashedType fkWashedTypeId;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "fkGradeId")
    private Collection<MasterProductGlMapping> masterProductGlMappingCollection;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "fkGradeId")
    private Collection<MasterGradeOriginAssoc> masterGradeOriginAssocCollection;
    @JoinColumn(name = "FK_PRODUCT_PROCESS_ID", referencedColumnName = "PK_PRODUCT_PROCESS_ID")
    @ManyToOne
    private MasterProductProcess fkProductProcessId;
    @JoinColumn(name = "FK_PRODUCT_PROCESS_SUB_ID", referencedColumnName = "PK_PRODUCT_PROCESS_SUB_ID")
    @ManyToOne
    private MasterProductProcessSub fkProductProcessSubId;
    @JoinColumn(name = "FK_PRODUCT_ICO_INDEX", referencedColumnName = "PK_PRODUCT_ICO_INDEX")
    @ManyToOne
    private MasterProductIcoIndex fkProductIcoIndex;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "fkGradeId")
    private Collection<MasterGradeIntlCode> masterGradeIntlCodeCollection;

    @Size(max = 1)
    @Column(name = "GRADE_IS_RAW")
    private String gradeIsRaw;
    @Column(name = "PREMIUM_DISCOUNT_OVER_BASE_VAL")
    private BigDecimal premDisc;
	@Column(name = "FK_PREMIUM_DISCOUNT_CURRENCY_ID")
    private Integer preDiscCurrencyId;
	@JoinColumn(name = "FK_GRADE_GROUP_REF_ID", referencedColumnName = "PK_GRADE_GROUP_REF_ID")
    @ManyToOne
    private MasterGradeGroupRef fkgradeGroupRef;
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "fkGradeId")
    private Collection<MasterGradeExternalMapping> gradeExternalMappings;
	
    public Collection<MasterGradeExternalMapping> getGradeExternalMappings() {
		return gradeExternalMappings;
	}

	public void setGradeExternalMappings(Collection<MasterGradeExternalMapping> gradeExternalMappings) {
		this.gradeExternalMappings = gradeExternalMappings;
	}

	public String getGradeIsRaw() {
		return gradeIsRaw;
	}

	public void setGradeIsRaw(String gradeIsRaw) {
		this.gradeIsRaw = gradeIsRaw;
	}

	public BigDecimal getPremDisc() {
		return premDisc;
	}

	public void setPremDisc(BigDecimal premDisc) {
		this.premDisc = premDisc;
	}

	public Integer getPreDiscCurrencyId() {
		return preDiscCurrencyId;
	}

	public void setPreDiscCurrencyId(Integer preDiscCurrencyId) {
		this.preDiscCurrencyId = preDiscCurrencyId;
	}

	public MasterGradeGroupRef getFkgradeGroupRef() {
		return fkgradeGroupRef;
	}

	public void setFkgradeGroupRef(MasterGradeGroupRef fkgradeGroupRef) {
		this.fkgradeGroupRef = fkgradeGroupRef;
	}

	public MasterGrade() {
    }

    public MasterGrade(Integer pkGradeId) {
        this.pkGradeId = pkGradeId;
    }

    public MasterGrade(Integer pkGradeId, String gradeName, String gradeIsBase, Integer fkBrandId) {
        this.pkGradeId = pkGradeId;
        this.gradeName = gradeName;
        this.gradeIsBase = gradeIsBase;
        this.fkBrandId = fkBrandId;
    }
    
    @XmlTransient
    public Collection<MasterGradeIntlCode> getMasterGradeIntlCodeCollection() {
        return masterGradeIntlCodeCollection;
    }

    public void setMasterGradeIntlCodeCollection(Collection<MasterGradeIntlCode> masterGradeIntlCodeCollection) {
        this.masterGradeIntlCodeCollection = masterGradeIntlCodeCollection;
    }
    
    public MasterProductIcoIndex getFkProductIcoIndex() {
        return fkProductIcoIndex;
    }

    public void setFkProductIcoIndex(MasterProductIcoIndex fkProductIcoIndex) {
        this.fkProductIcoIndex = fkProductIcoIndex;
    }

    public MasterProductProcess getFkProductProcessId() {
        return fkProductProcessId;
    }

    public void setFkProductProcessId(MasterProductProcess fkProductProcessId) {
        this.fkProductProcessId = fkProductProcessId;
    }

    public MasterProductProcessSub getFkProductProcessSubId() {
        return fkProductProcessSubId;
    }

    public void setFkProductProcessSubId(MasterProductProcessSub fkProductProcessSubId) {
        this.fkProductProcessSubId = fkProductProcessSubId;
    }
    
    public Integer getPkGradeId() {
        return pkGradeId;
    }

    public void setPkGradeId(Integer pkGradeId) {
        this.pkGradeId = pkGradeId;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getGradeFullname() {
        return gradeFullname;
    }

    public void setGradeFullname(String gradeFullname) {
        this.gradeFullname = gradeFullname;
    }

    public String getGradeCode() {
        return gradeCode;
    }

    public void setGradeCode(String gradeCode) {
        this.gradeCode = gradeCode;
    }

    public String getGradeIsTenderable() {
        return gradeIsTenderable;
    }

    public void setGradeIsTenderable(String gradeIsTenderable) {
        this.gradeIsTenderable = gradeIsTenderable;
    }

    public String getGradeHedgeRatio() {
        return gradeHedgeRatio;
    }

    public void setGradeHedgeRatio(String gradeHedgeRatio) {
        this.gradeHedgeRatio = gradeHedgeRatio;
    }

    public String getGradeIsWashed() {
        return gradeIsWashed;
    }

    public void setGradeIsWashed(String gradeIsWashed) {
        this.gradeIsWashed = gradeIsWashed;
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

    public Integer getFkDefaultGradeUomId() {
        return fkDefaultGradeUomId;
    }

    public void setFkDefaultGradeUomId(Integer fkDefaultGradeUomId) {
        this.fkDefaultGradeUomId = fkDefaultGradeUomId;
    }

    public String getGradeIsBase() {
        return gradeIsBase;
    }

    public void setGradeIsBase(String gradeIsBase) {
        this.gradeIsBase = gradeIsBase;
    }

    public Integer getFkStatusId() {
        return fkStatusId;
    }

    public void setFkStatusId(Integer fkStatusId) {
        this.fkStatusId = fkStatusId;
    }

    public String getGradeIsBrand() {
        return gradeIsBrand;
    }

    public void setGradeIsBrand(String gradeIsBrand) {
        this.gradeIsBrand = gradeIsBrand;
    }

    public String getGradeBrandLogo() {
        return gradeBrandLogo;
    }

    public void setGradeBrandLogo(String gradeBrandLogo) {
        this.gradeBrandLogo = gradeBrandLogo;
    }

    public String getGradeIsBlended() {
        return gradeIsBlended;
    }

    public void setGradeIsBlended(String gradeIsBlended) {
        this.gradeIsBlended = gradeIsBlended;
    }

    public String getGradeIncludeInPosition() {
        return gradeIncludeInPosition;
    }

    public void setGradeIncludeInPosition(String gradeIncludeInPosition) {
        this.gradeIncludeInPosition = gradeIncludeInPosition;
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

    public Integer getFkBrandId() {
        return fkBrandId;
    }

    public void setFkBrandId(Integer fkBrandId) {
        this.fkBrandId = fkBrandId;
    }

    @XmlTransient
    public Collection<MasterGrade> getMasterGradeCollection() {
        return masterGradeCollection;
    }

    public void setMasterGradeCollection(Collection<MasterGrade> masterGradeCollection) {
        this.masterGradeCollection = masterGradeCollection;
    }

    public MasterGrade getFkParentGradeId() {
        return fkParentGradeId;
    }

    public void setFkParentGradeId(MasterGrade fkParentGradeId) {
        this.fkParentGradeId = fkParentGradeId;
    }

    public MasterOriginCupProfile getFkOriginCupProfileId() {
        return fkOriginCupProfileId;
    }

    public void setFkOriginCupProfileId(MasterOriginCupProfile fkOriginCupProfileId) {
        this.fkOriginCupProfileId = fkOriginCupProfileId;
    }

    public MasterProduct getFkProdId() {
        return fkProdId;
    }

    public void setFkProdId(MasterProduct fkProdId) {
        this.fkProdId = fkProdId;
    }

    public MasterQualityTemplate getFkQltyTempId() {
        return fkQltyTempId;
    }

    public void setFkQltyTempId(MasterQualityTemplate fkQltyTempId) {
        this.fkQltyTempId = fkQltyTempId;
    }

    public MasterWashedType getFkWashedTypeId() {
        return fkWashedTypeId;
    }

    public void setFkWashedTypeId(MasterWashedType fkWashedTypeId) {
        this.fkWashedTypeId = fkWashedTypeId;
    }

    @XmlTransient
    public Collection<MasterProductGlMapping> getMasterProductGlMappingCollection() {
        return masterProductGlMappingCollection;
    }

    public void setMasterProductGlMappingCollection(Collection<MasterProductGlMapping> masterProductGlMappingCollection) {
        this.masterProductGlMappingCollection = masterProductGlMappingCollection;
    }

    @XmlTransient
    public Collection<MasterGradeOriginAssoc> getMasterGradeOriginAssocCollection() {
        return masterGradeOriginAssocCollection;
    }

    public void setMasterGradeOriginAssocCollection(Collection<MasterGradeOriginAssoc> masterGradeOriginAssocCollection) {
        this.masterGradeOriginAssocCollection = masterGradeOriginAssocCollection;
    }

    public GradeDto gradeModelToDto(List<MasterGradeOriginAssoc> gradeOriginAssocList, Map<Integer, String> statusMap,
			List<Map<Object, Object>> brands, List<MasterProcessSubTypeRef> subTypeRef,
			List<Map<Object, Object>> uomList, List<Map<Object, Object>> currencyList) {
		GradeDto gradeDto = new GradeDto();
		gradeDto.setStatusId(fkStatusId);
		gradeDto.setStatusName(statusMap.get(fkStatusId));
		gradeDto.setGradeId(pkGradeId);
		gradeDto.setGradeCode(gradeCode);
		gradeDto.setGradeName(gradeName);
		gradeDto.setGradeFullName(gradeFullname);
		if (fkProdId != null) {
			ProductDto productDto = new ProductDto();
			productDto.setProdId(fkProdId.getPkProdId());
			productDto.setProdCode(fkProdId.getProdCode());
			productDto.setProdName(fkProdId.getProdName());
			gradeDto.setProduct(productDto);
		}
		gradeDto.setOriginList(generateOriginDto(gradeOriginAssocList));
		gradeDto.setIsBlended(gradeIsBlended);
		gradeDto.setIsBrand(gradeIsBrand);

		BrandDto brandDto = new BrandDto();
		if (fkBrandId != null) {
			for (Map<Object, Object> brand : brands) {
				if (fkBrandId.equals(Integer.parseInt((String) brand.get("brandId")))) {
					brandDto.setBrandId((String) brand.get("brandId"));
					brandDto.setBrandCode((String) brand.get("brandCode"));
					brandDto.setBrandName((String) brand.get("brandName"));
					gradeDto.setBrand(brandDto);
					break;
				}

			}
			
		}
		
		gradeDto.setIsBase(gradeIsBase);
		if (fkParentGradeId != null) {
			gradeDto.setBaseGradeId(fkParentGradeId.getPkGradeId());
			gradeDto.setBaseGradeName(fkParentGradeId.getGradeName());
		}
		
		if (fkProductProcessId != null) {
			ProcessTypeDto processTypeDto = new ProcessTypeDto();
			processTypeDto.setPkProductProcessId(fkProductProcessId.getPkProductProcessId());
			processTypeDto.setProcessingCode(fkProductProcessId.getProcessingCode());
			processTypeDto.setProcessingName(fkProductProcessId.getProcessingName());
			gradeDto.setProcessingType(processTypeDto);
		}
		
		
		if (fkProductProcessSubId != null) {
			gradeDto=setProcessSubType(subTypeRef,gradeDto);
		}
		
		if (fkProductIcoIndex != null) {
			gradeDto.setIcoIndexId(fkProductIcoIndex.getPkProductIcoIndex());
			gradeDto.setIcoIndexCode(fkProductIcoIndex.getIcoCode());
			gradeDto.setIcoIndexName(fkProductIcoIndex.getIcoName());
		}
		
		if (fkgradeGroupRef != null) {
			gradeDto.setGradeGroupId(fkgradeGroupRef.getPkGradeGroupRefId());
			gradeDto.setGradeGroupCode(fkgradeGroupRef.getGradeGroupCode());
			gradeDto.setGradeGroupName(fkgradeGroupRef.getGradeGroupName());
		}
		gradeDto.setGradeIsRaw(gradeIsRaw);
		gradeDto.setIsTenderable(gradeIsTenderable);
		if (gradeHedgeRatio != null) {
			gradeDto.setHedgeRatio(new BigDecimal(gradeHedgeRatio));
		}
		gradeDto.setPremiumOrDiscount(premDisc);
		gradeDto=setGradeUomCurrency(currencyList,uomList,gradeDto);
	   gradeDto.setIntlGradeList(generateIntlGradeDtos());
		return gradeDto;
	}

private GradeDto setGradeUomCurrency(List<Map<Object, Object>> currencyList, List<Map<Object, Object>> uomList, GradeDto gradeDto) {
	for (Map<Object, Object> uom : uomList) {
		if (uom.get("uomId").equals(fkDefaultGradeUomId)) {
			gradeDto.setUomId((Integer) uom.get("uomId"));
			gradeDto.setUomCode((String) uom.get("uomCode"));
			gradeDto.setUomName((String) uom.get("uomName"));
			break;
		}
		
	}
	for (Map<Object, Object> currency : currencyList) {
		if (currency.get("pkCurrencyId").equals(preDiscCurrencyId)) {
			gradeDto.setCurrencyId((Integer) currency.get("pkCurrencyId"));
			gradeDto.setCurrencyCode((String) currency.get("currencyCode"));
			gradeDto.setCurrencyName((String) currency.get("currencyName"));
			break;
		}
		
	}
	return gradeDto;
}

private GradeDto setProcessSubType(List<MasterProcessSubTypeRef> subTypeRef, GradeDto gradeDto) {
	for (MasterProcessSubTypeRef processSubTypeRef : subTypeRef) {
		if (processSubTypeRef.getPkProcessSubTypeRefId().equals(fkProductProcessSubId.getFkProcessSubTypeRefId())) {
			ProcessSubTypeRefDto processTypeDto = new ProcessSubTypeRefDto();
			processTypeDto.setProcessSubTypeRefId(processSubTypeRef.getPkProcessSubTypeRefId());
			processTypeDto.setSubTypeName(processSubTypeRef.getProcessSubTypeName());
			processTypeDto.setSubTypeCode(processSubTypeRef.getProcessSubTypeCode());
			gradeDto.setProcessingSubType(processTypeDto);
			break;
		}
	}
	return gradeDto;
}

private List<IntlGradeDto> generateIntlGradeDtos() {
	List<IntlGradeDto> intlGradeDtos = new ArrayList<>();
	List<MasterGradeIntlCode> gradeIntlCodes = (List<MasterGradeIntlCode>) getMasterGradeIntlCodeCollection();
	if (gradeIntlCodes != null && !gradeIntlCodes.isEmpty()) {
		for (MasterGradeIntlCode masterGradeIntlCode : gradeIntlCodes) {
			IntlGradeDto intlGradeDto= new IntlGradeDto();
			if(masterGradeIntlCode.getFkIntlCodeTypeRefId()!=null){
			intlGradeDto.setIntlId(masterGradeIntlCode.getFkIntlCodeTypeRefId().getPkIntlCodeTypeRefId());
			intlGradeDto.setIntlName(masterGradeIntlCode.getFkIntlCodeTypeRefId().getIntlCodeTypeName());
			}
			intlGradeDto.setIntlCode(masterGradeIntlCode.getIntlCode());
			intlGradeDto.setIntlDesc(masterGradeIntlCode.getDesc());
			intlGradeDto.setIntlGradeMappingId(masterGradeIntlCode.getPkGradeIntlCodeId());
			intlGradeDtos.add(intlGradeDto);
		}
	}
	

	return intlGradeDtos;
}

private List<OriginGradeDto> generateOriginDto(List<MasterGradeOriginAssoc> gradeOriginAssocList) {
	List<OriginGradeDto> originGradeDtos = new ArrayList<>();
	for (MasterGradeOriginAssoc masterGradeOriginAssoc : gradeOriginAssocList) {
		if (masterGradeOriginAssoc.getFkGradeId().getPkGradeId().equals(pkGradeId)) {
			OriginGradeDto originGradeDto = new OriginGradeDto();
			originGradeDto.setOriginId(masterGradeOriginAssoc.getFkOriginId().getPkOriginId());
			originGradeDto.setOriginName(masterGradeOriginAssoc.getFkOriginId().getOriginName());
			originGradeDto.setOriginCode(masterGradeOriginAssoc.getFkOriginId().getOriginCode());
			originGradeDto.setOriginMappingId(masterGradeOriginAssoc.getPkGradeOriginAssocId());
			originGradeDtos.add(originGradeDto);
		}

	}	return originGradeDtos;
}
    
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olam.score.limit.domain;

import java.io.Serializable;
import java.sql.Timestamp;
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

import com.olam.score.common.constants.Constants;
import com.olam.score.limit.dto.BaseLimitAttribute;
import com.olam.score.limit.dto.LimitDetails;

/**
 *
 * @author rashmi.kedlaya
 */
@Entity
@Table(name = "MASTER_LIMIT_DETAIL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterLimitDetail.findAll", query = "SELECT m FROM MasterLimitDetail m")})
public class MasterLimitDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name="MASTER_LIMIT_DETAIL_LIMITDETAILIDSEQ_GENERATOR", sequenceName="LIMIT_DETAIL_ID_SEQ",allocationSize=1)
	@GeneratedValue(generator="MASTER_LIMIT_DETAIL_LIMITDETAILIDSEQ_GENERATOR")
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_LIMIT_DETAIL_ID")
    private Integer pkLimitDetailId;
    @Column(name = "LIMIT_VALID_FROM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date limitValidFrom;
    @Column(name = "LIMIT_VALID_TO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date limitValidTo;
    @Size(max = 1)
    @Column(name = "IS_TEMPORARY")
    private String isTemporary;
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
    @Size(max = 500)
    @Column(name = "LIMIT_DETAIL_REMARKS")
    private String limitDetailRemarks;
    @JoinColumn(name = "FK_LIMIT_ID", referencedColumnName = "PK_LIMIT_ID")
    @ManyToOne
    private MasterLimit fkLimitId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkLimitDetailId")
    private Collection<MasterLimitDetailAttribute> masterLimitDetailAttributeCollection;

    public MasterLimitDetail() {
    }

    public MasterLimitDetail(Integer pkLimitDetailId) {
        this.pkLimitDetailId = pkLimitDetailId;
    }

    public Integer getPkLimitDetailId() {
        return pkLimitDetailId;
    }

    public void setPkLimitDetailId(Integer pkLimitDetailId) {
        this.pkLimitDetailId = pkLimitDetailId;
    }

    public Date getLimitValidFrom() {
        return limitValidFrom;
    }

    public void setLimitValidFrom(Date limitValidFrom) {
        this.limitValidFrom = limitValidFrom;
    }

    public Date getLimitValidTo() {
        return limitValidTo;
    }

    public void setLimitValidTo(Date limitValidTo) {
        this.limitValidTo = limitValidTo;
    }

    public String getIsTemporary() {
        return isTemporary;
    }

    public void setIsTemporary(String isTemporary) {
        this.isTemporary = isTemporary;
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

    public String getLimitDetailRemarks() {
        return limitDetailRemarks;
    }

    public void setLimitDetailRemarks(String limitDetailRemarks) {
        this.limitDetailRemarks = limitDetailRemarks;
    }

    public MasterLimit getFkLimitId() {
        return fkLimitId;
    }

    public void setFkLimitId(MasterLimit fkLimitId) {
        this.fkLimitId = fkLimitId;
    }

    @XmlTransient
    public Collection<MasterLimitDetailAttribute> getMasterLimitDetailAttributeCollection() {
        return masterLimitDetailAttributeCollection;
    }

    public void setMasterLimitDetailAttributeCollection(Collection<MasterLimitDetailAttribute> masterLimitDetailAttributeCollection) {
        this.masterLimitDetailAttributeCollection = masterLimitDetailAttributeCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkLimitDetailId != null ? pkLimitDetailId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterLimitDetail)) {
            return false;
        }
        MasterLimitDetail other = (MasterLimitDetail) object;
        if ((this.pkLimitDetailId == null && other.pkLimitDetailId != null) || (this.pkLimitDetailId != null && !this.pkLimitDetailId.equals(other.pkLimitDetailId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.terms.domain.MasterLimitDetail[ pkLimitDetailId=" + pkLimitDetailId + " ]";
    }
    
    public LimitDetails convertEntityToDto(List<Map<Object, Object>> uomList, List<Map<Object, Object>> currencyList) {
		LimitDetails details=new LimitDetails();
		details.setLimitDetailsId(pkLimitDetailId);
		details.setValidFrom((Timestamp) limitValidFrom);
		details.setIsTemporary(isTemporary);
		details.setValidTo((Timestamp) limitValidTo);
		details=setLimitDetailAttribute(details,uomList,currencyList);
		return details;
	}

	private LimitDetails setLimitDetailAttribute(LimitDetails details,List<Map<Object, Object>> uomList, List<Map<Object, Object>> currencyList) {
		Collection<MasterLimitDetailAttribute> limitDetailAttributeCollection=getMasterLimitDetailAttributeCollection();
		for (MasterLimitDetailAttribute masterLimitDetailAttribute : limitDetailAttributeCollection) {
			if(masterLimitDetailAttribute.getFkLimitAttributeRefId().getLimitAttributeName().equalsIgnoreCase(Constants.OUTRIGHT_LIMIT)){
				details.setLimitOutRightAttribute(setBaseLimitAttribute(masterLimitDetailAttribute,uomList,currencyList));
			}else if(masterLimitDetailAttribute.getFkLimitAttributeRefId().getLimitAttributeName().equalsIgnoreCase(Constants.BASIS_LIMIT)){
				details.setLimitBasisAttribute(setBaseLimitAttribute(masterLimitDetailAttribute,uomList,currencyList));
			}else if(masterLimitDetailAttribute.getFkLimitAttributeRefId().getLimitAttributeName().equalsIgnoreCase(Constants.STRUCTURE_LIMIT)){
				details.setLimitStructureAttribute(setBaseLimitAttribute(masterLimitDetailAttribute,uomList,currencyList));
			}else if(masterLimitDetailAttribute.getFkLimitAttributeRefId().getLimitAttributeName().equalsIgnoreCase(Constants.DRAWDOWN_LIMIT)){
				details.setLimitDrawDownAttribute(setBaseLimitAttribute(masterLimitDetailAttribute,uomList,currencyList));
			}else if(masterLimitDetailAttribute.getFkLimitAttributeRefId().getLimitAttributeName().equalsIgnoreCase(Constants.VAR_LIMIT)){
				details.setLimitVaRAttribute(setBaseLimitAttribute(masterLimitDetailAttribute,uomList,currencyList));
			}else if(masterLimitDetailAttribute.getFkLimitAttributeRefId().getLimitAttributeName().equalsIgnoreCase(Constants.DELTA_LIMIT)){
				details.setLimitDeltaAttribute(setBaseLimitAttribute(masterLimitDetailAttribute,uomList,currencyList));
			}else if(masterLimitDetailAttribute.getFkLimitAttributeRefId().getLimitAttributeName().equalsIgnoreCase(Constants.GAMMA_LIMIT)){
				details.setLimitGammaAttribute(setBaseLimitAttribute(masterLimitDetailAttribute,uomList,currencyList));
			}else if(masterLimitDetailAttribute.getFkLimitAttributeRefId().getLimitAttributeName().equalsIgnoreCase(Constants.VEGA_LIMIT)){
				details.setLimitVegaAttribute(setBaseLimitAttribute(masterLimitDetailAttribute,uomList,currencyList));
			}else if(masterLimitDetailAttribute.getFkLimitAttributeRefId().getLimitAttributeName().equalsIgnoreCase(Constants.CURRENT_MONTH)){
				details.setCurrentMonthAttribute(setBaseLimitAttribute(masterLimitDetailAttribute,uomList,currencyList));
			}else if(masterLimitDetailAttribute.getFkLimitAttributeRefId().getLimitAttributeName().equalsIgnoreCase(Constants.FORWARD_MONTH)){
				details.setForwardMonthAttribute(setBaseLimitAttribute(masterLimitDetailAttribute,uomList,currencyList));
			}else if(masterLimitDetailAttribute.getFkLimitAttributeRefId().getLimitAttributeName().equalsIgnoreCase(Constants.TOTAL)){
				details.setTotalAttribute(setBaseLimitAttribute(masterLimitDetailAttribute,uomList,currencyList));
			}else if(masterLimitDetailAttribute.getFkLimitAttributeRefId().getLimitAttributeName().equalsIgnoreCase(Constants.VALUE)){
				details.setValueAttribute(setBaseLimitAttribute(masterLimitDetailAttribute,uomList,currencyList));
			}

			
		}
		return details;
	}
	private BaseLimitAttribute setBaseLimitAttribute(MasterLimitDetailAttribute masterLimitDetailAttribute, List<Map<Object, Object>> uomList, List<Map<Object, Object>> currencyList) {
		BaseLimitAttribute baseLimitAttribute=setGradeUomCurrency(currencyList,uomList, masterLimitDetailAttribute);
		baseLimitAttribute.setLimitAttributeRefId(masterLimitDetailAttribute.getFkLimitAttributeRefId().getPkLimitAttributeRefId());
		baseLimitAttribute.setLimitVolumeValueRefCode(masterLimitDetailAttribute.getFkLimitVolValueRefId().getLimitVolValueCode());
		baseLimitAttribute.setLimitVolumeValueRefId(masterLimitDetailAttribute.getFkLimitVolValueRefId().getPkLimitVolValueRefId());
		baseLimitAttribute.setLimitVolumeValueRefName(masterLimitDetailAttribute.getFkLimitVolValueRefId().getLimitVolValueName());
		baseLimitAttribute.setLimit(masterLimitDetailAttribute.getLimitData());
		return baseLimitAttribute;
	}

	private BaseLimitAttribute setGradeUomCurrency(List<Map<Object, Object>> currencyList, List<Map<Object, Object>> uomList, MasterLimitDetailAttribute masterLimitDetailAttribute) {
		BaseLimitAttribute baseLimitAttribute=new BaseLimitAttribute();
		for (Map<Object, Object> uom : uomList) {
			if (uom.get("uomId") != null && uom.get("uomId").equals(masterLimitDetailAttribute.getFkUomId())) {
				baseLimitAttribute.setLimitVolumeUomId((Integer) uom.get("uomId"));
				baseLimitAttribute.setLimitVolumeUomCode((String) uom.get("uomCode"));
				baseLimitAttribute.setLimitVolumeUomName((String) uom.get("uomName"));
				break;
			}
			
		}
		for (Map<Object, Object> currency : currencyList) {
			if (currency.get("pkCurrencyId") != null && currency.get("pkCurrencyId").equals(masterLimitDetailAttribute.getFkCurrencyId())) {
				baseLimitAttribute.setLimitCurrencyId((Integer) currency.get("pkCurrencyId"));
				baseLimitAttribute.setLimitCurrencyCode((String) currency.get("currencyCode"));
				baseLimitAttribute.setLimitCurrencyName((String) currency.get("currencyName"));
				break;
			}
			
		}
		return baseLimitAttribute;
	}
    
}

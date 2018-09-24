/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olam.score.product.domain;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

import com.olam.score.product.dto.MasterOriginDto;
import com.olam.score.product.dto.OriginDto;
import com.olam.score.product.dto.OutturnRatioDto;

/**
 *
 * @author Ramachandran_N02
 */
@Entity
@Table(name = "MASTER_ORIGIN")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterOrigin.findAll", query = "SELECT m FROM MasterOrigin m")})
public class MasterOrigin implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRODUCT_ORIGIN_ORIGINIDSEQGENERATOR")
   	@SequenceGenerator(name = "PRODUCT_ORIGIN_ORIGINIDSEQGENERATOR", sequenceName = "ORIGIN_ID_SEQ", initialValue = 1, allocationSize = 1)

    @Column(name = "PK_ORIGIN_ID")
    private Integer pkOriginId;
    @Size(max = 200)
    @Column(name = "ORIGIN_FULLNAME")
    private String originFullname;
    @Size(max = 200)
    @Column(name = "ORIGIN_NAME")
    private String originName;
    @Column(name = "FK_GEO_ID")
    private Integer fkGeoId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "ORIGIN_CODE")
    private String originCode;
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
    @Column(name = "CUSTOM_ATTRIBUTE_6")
    private BigInteger customAttribute6;
    @Column(name = "CUSTOM_ATTRIBUTE_7")
    private BigInteger customAttribute7;
    @Column(name = "CUSTOM_ATTRIBUTE_8")
    private BigInteger customAttribute8;
    @OneToMany(mappedBy = "fkOriginId")
    private Collection<MasterOriginExternalMapping> masterOriginExternalMappingCollection;
    @OneToMany(mappedBy = "fkOriginId")
    private Collection<MasterOriginPackingAssoc> masterOriginPackingAssocCollection;
    @OneToMany(mappedBy = "fkOriginId")
    private Collection<MasterBlendInput> masterBlendInputCollection;
    @OneToMany(mappedBy = "fkOriginId")
    private Collection<MasterBlendOutput> masterBlendOutputCollection;
    @OneToMany(mappedBy = "fkOriginId")
    private Collection<MasterGradeOriginAssoc> masterGradeOriginAssocCollection;
    @OneToMany(mappedBy = "fkOriginId")
    private Collection<MasterOriginCupProfile> masterOriginCupProfileCollection;
    @OneToMany(mappedBy = "fkOriginId")
    private Collection<MasterOriginRegion> masterOriginRegionCollection;
    @OneToMany(mappedBy = "fkOriginId")
    private Collection<MasterOutturnRawGrade> masterOutturnRawGradeCollection;
    @OneToMany(mappedBy = "fkOriginId")
    private Collection<MasterOriginGradeGroup> masterOriginGradeGroupCollection;

    public MasterOrigin() {
    }

    public MasterOrigin(Integer pkOriginId) {
        this.pkOriginId = pkOriginId;
    }

    public MasterOrigin(Integer pkOriginId, String originCode) {
        this.pkOriginId = pkOriginId;
        this.originCode = originCode;
    }

    public Integer getPkOriginId() {
        return pkOriginId;
    }

    public void setPkOriginId(Integer pkOriginId) {
        this.pkOriginId = pkOriginId;
    }

    public String getOriginFullname() {
        return originFullname;
    }

    public void setOriginFullname(String originFullname) {
        this.originFullname = originFullname;
    }

    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }

    public Integer getFkGeoId() {
        return fkGeoId;
    }

    public void setFkGeoId(Integer fkGeoId) {
        this.fkGeoId = fkGeoId;
    }

    public String getOriginCode() {
        return originCode;
    }

    public void setOriginCode(String originCode) {
        this.originCode = originCode;
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
    public Collection<MasterOriginExternalMapping> getMasterOriginExternalMappingCollection() {
        return masterOriginExternalMappingCollection;
    }

    public void setMasterOriginExternalMappingCollection(Collection<MasterOriginExternalMapping> masterOriginExternalMappingCollection) {
        this.masterOriginExternalMappingCollection = masterOriginExternalMappingCollection;
    }

    @XmlTransient
    public Collection<MasterOriginPackingAssoc> getMasterOriginPackingAssocCollection() {
        return masterOriginPackingAssocCollection;
    }

    public void setMasterOriginPackingAssocCollection(Collection<MasterOriginPackingAssoc> masterOriginPackingAssocCollection) {
        this.masterOriginPackingAssocCollection = masterOriginPackingAssocCollection;
    }

    @XmlTransient
    public Collection<MasterBlendInput> getMasterBlendInputCollection() {
        return masterBlendInputCollection;
    }

    public void setMasterBlendInputCollection(Collection<MasterBlendInput> masterBlendInputCollection) {
        this.masterBlendInputCollection = masterBlendInputCollection;
    }

    @XmlTransient
    public Collection<MasterBlendOutput> getMasterBlendOutputCollection() {
        return masterBlendOutputCollection;
    }

    public void setMasterBlendOutputCollection(Collection<MasterBlendOutput> masterBlendOutputCollection) {
        this.masterBlendOutputCollection = masterBlendOutputCollection;
    }

    @XmlTransient
    public Collection<MasterGradeOriginAssoc> getMasterGradeOriginAssocCollection() {
        return masterGradeOriginAssocCollection;
    }

    public void setMasterGradeOriginAssocCollection(Collection<MasterGradeOriginAssoc> masterGradeOriginAssocCollection) {
        this.masterGradeOriginAssocCollection = masterGradeOriginAssocCollection;
    }

    @XmlTransient
    public Collection<MasterOriginCupProfile> getMasterOriginCupProfileCollection() {
        return masterOriginCupProfileCollection;
    }

    public void setMasterOriginCupProfileCollection(Collection<MasterOriginCupProfile> masterOriginCupProfileCollection) {
        this.masterOriginCupProfileCollection = masterOriginCupProfileCollection;
    }

    @XmlTransient
    public Collection<MasterOriginRegion> getMasterOriginRegionCollection() {
        return masterOriginRegionCollection;
    }

    public void setMasterOriginRegionCollection(Collection<MasterOriginRegion> masterOriginRegionCollection) {
        this.masterOriginRegionCollection = masterOriginRegionCollection;
    }

    @XmlTransient
    public Collection<MasterOutturnRawGrade> getMasterOutturnRawGradeCollection() {
        return masterOutturnRawGradeCollection;
    }

    public void setMasterOutturnRawGradeCollection(Collection<MasterOutturnRawGrade> masterOutturnRawGradeCollection) {
        this.masterOutturnRawGradeCollection = masterOutturnRawGradeCollection;
    }

    @XmlTransient
    public Collection<MasterOriginGradeGroup> getMasterOriginGradeGroupCollection() {
        return masterOriginGradeGroupCollection;
    }

    public void setMasterOriginGradeGroupCollection(Collection<MasterOriginGradeGroup> masterOriginGradeGroupCollection) {
        this.masterOriginGradeGroupCollection = masterOriginGradeGroupCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkOriginId != null ? pkOriginId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterOrigin)) {
            return false;
        }
        MasterOrigin other = (MasterOrigin) object;
        if ((this.pkOriginId == null && other.pkOriginId != null) || (this.pkOriginId != null && !this.pkOriginId.equals(other.pkOriginId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.product.domain.MasterOrigin[ pkOriginId=" + pkOriginId + " ]";
    }
    
    
    public MasterOriginDto convertToMasterOriginDto(Map<Integer, String> statusMap,List<Map<Object, Object>> geoList) {
    	MasterOriginDto dto = new MasterOriginDto();
    	OriginDto originDto;
        ModelMapper modelMapper = new ModelMapper();
        originDto = modelMapper.map(this, OriginDto.class);
		
        dto.setOriginDto(originDto);
        dto.setStatus(statusMap.get(getFkStatusId()));
		return dto;
	} 
	
    
}

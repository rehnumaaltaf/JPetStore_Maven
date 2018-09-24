/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olam.score.finance.domain;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
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

import com.olam.score.finance.dto.GLDto;

/**
 *
 * @author ramachandran_n02
 */
@Entity
@Table(name = "MASTER_GL",  schema = "finance")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterGl.findAll", query = "SELECT m FROM MasterGl m")})
public class MasterGl implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @SequenceGenerator(name="MASTER_GL_GLIDSEQ_GENERATOR", sequenceName="GL_ID_SEQ",allocationSize=1, schema="finance")
	@GeneratedValue(generator="MASTER_GL_GLIDSEQ_GENERATOR")
    @Column(name = "PK_GL_ID")
    private Integer pkGlId;
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
    @Column(name = "GL_NAME")
    private String glName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "GL_CODE")
    private String glCode;
    @Column(name = "FK_STATUS_ID")
    private Integer fkStatusId;
    @Size(max = 200)
    @Column(name = "GL_DESC")
    private String glDesc;
    @Size(max = 1)
    @Column(name = "GL_IS_GROUP")
    private String glIsGroup;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FK_PARTY_ID")
    private Integer fkPartyId;
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
    @OneToMany(mappedBy = "fkParentGlId")
    private Collection<MasterGl> masterGlCollection;
    @JoinColumn(name = "FK_PARENT_GL_ID", referencedColumnName = "PK_GL_ID")
    @ManyToOne
    private MasterGl fkParentGlId;
    @JoinColumn(name = "FK_GL_TYPE_REF_ID", referencedColumnName = "PK_GL_TYPE_REF_ID")
    @ManyToOne
    /*@NotNull*/
    private MasterGlTypeRef fkGlTypeRefId;//private Integer fkGlTypeRefId;
    @OneToMany(mappedBy = "fkGlId",cascade= CascadeType.ALL,orphanRemoval = true)
    private Collection<MasterGlExternalMapping> masterGlExternalMappingCollection;

    public MasterGl() {
    }

    public MasterGl(Integer pkGlId) {
        this.pkGlId = pkGlId;
    }

    public MasterGl(Integer pkGlId, String glName, String glCode, int fkPartyId) {
        this.pkGlId = pkGlId;
        this.glName = glName;
        this.glCode = glCode;
        this.fkPartyId = fkPartyId;
    }

    public Integer getPkGlId() {
        return pkGlId;
    }

    public void setPkGlId(Integer pkGlId) {
        this.pkGlId = pkGlId;
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

    public String getGlName() {
        return glName;
    }

    public void setGlName(String glName) {
        this.glName = glName;
    }

    public String getGlCode() {
        return glCode;
    }

    public void setGlCode(String glCode) {
        this.glCode = glCode;
    }

    public Integer getFkStatusId() {
        return fkStatusId;
    }

    public void setFkStatusId(Integer fkStatusId) {
        this.fkStatusId = fkStatusId;
    }

    public String getGlDesc() {
        return glDesc;
    }

    public void setGlDesc(String glDesc) {
        this.glDesc = glDesc;
    }

    public String getGlIsGroup() {
        return glIsGroup;
    }

    public void setGlIsGroup(String glIsGroup) {
        this.glIsGroup = glIsGroup;
    }

    public Integer getFkPartyId() {
        return fkPartyId;
    }

    public void setFkPartyId(Integer fkPartyId) {
        this.fkPartyId = fkPartyId;
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
    public Collection<MasterGl> getMasterGlCollection() {
        return masterGlCollection;
    }

    public void setMasterGlCollection(Collection<MasterGl> masterGlCollection) {
        this.masterGlCollection = masterGlCollection;
    }

    public MasterGl getFkParentGlId() {
        return fkParentGlId;
    }

    public void setFkParentGlId(MasterGl fkParentGlId) {
        this.fkParentGlId = fkParentGlId;
    }

    public MasterGlTypeRef getFkGlTypeRefId() {//Integer getFkGlTypeRefId() {//
        return fkGlTypeRefId;
    }

    public void setFkGlTypeRefId(MasterGlTypeRef fkGlTypeRefId) {//(Integer fkGlTypeRefId) {
        this.fkGlTypeRefId = fkGlTypeRefId;
    }

    /*@XmlTransient
    public Collection<MasterGlExternalMapping> getMasterGlExternalMappingCollection() {
        return masterGlExternalMappingCollection;
    }

    public void setMasterGlExternalMappingCollection(Collection<MasterGlExternalMapping> masterGlExternalMappingCollection) {
        this.masterGlExternalMappingCollection = masterGlExternalMappingCollection;
    }*/

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkGlId != null ? pkGlId.hashCode() : 0);
        return hash;
    }
    @XmlTransient
    public Collection<MasterGlExternalMapping> getMasterGlExternalMappingCollection() {
		return masterGlExternalMappingCollection;
	}

	public void setMasterGlExternalMappingCollection(
			Collection<MasterGlExternalMapping> masterGlExternalMappingCollection) {
		this.masterGlExternalMappingCollection = masterGlExternalMappingCollection;
	}

	@Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterGl)) {
            return false;
        }
        MasterGl other = (MasterGl) object;
        System.out.println("this.pkGlId "+this.pkGlId);
        if ((this.pkGlId == null && other.pkGlId != null) || (this.pkGlId != null && !this.pkGlId.equals(other.pkGlId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.finance.domain.MasterGl[ pkGlId=" + pkGlId + " ]";
    }
    
   /* public GLDto convertToGLDto(Map<Integer,String> statusmap) {
        GLDto dto;
        ModelMapper modelMapper = new ModelMapper();
    	dto = modelMapper.map(this, GLDto.class);
    	if (this.getFkBaseUomId() != null) {
			dto.setParentUomId(this.getFkBaseUomId().getPkUomId());
			dto.setBaseUomCode(this.getFkBaseUomId().getUomCode());
		}
    	dto.setStatusName(statusmap.get(getFkStatusId()));
		return dto;
    }*/
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olam.score.party.domain;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Ramachandran_N02
 */
@Entity
@Table(name = "MASTER_UNIT")
@NamedQueries({
    @NamedQuery(name = "MasterUnit.findAll", query = "SELECT m FROM MasterUnit m")})
public class MasterUnit implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MASTER_PARTY_UNIT_GENERATOR")
   	@SequenceGenerator(name="MASTER_PARTY_UNIT_GENERATOR", sequenceName="party.UNIT_ID_SEQ",allocationSize=1)
    @NotNull
    @Column(name = "PK_UNIT_ID")
    private Integer pkUnitId;
    @Size(max = 200)
    @Column(name = "UNIT_NAME")
    private String unitName;
    @Size(max = 200)
    @Column(name = "UNIT_FULLNAME")
    private String unitFullname;
    @Size(max = 100)
    @Column(name = "CREATED_BY")
    private String createdBy;
    @Column(name = "CREATED_DATE", updatable = false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Size(max = 100)
    @Column(name = "MODIFIED_BY")
    private String modifiedBy;
    @Column(name = "MODIFIED_DATE",  updatable = true, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;
    @Column(name = "FK_STATUS_ID")
    private Integer fkStatusId;
    @Size(max = 20)
    @Column(name = "UNIT_CODE")
    private String unitCode;
    @Size(max = 1)
    @Column(name = "IS_GROUP_UNIT")
    private String isGroupUnit;
    @Column(name = "FK_PARENT_UNIT_ID")
    private Integer fkParentUnitId;
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
    @Column(name = "FK_GEO_ID")
    private Integer fkGeoId;
    @OneToMany(mappedBy = "fkUnitId")
    private Collection<MasterUnitExternalMapping> masterUnitExternalMappingCollection;
    @OneToMany(mappedBy = "fkUnitId")
    private Collection<MasterPartyCreditLimit> masterPartyCreditLimitCollection;
    @OneToMany(mappedBy = "fkUnitId")
    private Collection<MasterUnitComment> masterUnitCommentCollection;
    @JoinColumn(name = "INTERNAL_COMPANY_ID", referencedColumnName = "PK_PARTY_ID")
    @ManyToOne
    private MasterParty internalCompanyId;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "masterUnit1")
    private MasterUnit masterUnit;
    @JoinColumn(name = "PK_UNIT_ID", referencedColumnName = "PK_UNIT_ID", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private MasterUnit masterUnit1;
    @OneToMany(mappedBy = "fkUnitId")
    private Collection<MasterUnitGlMapping> masterUnitGlMappingCollection;
    @OneToMany(mappedBy = "fkUnitId")
    private Collection<MasterUnitLe> masterUnitLeCollection;
    @OneToMany(mappedBy = "fkUnitId")
    private Collection<MasterUnitProductAssoc> masterUnitProductAssocCollection;

    public MasterUnit() {
    }

    public MasterUnit(Integer pkUnitId) {
        this.pkUnitId = pkUnitId;
    }

    public Integer getPkUnitId() {
        return pkUnitId;
    }

    public void setPkUnitId(Integer pkUnitId) {
        this.pkUnitId = pkUnitId;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getUnitFullname() {
        return unitFullname;
    }

    public void setUnitFullname(String unitFullname) {
        this.unitFullname = unitFullname;
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

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    public String getIsGroupUnit() {
        return isGroupUnit;
    }

    public void setIsGroupUnit(String isGroupUnit) {
        this.isGroupUnit = isGroupUnit;
    }

    public Integer getFkParentUnitId() {
        return fkParentUnitId;
    }

    public void setFkParentUnitId(Integer fkParentUnitId) {
        this.fkParentUnitId = fkParentUnitId;
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

    public Integer getFkGeoId() {
        return fkGeoId;
    }

    public void setFkGeoId(Integer fkGeoId) {
        this.fkGeoId = fkGeoId;
    }

    public Collection<MasterUnitExternalMapping> getMasterUnitExternalMappingCollection() {
        return masterUnitExternalMappingCollection;
    }

    public void setMasterUnitExternalMappingCollection(Collection<MasterUnitExternalMapping> masterUnitExternalMappingCollection) {
        this.masterUnitExternalMappingCollection = masterUnitExternalMappingCollection;
    }

    public Collection<MasterPartyCreditLimit> getMasterPartyCreditLimitCollection() {
        return masterPartyCreditLimitCollection;
    }

    public void setMasterPartyCreditLimitCollection(Collection<MasterPartyCreditLimit> masterPartyCreditLimitCollection) {
        this.masterPartyCreditLimitCollection = masterPartyCreditLimitCollection;
    }

    public Collection<MasterUnitComment> getMasterUnitCommentCollection() {
        return masterUnitCommentCollection;
    }

    public void setMasterUnitCommentCollection(Collection<MasterUnitComment> masterUnitCommentCollection) {
        this.masterUnitCommentCollection = masterUnitCommentCollection;
    }

    public MasterParty getInternalCompanyId() {
        return internalCompanyId;
    }

    public void setInternalCompanyId(MasterParty internalCompanyId) {
        this.internalCompanyId = internalCompanyId;
    }

    public MasterUnit getMasterUnit() {
        return masterUnit;
    }

    public void setMasterUnit(MasterUnit masterUnit) {
        this.masterUnit = masterUnit;
    }

    public MasterUnit getMasterUnit1() {
        return masterUnit1;
    }

    public void setMasterUnit1(MasterUnit masterUnit1) {
        this.masterUnit1 = masterUnit1;
    }

    public Collection<MasterUnitGlMapping> getMasterUnitGlMappingCollection() {
        return masterUnitGlMappingCollection;
    }

    public void setMasterUnitGlMappingCollection(Collection<MasterUnitGlMapping> masterUnitGlMappingCollection) {
        this.masterUnitGlMappingCollection = masterUnitGlMappingCollection;
    }

    public Collection<MasterUnitLe> getMasterUnitLeCollection() {
        return masterUnitLeCollection;
    }

    public void setMasterUnitLeCollection(Collection<MasterUnitLe> masterUnitLeCollection) {
        this.masterUnitLeCollection = masterUnitLeCollection;
    }

    public Collection<MasterUnitProductAssoc> getMasterUnitProductAssocCollection() {
        return masterUnitProductAssocCollection;
    }

    public void setMasterUnitProductAssocCollection(Collection<MasterUnitProductAssoc> masterUnitProductAssocCollection) {
        this.masterUnitProductAssocCollection = masterUnitProductAssocCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkUnitId != null ? pkUnitId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterUnit)) {
            return false;
        }
        MasterUnit other = (MasterUnit) object;
        if ((this.pkUnitId == null && other.pkUnitId != null) || (this.pkUnitId != null && !this.pkUnitId.equals(other.pkUnitId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.party.domain.MasterUnit[ pkUnitId=" + pkUnitId + " ]";
    }
    
}

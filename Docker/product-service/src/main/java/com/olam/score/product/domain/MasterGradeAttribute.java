/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olam.score.product.domain;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ramachandran_N02
 */
@Entity
@Table(name = "MASTER_GRADE_ATTRIBUTE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterGradeAttribute.findAll", query = "SELECT m FROM MasterGradeAttribute m")})
public class MasterGradeAttribute implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_GRADE_ATTRIBUTE_ID")
    private Integer pkGradeAttributeId;
    @Size(max = 20)
    @Column(name = "GRADE_ATTRIBUTE_CODE")
    private String gradeAttributeCode;
    @Size(max = 200)
    @Column(name = "GRADE_ATTRIBUTE_NAME")
    private String gradeAttributeName;
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
    @Size(max = 1000)
    @Column(name = "GRADE_ATTRIBUTE_DESC")
    private String gradeAttributeDesc;
    @Column(name = "FK_STATUS_ID")
    private Integer fkStatusId;
    @Size(max = 200)
    @Column(name = "ENTITY_ID")
    private String entityId;
    @Size(max = 200)
    @Column(name = "ENTITY_DISPLAY_NAME")
    private String entityDisplayName;
    @Size(max = 200)
    @Column(name = "REF_TABLE_NAME")
    private String refTableName;
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
    @OneToMany(mappedBy = "fkGradeAttributeId")
    private Collection<MasterGradeSpec> masterGradeSpecCollection;

    public MasterGradeAttribute() {
    }

    public MasterGradeAttribute(Integer pkGradeAttributeId) {
        this.pkGradeAttributeId = pkGradeAttributeId;
    }

    public Integer getPkGradeAttributeId() {
        return pkGradeAttributeId;
    }

    public void setPkGradeAttributeId(Integer pkGradeAttributeId) {
        this.pkGradeAttributeId = pkGradeAttributeId;
    }

    public String getGradeAttributeCode() {
        return gradeAttributeCode;
    }

    public void setGradeAttributeCode(String gradeAttributeCode) {
        this.gradeAttributeCode = gradeAttributeCode;
    }

    public String getGradeAttributeName() {
        return gradeAttributeName;
    }

    public void setGradeAttributeName(String gradeAttributeName) {
        this.gradeAttributeName = gradeAttributeName;
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

    public String getGradeAttributeDesc() {
        return gradeAttributeDesc;
    }

    public void setGradeAttributeDesc(String gradeAttributeDesc) {
        this.gradeAttributeDesc = gradeAttributeDesc;
    }

    public Integer getFkStatusId() {
        return fkStatusId;
    }

    public void setFkStatusId(Integer fkStatusId) {
        this.fkStatusId = fkStatusId;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public String getEntityDisplayName() {
        return entityDisplayName;
    }

    public void setEntityDisplayName(String entityDisplayName) {
        this.entityDisplayName = entityDisplayName;
    }

    public String getRefTableName() {
        return refTableName;
    }

    public void setRefTableName(String refTableName) {
        this.refTableName = refTableName;
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
    public Collection<MasterGradeSpec> getMasterGradeSpecCollection() {
        return masterGradeSpecCollection;
    }

    public void setMasterGradeSpecCollection(Collection<MasterGradeSpec> masterGradeSpecCollection) {
        this.masterGradeSpecCollection = masterGradeSpecCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkGradeAttributeId != null ? pkGradeAttributeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterGradeAttribute)) {
            return false;
        }
        MasterGradeAttribute other = (MasterGradeAttribute) object;
        if ((this.pkGradeAttributeId == null && other.pkGradeAttributeId != null) || (this.pkGradeAttributeId != null && !this.pkGradeAttributeId.equals(other.pkGradeAttributeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.product.domain.MasterGradeAttribute[ pkGradeAttributeId=" + pkGradeAttributeId + " ]";
    }
    
}

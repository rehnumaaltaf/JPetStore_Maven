/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olam.score.product.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ramachandran_N02
 */
@Entity
@Table(name = "MASTER_GRADE_SPEC")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterGradeSpec.findAll", query = "SELECT m FROM MasterGradeSpec m")})
public class MasterGradeSpec implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_GRADE_SPEC_ID")
    private Integer pkGradeSpecId;
    @Size(max = 200)
    @Column(name = "SPEC_VALUE")
    private String specValue;
    @Column(name = "SPEC_VALUE_REF_ID")
    private Integer specValueRefId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "AMOUNT")
    private BigDecimal amount;
    @Column(name = "FK_CURRENCY_ID")
    private Integer fkCurrencyId;
    @Column(name = "FK_UOM_ID")
    private Integer fkUomId;
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
    private Double customAttribute6;
    @Column(name = "CUSTOM_ATTRIBUTE_7")
    private Double customAttribute7;
    @Column(name = "CUSTOM_ATTRIBUTE_8")
    private Double customAttribute8;
    @Size(max = 200)
    @Column(name = "ENTITY_ID")
    private String entityId;
    @Size(max = 200)
    @Column(name = "SPEC_REF_TABLE_NAME")
    private String specRefTableName;
    @JoinColumn(name = "FK_GRADE_ID", referencedColumnName = "PK_GRADE_ID")
    @ManyToOne
    private MasterGrade fkGradeId;
    @JoinColumn(name = "FK_GRADE_ATTRIBUTE_ID", referencedColumnName = "PK_GRADE_ATTRIBUTE_ID")
    @ManyToOne
    private MasterGradeAttribute fkGradeAttributeId;
    @JoinColumn(name = "FK_GRADE_SPEC_TYPE_ID", referencedColumnName = "PK_GRADE_SPEC_TYPE_REF_ID")
    @ManyToOne
    private MasterGradeSpecTypeRef fkGradeSpecTypeId;

    public MasterGradeSpec() {
    }

    public MasterGradeSpec(Integer pkGradeSpecId) {
        this.pkGradeSpecId = pkGradeSpecId;
    }

    public Integer getPkGradeSpecId() {
        return pkGradeSpecId;
    }

    public void setPkGradeSpecId(Integer pkGradeSpecId) {
        this.pkGradeSpecId = pkGradeSpecId;
    }

    public String getSpecValue() {
        return specValue;
    }

    public void setSpecValue(String specValue) {
        this.specValue = specValue;
    }

    public Integer getSpecValueRefId() {
        return specValueRefId;
    }

    public void setSpecValueRefId(Integer specValueRefId) {
        this.specValueRefId = specValueRefId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getFkCurrencyId() {
        return fkCurrencyId;
    }

    public void setFkCurrencyId(Integer fkCurrencyId) {
        this.fkCurrencyId = fkCurrencyId;
    }

    public Integer getFkUomId() {
        return fkUomId;
    }

    public void setFkUomId(Integer fkUomId) {
        this.fkUomId = fkUomId;
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

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public String getSpecRefTableName() {
        return specRefTableName;
    }

    public void setSpecRefTableName(String specRefTableName) {
        this.specRefTableName = specRefTableName;
    }

    public MasterGrade getFkGradeId() {
        return fkGradeId;
    }

    public void setFkGradeId(MasterGrade fkGradeId) {
        this.fkGradeId = fkGradeId;
    }

    public MasterGradeAttribute getFkGradeAttributeId() {
        return fkGradeAttributeId;
    }

    public void setFkGradeAttributeId(MasterGradeAttribute fkGradeAttributeId) {
        this.fkGradeAttributeId = fkGradeAttributeId;
    }

    public MasterGradeSpecTypeRef getFkGradeSpecTypeId() {
        return fkGradeSpecTypeId;
    }

    public void setFkGradeSpecTypeId(MasterGradeSpecTypeRef fkGradeSpecTypeId) {
        this.fkGradeSpecTypeId = fkGradeSpecTypeId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkGradeSpecId != null ? pkGradeSpecId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterGradeSpec)) {
            return false;
        }
        MasterGradeSpec other = (MasterGradeSpec) object;
        if ((this.pkGradeSpecId == null && other.pkGradeSpecId != null) || (this.pkGradeSpecId != null && !this.pkGradeSpecId.equals(other.pkGradeSpecId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.product.domain.MasterGradeSpec[ pkGradeSpecId=" + pkGradeSpecId + " ]";
    }
    
}

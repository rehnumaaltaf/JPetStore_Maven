/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olam.score.packing.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ramachandran_n02
 */
@Entity
@Table(name = "MASTER_PRIMARY_PACKING_TYPE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterInternalPackingType.findAll", query = "SELECT m FROM MasterInternalPackingType m")})
public class MasterInternalPackingType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PRIMARY_PACKING_TYPE_ID_SEQ")
   	@SequenceGenerator(name="PRIMARY_PACKING_TYPE_ID_SEQ", sequenceName="packing.PRIMARY_PACKING_TYPE_ID_SEQ",initialValue=1,allocationSize=1)
    @Column(name = "PK_PRIMARY_PACKING_TYPE_ID")
    private Integer pkInternalPackingTypeId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "PRIMARY_PACKING_TYPE_CODE")
    private String internalPackingTypeCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "PRIMARY_PACKING_TYPE_NAME")
    private String internalPackingTypeName;
    @Size(max = 1000)
    @Column(name = "PRIMARY_PACKING_TYPE_DESC")
    private String internalPackingTypeDesc;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "PRIMARY_PACKING_TYPE_WEIGHT")
    private BigDecimal internalPackingTypeWeight;
    @Column(name = "FK_PACKAGE_UOM_ID")
    private Integer fkPackageUomId;
    @Column(name = "FK_STATUS_ID")
    private Integer fkStatusId;
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
    @Size(max = 1)
    @Column(name = "PRIMARY_PACKING_TYPE_IS_BULK")
    private String internalPackingTypeIsBulk;

    public MasterInternalPackingType() {
    }

    public MasterInternalPackingType(Integer pkInternalPackingTypeId) {
        this.pkInternalPackingTypeId = pkInternalPackingTypeId;
    }

    public MasterInternalPackingType(Integer pkInternalPackingTypeId, String internalPackingTypeCode, String internalPackingTypeName) {
        this.pkInternalPackingTypeId = pkInternalPackingTypeId;
        this.internalPackingTypeCode = internalPackingTypeCode;
        this.internalPackingTypeName = internalPackingTypeName;
    }

    public Integer getPkInternalPackingTypeId() {
        return pkInternalPackingTypeId;
    }

    public void setPkInternalPackingTypeId(Integer pkInternalPackingTypeId) {
        this.pkInternalPackingTypeId = pkInternalPackingTypeId;
    }

    public String getInternalPackingTypeCode() {
        return internalPackingTypeCode;
    }

    public void setInternalPackingTypeCode(String internalPackingTypeCode) {
        this.internalPackingTypeCode = internalPackingTypeCode;
    }

    public String getInternalPackingTypeName() {
        return internalPackingTypeName;
    }

    public void setInternalPackingTypeName(String internalPackingTypeName) {
        this.internalPackingTypeName = internalPackingTypeName;
    }

    public String getInternalPackingTypeDesc() {
        return internalPackingTypeDesc;
    }

    public void setInternalPackingTypeDesc(String internalPackingTypeDesc) {
        this.internalPackingTypeDesc = internalPackingTypeDesc;
    }

    public BigDecimal getInternalPackingTypeWeight() {
        return internalPackingTypeWeight;
    }

    public void setInternalPackingTypeWeight(BigDecimal internalPackingTypeWeight) {
        this.internalPackingTypeWeight = internalPackingTypeWeight;
    }

    public Integer getFkPackageUomId() {
        return fkPackageUomId;
    }

    public void setFkPackageUomId(Integer fkPackageUomId) {
        this.fkPackageUomId = fkPackageUomId;
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

    public String getInternalPackingTypeIsBulk() {
        return internalPackingTypeIsBulk;
    }

    public void setInternalPackingTypeIsBulk(String internalPackingTypeIsBulk) {
        this.internalPackingTypeIsBulk = internalPackingTypeIsBulk;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkInternalPackingTypeId != null ? pkInternalPackingTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterInternalPackingType)) {
            return false;
        }
        MasterInternalPackingType other = (MasterInternalPackingType) object;
        if ((this.pkInternalPackingTypeId == null && other.pkInternalPackingTypeId != null) || (this.pkInternalPackingTypeId != null && !this.pkInternalPackingTypeId.equals(other.pkInternalPackingTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.packing.domain.MasterInternalPackingType[ pkInternalPackingTypeId=" + pkInternalPackingTypeId + " ]";
    }
    
}

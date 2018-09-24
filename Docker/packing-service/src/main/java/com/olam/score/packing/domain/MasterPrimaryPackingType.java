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

import com.olam.score.packing.dto.PrimaryPackingTypeDto;

/**
 *
 * @author ramachandran_n02
 */
@Entity
@Table(name = "MASTER_PRIMARY_PACKING_TYPE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterPrimaryPackingType.findAll", query = "SELECT m FROM MasterPrimaryPackingType m")})
public class MasterPrimaryPackingType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PRIMARY_PACKING_TYPE_ID_SEQ")
   	@SequenceGenerator(name="PRIMARY_PACKING_TYPE_ID_SEQ", sequenceName="packing.PRIMARY_PACKING_TYPE_ID_SEQ",initialValue=1,allocationSize=1)
    @Column(name = "PK_PRIMARY_PACKING_TYPE_ID")
    private Integer pkPrimaryPackingTypeId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "PRIMARY_PACKING_TYPE_CODE")
    private String primaryPackingTypeCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "PRIMARY_PACKING_TYPE_NAME")
    private String primaryPackingTypeName;
    @Size(max = 1000)
    @Column(name = "PRIMARY_PACKING_TYPE_DESC")
    private String primaryPackingTypeDesc;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "PRIMARY_PACKING_TYPE_WEIGHT")
    private BigDecimal primaryPackingTypeWeight;
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
    private String primaryPackingTypeIsBulk;

    public MasterPrimaryPackingType() {
    }

    public MasterPrimaryPackingType(Integer pkPrimaryPackingTypeId) {
        this.pkPrimaryPackingTypeId = pkPrimaryPackingTypeId;
    }

    public MasterPrimaryPackingType(Integer pkPrimaryPackingTypeId, String PrimaryPackingTypeCode, String PrimaryPackingTypeName) {
        this.pkPrimaryPackingTypeId = pkPrimaryPackingTypeId;
        this.primaryPackingTypeCode = PrimaryPackingTypeCode;
        this.primaryPackingTypeName = PrimaryPackingTypeName;
    }

    public Integer getPkPrimaryPackingTypeId() {
        return pkPrimaryPackingTypeId;
    }

    public void setPkPrimaryPackingTypeId(Integer pkPrimaryPackingTypeId) {
        this.pkPrimaryPackingTypeId = pkPrimaryPackingTypeId;
    }

    public String getPrimaryPackingTypeCode() {
        return primaryPackingTypeCode;
    }

    public void setPrimaryPackingTypeCode(String PrimaryPackingTypeCode) {
        this.primaryPackingTypeCode = PrimaryPackingTypeCode;
    }

    public String getPrimaryPackingTypeName() {
        return primaryPackingTypeName;
    }

    public void setPrimaryPackingTypeName(String PrimaryPackingTypeName) {
        this.primaryPackingTypeName = PrimaryPackingTypeName;
    }

    public String getPrimaryPackingTypeDesc() {
        return primaryPackingTypeDesc;
    }

    public void setPrimaryPackingTypeDesc(String PrimaryPackingTypeDesc) {
        this.primaryPackingTypeDesc = PrimaryPackingTypeDesc;
    }

    public BigDecimal getPrimaryPackingTypeWeight() {
        return primaryPackingTypeWeight;
    }

    public void setPrimaryPackingTypeWeight(BigDecimal PrimaryPackingTypeWeight) {
        this.primaryPackingTypeWeight = PrimaryPackingTypeWeight;
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

    public String getPrimaryPackingTypeIsBulk() {
        return primaryPackingTypeIsBulk;
    }

    public void setPrimaryPackingTypeIsBulk(String PrimaryPackingTypeIsBulk) {
        this.primaryPackingTypeIsBulk = PrimaryPackingTypeIsBulk;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkPrimaryPackingTypeId != null ? pkPrimaryPackingTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterPrimaryPackingType)) {
            return false;
        }
        MasterPrimaryPackingType other = (MasterPrimaryPackingType) object;
        if ((this.pkPrimaryPackingTypeId == null && other.pkPrimaryPackingTypeId != null) || (this.pkPrimaryPackingTypeId != null && !this.pkPrimaryPackingTypeId.equals(other.pkPrimaryPackingTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.packing.domain.MasterPrimaryPackingType[ pkPrimaryPackingTypeId=" + pkPrimaryPackingTypeId + " ]";
    }

    public PrimaryPackingTypeDto convertBasicEntityToDto() {
		PrimaryPackingTypeDto dto= new PrimaryPackingTypeDto();
    	dto.setInternalPackingTypeId(pkPrimaryPackingTypeId);
    	dto.setFkStatusId(fkStatusId);
    	dto.setInternalPackingTypeName(primaryPackingTypeName);
    	dto.setInternalPackingTypeCode(primaryPackingTypeCode);
 		return dto;
	}
}

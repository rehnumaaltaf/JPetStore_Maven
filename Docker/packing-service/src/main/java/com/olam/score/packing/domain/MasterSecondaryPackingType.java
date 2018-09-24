/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olam.score.packing.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
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

import com.olam.score.packing.dto.SecondaryPackingTypeDTO;






/**
 *
 * @author ramachandran_n02
 */
@Entity
@Table(name = "MASTER_SECONDARY_PACKING_TYPE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterSecondaryPackingType.findAll", query = "SELECT m FROM MasterSecondaryPackingType m")})
public class MasterSecondaryPackingType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_SECONDARY_PACKING_TYPE_ID")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SECONDARY_PACKING_TYPE_ID_SEQ_GENERATOR")
   	@SequenceGenerator(name="SECONDARY_PACKING_TYPE_ID_SEQ_GENERATOR", sequenceName="SECONDARY_PACKING_TYPE_ID_SEQ",allocationSize=1)
    private Integer pkSecondaryPackingTypeId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "SECONDARY_PACKING_TYPE_CODE")
    private String secondaryPackingTypeCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "SECONDARY_PACKING_TYPE_NAME")
    private String secondaryPackingTypeName;
    @Size(max = 1000)
    @Column(name = "SECONDARY_PACKING_TYPE_DESC")
    private String secondaryPackingTypeDesc;
    @Size(max = 1)
    @Column(name = "SECONDARY_PACKING_TYPE_IS_BULK")
    private String secondaryPackingTypeIsBulk;
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
   @OneToMany(cascade = CascadeType.ALL,mappedBy = "secPackAssoc")
    private List<MasterPackingAssoc> masterPackingAssocList;

    public MasterSecondaryPackingType() {
    }

    public MasterSecondaryPackingType(Integer pkSecondaryPackingTypeId) {
        this.pkSecondaryPackingTypeId = pkSecondaryPackingTypeId;
    }

    public MasterSecondaryPackingType(Integer pkSecondaryPackingTypeId, String secondaryPackingTypeCode, String secondaryPackingTypeName) {
        this.pkSecondaryPackingTypeId = pkSecondaryPackingTypeId;
        this.secondaryPackingTypeCode = secondaryPackingTypeCode;
        this.secondaryPackingTypeName = secondaryPackingTypeName;
    }

    public Integer getPkSecondaryPackingTypeId() {
        return pkSecondaryPackingTypeId;
    }

    public void setPkSecondaryPackingTypeId(Integer pkSecondaryPackingTypeId) {
        this.pkSecondaryPackingTypeId = pkSecondaryPackingTypeId;
    }

    public String getSecondaryPackingTypeCode() {
        return secondaryPackingTypeCode;
    }

    public void setSecondaryPackingTypeCode(String secondaryPackingTypeCode) {
        this.secondaryPackingTypeCode = secondaryPackingTypeCode;
    }

    public String getSecondaryPackingTypeName() {
        return secondaryPackingTypeName;
    }

    public void setSecondaryPackingTypeName(String secondaryPackingTypeName) {
        this.secondaryPackingTypeName = secondaryPackingTypeName;
    }

    public String getSecondaryPackingTypeDesc() {
        return secondaryPackingTypeDesc;
    }

    public void setSecondaryPackingTypeDesc(String secondaryPackingTypeDesc) {
        this.secondaryPackingTypeDesc = secondaryPackingTypeDesc;
    }

    public String getSecondaryPackingTypeIsBulk() {
        return secondaryPackingTypeIsBulk;
    }

    public void setSecondaryPackingTypeIsBulk(String secondaryPackingTypeIsBulk) {
        this.secondaryPackingTypeIsBulk = secondaryPackingTypeIsBulk;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkSecondaryPackingTypeId != null ? pkSecondaryPackingTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterSecondaryPackingType)) {
            return false;
        }
        MasterSecondaryPackingType other = (MasterSecondaryPackingType) object;
        if ((this.pkSecondaryPackingTypeId == null && other.pkSecondaryPackingTypeId != null) || (this.pkSecondaryPackingTypeId != null && !this.pkSecondaryPackingTypeId.equals(other.pkSecondaryPackingTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.packing.domain.MasterSecondaryPackingType[ pkSecondaryPackingTypeId=" + pkSecondaryPackingTypeId + " ]";
    }
    @XmlTransient
	public List<MasterPackingAssoc> getMasterPackingAssocList() {
		return masterPackingAssocList;
	}

	public void setMasterPackingAssocList(List<MasterPackingAssoc> masterPackingAssocList) {
		this.masterPackingAssocList = masterPackingAssocList;
	}

	public SecondaryPackingTypeDTO convertBasicEntityToDto() {
		SecondaryPackingTypeDTO dto= new SecondaryPackingTypeDTO();
    	dto.setPkSecondaryPackingTypeId(pkSecondaryPackingTypeId);
    	dto.setFkStatusId(fkStatusId);
    	dto.setSecondaryPackingTypeName(secondaryPackingTypeName);
    	dto.setSecondaryPackingTypeCode(secondaryPackingTypeCode);
 		return dto;
	}
}

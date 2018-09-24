/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olam.score.reference.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import org.modelmapper.ModelMapper;

import com.olam.score.reference.dto.UomDto;

/**
 *
 * @author ramachandran_n02
 */
@Entity
@Table(name = "MASTER_UOM")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterUom.findAll", query = "SELECT m FROM MasterUom m")})
public class MasterUom implements Serializable {
    private static final long serialVersionUID = 1L;
	@SequenceGenerator(name="MASTER_UOM_UOMIDSEQ_GENERATOR", sequenceName="UOM_ID_SEQ",allocationSize=1)
	@GeneratedValue(generator="MASTER_UOM_UOMIDSEQ_GENERATOR")
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_UOM_ID")
    private Integer pkUomId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "UOM_CODE")
    private String uomCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "UOM_NAME")
    private String uomName;
    @Size(max = 200)
    @Column(name = "UOM_FULLNAME")
    private String uomFullname;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "UOM_CONVERSION_FACTOR")
    private BigDecimal uomConversionFactor;
    @Size(max = 100)
    @Column(name = "CREATED_BY",updatable=false)
    private String createdBy;
    @Column(name = "CREATED_DATE",updatable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Size(max = 100)
    @Column(name = "MODIFIED_BY")
    private String modifiedBy;
   	@Column(name = "MODIFIED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;
    @Size(max = 50)
    @Column(name = "BASIS")
    private String basis;
    @Column(name = "FK_STATUS_ID")
    private Integer fkStatusId;
    @JoinColumn(name = "FK_BASE_UOM_ID", referencedColumnName = "PK_UOM_ID")
    @ManyToOne
    private MasterUom fkBaseUomId;

    public MasterUom() {
    }

    public MasterUom(Integer pkUomId) {
        this.pkUomId = pkUomId;
    }

    public MasterUom(Integer pkUomId, String uomCode, String uomName) {
        this.pkUomId = pkUomId;
        this.uomCode = uomCode;
        this.uomName = uomName;
    }

    public Integer getPkUomId() {
        return pkUomId;
    }

    public void setPkUomId(Integer pkUomId) {
        this.pkUomId = pkUomId;
    }

    public String getUomCode() {
        return uomCode;
    }

    public void setUomCode(String uomCode) {
        this.uomCode = uomCode;
    }

    public String getUomName() {
        return uomName;
    }

    public void setUomName(String uomName) {
        this.uomName = uomName;
    }

    public String getUomFullname() {
        return uomFullname;
    }

    public void setUomFullname(String uomFullname) {
        this.uomFullname = uomFullname;
    }

    public BigDecimal getUomConversionFactor() {
        return uomConversionFactor;
    }

    public void setUomConversionFactor(BigDecimal uomConversionFactor) {
        this.uomConversionFactor = uomConversionFactor;
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
    public String getBasis() {
		return basis;
	}

	public void setBasis(String basis) {
		this.basis = basis;
	}

    public Integer getFkStatusId() {
        return fkStatusId;
    }

    public void setFkStatusId(Integer statusId) {
        this.fkStatusId = statusId;
    }

    public MasterUom getFkBaseUomId() {
        return fkBaseUomId;
    }

    public void setFkBaseUomId(MasterUom fkBaseUomId) {
        this.fkBaseUomId = fkBaseUomId;
    }

    public UomDto convertToUomDto(Map<Integer,String> statusmap) {
        UomDto dto;
        ModelMapper modelMapper = new ModelMapper();
    	dto = modelMapper.map(this, UomDto.class);
    	if (this.getFkBaseUomId() != null) {
			dto.setParentUomId(this.getFkBaseUomId().getPkUomId());
			dto.setBaseUomCode(this.getFkBaseUomId().getUomCode());
		}
    	dto.setStatusName(statusmap.get(getFkStatusId()));
		return dto;
    }
  
    
}

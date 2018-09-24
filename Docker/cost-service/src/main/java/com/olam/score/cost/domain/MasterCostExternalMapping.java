package com.olam.score.cost.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import com.olam.score.cost.dto.MasterCostExternalMappingDto;

/**
*
* @author Manoj Kumar SP 
* Manual Creation
*/

@Entity
@Table(name = "MASTER_COST_EXTERNAL_MAPPING")
@XmlRootElement
@NamedQueries({
@NamedQuery(name = "MasterCostExternalMapping.findAll", query = "SELECT m FROM MasterCostExternalMapping m")})
public class MasterCostExternalMapping implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@NotNull
	@Column(name ="PK_COST_EXTERNAL_MAPPING_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="COST_EXTERNAL_MAPPING_ID_SEQ")
   	@SequenceGenerator(name="COST_EXTERNAL_MAPPING_ID_SEQ", sequenceName="COST_EXTERNAL_MAPPING_ID_SEQ",allocationSize=1)
	private Integer pkCostExternalMappingId;
	/*@Size(max = 4)
    @Column(name = "FK_COST_ID")
	private Integer fkCostId;*/
    @Column(name = "FK_EXTERNAL_SYSTEM_REF_ID")
	private Integer fkExternalSystemRefId;
	@Size(max = 100)
    @Column(name = "MAPPING_TYPE")
	private String mappingType;
	@Size(max = 100)
    @Column(name = "EXTERNAL_CODE")
	private String externalCode;
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
	
	@JoinColumn(name = "FK_COST_ID")
	@ManyToOne
	private	MasterCost costExterMapAssoc;
	
	public MasterCostExternalMapping(){
		
	}
	public Integer getPkCostExternalMappingId() {
		return pkCostExternalMappingId;
	}
	public void setPkCostExternalMappingId(Integer pkCostExternalMappingId) {
		this.pkCostExternalMappingId = pkCostExternalMappingId;
	}
	public MasterCost getCostExterMapAssoc() {
		return costExterMapAssoc;
	}
	public void setCostExterMapAssoc(MasterCost costExterMapAssoc) {
		this.costExterMapAssoc = costExterMapAssoc;
	}
	public Integer getFkExternalSystemRefId() {
		return fkExternalSystemRefId;
	}
	public void setFkExternalSystemRefId(Integer fkExternalSystemRefId) {
		this.fkExternalSystemRefId = fkExternalSystemRefId;
	}
	public String getMappingType() {
		return mappingType;
	}
	public void setMappingType(String mappingType) {
		this.mappingType = mappingType;
	}
	public String getExternalCode() {
		return externalCode;
	}
	public void setExternalCode(String externalCode) {
		this.externalCode = externalCode;
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
	

}

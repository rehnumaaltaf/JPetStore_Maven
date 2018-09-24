package com.olam.score.cost.domain;

import java.io.Serializable;
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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
*
* @author Manoj Kumar SP 
* Manual Creation
*/

@Entity
@Table(name = "MASTER_COST_PARTY_ASSOC")
@XmlRootElement
@NamedQueries({
@NamedQuery(name = "MasterCostPartyAssoc.findAll", query = "SELECT m FROM MasterCostPartyAssoc m")})
public class MasterCostPartyAssoc implements Serializable {
	
	@Id
	@Basic(optional = false)
	@NotNull
	@Column(name = "PK_COST_PARTY_ASSOC_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COST_PARTY_ASSOC_ID_SEQ")
	@SequenceGenerator(name = "COST_PARTY_ASSOC_ID_SEQ", sequenceName = "COST_PARTY_ASSOC_ID_SEQ", allocationSize = 1)
	private Integer pkCostPartyAssocId;
	@Column(name = "FK_PARTY_ID")
	private Integer fkPartyId;
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
	@JoinColumn(name = "FK_COST_ID")
	@ManyToOne
	private	MasterCost costPartyAssoc;
	
	public MasterCost getCostPartyAssoc() {
		return costPartyAssoc;
	}
	public void setCostPartyAssoc(MasterCost costPartyAssoc) {
		this.costPartyAssoc = costPartyAssoc;
	}
	public Integer getPkCostPartyAssocId() {
		return pkCostPartyAssocId;
	}
	public void setPkCostPartyAssocId(Integer pkCostPartyAssocId) {
		this.pkCostPartyAssocId = pkCostPartyAssocId;
	}
	public Integer getFkPartyId() {
		return fkPartyId;
	}
	public void setFkPartyId(Integer fkPartyId) {
		this.fkPartyId = fkPartyId;
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
	
	

}

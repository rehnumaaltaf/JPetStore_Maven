/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olam.score.packing.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Manoj Kumar SP Manually gendrated the class
 */
@Entity
@Table(name = "MASTER_PACKING_ASSOC")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "MasterPackingAssoc.findAll", query = "SELECT m FROM MasterPackingAssoc m") })
public class MasterPackingAssoc implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@NotNull
	@Column(name = "PK_PACKING_ASSOC_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PACKING_ASSOC_ID_SEQ_GENERATOR")
	@SequenceGenerator(name = "PACKING_ASSOC_ID_SEQ_GENERATOR", sequenceName = "PACKING_ASSOC_ID_SEQ", allocationSize = 1)
	private Integer pkPackingAssocId;
	/*@Column(name = "FK_SECONDARY_PACKING_TYPE_ID")
	private Integer fkSecondaryPackingTypeId;*/
	@Column(name = "FK_ORIGIN_ID")
	private Integer fkOriginId;
	@Column(name = "FK_PROD_ID")
	private Integer fkProdId;
	@Column(name = "FK_PRIMARY_PACKING_TYPE_ID")
	private Integer fkPrimaryPackingTypeId;
	@Column(name = "COUNT_OF_PRIMARY")
	private Float countOfPrimary;
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
	//@ManyToOne(fetch=FetchType.LAZY,cascade = CascadeType.ALL) , referencedColumnName = "FK_SECONDARY_PACKING_TYPE_ID"
	@JoinColumn(name = "FK_SECONDARY_PACKING_TYPE_ID")
	@ManyToOne(cascade = CascadeType.ALL)
	private	MasterSecondaryPackingType secPackAssoc;
	 

	public MasterPackingAssoc() {
	}

	public Integer getPkPackingAssocId() {
		return pkPackingAssocId;
	}

	public void setPkPackingAssocId(Integer pkPackingAssocId) {
		this.pkPackingAssocId = pkPackingAssocId;
	}

	/*public Integer getFkSecondaryPackingTypeId() {
		return fkSecondaryPackingTypeId;
	}

	public void setFkSecondaryPackingTypeId(Integer fkSecondaryPackingTypeId) {
		this.fkSecondaryPackingTypeId = fkSecondaryPackingTypeId;
	}*/

	public Integer getFkOriginId() {
		return fkOriginId;
	}

	public void setFkOriginId(Integer fkOriginId) {
		this.fkOriginId = fkOriginId;
	}

	public Integer getFkProdId() {
		return fkProdId;
	}

	public void setFkProdId(Integer fkProdId) {
		this.fkProdId = fkProdId;
	}

	public Integer getFkPrimaryPackingTypeId() {
		return fkPrimaryPackingTypeId;
	}

	public void setFkPrimaryPackingTypeId(Integer fkPrimaryPackingTypeId) {
		this.fkPrimaryPackingTypeId = fkPrimaryPackingTypeId;
	}

	public Float getCountOfPrimary() {
		return countOfPrimary;
	}

	public void setCountOfPrimary(Float countOfPrimary) {
		this.countOfPrimary = countOfPrimary;
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
	public MasterSecondaryPackingType getSecPackAssoc() {
		return secPackAssoc;
	}

	public void setSecPackAssoc(MasterSecondaryPackingType secPackAssoc) {
		this.secPackAssoc = secPackAssoc;
	} 

}

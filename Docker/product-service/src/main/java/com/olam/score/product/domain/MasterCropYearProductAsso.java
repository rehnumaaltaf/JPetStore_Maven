/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olam.score.product.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import com.olam.score.product.dto.CropYearDto;

/**
 *
 * @author ramachandran_n02
 */
@Entity
@Table(name = "MASTER_CROP_YEAR_PRODUCT",  schema = "product")
@XmlRootElement
public class MasterCropYearProductAsso implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MASTER_CROPYEARPRODIDSEQ_GENERATOR")
	@SequenceGenerator(name = "MASTER_CROPYEARPRODIDSEQ_GENERATOR", sequenceName = "CROP_YEAR_PRODUCT_ID_SEQ", allocationSize = 1, schema = "product")
	@Basic(optional = false)
	@NotNull
	@Column(name = "PK_CROP_YEAR_PRODUCT_ID")
	private Integer pkCropYearProductId;
	@Size(max = 100)
	@Column(name = "CREATED_BY")
	private String createdBy;
	@Column(name = "CREATED_DATE")
	private Timestamp createdDate;
	@Size(max = 100)
	@Column(name = "MODIFIED_BY")
	private String modifiedBy;
	@Column(name = "MODIFIED_DATE")
	private Timestamp modifiedDate;
	@JoinColumn(name = "FK_PROD_ID", referencedColumnName = "PK_PROD_ID")
	@ManyToOne
	private MasterProduct masterProduct;
	@JoinColumn(name = "FK_CROP_YEAR_ID", referencedColumnName = "PK_CROP_YEAR_ID")
	@ManyToOne
	private MasterCropYear masterCropYear;
	@Column(name = "FK_STATUS_ID")
	private Integer fkStatusId;

	public Integer getFkStatusId() {
		return fkStatusId;
	}

	public void setFkStatusId(Integer fkStatusId) {
		this.fkStatusId = fkStatusId;
	}

	public Integer getPkCropYearProductId() {
		return pkCropYearProductId;
	}

	public void setPkCropYearProductId(Integer pkCropYearProductId) {
		this.pkCropYearProductId = pkCropYearProductId;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Timestamp getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public MasterProduct getMasterProduct() {
		return masterProduct;
	}

	public void setMasterProduct(MasterProduct masterProduct) {
		this.masterProduct = masterProduct;
	}

	public MasterCropYear getMasterCropYear() {
		return masterCropYear;
	}

	public void setMasterCropYear(MasterCropYear masterCropYear) {
		this.masterCropYear = masterCropYear;
	}

	public CropYearDto cropYearDtoToModel(List<Map<Object, Object>> statusList, List<Map<Object, Object>> productList) {

		CropYearDto cropYearDto = new CropYearDto();
		cropYearDto.setCropYearId(getPkCropYearProductId());
		cropYearDto.setCropYearDescription("description of 2016-17");
		// cropYearDto.setProductGroup("Coffee");
		// cropYearDto.setProductGroupId(1);
		cropYearDto.setCreatedBy(getCreatedBy());
		cropYearDto.setCreatedDate(getCreatedDate());
		cropYearDto.setModifiedBy(getModifiedBy());
		cropYearDto.setModifiedDate(getModifiedDate());
		/*
		 * if (null != getFkStatusId()) {
		 * cropYearDto.setStatusId(getFkStatusId()); for (int x = 0; x <
		 * statusList.size(); x++) { if
		 * (statusList.get(x).get("pkStatusId").equals(getFkStatusId())) {
		 * cropYearDto.setStatusName((String)
		 * statusList.get(x).get("statusName")); break; } } }
		 */
		/*
		 * if (null != getProductGroupId()) { cropYearDto.setProductGroupId(1);
		 * for (int x = 0; x < productList.size(); x++) { if
		 * (productList.get(x).get("prodId").equals(getProductGroupId())) {
		 * cropYearDto.setStatusName((String)
		 * productList.get(x).get("prodName")); break; } } }
		 */
		return cropYearDto;

	}

}

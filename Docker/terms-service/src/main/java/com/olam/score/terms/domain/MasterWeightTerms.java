/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olam.score.terms.domain;

import java.io.Serializable;
import java.util.Date;
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

import com.olam.score.terms.dto.FranchiseDto;
import com.olam.score.terms.dto.ToleranceDto;


import com.olam.score.terms.dto.WeightTermDto;

/**
 *
 * @author Ramachandran_N02
 */
@Entity
@Table(name = "MASTER_WEIGHT_TERMS")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "MasterWeightTerms.findAll", query = "SELECT m FROM MasterWeightTerms m") })
public class MasterWeightTerms implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@NotNull
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TERMS_WEIGHTTERMS_WEIGHTTERMSIDSEQGENERATOR")
	@SequenceGenerator(name = "TERMS_WEIGHTTERMS_WEIGHTTERMSIDSEQGENERATOR", sequenceName = "WEIGHT_TERMS_ID_SEQ", initialValue = 1, allocationSize = 1)
	@Column(name = "PK_WEIGHT_TERMS_ID")
	private Integer pkWeightTermsId;
	@Size(max = 200)
	@Column(name = "WEIGHT_TERMS_NAME")
	private String weightTermsName;
	@Size(max = 20)
	@Column(name = "WEIGHT_TERMS_CODE")
	private String weightTermsCode;
	@Size(max = 1000)
	@Column(name = "WEIGHT_TERMS_DESC")
	private String weightTermsDesc;
	@Size(max = 1)
	@Column(name = "WEIGHT_TERMS_IS_FRANCHISE_APPLICABLE")
	private String weightTermsIsFranchiseApplicable;
	@Column(name = "WEIGHT_TERMS_DEFAULT_FRANCHISE_VALUE")
	private Double weightTermsDefaultFranchiseValue;
	@Size(max = 1)
	@Column(name = "WEIGHT_TERMS_IS_TOLERANCE_APPLICABLE")
	private String weightTermsIsToleranceApplicable;
	@Column(name = "WEIGHT_TERMS_DEFAULT_TOLERANCE_VALUE")
	private Double weightTermsDefaultToleranceValue;
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
	// @Max(value=?) @Min(value=?)//if you know range of your decimal fields
	// consider using these annotations to enforce field validation
	@Column(name = "CUSTOM_ATTRIBUTE_6")
	private Double customAttribute6;
	@Column(name = "CUSTOM_ATTRIBUTE_7")
	private Double customAttribute7;
	@Column(name = "CUSTOM_ATTRIBUTE_8")
	private Double customAttribute8;
	/*
	 * @JoinColumn(name = "FK_FRANCHISE_VALUE_UNIT_ID", referencedColumnName =
	 * "PK_FRANCHISE_VALUE_UNIT_ID")
	 * 
	 * @ManyToOne
	 */
	@Column(name = "FK_FRANCHISE_VALUE_UNIT_ID")
	private Integer fkFranchiseValueUnitId;

	/*
	 * @JoinColumn(name = "FK_TOLERANCE_VALUE_UNIT_ID", referencedColumnName =
	 * "PK_TOLERANCE_VALUE_UNIT_ID")
	 * 
	 * @ManyToOne
	 */
	@Column(name = "FK_TOLERANCE_VALUE_UNIT_ID")
	private Integer fkToleranceValueUnitId;
	@JoinColumn(name = "FK_WEIGHT_TERM_TYPE_REF_ID", referencedColumnName = "PK_WEIGHT_TERM_TYPE_REF_ID")
	@ManyToOne
	private MasterWeightTermTypeRef fkWeightTermTypeRefId;

	public MasterWeightTerms() {
	}

	public MasterWeightTerms(Integer pkWeightTermsId) {
		this.pkWeightTermsId = pkWeightTermsId;
	}

	public Integer getPkWeightTermsId() {
		return pkWeightTermsId;
	}

	public void setPkWeightTermsId(Integer pkWeightTermsId) {
		this.pkWeightTermsId = pkWeightTermsId;
	}

	public String getWeightTermsName() {
		return weightTermsName;
	}

	public void setWeightTermsName(String weightTermsName) {
		this.weightTermsName = weightTermsName;
	}

	public String getWeightTermsCode() {
		return weightTermsCode;
	}

	public void setWeightTermsCode(String weightTermsCode) {
		this.weightTermsCode = weightTermsCode;
	}

	public String getWeightTermsDesc() {
		return weightTermsDesc;
	}

	public void setWeightTermsDesc(String weightTermsDesc) {
		this.weightTermsDesc = weightTermsDesc;
	}

	public String getWeightTermsIsFranchiseApplicable() {
		return weightTermsIsFranchiseApplicable;
	}

	public void setWeightTermsIsFranchiseApplicable(String weightTermsIsFranchiseApplicable) {
		this.weightTermsIsFranchiseApplicable = weightTermsIsFranchiseApplicable;
	}

	public Double getWeightTermsDefaultFranchiseValue() {
		return weightTermsDefaultFranchiseValue;
	}

	public void setWeightTermsDefaultFranchiseValue(Double weightTermsDefaultFranchiseValue) {
		this.weightTermsDefaultFranchiseValue = weightTermsDefaultFranchiseValue;
	}

	public String getWeightTermsIsToleranceApplicable() {
		return weightTermsIsToleranceApplicable;
	}

	public void setWeightTermsIsToleranceApplicable(String weightTermsIsToleranceApplicable) {
		this.weightTermsIsToleranceApplicable = weightTermsIsToleranceApplicable;
	}

	public Double getWeightTermsDefaultToleranceValue() {
		return weightTermsDefaultToleranceValue;
	}

	public void setWeightTermsDefaultToleranceValue(Double weightTermsDefaultToleranceValue) {
		this.weightTermsDefaultToleranceValue = weightTermsDefaultToleranceValue;
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

	/*
	 * public MasterFranchiseValueUnit getFkFranchiseValueUnitId() { return
	 * fkFranchiseValueUnitId; }
	 * 
	 * public void setFkFranchiseValueUnitId(MasterFranchiseValueUnit
	 * fkFranchiseValueUnitId) { this.fkFranchiseValueUnitId =
	 * fkFranchiseValueUnitId; }
	 */
	public Integer getFkFranchiseValueUnitId() {
		return fkFranchiseValueUnitId;
	}

	public void setFkFranchiseValueUnitId(Integer fkFranchiseValueUnitId) {
		this.fkFranchiseValueUnitId = fkFranchiseValueUnitId;
	}
	/*
	 * public MasterToleranceValueUnit getFkToleranceValueUnitId() { return
	 * fkToleranceValueUnitId; } public void
	 * setFkToleranceValueUnitId(MasterToleranceValueUnit fkToleranceValueUnitId) {
	 * this.fkToleranceValueUnitId = fkToleranceValueUnitId; }
	 */

	public MasterWeightTermTypeRef getFkWeightTermTypeRefId() {
		return fkWeightTermTypeRefId;
	}

	public Integer getFkToleranceValueUnitId() {
		return fkToleranceValueUnitId;
	}

	public void setFkToleranceValueUnitId(Integer fkToleranceValueUnitId) {
		this.fkToleranceValueUnitId = fkToleranceValueUnitId;
	}

	public void setFkWeightTermTypeRefId(MasterWeightTermTypeRef fkWeightTermTypeRefId) {
		this.fkWeightTermTypeRefId = fkWeightTermTypeRefId;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (pkWeightTermsId != null ? pkWeightTermsId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof MasterWeightTerms)) {
			return false;
		}
		MasterWeightTerms other = (MasterWeightTerms) object;
		if ((this.pkWeightTermsId == null && other.pkWeightTermsId != null)
				|| (this.pkWeightTermsId != null && !this.pkWeightTermsId.equals(other.pkWeightTermsId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.olam.score.terms.domain.MasterWeightTerms[ pkWeightTermsId=" + pkWeightTermsId + " ]";
	}


	// My changes

	public WeightTermDto convertToWeightTerm(Map<Integer, String> statusMap, List<Map<Object, Object>> franchiseList,
			List<Map<Object, Object>> toleranceList) {

		WeightTermDto weightTermDto;
		// FranchiseDto franchiseDto = new FranchiseDto();
		ModelMapper modelMapper = new ModelMapper();
		weightTermDto = modelMapper.map(this, WeightTermDto.class);

		if (franchiseList != null) {
			String franchiseValueUnitName = null;
			String franchiseValueUnitCode = null;
			/* String franchiseValueUnitDesc = null; */
			Integer franchiseValueUnitId = null;
			//String statusName = null;

			for (int x = 0; x < franchiseList.size(); x++) {

				franchiseValueUnitId = (Integer) franchiseList.get(x).get("franchiseValueUnitId");
				if (franchiseValueUnitId != null && franchiseValueUnitId.equals(this.getFkFranchiseValueUnitId())) {
					franchiseValueUnitName = (String) franchiseList.get(x).get("franchiseValueUnitName");
					franchiseValueUnitCode = (String) franchiseList.get(x).get("franchiseValueUnitCode");
					//statusName = (String) franchiseList.get(x).get("statusName");
					/*
					 * franchiseValueUnitDesc = (String)
					 * franchiseList.get(x).get("franchiseValueUnitDesc");
					 */

					/*
					 * FranchiseDto franchiseDto = new FranchiseDto();
					 * franchiseDto.setFranchiseValueUnitCode(franchiseValueUnitCode);
					 * franchiseDto.setFranchiseValueUnitDesc(franchiseValueUnitDesc);
					 * franchiseDto.setFranchiseValueUnitId(franchiseValueUnitId);
					 * franchiseDto.setFranchiseValueUnitName(franchiseValueUnitName);
					 */

					weightTermDto.setFranchiseValueUnitId(franchiseValueUnitId);
					weightTermDto.setFranchiseValueUnitName(franchiseValueUnitName);
					weightTermDto.setFranchiseValueUnitCode(franchiseValueUnitCode);
					
					//weightTermDto.setStatusName(statusMap.get(getFkStatusId()));
					
					break;

				}
			}
		}

		if (toleranceList != null) {
			String toleranceValueUnitName = null;
			String toleranceValueUnitCode = null;
			/*String toleranceValueUnitDesc = null;*/
			Integer toleranceValueUnitId = null;

			for (int x = 0; x < toleranceList.size(); x++) {
				toleranceValueUnitId = (Integer) toleranceList.get(x).get("toleranceValueUnitId");
				if (toleranceValueUnitId != null && toleranceValueUnitId.equals(this.getFkToleranceValueUnitId())) {
					toleranceValueUnitName = (String) toleranceList.get(x).get("toleranceValueUnitName");
					toleranceValueUnitCode = (String) toleranceList.get(x).get("toleranceValueUnitCode");
					//toleranceValueUnitDesc = (String) franchiseList.get(x).get("toleranceValueUnitDesc");

					/*ToleranceDto toleranceDto = new ToleranceDto();
					toleranceDto.setToleranceValueUnitCode(toleranceValueUnitCode);
					toleranceDto.setToleranceValueUnitName(toleranceValueUnitName);
					toleranceDto.setToleranceValueUnitDesc(toleranceValueUnitDesc);
					toleranceDto.setToleranceValueUnitId(toleranceValueUnitId);
					weightTermDto.setToleranceDto(toleranceDto);*/
					
					
					weightTermDto.setToleranceValueUnitId(toleranceValueUnitId);
					weightTermDto.setToleranceValueUnitName(toleranceValueUnitName);
					weightTermDto.setToleranceValueUnitCode(toleranceValueUnitCode);
					
					break;

				}

			}

		}
		weightTermDto.setStatusName(statusMap.get(getFkStatusId()));
		return weightTermDto;

	}


}

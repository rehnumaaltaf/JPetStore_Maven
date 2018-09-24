/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olam.score.limit.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

import com.olam.score.common.util.WebServiceCallUtil;
import com.olam.score.limit.dto.CommentsDto;
import com.olam.score.limit.dto.LimitDetails;
import com.olam.score.limit.dto.LimitDto;
import com.olam.score.limit.service.LimitService;

/**
 *
 * @author rashmi.kedlaya
 */
@Entity
@Table(name = "MASTER_LIMIT")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "MasterLimit.findAll", query = "SELECT m FROM MasterLimit m") })
public class MasterLimit implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@SequenceGenerator(name = "MASTER_LIMIT_LIMITIDSEQ_GENERATOR", sequenceName = "LIMIT_ID_SEQ", allocationSize = 1)
	@GeneratedValue(generator = "MASTER_LIMIT_LIMITIDSEQ_GENERATOR")
	@Basic(optional = false)
	@NotNull
	@Column(name = "PK_LIMIT_ID")
	private Integer pkLimitId;
	@Size(max = 200)
	@SequenceGenerator(name = "MASTER_LIMIT_LIMITNOSEQ_GENERATOR", sequenceName = "LIMIT_ID_SEQ", allocationSize = 1)
	@GeneratedValue(generator = "LM" + "MASTER_LIMIT_LIMITNOSEQ_GENERATOR")
	@Column(name = "LIMIT_NUMBER")
	private String limitNumber;
	@Size(max = 500)
	@Column(name = "LIMIT_REMARKS")
	private String limitRemarks;
	@Size(max = 100)
	@Column(name = "CREATED_BY", updatable = false)
	private String createdBy;
	@Column(name = "CREATED_DATE", updatable = false)
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
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "fkLimitId")
	private Collection<MasterLimitComment> masterLimitCommentCollection;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "fkLimitId")
	private Collection<MasterLimitDetail> masterLimitDetailCollection;
	@JoinColumn(name = "FK_LIMIT_ALERT_LEVEL_ID", referencedColumnName = "PK_LIMIT_ALERT_LEVEL_ID")
	@ManyToOne
	private MasterLimitAlertLevel fkLimitAlertLevelId;
	@Column(name = "FK_PRIMARY_LIMIT_BASIS_ENTITY")
	private Integer fkPrimaryLimitBasisTypeId;
	@Column(name = "FK_ADDITIONAL_LMT_BASIS_ENTITY")
	private Integer fkAdditionalLmtBasisTypeId;
	@Column(name = "PRIMARY_LIMIT_BASIS_ID")
	private Integer primaryLimitBasisId;
	@Column(name = "ADDITIONAL_LIMIT_BASIS_ID")
	private Integer additionalLimitBasisId;

	public Integer getFkPrimaryLimitBasisTypeId() {
		return fkPrimaryLimitBasisTypeId;
	}

	public void setFkPrimaryLimitBasisTypeId(Integer fkPrimaryLimitBasisTypeId) {
		this.fkPrimaryLimitBasisTypeId = fkPrimaryLimitBasisTypeId;
	}

	public Integer getFkAdditionalLmtBasisTypeId() {
		return fkAdditionalLmtBasisTypeId;
	}

	public void setFkAdditionalLmtBasisTypeId(Integer fkAdditionalLmtBasisTypeId) {
		this.fkAdditionalLmtBasisTypeId = fkAdditionalLmtBasisTypeId;
	}

	public Integer getPrimaryLimitBasisId() {
		return primaryLimitBasisId;
	}

	public void setPrimaryLimitBasisId(Integer primaryLimitBasisId) {
		this.primaryLimitBasisId = primaryLimitBasisId;
	}

	public Integer getAdditionalLimitBasisId() {
		return additionalLimitBasisId;
	}

	public void setAdditionalLimitBasisId(Integer additionalLimitBasisId) {
		this.additionalLimitBasisId = additionalLimitBasisId;
	}

	public MasterLimit() {
	}

	public MasterLimit(Integer pkLimitId) {
		this.pkLimitId = pkLimitId;
	}

	public Integer getPkLimitId() {
		return pkLimitId;
	}

	public void setPkLimitId(Integer pkLimitId) {
		this.pkLimitId = pkLimitId;
	}

	public String getLimitNumber() {
		return limitNumber;
	}

	public void setLimitNumber(String limitNumber) {
		this.limitNumber = limitNumber;
	}

	public String getLimitRemarks() {
		return limitRemarks;
	}

	public void setLimitRemarks(String limitRemarks) {
		this.limitRemarks = limitRemarks;
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

	@XmlTransient
	public Collection<MasterLimitComment> getMasterLimitCommentCollection() {
		return masterLimitCommentCollection;
	}

	public void setMasterLimitCommentCollection(Collection<MasterLimitComment> masterLimitCommentCollection) {
		this.masterLimitCommentCollection = masterLimitCommentCollection;
	}

	@XmlTransient
	public Collection<MasterLimitDetail> getMasterLimitDetailCollection() {
		return masterLimitDetailCollection;
	}

	public void setMasterLimitDetailCollection(Collection<MasterLimitDetail> masterLimitDetailCollection) {
		this.masterLimitDetailCollection = masterLimitDetailCollection;
	}

	public MasterLimitAlertLevel getFkLimitAlertLevelId() {
		return fkLimitAlertLevelId;
	}

	public void setFkLimitAlertLevelId(MasterLimitAlertLevel fkLimitAlertLevelId) {
		this.fkLimitAlertLevelId = fkLimitAlertLevelId;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (pkLimitId != null ? pkLimitId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof MasterLimit)) {
			return false;
		}
		MasterLimit other = (MasterLimit) object;
		if ((this.pkLimitId == null && other.pkLimitId != null)
				|| (this.pkLimitId != null && !this.pkLimitId.equals(other.pkLimitId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.olam.score.terms.domain.MasterLimit[ pkLimitId=" + pkLimitId + " ]";
	}

	public LimitDto convertEntityToDto(Map<Integer, String> statusMap, List<Object[]> entityList,
			List<Map<Object, Object>> uomList, List<Map<Object, Object>> currencyList, WebServiceCallUtil webServiceCall) {
		LimitDto dto = new LimitDto();
		dto.setLimitHeaderId(pkLimitId);
		dto.setLimitHeaderNo(limitNumber);
		dto.setStatusId(fkStatusId);
		dto.setStatusName(statusMap.get(fkStatusId));
		dto.setCreatedBy(createdBy);
		dto.setCreatedDate((Timestamp) createdDate);
		dto.setModifiedBy(modifiedBy);
		dto.setModifiedDate((Timestamp) modifiedDate);
		dto = setBasistype(entityList, dto, webServiceCall);
		dto.setComments(setComments());
		dto.setLimitDetails(setLimitDetails(uomList, currencyList));
		return dto;
	}

	private List<LimitDetails> setLimitDetails(List<Map<Object, Object>> uomList,
			List<Map<Object, Object>> currencyList) {
		List<LimitDetails> limitDetailsList = new ArrayList<>();
		Collection<MasterLimitDetail> limitDetailCollection = getMasterLimitDetailCollection();
		for (MasterLimitDetail masterLimitDetail : limitDetailCollection) {
			LimitDetails detail = masterLimitDetail.convertEntityToDto(uomList, currencyList);
			limitDetailsList.add(detail);
		}
		return limitDetailsList;
	}

	private List<CommentsDto> setComments() {
		Collection<MasterLimitComment> commentCollection = getMasterLimitCommentCollection();
		List<CommentsDto> commentDtoList = new ArrayList<>();
		for (MasterLimitComment masterLimitComment : commentCollection) {
			CommentsDto commentDto = new CommentsDto();
			commentDto.setLimitCommentId(masterLimitComment.getPkLimitCommentId());
			commentDto.setCreatedDate((Timestamp) masterLimitComment.getCreatedDate());
			commentDto.setCommentText(masterLimitComment.getCommentText());
			commentDtoList.add(commentDto);
		}
		Collections.sort(commentDtoList,Collections.reverseOrder((CommentsDto p1, CommentsDto p2) -> p1.getCreatedDate().compareTo(p2.getCreatedDate())) );
		return commentDtoList;
	}

	private LimitDto setBasistype(List<Object[]> entityList, LimitDto dto, WebServiceCallUtil webServiceCall) {
		for (Object[] authEntity : entityList) {
			if (((Integer) authEntity[0]).equals(fkPrimaryLimitBasisTypeId)) {
				dto.setLimitBasisTypeId((Integer) authEntity[0]);
				dto.setLimitBasisTypeName((String) authEntity[1]);
				dto = setBasis(dto, authEntity[2], webServiceCall);
			}
			if (((Integer) authEntity[0]).equals(fkAdditionalLmtBasisTypeId)) {
				dto.setAdditionallimitBasisTypeId((Integer) authEntity[0]);
				dto.setAdditionallimitBasisTypeName((String) authEntity[1]);
				dto = setAdditionalBasis(dto, authEntity[2], webServiceCall);
			}

		}
		return dto;
	}

	@SuppressWarnings("unchecked")
	private LimitDto setBasis(LimitDto dto, Object entityUrl, WebServiceCallUtil webServiceCall) {
		
		Map<Object, Object> map = new HashMap<>();
		List<Map<Object, Object>> exchangeList = new ArrayList<>();
		String newEntityUrl=(String)entityUrl;
		String[] entityName=newEntityUrl.split("/");
		if (dto.getLimitBasisTypeName().equalsIgnoreCase("Exchange")) {
			exchangeList = webServiceCall.getInternalServiceData(entityName[1]+"-service" , newEntityUrl);
			for (Map<Object, Object> exchangeMap : exchangeList) {
				if (primaryLimitBasisId.equals((Integer) exchangeMap.get("partyId"))) {
					dto.setLimitBasisId((Integer) exchangeMap.get("partyId"));
					dto.setLimitBasisCode((String) exchangeMap.get("partyCode"));
					dto.setLimitBasisName((String) exchangeMap.get("partyName"));
					break;
				}
			}
		}else if (dto.getLimitBasisTypeName().equalsIgnoreCase("Trader")) {
			map = (Map<Object, Object>) webServiceCall.internalServiceCall(entityName[1]+"-config-service" , newEntityUrl + "/" + primaryLimitBasisId).getBody().getBody();
				dto.setLimitBasisId((Integer) map.get("userId"));
				dto.setLimitBasisCode((String) map.get("userCode"));
				dto.setLimitBasisName((String) map.get("userName"));

		} else {
			map = (Map<Object, Object>) webServiceCall.internalServiceCall(entityName[1]+"-service" , newEntityUrl + "/" + primaryLimitBasisId).getBody().getBody();

			if (dto.getLimitBasisTypeName().equalsIgnoreCase("Product")) {
				dto.setLimitBasisId((Integer) map.get("prodId"));
				dto.setLimitBasisCode((String) map.get("prodCode"));
				dto.setLimitBasisName((String) map.get("prodName"));
			} else {
				dto.setLimitBasisId((Integer) map.get(dto.getLimitBasisTypeName().toLowerCase() + "Id"));
				dto.setLimitBasisCode((String) map.get(dto.getLimitBasisTypeName().toLowerCase() + "Code"));
				dto.setLimitBasisName((String) map.get(dto.getLimitBasisTypeName().toLowerCase() + "Name"));
			}
		}

		return dto;
	}

	@SuppressWarnings("unchecked")
	private LimitDto setAdditionalBasis(LimitDto dto, Object entityUrl, WebServiceCallUtil webServiceCall) {
		String newEntityUrl=(String)entityUrl;
		String[] entityName=newEntityUrl.split("/");
		Map<Object, Object> map = (Map<Object, Object>) webServiceCall.internalServiceCall(entityName[1]+"-service", newEntityUrl + "/" + additionalLimitBasisId).getBody().getBody();
		if (dto.getAdditionallimitBasisTypeName().equalsIgnoreCase("Exchange")) {
			dto.setAdditionallimitBasisId((Integer) map.get("partyId"));
			dto.setAdditionallimitBasisCode((String) map.get("partyCode"));
			dto.setAdditionallimitBasisName((String) map.get("partyName"));
		} else if (dto.getAdditionallimitBasisTypeName().equalsIgnoreCase("Trader")) {
			dto.setAdditionallimitBasisId((Integer) map.get("userId"));
			dto.setAdditionallimitBasisCode((String) map.get("userCode"));
			dto.setAdditionallimitBasisName((String) map.get("userName"));
		} else if (dto.getAdditionallimitBasisTypeName().equalsIgnoreCase("Product")) {
			dto.setAdditionallimitBasisId((Integer) map.get("prodId"));
			dto.setAdditionallimitBasisCode((String) map.get("prodCode"));
			dto.setAdditionallimitBasisName((String) map.get("prodName"));
		}else {
			dto.setAdditionallimitBasisId(
					(Integer) map.get(dto.getAdditionallimitBasisTypeName().toLowerCase() + "Id"));
			dto.setAdditionallimitBasisCode(
					(String) map.get(dto.getAdditionallimitBasisTypeName().toLowerCase() + "Code"));
			dto.setAdditionallimitBasisName(
					(String) map.get(dto.getAdditionallimitBasisTypeName().toLowerCase() + "Name"));
		}
		return dto;
	}
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olam.score.common.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ramachandran_N02
 */
@Entity
@Table(name = "MASTER_VIEW_SCOPE_REF")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "MasterViewScopeRef.findAll", query = "SELECT m FROM MasterViewScopeRef m"),
		@NamedQuery(name = "MasterViewScopeRef.findByPkViewScopeRefId", query = "SELECT m FROM MasterViewScopeRef m WHERE m.pkViewScopeRefId = :pkViewScopeRefId"),
		@NamedQuery(name = "MasterViewScopeRef.findByViewScopeRefCode", query = "SELECT m FROM MasterViewScopeRef m WHERE m.viewScopeRefCode = :viewScopeRefCode"),
		@NamedQuery(name = "MasterViewScopeRef.findByViewScopeRefName", query = "SELECT m FROM MasterViewScopeRef m WHERE m.viewScopeRefName = :viewScopeRefName"),
		@NamedQuery(name = "MasterViewScopeRef.findByViewScopeRefFullname", query = "SELECT m FROM MasterViewScopeRef m WHERE m.viewScopeRefFullname = :viewScopeRefFullname"),
		@NamedQuery(name = "MasterViewScopeRef.findByCreatedBy", query = "SELECT m FROM MasterViewScopeRef m WHERE m.createdBy = :createdBy"),
		@NamedQuery(name = "MasterViewScopeRef.findByCreatedDate", query = "SELECT m FROM MasterViewScopeRef m WHERE m.createdDate = :createdDate"),
		@NamedQuery(name = "MasterViewScopeRef.findByModifiedBy", query = "SELECT m FROM MasterViewScopeRef m WHERE m.modifiedBy = :modifiedBy"),
		@NamedQuery(name = "MasterViewScopeRef.findByModifiedDate", query = "SELECT m FROM MasterViewScopeRef m WHERE m.modifiedDate = :modifiedDate") })
public class MasterViewScopeRef implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@NotNull
	@Column(name = "PK_VIEW_SCOPE_REF_ID")
	private Integer pkViewScopeRefId;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 20)
	@Column(name = "VIEW_SCOPE_REF_CODE")
	private String viewScopeRefCode;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 100)
	@Column(name = "VIEW_SCOPE_REF_NAME")
	private String viewScopeRefName;
	@Size(max = 100)
	@Column(name = "VIEW_SCOPE_REF_FULLNAME")
	private String viewScopeRefFullname;
	@Size(max = 20)
	@Column(name = "CREATED_BY")
	private String createdBy;
	@Column(name = "CREATED_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	@Size(max = 20)
	@Column(name = "MODIFIED_BY")
	private String modifiedBy;
	@Column(name = "MODIFIED_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedDate;
	@Column(name = "FK_STATUS_ID")
	private Integer fkStatusId;

	public MasterViewScopeRef() {
	}

	public MasterViewScopeRef(Integer pkViewScopeRefId, String viewScopeRefCode, String viewScopeRefName) {
		this.pkViewScopeRefId = pkViewScopeRefId;
		this.viewScopeRefCode = viewScopeRefCode;
		this.viewScopeRefName = viewScopeRefName;
	}

	public Integer getPkViewScopeRefId() {
		return pkViewScopeRefId;
	}

	public void setPkViewScopeRefId(Integer pkViewScopeRefId) {
		this.pkViewScopeRefId = pkViewScopeRefId;
	}

	public String getViewScopeRefCode() {
		return viewScopeRefCode;
	}

	public void setViewScopeRefCode(String viewScopeRefCode) {
		this.viewScopeRefCode = viewScopeRefCode;
	}

	public String getViewScopeRefName() {
		return viewScopeRefName;
	}

	public void setViewScopeRefName(String viewScopeRefName) {
		this.viewScopeRefName = viewScopeRefName;
	}

	public String getViewScopeRefFullname() {
		return viewScopeRefFullname;
	}

	public void setViewScopeRefFullname(String viewScopeRefFullname) {
		this.viewScopeRefFullname = viewScopeRefFullname;
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

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (pkViewScopeRefId != null ? pkViewScopeRefId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof MasterViewScopeRef)) {
			return false;
		}
		MasterViewScopeRef other = (MasterViewScopeRef) object;
		if ((this.pkViewScopeRefId == null && other.pkViewScopeRefId != null)
				|| (this.pkViewScopeRefId != null && !this.pkViewScopeRefId.equals(other.pkViewScopeRefId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "controller.MasterViewScopeRef[ pkViewScopeRefId=" + pkViewScopeRefId + " ]";
	}

}

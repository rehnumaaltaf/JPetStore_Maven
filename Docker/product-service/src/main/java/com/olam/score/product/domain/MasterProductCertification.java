/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olam.score.product.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ramachandran_n02
 */
@Entity
@Table(name = "MASTER_PRODUCT_CERTIFICATION", schema = "product")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "MasterProductCertification.findAll", query = "SELECT m FROM MasterProductCertification m") })
public class MasterProductCertification implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MASTER_PRODUCTCERTIFICATIONIDSEQ_GENERATOR")
	@SequenceGenerator(name = "MASTER_PRODUCTCERTIFICATIONIDSEQ_GENERATOR", sequenceName = "PROD_CERT_ID_SEQ", allocationSize = 1, schema = "product")
	@Basic(optional = false)
	@NotNull
	@Column(name = "PK_PROD_CERT_ID")
	private Integer pkProdCertId;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 20)
	@Column(name = "PROD_CERT_CODE")
	private String prodCertCode;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 200)
	@Column(name = "PROD_CERT_NAME")
	private String prodCertName;
	@Size(max = 200)
	@Column(name = "PROD_CERT_FULLNAME")
	private String prodCertFullname;
	@Column(name = "PROD_CERT_DESC")
	private String prodCertDescription;
	@Lob
	@Column(name = "LOGO_BLOB")
	private byte[] logoBlob;
	@Size(max = 20)
	@Column(name = "CREATED_BY")
	private String createdBy;
	@Column(name = "CREATED_DATE")
	private Timestamp createdDate;
	@Size(max = 20)
	@Column(name = "MODIFIED_BY")
	private String modifiedBy;
	@Column(name = "MODIFIED_DATE")
	private Timestamp modifiedDate;
	@Column(name = "FK_STATUS_ID")
	private Integer fkStatusId;
	@Size(max = 20)
	@Column(name = "PROD_CERT_LOGO")
	private String prodCertLogo;
	@Column(name = "FK_CERTF_BODY_PARTY_ID")
	private Integer fkCertfBodyPartyId;
	@Column(name = "REGISTRATION_NUMBER")
	private String registrationNumber;
	@Column(name = "VALID_FROM")
	private Timestamp validFrom;
	@Column(name = "VALID_TO")
	private Timestamp validTo;

	public MasterProductCertification() {
	}

	public MasterProductCertification(Integer pkProdCertId) {
		this.pkProdCertId = pkProdCertId;
	}

	public MasterProductCertification(Integer pkProdCertId, String prodCertCode, String prodCertName) {
		this.pkProdCertId = pkProdCertId;
		this.prodCertCode = prodCertCode;
		this.prodCertName = prodCertName;
	}

	public Integer getPkProdCertId() {
		return pkProdCertId;
	}

	public void setPkProdCertId(Integer pkProdCertId) {
		this.pkProdCertId = pkProdCertId;
	}

	public String getProdCertCode() {
		return prodCertCode;
	}

	public void setProdCertCode(String prodCertCode) {
		this.prodCertCode = prodCertCode;
	}

	public String getProdCertName() {
		return prodCertName;
	}

	public void setProdCertName(String prodCertName) {
		this.prodCertName = prodCertName;
	}

	public String getProdCertFullname() {
		return prodCertFullname;
	}

	public void setProdCertFullname(String prodCertFullname) {
		this.prodCertFullname = prodCertFullname;
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

	public Integer getFkStatusId() {
		return fkStatusId;
	}

	public void setFkStatusId(Integer fkStatusId) {
		this.fkStatusId = fkStatusId;
	}

	public String getProdCertLogo() {
		return prodCertLogo;
	}

	public void setProdCertLogo(String prodCertLogo) {
		this.prodCertLogo = prodCertLogo;
	}

	public Integer getFkCertfBodyPartyId() {
		return fkCertfBodyPartyId;
	}

	public void setFkCertfBodyPartyId(Integer fkCertfBodyPartyId) {
		this.fkCertfBodyPartyId = fkCertfBodyPartyId;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public Timestamp getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(Timestamp validFrom) {
		this.validFrom = validFrom;
	}

	public Timestamp getValidTo() {
		return validTo;
	}

	public void setValidTo(Timestamp validTo) {
		this.validTo = validTo;
	}

	public String getProdCertDescription() {
		return prodCertDescription;
	}

	public void setProdCertDescription(String prodCertDescription) {
		this.prodCertDescription = prodCertDescription;
	}

	public byte[] getLogoBlob() {
		return logoBlob;
	}

	public void setLogoBlob(byte[] logoBlob) {
		this.logoBlob = logoBlob;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (pkProdCertId != null ? pkProdCertId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof MasterProductCertification)) {
			return false;
		}
		MasterProductCertification other = (MasterProductCertification) object;
		if ((this.pkProdCertId == null && other.pkProdCertId != null)
				|| (this.pkProdCertId != null && !this.pkProdCertId.equals(other.pkProdCertId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.olam.score.product.domain.MasterProductCertification[ pkProdCertId=" + pkProdCertId + " ]";
	}

}

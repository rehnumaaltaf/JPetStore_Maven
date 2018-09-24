package com.olam.score.product.dto;

import java.sql.Timestamp;

import javax.validation.constraints.NotNull;

import com.olam.score.common.dto.BaseDto;

import io.swagger.annotations.ApiModelProperty;

public class CertificationDto extends BaseDto {

	private Integer pkProdCertId;
	@NotNull(message = "notNull_prodCertCode") 	@ApiModelProperty(notes = "Certification Code",required=true)
	private String prodCertCode;
	@NotNull(message = "notNull_prodCertName") 	@ApiModelProperty(notes = "Certification Name",required=true)
	private String prodCertName;
	private String prodCertDescription;
	private byte[] prodCertLogo;
	private Integer fkCertfBodyPartyId;
	private String fkCertfBodyPartyName;
	@NotNull(message = "notNull_registrationNumber") 	@ApiModelProperty(notes = "Certification RegistrationNumber",required=true)
	private String registrationNumber;
	private Timestamp validFrom;
	private Timestamp validTo;
	private Integer fkStatusId;
	private String statusName;

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

	public String getProdCertDescription() {
		return prodCertDescription;
	}

	public void setProdCertDescription(String prodCertDescription) {
		this.prodCertDescription = prodCertDescription;
	}

	public byte[] getProdCertLogo() {
		return prodCertLogo;
	}

	public void setProdCertLogo(byte[] prodCertLogo) {
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

	public Integer getFkStatusId() {
		return fkStatusId;
	}

	public void setFkStatusId(Integer fkStatusId) {
		this.fkStatusId = fkStatusId;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getFkCertfBodyPartyName() {
		return fkCertfBodyPartyName;
	}

	public void setFkCertfBodyPartyName(String fkCertfBodyPartyName) {
		this.fkCertfBodyPartyName = fkCertfBodyPartyName;
	}

}

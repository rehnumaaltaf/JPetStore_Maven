package com.olam.score.product.domain;

import java.io.Serializable;
import java.util.Date;

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

/**
*
* @author Manoj Kumar SP
* Manually Added
*/
@Entity
@Table(name = "MASTER_BLEND_INPUT_CERTIFICATION")
@XmlRootElement
@NamedQueries({
   @NamedQuery(name = "MasterBlendInputCertification.findAll", query = "SELECT m FROM MasterBlendInputCertification m")})
public class MasterBlendInputCertification implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@NotNull
	@Column(name = "PK_BLEND_INPUT_CERTIFICATION_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="BLEND_INPUT_CERTIFICATION_ID_SEQ_GENERATOR")
   	@SequenceGenerator(name="BLEND_INPUT_CERTIFICATION_ID_SEQ_GENERATOR", sequenceName="BLEND_INPUT_CERTIFICATION_ID_SEQ",allocationSize=1)
	private Integer pkBlendInputCertificationId;
	
	 @JoinColumn(name = "FK_PROD_CERT_ID", referencedColumnName = "PK_PROD_CERT_ID")
	    @ManyToOne
	    private MasterProductCertification fkProdCertId;
	@Column(name = "CERTIFICATION_PERCENTAGE")
	private Float certificationPercentage;
	@Column(name = "FK_BLEND_INPUT_ID")
	private Integer fkBlendInputId;
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

	@JoinColumn(name = "FK_BLEND_TEMPLATE_ID")
	@ManyToOne
	private	MasterBlendTemplate masterBlendInputCertAssoc;
	public MasterBlendInputCertification() {

	}
	
	public Integer getPkBlendInputCertificationId() {
		return pkBlendInputCertificationId;
	}
	public void setPkBlendInputCertificationId(Integer pkBlendInputCertificationId) {
		this.pkBlendInputCertificationId = pkBlendInputCertificationId;
	}
	
	
	public Float getCertificationPercentage() {
		return certificationPercentage;
	}
	public void setCertificationPercentage(Float certificationPercentage) {
		this.certificationPercentage = certificationPercentage;
	}
	public Integer getFkBlendInputId() {
		return fkBlendInputId;
	}
	public void setFkBlendInputId(Integer fkBlendInputId) {
		this.fkBlendInputId = fkBlendInputId;
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

	public MasterBlendTemplate getMasterBlendInputCertAssoc() {
		return masterBlendInputCertAssoc;
	}

	public MasterProductCertification getFkProdCertId() {
		return fkProdCertId;
	}

	public void setFkProdCertId(MasterProductCertification fkProdCertId) {
		this.fkProdCertId = fkProdCertId;
	}

	public void setMasterBlendInputCertAssoc(MasterBlendTemplate masterBlendInputCertAssoc) {
		this.masterBlendInputCertAssoc = masterBlendInputCertAssoc;
	}

	
	
	
	
}

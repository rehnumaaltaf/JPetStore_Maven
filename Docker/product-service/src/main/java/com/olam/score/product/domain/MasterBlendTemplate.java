package com.olam.score.product.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

/**
*
* @author Manoj Kumar SP
* Added Manually
*/
@Entity
@Table(name = "Master_Blend_Template")
@XmlRootElement
@NamedQueries({
@NamedQuery(name = "MasterBlendTemplate.findAll", query = "SELECT m FROM MasterBlendTemplate m")})
public class MasterBlendTemplate implements Serializable {

	@Id
	@Basic(optional = false)
	@NotNull
	@Column(name = "Pk_Blend_Template_Id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="BLEND_TEMPLATE_ID_SEQ_GENERATOR")
   	@SequenceGenerator(name="BLEND_TEMPLATE_ID_SEQ_GENERATOR", sequenceName="BLEND_TEMPLATE_ID_SEQ",allocationSize=1)
	private Integer pkBlendTemplateId;
	@Column(name = "Template_Code")
	private String templateCode;
	@Column(name = "Template_Name")
	private String templateName;
	@Column(name = "Template_Desc")
	private String templateDesc;
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
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "masterBlendTemplateAssoc")
    private List<MasterBlendOutput> masterBlendOutputAssoc;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "masterBlendInputAssoc")
    private List<MasterBlendInput> masterBlendInputAssoc;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "masterBlendInputCertAssoc")
    private List<MasterBlendInputCertification> masterBlendInputCertificationAssoc;
	
	public MasterBlendTemplate(){
		
	}

	public Integer getPkBlendTemplateId() {
		return pkBlendTemplateId;
	}
	public void setPkBlendTemplateId(Integer pkBlendTemplateId) {
		this.pkBlendTemplateId = pkBlendTemplateId;
	}
	public String getTemplateCode() {
		return templateCode;
	}
	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}
	public String getTemplateName() {
		return templateName;
	}
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	public String getTemplateDesc() {
		return templateDesc;
	}
	public void setTemplateDesc(String templateDesc) {
		this.templateDesc = templateDesc;
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
	public List<MasterBlendOutput> getMasterBlendOutputAssoc() {
		return masterBlendOutputAssoc;
	}
	public void setMasterBlendOutputAssoc(List<MasterBlendOutput> masterBlendOutputAssoc) {
		this.masterBlendOutputAssoc = masterBlendOutputAssoc;
	}

	public List<MasterBlendInput> getMasterBlendInputAssoc() {
		return masterBlendInputAssoc;
	}

	public void setMasterBlendInputAssoc(List<MasterBlendInput> masterBlendInputAssoc) {
		this.masterBlendInputAssoc = masterBlendInputAssoc;
	}

	public List<MasterBlendInputCertification> getMasterBlendInputCertificationAssoc() {
		return masterBlendInputCertificationAssoc;
	}

	public void setMasterBlendInputCertificationAssoc(
			List<MasterBlendInputCertification> masterBlendInputCertificationAssoc) {
		this.masterBlendInputCertificationAssoc = masterBlendInputCertificationAssoc;
	}
	
	
	
	
	
}

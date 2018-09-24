/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 * @author Ramachandran_N02
 */
@Entity
@Table(name = "MASTER_BLEND_INPUT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterBlendInput.findAll", query = "SELECT m FROM MasterBlendInput m")})
public class MasterBlendInput implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_BLEND_INPUT_ID")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="BLEND_INPUT_ID_SEQ_GENERATOR")
   	@SequenceGenerator(name="BLEND_INPUT_ID_SEQ_GENERATOR", sequenceName="BLEND_INPUT_ID_SEQ",allocationSize=1)
    private Integer pkBlendInputId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "QUANTITY_PERCENTAGE")
    private Double quantityPercentage;
    @Column(name = "CERTIFICATION_PERCENTAGE")
    private Double certificationPercentage;
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
    @Column(name = "CUSTOM_ATTRIBUTE_6")
    private Double customAttribute6;
    @Column(name = "CUSTOM_ATTRIBUTE_7")
    private Double customAttribute7;
    @Column(name = "CUSTOM_ATTRIBUTE_8")
    private Double customAttribute8;
    @JoinColumn(name = "FK_GRADE_ID", referencedColumnName = "PK_GRADE_ID")
    @ManyToOne
    private MasterGrade fkGradeId;
    @JoinColumn(name = "FK_ORIGIN_ID", referencedColumnName = "PK_ORIGIN_ID")
    @ManyToOne
    private MasterOrigin fkOriginId;
    @JoinColumn(name = "FK_PROD_ID", referencedColumnName = "PK_PROD_ID")
    @ManyToOne
    private MasterProduct fkProdId;
    @JoinColumn(name = "FK_PROD_CERT_ID", referencedColumnName = "PK_PROD_CERT_ID")
    @ManyToOne
    private MasterProductCertification fkProdCertId;
    
    @JoinColumn(name = "FK_BLEND_TEMPLATE_ID")
	@ManyToOne
	private	MasterBlendTemplate masterBlendInputAssoc;

    public MasterBlendInput() {
    }

    public MasterBlendInput(Integer pkBlendInputId) {
        this.pkBlendInputId = pkBlendInputId;
    }

    public Integer getPkBlendInputId() {
        return pkBlendInputId;
    }

    public void setPkBlendInputId(Integer pkBlendInputId) {
        this.pkBlendInputId = pkBlendInputId;
    }

    public Double getQuantityPercentage() {
        return quantityPercentage;
    }

    public void setQuantityPercentage(Double quantityPercentage) {
        this.quantityPercentage = quantityPercentage;
    }

    public Double getCertificationPercentage() {
        return certificationPercentage;
    }

    public void setCertificationPercentage(Double certificationPercentage) {
        this.certificationPercentage = certificationPercentage;
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

    public MasterGrade getFkGradeId() {
        return fkGradeId;
    }

    public void setFkGradeId(MasterGrade fkGradeId) {
        this.fkGradeId = fkGradeId;
    }

    public MasterOrigin getFkOriginId() {
        return fkOriginId;
    }

    public void setFkOriginId(MasterOrigin fkOriginId) {
        this.fkOriginId = fkOriginId;
    }

    public MasterProduct getFkProdId() {
        return fkProdId;
    }

    public void setFkProdId(MasterProduct fkProdId) {
        this.fkProdId = fkProdId;
    }

    public MasterProductCertification getFkProdCertId() {
        return fkProdCertId;
    }

    public void setFkProdCertId(MasterProductCertification fkProdCertId) {
        this.fkProdCertId = fkProdCertId;
    }

    

	public MasterBlendTemplate getMasterBlendInputAssoc() {
		return masterBlendInputAssoc;
	}

	public void setMasterBlendInputAssoc(MasterBlendTemplate masterBlendInputAssoc) {
		this.masterBlendInputAssoc = masterBlendInputAssoc;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (pkBlendInputId != null ? pkBlendInputId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterBlendInput)) {
            return false;
        }
        MasterBlendInput other = (MasterBlendInput) object;
        if ((this.pkBlendInputId == null && other.pkBlendInputId != null) || (this.pkBlendInputId != null && !this.pkBlendInputId.equals(other.pkBlendInputId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.product.domain.MasterBlendInput[ pkBlendInputId=" + pkBlendInputId + " ]";
    }
    
}

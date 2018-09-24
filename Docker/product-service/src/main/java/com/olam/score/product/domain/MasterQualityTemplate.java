/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olam.score.product.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ramachandran_n02
 */
@Entity
@Table(name = "MASTER_QUALITY_TEMPLATE",  schema = "product")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterQualityTemplate.findAll", query = "SELECT m FROM MasterQualityTemplate m")})
public class MasterQualityTemplate implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_QLTY_TEMP_ID")
    private Integer pkQltyTempId;
    @Size(max = 200)
    @Column(name = "QLTY_TEMP_NAME")
    private String qltyTempName;
    @Size(max = 20)
    @Column(name = "QLTY_TEMP_CODE")
    private String qltyTempCode;
    @Size(max = 1000)
    @Column(name = "QLTY_TEMP_DESC")
    private String qltyTempDesc;
    @Size(max = 20)
    @Column(name = "QLTY_TEMP_DEFAULT_VALUE")
    private String qltyTempDefaultValue;
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
    @Size(max = 20)
    @Column(name = "QLTY_TEMP_PRODUCT")
    private String qltyTempProduct;
    @Size(max = 20)
    @Column(name = "QLTY_TEMP_ORIGIN")
    private String qltyTempOrigin;
    @OneToMany(mappedBy = "fkQltyTempId")
    private Collection<MasterGrade> masterGradeCollection;

    public MasterQualityTemplate() {
    }

    public MasterQualityTemplate(Integer pkQltyTempId) {
        this.pkQltyTempId = pkQltyTempId;
    }

    public Integer getPkQltyTempId() {
        return pkQltyTempId;
    }

    public void setPkQltyTempId(Integer pkQltyTempId) {
        this.pkQltyTempId = pkQltyTempId;
    }

    public String getQltyTempName() {
        return qltyTempName;
    }

    public void setQltyTempName(String qltyTempName) {
        this.qltyTempName = qltyTempName;
    }

    public String getQltyTempCode() {
        return qltyTempCode;
    }

    public void setQltyTempCode(String qltyTempCode) {
        this.qltyTempCode = qltyTempCode;
    }

    public String getQltyTempDesc() {
        return qltyTempDesc;
    }

    public void setQltyTempDesc(String qltyTempDesc) {
        this.qltyTempDesc = qltyTempDesc;
    }

    public String getQltyTempDefaultValue() {
        return qltyTempDefaultValue;
    }

    public void setQltyTempDefaultValue(String qltyTempDefaultValue) {
        this.qltyTempDefaultValue = qltyTempDefaultValue;
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

    public String getQltyTempProduct() {
        return qltyTempProduct;
    }

    public void setQltyTempProduct(String qltyTempProduct) {
        this.qltyTempProduct = qltyTempProduct;
    }

    public String getQltyTempOrigin() {
        return qltyTempOrigin;
    }

    public void setQltyTempOrigin(String qltyTempOrigin) {
        this.qltyTempOrigin = qltyTempOrigin;
    }

    @XmlTransient
    public Collection<MasterGrade> getMasterGradeCollection() {
        return masterGradeCollection;
    }

    public void setMasterGradeCollection(Collection<MasterGrade> masterGradeCollection) {
        this.masterGradeCollection = masterGradeCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkQltyTempId != null ? pkQltyTempId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterQualityTemplate)) {
            return false;
        }
        MasterQualityTemplate other = (MasterQualityTemplate) object;
        if ((this.pkQltyTempId == null && other.pkQltyTempId != null) || (this.pkQltyTempId != null && !this.pkQltyTempId.equals(other.pkQltyTempId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.product.domain.MasterQualityTemplate[ pkQltyTempId=" + pkQltyTempId + " ]";
    }
    
}

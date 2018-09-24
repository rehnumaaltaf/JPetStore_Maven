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
 * @author ramachandran_n02
 */
@Entity
@Table(name = "MASTER_BRAND")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterBrand.findAll", query = "SELECT m FROM MasterBrand m")})
public class MasterBrand implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="BRAND_ID_SEQ")
   	@SequenceGenerator(name="BRAND_ID_SEQ", sequenceName="product.BRAND_ID_SEQ",initialValue=1,allocationSize=1)
    @Column(name = "PK_BRAND_ID")
    private Integer pkBrandId;
    @Size(max = 20)
    @Column(name = "BRAND_CODE")
    private String brandCode;
    @Size(max = 200)
    @Column(name = "BRAND_NAME")
    private String brandName;
    @Size(max = 1000)
    @Column(name = "BRAND_FULLNAME")
    private String brandFullname;
    @Size(max = 100)
    @Column(name = "CREATED_BY")
    private String createdBy;
    @Column(name = "CREATED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Size(max = 100)
    @Column(name = "MODIFIED_BY")
    private String modifiedBy;
    @Column(name = "MODFIED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modfiedDate;
    @Size(max = 1000)
    @Column(name = "BRAND_LOGO_URL")
    private String brandLogoUrl;
    @Size(max = 1)
    @Column(name = "BRAND_IS_INTERNAL")
    private String brandIsInternal;
    @Column(name = "FK_STATUS_ID")
    private Integer fkStatusId;
    @Column(name="LOGO_BLOB")
    private byte[] logoBlob;
    

    public Integer getFkStatusId() {
		return fkStatusId;
	}

	public void setFkStatusId(Integer fkStatusId) {
		this.fkStatusId = fkStatusId;
	}

	public String getBrandIsInternal() {
		return brandIsInternal;
	}

	public void setBrandIsInternal(String brandIsInternal) {
		this.brandIsInternal = brandIsInternal;
	}

	public MasterBrand() {
    }

    public MasterBrand(Integer pkBrandId) {
        this.pkBrandId = pkBrandId;
    }

    public Integer getPkBrandId() {
        return pkBrandId;
    }

    public void setPkBrandId(Integer pkBrandId) {
        this.pkBrandId = pkBrandId;
    }

    public String getBrandCode() {
        return brandCode;
    }

    public void setBrandCode(String brandCode) {
        this.brandCode = brandCode;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBrandFullname() {
        return brandFullname;
    }

    public void setBrandFullname(String brandFullname) {
        this.brandFullname = brandFullname;
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

    public Date getModfiedDate() {
        return modfiedDate;
    }

    public void setModfiedDate(Date modfiedDate) {
        this.modfiedDate = modfiedDate;
    }

    public String getBrandLogoUrl() {
        return brandLogoUrl;
    }

    public void setBrandLogoUrl(String brandLogoUrl) {
        this.brandLogoUrl = brandLogoUrl;
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
        hash += (pkBrandId != null ? pkBrandId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterBrand)) {
            return false;
        }
        MasterBrand other = (MasterBrand) object;
        if ((this.pkBrandId == null && other.pkBrandId != null) || (this.pkBrandId != null && !this.pkBrandId.equals(other.pkBrandId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.product.domain.MasterBrand[ pkBrandId=" + pkBrandId + " ]";
    }
    
}

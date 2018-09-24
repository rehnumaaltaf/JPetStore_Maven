/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olam.score.finance.domain;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
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
 * @author ramachandran_n02
 */
@Entity
@Table(name = "MASTER_GL_EXTERNAL_MAPPING",  schema = "finance")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterGlExternalMapping.findAll", query = "SELECT m FROM MasterGlExternalMapping m")})
public class MasterGlExternalMapping implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name="MASTER_GL_EXTERNALMAPPINGIDSEQ_GENERATOR", sequenceName="GL_EXTERNAL_MAPPING_ID_SEQ",allocationSize=1, schema="finance")
   	@GeneratedValue(generator="MASTER_GL_EXTERNALMAPPINGIDSEQ_GENERATOR")
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_GL_EXTERNAL_MAPPING_ID")
    private Integer pkGlExternalMappingId;
    public Integer getPkGlExternalMappingId() {
		return pkGlExternalMappingId;
	}

	public void setPkGlExternalMappingId(Integer pkGlExternalMappingId) {
		this.pkGlExternalMappingId = pkGlExternalMappingId;
	}

	@Basic(optional = true)//(optional = false)
    /*@NotNull*/
    @Size(min = 1, max = 200)
    @Column(name = "EXTERNAL_GL_MAPPING_NAME")
    private String externalGlMappingName;
    @Basic(optional = true)//(optional = false)
    /*@NotNull*/
    /*@Size(min = 1, max = 20)*/
    @Size(max = 50)
    @Column(name = "EXTERNAL_CODE")
    private String externalGlMappingCode;
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
    @JoinColumn(name = "FK_EXTERNAL_SYSTEM_REF_ID", referencedColumnName = "PK_EXTERNAL_SYSTEM_REF_ID")
    @ManyToOne(optional = true)//(optional = false)
    private MasterExternalSystemRef fkExternalSystemRefId;
    @JoinColumn(name = "FK_GL_ID", referencedColumnName = "PK_GL_ID")
    @ManyToOne
    private MasterGl fkGlId;
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
    private BigInteger customAttribute6;
    @Column(name = "CUSTOM_ATTRIBUTE_7")
    private BigInteger customAttribute7;
    @Column(name = "CUSTOM_ATTRIBUTE_8")
    private BigInteger customAttribute8;
    
    @Size(max = 50)
    @Column(name = "MAPPING_TYPE")
    private String mappingType;

    public String getMappingType() {
		return mappingType;
	}

	public void setMappingType(String mappingType) {
		this.mappingType = mappingType;
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

	public BigInteger getCustomAttribute6() {
		return customAttribute6;
	}

	public void setCustomAttribute6(BigInteger customAttribute6) {
		this.customAttribute6 = customAttribute6;
	}

	public BigInteger getCustomAttribute7() {
		return customAttribute7;
	}

	public void setCustomAttribute7(BigInteger customAttribute7) {
		this.customAttribute7 = customAttribute7;
	}

	public BigInteger getCustomAttribute8() {
		return customAttribute8;
	}

	public void setCustomAttribute8(BigInteger customAttribute8) {
		this.customAttribute8 = customAttribute8;
	}

	public MasterGlExternalMapping() {
    }

    public MasterGlExternalMapping(Integer pkGlExternalMappingId) {
        this.pkGlExternalMappingId = pkGlExternalMappingId;
    }

    public MasterGlExternalMapping(Integer pkGlExternalMappingId, String externalGlMappingName, String externalGlMappingCode) {
        this.pkGlExternalMappingId = pkGlExternalMappingId;
        this.externalGlMappingName = externalGlMappingName;
        this.externalGlMappingCode = externalGlMappingCode;
    }

    public Integer getPkExternalGlMappingId() {
        return pkGlExternalMappingId;
    }

    public void setPkExternalGlMappingId(Integer pkGlExternalMappingId) {
        this.pkGlExternalMappingId = pkGlExternalMappingId;
    }

    public String getExternalGlMappingName() {
        return externalGlMappingName;
    }

    public void setExternalGlMappingName(String externalGlMappingName) {
        this.externalGlMappingName = externalGlMappingName;
    }

    public String getExternalGlMappingCode() {
        return externalGlMappingCode;
    }

    public void setExternalGlMappingCode(String externalGlMappingCode) {
        this.externalGlMappingCode = externalGlMappingCode;
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

    public MasterExternalSystemRef getFkExternalSystemRefId() {
        return fkExternalSystemRefId;
    }

    public void setFkExternalSystemRefId(MasterExternalSystemRef fkExternalSystemRefId) {
        this.fkExternalSystemRefId = fkExternalSystemRefId;
    }

    public MasterGl getFkGlId() {
        return fkGlId;
    }

    public void setFkGlId(MasterGl fkGlId) {
        this.fkGlId = fkGlId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkGlExternalMappingId != null ? pkGlExternalMappingId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterGlExternalMapping)) {
            return false;
        }
        MasterGlExternalMapping other = (MasterGlExternalMapping) object;
        if ((this.pkGlExternalMappingId == null && other.pkGlExternalMappingId != null) || (this.pkGlExternalMappingId != null && !this.pkGlExternalMappingId.equals(other.pkGlExternalMappingId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.finance.domain.MasterGlExternalMapping[ pkGlExternalMappingId=" + pkGlExternalMappingId + " ]";
    }
    
}

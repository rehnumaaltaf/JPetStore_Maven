/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olam.score.finance.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
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
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ramachandran_n02
 */
@Entity
@Table(name = "MASTER_EXTERNAL_SYSTEM_REF",  schema = "finance")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterExternalSystemRef.findAll", query = "SELECT m FROM MasterExternalSystemRef m")})
public class MasterExternalSystemRef implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name="MASTER_GL_EXTERNALSYSTEMREFIDSEQ_GENERATOR", sequenceName="EXTERNAL_SYSTEM_REF_ID_SEQ",allocationSize=1, schema="finance")
	@GeneratedValue(generator="MASTER_GL_EXTERNALSYSTEMREFIDSEQ_GENERATOR")
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_EXTERNAL_SYSTEM_REF_ID")
    private Integer pkExternalSystemRefId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "EXTERNAL_SYSTEM_REF_NAME")
    private String externalSystemRefName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "EXTERNAL_SYSTEM_REF_CODE")
    private String externalSystemRefCode;
    @Size(max = 1000)
    @Column(name = "EXTERNAL_SYSTEM_REF_DESC")
    private String externalSystemRefDesc;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkExternalSystemRefId")
    private Collection<MasterGlExternalMapping> masterExternalGlMappingCollection;

    public MasterExternalSystemRef() {
    }

    public MasterExternalSystemRef(Integer pkExternalSystemRefId) {
        this.pkExternalSystemRefId = pkExternalSystemRefId;
    }

    public MasterExternalSystemRef(Integer pkExternalSystemRefId, String externalSystemRefName, String externalSystemRefCode) {
        this.pkExternalSystemRefId = pkExternalSystemRefId;
        this.externalSystemRefName = externalSystemRefName;
        this.externalSystemRefCode = externalSystemRefCode;
    }

    public Integer getPkExternalSystemRefId() {
        return pkExternalSystemRefId;
    }

    public void setPkExternalSystemRefId(Integer pkExternalSystemRefId) {
        this.pkExternalSystemRefId = pkExternalSystemRefId;
    }

    public String getExternalSystemRefName() {
        return externalSystemRefName;
    }

    public void setExternalSystemRefName(String externalSystemRefName) {
        this.externalSystemRefName = externalSystemRefName;
    }

    public String getExternalSystemRefCode() {
        return externalSystemRefCode;
    }

    public void setExternalSystemRefCode(String externalSystemRefCode) {
        this.externalSystemRefCode = externalSystemRefCode;
    }

    public String getExternalSystemRefDesc() {
        return externalSystemRefDesc;
    }

    public void setExternalSystemRefDesc(String externalSystemRefDesc) {
        this.externalSystemRefDesc = externalSystemRefDesc;
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

    @XmlTransient
    public Collection<MasterGlExternalMapping> getMasterExternalGlMappingCollection() {
        return masterExternalGlMappingCollection;
    }

    public void setMasterExternalGlMappingCollection(Collection<MasterGlExternalMapping> masterExternalGlMappingCollection) {
        this.masterExternalGlMappingCollection = masterExternalGlMappingCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkExternalSystemRefId != null ? pkExternalSystemRefId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterExternalSystemRef)) {
            return false;
        }
        MasterExternalSystemRef other = (MasterExternalSystemRef) object;
        if ((this.pkExternalSystemRefId == null && other.pkExternalSystemRefId != null) || (this.pkExternalSystemRefId != null && !this.pkExternalSystemRefId.equals(other.pkExternalSystemRefId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.finance.domain.MasterExternalSystemRef[ pkExternalSystemRefId=" + pkExternalSystemRefId + " ]";
    }
    
}

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
@Table(name = "MASTER_GL_TYPE_REF",  schema = "finance")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterGlTypeRef.findAll", query = "SELECT m FROM MasterGlTypeRef m")})
public class MasterGlTypeRef implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name="MASTER_GL_GLTYPEIDSEQ_GENERATOR", sequenceName="GL_TYPE_REF_ID_SEQ",allocationSize=1, schema="finance")
	@GeneratedValue(generator="MASTER_GL_GLTYPEIDSEQ_GENERATOR")
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_GL_TYPE_REF_ID")
    private Integer pkGlTypeRefId;
    @Size(max = 20)
    @Column(name = "GL_TYPE_REF_CD")
    private String glTypeRefCd;
    @Size(max = 200)
    @Column(name = "GL_TYPE_REF_NAME")
    private String glTypeRefName;
    @Size(max = 1000)
    @Column(name = "GL_TYPE_REF_DESC")
    private String glTypeRefDesc;
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
    @OneToMany(mappedBy = "fkGlTypeRefId")
    private Collection<MasterGl> masterGlCollection;

    public MasterGlTypeRef() {
    }

    public MasterGlTypeRef(Integer pkGlTypeRefId) {
        this.pkGlTypeRefId = pkGlTypeRefId;
    }

    public Integer getPkGlTypeRefId() {
        return pkGlTypeRefId;
    }

    public void setPkGlTypeRefId(Integer pkGlTypeRefId) {
        this.pkGlTypeRefId = pkGlTypeRefId;
    }

    public String getGlTypeRefCd() {
        return glTypeRefCd;
    }

    public void setGlTypeRefCd(String glTypeRefCd) {
        this.glTypeRefCd = glTypeRefCd;
    }

    public String getGlTypeRefName() {
        return glTypeRefName;
    }

    public void setGlTypeRefName(String glTypeRefName) {
        this.glTypeRefName = glTypeRefName;
    }

    public String getGlTypeRefDesc() {
        return glTypeRefDesc;
    }

    public void setGlTypeRefDesc(String glTypeRefDesc) {
        this.glTypeRefDesc = glTypeRefDesc;
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

    @XmlTransient
    public Collection<MasterGl> getMasterGlCollection() {
        return masterGlCollection;
    }

    public void setMasterGlCollection(Collection<MasterGl> masterGlCollection) {
        this.masterGlCollection = masterGlCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkGlTypeRefId != null ? pkGlTypeRefId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterGlTypeRef)) {
            return false;
        }
        MasterGlTypeRef other = (MasterGlTypeRef) object;
        if ((this.pkGlTypeRefId == null && other.pkGlTypeRefId != null) || (this.pkGlTypeRefId != null && !this.pkGlTypeRefId.equals(other.pkGlTypeRefId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.finance.domain.MasterGlTypeRef[ pkGlTypeRefId=" + pkGlTypeRefId + " ]";
    }
    
}

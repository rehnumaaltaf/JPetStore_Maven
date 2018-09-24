/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olam.score.authorizationconfig.domain;

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

/**
 *
 * @author Ramachandran_N02
 */
@Entity
@Table(name = "AUTH_MODULE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AuthModule.findAll", query = "SELECT a FROM AuthModule a")
    , @NamedQuery(name = "AuthModule.findByPkModuleId", query = "SELECT a FROM AuthModule a WHERE a.pkModuleId = :pkModuleId")
    , @NamedQuery(name = "AuthModule.findByModuleCode", query = "SELECT a FROM AuthModule a WHERE a.moduleCode = :moduleCode")
    , @NamedQuery(name = "AuthModule.findByModuleName", query = "SELECT a FROM AuthModule a WHERE a.moduleName = :moduleName")
    , @NamedQuery(name = "AuthModule.findByModuleDesc", query = "SELECT a FROM AuthModule a WHERE a.moduleDesc = :moduleDesc")
    , @NamedQuery(name = "AuthModule.findByUrl", query = "SELECT a FROM AuthModule a WHERE a.url = :url")
    , @NamedQuery(name = "AuthModule.findByFkStatusId", query = "SELECT a FROM AuthModule a WHERE a.fkStatusId = :fkStatusId")
    , @NamedQuery(name = "AuthModule.findByCreatedBy", query = "SELECT a FROM AuthModule a WHERE a.createdBy = :createdBy")
    , @NamedQuery(name = "AuthModule.findByCreatedDate", query = "SELECT a FROM AuthModule a WHERE a.createdDate = :createdDate")
    , @NamedQuery(name = "AuthModule.findByModifiedBy", query = "SELECT a FROM AuthModule a WHERE a.modifiedBy = :modifiedBy")
    , @NamedQuery(name = "AuthModule.findByModifiedDate", query = "SELECT a FROM AuthModule a WHERE a.modifiedDate = :modifiedDate")})

public class AuthModule implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_MODULE_ID")
    private Integer pkModuleId;
    @Size(max = 20)
    @Column(name = "MODULE_CODE")
    private String moduleCode;
    @Size(max = 200)
    @Column(name = "MODULE_NAME")
    private String moduleName;
    @Size(max = 1000)
    @Column(name = "MODULE_DESC")
    private String moduleDesc;
    @Size(max = 500)
    @Column(name = "MODULE_URL")
    private String moduleUrl;
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
    @OneToMany(mappedBy = "fkModuleId")
    private Collection<AuthEntity> authEntityCollection;
    @OneToMany(mappedBy = "fkModuleId")
    private Collection<AuthFeature> authFeatureCollection;

    public AuthModule() {
    }

    public AuthModule(Integer pkModuleId) {
        this.pkModuleId = pkModuleId;
    }

    public Integer getPkModuleId() {
        return pkModuleId;
    }

    public void setPkModuleId(Integer pkModuleId) {
        this.pkModuleId = pkModuleId;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getModuleDesc() {
        return moduleDesc;
    }

    public void setModuleDesc(String moduleDesc) {
        this.moduleDesc = moduleDesc;
    }

    public String getModuleUrl() {
        return moduleUrl;
    }

    public void setModuleUrl(String moduleUrl) {
        this.moduleUrl = moduleUrl;
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

    public Collection<AuthEntity> getAuthEntityCollection() {
        return authEntityCollection;
    }

    public void setAuthEntityCollection(Collection<AuthEntity> authEntityCollection) {
        this.authEntityCollection = authEntityCollection;
    }

    public Collection<AuthFeature> getAuthFeatureCollection() {
        return authFeatureCollection;
    }

    public void setAuthFeatureCollection(Collection<AuthFeature> authFeatureCollection) {
        this.authFeatureCollection = authFeatureCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkModuleId != null ? pkModuleId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AuthModule)) {
            return false;
        }
        AuthModule other = (AuthModule) object;
        if ((this.pkModuleId == null && other.pkModuleId != null) || (this.pkModuleId != null && !this.pkModuleId.equals(other.pkModuleId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.authconfig.domain.AuthModule[ pkModuleId=" + pkModuleId + " ]";
    }
    
}

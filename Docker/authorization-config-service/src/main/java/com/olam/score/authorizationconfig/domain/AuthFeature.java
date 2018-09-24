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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "AUTH_FEATURE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AuthFeature.findAll", query = "SELECT a FROM AuthFeature a")
    , @NamedQuery(name = "AuthFeature.findByPkFeatureId", query = "SELECT a FROM AuthFeature a WHERE a.pkFeatureId = :pkFeatureId")
    , @NamedQuery(name = "AuthFeature.findByFeatureName", query = "SELECT a FROM AuthFeature a WHERE a.featureName = :featureName")
    , @NamedQuery(name = "AuthFeature.findByFeatureDesc", query = "SELECT a FROM AuthFeature a WHERE a.featureDesc = :featureDesc")
    , @NamedQuery(name = "AuthFeature.findByFkStatusId", query = "SELECT a FROM AuthFeature a WHERE a.fkStatusId = :fkStatusId")
    , @NamedQuery(name = "AuthFeature.findByCreatedBy", query = "SELECT a FROM AuthFeature a WHERE a.createdBy = :createdBy")
    , @NamedQuery(name = "AuthFeature.findByCreatedDate", query = "SELECT a FROM AuthFeature a WHERE a.createdDate = :createdDate")
    , @NamedQuery(name = "AuthFeature.findByModifiedBy", query = "SELECT a FROM AuthFeature a WHERE a.modifiedBy = :modifiedBy")
    , @NamedQuery(name = "AuthFeature.findByModifiedDate", query = "SELECT a FROM AuthFeature a WHERE a.modifiedDate = :modifiedDate")})

public class AuthFeature implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_FEATURE_ID")
    private Integer pkFeatureId;
    @Size(max = 200)
    @Column(name = "FEATURE_NAME")
    private String featureName;
    @Size(max = 1000)
    @Column(name = "FEATURE_DESC")
    private String featureDesc;
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
    @OneToMany(mappedBy = "fkFeatureId")
    private Collection<AuthRoleFeatureMapping> authRoleFeatureMappingCollection;
    @OneToMany(mappedBy = "fkFeatureId")
    private Collection<AuthFeatureAttributeMapping> authFeatureAttributeMappingCollection;
    @JoinColumn(name = "FK_ENTITY_ID", referencedColumnName = "PK_ENTITY_ID")
    @ManyToOne
    private AuthEntity fkEntityId;
    @JoinColumn(name = "FK_MODULE_ID", referencedColumnName = "PK_MODULE_ID")
    @ManyToOne
    private AuthModule fkModuleId;
    @JoinColumn(name = "FK_PERMISSION_LEVEL_ID", referencedColumnName = "PK_PERMISSION_LEVEL_ID")
    @ManyToOne
    private AuthPermissionLevel fkPermissionLevelId;

    public AuthFeature() {
    }

    public AuthFeature(Integer pkFeatureId) {
        this.pkFeatureId = pkFeatureId;
    }

    public Integer getPkFeatureId() {
        return pkFeatureId;
    }

    public void setPkFeatureId(Integer pkFeatureId) {
        this.pkFeatureId = pkFeatureId;
    }

    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    public String getFeatureDesc() {
        return featureDesc;
    }

    public void setFeatureDesc(String featureDesc) {
        this.featureDesc = featureDesc;
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

    public Collection<AuthRoleFeatureMapping> getAuthRoleFeatureMappingCollection() {
        return authRoleFeatureMappingCollection;
    }

    public void setAuthRoleFeatureMappingCollection(Collection<AuthRoleFeatureMapping> authRoleFeatureMappingCollection) {
        this.authRoleFeatureMappingCollection = authRoleFeatureMappingCollection;
    }

    public Collection<AuthFeatureAttributeMapping> getAuthFeatureAttributeMappingCollection() {
        return authFeatureAttributeMappingCollection;
    }

    public void setAuthFeatureAttributeMappingCollection(Collection<AuthFeatureAttributeMapping> authFeatureAttributeMappingCollection) {
        this.authFeatureAttributeMappingCollection = authFeatureAttributeMappingCollection;
    }

    public AuthEntity getFkEntityId() {
        return fkEntityId;
    }

    public void setFkEntityId(AuthEntity fkEntityId) {
        this.fkEntityId = fkEntityId;
    }

    public AuthModule getFkModuleId() {
        return fkModuleId;
    }

    public void setFkModuleId(AuthModule fkModuleId) {
        this.fkModuleId = fkModuleId;
    }

    public AuthPermissionLevel getFkPermissionLevelId() {
        return fkPermissionLevelId;
    }

    public void setFkPermissionLevelId(AuthPermissionLevel fkPermissionLevelId) {
        this.fkPermissionLevelId = fkPermissionLevelId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkFeatureId != null ? pkFeatureId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AuthFeature)) {
            return false;
        }
        AuthFeature other = (AuthFeature) object;
        if ((this.pkFeatureId == null && other.pkFeatureId != null) || (this.pkFeatureId != null && !this.pkFeatureId.equals(other.pkFeatureId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.authconfig.domain.AuthFeature[ pkFeatureId=" + pkFeatureId + " ]";
    }
    
}

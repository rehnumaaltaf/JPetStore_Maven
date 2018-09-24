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

/**
 *
 * @author Ramachandran_N02
 */
@Entity
@Table(name = "AUTH_PERMISSION_LEVEL")

@NamedQueries({
    @NamedQuery(name = "AuthPermissionLevel.findAll", query = "SELECT a FROM AuthPermissionLevel a")
    , @NamedQuery(name = "AuthPermissionLevel.findByPkPermissionLevelId", query = "SELECT a FROM AuthPermissionLevel a WHERE a.pkPermissionLevelId = :pkPermissionLevelId")
    , @NamedQuery(name = "AuthPermissionLevel.findByPermissionLevelCode", query = "SELECT a FROM AuthPermissionLevel a WHERE a.permissionLevelCode = :permissionLevelCode")
    , @NamedQuery(name = "AuthPermissionLevel.findByPermissionLevelName", query = "SELECT a FROM AuthPermissionLevel a WHERE a.permissionLevelName = :permissionLevelName")
    , @NamedQuery(name = "AuthPermissionLevel.findByPermissionLevelDesc", query = "SELECT a FROM AuthPermissionLevel a WHERE a.permissionLevelDesc = :permissionLevelDesc")
    , @NamedQuery(name = "AuthPermissionLevel.findByFkStatusId", query = "SELECT a FROM AuthPermissionLevel a WHERE a.fkStatusId = :fkStatusId")
    , @NamedQuery(name = "AuthPermissionLevel.findByCreatedBy", query = "SELECT a FROM AuthPermissionLevel a WHERE a.createdBy = :createdBy")
    , @NamedQuery(name = "AuthPermissionLevel.findByCreatedDate", query = "SELECT a FROM AuthPermissionLevel a WHERE a.createdDate = :createdDate")
    , @NamedQuery(name = "AuthPermissionLevel.findByModifiedBy", query = "SELECT a FROM AuthPermissionLevel a WHERE a.modifiedBy = :modifiedBy")
    , @NamedQuery(name = "AuthPermissionLevel.findByModifiedDate", query = "SELECT a FROM AuthPermissionLevel a WHERE a.modifiedDate = :modifiedDate")})

public class AuthPermissionLevel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_PERMISSION_LEVEL_ID")
    private Integer pkPermissionLevelId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "PERMISSION_LEVEL_CODE")
    private String permissionLevelCode;
    @Size(max = 200)
    @Column(name = "PERMISSION_LEVEL_NAME")
    private String permissionLevelName;
    @Size(max = 1000)
    @Column(name = "PERMISSION_LEVEL_DESC")
    private String permissionLevelDesc;
    @Size(max = 50)
    @Column(name = "PERMISSION_LEVEL_METHOD")
    private String permissionLevelMethod;
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
    @OneToMany(mappedBy = "fkPermissionLevelId")
    private Collection<AuthPermissionLevelGroup> authPermissionLevelGroupCollection;
    @OneToMany(mappedBy = "fkInheritedPermissionLevelId")
    private Collection<AuthPermissionLevelGroup> authPermissionLevelGroupCollection1;
    @OneToMany(mappedBy = "fkPermissionLevelId")
    private Collection<AuthFeature> authFeatureCollection;

    public AuthPermissionLevel() {
    }

    public AuthPermissionLevel(Integer pkPermissionLevelId) {
        this.pkPermissionLevelId = pkPermissionLevelId;
    }

    public AuthPermissionLevel(Integer pkPermissionLevelId, String permissionLevelCode) {
        this.pkPermissionLevelId = pkPermissionLevelId;
        this.permissionLevelCode = permissionLevelCode;
    }

    public Integer getPkPermissionLevelId() {
        return pkPermissionLevelId;
    }

    public void setPkPermissionLevelId(Integer pkPermissionLevelId) {
        this.pkPermissionLevelId = pkPermissionLevelId;
    }

    public String getPermissionLevelCode() {
        return permissionLevelCode;
    }

    public void setPermissionLevelCode(String permissionLevelCode) {
        this.permissionLevelCode = permissionLevelCode;
    }

    public String getPermissionLevelName() {
        return permissionLevelName;
    }

    public void setPermissionLevelName(String permissionLevelName) {
        this.permissionLevelName = permissionLevelName;
    }

    public String getPermissionLevelDesc() {
        return permissionLevelDesc;
    }

    public void setPermissionLevelDesc(String permissionLevelDesc) {
        this.permissionLevelDesc = permissionLevelDesc;
    }

    public String getPermissionLevelMethod() {
        return permissionLevelMethod;
    }

    public void setPermissionLevelMethod(String permissionLevelMethod) {
        this.permissionLevelMethod = permissionLevelMethod;
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

    public Collection<AuthPermissionLevelGroup> getAuthPermissionLevelGroupCollection() {
        return authPermissionLevelGroupCollection;
    }

    public void setAuthPermissionLevelGroupCollection(Collection<AuthPermissionLevelGroup> authPermissionLevelGroupCollection) {
        this.authPermissionLevelGroupCollection = authPermissionLevelGroupCollection;
    }

    public Collection<AuthPermissionLevelGroup> getAuthPermissionLevelGroupCollection1() {
        return authPermissionLevelGroupCollection1;
    }

    public void setAuthPermissionLevelGroupCollection1(Collection<AuthPermissionLevelGroup> authPermissionLevelGroupCollection1) {
        this.authPermissionLevelGroupCollection1 = authPermissionLevelGroupCollection1;
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
        hash += (pkPermissionLevelId != null ? pkPermissionLevelId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AuthPermissionLevel)) {
            return false;
        }
        AuthPermissionLevel other = (AuthPermissionLevel) object;
        if ((this.pkPermissionLevelId == null && other.pkPermissionLevelId != null) || (this.pkPermissionLevelId != null && !this.pkPermissionLevelId.equals(other.pkPermissionLevelId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.authconfig.domain.AuthPermissionLevel[ pkPermissionLevelId=" + pkPermissionLevelId + " ]";
    }
    
}

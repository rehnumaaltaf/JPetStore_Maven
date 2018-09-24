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
 * @author Ramachandran_N02
 */
@Entity
@Table(name = "AUTH_ROLE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AuthRole.findAll", query = "SELECT a FROM AuthRole a")
    , @NamedQuery(name = "AuthRole.findByPkRoleId", query = "SELECT a FROM AuthRole a WHERE a.pkRoleId = :pkRoleId")
    , @NamedQuery(name = "AuthRole.findByRoleName", query = "SELECT a FROM AuthRole a WHERE a.roleName = :roleName")
    , @NamedQuery(name = "AuthRole.findByRoleDesc", query = "SELECT a FROM AuthRole a WHERE a.roleDesc = :roleDesc")
    , @NamedQuery(name = "AuthRole.findByFkStatusId", query = "SELECT a FROM AuthRole a WHERE a.fkStatusId = :fkStatusId")
    , @NamedQuery(name = "AuthRole.findByCreatedBy", query = "SELECT a FROM AuthRole a WHERE a.createdBy = :createdBy")
    , @NamedQuery(name = "AuthRole.findByCreatedDate", query = "SELECT a FROM AuthRole a WHERE a.createdDate = :createdDate")
    , @NamedQuery(name = "AuthRole.findByModifiedBy", query = "SELECT a FROM AuthRole a WHERE a.modifiedBy = :modifiedBy")
    , @NamedQuery(name = "AuthRole.findByModifiedDate", query = "SELECT a FROM AuthRole a WHERE a.modifiedDate = :modifiedDate")})

public class AuthRole implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="AUTH_ROLE_UOMIDSEQ_GENERATOR")
   	@SequenceGenerator(name="AUTH_ROLE_UOMIDSEQ_GENERATOR", sequenceName="auth.MODULE_ID_SEQ",allocationSize=1)
    @Column(name = "PK_ROLE_ID")
    private Integer pkRoleId;
    @Size(max = 200)
    @Column(name = "ROLE_NAME")
    private String roleName;
    @Size(max = 1000)
    @Column(name = "ROLE_DESC")
    private String roleDesc;
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
    @OneToMany(mappedBy = "fkRoleId")
    private Collection<AuthPermissionGroupRoleMapping> authPermissionGroupRoleMappingCollection;
    @OneToMany(mappedBy = "fkRoleId")
    private Collection<AuthUserRoleMapping> authUserRoleMappingCollection;
    @OneToMany(mappedBy = "fkRoleId")
    private Collection<AuthRoleFeatureMapping> authRoleFeatureMappingCollection;

    public AuthRole() {
    }

    public AuthRole(Integer pkRoleId) {
        this.pkRoleId = pkRoleId;
    }

    public Integer getPkRoleId() {
        return pkRoleId;
    }

    public void setPkRoleId(Integer pkRoleId) {
        this.pkRoleId = pkRoleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
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

    public Collection<AuthPermissionGroupRoleMapping> getAuthPermissionGroupRoleMappingCollection() {
        return authPermissionGroupRoleMappingCollection;
    }

    public void setAuthPermissionGroupRoleMappingCollection(Collection<AuthPermissionGroupRoleMapping> authPermissionGroupRoleMappingCollection) {
        this.authPermissionGroupRoleMappingCollection = authPermissionGroupRoleMappingCollection;
    }

    public Collection<AuthUserRoleMapping> getAuthUserRoleMappingCollection() {
        return authUserRoleMappingCollection;
    }

    public void setAuthUserRoleMappingCollection(Collection<AuthUserRoleMapping> authUserRoleMappingCollection) {
        this.authUserRoleMappingCollection = authUserRoleMappingCollection;
    }

    public Collection<AuthRoleFeatureMapping> getAuthRoleFeatureMappingCollection() {
        return authRoleFeatureMappingCollection;
    }

    public void setAuthRoleFeatureMappingCollection(Collection<AuthRoleFeatureMapping> authRoleFeatureMappingCollection) {
        this.authRoleFeatureMappingCollection = authRoleFeatureMappingCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkRoleId != null ? pkRoleId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AuthRole)) {
            return false;
        }
        AuthRole other = (AuthRole) object;
        if ((this.pkRoleId == null && other.pkRoleId != null) || (this.pkRoleId != null && !this.pkRoleId.equals(other.pkRoleId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.authconfig.domain.AuthRole[ pkRoleId=" + pkRoleId + " ]";
    }
    
}

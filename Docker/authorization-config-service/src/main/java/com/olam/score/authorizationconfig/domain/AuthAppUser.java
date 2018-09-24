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
@Table(name = "AUTH_APP_USER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AuthAppUser.findAll", query = "SELECT a FROM AuthAppUser a")})
public class AuthAppUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_APP_USER_ID")
    private Integer pkAppUserId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "APP_USER_CODE")
    private String appUserCode;
    @Size(max = 200)
    @Column(name = "APP_USER_NAME")
    private String appUserName;
    @Size(max = 1000)
    @Column(name = "APP_USER_DESC")
    private String appUserDesc;
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
    @OneToMany(mappedBy = "fkAppUserId")
    private Collection<AuthUserOrganizationRole> authUserOrganizationRoleCollection;
    @OneToMany(mappedBy = "fkAppUserId")
    private Collection<AuthTrader> authTraderCollection;
    @OneToMany(mappedBy = "fkAppUserId")
    private Collection<AuthUserRoleMapping> authUserRoleMappingCollection;
    @OneToMany(mappedBy = "fkAppUserId")
    private Collection<AuthUserPermissionGroupMapping> authUserPermissionGroupMappingCollection;
    @JoinColumn(name = "FK_ORGANIZATION_ID", referencedColumnName = "PK_ORGANIZATION_ID")
    @ManyToOne
    private AuthOrganization fkOrganizationId;

    public AuthAppUser() {
    }

    public AuthAppUser(Integer pkAppUserId) {
        this.pkAppUserId = pkAppUserId;
    }

    public AuthAppUser(Integer pkAppUserId, String appUserCode) {
        this.pkAppUserId = pkAppUserId;
        this.appUserCode = appUserCode;
    }

    public Integer getPkAppUserId() {
        return pkAppUserId;
    }

    public void setPkAppUserId(Integer pkAppUserId) {
        this.pkAppUserId = pkAppUserId;
    }

    public String getAppUserCode() {
        return appUserCode;
    }

    public void setAppUserCode(String appUserCode) {
        this.appUserCode = appUserCode;
    }

    public String getAppUserName() {
        return appUserName;
    }

    public void setAppUserName(String appUserName) {
        this.appUserName = appUserName;
    }

    public String getAppUserDesc() {
        return appUserDesc;
    }

    public void setAppUserDesc(String appUserDesc) {
        this.appUserDesc = appUserDesc;
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

    public Collection<AuthUserOrganizationRole> getAuthUserOrganizationRoleCollection() {
        return authUserOrganizationRoleCollection;
    }

    public void setAuthUserOrganizationRoleCollection(Collection<AuthUserOrganizationRole> authUserOrganizationRoleCollection) {
        this.authUserOrganizationRoleCollection = authUserOrganizationRoleCollection;
    }

    public Collection<AuthTrader> getAuthTraderCollection() {
        return authTraderCollection;
    }

    public void setAuthTraderCollection(Collection<AuthTrader> authTraderCollection) {
        this.authTraderCollection = authTraderCollection;
    }

    public Collection<AuthUserRoleMapping> getAuthUserRoleMappingCollection() {
        return authUserRoleMappingCollection;
    }

    public void setAuthUserRoleMappingCollection(Collection<AuthUserRoleMapping> authUserRoleMappingCollection) {
        this.authUserRoleMappingCollection = authUserRoleMappingCollection;
    }

    public Collection<AuthUserPermissionGroupMapping> getAuthUserPermissionGroupMappingCollection() {
        return authUserPermissionGroupMappingCollection;
    }

    public void setAuthUserPermissionGroupMappingCollection(Collection<AuthUserPermissionGroupMapping> authUserPermissionGroupMappingCollection) {
        this.authUserPermissionGroupMappingCollection = authUserPermissionGroupMappingCollection;
    }

    public AuthOrganization getFkOrganizationId() {
        return fkOrganizationId;
    }

    public void setFkOrganizationId(AuthOrganization fkOrganizationId) {
        this.fkOrganizationId = fkOrganizationId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkAppUserId != null ? pkAppUserId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AuthAppUser)) {
            return false;
        }
        AuthAppUser other = (AuthAppUser) object;
        if ((this.pkAppUserId == null && other.pkAppUserId != null) || (this.pkAppUserId != null && !this.pkAppUserId.equals(other.pkAppUserId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.authconfig.domain.AuthAppUser[ pkAppUserId=" + pkAppUserId + " ]";
    }
    
}

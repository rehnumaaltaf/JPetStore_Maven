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
@Table(name = "AUTH_PERMISSION_GROUP")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AuthPermissionGroup.findAll", query = "SELECT a FROM AuthPermissionGroup a")
    , @NamedQuery(name = "AuthPermissionGroup.findByPkPermissionGroupId", query = "SELECT a FROM AuthPermissionGroup a WHERE a.pkPermissionGroupId = :pkPermissionGroupId")
    , @NamedQuery(name = "AuthPermissionGroup.findByPermissionGroupCode", query = "SELECT a FROM AuthPermissionGroup a WHERE a.permissionGroupCode = :permissionGroupCode")
    , @NamedQuery(name = "AuthPermissionGroup.findByPermissionGroupName", query = "SELECT a FROM AuthPermissionGroup a WHERE a.permissionGroupName = :permissionGroupName")
    , @NamedQuery(name = "AuthPermissionGroup.findByPermissionGroupDesc", query = "SELECT a FROM AuthPermissionGroup a WHERE a.permissionGroupDesc = :permissionGroupDesc")
    , @NamedQuery(name = "AuthPermissionGroup.findByFkStatusId", query = "SELECT a FROM AuthPermissionGroup a WHERE a.fkStatusId = :fkStatusId")
    , @NamedQuery(name = "AuthPermissionGroup.findByCreatedBy", query = "SELECT a FROM AuthPermissionGroup a WHERE a.createdBy = :createdBy")
    , @NamedQuery(name = "AuthPermissionGroup.findByCreatedDate", query = "SELECT a FROM AuthPermissionGroup a WHERE a.createdDate = :createdDate")
    , @NamedQuery(name = "AuthPermissionGroup.findByModifiedBy", query = "SELECT a FROM AuthPermissionGroup a WHERE a.modifiedBy = :modifiedBy")
    , @NamedQuery(name = "AuthPermissionGroup.findByModifiedDate", query = "SELECT a FROM AuthPermissionGroup a WHERE a.modifiedDate = :modifiedDate")})

public class AuthPermissionGroup implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="AUTH_PERMISSION_UOMIDSEQ_GENERATOR")
   	@SequenceGenerator(name="AUTH_PERMISSION_UOMIDSEQ_GENERATOR", sequenceName="auth.PERMISSION_GROUP_ID_SEQ",allocationSize=1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_PERMISSION_GROUP_ID")
    private Integer pkPermissionGroupId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "PERMISSION_GROUP_CODE")
    private String permissionGroupCode;
    @Size(max = 200)
    @Column(name = "PERMISSION_GROUP_NAME")
    private String permissionGroupName;
    @Size(max = 1000)
    @Column(name = "PERMISSION_GROUP_DESC")
    private String permissionGroupDesc;
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
    @OneToMany(mappedBy = "fkPermissionGroupId")
    private Collection<AuthPermissionGroupRoleMapping> authPermissionGroupRoleMappingCollection;
    @OneToMany(mappedBy = "fkPermissionGroupId")
    private Collection<AuthUserPermissionGroupMapping> authUserPermissionGroupMappingCollection;

    public AuthPermissionGroup() {
    }

    public AuthPermissionGroup(Integer pkPermissionGroupId) {
        this.pkPermissionGroupId = pkPermissionGroupId;
    }

    public AuthPermissionGroup(Integer pkPermissionGroupId, String permissionGroupCode) {
        this.pkPermissionGroupId = pkPermissionGroupId;
        this.permissionGroupCode = permissionGroupCode;
    }

    public Integer getPkPermissionGroupId() {
        return pkPermissionGroupId;
    }

    public void setPkPermissionGroupId(Integer pkPermissionGroupId) {
        this.pkPermissionGroupId = pkPermissionGroupId;
    }

    public String getPermissionGroupCode() {
        return permissionGroupCode;
    }

    public void setPermissionGroupCode(String permissionGroupCode) {
        this.permissionGroupCode = permissionGroupCode;
    }

    public String getPermissionGroupName() {
        return permissionGroupName;
    }

    public void setPermissionGroupName(String permissionGroupName) {
        this.permissionGroupName = permissionGroupName;
    }

    public String getPermissionGroupDesc() {
        return permissionGroupDesc;
    }

    public void setPermissionGroupDesc(String permissionGroupDesc) {
        this.permissionGroupDesc = permissionGroupDesc;
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

    public Collection<AuthUserPermissionGroupMapping> getAuthUserPermissionGroupMappingCollection() {
        return authUserPermissionGroupMappingCollection;
    }

    public void setAuthUserPermissionGroupMappingCollection(Collection<AuthUserPermissionGroupMapping> authUserPermissionGroupMappingCollection) {
        this.authUserPermissionGroupMappingCollection = authUserPermissionGroupMappingCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkPermissionGroupId != null ? pkPermissionGroupId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AuthPermissionGroup)) {
            return false;
        }
        AuthPermissionGroup other = (AuthPermissionGroup) object;
        if ((this.pkPermissionGroupId == null && other.pkPermissionGroupId != null) || (this.pkPermissionGroupId != null && !this.pkPermissionGroupId.equals(other.pkPermissionGroupId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.authconfig.domain.AuthPermissionGroup[ pkPermissionGroupId=" + pkPermissionGroupId + " ]";
    }
    
}

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

/**
 *
 * @author Ramachandran_N02
 */
@Entity
@Table(name = "AUTH_ORGANIZATION_ROLE")
@NamedQueries({
    @NamedQuery(name = "AuthOrganizationRole.findAll", query = "SELECT a FROM AuthOrganizationRole a")})
public class AuthOrganizationRole implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_ORGANIZATION_ROLE_ID")
    private Integer pkOrganizationRoleId;
    @Size(max = 20)
    @Column(name = "ORGANIZATION_ROLE_CODE")
    private String organizationRoleCode;
    @Size(max = 200)
    @Column(name = "ORGANIZATION_ROLE_NAME")
    private String organizationRoleName;
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
    @OneToMany(mappedBy = "fkOrganizationRoleId")
    private Collection<AuthUserOrganizationRole> authUserOrganizationRoleCollection;
    @JoinColumn(name = "FK_ORGANIZATION_ID", referencedColumnName = "PK_ORGANIZATION_ID")
    @ManyToOne
    private AuthOrganization fkOrganizationId;

    public AuthOrganizationRole() {
    }

    public AuthOrganizationRole(Integer pkOrganizationRoleId) {
        this.pkOrganizationRoleId = pkOrganizationRoleId;
    }

    public Integer getPkOrganizationRoleId() {
        return pkOrganizationRoleId;
    }

    public void setPkOrganizationRoleId(Integer pkOrganizationRoleId) {
        this.pkOrganizationRoleId = pkOrganizationRoleId;
    }

    public String getOrganizationRoleCode() {
        return organizationRoleCode;
    }

    public void setOrganizationRoleCode(String organizationRoleCode) {
        this.organizationRoleCode = organizationRoleCode;
    }

    public String getOrganizationRoleName() {
        return organizationRoleName;
    }

    public void setOrganizationRoleName(String organizationRoleName) {
        this.organizationRoleName = organizationRoleName;
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

    public AuthOrganization getFkOrganizationId() {
        return fkOrganizationId;
    }

    public void setFkOrganizationId(AuthOrganization fkOrganizationId) {
        this.fkOrganizationId = fkOrganizationId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkOrganizationRoleId != null ? pkOrganizationRoleId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AuthOrganizationRole)) {
            return false;
        }
        AuthOrganizationRole other = (AuthOrganizationRole) object;
        if ((this.pkOrganizationRoleId == null && other.pkOrganizationRoleId != null) || (this.pkOrganizationRoleId != null && !this.pkOrganizationRoleId.equals(other.pkOrganizationRoleId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.authconfig.domain.AuthOrganizationRole[ pkOrganizationRoleId=" + pkOrganizationRoleId + " ]";
    }
    
}

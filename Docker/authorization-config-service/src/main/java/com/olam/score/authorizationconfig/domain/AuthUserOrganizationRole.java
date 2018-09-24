/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olam.score.authorizationconfig.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "AUTH_USER_ORGANIZATION_ROLE")
@NamedQueries({
    @NamedQuery(name = "AuthUserOrganizationRole.findAll", query = "SELECT a FROM AuthUserOrganizationRole a")})
public class AuthUserOrganizationRole implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_USER_ORGANIZATION_ROLE_ID")
    private Integer pkUserOrganizationRoleId;
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
    @JoinColumn(name = "FK_APP_USER_ID", referencedColumnName = "PK_APP_USER_ID")
    @ManyToOne
    private AuthAppUser fkAppUserId;
    @JoinColumn(name = "FK_ORGANIZATION_ROLE_ID", referencedColumnName = "PK_ORGANIZATION_ROLE_ID")
    @ManyToOne
    private AuthOrganizationRole fkOrganizationRoleId;

    public AuthUserOrganizationRole() {
    }

    public AuthUserOrganizationRole(Integer pkUserOrganizationRoleId) {
        this.pkUserOrganizationRoleId = pkUserOrganizationRoleId;
    }

    public Integer getPkUserOrganizationRoleId() {
        return pkUserOrganizationRoleId;
    }

    public void setPkUserOrganizationRoleId(Integer pkUserOrganizationRoleId) {
        this.pkUserOrganizationRoleId = pkUserOrganizationRoleId;
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

    public AuthAppUser getFkAppUserId() {
        return fkAppUserId;
    }

    public void setFkAppUserId(AuthAppUser fkAppUserId) {
        this.fkAppUserId = fkAppUserId;
    }

    public AuthOrganizationRole getFkOrganizationRoleId() {
        return fkOrganizationRoleId;
    }

    public void setFkOrganizationRoleId(AuthOrganizationRole fkOrganizationRoleId) {
        this.fkOrganizationRoleId = fkOrganizationRoleId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkUserOrganizationRoleId != null ? pkUserOrganizationRoleId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AuthUserOrganizationRole)) {
            return false;
        }
        AuthUserOrganizationRole other = (AuthUserOrganizationRole) object;
        if ((this.pkUserOrganizationRoleId == null && other.pkUserOrganizationRoleId != null) || (this.pkUserOrganizationRoleId != null && !this.pkUserOrganizationRoleId.equals(other.pkUserOrganizationRoleId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.authconfig.domain.AuthUserOrganizationRole[ pkUserOrganizationRoleId=" + pkUserOrganizationRoleId + " ]";
    }
    
}

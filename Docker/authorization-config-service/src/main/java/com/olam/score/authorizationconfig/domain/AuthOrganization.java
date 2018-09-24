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
@Table(name = "AUTH_ORGANIZATION")
@NamedQueries({
    @NamedQuery(name = "AuthOrganization.findAll", query = "SELECT a FROM AuthOrganization a")})
public class AuthOrganization implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_ORGANIZATION_ID")
    private Integer pkOrganizationId;
    @Size(max = 200)
    @Column(name = "ORGANIZATION_NAME")
    private String organizationName;
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
    @OneToMany(mappedBy = "fkOrganizationId")
    private Collection<AuthOrganizationRole> authOrganizationRoleCollection;
    @OneToMany(mappedBy = "fkOrganizationId")
    private Collection<AuthAppUser> authAppUserCollection;

    public AuthOrganization() {
    }

    public AuthOrganization(Integer pkOrganizationId) {
        this.pkOrganizationId = pkOrganizationId;
    }

    public Integer getPkOrganizationId() {
        return pkOrganizationId;
    }

    public void setPkOrganizationId(Integer pkOrganizationId) {
        this.pkOrganizationId = pkOrganizationId;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
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

    public Collection<AuthOrganizationRole> getAuthOrganizationRoleCollection() {
        return authOrganizationRoleCollection;
    }

    public void setAuthOrganizationRoleCollection(Collection<AuthOrganizationRole> authOrganizationRoleCollection) {
        this.authOrganizationRoleCollection = authOrganizationRoleCollection;
    }

    public Collection<AuthAppUser> getAuthAppUserCollection() {
        return authAppUserCollection;
    }

    public void setAuthAppUserCollection(Collection<AuthAppUser> authAppUserCollection) {
        this.authAppUserCollection = authAppUserCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkOrganizationId != null ? pkOrganizationId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AuthOrganization)) {
            return false;
        }
        AuthOrganization other = (AuthOrganization) object;
        if ((this.pkOrganizationId == null && other.pkOrganizationId != null) || (this.pkOrganizationId != null && !this.pkOrganizationId.equals(other.pkOrganizationId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.authconfig.domain.AuthOrganization[ pkOrganizationId=" + pkOrganizationId + " ]";
    }
    
}

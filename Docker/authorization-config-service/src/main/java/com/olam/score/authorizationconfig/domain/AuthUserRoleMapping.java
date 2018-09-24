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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ramachandran_N02
 */
@Entity
@Table(name = "AUTH_USER_ROLE_MAPPING")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AuthUserRoleMapping.findAll", query = "SELECT a FROM AuthUserRoleMapping a")
    , @NamedQuery(name = "AuthUserRoleMapping.findByPkUserRoleMappingId", query = "SELECT a FROM AuthUserRoleMapping a WHERE a.pkUserRoleMappingId = :pkUserRoleMappingId")
    , @NamedQuery(name = "AuthUserRoleMapping.findByFkPartyId", query = "SELECT a FROM AuthUserRoleMapping a WHERE a.fkPartyId = :fkPartyId")
    , @NamedQuery(name = "AuthUserRoleMapping.findByFkProdId", query = "SELECT a FROM AuthUserRoleMapping a WHERE a.fkProdId = :fkProdId")
    , @NamedQuery(name = "AuthUserRoleMapping.findByUnitId", query = "SELECT a FROM AuthUserRoleMapping a WHERE a.unitId = :unitId")
    , @NamedQuery(name = "AuthUserRoleMapping.findByPortfolioId", query = "SELECT a FROM AuthUserRoleMapping a WHERE a.portfolioId = :portfolioId")
    , @NamedQuery(name = "AuthUserRoleMapping.findByFkStatusId", query = "SELECT a FROM AuthUserRoleMapping a WHERE a.fkStatusId = :fkStatusId")
    , @NamedQuery(name = "AuthUserRoleMapping.findByCreatedBy", query = "SELECT a FROM AuthUserRoleMapping a WHERE a.createdBy = :createdBy")
    , @NamedQuery(name = "AuthUserRoleMapping.findByCreatedDate", query = "SELECT a FROM AuthUserRoleMapping a WHERE a.createdDate = :createdDate")
    , @NamedQuery(name = "AuthUserRoleMapping.findByModifiedBy", query = "SELECT a FROM AuthUserRoleMapping a WHERE a.modifiedBy = :modifiedBy")
    , @NamedQuery(name = "AuthUserRoleMapping.findByModifiedDate", query = "SELECT a FROM AuthUserRoleMapping a WHERE a.modifiedDate = :modifiedDate")})

public class AuthUserRoleMapping implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_USER_ROLE_MAPPING_ID")
    private Integer pkUserRoleMappingId;
    @Column(name = "FK_PARTY_ID")
    private Integer fkPartyId;
    @Column(name = "FK_PROD_ID")
    private Integer fkProdId;
    @Column(name = "UNIT_ID")
    private Integer unitId;
    @Column(name = "PORTFOLIO_ID")
    private Integer portfolioId;
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
    @JoinColumn(name = "FK_ROLE_ID", referencedColumnName = "PK_ROLE_ID")
    @ManyToOne
    private AuthRole fkRoleId;

    public AuthUserRoleMapping() {
    }

    public AuthUserRoleMapping(Integer pkUserRoleMappingId) {
        this.pkUserRoleMappingId = pkUserRoleMappingId;
    }

    public Integer getPkUserRoleMappingId() {
        return pkUserRoleMappingId;
    }

    public void setPkUserRoleMappingId(Integer pkUserRoleMappingId) {
        this.pkUserRoleMappingId = pkUserRoleMappingId;
    }

    public Integer getFkPartyId() {
        return fkPartyId;
    }

    public void setFkPartyId(Integer fkPartyId) {
        this.fkPartyId = fkPartyId;
    }

    public Integer getFkProdId() {
        return fkProdId;
    }

    public void setFkProdId(Integer fkProdId) {
        this.fkProdId = fkProdId;
    }

    public Integer getUnitId() {
        return unitId;
    }

    public void setUnitId(Integer unitId) {
        this.unitId = unitId;
    }

    public Integer getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(Integer portfolioId) {
        this.portfolioId = portfolioId;
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

    public AuthRole getFkRoleId() {
        return fkRoleId;
    }

    public void setFkRoleId(AuthRole fkRoleId) {
        this.fkRoleId = fkRoleId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkUserRoleMappingId != null ? pkUserRoleMappingId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AuthUserRoleMapping)) {
            return false;
        }
        AuthUserRoleMapping other = (AuthUserRoleMapping) object;
        if ((this.pkUserRoleMappingId == null && other.pkUserRoleMappingId != null) || (this.pkUserRoleMappingId != null && !this.pkUserRoleMappingId.equals(other.pkUserRoleMappingId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.authconfig.domain.AuthUserRoleMapping[ pkUserRoleMappingId=" + pkUserRoleMappingId + " ]";
    }
    
}

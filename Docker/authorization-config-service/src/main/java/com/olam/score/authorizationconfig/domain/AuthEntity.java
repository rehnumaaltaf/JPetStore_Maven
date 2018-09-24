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
@Table(name = "AUTH_ENTITY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AuthEntity.findAll", query = "SELECT a FROM AuthEntity a")
    , @NamedQuery(name = "AuthEntity.findByPkEntityId", query = "SELECT a FROM AuthEntity a WHERE a.pkEntityId = :pkEntityId")
    , @NamedQuery(name = "AuthEntity.findByEntityCode", query = "SELECT a FROM AuthEntity a WHERE a.entityCode = :entityCode")
    , @NamedQuery(name = "AuthEntity.findByEntityName", query = "SELECT a FROM AuthEntity a WHERE a.entityName = :entityName")
    , @NamedQuery(name = "AuthEntity.findByEntityDesc", query = "SELECT a FROM AuthEntity a WHERE a.entityDesc = :entityDesc")
    , @NamedQuery(name = "AuthEntity.findByFkStatusId", query = "SELECT a FROM AuthEntity a WHERE a.fkStatusId = :fkStatusId")
    , @NamedQuery(name = "AuthEntity.findByCreatedBy", query = "SELECT a FROM AuthEntity a WHERE a.createdBy = :createdBy")
    , @NamedQuery(name = "AuthEntity.findByCreatedDate", query = "SELECT a FROM AuthEntity a WHERE a.createdDate = :createdDate")
    , @NamedQuery(name = "AuthEntity.findByModifiedBy", query = "SELECT a FROM AuthEntity a WHERE a.modifiedBy = :modifiedBy")
    , @NamedQuery(name = "AuthEntity.findByModifiedDate", query = "SELECT a FROM AuthEntity a WHERE a.modifiedDate = :modifiedDate")
    , @NamedQuery(name = "AuthEntity.findByUrl", query = "SELECT a FROM AuthEntity a WHERE a.url = :url")})

public class AuthEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_ENTITY_ID")
    private Integer pkEntityId;
    @Size(max = 20)
    @Column(name = "ENTITY_CODE")
    private String entityCode;
    @Size(max = 200)
    @Column(name = "ENTITY_NAME")
    private String entityName;
    @Size(max = 1000)
    @Column(name = "ENTITY_DESC")
    private String entityDesc;
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
    @Size(max = 500)
    @Column(name = "ENTITY_URL")
    private String entityUrl;
    @Size(max = 200)
    @Column(name = "ENTITY_DISPLAY_NAME")
    private String entityDisplayName;
    @Size(max = 1)
    @Column(name = "LIMIT_IND")
    private String limitInd;
    @Size(max = 1)
    @Column(name = "COST_IND")
    private String costInd;
    @JoinColumn(name = "FK_MODULE_ID", referencedColumnName = "PK_MODULE_ID")
    @ManyToOne
    private AuthModule fkModuleId;
    @OneToMany(mappedBy = "fkEntityId")
    private Collection<AuthAttribute> authAttributeCollection;
    @OneToMany(mappedBy = "fkEntityId")
    private Collection<AuthFeature> authFeatureCollection;

    public AuthEntity() {
    }

    public AuthEntity(Integer pkEntityId) {
        this.pkEntityId = pkEntityId;
    }

    public Integer getPkEntityId() {
        return pkEntityId;
    }

    public void setPkEntityId(Integer pkEntityId) {
        this.pkEntityId = pkEntityId;
    }

    public String getEntityCode() {
        return entityCode;
    }

    public void setEntityCode(String entityCode) {
        this.entityCode = entityCode;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getEntityDesc() {
        return entityDesc;
    }

    public void setEntityDesc(String entityDesc) {
        this.entityDesc = entityDesc;
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

    public String getEntityUrl() {
        return entityUrl;
    }

    public void setEntityUrl(String entityUrl) {
        this.entityUrl = entityUrl;
    }

    public String getEntityDisplayName() {
        return entityDisplayName;
    }

    public void setEntityDisplayName(String entityDisplayName) {
        this.entityDisplayName = entityDisplayName;
    }

    public String getLimitInd() {
        return limitInd;
    }

    public void setLimitInd(String limitInd) {
        this.limitInd = limitInd;
    }

    public String getCostInd() {
        return costInd;
    }

    public void setCostInd(String costInd) {
        this.costInd = costInd;
    }

    public AuthModule getFkModuleId() {
        return fkModuleId;
    }

    public void setFkModuleId(AuthModule fkModuleId) {
        this.fkModuleId = fkModuleId;
    }

    public Collection<AuthAttribute> getAuthAttributeCollection() {
        return authAttributeCollection;
    }

    public void setAuthAttributeCollection(Collection<AuthAttribute> authAttributeCollection) {
        this.authAttributeCollection = authAttributeCollection;
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
        hash += (pkEntityId != null ? pkEntityId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AuthEntity)) {
            return false;
        }
        AuthEntity other = (AuthEntity) object;
        if ((this.pkEntityId == null && other.pkEntityId != null) || (this.pkEntityId != null && !this.pkEntityId.equals(other.pkEntityId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.authconfig.domain.AuthEntity[ pkEntityId=" + pkEntityId + " ]";
    }
    
}

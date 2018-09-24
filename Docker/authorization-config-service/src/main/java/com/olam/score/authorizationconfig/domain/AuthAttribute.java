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
@Table(name = "AUTH_ATTRIBUTE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AuthAttribute.findAll", query = "SELECT a FROM AuthAttribute a")
    , @NamedQuery(name = "AuthAttribute.findByPkAttributeId", query = "SELECT a FROM AuthAttribute a WHERE a.pkAttributeId = :pkAttributeId")
    , @NamedQuery(name = "AuthAttribute.findByAttributeName", query = "SELECT a FROM AuthAttribute a WHERE a.attributeName = :attributeName")
    , @NamedQuery(name = "AuthAttribute.findByAttributeDesc", query = "SELECT a FROM AuthAttribute a WHERE a.attributeDesc = :attributeDesc")
    , @NamedQuery(name = "AuthAttribute.findByFkStatusId", query = "SELECT a FROM AuthAttribute a WHERE a.fkStatusId = :fkStatusId")
    , @NamedQuery(name = "AuthAttribute.findByCreatedBy", query = "SELECT a FROM AuthAttribute a WHERE a.createdBy = :createdBy")
    , @NamedQuery(name = "AuthAttribute.findByCreatedDate", query = "SELECT a FROM AuthAttribute a WHERE a.createdDate = :createdDate")
    , @NamedQuery(name = "AuthAttribute.findByModifiedBy", query = "SELECT a FROM AuthAttribute a WHERE a.modifiedBy = :modifiedBy")
    , @NamedQuery(name = "AuthAttribute.findByModifiedDate", query = "SELECT a FROM AuthAttribute a WHERE a.modifiedDate = :modifiedDate")})

public class AuthAttribute implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_ATTRIBUTE_ID")
    private Integer pkAttributeId;
    @Size(max = 200)
    @Column(name = "ATTRIBUTE_NAME")
    private String attributeName;
    @Size(max = 1000)
    @Column(name = "ATTRIBUTE_DESC")
    private String attributeDesc;
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
    @OneToMany(mappedBy = "fkAttributeId")
    private Collection<AuthFeatureAttributeMapping> authFeatureAttributeMappingCollection;
    @JoinColumn(name = "FK_ENTITY_ID", referencedColumnName = "PK_ENTITY_ID")
    @ManyToOne
    private AuthEntity fkEntityId;

    public AuthAttribute() {
    }

    public AuthAttribute(Integer pkAttributeId) {
        this.pkAttributeId = pkAttributeId;
    }

    public Integer getPkAttributeId() {
        return pkAttributeId;
    }

    public void setPkAttributeId(Integer pkAttributeId) {
        this.pkAttributeId = pkAttributeId;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public String getAttributeDesc() {
        return attributeDesc;
    }

    public void setAttributeDesc(String attributeDesc) {
        this.attributeDesc = attributeDesc;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkAttributeId != null ? pkAttributeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AuthAttribute)) {
            return false;
        }
        AuthAttribute other = (AuthAttribute) object;
        if ((this.pkAttributeId == null && other.pkAttributeId != null) || (this.pkAttributeId != null && !this.pkAttributeId.equals(other.pkAttributeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.authconfig.domain.AuthAttribute[ pkAttributeId=" + pkAttributeId + " ]";
    }
    
}

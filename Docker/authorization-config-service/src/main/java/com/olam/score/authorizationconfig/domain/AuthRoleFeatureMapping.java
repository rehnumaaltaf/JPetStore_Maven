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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "AUTH_ROLE_FEATURE_MAPPING")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AuthRoleFeatureMapping.findAll", query = "SELECT a FROM AuthRoleFeatureMapping a")
    , @NamedQuery(name = "AuthRoleFeatureMapping.findByPkRoleFeatureMappingId", query = "SELECT a FROM AuthRoleFeatureMapping a WHERE a.pkRoleFeatureMappingId = :pkRoleFeatureMappingId")
    , @NamedQuery(name = "AuthRoleFeatureMapping.findByFkStatusId", query = "SELECT a FROM AuthRoleFeatureMapping a WHERE a.fkStatusId = :fkStatusId")
    , @NamedQuery(name = "AuthRoleFeatureMapping.findByCreatedBy", query = "SELECT a FROM AuthRoleFeatureMapping a WHERE a.createdBy = :createdBy")
    , @NamedQuery(name = "AuthRoleFeatureMapping.findByCreatedDate", query = "SELECT a FROM AuthRoleFeatureMapping a WHERE a.createdDate = :createdDate")
    , @NamedQuery(name = "AuthRoleFeatureMapping.findByModifiedBy", query = "SELECT a FROM AuthRoleFeatureMapping a WHERE a.modifiedBy = :modifiedBy")
    , @NamedQuery(name = "AuthRoleFeatureMapping.findByModifiedDate", query = "SELECT a FROM AuthRoleFeatureMapping a WHERE a.modifiedDate = :modifiedDate")})

public class AuthRoleFeatureMapping implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="AUTH_ROLEFEATURE_UOMIDSEQ_GENERATOR")
   	@SequenceGenerator(name="AUTH_ROLEFEATURE_UOMIDSEQ_GENERATOR", sequenceName="auth.ROLE_FEATURE_MAPPING_ID_SEQ" ,allocationSize=1)
    @Column(name = "PK_ROLE_FEATURE_MAPPING_ID")
    private Integer pkRoleFeatureMappingId;
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
    @JoinColumn(name = "FK_FEATURE_ID", referencedColumnName = "PK_FEATURE_ID")
    @ManyToOne
    private AuthFeature fkFeatureId;
    @JoinColumn(name = "FK_ROLE_ID", referencedColumnName = "PK_ROLE_ID")
    @ManyToOne
    private AuthRole fkRoleId;

    public AuthRoleFeatureMapping() {
    }

    public AuthRoleFeatureMapping(Integer pkRoleFeatureMappingId) {
        this.pkRoleFeatureMappingId = pkRoleFeatureMappingId;
    }

    public Integer getPkRoleFeatureMappingId() {
        return pkRoleFeatureMappingId;
    }

    public void setPkRoleFeatureMappingId(Integer pkRoleFeatureMappingId) {
        this.pkRoleFeatureMappingId = pkRoleFeatureMappingId;
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

    public AuthFeature getFkFeatureId() {
        return fkFeatureId;
    }

    public void setFkFeatureId(AuthFeature fkFeatureId) {
        this.fkFeatureId = fkFeatureId;
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
        hash += (pkRoleFeatureMappingId != null ? pkRoleFeatureMappingId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AuthRoleFeatureMapping)) {
            return false;
        }
        AuthRoleFeatureMapping other = (AuthRoleFeatureMapping) object;
        if ((this.pkRoleFeatureMappingId == null && other.pkRoleFeatureMappingId != null) || (this.pkRoleFeatureMappingId != null && !this.pkRoleFeatureMappingId.equals(other.pkRoleFeatureMappingId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.authconfig.domain.AuthRoleFeatureMapping[ pkRoleFeatureMappingId=" + pkRoleFeatureMappingId + " ]";
    }
    
}

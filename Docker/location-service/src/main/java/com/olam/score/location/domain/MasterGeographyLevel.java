/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olam.score.location.domain;

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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author rashmi.kedlaya
 */
@Entity
@Table(name = "MASTER_GEOGRAPHY_LEVEL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterGeographyLevel.findAll", query = "SELECT m FROM MasterGeographyLevel m")})
public class MasterGeographyLevel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_GEO_LEVEL_ID")
    private Integer pkGeoLevelId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "GEO_LEVEL_CODE")
    private String geoLevelCode;
    @Size(max = 200)
    @Column(name = "GEO_LEVEL_NAME")
    private String geoLevelName;
    @Size(max = 200)
    @Column(name = "GEO_LEVEL_FULLNAME")
    private String geoLevelFullname;
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
    @Column(name = "FK_STATUS_ID")
    private Integer fkStatusId;
    @OneToMany(mappedBy = "fkGeoLevelId")
    private Collection<MasterGeography> masterGeographyCollection;

    public MasterGeographyLevel() {
    }

    public MasterGeographyLevel(Integer pkGeoLevelId) {
        this.pkGeoLevelId = pkGeoLevelId;
    }

    public MasterGeographyLevel(Integer pkGeoLevelId, String geoLevelCode) {
        this.pkGeoLevelId = pkGeoLevelId;
        this.geoLevelCode = geoLevelCode;
    }

    public Integer getPkGeoLevelId() {
        return pkGeoLevelId;
    }

    public void setPkGeoLevelId(Integer pkGeoLevelId) {
        this.pkGeoLevelId = pkGeoLevelId;
    }

    public String getGeoLevelCode() {
        return geoLevelCode;
    }

    public void setGeoLevelCode(String geoLevelCode) {
        this.geoLevelCode = geoLevelCode;
    }

    public String getGeoLevelName() {
        return geoLevelName;
    }

    public void setGeoLevelName(String geoLevelName) {
        this.geoLevelName = geoLevelName;
    }

    public String getGeoLevelFullname() {
        return geoLevelFullname;
    }

    public void setGeoLevelFullname(String geoLevelFullname) {
        this.geoLevelFullname = geoLevelFullname;
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

    public Integer getFkStatusId() {
        return fkStatusId;
    }

    public void setFkStatusId(Integer fkStatusId) {
        this.fkStatusId = fkStatusId;
    }

    @XmlTransient
    public Collection<MasterGeography> getMasterGeographyCollection() {
        return masterGeographyCollection;
    }

    public void setMasterGeographyCollection(Collection<MasterGeography> masterGeographyCollection) {
        this.masterGeographyCollection = masterGeographyCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkGeoLevelId != null ? pkGeoLevelId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterGeographyLevel)) {
            return false;
        }
        MasterGeographyLevel other = (MasterGeographyLevel) object;
        if ((this.pkGeoLevelId == null && other.pkGeoLevelId != null) || (this.pkGeoLevelId != null && !this.pkGeoLevelId.equals(other.pkGeoLevelId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.location.domain.MasterGeographyLevel[ pkGeoLevelId=" + pkGeoLevelId + " ]";
    }
    
}

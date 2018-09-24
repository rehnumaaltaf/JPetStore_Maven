/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olam.score.location.domain;

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
 * @author rashmi.kedlaya
 */
@Entity
@Table(name = "MASTER_ISD")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterIsd.findAll", query = "SELECT m FROM MasterIsd m")})
public class MasterIsd implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_ISD_ID")
    private Integer pkIsdId;
    @Size(max = 20)
    @Column(name = "ISD_CODE")
    private String isdCode;
    @Size(max = 200)
    @Column(name = "COUNTRY_NAME")
    private String countryName;
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
    @JoinColumn(name = "FK_GEO_ID", referencedColumnName = "PK_GEO_ID")
    @ManyToOne
    private MasterGeography fkGeoId;

    public MasterIsd() {
    }

    public MasterIsd(Integer pkIsdId) {
        this.pkIsdId = pkIsdId;
    }

    public Integer getPkIsdId() {
        return pkIsdId;
    }

    public void setPkIsdId(Integer pkIsdId) {
        this.pkIsdId = pkIsdId;
    }

    public String getIsdCode() {
        return isdCode;
    }

    public void setIsdCode(String isdCode) {
        this.isdCode = isdCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
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

    public MasterGeography getFkGeoId() {
        return fkGeoId;
    }

    public void setFkGeoId(MasterGeography fkGeoId) {
        this.fkGeoId = fkGeoId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkIsdId != null ? pkIsdId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterIsd)) {
            return false;
        }
        MasterIsd other = (MasterIsd) object;
        if ((this.pkIsdId == null && other.pkIsdId != null) || (this.pkIsdId != null && !this.pkIsdId.equals(other.pkIsdId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.location.domain.MasterIsd[ pkIsdId=" + pkIsdId + " ]";
    }
    
}

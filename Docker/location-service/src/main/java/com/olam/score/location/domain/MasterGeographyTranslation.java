/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olam.score.location.domain;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ramachandran_n02
 */
@Entity
@Table(name = "MASTER_GEOGRAPHY_TRANSLATION",  schema = "location")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterGeographyTranslation.findAll", query = "SELECT m FROM MasterGeographyTranslation m")})
public class MasterGeographyTranslation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_GEO_TRANSL_ID")
    private Integer pkGeoTranslId;
    @Column(name = "FK_LANGUAGE_ID")
    private Integer fkLanguageId;
    @Size(max = 200)
    @Column(name = "GEO_TRANS_LNAME")
    private String geoTransLname;
    @Size(max = 1000)
    @Column(name = "GEO_TRANSL_FULLNAME")
    private String geoTranslFullname;
    @JoinColumn(name = "FK_GEO_ID", referencedColumnName = "PK_GEO_ID")
    @ManyToOne
    private MasterGeography fkGeoId;

    public MasterGeographyTranslation() {
    }

    public MasterGeographyTranslation(Integer pkGeoTranslId) {
        this.pkGeoTranslId = pkGeoTranslId;
    }

    public Integer getPkGeoTranslId() {
        return pkGeoTranslId;
    }

    public void setPkGeoTranslId(Integer pkGeoTranslId) {
        this.pkGeoTranslId = pkGeoTranslId;
    }

    public Integer getFkLanguageId() {
        return fkLanguageId;
    }

    public void setFkLanguageId(Integer fkLanguageId) {
        this.fkLanguageId = fkLanguageId;
    }

    public String getGeoTransLname() {
        return geoTransLname;
    }

    public void setGeoTransLname(String geoTransLname) {
        this.geoTransLname = geoTransLname;
    }

    public String getGeoTranslFullname() {
        return geoTranslFullname;
    }

    public void setGeoTranslFullname(String geoTranslFullname) {
        this.geoTranslFullname = geoTranslFullname;
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
        hash += (pkGeoTranslId != null ? pkGeoTranslId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterGeographyTranslation)) {
            return false;
        }
        MasterGeographyTranslation other = (MasterGeographyTranslation) object;
        if ((this.pkGeoTranslId == null && other.pkGeoTranslId != null) || (this.pkGeoTranslId != null && !this.pkGeoTranslId.equals(other.pkGeoTranslId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.location.domain.MasterGeographyTranslation[ pkGeoTranslId=" + pkGeoTranslId + " ]";
    }
    
}

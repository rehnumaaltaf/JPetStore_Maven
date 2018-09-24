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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ramachandran_n02
 */
@Entity
@Table(name = "MASTER_LOCATION_TYPE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterLocationType.findAll", query = "SELECT m FROM MasterLocationType m")})
public class MasterLocationType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_LOC_TYPE_ID")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LOC_TYPE_ID_SEQ_GENERATOR")
   	@SequenceGenerator(name="LOC_TYPE_ID_SEQ_GENERATOR", sequenceName="LOC_TYPE_ID_SEQ",allocationSize=1) 
    private Integer pkLocTypeId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "LOC_TYPE_NAME")
    private String locTypeName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "LOC_TYPE_CODE")
    private String locTypeCode;
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
    @Size(max = 200)
    @Column(name = "LOC_TYPE_FULLNAME")
    private String locTypeFullname;
    @Column(name = "FK_STATUS_ID")
    private Integer fkStatusId;
    @OneToMany(mappedBy = "pkLocTypeId")
    private Collection<MasterLocLocTypeAssoc> masterLocLocTypeAssocCollection;

    public MasterLocationType() {
    }

    public MasterLocationType(Integer pkLocTypeId) {
        this.pkLocTypeId = pkLocTypeId;
    }

    public MasterLocationType(Integer pkLocTypeId, String locTypeName, String locTypeCode) {
        this.pkLocTypeId = pkLocTypeId;
        this.locTypeName = locTypeName;
        this.locTypeCode = locTypeCode;
    }

    public Integer getPkLocTypeId() {
        return pkLocTypeId;
    }

    public void setPkLocTypeId(Integer pkLocTypeId) {
        this.pkLocTypeId = pkLocTypeId;
    }

    public String getLocTypeName() {
        return locTypeName;
    }

    public void setLocTypeName(String locTypeName) {
        this.locTypeName = locTypeName;
    }

    public String getLocTypeCode() {
        return locTypeCode;
    }

    public void setLocTypeCode(String locTypeCode) {
        this.locTypeCode = locTypeCode;
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

    public String getLocTypeFullname() {
        return locTypeFullname;
    }

    public void setLocTypeFullname(String locTypeFullname) {
        this.locTypeFullname = locTypeFullname;
    }

    public Integer getFkStatusId() {
        return fkStatusId;
    }

    public void setFkStatusId(Integer fkStatusId) {
        this.fkStatusId = fkStatusId;
    }

    @XmlTransient
    public Collection<MasterLocLocTypeAssoc> getMasterLocLocTypeAssocCollection() {
        return masterLocLocTypeAssocCollection;
    }

    public void setMasterLocLocTypeAssocCollection(Collection<MasterLocLocTypeAssoc> masterLocLocTypeAssocCollection) {
        this.masterLocLocTypeAssocCollection = masterLocLocTypeAssocCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkLocTypeId != null ? pkLocTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterLocationType)) {
            return false;
        }
        MasterLocationType other = (MasterLocationType) object;
        if ((this.pkLocTypeId == null && other.pkLocTypeId != null) || (this.pkLocTypeId != null && !this.pkLocTypeId.equals(other.pkLocTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.location.domain.MasterLocationType[ pkLocTypeId=" + pkLocTypeId + " ]";
    }
    
}

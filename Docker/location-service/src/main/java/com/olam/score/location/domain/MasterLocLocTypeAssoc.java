/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olam.score.location.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
 * @author ramachandran_n02
 */
@Entity
@Table(name = "MASTER_LOC_LOC_TYPE_ASSOC")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterLocLocTypeAssoc.findAll", query = "SELECT m FROM MasterLocLocTypeAssoc m")})
public class MasterLocLocTypeAssoc implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_LOC_LOC_TYPE_ASSOC_ID")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LOC_LOC_TYPE_ASSOC_ID_SEQ_GENERATOR")
   	@SequenceGenerator(name="LOC_LOC_TYPE_ASSOC_ID_SEQ_GENERATOR", sequenceName="LOC_LOC_TYPE_ASSOC_ID_SEQ",allocationSize=1) 
    private Integer pkLocLocTypeAssocId;
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
    @JoinColumn(name = "PK_LOC_ID", referencedColumnName = "PK_LOC_ID")
    @ManyToOne(fetch=FetchType.LAZY,cascade = CascadeType.ALL)
    private MasterLocation pkLocId;
    @JoinColumn(name = "PK_LOC_TYPE_ID", referencedColumnName = "PK_LOC_TYPE_ID")
    @ManyToOne
    private MasterLocationType pkLocTypeId;

    public MasterLocLocTypeAssoc() {
    }

    public MasterLocLocTypeAssoc(Integer pkLocLocTypeAssocId) {
        this.pkLocLocTypeAssocId = pkLocLocTypeAssocId;
    }

    public Integer getPkLocLocTypeAssocId() {
        return pkLocLocTypeAssocId;
    }

    public void setPkLocLocTypeAssocId(Integer pkLocLocTypeAssocId) {
        this.pkLocLocTypeAssocId = pkLocLocTypeAssocId;
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

    public MasterLocation getPkLocId() {
        return pkLocId;
    }

    public void setPkLocId(MasterLocation pkLocId) {
        this.pkLocId = pkLocId;
    }

    public MasterLocationType getPkLocTypeId() {
        return pkLocTypeId;
    }

    public void setPkLocTypeId(MasterLocationType pkLocTypeId) {
        this.pkLocTypeId = pkLocTypeId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkLocLocTypeAssocId != null ? pkLocLocTypeAssocId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterLocLocTypeAssoc)) {
            return false;
        }
        MasterLocLocTypeAssoc other = (MasterLocLocTypeAssoc) object;
        if ((this.pkLocLocTypeAssocId == null && other.pkLocLocTypeAssocId != null) || (this.pkLocLocTypeAssocId != null && !this.pkLocLocTypeAssocId.equals(other.pkLocLocTypeAssocId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.location.domain.MasterLocLocTypeAssoc[ pkLocLocTypeAssocId=" + pkLocLocTypeAssocId + " ]";
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olam.score.reference.domain;

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
 * @author ramachandran_n02
 */
@Entity
@Table(name = "MASTER_TRANSPORT_MODE_REF")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterTransportModeRef.findAll", query = "SELECT m FROM MasterTransportModeRef m")})
public class MasterTransportModeRef implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_TRANSPORT_MODE_REF_ID")
    private Integer pkTransportModeRefId;
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
    @Column(name = "TRANSPORT_MODE_REF_NAME")
    private String transportModeRefName;
    @Size(max = 20)
    @Column(name = "TRANSPORT_MODE_REF_CODE")
    private String transportModeRefCode;
    @Size(max = 200)
    @Column(name = "TRANSPORT_MODE_REF_FULLNAME")
    private String transportModeRefFullname;
    @Column(name = "FK_STATUS_ID")
    private int fkStatusId;

    public MasterTransportModeRef() {
    }

    public MasterTransportModeRef(Integer pkTransportModeRefId) {
        this.pkTransportModeRefId = pkTransportModeRefId;
    }

    public Integer getPkTransportModeRefId() {
        return pkTransportModeRefId;
    }

    public void setPkTransportModeRefId(Integer pkTransportModeRefId) {
        this.pkTransportModeRefId = pkTransportModeRefId;
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

    public String getTransportModeRefName() {
        return transportModeRefName;
    }

    public void setTransportModeRefName(String transportModeRefName) {
        this.transportModeRefName = transportModeRefName;
    }

    public String getTransportModeRefCode() {
        return transportModeRefCode;
    }

    public void setTransportModeRefCode(String transportModeRefCode) {
        this.transportModeRefCode = transportModeRefCode;
    }

    public String getTransportModeRefFullname() {
        return transportModeRefFullname;
    }

    public void setTransportModeRefFullname(String transportModeRefFullname) {
        this.transportModeRefFullname = transportModeRefFullname;
    }

    public int getFkStatusId() {
        return fkStatusId;
    }

    public void setFkStatusId(int fkStatusId) {
        this.fkStatusId = fkStatusId;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkTransportModeRefId != null ? pkTransportModeRefId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterTransportModeRef)) {
            return false;
        }
        MasterTransportModeRef other = (MasterTransportModeRef) object;
        if ((this.pkTransportModeRefId == null && other.pkTransportModeRefId != null) || (this.pkTransportModeRefId != null && !this.pkTransportModeRefId.equals(other.pkTransportModeRefId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.reference.domain.MasterTransportModeRef[ pkTransportModeRefId=" + pkTransportModeRefId + " ]";
    }
    
}

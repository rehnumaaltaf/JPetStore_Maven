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
@Table(name = "MASTER_PORT",  schema = "location")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterPort.findAll", query = "SELECT m FROM MasterPort m")})
public class MasterPort implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_PORT_ID")
    private Integer pkPortId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "PORT_CODE")
    private String portCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "PORT_NAME")
    private String portName;
    @Size(max = 200)
    @Column(name = "PORT_FULLNAME")
    private String portFullname;
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

    public MasterPort() {
    }

    public MasterPort(Integer pkPortId) {
        this.pkPortId = pkPortId;
    }

    public MasterPort(Integer pkPortId, String portCode, String portName) {
        this.pkPortId = pkPortId;
        this.portCode = portCode;
        this.portName = portName;
    }

    public Integer getPkPortId() {
        return pkPortId;
    }

    public void setPkPortId(Integer pkPortId) {
        this.pkPortId = pkPortId;
    }

    public String getPortCode() {
        return portCode;
    }

    public void setPortCode(String portCode) {
        this.portCode = portCode;
    }

    public String getPortName() {
        return portName;
    }

    public void setPortName(String portName) {
        this.portName = portName;
    }

    public String getPortFullname() {
        return portFullname;
    }

    public void setPortFullname(String portFullname) {
        this.portFullname = portFullname;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkPortId != null ? pkPortId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterPort)) {
            return false;
        }
        MasterPort other = (MasterPort) object;
        if ((this.pkPortId == null && other.pkPortId != null) || (this.pkPortId != null && !this.pkPortId.equals(other.pkPortId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.location.domain.MasterPort[ pkPortId=" + pkPortId + " ]";
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olam.score.limit.domain;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "MASTER_FOREX_LIMIT_MAPPING",  schema = "limit")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterForexLimitMapping.findAll", query = "SELECT m FROM MasterForexLimitMapping m")})
public class MasterForexLimitMapping implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_FOREX_LIMIT_MAPPING_ID")
    private Integer pkForexLimitMappingId;
    @Column(name = "FK_FOREX_LIMIT_ID")
    private Integer fkForexLimitId;
    @Column(name = "FK_FOREX_ID")
    private Integer fkForexId;
    @Column(name = "FK_STATUS_ID")
    private Integer fkStatusId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "FOREX_LIMIT")
    private BigDecimal forexLimit;
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

    public MasterForexLimitMapping() {
    }

    public MasterForexLimitMapping(Integer pkForexLimitMappingId) {
        this.pkForexLimitMappingId = pkForexLimitMappingId;
    }

    public Integer getPkForexLimitMappingId() {
        return pkForexLimitMappingId;
    }

    public void setPkForexLimitMappingId(Integer pkForexLimitMappingId) {
        this.pkForexLimitMappingId = pkForexLimitMappingId;
    }

    public Integer getFkForexLimitId() {
        return fkForexLimitId;
    }

    public void setFkForexLimitId(Integer fkForexLimitId) {
        this.fkForexLimitId = fkForexLimitId;
    }

    public Integer getFkForexId() {
        return fkForexId;
    }

    public void setFkForexId(Integer fkForexId) {
        this.fkForexId = fkForexId;
    }

    public Integer getFkStatusId() {
        return fkStatusId;
    }

    public void setFkStatusId(Integer fkStatusId) {
        this.fkStatusId = fkStatusId;
    }

    public BigDecimal getForexLimit() {
        return forexLimit;
    }

    public void setForexLimit(BigDecimal forexLimit) {
        this.forexLimit = forexLimit;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkForexLimitMappingId != null ? pkForexLimitMappingId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterForexLimitMapping)) {
            return false;
        }
        MasterForexLimitMapping other = (MasterForexLimitMapping) object;
        if ((this.pkForexLimitMappingId == null && other.pkForexLimitMappingId != null) || (this.pkForexLimitMappingId != null && !this.pkForexLimitMappingId.equals(other.pkForexLimitMappingId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.limit.domain.MasterForexLimitMapping[ pkForexLimitMappingId=" + pkForexLimitMappingId + " ]";
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olam.score.cost.domain;

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
@Table(name = "MASTER_COST_GL_MAPPING", catalog = "SCORE_DEV_002", schema = "cost")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterCostGlMapping.findAll", query = "SELECT m FROM MasterCostGlMapping m")})
public class MasterCostGlMapping implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_COST_GL_MAPPING_ID")
    private Integer pkCostGlMappingId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "COST_GL_MAPPING_NAME")
    private String costGlMappingName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "COST_GL_MAPPING_CODE")
    private String costGlMappingCode;
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
    @Column(name = "FK_GL_ID")
    private Integer fkGlId;
    @Column(name = "FK_PARTY_ID")
    private Integer fkPartyId;
    @Column(name = "FK_STATUS_ID")
    private Integer fkStatusId;
    @JoinColumn(name = "FK_COST_ID", referencedColumnName = "PK_COST_ID")
    @ManyToOne
    private MasterCost fkCostId;

    public MasterCostGlMapping() {
    }

    public MasterCostGlMapping(Integer pkCostGlMappingId) {
        this.pkCostGlMappingId = pkCostGlMappingId;
    }

    public MasterCostGlMapping(Integer pkCostGlMappingId, String costGlMappingName, String costGlMappingCode) {
        this.pkCostGlMappingId = pkCostGlMappingId;
        this.costGlMappingName = costGlMappingName;
        this.costGlMappingCode = costGlMappingCode;
    }

    public Integer getPkCostGlMappingId() {
        return pkCostGlMappingId;
    }

    public void setPkCostGlMappingId(Integer pkCostGlMappingId) {
        this.pkCostGlMappingId = pkCostGlMappingId;
    }

    public String getCostGlMappingName() {
        return costGlMappingName;
    }

    public void setCostGlMappingName(String costGlMappingName) {
        this.costGlMappingName = costGlMappingName;
    }

    public String getCostGlMappingCode() {
        return costGlMappingCode;
    }

    public void setCostGlMappingCode(String costGlMappingCode) {
        this.costGlMappingCode = costGlMappingCode;
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

    public Integer getFkGlId() {
        return fkGlId;
    }

    public void setFkGlId(Integer fkGlId) {
        this.fkGlId = fkGlId;
    }

    public Integer getFkPartyId() {
        return fkPartyId;
    }

    public void setFkPartyId(Integer fkPartyId) {
        this.fkPartyId = fkPartyId;
    }

    public Integer getFkStatusId() {
        return fkStatusId;
    }

    public void setFkStatusId(Integer fkStatusId) {
        this.fkStatusId = fkStatusId;
    }

    public MasterCost getFkCostId() {
        return fkCostId;
    }

    public void setFkCostId(MasterCost fkCostId) {
        this.fkCostId = fkCostId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkCostGlMappingId != null ? pkCostGlMappingId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterCostGlMapping)) {
            return false;
        }
        MasterCostGlMapping other = (MasterCostGlMapping) object;
        if ((this.pkCostGlMappingId == null && other.pkCostGlMappingId != null) || (this.pkCostGlMappingId != null && !this.pkCostGlMappingId.equals(other.pkCostGlMappingId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.cost.domain.MasterCostGlMapping[ pkCostGlMappingId=" + pkCostGlMappingId + " ]";
    }
    
}

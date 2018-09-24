/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olam.score.cost.domain;

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
 * @author ramachandran_n02
 */
@Entity
@Table(name = "MASTER_COST_GROUP", catalog = "SCORE_DEV_002", schema = "cost")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterCostGroup.findAll", query = "SELECT m FROM MasterCostGroup m")})
public class MasterCostGroup implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_COST_GROUP_ID")
    private Integer pkCostGroupId;
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
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "COST_GROUP_NAME")
    private String costGroupName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "COST_GROUP_CODE")
    private String costGroupCode;
    @Column(name = "FK_STATUS_ID")
    private Integer fkStatusId;
    @Size(max = 500)
    @Column(name = "COST_GROUP_DESC")
    private String costGroupDesc;
    @OneToMany(mappedBy = "fkCostGroupId")
    private Collection<MasterCost> masterCostCollection;

    public MasterCostGroup() {
    }

    public MasterCostGroup(Integer pkCostGroupId) {
        this.pkCostGroupId = pkCostGroupId;
    }

    public MasterCostGroup(Integer pkCostGroupId, String costGroupName, String costGroupCode) {
        this.pkCostGroupId = pkCostGroupId;
        this.costGroupName = costGroupName;
        this.costGroupCode = costGroupCode;
    }

    public Integer getPkCostGroupId() {
        return pkCostGroupId;
    }

    public void setPkCostGroupId(Integer pkCostGroupId) {
        this.pkCostGroupId = pkCostGroupId;
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

    public String getCostGroupName() {
        return costGroupName;
    }

    public void setCostGroupName(String costGroupName) {
        this.costGroupName = costGroupName;
    }

    public String getCostGroupCode() {
        return costGroupCode;
    }

    public void setCostGroupCode(String costGroupCode) {
        this.costGroupCode = costGroupCode;
    }

    public Integer getFkStatusId() {
        return fkStatusId;
    }

    public void setFkStatusId(Integer fkStatusId) {
        this.fkStatusId = fkStatusId;
    }

    public String getCostGroupDesc() {
        return costGroupDesc;
    }

    public void setCostGroupDesc(String costGroupDesc) {
        this.costGroupDesc = costGroupDesc;
    }

    @XmlTransient
    public Collection<MasterCost> getMasterCostCollection() {
        return masterCostCollection;
    }

    public void setMasterCostCollection(Collection<MasterCost> masterCostCollection) {
        this.masterCostCollection = masterCostCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkCostGroupId != null ? pkCostGroupId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterCostGroup)) {
            return false;
        }
        MasterCostGroup other = (MasterCostGroup) object;
        if ((this.pkCostGroupId == null && other.pkCostGroupId != null) || (this.pkCostGroupId != null && !this.pkCostGroupId.equals(other.pkCostGroupId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.cost.domain.MasterCostGroup[ pkCostGroupId=" + pkCostGroupId + " ]";
    }
    
}

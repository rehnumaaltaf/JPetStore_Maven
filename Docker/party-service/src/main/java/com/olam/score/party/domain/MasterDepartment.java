/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olam.score.party.domain;

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

/**
 *
 * @author Ramachandran_N02
 */
@Entity
@Table(name = "MASTER_DEPARTMENT")
@NamedQueries({
    @NamedQuery(name = "MasterDepartment.findAll", query = "SELECT m FROM MasterDepartment m")})
public class MasterDepartment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_DEPT_ID")
    private Integer pkDeptId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "DEPT_CODE")
    private String deptCode;
    @Size(max = 200)
    @Column(name = "DEPT_NAME")
    private String deptName;
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
    @OneToMany(mappedBy = "fkDeptId")
    private Collection<MasterPartyContact> masterPartyContactCollection;

    public MasterDepartment() {
    }

    public MasterDepartment(Integer pkDeptId) {
        this.pkDeptId = pkDeptId;
    }

    public MasterDepartment(Integer pkDeptId, String deptCode) {
        this.pkDeptId = pkDeptId;
        this.deptCode = deptCode;
    }

    public Integer getPkDeptId() {
        return pkDeptId;
    }

    public void setPkDeptId(Integer pkDeptId) {
        this.pkDeptId = pkDeptId;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
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

    public Collection<MasterPartyContact> getMasterPartyContactCollection() {
        return masterPartyContactCollection;
    }

    public void setMasterPartyContactCollection(Collection<MasterPartyContact> masterPartyContactCollection) {
        this.masterPartyContactCollection = masterPartyContactCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkDeptId != null ? pkDeptId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterDepartment)) {
            return false;
        }
        MasterDepartment other = (MasterDepartment) object;
        if ((this.pkDeptId == null && other.pkDeptId != null) || (this.pkDeptId != null && !this.pkDeptId.equals(other.pkDeptId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.party.domain.MasterDepartment[ pkDeptId=" + pkDeptId + " ]";
    }
    
}

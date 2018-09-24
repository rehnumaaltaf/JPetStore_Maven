/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olam.score.cost.domain;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ramachandran_n02
 */
@Entity
@Table(name = "MASTER_COST_DEF_VALUE_TYPE_REF", catalog = "SCORE_DEV_002", schema = "cost")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterCostDefValueTypeRef.findAll", query = "SELECT m FROM MasterCostDefValueTypeRef m")})
public class MasterCostDefValueTypeRef implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_COST_DEF_VALUE_TYPE_ID")
    private Integer pkCostDefValueTypeId;
    @Size(max = 100)
    @Column(name = "CREATED_BY")
    private String createdBy;
    @Size(max = 100)
    @Column(name = "MODIFIED_BY")
    private String modifiedBy;
   //@Size(max = 200)
    /*@Column(name = "COST_DEF_VALUE_TYPE_NAME")
    private String costDefValueTypeName;*/
    /*@Size(max = 20)
    @Column(name = "COST_DEF_VALUE_TYPE_CODE")
    private String costDefValueTypeCode;*/
    @Column(name = "FK_STATUS_ID")
    private Integer fkStatusId;
    @OneToMany(mappedBy = "fkCostDefValueTypeId")
    private Collection<MasterCost> masterCostCollection;

    public MasterCostDefValueTypeRef() {
    }

    public MasterCostDefValueTypeRef(Integer pkCostDefValueTypeId) {
        this.pkCostDefValueTypeId = pkCostDefValueTypeId;
    }

    public Integer getPkCostDefValueTypeId() {
        return pkCostDefValueTypeId;
    }

    public void setPkCostDefValueTypeId(Integer pkCostDefValueTypeId) {
        this.pkCostDefValueTypeId = pkCostDefValueTypeId;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    

    /*public String getCostDefValueTypeName() {
		return costDefValueTypeName;
	}

	public void setCostDefValueTypeName(String costDefValueTypeName) {
		this.costDefValueTypeName = costDefValueTypeName;
	}*/

	/*public String getCostDefValueTypeCode() {
		return costDefValueTypeCode;
	}

	public void setCostDefValueTypeCode(String costDefValueTypeCode) {
		this.costDefValueTypeCode = costDefValueTypeCode;
	}*/

	public Integer getFkStatusId() {
        return fkStatusId;
    }

    public void setFkStatusId(Integer fkStatusId) {
        this.fkStatusId = fkStatusId;
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
        hash += (pkCostDefValueTypeId != null ? pkCostDefValueTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterCostDefValueTypeRef)) {
            return false;
        }
        MasterCostDefValueTypeRef other = (MasterCostDefValueTypeRef) object;
        if ((this.pkCostDefValueTypeId == null && other.pkCostDefValueTypeId != null) || (this.pkCostDefValueTypeId != null && !this.pkCostDefValueTypeId.equals(other.pkCostDefValueTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.cost.domain.MasterCostDefValueTypeRef[ pkCostDefValueTypeId=" + pkCostDefValueTypeId + " ]";
    }
    
}

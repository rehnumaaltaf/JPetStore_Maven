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
@Table(name = "MASTER_WH_COST_TYPE_REF", catalog = "SCORE_DEV_002", schema = "cost")
@XmlRootElement
/*@NamedQueries({
    @NamedQuery(name = "MasterWhCostType.findAll", query = "SELECT m FROM MasterWhCostType m")})*/
public class MasterWhCostTypeRef implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="WH_COST_TYPE_REF_ID_SEQ")
   	@SequenceGenerator(name="WH_COST_TYPE_REF_ID_SEQ", sequenceName="cost.WH_COST_TYPE_REF_ID_SEQ",initialValue=1,allocationSize=1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_WH_COST_TYPE_REF_ID")
    private Integer pkWhCostTypeId;
    @Size(max = 200)
    @Column(name = "WH_COST_TYPE_NAME")
    private String whCostTypeName;
    @Size(max = 20)
    @Column(name = "WH_COST_TYPE_CODE")
    private String whCostTypeCode;
    @Size(max = 200)
    @Column(name = "WH_COST_TYPE_FULLNAME")
    private String whCostTypeFullname;
    @Column(name = "FK_STATUS_ID")
    private Integer fkStatusId;
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
   /* @OneToMany(mappedBy = "fkWhCostTypeId")
    private Collection<MasterWhCost> masterWhCostCollection;*/

    public MasterWhCostTypeRef() {
    }

    public MasterWhCostTypeRef(Integer pkWhCostTypeId) {
        this.pkWhCostTypeId = pkWhCostTypeId;
    }

    public Integer getPkWhCostTypeId() {
        return pkWhCostTypeId;
    }

    public void setPkWhCostTypeId(Integer pkWhCostTypeId) {
        this.pkWhCostTypeId = pkWhCostTypeId;
    }

    public String getWhCostTypeName() {
        return whCostTypeName;
    }

    public void setWhCostTypeName(String whCostTypeName) {
        this.whCostTypeName = whCostTypeName;
    }

    public String getWhCostTypeCode() {
        return whCostTypeCode;
    }

    public void setWhCostTypeCode(String whCostTypeCode) {
        this.whCostTypeCode = whCostTypeCode;
    }

    public String getWhCostTypeFullname() {
        return whCostTypeFullname;
    }

    public void setWhCostTypeFullname(String whCostTypeFullname) {
        this.whCostTypeFullname = whCostTypeFullname;
    }

    public Integer getFkStatusId() {
        return fkStatusId;
    }

    public void setFkStatusId(Integer fkStatusId) {
        this.fkStatusId = fkStatusId;
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
/*
    @XmlTransient
    public Collection<MasterWhCost> getMasterWhCostCollection() {
        return masterWhCostCollection;
    }

    public void setMasterWhCostCollection(Collection<MasterWhCost> masterWhCostCollection) {
        this.masterWhCostCollection = masterWhCostCollection;
    }*/

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkWhCostTypeId != null ? pkWhCostTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterWhCostTypeRef)) {
            return false;
        }
        MasterWhCostTypeRef other = (MasterWhCostTypeRef) object;
        if ((this.pkWhCostTypeId == null && other.pkWhCostTypeId != null) || (this.pkWhCostTypeId != null && !this.pkWhCostTypeId.equals(other.pkWhCostTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.cost.domain.MasterWhCostType[ pkWhCostTypeId=" + pkWhCostTypeId + " ]";
    }
    
}

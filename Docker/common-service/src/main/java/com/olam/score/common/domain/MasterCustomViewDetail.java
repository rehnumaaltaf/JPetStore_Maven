/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olam.score.common.domain;

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
 * @author Ramachandran_N02
 */
@Entity
@Table(name = "MASTER_CUSTOM_VIEW_DETAIL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterCustomViewDetail.findAll", query = "SELECT m FROM MasterCustomViewDetail m")
    , @NamedQuery(name = "MasterCustomViewDetail.findByPkCustomViewDetailId", query = "SELECT m FROM MasterCustomViewDetail m WHERE m.pkCustomViewDetailId = :pkCustomViewDetailId")
    , @NamedQuery(name = "MasterCustomViewDetail.findByCustomViewDetailAttributeType", query = "SELECT m FROM MasterCustomViewDetail m WHERE m.customViewDetailAttributeType = :customViewDetailAttributeType")
    , @NamedQuery(name = "MasterCustomViewDetail.findByCustomViewDetailAttributeValue", query = "SELECT m FROM MasterCustomViewDetail m WHERE m.customViewDetailAttributeValue = :customViewDetailAttributeValue")
    , @NamedQuery(name = "MasterCustomViewDetail.findByCustomViewDetailOrder", query = "SELECT m FROM MasterCustomViewDetail m WHERE m.customViewDetailOrder = :customViewDetailOrder")
    , @NamedQuery(name = "MasterCustomViewDetail.findByCreatedBy", query = "SELECT m FROM MasterCustomViewDetail m WHERE m.createdBy = :createdBy")
    , @NamedQuery(name = "MasterCustomViewDetail.findByCreatedDate", query = "SELECT m FROM MasterCustomViewDetail m WHERE m.createdDate = :createdDate")
    , @NamedQuery(name = "MasterCustomViewDetail.findByModifiedBy", query = "SELECT m FROM MasterCustomViewDetail m WHERE m.modifiedBy = :modifiedBy")
    , @NamedQuery(name = "MasterCustomViewDetail.findByModifiedDate", query = "SELECT m FROM MasterCustomViewDetail m WHERE m.modifiedDate = :modifiedDate")})
public class MasterCustomViewDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_CUSTOM_VIEW_DETAIL_ID")
    private Integer pkCustomViewDetailId;
    @Size(max = 100)
    @Column(name = "CUSTOM_VIEW_DETAIL_ATTRIBUTE_TYPE")
    private String customViewDetailAttributeType;
    @Size(max = 100)
    @Column(name = "CUSTOM_VIEW_DETAIL_ATTRIBUTE_VALUE")
    private String customViewDetailAttributeValue;
    @Column(name = "CUSTOM_VIEW_DETAIL_ORDER")
    private Integer customViewDetailOrder;
    @Size(max = 20)
    @Column(name = "CREATED_BY")
    private String createdBy;
    @Column(name = "CREATED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Size(max = 20)
    @Column(name = "MODIFIED_BY")
    private String modifiedBy;
    @Column(name = "MODIFIED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;
    @JoinColumn(name = "FK_CUSTOM_VIEW_ID", referencedColumnName = "PK_CUSTOM_VIEW_ID")
    @ManyToOne(optional = false)
    private MasterCustomView fkCustomViewId;
    @Column(name = "FK_STATUS_ID")
    private Integer fkStatusId;

    public MasterCustomViewDetail() {
    }

    public MasterCustomViewDetail(Integer pkCustomViewDetailId) {
        this.pkCustomViewDetailId = pkCustomViewDetailId;
    }

    public Integer getPkCustomViewDetailId() {
        return pkCustomViewDetailId;
    }

    public void setPkCustomViewDetailId(Integer pkCustomViewDetailId) {
        this.pkCustomViewDetailId = pkCustomViewDetailId;
    }

    public String getCustomViewDetailAttributeType() {
        return customViewDetailAttributeType;
    }

    public void setCustomViewDetailAttributeType(String customViewDetailAttributeType) {
        this.customViewDetailAttributeType = customViewDetailAttributeType;
    }

    public String getCustomViewDetailAttributeValue() {
        return customViewDetailAttributeValue;
    }

    public void setCustomViewDetailAttributeValue(String customViewDetailAttributeValue) {
        this.customViewDetailAttributeValue = customViewDetailAttributeValue;
    }

    public Integer getCustomViewDetailOrder() {
        return customViewDetailOrder;
    }

    public void setCustomViewDetailOrder(Integer customViewDetailOrder) {
        this.customViewDetailOrder = customViewDetailOrder;
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

    public MasterCustomView getFkCustomViewId() {
        return fkCustomViewId;
    }

    public void setFkCustomViewId(MasterCustomView fkCustomViewId) {
        this.fkCustomViewId = fkCustomViewId;
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
        hash += (pkCustomViewDetailId != null ? pkCustomViewDetailId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterCustomViewDetail)) {
            return false;
        }
        MasterCustomViewDetail other = (MasterCustomViewDetail) object;
        if ((this.pkCustomViewDetailId == null && other.pkCustomViewDetailId != null) || (this.pkCustomViewDetailId != null && !this.pkCustomViewDetailId.equals(other.pkCustomViewDetailId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "controller.MasterCustomViewDetail[ pkCustomViewDetailId=" + pkCustomViewDetailId + " ]";
    }
    
}

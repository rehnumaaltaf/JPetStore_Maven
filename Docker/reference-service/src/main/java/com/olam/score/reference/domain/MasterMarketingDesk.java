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
@Table(name = "MASTER_MARKETING_DESK")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterMarketingDesk.findAll", query = "SELECT m FROM MasterMarketingDesk m")})
public class MasterMarketingDesk implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_MARKET_DESK_ID")
    private Integer pkMarketDeskId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "MARKET_DESK_CODE")
    private String marketDeskCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "MARKET_DESK_NAME")
    private String marketDeskName;
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
    private int fkStatusId;

    public MasterMarketingDesk() {
    }

    public MasterMarketingDesk(Integer pkMarketDeskId) {
        this.pkMarketDeskId = pkMarketDeskId;
    }

    public MasterMarketingDesk(Integer pkMarketDeskId, String marketDeskCode, String marketDeskName) {
        this.pkMarketDeskId = pkMarketDeskId;
        this.marketDeskCode = marketDeskCode;
        this.marketDeskName = marketDeskName;
    }

    public Integer getPkMarketDeskId() {
        return pkMarketDeskId;
    }

    public void setPkMarketDeskId(Integer pkMarketDeskId) {
        this.pkMarketDeskId = pkMarketDeskId;
    }

    public String getMarketDeskCode() {
        return marketDeskCode;
    }

    public void setMarketDeskCode(String marketDeskCode) {
        this.marketDeskCode = marketDeskCode;
    }

    public String getMarketDeskName() {
        return marketDeskName;
    }

    public void setMarketDeskName(String marketDeskName) {
        this.marketDeskName = marketDeskName;
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

    public int getFkStatusId() {
        return fkStatusId;
    }

    public void setFkStatusId(int fkStatusId) {
        this.fkStatusId = fkStatusId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkMarketDeskId != null ? pkMarketDeskId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterMarketingDesk)) {
            return false;
        }
        MasterMarketingDesk other = (MasterMarketingDesk) object;
        if ((this.pkMarketDeskId == null && other.pkMarketDeskId != null) || (this.pkMarketDeskId != null && !this.pkMarketDeskId.equals(other.pkMarketDeskId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.reference.domain.MasterMarketingDesk[ pkMarketDeskId=" + pkMarketDeskId + " ]";
    }
    
}

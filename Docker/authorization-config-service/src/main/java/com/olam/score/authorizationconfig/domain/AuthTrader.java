/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olam.score.authorizationconfig.domain;

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

/**
 *
 * @author Ramachandran_N02
 */
@Entity
@Table(name = "AUTH_TRADER")
@NamedQueries({
    @NamedQuery(name = "AuthTrader.findAll", query = "SELECT a FROM AuthTrader a")})
public class AuthTrader implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_TRADER_ID")
    private Integer pkTraderId;
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
    @JoinColumn(name = "FK_APP_USER_ID", referencedColumnName = "PK_APP_USER_ID")
    @ManyToOne
    private AuthAppUser fkAppUserId;

    public AuthTrader() {
    }

    public AuthTrader(Integer pkTraderId) {
        this.pkTraderId = pkTraderId;
    }

    public Integer getPkTraderId() {
        return pkTraderId;
    }

    public void setPkTraderId(Integer pkTraderId) {
        this.pkTraderId = pkTraderId;
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

    public AuthAppUser getFkAppUserId() {
        return fkAppUserId;
    }

    public void setFkAppUserId(AuthAppUser fkAppUserId) {
        this.fkAppUserId = fkAppUserId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkTraderId != null ? pkTraderId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AuthTrader)) {
            return false;
        }
        AuthTrader other = (AuthTrader) object;
        if ((this.pkTraderId == null && other.pkTraderId != null) || (this.pkTraderId != null && !this.pkTraderId.equals(other.pkTraderId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.authconfig.domain.AuthTrader[ pkTraderId=" + pkTraderId + " ]";
    }
    
}

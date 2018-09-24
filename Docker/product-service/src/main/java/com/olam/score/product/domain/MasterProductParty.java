/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olam.score.product.domain;

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
@Table(name = "MASTER_PRODUCT_PARTY",  schema = "product")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterProductParty.findAll", query = "SELECT m FROM MasterProductParty m")})
public class MasterProductParty implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FK_MASTER_PARTY")
    private int fkMasterParty;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "PK_PROD_PARTY_MAP_ID")
    private String pkProdPartyMapId;
    @Size(max = 100)
    @Column(name = "PROD_CREATED_BY")
    private String prodCreatedBy;
    @Column(name = "PROD_CREATED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date prodCreatedDate;
    @Size(max = 100)
    @Column(name = "PROD_MODIFIED_BY")
    private String prodModifiedBy;
    @Column(name = "PROD_MODIFIED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date prodModifiedDate;
    @JoinColumn(name = "FK_PROD_CODE", referencedColumnName = "PK_PROD_ID")
    @ManyToOne(optional = false)
    private MasterProduct fkProdCode;

    public MasterProductParty() {
    }

    public MasterProductParty(String pkProdPartyMapId) {
        this.pkProdPartyMapId = pkProdPartyMapId;
    }

    public MasterProductParty(String pkProdPartyMapId, int fkMasterParty) {
        this.pkProdPartyMapId = pkProdPartyMapId;
        this.fkMasterParty = fkMasterParty;
    }

    public int getFkMasterParty() {
        return fkMasterParty;
    }

    public void setFkMasterParty(int fkMasterParty) {
        this.fkMasterParty = fkMasterParty;
    }

    public String getPkProdPartyMapId() {
        return pkProdPartyMapId;
    }

    public void setPkProdPartyMapId(String pkProdPartyMapId) {
        this.pkProdPartyMapId = pkProdPartyMapId;
    }

    public String getProdCreatedBy() {
        return prodCreatedBy;
    }

    public void setProdCreatedBy(String prodCreatedBy) {
        this.prodCreatedBy = prodCreatedBy;
    }

    public Date getProdCreatedDate() {
        return prodCreatedDate;
    }

    public void setProdCreatedDate(Date prodCreatedDate) {
        this.prodCreatedDate = prodCreatedDate;
    }

    public String getProdModifiedBy() {
        return prodModifiedBy;
    }

    public void setProdModifiedBy(String prodModifiedBy) {
        this.prodModifiedBy = prodModifiedBy;
    }

    public Date getProdModifiedDate() {
        return prodModifiedDate;
    }

    public void setProdModifiedDate(Date prodModifiedDate) {
        this.prodModifiedDate = prodModifiedDate;
    }

    public MasterProduct getFkProdCode() {
        return fkProdCode;
    }

    public void setFkProdCode(MasterProduct fkProdCode) {
        this.fkProdCode = fkProdCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkProdPartyMapId != null ? pkProdPartyMapId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterProductParty)) {
            return false;
        }
        MasterProductParty other = (MasterProductParty) object;
        if ((this.pkProdPartyMapId == null && other.pkProdPartyMapId != null) || (this.pkProdPartyMapId != null && !this.pkProdPartyMapId.equals(other.pkProdPartyMapId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.product.domain.MasterProductParty[ pkProdPartyMapId=" + pkProdPartyMapId + " ]";
    }
    
}

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
@Table(name = "MASTER_PRODUCT_GL_MAPPING",  schema = "product")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterProductGlMapping.findAll", query = "SELECT m FROM MasterProductGlMapping m")})
public class MasterProductGlMapping implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_PROD_GL_MAPPING_ID")
    private Integer pkProdGlMappingId;
    @Size(max = 200)
    @Column(name = "PROD_GL_MAPPING_NAME")
    private String prodGlMappingName;
    @Size(max = 20)
    @Column(name = "PROD_GL_MAPPING_CODE")
    private String prodGlMappingCode;
    @Size(max = 1000)
    @Column(name = "PROD_GL_MAPPING_DESC")
    private String prodGlMappingDesc;
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
    @Column(name = "FK_PARTY_ID")
    private Integer fkPartyId;
    @Column(name = "FK_GL_ID")
    private Integer fkGlId;
    @Column(name = "FK_STATUS_ID")
    private Integer fkStatusId;
    @JoinColumn(name = "FK_GRADE_ID", referencedColumnName = "PK_GRADE_ID")
    @ManyToOne
    private MasterGrade fkGradeId;

    public MasterProductGlMapping() {
    }

    public MasterProductGlMapping(Integer pkProdGlMappingId) {
        this.pkProdGlMappingId = pkProdGlMappingId;
    }

    public Integer getPkProdGlMappingId() {
        return pkProdGlMappingId;
    }

    public void setPkProdGlMappingId(Integer pkProdGlMappingId) {
        this.pkProdGlMappingId = pkProdGlMappingId;
    }

    public String getProdGlMappingName() {
        return prodGlMappingName;
    }

    public void setProdGlMappingName(String prodGlMappingName) {
        this.prodGlMappingName = prodGlMappingName;
    }

    public String getProdGlMappingCode() {
        return prodGlMappingCode;
    }

    public void setProdGlMappingCode(String prodGlMappingCode) {
        this.prodGlMappingCode = prodGlMappingCode;
    }

    public String getProdGlMappingDesc() {
        return prodGlMappingDesc;
    }

    public void setProdGlMappingDesc(String prodGlMappingDesc) {
        this.prodGlMappingDesc = prodGlMappingDesc;
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

    public Integer getFkPartyId() {
        return fkPartyId;
    }

    public void setFkPartyId(Integer fkPartyId) {
        this.fkPartyId = fkPartyId;
    }

    public Integer getFkGlId() {
        return fkGlId;
    }

    public void setFkGlId(Integer fkGlId) {
        this.fkGlId = fkGlId;
    }

    public Integer getFkStatusId() {
        return fkStatusId;
    }

    public void setFkStatusId(Integer fkStatusId) {
        this.fkStatusId = fkStatusId;
    }

    public MasterGrade getFkGradeId() {
        return fkGradeId;
    }

    public void setFkGradeId(MasterGrade fkGradeId) {
        this.fkGradeId = fkGradeId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkProdGlMappingId != null ? pkProdGlMappingId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterProductGlMapping)) {
            return false;
        }
        MasterProductGlMapping other = (MasterProductGlMapping) object;
        if ((this.pkProdGlMappingId == null && other.pkProdGlMappingId != null) || (this.pkProdGlMappingId != null && !this.pkProdGlMappingId.equals(other.pkProdGlMappingId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.product.domain.MasterProductGlMapping[ pkProdGlMappingId=" + pkProdGlMappingId + " ]";
    }
    
}

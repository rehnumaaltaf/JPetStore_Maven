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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
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
@Table(name = "MASTER_PRODUCT_ARBITRATION_MAPPING",  schema = "product")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterProductArbitrationMapping.findAll", query = "SELECT m FROM MasterProductArbitrationMapping m")})
public class MasterProductArbitrationMapping implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MASTER_PRODUCT_ARBITRATION_GENERATOR")
	@SequenceGenerator(name="MASTER_PRODUCT_ARBITRATION_GENERATOR", sequenceName="product.PRODUCT_CLASSIFICATION_REF_ID_SEQ",allocationSize=1)
    @NotNull
    @Column(name = "PK_PROD_ARBITRATION_MAPPING_ID")
    private Integer pkProdArbitrationMappingId;
    @Column(name = "FK_ARBITRATION_AGENCY_ID")
    private Integer fkArbitrationAgencyId;
    @Size(max = 100)
    @Column(name = "CREATED_BY")
    private String createdBy;
    @Column(name = "PROD_ARBITRATION_AGENCY_CR3011")
    @Temporal(TemporalType.TIMESTAMP)
    private Date prodArbitrationAgencyCr3011;
    @Size(max = 100)
    @Column(name = "MODIFIED_BY")
    private String modifiedBy;
    @Column(name = "PROD_ARBITRATION_AGENCY_MO3013")
    @Temporal(TemporalType.TIMESTAMP)
    private Date prodArbitrationAgencyMo3013;
    @Column(name = "FK_STATUS_ID")
    private Integer fkStatusId;
    @JoinColumn(name = "FK_PROD_ID", referencedColumnName = "PK_PROD_ID")
    @ManyToOne
    private MasterProduct fkProdId;

    public MasterProductArbitrationMapping() {
    }

    public MasterProductArbitrationMapping(Integer pkProdArbitrationMappingId) {
        this.pkProdArbitrationMappingId = pkProdArbitrationMappingId;
    }

    public Integer getPkProdArbitrationMappingId() {
        return pkProdArbitrationMappingId;
    }

    public void setPkProdArbitrationMappingId(Integer pkProdArbitrationMappingId) {
        this.pkProdArbitrationMappingId = pkProdArbitrationMappingId;
    }

    public Integer getFkArbitrationAgencyId() {
        return fkArbitrationAgencyId;
    }

    public void setFkArbitrationAgencyId(Integer fkArbitrationAgencyId) {
        this.fkArbitrationAgencyId = fkArbitrationAgencyId;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getProdArbitrationAgencyCr3011() {
        return prodArbitrationAgencyCr3011;
    }

    public void setProdArbitrationAgencyCr3011(Date prodArbitrationAgencyCr3011) {
        this.prodArbitrationAgencyCr3011 = prodArbitrationAgencyCr3011;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getProdArbitrationAgencyMo3013() {
        return prodArbitrationAgencyMo3013;
    }

    public void setProdArbitrationAgencyMo3013(Date prodArbitrationAgencyMo3013) {
        this.prodArbitrationAgencyMo3013 = prodArbitrationAgencyMo3013;
    }

    public Integer getFkStatusId() {
        return fkStatusId;
    }

    public void setFkStatusId(Integer fkStatusId) {
        this.fkStatusId = fkStatusId;
    }

    public MasterProduct getFkProdId() {
        return fkProdId;
    }

    public void setFkProdId(MasterProduct fkProdId) {
        this.fkProdId = fkProdId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkProdArbitrationMappingId != null ? pkProdArbitrationMappingId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterProductArbitrationMapping)) {
            return false;
        }
        MasterProductArbitrationMapping other = (MasterProductArbitrationMapping) object;
        if ((this.pkProdArbitrationMappingId == null && other.pkProdArbitrationMappingId != null) || (this.pkProdArbitrationMappingId != null && !this.pkProdArbitrationMappingId.equals(other.pkProdArbitrationMappingId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.product.domain.MasterProductArbitrationMapping[ pkProdArbitrationMappingId=" + pkProdArbitrationMappingId + " ]";
    }
    
}

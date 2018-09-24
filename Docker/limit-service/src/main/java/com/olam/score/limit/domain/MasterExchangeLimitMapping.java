/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olam.score.limit.domain;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "MASTER_EXCHANGE_LIMIT_MAPPING",  schema = "limit")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterExchangeLimitMapping.findAll", query = "SELECT m FROM MasterExchangeLimitMapping m")})
public class MasterExchangeLimitMapping implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_XCHNG_LIMIT_MAPPING")
    private Integer pkXchngLimitMapping;
    @Column(name = "FK_PROD_ID")
    private Integer fkProdId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "XCHNG_MAPPING_TOTAL_LIMIT")
    private BigDecimal xchngMappingTotalLimit;
    @Column(name = "XCHNG_MAPPING_CURRENT_MONTH_LIMIT")
    private BigDecimal xchngMappingCurrentMonthLimit;
    @Column(name = "XCHNG_MAPPING_FORWARD_MONTH_LIMIT")
    private BigDecimal xchngMappingForwardMonthLimit;
    @Column(name = "XCHNG_MAPPING_STRUCTURE_LIMIT")
    private BigDecimal xchngMappingStructureLimit;
    @Column(name = "XCHNG_LIMIT_MAPPING_VALID_FROM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date xchngLimitMappingValidFrom;
    @Column(name = "XCHNG_LIMIT_MAPPING_VALID_TO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date xchngLimitMappingValidTo;
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
    @Column(name = "FK_XCHNG_ID")
    private Integer fkXchngId;
    @Column(name = "FK_STATUS_ID")
    private Integer fkStatusId;
    @JoinColumn(name = "FK_EXCHANGE_LIMIT_ID", referencedColumnName = "PK_EXCHANGE_LIMIT_ID")
    @ManyToOne
    private MasterExchangeLimit fkExchangeLimitId;

    public MasterExchangeLimitMapping() {
    }

    public MasterExchangeLimitMapping(Integer pkXchngLimitMapping) {
        this.pkXchngLimitMapping = pkXchngLimitMapping;
    }

    public Integer getPkXchngLimitMapping() {
        return pkXchngLimitMapping;
    }

    public void setPkXchngLimitMapping(Integer pkXchngLimitMapping) {
        this.pkXchngLimitMapping = pkXchngLimitMapping;
    }

    public Integer getFkProdId() {
        return fkProdId;
    }

    public void setFkProdId(Integer fkProdId) {
        this.fkProdId = fkProdId;
    }

    public BigDecimal getXchngMappingTotalLimit() {
        return xchngMappingTotalLimit;
    }

    public void setXchngMappingTotalLimit(BigDecimal xchngMappingTotalLimit) {
        this.xchngMappingTotalLimit = xchngMappingTotalLimit;
    }

    public BigDecimal getXchngMappingCurrentMonthLimit() {
        return xchngMappingCurrentMonthLimit;
    }

    public void setXchngMappingCurrentMonthLimit(BigDecimal xchngMappingCurrentMonthLimit) {
        this.xchngMappingCurrentMonthLimit = xchngMappingCurrentMonthLimit;
    }

    public BigDecimal getXchngMappingForwardMonthLimit() {
        return xchngMappingForwardMonthLimit;
    }

    public void setXchngMappingForwardMonthLimit(BigDecimal xchngMappingForwardMonthLimit) {
        this.xchngMappingForwardMonthLimit = xchngMappingForwardMonthLimit;
    }

    public BigDecimal getXchngMappingStructureLimit() {
        return xchngMappingStructureLimit;
    }

    public void setXchngMappingStructureLimit(BigDecimal xchngMappingStructureLimit) {
        this.xchngMappingStructureLimit = xchngMappingStructureLimit;
    }

    public Date getXchngLimitMappingValidFrom() {
        return xchngLimitMappingValidFrom;
    }

    public void setXchngLimitMappingValidFrom(Date xchngLimitMappingValidFrom) {
        this.xchngLimitMappingValidFrom = xchngLimitMappingValidFrom;
    }

    public Date getXchngLimitMappingValidTo() {
        return xchngLimitMappingValidTo;
    }

    public void setXchngLimitMappingValidTo(Date xchngLimitMappingValidTo) {
        this.xchngLimitMappingValidTo = xchngLimitMappingValidTo;
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

    public Integer getFkXchngId() {
        return fkXchngId;
    }

    public void setFkXchngId(Integer fkXchngId) {
        this.fkXchngId = fkXchngId;
    }

    public Integer getFkStatusId() {
        return fkStatusId;
    }

    public void setFkStatusId(Integer fkStatusId) {
        this.fkStatusId = fkStatusId;
    }

    public MasterExchangeLimit getFkExchangeLimitId() {
        return fkExchangeLimitId;
    }

    public void setFkExchangeLimitId(MasterExchangeLimit fkExchangeLimitId) {
        this.fkExchangeLimitId = fkExchangeLimitId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkXchngLimitMapping != null ? pkXchngLimitMapping.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterExchangeLimitMapping)) {
            return false;
        }
        MasterExchangeLimitMapping other = (MasterExchangeLimitMapping) object;
        if ((this.pkXchngLimitMapping == null && other.pkXchngLimitMapping != null) || (this.pkXchngLimitMapping != null && !this.pkXchngLimitMapping.equals(other.pkXchngLimitMapping))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.limit.domain.MasterExchangeLimitMapping[ pkXchngLimitMapping=" + pkXchngLimitMapping + " ]";
    }
    
}

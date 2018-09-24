/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olam.score.forex.domain;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "MASTER_FOREX_FORWARD_TENOR",  schema = "forex")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterForexForwardTenor.findAll", query = "SELECT m FROM MasterForexForwardTenor m")})
public class MasterForexForwardTenor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_FOREX_FORWARD_TENOR_ID")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="FOREX_FORWARD_TENOR_ID_SEQ")
   	@SequenceGenerator(name="FOREX_FORWARD_TENOR_ID_SEQ", sequenceName="forex.FOREX_FORWARD_TENOR_ID_SEQ",initialValue=1,allocationSize=1)
    private Integer pkForexForwardTenorId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "FOREX_TICKER_CODE")
    private String forexTickerCode;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "FOREX_SPAN")
    private BigDecimal forexSpan;
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
    @JoinColumn(name = "FK_FOREX_ID", referencedColumnName = "PK_FOREX_ID")
    @ManyToOne
    private MasterForex fkForexId;
    @JoinColumn(name = "FK_TENOR_DAY_TYPE_ID", referencedColumnName = "PK_TENOR_DAY_TYPE_ID")
    @ManyToOne
    private MasterTenorDayType fkTenorDayTypeId;
    @JoinColumn(name = "FK_TENOR_DURATION_TYPE_ID", referencedColumnName = "PK_TENOR_DURATION_TYPE_ID")
    @ManyToOne
    private MasterTenorDurationType fkTenorDurationTypeId;
    @JoinColumn(name = "FK_TENOR_TYPE_ID", referencedColumnName = "PK_TENOR_TYPE_ID")
    @ManyToOne(optional = false)
    private MasterTenorType fkTenorTypeId;

    public MasterForexForwardTenor() {
    }

    public MasterForexForwardTenor(Integer pkForexForwardTenorId) {
        this.pkForexForwardTenorId = pkForexForwardTenorId;
    }

    public MasterForexForwardTenor(Integer pkForexForwardTenorId, String forexTickerCode) {
        this.pkForexForwardTenorId = pkForexForwardTenorId;
        this.forexTickerCode = forexTickerCode;
    }

    public Integer getPkForexForwardTenorId() {
        return pkForexForwardTenorId;
    }

    public void setPkForexForwardTenorId(Integer pkForexForwardTenorId) {
        this.pkForexForwardTenorId = pkForexForwardTenorId;
    }

    public String getForexTickerCode() {
        return forexTickerCode;
    }

    public void setForexTickerCode(String forexTickerCode) {
        this.forexTickerCode = forexTickerCode;
    }

    public BigDecimal getForexSpan() {
        return forexSpan;
    }

    public void setForexSpan(BigDecimal forexSpan) {
        this.forexSpan = forexSpan;
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

    public MasterForex getFkForexId() {
        return fkForexId;
    }

    public void setFkForexId(MasterForex fkForexId) {
        this.fkForexId = fkForexId;
    }

    public MasterTenorDayType getFkTenorDayTypeId() {
        return fkTenorDayTypeId;
    }

    public void setFkTenorDayTypeId(MasterTenorDayType fkTenorDayTypeId) {
        this.fkTenorDayTypeId = fkTenorDayTypeId;
    }

    public MasterTenorDurationType getFkTenorDurationTypeId() {
        return fkTenorDurationTypeId;
    }

    public void setFkTenorDurationTypeId(MasterTenorDurationType fkTenorDurationTypeId) {
        this.fkTenorDurationTypeId = fkTenorDurationTypeId;
    }

    public MasterTenorType getFkTenorTypeId() {
        return fkTenorTypeId;
    }

    public void setFkTenorTypeId(MasterTenorType fkTenorTypeId) {
        this.fkTenorTypeId = fkTenorTypeId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkForexForwardTenorId != null ? pkForexForwardTenorId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterForexForwardTenor)) {
            return false;
        }
        MasterForexForwardTenor other = (MasterForexForwardTenor) object;
        if ((this.pkForexForwardTenorId == null && other.pkForexForwardTenorId != null) || (this.pkForexForwardTenorId != null && !this.pkForexForwardTenorId.equals(other.pkForexForwardTenorId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.forex.domain.MasterForexForwardTenor[ pkForexForwardTenorId=" + pkForexForwardTenorId + " ]";
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olam.score.marketdata.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "MASTER_FUTURE_RATES",  schema = "marketdata")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterFutureRates.findAll", query = "SELECT m FROM MasterFutureRates m")})
public class MasterFutureRates implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_\u001fFUTURE_RATES_ID")
    private Integer pkFutureRatesId;
    @Column(name = "PRICE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date priceDate;
    @Size(max = 20)
    @Column(name = "TICKER_CODE")
    private String tickerCode;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "OPEN")
    private BigDecimal open;
    @Column(name = "HIGH")
    private BigDecimal high;
    @Column(name = "LOW")
    private BigDecimal low;
    @Column(name = "CLOSE")
    private BigDecimal close;
    @Size(max = 20)
    @Column(name = "SOURCE")
    private String source;
    @Column(name = "SETTLE")
    private BigDecimal settle;
    @Size(max = 20)
    @Column(name = "FUTURE_RATES_STATUS")
    private String futureRatesStatus;
    @Column(name = "FK_FOREX_ID")
    private Integer fkForexId;
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
    @Column(name = "DELIVERY_MONTH")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deliveryMonth;
    @Column(name = "SOURCE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date sourceDate;
    @Column(name = "FK_CURRENCY_ID")
    private Integer fkCurrencyId;
    @Column(name = "FK_UOM_ID")
    private Integer fkUomId;
    @Column(name = "FK_STATUS_ID")
    private Integer fkStatusId;

    public MasterFutureRates() {
    }

    public MasterFutureRates(Integer pkFutureRatesId) {
        this.pkFutureRatesId = pkFutureRatesId;
    }

    public Integer getPkFutureRatesId() {
        return pkFutureRatesId;
    }

    public void setPkFutureRatesId(Integer pkFutureRatesId) {
        this.pkFutureRatesId = pkFutureRatesId;
    }

    public Date getPriceDate() {
        return priceDate;
    }

    public void setPriceDate(Date priceDate) {
        this.priceDate = priceDate;
    }

    public String getTickerCode() {
        return tickerCode;
    }

    public void setTickerCode(String tickerCode) {
        this.tickerCode = tickerCode;
    }

    public BigDecimal getOpen() {
        return open;
    }

    public void setOpen(BigDecimal open) {
        this.open = open;
    }

    public BigDecimal getHigh() {
        return high;
    }

    public void setHigh(BigDecimal high) {
        this.high = high;
    }

    public BigDecimal getLow() {
        return low;
    }

    public void setLow(BigDecimal low) {
        this.low = low;
    }

    public BigDecimal getClose() {
        return close;
    }

    public void setClose(BigDecimal close) {
        this.close = close;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public BigDecimal getSettle() {
        return settle;
    }

    public void setSettle(BigDecimal settle) {
        this.settle = settle;
    }

    public String getFutureRatesStatus() {
        return futureRatesStatus;
    }

    public void setFutureRatesStatus(String futureRatesStatus) {
        this.futureRatesStatus = futureRatesStatus;
    }

    public Integer getFkForexId() {
        return fkForexId;
    }

    public void setFkForexId(Integer fkForexId) {
        this.fkForexId = fkForexId;
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

    public Date getDeliveryMonth() {
        return deliveryMonth;
    }

    public void setDeliveryMonth(Date deliveryMonth) {
        this.deliveryMonth = deliveryMonth;
    }

    public Date getSourceDate() {
        return sourceDate;
    }

    public void setSourceDate(Date sourceDate) {
        this.sourceDate = sourceDate;
    }

    public Integer getFkCurrencyId() {
        return fkCurrencyId;
    }

    public void setFkCurrencyId(Integer fkCurrencyId) {
        this.fkCurrencyId = fkCurrencyId;
    }

    public Integer getFkUomId() {
        return fkUomId;
    }

    public void setFkUomId(Integer fkUomId) {
        this.fkUomId = fkUomId;
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
        hash += (pkFutureRatesId != null ? pkFutureRatesId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterFutureRates)) {
            return false;
        }
        MasterFutureRates other = (MasterFutureRates) object;
        if ((this.pkFutureRatesId == null && other.pkFutureRatesId != null) || (this.pkFutureRatesId != null && !this.pkFutureRatesId.equals(other.pkFutureRatesId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.marketdata.domain.MasterFutureRates[ pkFutureRatesId=" + pkFutureRatesId + " ]";
    }
    
}

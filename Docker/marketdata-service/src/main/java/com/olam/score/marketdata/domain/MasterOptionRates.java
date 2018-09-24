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
@Table(name = "MASTER_OPTION_RATES",  schema = "marketdata")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterOptionRates.findAll", query = "SELECT m FROM MasterOptionRates m")})
public class MasterOptionRates implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_OPTION_RATES_ID")
    private Integer pkOptionRatesId;
    @Size(max = 20)
    @Column(name = "OPTION_RATES_TICKER_CODE")
    private String optionRatesTickerCode;
    @Column(name = "OPTION_RATES_PRICE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date optionRatesPriceDate;
    @Column(name = "OPTION_RATES_DELIVERY_MONTH")
    @Temporal(TemporalType.TIMESTAMP)
    private Date optionRatesDeliveryMonth;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "OPTION_RATES_STRIKE_PRICE")
    private BigDecimal optionRatesStrikePrice;
    @Column(name = "OPTION_RATES_CALL_OPEN")
    private BigDecimal optionRatesCallOpen;
    @Column(name = "OPTION_RATES_CALL_HIGH")
    private BigDecimal optionRatesCallHigh;
    @Column(name = "OPTION_RATES_CALL_LOW")
    private BigDecimal optionRatesCallLow;
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
    @Column(name = "OPTION_RATES_CALL_SETTLE")
    private BigDecimal optionRatesCallSettle;
    @Column(name = "OPTION_RATES_CALL_CLOSE")
    private BigDecimal optionRatesCallClose;
    @Size(max = 20)
    @Column(name = "OPTION_RATES_CALL_OPTION_DELTA")
    private String optionRatesCallOptionDelta;
    @Column(name = "OPTION_RATES_PUT_OPEN")
    private BigDecimal optionRatesPutOpen;
    @Column(name = "OPTION_RATES_PUT_HIGH")
    private BigDecimal optionRatesPutHigh;
    @Column(name = "OPTION_RATES_PUT_LOW")
    private BigDecimal optionRatesPutLow;
    @Column(name = "OPTION_RATES_PUT_SETTLE")
    private BigDecimal optionRatesPutSettle;
    @Column(name = "OPTION_RATES_PUT_CLOSE")
    private BigDecimal optionRatesPutClose;
    @Size(max = 20)
    @Column(name = "OPTION_RATES_PUT_OPTION_DELTA")
    private String optionRatesPutOptionDelta;
    @Size(max = 20)
    @Column(name = "OPTION_RATES_STATUS")
    private String optionRatesStatus;
    @Size(max = 20)
    @Column(name = "OPTION_RATES_SOURCE")
    private String optionRatesSource;
    @Column(name = "OPTION_RATES_SOURCE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date optionRatesSourceDate;
    @Column(name = "FK_CURRENCY_ID")
    private Integer fkCurrencyId;
    @Column(name = "FK_UOM_ID")
    private Integer fkUomId;
    @Column(name = "FK_STATUS_ID")
    private Integer fkStatusId;

    public MasterOptionRates() {
    }

    public MasterOptionRates(Integer pkOptionRatesId) {
        this.pkOptionRatesId = pkOptionRatesId;
    }

    public Integer getPkOptionRatesId() {
        return pkOptionRatesId;
    }

    public void setPkOptionRatesId(Integer pkOptionRatesId) {
        this.pkOptionRatesId = pkOptionRatesId;
    }

    public String getOptionRatesTickerCode() {
        return optionRatesTickerCode;
    }

    public void setOptionRatesTickerCode(String optionRatesTickerCode) {
        this.optionRatesTickerCode = optionRatesTickerCode;
    }

    public Date getOptionRatesPriceDate() {
        return optionRatesPriceDate;
    }

    public void setOptionRatesPriceDate(Date optionRatesPriceDate) {
        this.optionRatesPriceDate = optionRatesPriceDate;
    }

    public Date getOptionRatesDeliveryMonth() {
        return optionRatesDeliveryMonth;
    }

    public void setOptionRatesDeliveryMonth(Date optionRatesDeliveryMonth) {
        this.optionRatesDeliveryMonth = optionRatesDeliveryMonth;
    }

    public BigDecimal getOptionRatesStrikePrice() {
        return optionRatesStrikePrice;
    }

    public void setOptionRatesStrikePrice(BigDecimal optionRatesStrikePrice) {
        this.optionRatesStrikePrice = optionRatesStrikePrice;
    }

    public BigDecimal getOptionRatesCallOpen() {
        return optionRatesCallOpen;
    }

    public void setOptionRatesCallOpen(BigDecimal optionRatesCallOpen) {
        this.optionRatesCallOpen = optionRatesCallOpen;
    }

    public BigDecimal getOptionRatesCallHigh() {
        return optionRatesCallHigh;
    }

    public void setOptionRatesCallHigh(BigDecimal optionRatesCallHigh) {
        this.optionRatesCallHigh = optionRatesCallHigh;
    }

    public BigDecimal getOptionRatesCallLow() {
        return optionRatesCallLow;
    }

    public void setOptionRatesCallLow(BigDecimal optionRatesCallLow) {
        this.optionRatesCallLow = optionRatesCallLow;
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

    public BigDecimal getOptionRatesCallSettle() {
        return optionRatesCallSettle;
    }

    public void setOptionRatesCallSettle(BigDecimal optionRatesCallSettle) {
        this.optionRatesCallSettle = optionRatesCallSettle;
    }

    public BigDecimal getOptionRatesCallClose() {
        return optionRatesCallClose;
    }

    public void setOptionRatesCallClose(BigDecimal optionRatesCallClose) {
        this.optionRatesCallClose = optionRatesCallClose;
    }

    public String getOptionRatesCallOptionDelta() {
        return optionRatesCallOptionDelta;
    }

    public void setOptionRatesCallOptionDelta(String optionRatesCallOptionDelta) {
        this.optionRatesCallOptionDelta = optionRatesCallOptionDelta;
    }

    public BigDecimal getOptionRatesPutOpen() {
        return optionRatesPutOpen;
    }

    public void setOptionRatesPutOpen(BigDecimal optionRatesPutOpen) {
        this.optionRatesPutOpen = optionRatesPutOpen;
    }

    public BigDecimal getOptionRatesPutHigh() {
        return optionRatesPutHigh;
    }

    public void setOptionRatesPutHigh(BigDecimal optionRatesPutHigh) {
        this.optionRatesPutHigh = optionRatesPutHigh;
    }

    public BigDecimal getOptionRatesPutLow() {
        return optionRatesPutLow;
    }

    public void setOptionRatesPutLow(BigDecimal optionRatesPutLow) {
        this.optionRatesPutLow = optionRatesPutLow;
    }

    public BigDecimal getOptionRatesPutSettle() {
        return optionRatesPutSettle;
    }

    public void setOptionRatesPutSettle(BigDecimal optionRatesPutSettle) {
        this.optionRatesPutSettle = optionRatesPutSettle;
    }

    public BigDecimal getOptionRatesPutClose() {
        return optionRatesPutClose;
    }

    public void setOptionRatesPutClose(BigDecimal optionRatesPutClose) {
        this.optionRatesPutClose = optionRatesPutClose;
    }

    public String getOptionRatesPutOptionDelta() {
        return optionRatesPutOptionDelta;
    }

    public void setOptionRatesPutOptionDelta(String optionRatesPutOptionDelta) {
        this.optionRatesPutOptionDelta = optionRatesPutOptionDelta;
    }

    public String getOptionRatesStatus() {
        return optionRatesStatus;
    }

    public void setOptionRatesStatus(String optionRatesStatus) {
        this.optionRatesStatus = optionRatesStatus;
    }

    public String getOptionRatesSource() {
        return optionRatesSource;
    }

    public void setOptionRatesSource(String optionRatesSource) {
        this.optionRatesSource = optionRatesSource;
    }

    public Date getOptionRatesSourceDate() {
        return optionRatesSourceDate;
    }

    public void setOptionRatesSourceDate(Date optionRatesSourceDate) {
        this.optionRatesSourceDate = optionRatesSourceDate;
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
        hash += (pkOptionRatesId != null ? pkOptionRatesId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterOptionRates)) {
            return false;
        }
        MasterOptionRates other = (MasterOptionRates) object;
        if ((this.pkOptionRatesId == null && other.pkOptionRatesId != null) || (this.pkOptionRatesId != null && !this.pkOptionRatesId.equals(other.pkOptionRatesId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.marketdata.domain.MasterOptionRates[ pkOptionRatesId=" + pkOptionRatesId + " ]";
    }
    
}

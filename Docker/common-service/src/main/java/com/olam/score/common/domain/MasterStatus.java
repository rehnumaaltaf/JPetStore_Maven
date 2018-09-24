/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olam.score.common.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ramachandran_n02
 */
@Entity
@Table(name = "MASTER_STATUS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterStatus.findAll", query = "SELECT m FROM MasterStatus m")})
public class MasterStatus implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_STATUS_ID")
    private Integer pkStatusId;
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
    @Size(max = 200)
    @Column(name = "STATUS_NAME")
    private String statusName;
    @Size(max = 20)
    @Column(name = "STATUS_CODE")
    private String statusCode;
    @Size(max = 20)
    @Column(name = "STATUS_TYPE")
    private String statusType;
    /*@OneToMany(mappedBy = "fkStatusId")
    private Collection<MasterUom> masterUomCollection;
    @OneToMany(mappedBy = "fkStatusId")
    private Collection<MasterHolidayList> masterHolidayListCollection;
    @OneToMany(mappedBy = "fkStatusId")
    private Collection<MasterTransportModeRef> masterTransportModeRefCollection;
    @OneToMany(mappedBy = "fkStatusId")
    private Collection<MasterLanguage> masterLanguageCollection;
    @OneToMany(mappedBy = "fkStatusId")
    private Collection<MasterMarketingDesk> masterMarketingDeskCollection;
    @OneToMany(mappedBy = "fkStatusId")
    private Collection<MasterPaymentNegotiation> masterPaymentNegotiationCollection;
    @OneToMany(mappedBy = "fkStatusId")
    private Collection<MasterPaymentNegotiationType> masterPaymentNegotiationTypeCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkErpStatusId")
    private Collection<MasterFinancialCalenderMapping> masterFinancialCalenderMappingCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkStatusId")
    private Collection<MasterFinancialCalenderMapping> masterFinancialCalenderMappingCollection1;
    @OneToMany(mappedBy = "fkStatusId")
    private Collection<MasterFinancialCalender> masterFinancialCalenderCollection;
    @OneToMany(mappedBy = "fkStatusId")
    private Collection<MasterCurrency> masterCurrencyCollection;
    @OneToMany(mappedBy = "fkStatusId")
    private Collection<MasterHolidayCalender> masterHolidayCalenderCollection;*/

    public MasterStatus() {
    }

    public MasterStatus(Integer pkStatusId) {
        this.pkStatusId = pkStatusId;
    }

    public Integer getPkStatusId() {
        return pkStatusId;
    }

    public void setPkStatusId(Integer pkStatusId) {
        this.pkStatusId = pkStatusId;
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

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusType() {
        return statusType;
    }

    public void setStatusType(String statusType) {
        this.statusType = statusType;
    }
/*
    @XmlTransient
    public Collection<MasterUom> getMasterUomCollection() {
        return masterUomCollection;
    }

    public void setMasterUomCollection(Collection<MasterUom> masterUomCollection) {
        this.masterUomCollection = masterUomCollection;
    }

    @XmlTransient
    public Collection<MasterHolidayList> getMasterHolidayListCollection() {
        return masterHolidayListCollection;
    }

    public void setMasterHolidayListCollection(Collection<MasterHolidayList> masterHolidayListCollection) {
        this.masterHolidayListCollection = masterHolidayListCollection;
    }

    @XmlTransient
    public Collection<MasterTransportModeRef> getMasterTransportModeRefCollection() {
        return masterTransportModeRefCollection;
    }

    public void setMasterTransportModeRefCollection(Collection<MasterTransportModeRef> masterTransportModeRefCollection) {
        this.masterTransportModeRefCollection = masterTransportModeRefCollection;
    }

    @XmlTransient
    public Collection<MasterLanguage> getMasterLanguageCollection() {
        return masterLanguageCollection;
    }

    public void setMasterLanguageCollection(Collection<MasterLanguage> masterLanguageCollection) {
        this.masterLanguageCollection = masterLanguageCollection;
    }

    @XmlTransient
    public Collection<MasterMarketingDesk> getMasterMarketingDeskCollection() {
        return masterMarketingDeskCollection;
    }

    public void setMasterMarketingDeskCollection(Collection<MasterMarketingDesk> masterMarketingDeskCollection) {
        this.masterMarketingDeskCollection = masterMarketingDeskCollection;
    }

    @XmlTransient
    public Collection<MasterPaymentNegotiation> getMasterPaymentNegotiationCollection() {
        return masterPaymentNegotiationCollection;
    }

    public void setMasterPaymentNegotiationCollection(Collection<MasterPaymentNegotiation> masterPaymentNegotiationCollection) {
        this.masterPaymentNegotiationCollection = masterPaymentNegotiationCollection;
    }

    @XmlTransient
    public Collection<MasterPaymentNegotiationType> getMasterPaymentNegotiationTypeCollection() {
        return masterPaymentNegotiationTypeCollection;
    }

    public void setMasterPaymentNegotiationTypeCollection(Collection<MasterPaymentNegotiationType> masterPaymentNegotiationTypeCollection) {
        this.masterPaymentNegotiationTypeCollection = masterPaymentNegotiationTypeCollection;
    }

    @XmlTransient
    public Collection<MasterFinancialCalenderMapping> getMasterFinancialCalenderMappingCollection() {
        return masterFinancialCalenderMappingCollection;
    }

    public void setMasterFinancialCalenderMappingCollection(Collection<MasterFinancialCalenderMapping> masterFinancialCalenderMappingCollection) {
        this.masterFinancialCalenderMappingCollection = masterFinancialCalenderMappingCollection;
    }

    @XmlTransient
    public Collection<MasterFinancialCalenderMapping> getMasterFinancialCalenderMappingCollection1() {
        return masterFinancialCalenderMappingCollection1;
    }

    public void setMasterFinancialCalenderMappingCollection1(Collection<MasterFinancialCalenderMapping> masterFinancialCalenderMappingCollection1) {
        this.masterFinancialCalenderMappingCollection1 = masterFinancialCalenderMappingCollection1;
    }

    @XmlTransient
    public Collection<MasterFinancialCalender> getMasterFinancialCalenderCollection() {
        return masterFinancialCalenderCollection;
    }

    public void setMasterFinancialCalenderCollection(Collection<MasterFinancialCalender> masterFinancialCalenderCollection) {
        this.masterFinancialCalenderCollection = masterFinancialCalenderCollection;
    }

    @XmlTransient
    public Collection<MasterCurrency> getMasterCurrencyCollection() {
        return masterCurrencyCollection;
    }

    public void setMasterCurrencyCollection(Collection<MasterCurrency> masterCurrencyCollection) {
        this.masterCurrencyCollection = masterCurrencyCollection;
    }

    @XmlTransient
    public Collection<MasterHolidayCalender> getMasterHolidayCalenderCollection() {
        return masterHolidayCalenderCollection;
    }

    public void setMasterHolidayCalenderCollection(Collection<MasterHolidayCalender> masterHolidayCalenderCollection) {
        this.masterHolidayCalenderCollection = masterHolidayCalenderCollection;
    }
*/
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkStatusId != null ? pkStatusId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterStatus)) {
            return false;
        }
        MasterStatus other = (MasterStatus) object;
        if ((this.pkStatusId == null && other.pkStatusId != null) || (this.pkStatusId != null && !this.pkStatusId.equals(other.pkStatusId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.reference.domain.MasterStatus[ pkStatusId=" + pkStatusId + " ]";
    }
    
}

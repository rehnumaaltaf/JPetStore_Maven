/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olam.score.reference.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

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

import org.modelmapper.ModelMapper;

import com.olam.score.reference.dto.FinancialCalenderDTO;
import com.olam.score.reference.dto.UomDto;

/**
 *
 * @author ramachandran_n02
 */
@Entity
@Table(name = "MASTER_FINANCIAL_CALENDER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterFinancialCalender.findAll", query = "SELECT m FROM MasterFinancialCalender m")})
public class MasterFinancialCalender implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
	@SequenceGenerator(name="MASTER_FINANCIAL_CALENDER_PK_FIN_CAL_ID_GENERATOR", sequenceName="FIN_CAL_ID_SEQ",initialValue = 10, allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MASTER_FINANCIAL_CALENDER_PK_FIN_CAL_ID_GENERATOR")
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_FIN_CAL_ID")
    private Integer pkFinCalId;
    @Basic(optional = false)
    @Size(min = 1, max = 200)
    @Column(name = "FIN_CAL_NAME")
    private String finCalName;
    @Basic(optional = false)
    
    @Size(min = 1, max = 20)
    @Column(name = "FIN_CAL_CODE")
    private String finCalCode;
    @Size(max = 1000)
    @Column(name = "FIN_CAL_DESC")
    private String finCalDesc;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "FIN_CAL_FISCAL_YEAR")
    private String finCalFiscalYear;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "FIN_CAL_START_YEAR")
    private String finCalStartYear;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "FIN_CAL_START_MONTH")
    private String finCalStartMonth;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "FIN_CAL_END_YEAR")
    private String finCalEndYear;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "FIN_CAL_END_MONTH")
    private String finCalEndMonth;
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
   /* @JoinColumn(name = "FK_STATUS_ID", referencedColumnName = "PK_STATUS_ID")
    @ManyToOne
    private MasterStatus fkStatusId;*/
    @Column(name = "FK_STATUS_ID")
    private Integer fkStatusId;

    public Integer getFkStatusId() {
		return fkStatusId;
	}

	public void setFkStatusId(Integer fkStatusId) {
		this.fkStatusId = fkStatusId;
	}

	public MasterFinancialCalender() {
    }

    public MasterFinancialCalender(Integer pkFinCalId) {
        this.pkFinCalId = pkFinCalId;
    }

    public MasterFinancialCalender(Integer pkFinCalId, String finCalName, String finCalCode, String finCalFiscalYear, String finCalStartYear, String finCalStartMonth, String finCalEndYear, String finCalEndMonth) {
        this.pkFinCalId = pkFinCalId;
        this.finCalName = finCalName;
        this.finCalCode = finCalCode;
        this.finCalFiscalYear = finCalFiscalYear;
        this.finCalStartYear = finCalStartYear;
        this.finCalStartMonth = finCalStartMonth;
        this.finCalEndYear = finCalEndYear;
        this.finCalEndMonth = finCalEndMonth;
    }

    public Integer getPkFinCalId() {
        return pkFinCalId;
    }

    public void setPkFinCalId(Integer pkFinCalId) {
        this.pkFinCalId = pkFinCalId;
    }

    public String getFinCalName() {
        return finCalName;
    }

    public void setFinCalName(String finCalName) {
        this.finCalName = finCalName;
    }

    public String getFinCalCode() {
        return finCalCode;
    }

    public void setFinCalCode(String finCalCode) {
        this.finCalCode = finCalCode;
    }

    public String getFinCalDesc() {
        return finCalDesc;
    }

    public void setFinCalDesc(String finCalDesc) {
        this.finCalDesc = finCalDesc;
    }

    public String getFinCalFiscalYear() {
        return finCalFiscalYear;
    }

    public void setFinCalFiscalYear(String finCalFiscalYear) {
        this.finCalFiscalYear = finCalFiscalYear;
    }

    public String getFinCalStartYear() {
        return finCalStartYear;
    }

    public void setFinCalStartYear(String finCalStartYear) {
        this.finCalStartYear = finCalStartYear;
    }

    public String getFinCalStartMonth() {
        return finCalStartMonth;
    }

    public void setFinCalStartMonth(String finCalStartMonth) {
        this.finCalStartMonth = finCalStartMonth;
    }

    public String getFinCalEndYear() {
        return finCalEndYear;
    }

    public void setFinCalEndYear(String finCalEndYear) {
        this.finCalEndYear = finCalEndYear;
    }

    public String getFinCalEndMonth() {
        return finCalEndMonth;
    }

    public void setFinCalEndMonth(String finCalEndMonth) {
        this.finCalEndMonth = finCalEndMonth;
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

    /*public MasterStatus getFkStatusId() {
        return fkStatusId;
    }

    public void setFkStatusId(MasterStatus fkStatusId) {
        this.fkStatusId = fkStatusId;
    }*/

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkFinCalId != null ? pkFinCalId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterFinancialCalender)) {
            return false;
        }
        MasterFinancialCalender other = (MasterFinancialCalender) object;
        if ((this.pkFinCalId == null && other.pkFinCalId != null) || (this.pkFinCalId != null && !this.pkFinCalId.equals(other.pkFinCalId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.reference.domain.MasterFinancialCalender[ pkFinCalId=" + pkFinCalId + " ]";
    }

    public FinancialCalenderDTO convertToFinancialCalendarDto(Map<Integer,String> statusmap) {
    	FinancialCalenderDTO dto;
        ModelMapper modelMapper = new ModelMapper();
    	dto = modelMapper.map(this, FinancialCalenderDTO.class);
    	dto.setStatusName(statusmap.get(getFkStatusId()));
		return dto;
    }
}

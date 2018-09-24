
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olam.score.reference.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
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
@Table(name = "MASTER_HOLIDAY_LIST")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterHolidayList.findAll", query = "SELECT m FROM MasterHolidayList m")})
public class MasterHolidayList implements Serializable {

    private static final long serialVersionUID = 1L;
    @SequenceGenerator(name="MASTER_HOLIDAY_LIST_HOLIDAYLISTIDSEQ_GENERATOR", sequenceName="HOLIDAY_LIST_ID_SEQ",allocationSize=1)
	@GeneratedValue(generator="MASTER_HOLIDAY_LIST_HOLIDAYLISTIDSEQ_GENERATOR")
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_HOLIDAY_LIST_ID")
    private Integer pkHolidayListId;
    @Basic(optional = false)
    /*@NotNull*/
    @Column(name = "HOLIDAY_LIST_YEAR")
    private long holidayListYear;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "HOLIDAY_LIST_NAME")
    private String holidayListName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "HOLIDAY_LIST_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date holidayListDate;
    @Size(max = 100)
    @Column(name = "CREATED_BY", updatable=false)
    private String createdBy;
    @Column(name = "CREATED_DATE", updatable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Size(max = 100)
    @Column(name = "MODIFIED_BY")
    private String modifiedBy;
    @Column(name = "MODIFIED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;
    @JoinColumn(name = "FK_HOLIDAY_CAL_ID", referencedColumnName = "PK_HOLIDAY_CAL_ID")
    @ManyToOne(cascade = CascadeType.ALL)
    private MasterHolidayCalender fkHolidayCalId;
    /*@JoinColumn(name = "FK_STATUS_ID", referencedColumnName = "PK_STATUS_ID")
    @ManyToOne
    private MasterStatus fkStatusId;*/
    @Column(name = "FK_STATUS_ID")
    private Integer fkStatusId;

    public MasterHolidayList() {
    }

    public MasterHolidayList(Integer pkHolidayListId) {
        this.pkHolidayListId = pkHolidayListId;
    }

    public MasterHolidayList(Integer pkHolidayListId, long holidayListYear, String holidayListName, Date holidayListDate) {
        this.pkHolidayListId = pkHolidayListId;
        this.holidayListYear = holidayListYear;
        this.holidayListName = holidayListName;
        this.holidayListDate = holidayListDate;
    }

    public Integer getPkHolidayListId() {
        return pkHolidayListId;
    }

    public void setPkHolidayListId(Integer pkHolidayListId) {
        this.pkHolidayListId = pkHolidayListId;
    }

    public long getHolidayListYear() {
        return holidayListYear;
    }

    public void setHolidayListYear(long holidayListYear) {
        this.holidayListYear = holidayListYear;
    }

    public String getHolidayListName() {
        return holidayListName;
    }

    public void setHolidayListName(String holidayListName) {
        this.holidayListName = holidayListName;
    }

    public Date getHolidayListDate() {
        return holidayListDate;
    }

    public void setHolidayListDate(Date holidayListDate) {
        this.holidayListDate = holidayListDate;
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

    public MasterHolidayCalender getFkHolidayCalId() {
        return fkHolidayCalId;
    }

    public void setFkHolidayCalId(MasterHolidayCalender fkHolidayCalId) {
        this.fkHolidayCalId = fkHolidayCalId;
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
        hash += (pkHolidayListId != null ? pkHolidayListId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterHolidayList)) {
            return false;
        }
        MasterHolidayList other = (MasterHolidayList) object;
        if ((this.pkHolidayListId == null && other.pkHolidayListId != null) || (this.pkHolidayListId != null && !this.pkHolidayListId.equals(other.pkHolidayListId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.reference.domain.MasterHolidayList[ pkHolidayListId=" + pkHolidayListId + " ]";
    }
    
}


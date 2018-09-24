/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olam.score.reference.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.modelmapper.ModelMapper;

import com.olam.score.reference.dto.HolidayCalendarDto;
import com.olam.score.reference.dto.HolidayListDto;


/**
 *
 * @author ramachandran_n02
 */
@Entity
@Table(name = "MASTER_HOLIDAY_CALENDER")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "MasterHolidayCalender.findAll", query = "SELECT m FROM MasterHolidayCalender m")})
public class MasterHolidayCalender implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@NotNull
	@SequenceGenerator(name="MASTER_HOLIDAY_CAL_HOLIDAYCALIDSEQ_GENERATOR", sequenceName="HOLIDAY_CAL_ID_SEQ",allocationSize=1)
	@GeneratedValue(generator="MASTER_HOLIDAY_CAL_HOLIDAYCALIDSEQ_GENERATOR")
	@Column(name = "PK_HOLIDAY_CAL_ID")
	private Integer pkHolidayCalId;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 200)
	@Column(name = "HOLIDAY_CAL_NAME")
	private String holidayCalName;
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
	@Size(max = 200)
	@Column(name = "HOLIDAY_CAL_DESC")
	private String holidayCalDesc;
	@Size(max = 20)
	@Column(name = "HOLIDAY_CAL_CODE")
	private String holidayCalCode;
	@OneToMany(mappedBy = "fkHolidayCalId", cascade= CascadeType.ALL)//,orphanRemoval = true)
	private Collection<MasterHolidayList> masterHolidayListCollection;
	/* @JoinColumn(name = "FK_STATUS_ID", referencedColumnName = "PK_STATUS_ID")
    @ManyToOne
    private MasterStatus fkStatusId;*/
	/*@NotNull*/
	
	@Column(name = "FK_GEO_ID")
	private Integer fkGeoId;
	@Column(name = "FK_STATUS_ID")
	private Integer fkStatusId;

	public Integer getFkGeoId() {
		return fkGeoId;
	}

	public String getHolidayCalDesc() {
		return holidayCalDesc;
	}

	public void setHolidayCalDesc(String holidayCalDesc) {
		this.holidayCalDesc = holidayCalDesc;
	}

	public void setFkGeoId(Integer fkGeoId) {
		this.fkGeoId = fkGeoId;
	}

	public MasterHolidayCalender() {
	}

	public MasterHolidayCalender(Integer pkHolidayCalId) {
		this.pkHolidayCalId = pkHolidayCalId;
	}

	public MasterHolidayCalender(Integer pkHolidayCalId, String holidayCalName) {
		this.pkHolidayCalId = pkHolidayCalId;
		this.holidayCalName = holidayCalName;
	}
	
	public MasterHolidayCalender(Integer pkHolidayCalId, String holidayCalName, String holidayCalCode) {
		this.pkHolidayCalId = pkHolidayCalId;
		this.holidayCalName = holidayCalName;
		this.holidayCalCode = holidayCalCode;
	}

	public Integer getPkHolidayCalId() {
		return pkHolidayCalId;
	}

	public void setPkHolidayCalId(Integer pkHolidayCalId) {
		this.pkHolidayCalId = pkHolidayCalId;
	}

	public String getHolidayCalName() {
		return holidayCalName;
	}

	public void setHolidayCalName(String holidayCalName) {
		this.holidayCalName = holidayCalName;
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

	/*public String getHolidayCalFullname() {
        return holidayCalFullname;
    }

    public void setHolidayCalFullname(String holidayCalFullname) {
        this.holidayCalFullname = holidayCalFullname;
    }*/

	public String getHolidayCalCode() {
		return holidayCalCode;
	}

	public void setHolidayCalCode(String holidayCalCode) {
		this.holidayCalCode = holidayCalCode;
	}

	@XmlTransient
	public Collection<MasterHolidayList> getMasterHolidayListCollection() {
		return masterHolidayListCollection;
	}

	public void setMasterHolidayListCollection(Collection<MasterHolidayList> masterHolidayListCollection) {
		this.masterHolidayListCollection = masterHolidayListCollection;
	}

	/* public MasterStatus getFkStatusId() {
        return fkStatusId;
    }

    public void setFkStatusId(MasterStatus fkStatusId) {
        this.fkStatusId = fkStatusId;
    }*/

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (pkHolidayCalId != null ? pkHolidayCalId.hashCode() : 0);
		return hash;
	}

	public Integer getFkStatusId() {
		return fkStatusId;
	}

	public void setFkStatusId(Integer fkStatusId) {
		this.fkStatusId = fkStatusId;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof MasterHolidayCalender)) {
			return false;
		}
		MasterHolidayCalender other = (MasterHolidayCalender) object;
		if ((this.pkHolidayCalId == null && other.pkHolidayCalId != null) || (this.pkHolidayCalId != null && !this.pkHolidayCalId.equals(other.pkHolidayCalId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.olam.score.reference.domain.MasterHolidayCalender[ pkHolidayCalId=" + pkHolidayCalId + " ]";
	}

	public HolidayCalendarDto convertToHolidayCalendarDto(Map<Integer, String> statusMap, List<Map<Object, Object>> geoList) 
	{
		HolidayCalendarDto dto;

		ModelMapper modelMapper = new ModelMapper();
		dto = modelMapper.map(this, HolidayCalendarDto.class);
		
		if(geoList!=null)
		{
			String geoName = null;
			 Integer pkGeoId = 0;
			 for (int x = 0; x < geoList.size(); x++) {
				pkGeoId = (int) geoList.get(x).get("pkGeoId");
				if (pkGeoId != 0 && this.getFkGeoId().equals(pkGeoId)) {
					geoName = (String) geoList.get(x).get("geoName");
					dto.setGeoName(geoName);
					dto.setGeoId(pkGeoId);
					break;
				}
			}
		}
		

		
		Collection<MasterHolidayList> myCollection = this.getMasterHolidayListCollection();
		
		if(!myCollection.isEmpty()){
			List<HolidayListDto> holidayDtoList = new ArrayList<>();

			for (MasterHolidayList masterHolidayList : myCollection) {
				HolidayListDto holidayListDto = new HolidayListDto();
				holidayListDto.setHolidayCalId(masterHolidayList.getFkHolidayCalId().getPkHolidayCalId());
				
				//commented AS date is not sent as string
				/*Timestamp ts = (Timestamp) masterHolidayList.getHolidayListDate();
				Date date = new Date();
				date.setTime(ts.getTime());
				String formattedDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
				holidayListDto.setHolidayListDate(formattedDate);*/
				holidayListDto.setHolidayListDate((Timestamp)masterHolidayList.getHolidayListDate());
				holidayListDto.setHolidayListName(masterHolidayList.getHolidayListName());
				holidayListDto.setStatusName(statusMap.get(getFkStatusId()));
				holidayListDto.setHolidayListId(masterHolidayList.getPkHolidayListId());
				holidayDtoList.add(holidayListDto);
			}
			dto.setHolidayListDto(holidayDtoList);
		}
		dto.setStatusName(statusMap.get(getFkStatusId()));
		return dto;
	}

	
}


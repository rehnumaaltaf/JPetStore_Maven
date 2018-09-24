/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olam.score.location.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.Basic;
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

import com.olam.score.common.constants.Constants;
import com.olam.score.location.dto.GeographyDto;

/**
 *
 * @author rashmi.kedlaya
 */
@Entity
@Table(name = "MASTER_GEOGRAPHY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterGeography.findAll", query = "SELECT m FROM MasterGeography m")})
public class MasterGeography implements Serializable {

    private static final long serialVersionUID = 1L;
	@SequenceGenerator(name="MASTER_GEOGRAPHY_GEOIDSEQ_GENERATOR", sequenceName="GEO_ID_SEQ",allocationSize=1)
	@GeneratedValue(generator="MASTER_GEOGRAPHY_GEOIDSEQ_GENERATOR")
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_GEO_ID")
    private Integer pkGeoId;
    @Size(max = 200)
    @Column(name = "GEO_NAME")
    private String geoName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "GEO_CODE")
    private String geoCode;
    @Size(max = 100)
    @Column(name = "CREATED_BY", updatable= false)
    private String createdBy;
    @Column(name = "CREATED_DATE", updatable= false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Size(max = 100)
    @Column(name = "MODIFIED_BY")
    private String modifiedBy;
    @Column(name = "MODIFIED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;
    @Column(name = "GEO_LEVEL_NO")
    private Integer geoLevelNo;
    @Column(name = "FK_STATUS_ID")
    private Integer fkStatusId;
    @Column(name = "FK_MARKET_DESK_ID")
    private Integer fkMarketDeskId;
    @Size(max = 200)
    @Column(name = "GEO_FULLNAME")
    private String geoFullname;
    @Size(max = 500)
    @Column(name = "CUSTOM_ATTRIBUTE_1")
    private String customAttribute1;
    @Size(max = 500)
    @Column(name = "CUSTOM_ATTRIBUTE_2")
    private String customAttribute2;
    @Size(max = 500)
    @Column(name = "CUSTOM_ATTRIBUTE_3")
    private String customAttribute3;
    @Size(max = 500)
    @Column(name = "CUSTOM_ATTRIBUTE_4")
    private String customAttribute4;
    @Size(max = 500)
    @Column(name = "CUSTOM_ATTRIBUTE_5")
    private String customAttribute5;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "CUSTOM_ATTRIBUTE_6")
    private Double customAttribute6;
    @Column(name = "CUSTOM_ATTRIBUTE_7")
    private Double customAttribute7;
    @Column(name = "CUSTOM_ATTRIBUTE_8")
    private Double customAttribute8;
    @JoinColumn(name = "FK_PARENT_GEO_ID", referencedColumnName = "PK_GEO_ID")
    @ManyToOne
    private MasterGeography fkParentGeoId;
    @JoinColumn(name = "FK_GEO_LEVEL_ID", referencedColumnName = "PK_GEO_LEVEL_ID")
    @ManyToOne
    private MasterGeographyLevel fkGeoLevelId;
   
    public MasterGeography() {
    }

    public MasterGeography(Integer pkGeoId) {
        this.pkGeoId = pkGeoId;
    }

    public MasterGeography(Integer pkGeoId, String geoCode) {
        this.pkGeoId = pkGeoId;
        this.geoCode = geoCode;
    }

    public Integer getPkGeoId() {
        return pkGeoId;
    }

    public void setPkGeoId(Integer pkGeoId) {
        this.pkGeoId = pkGeoId;
    }

    public String getGeoName() {
        return geoName;
    }

    public void setGeoName(String geoName) {
        this.geoName = geoName;
    }

    public String getGeoCode() {
        return geoCode;
    }

    public void setGeoCode(String geoCode) {
        this.geoCode = geoCode;
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

    public Integer getGeoLevelNo() {
        return geoLevelNo;
    }

    public void setGeoLevelNo(Integer geoLevelNo) {
        this.geoLevelNo = geoLevelNo;
    }

    public Integer getFkStatusId() {
        return fkStatusId;
    }

    public void setFkStatusId(Integer fkStatusId) {
        this.fkStatusId = fkStatusId;
    }

    public Integer getFkMarketDeskId() {
        return fkMarketDeskId;
    }

    public void setFkMarketDeskId(Integer fkMarketDeskId) {
        this.fkMarketDeskId = fkMarketDeskId;
    }

    public String getGeoFullname() {
        return geoFullname;
    }

    public void setGeoFullname(String geoFullname) {
        this.geoFullname = geoFullname;
    }

    public String getCustomAttribute1() {
        return customAttribute1;
    }

    public void setCustomAttribute1(String customAttribute1) {
        this.customAttribute1 = customAttribute1;
    }

    public String getCustomAttribute2() {
        return customAttribute2;
    }

    public void setCustomAttribute2(String customAttribute2) {
        this.customAttribute2 = customAttribute2;
    }

    public String getCustomAttribute3() {
        return customAttribute3;
    }

    public void setCustomAttribute3(String customAttribute3) {
        this.customAttribute3 = customAttribute3;
    }

    public String getCustomAttribute4() {
        return customAttribute4;
    }

    public void setCustomAttribute4(String customAttribute4) {
        this.customAttribute4 = customAttribute4;
    }

    public String getCustomAttribute5() {
        return customAttribute5;
    }

    public void setCustomAttribute5(String customAttribute5) {
        this.customAttribute5 = customAttribute5;
    }

    public Double getCustomAttribute6() {
        return customAttribute6;
    }

    public void setCustomAttribute6(Double customAttribute6) {
        this.customAttribute6 = customAttribute6;
    }

    public Double getCustomAttribute7() {
        return customAttribute7;
    }

    public void setCustomAttribute7(Double customAttribute7) {
        this.customAttribute7 = customAttribute7;
    }

    public Double getCustomAttribute8() {
        return customAttribute8;
    }

    public void setCustomAttribute8(Double customAttribute8) {
        this.customAttribute8 = customAttribute8;
    }

      public MasterGeography getFkParentGeoId() {
        return fkParentGeoId;
    }

    public void setFkParentGeoId(MasterGeography fkParentGeoId) {
        this.fkParentGeoId = fkParentGeoId;
    }

    public MasterGeographyLevel getFkGeoLevelId() {
        return fkGeoLevelId;
    }

    public void setFkGeoLevelId(MasterGeographyLevel fkGeoLevelId) {
        this.fkGeoLevelId = fkGeoLevelId;
    }

	public GeographyDto geographyModelToDto(Map<Integer, String> statusMap, List<Map<Object, Object>> marketDeskList) {
		GeographyDto dto=new GeographyDto();
		dto.setPkGeoId(pkGeoId);
		dto.setGeoCode(geoCode);
		dto.setGeoName(geoName);
		dto.setGeoDescription(geoFullname);
		if(fkParentGeoId!=null){
		dto.setGeoParentCode(fkParentGeoId.geoCode);
		dto.setGeoParentId(fkParentGeoId.pkGeoId);
		dto.setGeoParentName(fkParentGeoId.geoName);
		}
		if(fkGeoLevelId!=null){
		dto.setGeoTypeId(fkGeoLevelId.getPkGeoLevelId());
		dto.setGeoTypeCode(fkGeoLevelId.getGeoLevelCode());
		dto.setGeoTypeName(fkGeoLevelId.getGeoLevelName());
		}
		dto.setStatusId(fkStatusId);
		dto.setStatusName(statusMap.get(fkStatusId));
		dto.setCreatedBy(createdBy);
		dto.setCreatedDate((Timestamp) createdDate);
		dto.setModifiedBy(modifiedBy);
		dto.setModifiedDate((Timestamp) modifiedDate);
		for (Map<Object, Object> map : marketDeskList) {
			if(map.get(Constants.MARKET_DESK_ID)==fkMarketDeskId){
				dto.setMarketDeskId(fkMarketDeskId);
				dto.setMarketDeskCode((String)map.get(Constants.MARKET_DESK_CODE));
				dto.setMarketDeskName((String)map.get(Constants.MARKET_DESK_NAME));
				
			}
		}
		return dto;
	}

 }

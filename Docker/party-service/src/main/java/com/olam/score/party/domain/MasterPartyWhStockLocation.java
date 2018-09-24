/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olam.score.party.domain;

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
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 *
 * @author Ramachandran_N02
 */
@Entity
@Table(name = "MASTER_PARTY_WH_STOCK_LOCATION")
@NamedQueries({
    @NamedQuery(name = "MasterPartyWhStockLocation.findAll", query = "SELECT m FROM MasterPartyWhStockLocation m")})
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(of = { "pkPartyWhStockLocationId" })
@ToString(of = { "pkPartyWhStockLocationId" })
public class MasterPartyWhStockLocation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MASTER_PARTY_WH_STOCK_LOCATION_GENERATOR")
   	@SequenceGenerator(name="MASTER_PARTY_WH_STOCK_LOCATION_GENERATOR", sequenceName="party.PARTY_WH_STOCK_LOCATION_ID_SEQ",allocationSize=1)
    @NotNull
    @NonNull
    @Column(name = "PK_PARTY_WH_STOCK_LOCATION_ID")
    private Integer pkPartyWhStockLocationId;
    @Size(max = 200)
    @Column(name = "WH_STOCK_LOC_NAME")
    private String whStockLocName;
    @Size(max = 20)
    @Column(name = "WH_STOCK_LOC_CODE")
    private String whStockLocCode;
    @Size(max = 1000)
    @Column(name = "WH_STOCK_LOC_ADDR1")
    private String whStockLocAddr1;
    @Size(max = 20)
    @Column(name = "WH_STOCK_LOC_ZIPCODE")
    private String whStockLocZipcode;
    @Size(max = 50)
    @Column(name = "WH_STOCK_LOC_PHONE")
    private String whStockLocPhone;
    @Size(max = 1000)
    @Column(name = "WH_STOCK_LOC_EMAIL")
    private String whStockLocEmail;
    @Column(name = "WH_STOCK_LOC_FAX")
    private String whStockLocFax;
    @Size(max = 2000)
    @Column(name = "WH_STOCK_LOC_WEBSITE")
    private String whStockLocWebsite;
    @Column(name = "FK_GEO_ID")
    private Integer fkGeoId;
    @Size(max = 100)
    @Column(name = "CREATED_BY")
    private String createdBy;
    @Column(name = "CREATED_DATE",  updatable = false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Size(max = 100)
    @Column(name = "MODIFIED_BY")
    private String modifiedBy;
    @Column(name = "MODIFIED_DATE", updatable = true, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;
    @Column(name = "FK_STATUS_ID")
    private Integer fkStatusId;
    @Column(name = "FK_HOLIDAY_CAL_ID")
    private Integer fkHolidayCalId;
    @Size(max = 200)
    @Column(name = "WH_STOCK_LOC_FULLNAME")
    private String whStockLocFullname;
    @Size(max = 1000)
    @Column(name = "WH_STOCK_LOC_ADDR2")
    private String whStockLocAddr2;
    @Size(max = 1)
    @Column(name = "IS_EXCHANGE_DESIGNATED")
    private String isExchangeDesignated;
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
    @Column(name = "FK_LOC_ID")
    private Integer fkLocId;
    @Size(max = 100)
    @Column(name = "TAX_REG_NO")
    private String taxRegNo;
    @Column(name = "TAX_REG_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date taxRegDate;
    @Size(max = 20)
    @Column(name = "WEBSITE")
    private String website;
    @Size(max = 1)
    @Column(name = "IS_BONDED_WAREHOUSE")
    private String isBondedWarehouse;
    @JoinColumn(name = "FK_PARTY_ID", referencedColumnName = "PK_PARTY_ID")
    @ManyToOne
    private MasterParty fkPartyId;

}

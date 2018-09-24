/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olam.score.party.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "MASTER_PARTY")
@NamedQueries({
    @NamedQuery(name = "MasterParty.findAll", query = "SELECT m FROM MasterParty m")})
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(of = { "pkPartyId" })
@ToString(of = { "pkPartyId" })
public  class MasterParty implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MASTER_PARTY_GENERATOR")
	@SequenceGenerator(name="MASTER_PARTY_GENERATOR", sequenceName="party.PARTY_ID_SEQ",allocationSize=1)
    @NotNull
    @NonNull
    @Column(name = "PK_PARTY_ID")
    private Integer pkPartyId;
    @Column(name = "FK_PARENT_PARTY_ID")
    private Integer fkParentPartyId;
    @Size(max = 20)
    @Column(name = "PARTY_CODE")
    private String partyCode;
    @Size(max = 200)
    @Column(name = "PARTY_NAME")
    private String partyName;
    @Size(max = 200)
    @Column(name = "PARTY_FULLNAME")
    private String partyFullname;
    @Size(max = 1)
    @Column(name = "IS_GROUP_PARTY")
    private String isGroupParty;
    @Column(name = "FK_FUNCTIONAL_CURRENCY_ID")
    private Integer fkFunctionalCurrencyId;
    @Column(name = "FK_DEFAULT_REPORTING_UOM_ID")
    private Integer fkDefaultReportingUomId;
    @Size(max = 1)
    @Column(name = "PARTY_INTERNAL_FLAG")
    private String partyInternalFlag;
    @Column(name = "FK_OPERATIONAL_CURRENCY_ID")
    private Integer fkOperationalCurrencyId;
    @Size(max = 100)
    @Column(name = "CREATED_BY")
    private String createdBy;
    @Column(name = "CREATED_DATE", updatable = false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Size(max = 100)
    @Column(name = "MODIFIED_BY")
    private String modifiedBy;
    @Column(name = "MODIFIED_DATE",  updatable = true, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;
    @Size(max = 100)
    @Column(name = "PARTY_COMPANY_REG_NO")
    private String partyCompanyRegNo;
    @Column(name = "PARTY_COMP_REG_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date partyCompRegDate;
    @Size(max = 100)
    @Column(name = "PARTY_TAX_REG_NO")
    private String partyTaxRegNo;
    @Column(name = "PARTY_TAX_REG_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date partyTaxRegDate;
    @Column(name = "FK_STATUS_ID")
    private Integer fkStatusId;
    @Size(max = 1)
    @Column(name = "PARTY_ALLOW_CREDIT")
    private String partyAllowCredit;
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
    @Column(name = "FK_GEO_ID")
    private Integer fkGeoCountryId;
    @Column(name = "FK_MARKET_DESK_ID")
    private Integer fkMarketingDeskId;
    @Column(name = "FIRST_TRANS_WITH_OLAM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date firstTransWithOlam;
    @Size(max = 1)
    @Column(name = "IS_GTP_APPLICABLE_IND")
    private String isGtpApplicableInd;
    @Column(name = "GROUP_CREDIT_LIMIT")
    private BigDecimal groupCreditLimit;
    @Column(name = "GROUP_CREDIT_TENOR_IN_DAYS")
    private BigDecimal groupCreditTenorInDays;
    @OneToMany(mappedBy = "fkPartyId")
    private List<MasterPartyGlMapping> masterPartyGlMappingList;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_BANK_PARTY_ID")
    private List<MasterPartyBank> masterPartyBankList;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_PARTY_ID")
    private List<MasterPartyContact> masterPartyContactList;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_PARTY_ID")
    private List<MasterPartyTranslation> masterPartyTranslationList;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_PARTY_ID")
    private List<MasterPartyCreditLimit> masterPartyCreditLimitList;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_PARTY_ID")
    private List<MasterPartyPartyType> masterPartyPartyTypeList;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_PARTY_ID")
    private List<MasterPartyPlant> masterPartyPlantList;
    //@OneToMany(mappedBy = "internalCompanyId",cascade = CascadeType.ALL)
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "INTERNAL_COMPANY_ID")
    private List<MasterUnit> masterUnitList;
    @OneToMany(mappedBy = "fkPartyId")
    private List<MasterUnitGlMapping> masterUnitGlMappingList;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_PARTY_ID")
    private List<MasterPartyDocument> masterPartyDocumentList;
    @OneToMany(mappedBy = "fkPartyId")
    private List<MasterUnitLe> masterUnitLeList;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_PARTY_ID")
    private List<MasterPartyWhStockLocation> masterPartyWhStockLocationList;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_PARTY_ID")
    private List<MasterPartyExternalMapping> masterPartyExternalMappingList;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_PARTY_ID")
    private List<MasterPartyPartyClass> masterPartyPartyClassList;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_PARTY_ID")
    private List<MasterPartyGrade> masterPartyGradeList;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_PARTY_ID")
    private List<MasterPartyPaymentTerm> masterPartyPaymentTermList;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_PARTY_ID")
    private List<MasterPartyBroker> masterPartyBrokerList;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_PARTY_ID")
    private List<MasterPartyAddress> masterPartyAddressList;
    @OneToMany(mappedBy = "fkPartyId")
    private List<MasterPartyComment> masterPartyCommentList;
    @JoinColumn(name = "FK_PARTY_RATING_REF_ID", referencedColumnName = "PK_PARTY_RATING_REF_ID")
    @ManyToOne(cascade = CascadeType.ALL)
    private MasterPartyRatingRef fkPartyRatingRefId;
    
    

}

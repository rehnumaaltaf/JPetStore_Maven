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
@Table(name = "MASTER_PARTY_BANK")
@NamedQueries({
    @NamedQuery(name = "MasterPartyBank.findAll", query = "SELECT m FROM MasterPartyBank m")})
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(of = { "pkPartyBankId" })
@ToString(of = { "pkPartyBankId" })
public class MasterPartyBank implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MASTER_PARTY_BANK_GENERATOR")
   	@SequenceGenerator(name="MASTER_PARTY_BANK_GENERATOR", sequenceName="party.PARTY_BANK_ID_SEQ",allocationSize=1)
    @NotNull
    @NonNull
    @Column(name = "PK_PARTY_BANK_ID")
    private Integer pkPartyBankId;
    @Size(max = 100)
    @Column(name = "PARTY_BANK_BIC_CODE")
    private String partyBankBicCode;
    @Size(max = 100)
    @Column(name = "PARTY_BANK_SWIFT_CODE")
    private String partyBankSwiftCode;
    @Size(max = 1000)
    @Column(name = "PARTY_BANK_ADDR1")
    private String partyBankAddr1;
    @Size(max = 100)
    @Column(name = "PARTY_BANK_BRANCH")
    private String partyBankBranch;
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
    @Column(name = "FK_BANK_ADDR_GEO_ID")
    private Integer fkBankAddrGeoId;
    @Size(max = 20)
    @Column(name = "PARTY_BANK_ACCOUNT_NO")
    private String partyBankAccountNo;
    @Column(name = "FK_STATUS_ID")
    private Integer fkStatusId;
    @Size(max = 1000)
    @Column(name = "PARTY_BANK_ADDR2")
    private String partyBankAddr2;
    @Size(max = 20)
    @Column(name = "PARTY_BANK_ZIP_CODE")
    private String partyBankZipCode;
    @Size(max = 50)
    @Column(name = "PARTY_BANK_PHONE")
    private String partyBankPhone;
    @Size(max = 20)
    @Column(name = "PARTY_BANK_EMAIL")
    private String partyBankEmail;
    @Column(name = "PARTY_BANK_FAX")
    private String partyBankFax;
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
    @Size(max = 100)
    @Column(name = "PARTY_BANK_ROUTING")
    private String partyBankRouting;
    @Size(max = 500)
    @Column(name = "PARTY_BANK_WEBSITE")
    private String partyBankWebsite;
    @Size(max = 1)
    @Column(name = "PARTY_BANK_ACCOUNT_IS_DEFAULT")
    private String partyBankAccountIsDefault;
    @Size(max = 200)
    @Column(name = "PARTY_BANK_GROUP_NAME")
    private String partyBankGroupName;
    @Column(name = "FK_BANK_BRANCH_COUNTRY_GEO_ID")
    private Integer fkBankBranchCountryGeoId;
    @JoinColumn(name = "FK_ACCOUNT_TYPE_ID", referencedColumnName = "PK_ACCOUNT_TYPE_ID")
    @ManyToOne
    private MasterAccountType fkAccountTypeId;
    @JoinColumn(name = "FK_PARENT_PARTY_ID", referencedColumnName = "PK_PARTY_ID")
    @ManyToOne
    private MasterParty fkParentPartyId;
    /*@JoinColumn(name = "FK_BANK_PARTY_ID", referencedColumnName = "PK_PARTY_ID")
    @ManyToOne
    private MasterParty fkBankPartyId;*/

}

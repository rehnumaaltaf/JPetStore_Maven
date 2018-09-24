/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olam.score.party.domain;

import java.io.Serializable;
import java.util.Collection;
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
@Table(name = "MASTER_PARTY_ADDRESS")
@NamedQueries({
    @NamedQuery(name = "MasterPartyAddress.findAll", query = "SELECT m FROM MasterPartyAddress m")})
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(of = { "pkPartyAddrId" })
@ToString(of = { "pkPartyAddrId" })
public class MasterPartyAddress implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MASTER_PARTY_ADDR_GENERATOR")
   	@SequenceGenerator(name="MASTER_PARTY_ADDR_GENERATOR", sequenceName="party.PARTY_ADDR_ID_SEQ",allocationSize=1)
    @NotNull
    @NonNull
    @Column(name = "PK_PARTY_ADDR_ID")
    private Integer pkPartyAddrId;
    @Size(max = 200)
    @Column(name = "PARTY_ADDR_NAME")
    private String partyAddrName;
    @Size(max = 1000)
    @Column(name = "PARTY_ADDR1")
    private String partyAddressOne;
    @Size(max = 1000)
    @Column(name = "PARTY_ADDR2")
    private String partyAddressTwo;
    @Size(max = 20)
    @Column(name = "PARTY_ADDR_ZIPCODE")
    private String partyAddrZipcode;
    @Size(max = 50)
    @Column(name = "PARTY_ADDR_PHONE")
    private String partyAddrPhone;
    @Size(max = 1000)
    @Column(name = "PARTY_ADDR_EMAIL")
    private String partyAddrEmail;
    @Size(max = 50)
    @Column(name = "PARTY_ADDR_FAX")
    private String partyAddrFax;
    @Size(max = 500)
    @Column(name = "PARTY_ADDR_WEBSITE")
    private String partyAddrWebsite;
    @Size(max = 1)
    @Column(name = "PARTY_ADDR_IS_DEFAULT")
    private String partyAddrIsDefault;
    @Size(max = 100)
    @Column(name = "CREATED_BY")
    private String createdBy;
    @Column(name = "CREATED_DATE",  updatable = false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Size(max = 100)
    @Column(name = "MODIFIED_BY")
    private String modifiedBy;
    @Column(name = "MODIFIED_DATE",  updatable = true, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;
    @Column(name = "FK_GEO_ID")
    private Integer fkGeoCountryId;
    @Column(name = "FK_STATUS_ID")
    private Integer fkStatusId;
    @Column(name = "FK_ADDR_TYPE_REF_ID")
    private Integer fkAddrTypeRefId;
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
    @JoinColumn(name = "FK_PARTY_ID", referencedColumnName = "PK_PARTY_ID")
    @ManyToOne
    private MasterParty fkPartyId;
    @OneToMany(mappedBy = "fkPartyAddrId")
    private Collection<MasterPartyAddressTranslation> masterPartyAddressTranslationCollection;

}

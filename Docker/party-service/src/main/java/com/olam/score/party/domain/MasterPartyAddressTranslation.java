/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olam.score.party.domain;

import java.io.Serializable;
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
@Table(name = "MASTER_PARTY_ADDRESS_TRANSLATION")
@NamedQueries({
    @NamedQuery(name = "MasterPartyAddressTranslation.findAll", query = "SELECT m FROM MasterPartyAddressTranslation m")})
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(of = { "pkPartyAddrTranslId" })
@ToString(of = { "pkPartyAddrTranslId" })
public class MasterPartyAddressTranslation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MASTER_PARTY_ADDR_TRANSL_GENERATOR")
   	@SequenceGenerator(name="MASTER_PARTY_ADDR_TRANSL_GENERATOR", sequenceName="party.PARTY_ADDR_TRANSL_ID_SEQ",allocationSize=1)
    @NotNull
    @NonNull
    @Column(name = "PK_PARTY_ADDR_TRANSL_ID")
    private Integer pkPartyAddrTranslId;
    @Column(name = "FK_LANGUAGE_ID")
    private Integer fkLanguageId;
    @Size(max = 200)
    @Column(name = "PARTY_ADDR_TRANSL_NAME")
    private String partyAddrTranslName;
    @Size(max = 1000)
    @Column(name = "PARTY_ADDR_TRANSL_ADDR1")
    private String partyAddrTranslAddr1;
    @Size(max = 1000)
    @Column(name = "PARTY_ADDR_TRANSL_ADDR2")
    private String partyAddrTranslAddr2;
    @Size(max = 100)
    @Column(name = "PARTY_ADDR_TRANSL_ZIPCODE")
    private String partyAddrTranslZipcode;
    @Size(max = 50)
    @Column(name = "PARTY_ADDR_TRANSL_PHONE")
    private String partyAddrTranslPhone;
    @Size(max = 1000)
    @Column(name = "PARTY_ADDR_TRANSL_EMAIL")
    private String partyAddrTranslEmail;
    @Size(max = 50)
    @Column(name = "PARTY_ADDR_TRANSL_FAX")
    private String partyAddrTranslFax;
    @Size(max = 500)
    @Column(name = "PARTY_ADDR_TRANSL_WEBSITE")
    private String partyAddrTranslWebsite;
    @Column(name = "FK_GEO_TRANSL_ID")
    private Integer fkGeoTranslId;
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
    @Column(name = "FK_STATUS_ID")
    private Integer fkStatusId;
    @JoinColumn(name = "FK_PARTY_ADDR_ID", referencedColumnName = "PK_PARTY_ADDR_ID")
    @ManyToOne
    private MasterPartyAddress fkPartyAddrId;

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olam.score.party.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "MASTER_PARTY_RATING_REF")
@NamedQueries({
    @NamedQuery(name = "MasterPartyRatingRef.findAll", query = "SELECT m FROM MasterPartyRatingRef m")})
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(of = { "pkPartyRatingRefId" })
@ToString(of = { "pkPartyRatingRefId" })
public class MasterPartyRatingRef implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MASTER_PARTY_RATING_REF_GENERATOR")
   	@SequenceGenerator(name="MASTER_PARTY_RATING_REF_GENERATOR", sequenceName="party.PARTY_RATING_REF_ID_SEQ",allocationSize=1)
    @NotNull
    @NonNull
    @Column(name = "PK_PARTY_RATING_REF_ID")
    private Integer pkPartyRateRefId;
    @Size(max = 20)
    @Column(name = "PARTY_RATING_CODE")
    private String rCode;
    @Size(max = 200)
    @Column(name = "PARTY_RATING_NAME")
    private String partyRatingName;
    @Column(name = "FK_STATUS_ID")
    private Integer fkStatusId;
    @Size(max = 100)
    @Column(name = "CREATED_BY")
    private String createdBy;
    @Column(name = "CREATED_DATE", updatable = false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Size(max = 100)
    @Column(name = "MODIFIED_BY")
    private String modifiedBy;
    @Column(name = "MODIFIED_DATE", updatable = true, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;
    @OneToMany(mappedBy = "fkPartyRatingRefId")
    private Collection<MasterParty> masterPartyCollection;

}

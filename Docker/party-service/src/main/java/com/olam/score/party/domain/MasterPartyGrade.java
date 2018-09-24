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
@Table(name = "MASTER_PARTY_GRADE")
@NamedQueries({
    @NamedQuery(name = "MasterPartyGrade.findAll", query = "SELECT m FROM MasterPartyGrade m")})
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(of = { "pkPartyGradeId" })
@ToString(of = { "pkPartyGradeId" })
public class MasterPartyGrade implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MASTER_PARTY_GRADE_GENERATOR")
   	@SequenceGenerator(name="MASTER_PARTY_GRADE_GENERATOR", sequenceName="party.PARTY_GRADE_ID_SEQ",allocationSize=1)
    @NotNull
    @NonNull
    @Column(name = "PK_PARTY_GRADE_ID")
    private Integer pkPartyGradeId;
    @Size(max = 200)
    @Column(name = "PARTY_GRADE_NAME")
    private String partyGradeName;
    @Size(max = 1000)
    @Column(name = "PARTY_GRADE_DESC")
    private String partyGradeDescription;
    @Size(max = 20)
    @Column(name = "CREATED_BY")
    private String createdBy;
    @Column(name = "CREATED_DATE", updatable = false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Size(max = 20)
    @Column(name = "MODIFIED_BY")
    private String modifiedBy;
    @Column(name = "MODIFIED_DATE", updatable = true, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;
    @Column(name = "FK_STATUS_ID")
    private Integer fkStatusId;
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
    @Column(name = "FK_PROD_ID")
    private Integer fkProdId;
    @Size(max = 20)
    @Column(name = "PARTY_GRADE_CODE")
    private String partyGradeCode;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_PARTY_GRADE_ID")
    private Collection<MasterPartyGradeAssoc> masterPartyGradeAssocCollection;
    @JoinColumn(name = "FK_PARTY_ID", referencedColumnName = "PK_PARTY_ID")
    @ManyToOne
    private MasterParty fkPartyId;

}

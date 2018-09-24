/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olam.score.party.domain;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "MASTER_PARTY_CREDIT_LIMIT")
@NamedQueries({
    @NamedQuery(name = "MasterPartyCreditLimit.findAll", query = "SELECT m FROM MasterPartyCreditLimit m")})

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(of = { "pkCreditLimitId" })
@ToString(of = { "pkCreditLimitId" })
public class MasterPartyCreditLimit implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MASTER_PARTY_CREDIT_LIMIT_GENERATOR")
   	@SequenceGenerator(name="MASTER_PARTY_CREDIT_LIMIT_GENERATOR", sequenceName="party.CREDIT_LIMIT_ID_SEQ",allocationSize=1)
    @NotNull
    @NonNull
    @Column(name = "PK_CREDIT_LIMIT_ID")
    private Integer pkCreditLimitId;
    @Column(name = "FK_PROD_ID")
    private Integer fkProd;
    @Column(name = "FK_LIMIT_ALERT_LEVEL_ID")
    private Integer fkLimitAlertLevel;
    @Column(name = "FK_CURRENCY_ID")
    private Integer fkCurrency;
    @Size(max = 20)
    @Column(name = "CREDIT_LIMIT_CODE")
    private String creditLimitCode;
    @Size(max = 200)
    @Column(name = "CREDIT_LIMIT_NAME")
    private String creditLimitName;
    @Size(max = 1000)
    @Column(name = "CREDIT_LIMIT_DESC")
    private String creditLimitDesc;
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
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "CREDIT_LIMIT_BGCI_FACTORING")
    private BigDecimal creditLimitBgciFactoring;
    @Column(name = "CREDIT_LIMIT_BGCI_FACTORING_END_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creditLimitBgciFactoringEndDate;
    @Column(name = "CREDIT_NET_LIMIT")
    private BigDecimal creditNetLimit;
    @Column(name = "CREDIT_LIMIT_VALID_FROM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creditLimitValidFrom;
    @Column(name = "CREDIT_LIMIT_VALID_TO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creditLimitValidTo;
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
    @Column(name = "CUSTOM_ATTRIBUTE_6")
    private Double customAttribute6;
    @Column(name = "CUSTOM_ATTRIBUTE_7")
    private Double customAttribute7;
    @Column(name = "CUSTOM_ATTRIBUTE_8")
    private Double customAttribute8;
    @Size(max = 1)
    @Column(name = "is_temporary_ind")
    private String isTemporaryInd;
    @JoinColumn(name = "FK_INTERNAL_PARTY_ID", referencedColumnName = "PK_PARTY_ID")
    @ManyToOne
    private MasterParty fkInternalPartyId;
    /*@JoinColumn(name = "FK_PARTY_ID", referencedColumnName = "PK_PARTY_ID")
    @ManyToOne
    private MasterParty fkPartyId;*/
    @JoinColumn(name = "FK_UNIT_ID", referencedColumnName = "PK_UNIT_ID")
    @ManyToOne
    private MasterUnit fkUnitId;

}

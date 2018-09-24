/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olam.score.limit.domain;

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
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 *
 * @author ramachandran_n02
 */
@Entity
@Table(name = "MASTER_COUNTER_PARTY_LIMIT",  schema = "limit")
@XmlRootElement
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(of = { "pkCounterPartyLimitId" })
@ToString(of = { "pkCounterPartyLimitId" })
@NamedQueries({
    @NamedQuery(name = "MasterCounterPartyLimit.findAll", query = "SELECT m FROM MasterCounterPartyLimit m")})
public class MasterCounterPartyLimit implements Serializable {
	
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @NonNull
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MASTER_COUNTER_PARTY_GENERATOR")
	@SequenceGenerator(name="MASTER_COUNTER_PARTY_GENERATOR", sequenceName="limit.COUNTER_PARTY_LIMIT_ID_SEQ",allocationSize=1)
    @Column(name = "PK_COUNTER_PARTY_LIMIT_ID")
    private Integer pkCounterPartyLimitId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FK_COUNTER_PARTY_ID")
    private int fkPartyId;
    @Column(name = "FK_PROD_ID")
    private Integer fkProdId;
    @Column(name = "FK_UNIT_ID")
    private Integer unitId;
    @Column(name = "FK_UOM_ID")
    private Integer uomId;
    @Column(name = "FK_CURRENCY_ID")
    private Integer currUnique;
    @Column(name = "FK_STATUS_ID")
    private Integer fkStatusId;
    @Size(max = 100)
    @Column(name = "CREATED_BY")
    private String createdBy;
    @Column(name = "CREATED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Size(max = 100)
    @Column(name = "MODIFIED_BY")
    private String modifiedBy;
    @Column(name = "MODIFIED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "COUNTER_PARTY_FIXED_QTY_LIMIT")
    private BigDecimal counterPartyFixedQtyLimit;
    @Column(name = "COUNTER_PARTY_FIXED_VALUE_LIMIT")
    private BigDecimal counterPartyFixedValueLimit;
    @Column(name = "COUNTER_PARTY_FIXED_FORWARD_TENOR")
    private BigDecimal counterPartyFixedForwardTenor;
    @Column(name = "COUNTER_PARTY_DIFF_QTY_LIMIT")
    private BigDecimal counterPartyDiffQtyLimit;
    @Column(name = "COUNTER_PARTY_DIFF_VALUE_LIMIT")
    private BigDecimal counterPartyDiffValueLimit;
    @Column(name = "COUNTER_PARTY_DIFF_FORWARD_TENOR")
    private BigDecimal counterPartyDiffForwardTenor;
    @Column(name = "COUNTER_PARTY_M2M_LIMIT")
    private BigDecimal counterPartyM2mLimit;
    @Column(name = "COUNTER_PARTY_VAR_LIMIT")
    private BigDecimal counterPartyVarLimit;
    @Column(name = "COUNTER_PARTY_TOTAL_QTY_LIMIT")
    private BigDecimal counterPartyTotalQtyLimit;
    @Column(name = "COUNTER_PARTY_TOTAL_VALUE_LIMIT")
    private BigDecimal counterPartyTotalValueLimit;
    @Column(name = "COUNTER_PARTY_LIMIT_VALID_FROM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date counterPartyLimitValidFrom;
    @Column(name = "COUNTER_PARTY_LIMIT_VALID_TO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date counterPartyLimitValidTo;
    @JoinColumn(name = "FK_COUNTER_PARTY_LIMIT_TYPE_ID", referencedColumnName = "PK_COUNTER_PARTY_LIMIT_TYPE_ID")
    @ManyToOne
    private MasterCounterPartyLimitType fkCounterPartyLimitTypeId;
    @JoinColumn(name = "FK_LIMIT_ALERT_LEVEL_ID", referencedColumnName = "PK_LIMIT_ALERT_LEVEL_ID")
    @ManyToOne
    private MasterLimitAlertLevel fkLimitAlertLevelKey;
    
    @Column(name = "FK_INTERNAL_PARTY_ID")
    private Integer internalPartyId;
    
   
}

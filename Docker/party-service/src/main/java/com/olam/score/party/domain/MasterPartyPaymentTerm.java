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
@Table(name = "MASTER_PARTY_PAYMENT_TERM")
@NamedQueries({
		@NamedQuery(name = "MasterPartyPaymentTerm.findAll", query = "SELECT m FROM MasterPartyPaymentTerm m") })
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(of = { "pkPartyPaymentTermId" })
@ToString(of = { "pkPartyPaymentTermId" })
public class MasterPartyPaymentTerm implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MASTER_PARTY_PAYMENT_TERM_GENERATOR")
	@SequenceGenerator(name="MASTER_PARTY_PAYMENT_TERM_GENERATOR", sequenceName="party.PARTY_PAYMENT_TERM_ID_SEQ",allocationSize=1)
	@NotNull
	@NonNull
	@Column(name = "PK_PARTY_PAYMENT_TERM_ID")
	private Integer pkPartyPaymentTermId;
	@Column(name = "FK_PAYMENT_TERM_ID")
	private Integer paymentTermId;
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
	// @Max(value=?) @Min(value=?)//if you know range of your decimal fields
	// consider using these annotations to enforce field validation
	@Column(name = "CUSTOM_ATTRIBUTE_6")
	private Double customAttribute6;
	@Column(name = "CUSTOM_ATTRIBUTE_7")
	private Double customAttribute7;
	@Column(name = "CUSTOM_ATTRIBUTE_8")
	private Double customAttribute8;
	@JoinColumn(name = "FK_PARTY_ID", referencedColumnName = "PK_PARTY_ID")
    @ManyToOne
	private MasterParty fkPartyId;

}

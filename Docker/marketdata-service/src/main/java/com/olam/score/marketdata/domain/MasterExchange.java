/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olam.score.marketdata.domain;

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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "MASTER_EXCHANGE",  schema = "marketdata")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterExchange.findAll", query = "SELECT m FROM MasterExchange m")})
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(of = { "pkXchngId" })
@ToString(of = { "pkXchngId" })
public class MasterExchange implements Serializable {
	
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @NonNull
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MASTER_EXCHANGE_GENERATOR")
	@SequenceGenerator(name="MASTER_EXCHANGE_GENERATOR", sequenceName="marketdata.EXCHANGE_ID_SEQ",allocationSize=1)
    @Column(name = "PK_EXCHANGE_ID")
    private Integer pkXchngId;
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
    @Column(name = "FK_STATUS_ID")
    private Integer fkStatusId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FK_PARTY_ID")
    private int fkParentPartyId;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_EXCHANGE_ID")
    private Collection<MasterXchngProductAssociation> masterXchngProductAssociationCollection;

    public MasterExchange(Integer pkXchngId, int fkParentPartyId) {
        this.pkXchngId = pkXchngId;
        this.fkParentPartyId = fkParentPartyId;
    }

}

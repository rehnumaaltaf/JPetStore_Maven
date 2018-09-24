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
@Table(name = "MASTER_EXCHANGE_PROD_ASSOC",  schema = "marketdata")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterXchngProductAssociation.findAll", query = "SELECT m FROM MasterXchngProductAssociation m")})
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(of = { "pkXchngProdAssoId" })
@ToString(of = { "pkXchngProdAssoId" })
public class MasterXchngProductAssociation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @NonNull
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MASTER_EXCHANGE_ASSOC_GENERATOR")
	@SequenceGenerator(name="MASTER_EXCHANGE_ASSOC_GENERATOR", sequenceName="marketdata.EXCHANGE_PROD_ASSOC_ID_SEQ",allocationSize=1)
    @Column(name = "PK_EXCHANGE_PROD_ASSOC_ID")
    private Integer pkXchngProdAssoId;
    @Column(name = "FK_PROD_ID")
    private Integer productUnique;
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
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "SCREEN_NAME")
    private String xchngProdAssoScreenName;
    @Size(max = 200)
    @Column(name = "HUB_NAME")
    private String hub;
    @Size(max = 20)
    @Column(name = "CONTRACT_SYMBOL")
    private String symbol;
    @Column(name = "CONTRACT_SIZE")
    private Integer size;
    @Column(name = "FK_LOT_UOM_ID")
    private Integer lotOrContractSize;
    @Column(name = "FK_QUOTE_CURRENCY_ID")
    private Integer priceQuotationCurrency;
    @Column(name = "FK_QUOTE_UOM_ID")
    private Integer priceQuotationUoM;
    @Column(name = "FK_HOLIDAY_CAL_ID")
    private Integer holidayCalendar;
    @Column(name = "MINIMUM_PRICE_MOVEMENT")
    private Integer minimumPriceMovement;
    @Column(name = "FK_MIN_PRC_MOVEMENT_CURRENCY_ID")
    private Integer minimumPriceMovementCurrencyId;
    @Column(name = "FK_MIN_PRC_MOVEMENT_UOM_ID")
    private Integer minimumPriceMovementUoMId;
    @JoinColumn(name = "FK_EXCHANGE_ID", referencedColumnName = "PK_EXCHANGE_ID")
    @ManyToOne
    private MasterExchange fkXchngId;

    public MasterXchngProductAssociation(Integer pkXchngProdAssoId, String xchngProdAssoScreenName) {
        this.pkXchngProdAssoId = pkXchngProdAssoId;
        this.xchngProdAssoScreenName = xchngProdAssoScreenName;
    }

}

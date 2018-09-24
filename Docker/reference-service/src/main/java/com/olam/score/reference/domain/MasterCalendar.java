package com.olam.score.reference.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Table(name = "MASTER_CALENDAR_SETUP", schema = "reference")
@NamedQueries({ @NamedQuery(name = "MasterCalendar.findAll", query = "SELECT m FROM MasterCalendar m") })
@Data
public class MasterCalendar implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	//@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CALENDAR_SETUP_ID_SEQ" )
	@SequenceGenerator(name = "CALENDAR_SETUP_ID_SEQ", sequenceName = "reference.CALENDAR_SETUP_ID_SEQ", initialValue = 1, allocationSize = 1, schema = "reference")
	@Column(name ="PK_CALENDAR_SETUP_ID")
	private Integer calendarSetupId;
	
	@NotNull
	@Column(name = "CALENDAR_CODE")
	private String calendarCode;
	
	@NotNull
	@Column(name = "CALENDAR_NAME")
	private String calendarName;

	@Column(name = "CALENDAR_DESC")
	private String calendarDesc;
	
	@Column(name = "FK_CALENDAR_TYPE_REF_ID")
	private Integer calendarTypeId;
	
	
	
	@Column(name = "FK_EXCHANGE_PARTY_ID")
	private Integer exchangeId;
	
	
	@Column(name = "FK_PROD_ID")
	private Integer productId;
	

	
	@Column(name = "FK_STATUS_ID")
	private Integer status;
	
	//@NotNull
	@Column(name = "TICKER_CODE")
	private String trickerCode;
	
	//@NotNull
	@Column(name = "PHYSICAL_DELIVERY_START_DATE")
	private Date phyDlvryStrtDate;
	
	//@NotNull
	@Column(name = "PHYSICAL_DELIVERY_END_DATE")
	private Date phyDlvryEndDate;
	
	//@NotNull
	@Column(name = "FK_FUTURES_CALENDAR_SETUP_ID")
	private Integer  futursCalendarId;
	
	//@NotNull
	@Column(name = "DELIVERY_MONTH")
	private String dlvryMonth;
	//@NotNull
	@Column(name = "FIRST_TRADE_DATE")
	private Date firstTradeDate;
	//@NotNull
	@Column(name = "LAST_TRADE_DATE")
	private Date lastTradeDate;
	//@NotNull
	@Column(name = "FIRST_NOTICE_DATE")
	private Date firstNoticeDate;
	//@NotNull
	@Column(name = "LAST_NOTICE_DATE")
	private Date lastNoticeDate;
	//@NotNull
	@Column(name = "FIRST_DELIVERY_DATE")
	private Date firstDlvryDate;
	//@NotNull
	@Column(name = "LAST_DELIVERY_DATE")
	private Date lastDlvryDate;
	//@NotNull
	@Column(name = "FINAL_SETTLEMENT_DATE")
	private Date finalStlmntDate;
	//@NotNull
	@Column(name = "SPREAD_MONTH_1")
	private String spreadMonth1;
	///@NotNull
	@Column(name = "SPREAD_MONTH_2")
	private String spreadMonth2;
	@NotNull
	@Column(name = "FK_FOREX_FORWARD_TENOR_ID")
	private Integer tenorId;

	//@NotNull
	@Column(name = "SPAN")
	private String span;
	
	//@NotNull
	@Column(name = "DURATION_TYPE")
	private String durationType;
	
	@Column(name = "CREATED_BY")
	private String createdBy;
	
	@Column(name = "CREATED_DATE")
	private Date  createdDate;
	
	@Column(name = "MODIFIED_BY")
	private String modifiedBy;
	@Column(name = "MODIFIED_DATE")
	private Date  modifiedDate;
	
	
	
}

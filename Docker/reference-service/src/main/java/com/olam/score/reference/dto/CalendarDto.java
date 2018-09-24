package com.olam.score.reference.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class CalendarDto {

	private Integer calendarSetupId;
	@NotNull(message = "calendarCode_Mandatory")
	@Size(min = 1, max = 20)
	private String calendarCode;
	@NotNull(message = "calendarName_Mandatory")
	@Size(min = 1, max = 200)
	private String calendarName;
	
	private String calendarDesc;
	
	//@NotNull(message = "calendarTypeId_Mandatory")
	private Integer calendarTypeId;
	//@NotNull(message = "calendarTypeName_Mandatory")
	@Size(min = 1, max = 500)
	private String calendarTypeName;
	private Integer exchangeId;
	private String exchangeName;
	private Integer productId;
	private String productName;
	private Integer status;
	//@NotNull(message = "trickerCode_Mandatory")
	@Size(min = 1, max = 20)
	private String trickerCode;
	//@NotNull(message = "phyDlvryStrtDate_Mandatory")
	private Date phyDlvryStrtDate;
	//@NotNull(message = "phyDlvryEndDate_Mandatory")
	private Date phyDlvryEndDate;
	//@NotNull(message = "futursCalendarId_Mandatory")
	private Integer futursCalendarId;
	//@NotNull(message = "futursCalendarName_Mandatory")
	private String futursCalendarName;
	//@NotNull(message = "dlvryMonth_Mandatory")
	@Size(min = 1, max = 200)
	private String dlvryMonth;
	//@NotNull(message = "firstTradeDate_Mandatory")
	private Date firstTradeDate;
	//@NotNull(message = "lastTradeDate_Mandatory")
	private Date lastTradeDate;
	//@NotNull(message = "firstNoticeDate_Mandatory")
	private Date firstNoticeDate;
	//@NotNull(message = "lastNoticeDate_Mandatory")
	private Date lastNoticeDate;
	//@NotNull(message = "firstDlvryDate_Mandatory")
	private Date firstDlvryDate;
	//@NotNull(message = "lastDlvryDate_Mandatory")
	private Date lastDlvryDate;
	//@NotNull(message = "finalStlmntDate_Mandatory")
	private Date finalStlmntDate;
	//@NotNull(message = "spreadMonth1_Mandatory")
	@Size(min = 1, max = 200)
	private String spreadMonth1;
	//@NotNull(message = "spreadMonth2_Mandatory")
	@Size(min = 1, max = 200)
	private String spreadMonth2;
	//@NotNull(message = "tenorId_Mandatory")
	@Size(min = 1, max = 20)
	private Integer tenorId;
	//@NotNull(message = "tenorName_Mandatory")
	@Size(min = 1, max = 200)
	private String tenorName;
	//@NotNull(message = "span_Mandatory")
	private String span;
	//@NotNull(message = "durationType_Mandatory")
	private String durationType;

}

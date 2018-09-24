package com.olam.score.reference.domain;

import java.io.Serializable;
import java.util.Date;

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
@Table(name = "MASTER_CALENDAR_TYPE_REF", schema = "reference")
@Data
public class MasterCalendarTypeRef implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	// @Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = " CALENDAR_TYPE_REF_ID_SEQ")
	@SequenceGenerator(name = " CALENDAR_TYPE_REF_ID_SEQ", sequenceName = "reference. CALENDAR_TYPE_REF_ID_SEQ", initialValue = 1, allocationSize = 1, schema = "reference")
	@Column(name = "PK_CALENDAR_TYPE_REF_ID")
	private Integer calendarTypeRefId;

	@NotNull
	@Column(name = "CALENDAR_TYPE_CODE")
	private String calendarTypeCode;

	@NotNull
	@Column(name = "CALENDAR_TYPE_NAME")
	private String calendarTypeName;

	@Column(name = "CALENDAR_TYPE_DESC")
	private String calendarTypeDesc;

	@Column(name = "CREATED_BY")
	private String createdBy;

	@Column(name = "CREATED_DATE")
	private Date createdDate;

	@Column(name = "MODIFIED_BY")
	private String modifiedBy;
	@Column(name = "MODIFIED_DATE")
	private Date modifiedDate;
	@Column(name = "FK_STATUS_ID")
	private Integer fkStatusId;

}

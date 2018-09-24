package com.olam.score.finance.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Table(name = "MASTER_TAX_RATE_DETAIL")
@Data
public class TaxRateDetails {

	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TAX_RATE_DETAIL_ID_SEQ")
	@SequenceGenerator(name = "TAX_RATE_DETAIL_ID_SEQ", sequenceName = "finance.TAX_RATE_DETAIL_ID_SEQ", initialValue = 1, allocationSize = 1)
	@Column(name = "PK_TAX_RATE_DETAIL_ID")
	private Integer taxRateDetailId;

	@JoinColumn(name = "FK_TAX_RATE_ID", referencedColumnName = "PK_TAX_RATE_ID")
	@NotNull
	@Column(name = "FK_TAX_RATE_ID")
	private Integer taxCodeUID;

	@Column(name = "TAX_RATE_PERCENTAGE")
	private BigDecimal taxRatePercentage;

	@Column(name = "EFFECTIVE_FROM")
	@Temporal(TemporalType.TIMESTAMP)
	private Date effectiveFrom;

	@Column(name = "EFFECTIVE_TO")
	@Temporal(TemporalType.TIMESTAMP)
	private Date expirationDate;

}

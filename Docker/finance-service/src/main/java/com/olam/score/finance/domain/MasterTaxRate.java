package com.olam.score.finance.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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



@Entity
@Table(name = "MASTER_TAX_RATE", schema = "finance")
@NamedQueries({ @NamedQuery(name = "MasterTaxRate.findAll", query = "SELECT m FROM MasterTaxRate m") })
@Data
public class MasterTaxRate implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TAX_RATE_ID_SEQ")
	@SequenceGenerator(name = "TAX_RATE_ID_SEQ", sequenceName = "finance.TAX_RATE_ID_SEQ", initialValue = 1, allocationSize = 1, schema = "finance")
	@Column(name = "PK_TAX_RATE_ID")
	private Integer taxCodeUID;
	@NotNull
	@Column(name = "TAX_RATE_CODE")
	@Size(min = 1, max = 20)
	private String taxCode;
	@NotNull
	@Size(min = 1, max = 200)
	@Column(name = "TAX_RATE_NAME")
	private String taxName;
	@Size(max = 500)
	@Column(name = "TAX_RATE_DESC")
	private String taxDescription;
//	@NotNull
	@Column(name = "FK_GEO_ID")
	private Integer taxCountryID;
	@Size(max = 20)
	@Column(name = "GOVT_TAX_REF")
	private String govTaxRef;
	@Size(max = 1)
	@Column(name = "TAX_BY_LINE_IND")
	private String taxByLine;
	@Column(name = "FK_STATUS_ID")
	private Integer status;
	@Column(name = "CREATED_BY")
	@Size(max = 100)
	private String createdBy;
	@Column(name = "CREATED_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	@Column(name = "MODIFIED_BY")
	@Size(max = 100)
	private String modifiedBy;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MODIFIED_DATE")
	private Date modifiedDate;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "taxCodeUID")
	private List<TaxRateDetails> taxRateDetails;

}

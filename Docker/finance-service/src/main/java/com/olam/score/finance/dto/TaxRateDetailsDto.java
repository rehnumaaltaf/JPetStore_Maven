package com.olam.score.finance.dto;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;

import lombok.Data;


@Data
public class TaxRateDetailsDto {


	private Integer taxRateDetailId;
	@NotNull
	private Integer taxCodeUID;
	@NotNull(message="taxRaxPercentage_should_not_be_null")	
	private BigDecimal taxRatePercentage;
	@NotNull(message="effectiveFrom_Mandatory")
	private Date effectiveFrom;
	private Date expirationDate;
	
	
}

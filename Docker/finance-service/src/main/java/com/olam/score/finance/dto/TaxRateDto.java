package com.olam.score.finance.dto;

import java.util.Date;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;


@Data
public class TaxRateDto {
	
	private Integer taxCodeUID;

	@NotNull(message="taxCode_Mandatory")
	@Size(min = 1, max = 20)
	private String taxCode;
	@NotNull(message="taxName_Mandatory")
	@Size(min = 1, max = 200)
	private String taxName;
	/*@NotNull(message="taxCountry_Mandatory")*/
	private Integer taxCountryID;
	private String taxCountryName;
	@NotNull(message="govTaxRef_Mandatory")
	@Size(max = 20)
	private String govTaxRef;
	@NotNull(message="taxByLine_Mandatory")
	@Size(max = 200)
	private String taxByLine;
	
	@Size(min = 1, max = 20)
	@NotNull(message="status_Mandatory")
	private String status;
	private Integer taxRate;
//	@Size(max = 100)
//	private String createdBy;
//	private Date createdDate;
//	@Size(max = 100)
//	private String modifiedBy;
//	private Date modifiedDate;
	@Size(max = 500)
	private String taxDescription;
	private List<TaxRateDetailsDto> taxRateDetails;

}

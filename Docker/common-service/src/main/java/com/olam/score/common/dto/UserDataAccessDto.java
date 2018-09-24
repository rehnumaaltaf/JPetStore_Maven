package com.olam.score.common.dto;


import java.io.Serializable;

import lombok.Data;

@Data
public class UserDataAccessDto implements Serializable  {

	private static final long serialVersionUID = 1L;
	
	private Integer partyId;
	private String partyName;
	private String partyCode;
	private Integer prodId;
	private String prodCode;
	private String prodName;
	private Integer unitId;
	private Integer portfolio;
}

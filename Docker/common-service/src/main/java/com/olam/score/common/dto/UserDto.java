package com.olam.score.common.dto;

import java.util.List;
import java.util.Locale;
import java.io.Serializable;
import lombok.Data;

@Data
public class UserDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer userId;
	private String name;
	private Locale locale;
	private String secretKey;
	private List<UserResourceAccessDto>	userResourceAccessList;
	private List<UserDataAccessDto>	userDataAccessList;
}

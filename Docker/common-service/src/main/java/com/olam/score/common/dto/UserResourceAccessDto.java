package com.olam.score.common.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class UserResourceAccessDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer featureId;
	private String featureName;
	private Integer entityId;
	private String entityName;
	private Integer moduleId;
	private String moduleName;
	private String endPointMethod;
	private String endPointUrl;
	private String operation;
	private List<String> allowedAttributes;
	private List<String> deniedAttributes;
}

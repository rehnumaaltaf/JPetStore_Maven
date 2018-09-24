/**
 * 
 */
package com.olam.score.reference.dto;

import lombok.Data;

/**
 * @author umme.hani01
 *
 */
public @Data class MasterLanguageDTO 
{
	private Integer languageId;
	private String languageCode;
	private String languageFullName;
	private Integer fkStatusId;
	private String statusName;
}

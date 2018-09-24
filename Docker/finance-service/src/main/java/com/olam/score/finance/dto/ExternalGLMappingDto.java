package com.olam.score.finance.dto;

import com.olam.score.common.dto.BaseDto;

public class ExternalGLMappingDto extends BaseDto{
	// @NotBlank, @Digits, @Email, @Max, @Min validations can also be used
		private Integer externalGlMappingId;
	    //@NotNull(message = "notNull_externalGlMappingName")
	    //@NotEmpty(message = "mandatory_externalGlMappingName")
		private String externalGlMappingName;
	    //@NotNull(message = "notNull_externalGlMappingCode")
	    //@NotEmpty(message = "mandatory_externalGlMappingCode")
		private String externalGlMappingCode;
		/*private String createdBy;		
		private Timestamp createdDate;
		private String modifiedBy;
		private Timestamp modifiedDate;*/
		private String statusName;
		private Integer glId;
		private Integer externalSystemRefId;
		private String mappingType;
		
		
		public String getMappingType() {
			return mappingType;
		}
		public void setMappingType(String mappingType) {
			this.mappingType = mappingType;
		}
		public Integer getExternalGlMappingId() {
			return externalGlMappingId;
		}
		public void setExternalGlMappingId(Integer externalGlMappingId) {
			this.externalGlMappingId = externalGlMappingId;
		}
		public String getExternalGlMappingName() {
			return externalGlMappingName;
		}
		public void setExternalGlMappingName(String externalGlMappingName) {
			this.externalGlMappingName = externalGlMappingName;
		}
		public String getExternalGlMappingCode() {
			return externalGlMappingCode;
		}
		public void setExternalGlMappingCode(String externalGlMappingCode) {
			this.externalGlMappingCode = externalGlMappingCode;
		}
		//extending BaseDto
		/*public String getCreatedBy() {
			return createdBy;
		}
		public void setCreatedBy(String createdBy) {
			this.createdBy = createdBy;
		}
		public Timestamp getCreatedDate() {
			return createdDate;
		}
		public void setCreatedDate(Timestamp createdDate) {
			this.createdDate = createdDate;
		}
		public String getModifiedBy() {
			return modifiedBy;
		}
		public void setModifiedBy(String modifiedBy) {
			this.modifiedBy = modifiedBy;
		}
		public Timestamp getModifiedDate() {
			return modifiedDate;
		}
		public void setModifiedDate(Timestamp modifiedDate) {
			this.modifiedDate = modifiedDate;
		}*/
		/*public String getStatusName() {
			return statusName;
		}
		public void setStatusName(String statusName) {
			statusName = statusName;
		}
		public Integer getGlId() {
			return glId;
		}
		public void setGlId(Integer glId) {
			glId = glId;
		}
		public Integer getExternalSystemRefId() {
			return externalSystemRefId;
		}
		public void setExternalSystemRefId(Integer externalSystemRefId) {
			externalSystemRefId = externalSystemRefId;
		}*/
		public String getStatusName() {
			return statusName;
		}
		public void setStatusName(String statusName) {
			this.statusName = statusName;
		}
		public Integer getGlId() {
			return glId;
		}
		public void setGlId(Integer glId) {
			this.glId = glId;
		}
		public Integer getExternalSystemRefId() {
			return externalSystemRefId;
		}
		public void setExternalSystemRefId(Integer externalSystemRefId) {
			this.externalSystemRefId = externalSystemRefId;
		}
}

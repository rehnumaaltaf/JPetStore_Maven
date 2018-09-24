package com.olam.score.finance.dto;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.olam.score.common.dto.BaseDto;
import com.olam.score.finance.domain.MasterGl;

public class GLBasicDto extends BaseDto{
	// @NotBlank, @Digits, @Email, @Max, @Min validations can also be used
			private Integer glId;
			/*private String createdBy;
			private Timestamp createdDate;
			private String modifiedBy;
			private Timestamp modifiedDate;*/
			@NotNull(message = "notNull_glName")
			@NotEmpty(message = "mandatory_glName")
			private String glName;
			@NotNull(message = "notNull_glCode")
			@NotEmpty(message = "mandatory_glCode")
			private String glCode;			
			private String glDesc;
			private String glIsGroup;
			private GLBasicDto parentGlIdObj;
			public GLBasicDto getParentGlIdObj() {
				return parentGlIdObj;
			}
			public void setParentGlIdObj(GLBasicDto parentGlIdObj) {
				this.parentGlIdObj = parentGlIdObj;
			}
			private Integer parentGlId;
			/*@NotNull(message = "notNull_partyName")
			@NotEmpty(message = "mandatory_partyName")*/
			private String partyName;
			@NotNull(message = "notNull_partyId")
			/*@NotEmpty(message = "mandatory_partyId")*/
			private Integer partyId;			
			private String statusName;
			private String parentGl;	
			private Integer glTypeRefId;
			/*@NotNull(message = "notNull_glTypeRefName")*/
			private String glTypeRefName;
			
			
			
			public Integer getGlTypeRefId() {
				return glTypeRefId;
			}
			public void setGlTypeRefId(Integer glTypeRefId) {
				this.glTypeRefId = glTypeRefId;
			}
			public String getGlTypeRefName() {
				return glTypeRefName;
			}
			public void setGlTypeRefName(String glTypeRefName) {
				this.glTypeRefName = glTypeRefName;
			}
			public String getParentGl() {
				return parentGl;
			}
			public void setParentGl(String parentGl) {
				this.parentGl = parentGl;
			}
			/*public Integer getGlId() {
				return GlId;
			}
			public void setGlId(Integer glId) {
				GlId = glId;
			}*/
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
			public String getGlName() {
				return glName;
			}
			public Integer getGlId() {
				return glId;
			}
			public void setGlId(Integer glId) {
				this.glId = glId;
			}
			public void setGlName(String glName) {
				this.glName = glName;
			}
			public String getGlCode() {
				return glCode;
			}
			public void setGlCode(String glCode) {
				this.glCode = glCode;
			}
			public String getGlDesc() {
				return glDesc;
			}
			public void setGlDesc(String glDesc) {
				this.glDesc = glDesc;
			}
			public String getGlIsGroup() {
				return glIsGroup;
			}
			public void setGlIsGroup(String glIsGroup) {
				this.glIsGroup = glIsGroup;
			}
			public Integer getParentGlId() {
				return parentGlId;
			}
			public void setParentGlId(Integer parentGlId) {
				this.parentGlId = parentGlId;
			}
			public String getPartyName() {
				return partyName;
			}
			public void setPartyName(String partyName) {
				this.partyName = partyName;
			}
			public String getStatusName() {
				return statusName;
			}
			public void setStatusName(String statusName) {
				this.statusName = statusName;
			}
			public Integer getPartyId() {
				return partyId;
			}
			public void setPartyId(Integer partyId) {
				this.partyId = partyId;
			}
}

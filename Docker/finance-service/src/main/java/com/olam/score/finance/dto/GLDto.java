
package com.olam.score.finance.dto;



import java.util.List;

import javax.validation.*;

public class GLDto {
	// @NotBlank, @Digits, @Email, @Max, @Min validations can also be used
	@Valid
	private GLBasicDto glBasicDto;
	@Valid
	private List<ExternalGLMappingDto> externalGLMappingDto;
	//private ExternalGLMappingDto externalGLMappingDto;
	private String statusName;
	public GLBasicDto getGlBasicDto() {
		return glBasicDto;
	}
	public void setGlBasicDto(GLBasicDto glBasicDto) {
		this.glBasicDto = glBasicDto;
	}
	public List<ExternalGLMappingDto> getExternalGLMappingDto() {
		return externalGLMappingDto;
	}
	public void setExternalGLMappingDto(List<ExternalGLMappingDto> externalGLMappingDto) {
		this.externalGLMappingDto = externalGLMappingDto;
	}
	/*public ExternalGLMappingDto getExternalGLMappingDto() {
		return externalGLMappingDto;
	}
	public void setExternalGLMappingDto(ExternalGLMappingDto externalGLMappingDto) {
		this.externalGLMappingDto = externalGLMappingDto;
	}*/
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	
	/*public GLBasicDto getGlBasicDto() {
		return glBasicDto;
	}
	public void setGlBasicDto(GLBasicDto glBasicDto) {
		this.glBasicDto = glBasicDto;
	}
	public ExternalGLMappingDto getExternalGLMappingDto() {
		return externalGLMappingDto;
	}
	public void setExternalGLMappingDto(ExternalGLMappingDto externalGLMappingDto) {
		this.externalGLMappingDto = externalGLMappingDto;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}*/
	
	
	
	
}

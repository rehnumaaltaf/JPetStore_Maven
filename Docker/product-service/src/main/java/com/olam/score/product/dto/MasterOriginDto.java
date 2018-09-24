package com.olam.score.product.dto;

import java.util.List;

public class MasterOriginDto {
	
	private OriginDto originDto;
	private List<OriginGradeGroupDto> originGradeGroupDto;
	private List<DefaultPackingDto> defaultPackingDto;
	private List<ExternalSystemMappingDto> externalSystemMappingDto;
	private String status;
	
	public List<ExternalSystemMappingDto> getExternalSystemMappingDto() {
		return externalSystemMappingDto;
	}
	public void setExternalSystemMappingDto(List<ExternalSystemMappingDto> externalSystemMappingDto) {
		this.externalSystemMappingDto = externalSystemMappingDto;
	}
	public List<OriginGradeGroupDto> getOriginGradeGroupDto() {
		return originGradeGroupDto;
	}
	public void setOriginGradeGroupDto(List<OriginGradeGroupDto> originGradeGroupDto) {
		this.originGradeGroupDto = originGradeGroupDto;
	}
	
	
	
	
	
	
	
	public List<DefaultPackingDto> getDefaultPackingDto() {
		return defaultPackingDto;
	}
	public void setDefaultPackingDto(List<DefaultPackingDto> defaultPackingDto) {
		this.defaultPackingDto = defaultPackingDto;
	}

	
	
	public OriginDto getOriginDto() {
		return originDto;
	}
	public void setOriginDto(OriginDto originDto) {
		this.originDto = originDto;
	}
	/*public List<OriginRegionDto> getOriginRegionDto() {
		return originRegionDto;
	}
	public void setOriginRegionDto(List<OriginRegionDto> originRegionDto) {
		this.originRegionDto = originRegionDto;
	}
	public List<OriginCupProfileDto> getOriginCupProfileDto() {
		return originCupProfileDto;
	}
	public void setOriginCupProfileDto(List<OriginCupProfileDto> originCupProfileDto) {
		this.originCupProfileDto = originCupProfileDto;
	}*/
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	//private List<OriginRegionDto> originRegionDto;
		//private List<OriginCupProfileDto> originCupProfileDto;
	
}

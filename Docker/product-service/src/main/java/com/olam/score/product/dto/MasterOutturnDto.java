package com.olam.score.product.dto;

import java.util.List;

import javax.validation.Valid;

public class MasterOutturnDto {
	@Valid
	OutturnRawGradeDto outturnRawGradeDto;
	@Valid
	List<OutturnRatioDto> outturnRatioDto;
	String statusName;
	List<Integer> deletedIds;
	
	
	
	public OutturnRawGradeDto getOutturnRawGradeDto() {
		return outturnRawGradeDto;
	}
	public void setOutturnRawGradeDto(OutturnRawGradeDto outturnRawGradeDto) {
		this.outturnRawGradeDto = outturnRawGradeDto;
	}
	public List<OutturnRatioDto> getOutturnRatioDto() {
		return outturnRatioDto;
	}
	public void setOutturnRatioDto(List<OutturnRatioDto> outturnRatioDto) {
		this.outturnRatioDto = outturnRatioDto;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public List<Integer> getDeletedIds() {
		return deletedIds;
	}
	public void setDeletedIds(List<Integer> deletedIds) {
		this.deletedIds = deletedIds;
	}
	
	
	
	
	

}

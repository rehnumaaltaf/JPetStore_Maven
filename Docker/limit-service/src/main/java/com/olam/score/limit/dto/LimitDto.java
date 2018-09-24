package com.olam.score.limit.dto;

import java.util.List;

import com.olam.score.common.dto.BaseDto;

public class LimitDto extends BaseDto {

	private Integer limitHeaderId;
	private String limitHeaderNo;
	private Integer limitBasisTypeId;
	private String limitBasisTypeCode;
	private String limitBasisTypeName;
	private Integer limitBasisId;
	private String limitBasisName;
	private String limitBasisCode;
	private Integer additionallimitBasisTypeId;
	private String additionallimitBasisTypeCode;
	private String additionallimitBasisTypeName;
	private Integer additionallimitBasisId;
	private String additionallimitBasisName;
	private String additionallimitBasisCode;

	private List<CommentsDto> comments;
	private List<LimitDetails> limitDetails;

	private Integer statusId;
	private String statusName;

	public Integer getLimitHeaderId() {
		return limitHeaderId;
	}

	public void setLimitHeaderId(Integer limitHeaderId) {
		this.limitHeaderId = limitHeaderId;
	}

	public String getLimitHeaderNo() {
		return limitHeaderNo;
	}

	public void setLimitHeaderNo(String limitHeaderNo) {
		this.limitHeaderNo = limitHeaderNo;
	}

	public Integer getLimitBasisTypeId() {
		return limitBasisTypeId;
	}

	public void setLimitBasisTypeId(Integer limitBasisTypeId) {
		this.limitBasisTypeId = limitBasisTypeId;
	}

	public String getLimitBasisTypeCode() {
		return limitBasisTypeCode;
	}

	public void setLimitBasisTypeCode(String limitBasisTypeCode) {
		this.limitBasisTypeCode = limitBasisTypeCode;
	}

	public String getLimitBasisTypeName() {
		return limitBasisTypeName;
	}

	public void setLimitBasisTypeName(String limitBasisTypeName) {
		this.limitBasisTypeName = limitBasisTypeName;
	}

	public Integer getLimitBasisId() {
		return limitBasisId;
	}

	public void setLimitBasisId(Integer limitBasisId) {
		this.limitBasisId = limitBasisId;
	}

	public String getLimitBasisName() {
		return limitBasisName;
	}

	public void setLimitBasisName(String limitBasisName) {
		this.limitBasisName = limitBasisName;
	}

	public String getLimitBasisCode() {
		return limitBasisCode;
	}

	public void setLimitBasisCode(String limitBasisCode) {
		this.limitBasisCode = limitBasisCode;
	}

	public Integer getAdditionallimitBasisTypeId() {
		return additionallimitBasisTypeId;
	}

	public void setAdditionallimitBasisTypeId(Integer additionallimitBasisTypeId) {
		this.additionallimitBasisTypeId = additionallimitBasisTypeId;
	}

	public String getAdditionallimitBasisTypeCode() {
		return additionallimitBasisTypeCode;
	}

	public void setAdditionallimitBasisTypeCode(String additionallimitBasisTypeCode) {
		this.additionallimitBasisTypeCode = additionallimitBasisTypeCode;
	}

	public String getAdditionallimitBasisTypeName() {
		return additionallimitBasisTypeName;
	}

	public void setAdditionallimitBasisTypeName(String additionallimitBasisTypeName) {
		this.additionallimitBasisTypeName = additionallimitBasisTypeName;
	}

	public Integer getAdditionallimitBasisId() {
		return additionallimitBasisId;
	}

	public void setAdditionallimitBasisId(Integer additionallimitBasisId) {
		this.additionallimitBasisId = additionallimitBasisId;
	}

	public String getAdditionallimitBasisName() {
		return additionallimitBasisName;
	}

	public void setAdditionallimitBasisName(String additionallimitBasisName) {
		this.additionallimitBasisName = additionallimitBasisName;
	}

	public String getAdditionallimitBasisCode() {
		return additionallimitBasisCode;
	}

	public void setAdditionallimitBasisCode(String additionallimitBasisCode) {
		this.additionallimitBasisCode = additionallimitBasisCode;
	}

	public List<CommentsDto> getComments() {
		return comments;
	}

	public void setComments(List<CommentsDto> comments) {
		this.comments = comments;
	}

	public List<LimitDetails> getLimitDetails() {
		return limitDetails;
	}

	public void setLimitDetails(List<LimitDetails> limitDetails) {
		this.limitDetails = limitDetails;
	}

	public Integer getStatusId() {
		return statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

}

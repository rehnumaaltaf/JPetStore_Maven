package com.olam.score.product.dto;

import java.util.List;

import com.olam.score.common.dto.BaseDto;

public class BlendTemplateDto extends BaseDto {
	
	private Integer pkBlendTemplateId;
	private String templateCode;
	private String templateName;
	private String templateDesc;
	private Integer fkStatusId;
	private String statusName;
	private String toValidate;
	private  List<Integer> deleteblend;
	private List<BlendInputDto> blendInputList;
	private List<BlendOutputDto> blendOutputList;
	private List<BlendInputCertificationDto> blendInputCertificationList;
	
	public String getToValidate() {
		return toValidate;
	}
	public void setToValidate(String toValidate) {
		this.toValidate = toValidate;
	}
	public List<Integer> getDeleteblend() {
		return deleteblend;
	}
	public void setDeleteblend(List<Integer> deleteblend) {
		this.deleteblend = deleteblend;
	}
	public Integer getPkBlendTemplateId() {
		return pkBlendTemplateId;
	}
	public void setPkBlendTemplateId(Integer pkBlendTemplateId) {
		this.pkBlendTemplateId = pkBlendTemplateId;
	}
	public String getTemplateCode() {
		return templateCode;
	}
	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}
	public String getTemplateName() {
		return templateName;
	}
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	public String getTemplateDesc() {
		return templateDesc;
	}
	public void setTemplateDesc(String templateDesc) {
		this.templateDesc = templateDesc;
	}
	public Integer getFkStatusId() {
		return fkStatusId;
	}
	public void setFkStatusId(Integer fkStatusId) {
		this.fkStatusId = fkStatusId;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public List<BlendInputDto> getBlendInputList() {
		return blendInputList;
	}
	public void setBlendInputList(List<BlendInputDto> blendInputList) {
		this.blendInputList = blendInputList;
	}
	public List<BlendOutputDto> getBlendOutputList() {
		return blendOutputList;
	}
	public void setBlendOutputList(List<BlendOutputDto> blendOutputList) {
		this.blendOutputList = blendOutputList;
	}
	public List<BlendInputCertificationDto> getBlendInputCertificationList() {
		return blendInputCertificationList;
	}
	public void setBlendInputCertificationList(List<BlendInputCertificationDto> blendInputCertificationList) {
		this.blendInputCertificationList = blendInputCertificationList;
	}
	
	
	
}

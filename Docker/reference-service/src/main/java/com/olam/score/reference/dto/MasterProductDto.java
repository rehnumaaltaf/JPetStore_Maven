package com.olam.score.reference.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MasterProductDto {
	
	
	
	 private Integer pkFinCalProductId;
	 private Integer fkFinCalMappingId;
	 private Integer fkStatusId;
	 @JsonProperty("fkProdId")
	 private Integer fkProdId;
	 @JsonProperty("fkProdName")
	 private String fkProdName;
	 private String fkFinCalMappingName;
	 private String fkStatusName;
	public Integer getPkFinCalProductId() {
		return pkFinCalProductId;
	}
	public void setPkFinCalProductId(Integer pkFinCalProductId) {
		this.pkFinCalProductId = pkFinCalProductId;
	}
	public Integer getFkFinCalMappingId() {
		return fkFinCalMappingId;
	}
	public void setFkFinCalMappingId(Integer fkFinCalMappingId) {
		this.fkFinCalMappingId = fkFinCalMappingId;
	}
	public Integer getFkStatusId() {
		return fkStatusId;
	}
	public void setFkStatusId(Integer fkStatusId) {
		this.fkStatusId = fkStatusId;
	}
	public Integer getFkProdId() {
		return fkProdId;
	}
	public void setFkProdId(Integer fkProdId) {
		this.fkProdId = fkProdId;
	}
	public String getFkProdName() {
		return fkProdName;
	}
	public void setFkProdName(String fkProdName) {
		this.fkProdName = fkProdName;
	}
	public String getFkFinCalMappingName() {
		return fkFinCalMappingName;
	}
	public void setFkFinCalMappingName(String fkFinCalMappingName) {
		this.fkFinCalMappingName = fkFinCalMappingName;
	}
	public String getFkStatusName() {
		return fkStatusName;
	}
	public void setFkStatusName(String fkStatusName) {
		this.fkStatusName = fkStatusName;
	}
	 
	 
	

}

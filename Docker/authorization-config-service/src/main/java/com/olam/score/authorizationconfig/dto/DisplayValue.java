package com.olam.score.authorizationconfig.dto;

public class DisplayValue {
	
	private Integer id;
	private String value;
	private Integer mappingId;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Integer getMappingId() {
		return mappingId;
	}

	public void setMappingId(Integer mappingId) {
		this.mappingId = mappingId;
	}

	public DisplayValue(Integer id, String value) {
		super();
		this.id = id;
		this.value = value;
	}
	
	public DisplayValue(Integer id, String value , int mappingId){
		super();
		this.id = id;
		this.value = value;
		this.mappingId = mappingId;
	}
	
	public DisplayValue(){
		
	}
	
}

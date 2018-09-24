package com.olam.score.authorizationconfig.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Module {
	
	@JsonProperty("id")
	private int moduleId;
	
	@JsonProperty("value")
	private String moduleName;
	
	@JsonProperty("mappingId")
	private String moduleDesc;
	
	public Module(int moduleId, String moduleName, String moduleDesc) {
		super();
		this.moduleId = moduleId;
		this.moduleName = moduleName;
		this.moduleDesc = moduleDesc;
	}
	
	public Module(){
		
	}
	
	public int getModuleId() {
		return moduleId;
	}
	public void setModuleId(int moduleId) {
		this.moduleId = moduleId;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public String getModuleDesc() {
		return moduleDesc;
	}
	public void setModuleDesc(String moduleDesc) {
		this.moduleDesc = moduleDesc;
	}
	
}

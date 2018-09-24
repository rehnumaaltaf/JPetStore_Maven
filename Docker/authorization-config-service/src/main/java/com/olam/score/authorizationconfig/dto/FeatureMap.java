package com.olam.score.authorizationconfig.dto;

public class FeatureMap {
	
	private Feature feature;
	private Module module;
	
	public Feature getFeature() {
		return feature;
	}
	public void setFeature(Feature feature) {
		this.feature = feature;
	}
	public Module getModule() {
		return module;
	}
	public void setModule(Module module) {
		this.module = module;
	}
	
	public FeatureMap(int featureId, String featureName, String featureDesc, int roleFeatureMapping) {
		this.feature = new Feature(featureId,featureName,featureDesc,roleFeatureMapping);
	}
	
	public FeatureMap(int featureId, String featureName, String featureDesc, int roleFeatureMapping,int moduleId,
			String moduleName,String moduleDesc) {
		this.feature = new Feature(featureId,featureName,featureDesc,roleFeatureMapping);
		this.module = new Module(moduleId,moduleName,moduleDesc);
	}
	
	public FeatureMap(){
		
	}
	
	

}

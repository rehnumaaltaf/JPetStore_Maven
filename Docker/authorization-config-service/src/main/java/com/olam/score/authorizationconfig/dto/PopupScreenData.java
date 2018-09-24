package com.olam.score.authorizationconfig.dto;

public class PopupScreenData implements Comparable<PopupScreenData> {
	
	private String module;
	private String entity;
	private String feature;
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	public String getEntity() {
		return entity;
	}
	public void setEntity(String entity) {
		this.entity = entity;
	}
	public String getFeature() {
		return feature;
	}
	public void setFeature(String feature) {
		this.feature = feature;
	}
	public PopupScreenData(String module, String entity, String feature) {
		super();
		this.module = module;
		this.entity = entity;
		this.feature = feature;
	}
	@Override
	public int compareTo(PopupScreenData newData) {
		if(module.equals(newData.getModule())&&entity.equals(newData.getEntity())&&
				feature.equals(newData.getFeature()))
			return 0 ;
		else 
		    return 1;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((entity == null) ? 0 : entity.hashCode());
		result = prime * result + ((feature == null) ? 0 : feature.hashCode());
		result = prime * result + ((module == null) ? 0 : module.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PopupScreenData other = (PopupScreenData) obj;
		if (entity == null) {
			if (other.entity != null)
				return false;
		} else if (!entity.equals(other.entity))
			return false;
		if (feature == null) {
			if (other.feature != null)
				return false;
		} else if (!feature.equals(other.feature))
			return false;
		if (module == null) {
			if (other.module != null)
				return false;
		} else if (!module.equals(other.module))
			return false;
		return true;
	}
	
	
}

package com.olam.score.common.dto;

import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.olam.score.common.util.Mapping;
import com.olam.score.common.util.MethodTypeCd;

/**
 * 
 * @author sanjay.das.sda
 * @param <T>
 *
 */
public class Link<T> {
	private String rel;
	private String href;
	private String plainHref;
	private String method;
	private String relativePath;
	private String accessHandlr;

	/**
	 * 
	 * @param rel
	 * @param method
	 * @param href
	 */
	public Link(String rel, String method, String href) {
		this.rel = rel;
		this.href = href;
		this.plainHref = href;
		this.method = MethodTypeCd.valueOf(method.toUpperCase()).toString();
		this.relativePath = null;
		this.accessHandlr = null;
	}

	/**
	 * 
	 * @param ref
	 * @param method
	 * @param isSelf
	 */
	public Link(Class<T> ref, String method, Boolean isSelf) {

		this.rel = isSelf ? "self" : ref.getAnnotation(Mapping.class).featureName();
		this.href = ((RequestMapping) ref.getAnnotation(RequestMapping.class)).value()[0];
		this.plainHref = href;
		this.method = MethodTypeCd.valueOf(method.toUpperCase()).toString();
		this.relativePath = null;
		this.accessHandlr = null;
	}

	/**
	 * @param featureName
	 * @param ref
	 * @param method
	 * @param isSelf
	 */
	public Link(AuthorizationWrapper authorizationWrapper) {

		/*
		 * this.rel=isSelf?"self":ref.getAnnotation(Mapping.class).featureName()
		 * ;
		 */
		this.rel = authorizationWrapper.getFeatureName();
		this.href = (authorizationWrapper.getEntityUrl() != null)?authorizationWrapper.getEntityUrl():authorizationWrapper.getModuleUrl();
		this.plainHref = href;
		this.method = MethodTypeCd.valueOf(authorizationWrapper.getPermissionLevelname().toUpperCase()).toString();
		this.relativePath = null;
		this.accessHandlr = null;
	}

	/**
	 * 
	 * @param rel
	 * @param method
	 * @param href
	 * @param relativePath
	 * 
	 */
	public Link(String rel, String method, String href, String relativePath, String accessHandlr) {
		this.rel = rel;
		this.href = href;
		this.plainHref = href;
		this.method = MethodTypeCd.valueOf(method.toUpperCase()).toString();
		this.relativePath = relativePath;
		this.accessHandlr = accessHandlr;
	}

	/**
	 * 
	 * @param link
	 */
	public Link(Link<T> link) {
		this.rel = link.rel;
		this.href = link.href;
		this.plainHref = link.plainHref;
		this.method = link.method;
		this.relativePath = link.relativePath;
		this.accessHandlr = link.accessHandlr;
	}
	
	public Link(){
		
	}

	public String getRel() {
		return rel;
	}

	public void setRel(String rel) {
		this.rel = rel;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	@JsonIgnore
	public String getPlainHref() {
		return plainHref;
	}

	public void setPlainHref(String plainHref) {
		this.plainHref = plainHref;
	}

	@JsonIgnore
	public String getRelativePath() {
		return relativePath;
	}

	public void setRelativePath(String relativePath) {
		this.relativePath = relativePath;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	@JsonIgnore
	public String getAccessHandlr() {
		return accessHandlr;
	}

	public void setAccessHandlr(String accessHandlr) {
		this.accessHandlr = accessHandlr;
	}

}

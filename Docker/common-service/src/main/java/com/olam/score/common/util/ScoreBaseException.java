package com.olam.score.common.util;


import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;

public class ScoreBaseException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int resourceId;
	private HttpStatus httpStattus;
	private Map<Object, Object> map;
	private List<ScoreBaseExceptionResponse> errorBeans;

    public ScoreBaseException(int resourceId, String message, HttpStatus httpStattus) {
        super(message);
        this.setResourceId(resourceId);
        this.setHttpStattus(httpStattus);
    }
    public ScoreBaseException(Map<Object, Object> map, HttpStatus httpStattus) {
    	this.setMap(map);
        this.setHttpStattus(httpStattus);
    }
    public ScoreBaseException(List<ScoreBaseExceptionResponse> errorBeans, HttpStatus httpStattus) {
    	this.setErrorBeans(errorBeans);
        this.setHttpStattus(httpStattus);
    }

	public int getResourceId() {
		return resourceId;
	}

	public void setResourceId(int resourceId) {
		this.resourceId = resourceId;
	}

	public HttpStatus getHttpStattus() {
		return httpStattus;
	}

	public void setHttpStattus(HttpStatus httpStattus) {
		this.httpStattus = httpStattus;
	}
	public Map<Object, Object> getMap() {
		return map;
	}
	public void setMap(Map<Object, Object> map) {
		this.map = map;
	}
	public List<ScoreBaseExceptionResponse> getErrorBeans() {
		return errorBeans;
	}
	public void setErrorBeans(List<ScoreBaseExceptionResponse> errorBeans) {
		this.errorBeans = errorBeans;
	}
	
}

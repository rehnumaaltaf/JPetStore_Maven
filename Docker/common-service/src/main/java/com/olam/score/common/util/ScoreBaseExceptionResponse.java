package com.olam.score.common.util;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ScoreBaseExceptionResponse {
    private String errorType;
    private String errorMessage;
    private String errorCodes;
	private List<String> validationCodes;
    private List<ScoreBaseExceptionResponse> errorBeans;
    private Map<Object,Object> errorMessagesForIds;
    
    public ScoreBaseExceptionResponse() {
    }

    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

	public void setErrorCodes(String errorCodes) {
		this.errorCodes=errorCodes;
	}
	public String getCodes() {
		return errorCodes;
	}

	public Map<Object,Object> getErrorMessagesForIds() {
		return errorMessagesForIds;
	}

	public void setErrorMessagesForIds(Map<Object,Object> errorMessagesForIds) {
		this.errorMessagesForIds = errorMessagesForIds;
	}

	public List<ScoreBaseExceptionResponse> getErrorBeans() {
		return errorBeans;
	}

	public void setErrorBeans(List<ScoreBaseExceptionResponse> errorBeans) {
		this.errorBeans = errorBeans;
	}

	public List<String> getValidationCodes() {
		return validationCodes;
	}

	public void setValidationCodes(List<String> validationCodes) {
		this.validationCodes = validationCodes;
	}
}

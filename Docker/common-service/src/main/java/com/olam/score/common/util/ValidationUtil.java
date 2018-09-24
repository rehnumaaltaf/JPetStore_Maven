package com.olam.score.common.util;

import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;
public class ValidationUtil {
	 public static List<String> fromBindingErrors(Errors codes) {
	        List<String> validErrors = new ArrayList<String>();
	        for (ObjectError objectError : codes.getAllErrors()) {
	            validErrors.add(objectError.getDefaultMessage());
	        }
	        return validErrors;
	    }
}

package com.olam.score.common.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;  
import org.springframework.web.bind.annotation.ExceptionHandler;  
import org.springframework.web.bind.annotation.ResponseStatus;  
import org.springframework.web.bind.annotation.RestController; 

@ControllerAdvice
@RestController
public class ErrorHandeler {
	 @ResponseStatus(HttpStatus.BAD_REQUEST)  
	    @ExceptionHandler(value = NullPointerException.class)  
	    public ResponseEntity<ScoreBaseExceptionResponse> handleNullPointer(NullPointerException ex){  
		 ScoreBaseExceptionResponse response = new ScoreBaseExceptionResponse();
	        response.setErrorType("Null entry");
	        response.setErrorMessage(ex.getMessage());

	        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);	
	        }
	 @ExceptionHandler(ScoreBaseException.class)
	 public ResponseEntity<ScoreBaseExceptionResponse> resourceNotFound(ScoreBaseException ex) {
	        ScoreBaseExceptionResponse response = new ScoreBaseExceptionResponse();
	        if(ex.getMap()!=null){
	        	response.setErrorMessagesForIds(ex.getMap());
	        }else if(ex.getMessage()!=null){
	        response.setErrorMessage(ex.getMessage());
	        }else if(ex.getErrorBeans()!=null){
	        	response.setErrorBeans(ex.getErrorBeans());
	        }
	        return new ResponseEntity<>(response, ex.getHttpStattus());
	    }
	 @ExceptionHandler(MethodArgumentNotValidException.class)
	    public ResponseEntity<ScoreBaseExceptionResponse> invalidInput(MethodArgumentNotValidException ex) {
	        ScoreBaseExceptionResponse response = new ScoreBaseExceptionResponse();
	        BindingResult result = ex.getBindingResult();
	        response.setErrorType("Validation Error");
	        response.setErrorMessage("Invalid inputs.");
	        response.setValidationCodes(ValidationUtil.fromBindingErrors(result));
	        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	        
	    }
}

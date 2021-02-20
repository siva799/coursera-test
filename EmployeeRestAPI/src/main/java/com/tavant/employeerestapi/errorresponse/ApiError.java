package com.tavant.employeerestapi.errorresponse;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiError {

	@JsonFormat(shape = JsonFormat.Shape.STRING,
			pattern= "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime timeStamp;
	private String message;
	private String debugMessage;
	private HttpStatus httpStatus;
	private List<ApiSubError> subErrors;
	
	public ApiError(String message, String debugMessage, HttpStatus httpStatus) {
		super();
		this.message = message;
		this.debugMessage = debugMessage;
		this.httpStatus = httpStatus;
	}
	public ApiError(HttpStatus httpStatus,Throwable t) {
		super();
		this.debugMessage = t.getLocalizedMessage();
		this.httpStatus = httpStatus;
	}
	
	//to add the error object into the list
	public void addSubError(ApiSubError subError) {
		if(subErrors == null) {
			subErrors = new ArrayList<ApiSubError>();
		}
		subErrors.add(subError);
	}
//This is used to get the details for ur validation object
	private void addValidatonError(String object,String field,Object rejectedValue,String message) {
		addSubError(new ApiValidationError(object, field,rejectedValue,message));
	}
	
	//do we need to read the errors which are coming for our validation annotation
	private void addValidationError(FieldError fieldError) {
		
	}
	
	
}







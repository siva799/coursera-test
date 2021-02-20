package com.tavant.employeerestapi.exception;

public class EmployeeNotFoundException extends Exception{

	public EmployeeNotFoundException(String message) {
		// TODO Auto-generated constructor stub
	
		super(message);
		
	}
	
	@Override
	public String toString() {
		return super.toString() ;
	}
}

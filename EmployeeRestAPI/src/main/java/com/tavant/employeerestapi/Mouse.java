package com.tavant.employeerestapi;
 public class Mouse { 
	 public String name; 
	 public void run() { 
	 System.out.print("1"); 
	 try { 
		 System.out.print("2"); 
	 	 name.toString(); 
	 		System.out.print("3"); 
	 } catch (NullPointerException e) { 
		 System.out.print("4"); 
		 throw e;  } 
	 System.out.print("5"); 
	 } 
	 public static void main(String[] args) { 
		 Mouse jerry = new Mouse(); 
		 jerry.run(); 
		 System.out.print("6"); 
	 			} }
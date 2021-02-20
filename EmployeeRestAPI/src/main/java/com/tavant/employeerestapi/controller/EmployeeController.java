package com.tavant.employeerestapi.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tavant.employeerestapi.exception.EmployeeNotFoundException;
import com.tavant.employeerestapi.exception.EmptyData;
import com.tavant.employeerestapi.exception.NoDataFoundException;
import com.tavant.employeerestapi.model.Employee;
import com.tavant.employeerestapi.repository.EmployeeRepository;

//to perform the work of controller if we are using spring mvc
//then we will use @controller
//but here we are using rest then
//we should use @restController


//this annotation is introduced from spring mvc version 4.x
//before spring 3.0 and @controller
//in 4.0 they form @RestController
//when we will deal with any rest application there
// we have to send the response
// will be a json, html,xml,or any file
//wherever we have to share the data there we have to mark @ResponseEntity
//then what they have done instead of marking it on each and every method 
//they come up with a solution @RestController

@RestController
@RequestMapping("/api/employee")
// here we have the resources for employee
public class EmployeeController {
	
	@Autowired
	EmployeeRepository employeeRepository;


	
	//can we retrive the specific id record
	@GetMapping("/{id}")
	public ResponseEntity<?> getEmployeeById(@PathVariable("id")Integer id) throws EmployeeNotFoundException {
		Optional<Employee> optional = employeeRepository.findById(id);
		 
		if(optional.isPresent()) {
			return ResponseEntity.ok(optional.get());
		}  
		else {
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).
//					body(new EmployeeNotFoundException("Not found"));
			
			throw new EmployeeNotFoundException("no record found");
		}
		
	}
	
	@PostMapping  //transforming JSON java object
	//jackson api will take care implicity
public Employee addEmployee(@RequestBody Employee employee) throws EmptyData {
		
		//we can provide blank object....{}-blank object
		if(employee.getEmployeeNumber()==null) {
			throw new EmptyData("provide the employee ");
		}
		else {
			return employeeRepository.save(employee);
		}
		
	}
	
	
	@DeleteMapping("/{id}")
	public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Integer employeeId)
	  throws EmployeeNotFoundException {
	 Employee employee = employeeRepository.findById(employeeId)
	   .orElseThrow(() -> new EmployeeNotFoundException("Employee not found for this id :: " + employeeId));

	 employeeRepository.delete(employee);
	 Map<String, Boolean> response = new HashMap<>();
	 response.put("deleted", Boolean.TRUE);
	 return response;
	}
	@PutMapping("/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Integer employeeId,
	  @Valid @RequestBody Employee employeeDetails) throws EmployeeNotFoundException {
	 Employee employee = employeeRepository.findById(employeeId)
	 .orElseThrow(() -> new EmployeeNotFoundException("Employee not found for this id :: " + employeeId));

	 employee.setEmail(employeeDetails.getEmail());
	 employee.setLastName(employeeDetails.getLastName());
	 employee.setFirstName(employeeDetails.getFirstName());
	 final Employee updatedEmployee = employeeRepository.save(employee);
	 return ResponseEntity.ok(updatedEmployee);
	}
}






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
import org.springframework.web.bind.annotation.RestController;

import com.tavant.employeerestapi.exception.EmployeeNotFoundException;
import com.tavant.employeerestapi.exception.EmptyData;
import com.tavant.employeerestapi.exception.NoDataFoundException;
import com.tavant.employeerestapi.model.Customers;
import com.tavant.employeerestapi.model.Employee;
import com.tavant.employeerestapi.repository.CustomersRepository;

@RestController
@RequestMapping("/api/customers")
// here we have the resources for employee
public class CustomersController {
	
	@Autowired
	CustomersRepository customersRepository;
	@GetMapping
	public String getCustomers() {
		return "hello";
	}
	
	@GetMapping("/all")
	public List<Customers> getAllOders() throws NoDataFoundException {
//	return Optional.ofNullable(employeeRepository.
//			findAll()).orElseThrow(()
//					->new NoDataFoundException("record not found"));
      if(customersRepository.findAll().isEmpty())
      {
    	  throw new NoDataFoundException("no records found");
      }
      return customersRepository.findAll();
	}
	
	
	
	
	
	//can we retrive the specific id record
	@GetMapping("/{id}")
	public ResponseEntity<?> getCustomersById(@PathVariable("id")Integer id) throws EmployeeNotFoundException {
		Optional<Customers> optional = customersRepository.findById(id);
		 
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
public Customers addCustomers(@RequestBody Customers customers) throws EmptyData {
		
		//we can provide blank object....{}-blank object
		if(customers.getCustomerNumber()==null) {
			throw new EmptyData("provide the employee ");
		}
		else {
			return customersRepository.save(customers);
		}
		
	}

	@DeleteMapping("/{id}")
	public Map<String, Boolean> deleteCustomer(@PathVariable(value = "id") Integer customerId)
	  throws EmployeeNotFoundException {
	 Customers customer = customersRepository.findById(customerId)
	   .orElseThrow(() -> new EmployeeNotFoundException("Employee not found for this id :: " + customerId));

	 customersRepository.delete(customer);
	 Map<String, Boolean> response = new HashMap<>();
	 response.put("deleted", Boolean.TRUE);
	 return response;
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Customers> updateCustomers(@PathVariable(value = "id") Integer customerId,
	  @Valid @RequestBody Customers customerDetails) throws EmployeeNotFoundException {
	 Customers customer = customersRepository.findById(customerId)
	 .orElseThrow(() -> new EmployeeNotFoundException("Employee not found for this id :: " + customerId));

	 customer.setCustomerName(customerDetails.getCustomerName());
	 customer.setCustomerNumber(customerDetails.getCustomerNumber());
	
	 final Customers updatedCustomers = customersRepository.save(customer);
	 return ResponseEntity.ok(updatedCustomers);
	}
}
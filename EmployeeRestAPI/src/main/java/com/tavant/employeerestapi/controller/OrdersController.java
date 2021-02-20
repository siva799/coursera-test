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
import com.tavant.employeerestapi.model.Employee;
//import com.tavant.employeerestapi.model.Employee;
import com.tavant.employeerestapi.model.Orders;
import com.tavant.employeerestapi.repository.OrdersRepository;

@RestController
@RequestMapping("/api/orders")
// here we have the resources for employee
public class OrdersController {
	
	@Autowired
	OrdersRepository ordersRepository;
	@GetMapping
	public String getOrders() {
		return "hello";
	}
	
	@GetMapping("/all")
	public List<Orders> getAllOders() throws NoDataFoundException {
//	return Optional.ofNullable(employeeRepository.
//			findAll()).orElseThrow(()
//					->new NoDataFoundException("record not found"));
      if(ordersRepository.findAll().isEmpty())
      {
    	  throw new NoDataFoundException("no records found");
      }
      return ordersRepository.findAll();
	}
	
	
	
	
	
	//can we retrive the specific id record
	@GetMapping("/{id}")
	public ResponseEntity<?> getOrdersById(@PathVariable("id")Integer id) throws EmployeeNotFoundException {
		Optional<Orders> optional = ordersRepository.findById(id);
		 
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
public Orders addOrders(@RequestBody Orders orders) throws EmptyData {
		
		//we can provide blank object....{}-blank object
		if(orders.getOrderNumber()==null) {
			throw new EmptyData("provide the employee ");
		}
		else {
			return ordersRepository.save(orders);
		}
		
	}
	
	@DeleteMapping("/{id}")
	public Map<String, Boolean> deleteOrder(@PathVariable(value = "id") Integer orderId)
	  throws EmployeeNotFoundException {
	 Orders orders = ordersRepository.findById(orderId)
	   .orElseThrow(() -> new EmployeeNotFoundException("Employee not found for this id :: " + orderId));

	 ordersRepository.delete(orders);
	 Map<String, Boolean> response = new HashMap<>();
	 response.put("deleted", Boolean.TRUE);
	 return response;
	}
	@PutMapping("/{id}")
	public ResponseEntity<Orders> updateOrders(@PathVariable(value = "id") Integer orderId,
	  @Valid @RequestBody Orders orderDetails) throws EmployeeNotFoundException {
		Orders orders = ordersRepository.findById(orderId)
	 .orElseThrow(() -> new EmployeeNotFoundException("Employee not found for this id :: " + orderId));

		orders.setComments(orderDetails.getComments());
		
	 final Orders updatedOrders = ordersRepository.save(orders);
	 return ResponseEntity.ok(updatedOrders);
	}
	
}

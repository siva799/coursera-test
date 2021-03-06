package com.tavant.employeerestapi.controller;





import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.tavant.employeerestapi.model.OrderDetails;
import com.tavant.employeerestapi.repository.OrdersDetailsRepository;




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
@RequestMapping("/api/OrderDetails")
// here we have the resources for OrderDetails
public class OrderDetailsController {
	
	@Autowired
	OrdersDetailsRepository orderDetailsRepository;
	@GetMapping
	public String getOrderDetails() {
		return "hello";
	}
	
	@GetMapping("/all")
	public List<OrderDetails> getAllOrderDetailss() throws NoDataFoundException {
//	return Optional.ofNullable(OrderDetailsRepository.
//			findAll()).orElseThrow(()
//					->new NoDataFoundException("record not found"));
      if(orderDetailsRepository.findAll().isEmpty())
      {
    	  throw new NoDataFoundException("no records found");
      }
      return orderDetailsRepository.findAll();
	}
	
	
	
	
	
	//can we retrive the specific id record
	@GetMapping("/{id}")
	public ResponseEntity<?> getOrderDetailsById(@PathVariable("id")Integer id) throws EmployeeNotFoundException {
		Optional<OrderDetails> optional = orderDetailsRepository.findById(id);
		 
		if(optional.isPresent()) {
			return ResponseEntity.ok(optional.get());
		}  
		else {
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).
//					body(new OrderDetailsNotFoundException("Not found"));
			
			throw new EmployeeNotFoundException("no record found");
		}
		
	}
	
	@PostMapping  //transforming JSON java object
	//jackson api will take care implicity
public OrderDetails addOrderDetails(@RequestBody OrderDetails OrderDetails) throws EmptyData {
		
		//we can provide blank object....{}-blank object
		if(OrderDetails.getOrderNumber()==null) {
			throw new EmptyData("provide the OrderDetails ");
		}
		return OrderDetails;
		
		
	}
	
	@DeleteMapping("/{id}")
	public Map<String, Boolean> deleteOrderDetails(@PathVariable(value = "id") Integer OrderDetailsId)
	  throws EmployeeNotFoundException {
	 OrderDetails OrderDetails = orderDetailsRepository.findById(OrderDetailsId)
	   .orElseThrow(() -> new EmployeeNotFoundException("OrderDetails not found for this id :: " + OrderDetailsId));

	 orderDetailsRepository.delete(OrderDetails);
	 Map<String, Boolean> response = new HashMap<>();
	 response.put("deleted", Boolean.TRUE);
	 return response;
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<OrderDetails> updateOrderDetails(@PathVariable(value = "id") Integer OrderDetailsId,
	  @Valid @RequestBody OrderDetails OrderDetailsDetails) throws EmployeeNotFoundException {
	 OrderDetails orderDetails = orderDetailsRepository.findById(OrderDetailsId)
	 .orElseThrow(() -> new EmployeeNotFoundException("OrderDetails not found for this id :: " + OrderDetailsId));

	 orderDetails.setOrderLineNumber(OrderDetailsDetails.getOrderLineNumber());
	 orderDetails.setOrderNumber(OrderDetailsDetails.getOrderNumber());
	 orderDetails.setPriceEach(OrderDetailsDetails.getPriceEach());
	 final OrderDetails updatedOrderDetails = orderDetailsRepository.save(orderDetails);
	 return ResponseEntity.ok(updatedOrderDetails);
	}
	
}
	

































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
import com.tavant.employeerestapi.model.Payments;
import com.tavant.employeerestapi.repository.PaymentRepository;





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
@RequestMapping("/api/Payment")
// here we have the resources for Payment
public class PaymentController {
	
	@Autowired
	PaymentRepository paymentRepository;
	@GetMapping
	public String getPayment() {
		return "hello";
	}
	
	@GetMapping("/all")
	public List<Payments> getAllPayments() throws NoDataFoundException {
//	return Optional.ofNullable(PaymentRepository.
//			findAll()).orElseThrow(()
//					->new NoDataFoundException("record not found"));
      if(paymentRepository.findAll().isEmpty())
      {
    	  throw new NoDataFoundException("no records found");
      }
      return paymentRepository.findAll();
	}
	
	
	
	
	
	//can we retrive the specific id record
	@GetMapping("/{id}")
	public ResponseEntity<?> getPaymentById(@PathVariable("id")Integer id) throws EmployeeNotFoundException {
		Optional<Payments> optional = paymentRepository.findById(id);
		 
		if(optional.isPresent()) {
			return ResponseEntity.ok(optional.get());
		}  
		else {
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).
//					body(new PaymentNotFoundException("Not found"));
			
			throw new EmployeeNotFoundException("no record found");
		}
		
	}
	
	@PostMapping  //transforming JSON java object
	//jackson api will take care implicity
public Payments addPayment(@RequestBody Payments Payment) throws EmptyData {
		
		//we can provide blank object....{}-blank object
		if(Payment.getCheckNumber()==null) {
			throw new EmptyData("provide the Payment ");
		}
		else {
			return paymentRepository.save(Payment);
		}
		
	}
	@DeleteMapping("/{id}")
	public Map<String, Boolean> deletePayments(@PathVariable(value = "id") Integer PaymentsId)
	  throws EmployeeNotFoundException {
	 Payments payments = paymentRepository.findById(PaymentsId)
	   .orElseThrow(() -> new EmployeeNotFoundException("Payments not found for this id :: " + PaymentsId));

	 paymentRepository.delete(payments);
	 Map<String, Boolean> response = new HashMap<>();
	 response.put("deleted", Boolean.TRUE);
	 return response;
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<Payments> updatePayments(@PathVariable(value = "id") Integer PaymentsId,
	  @Valid @RequestBody Payments PaymentsDetails) throws EmployeeNotFoundException {
	 Payments Payments = paymentRepository.findById(PaymentsId)
	 .orElseThrow(() -> new EmployeeNotFoundException("Payments not found for this id :: " + PaymentsId));

	 Payments.setAmount(PaymentsDetails.getAmount());
	 Payments.setCheckNumber(PaymentsDetails.getCheckNumber());
	 Payments.setCustomerNumber(PaymentsDetails.getCustomerNumber());
	 final Payments updatedPayments = paymentRepository.save(Payments);
	 return ResponseEntity.ok(updatedPayments);
	}
	
}




































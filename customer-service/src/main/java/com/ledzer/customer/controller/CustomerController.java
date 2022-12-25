package com.ledzer.customer.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.ledzer.customer.common.exceptions.ResourceNotFoundException;
import com.ledzer.customer.common.messages.ApiResponse;
import com.ledzer.customer.dto.CustomerDTO;
import com.ledzer.customer.entity.Customer;
import com.ledzer.customer.service.CustomerService;
@RestController
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/")
    public ResponseEntity<List<Customer>> getAllCustomers() throws ResourceNotFoundException {
        return new ResponseEntity<List<Customer>>((List<Customer>) customerService.findAll(), HttpStatus.OK);
    }
	
	@GetMapping("/{id}")
	public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long id) {
		CustomerDTO customerDTO = customerService.findById(id);
		return new ResponseEntity<>(customerDTO, HttpStatus.OK);
	}
	
	@PostMapping("/")
	public ResponseEntity<ApiResponse> create(@RequestBody Customer customer) {
		ApiResponse response =  customerService.createCustomer(customer);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}
	
	@PutMapping("/{customerId}")
    public ResponseEntity<ApiResponse> updateCustomerById(@PathVariable Long customerId, @Valid @RequestBody Customer customer){
		ApiResponse response =  customerService.updateCustomer(customerId,customer);
		return new ResponseEntity<>(response, HttpStatus.OK);
    }
	
	@DeleteMapping("/{customerId}")
    public ResponseEntity<CustomerDTO> deleteCustomerById(@PathVariable Long customerId){
        CustomerDTO customerDTO =  customerService.deleteCustomer(customerId);
		return new ResponseEntity<>(customerDTO, HttpStatus.OK);
    }
	
	@GetMapping("/list")
    public List<CustomerDTO> getCustomersList() {
        return customerService.getCustomersList();
    }
	
}

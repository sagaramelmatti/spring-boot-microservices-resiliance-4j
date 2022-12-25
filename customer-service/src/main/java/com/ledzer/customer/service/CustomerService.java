package com.ledzer.customer.service;

import java.util.List;

import com.ledzer.customer.common.messages.ApiResponse;
import com.ledzer.customer.dto.CustomerDTO;
import com.ledzer.customer.entity.Customer;

public interface CustomerService {
	
	public CustomerDTO findById(Long id);
	
	public List<Customer> findAll();
	
	public ApiResponse createCustomer(Customer customer);
	
	public ApiResponse updateCustomer(Long customerId,  Customer customer);
	
	public CustomerDTO deleteCustomer(Long customerId);

	public List<CustomerDTO> getCustomersList();

}

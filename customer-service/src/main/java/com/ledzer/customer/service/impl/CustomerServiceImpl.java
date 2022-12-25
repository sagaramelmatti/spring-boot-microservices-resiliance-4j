package com.ledzer.customer.service.impl;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ledzer.customer.common.exceptions.ResourceNotFoundException;
import com.ledzer.customer.common.messages.ApiResponse;
import com.ledzer.customer.dto.CustomerDTO;
import com.ledzer.customer.entity.Customer;
import com.ledzer.customer.repository.CustomerRepository;
import com.ledzer.customer.service.CustomerService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class CustomerServiceImpl implements CustomerService {

	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private MessageSource messageSource;

	public CustomerDTO findById(Long id) {
		Customer customer = customerRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Not found Customer with id = " + id));

		CustomerDTO customerDTO = convertToDto(customer);
		return customerDTO;

	}

	public List<Customer> findAll() {
		return customerRepository.findAll();
	}

	public ApiResponse createCustomer(Customer customer) {

		customerRepository.save(customer);
		return new ApiResponse("Customer Saved succesfully ", HttpStatus.CREATED.value());

	}

	public ApiResponse updateCustomer(Long id, Customer customer) {
		Customer customerDB = customerRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Not found Customer with id = " + id));
		// If user exists then updated
		customerRepository.save(customer);
		return new ApiResponse("Customer Updated succesfully ", HttpStatus.CREATED.value());

	}

	public CustomerDTO deleteCustomer(Long id) {

		Customer customer = customerRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Not found Customer with id = " + id));

		customerRepository.deleteById(id);
		CustomerDTO customerDTO = convertToDto(customer);
		return customerDTO;
	}

	private CustomerDTO convertToDto(Customer customer) {
		CustomerDTO customerDTO = modelMapper.map(customer, CustomerDTO.class);
		customerDTO.setId(customer.getId().toString());
		return customerDTO;
	}

	@Override
	public List<CustomerDTO> getCustomersList() {
		List<Customer> customers = customerRepository.findAll();
		return customers.stream().map(customer -> new CustomerDTO(Long.toString(customer.getId()), customer.getName()))
				.collect(Collectors.toList());

	}

}

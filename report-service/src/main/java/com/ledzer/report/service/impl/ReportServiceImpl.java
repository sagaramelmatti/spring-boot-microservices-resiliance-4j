package com.ledzer.report.service.impl;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.stereotype.Service;

import com.ledzer.common.model.Customer;
import com.ledzer.common.model.Invoice;
import com.ledzer.report.dto.CustomerReportDTO;
import com.ledzer.report.dto.CustomerSalesReportDTO;
import com.ledzer.report.service.ReportService;

@Service
public class ReportServiceImpl implements ReportService {

	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	@Autowired
	private ModelMapper modelMapper;

	@Value("${masterAPI}")
	private String masterAPIApi;

	@Value("${customerAPI}")
	private String customerAPI;

	@Value("${invoiceAPI}")
	private String invoiceAPI;

	@Autowired
	private OAuth2RestOperations restTemplate;

	public List<CustomerReportDTO> findAllCustomers() {
		List<CustomerReportDTO> customerReportDTOList = null;
		try {
			ResponseEntity<List<Customer>> responseEntity = restTemplate.exchange(customerAPI + "/", HttpMethod.GET,
					null, new ParameterizedTypeReference<List<Customer>>() {
					});
			List<Customer> customerList = responseEntity.getBody();

			customerReportDTOList = customerList.stream().map(this::convertToDto).collect(Collectors.toList());

		} catch (Exception e) {
			System.out.println(e);
		}

		return customerReportDTOList;
	}

	private CustomerReportDTO convertToDto(Customer customer) {
		CustomerReportDTO customerReportDTO = modelMapper.map(customer, CustomerReportDTO.class);
		customerReportDTO.setId(customer.getId().toString());
		return customerReportDTO;
	}

	@Override
	public List<CustomerSalesReportDTO> findCustomerSalesDetails(Long customerId) {
		List<CustomerSalesReportDTO> customerReportDTOList = null;
		try {
			ResponseEntity<List<Invoice>> responseEntity = restTemplate.exchange(invoiceAPI + "/customer/" + customerId,
					HttpMethod.GET, null, new ParameterizedTypeReference<List<Invoice>>() {
					});
			List<Invoice> invoiceList = responseEntity.getBody();

			customerReportDTOList = invoiceList.stream().map(this::convertToSalesDto).collect(Collectors.toList());

		} catch (Exception e) {
			System.out.println(e);
		}

		return customerReportDTOList;
	}

	private CustomerSalesReportDTO convertToSalesDto(Invoice invoice) {
		CustomerSalesReportDTO customerSalesReportDTO = modelMapper.map(invoice, CustomerSalesReportDTO.class);
		return customerSalesReportDTO;
	}
}

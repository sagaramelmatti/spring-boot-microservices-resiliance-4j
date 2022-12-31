package com.ledzer.report.service;

import java.util.List;

import com.ledzer.report.dto.CustomerReportDTO;
import com.ledzer.report.dto.CustomerSalesReportDTO;

public interface ReportService {
	
	public List<CustomerReportDTO> findAllCustomers();

	public List<CustomerSalesReportDTO> findCustomerSalesDetails(Long customerId);
	
}

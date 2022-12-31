package com.ledzer.report.controller;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import com.ledzer.report.common.exceptions.ResourceNotFoundException;
import com.ledzer.report.dto.CustomerReportDTO;
import com.ledzer.report.dto.CustomerSalesReportDTO;
import com.ledzer.report.service.ReportService;
import com.ledzer.report.util.PDFGenerator;

@RestController
public class ReportController {

	@Autowired
	private ReportService reportService;

	@GetMapping(value = "/customers", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<InputStreamResource> getCustomerListReport() throws ResourceNotFoundException {

		List<CustomerReportDTO> customerReportDTOList = (List<CustomerReportDTO>) reportService.findAllCustomers();

		ByteArrayInputStream bis = PDFGenerator.employeePDFReport(customerReportDTOList);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=customers.pdf");

		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(bis));

	}

	@GetMapping(value = "/customers/sales/{customerId}", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<InputStreamResource> getCustomerWiseSalesReport(@PathVariable Long customerId)
			throws ResourceNotFoundException {

		List<CustomerSalesReportDTO> customerReportDTOList = (List<CustomerSalesReportDTO>) reportService
				.findCustomerSalesDetails(customerId);

		ByteArrayInputStream bis = PDFGenerator.customerSalesPDFReport(customerReportDTOList);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=customers.pdf");

		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(bis));

	}
}

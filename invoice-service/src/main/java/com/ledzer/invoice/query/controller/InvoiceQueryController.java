package com.ledzer.invoice.query.controller;
import java.util.List;

import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ledzer.invoice.dto.InvoiceDTO;
import com.ledzer.invoice.query.query.GetInvoiceQuery;
import com.ledzer.invoice.query.service.InvoiceQueryService;

@RestController
@RequestMapping("/invoices")
public class InvoiceQueryController {
	
    @Autowired
    QueryGateway queryGateway;
    
    @Autowired
    InvoiceQueryService invoiceService;

    @GetMapping("/")
    public ResponseEntity<List<InvoiceDTO>> getAllInvoices() {
    	
    	List<InvoiceDTO> invoiceDTOList = this.invoiceService.findAllInvoices();
		return new ResponseEntity<>(invoiceDTOList, HttpStatus.OK);
		
		
    	/*
    	GetInvoiceQuery invoiceQuery = new GetInvoiceQuery();
    	
        List<InvoiceDTO> list = queryGateway.query
                (invoiceQuery,
                ResponseTypes.multipleInstancesOf(InvoiceDTO.class))
                .join();
		return new ResponseEntity<List<InvoiceDTO>>(list, HttpStatus.OK);
		*/
    }
    
    @GetMapping("/{invoiceId}")
    public ResponseEntity<InvoiceDTO> findById(@PathVariable("invoiceId") String invoiceId) {
		InvoiceDTO invoiceDTO = this.invoiceService.findById(invoiceId);
		return new ResponseEntity<>(invoiceDTO, HttpStatus.OK);
    }
    
    
	@GetMapping("/customer/{customerId}")
	public ResponseEntity<List<InvoiceDTO>> getInvoiceByCustomerId(@PathVariable String customerId) {
		List<InvoiceDTO> invoiceDTOList = invoiceService.findByCustomerId(customerId);
		return new ResponseEntity<>(invoiceDTOList, HttpStatus.OK);
	}
}

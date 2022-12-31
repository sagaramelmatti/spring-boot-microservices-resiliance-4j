package com.ledzer.invoice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ledzer.invoice.dto.InvoiceDTO;
import com.ledzer.invoice.service.InvoiceService;

@RestController
public class InvoiceController {

	@Autowired
	private InvoiceService invoiceService;
	
	public static final String SUCCESS = "success";

	@GetMapping("/")
	public ResponseEntity<List<InvoiceDTO>> getAllInvoices() {
		List<InvoiceDTO> list = invoiceService.getInvoiceList();
		return new ResponseEntity<List<InvoiceDTO>>(list, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<InvoiceDTO> getInvoiceById(@PathVariable Long id) {
		InvoiceDTO invoiceDTO = invoiceService.findById(id);
		return new ResponseEntity<>(invoiceDTO, HttpStatus.OK);
	}

	/*
	@PostMapping
	public ResponseEntity<Invoice> create(@RequestBody InvoiceDTO invoiceDTO) {
		Invoice invoice =  invoiceService.createInvoice(invoiceDTO);
		return new ResponseEntity<>(invoice, HttpStatus.CREATED);

	}

	@PutMapping("/{invoiceId}")
	public Invoice updateInvoiceById(@PathVariable Long invoiceId, @RequestBody Invoice invoice) {
		return invoiceService.updateInvoice(invoiceId, invoice);
	}
	
	*/

	@DeleteMapping("/{invoiceId}")
	public void deleteInvoiceById(@PathVariable Long invoiceId) {
		invoiceService.deleteInvoice(invoiceId);
	}

	@GetMapping("/invoiceNoSeq")
	public Long getInvoiceNoSeq() {
		return invoiceService.getInvoiceNoSeq();
	}

	@GetMapping("/invoiceId")
	public Long getInvoiceId() {
		return invoiceService.getInvoiceId();
	}
}

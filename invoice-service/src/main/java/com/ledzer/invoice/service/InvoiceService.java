package com.ledzer.invoice.service;

import java.util.List;

import com.ledzer.invoice.dto.Business;
import com.ledzer.invoice.dto.Customer;
import com.ledzer.invoice.dto.InvoiceDTO;
import com.ledzer.invoice.entity.Invoice;


public interface InvoiceService {
	
	public InvoiceDTO findById(Long id);
	
	public List<InvoiceDTO> getInvoiceList();
	
	public Invoice createInvoice(InvoiceDTO invoiceDTO);
	
	public Invoice updateInvoice(Long invoiceId,  Invoice invoice);
	
	public void deleteInvoice(Long invoiceId);

	public Long getInvoiceNoSeq();

	public Long getInvoiceId();
	
	public Business getBusiness(Long businessId);
	
	public Customer getCustomer(Long customerId);

}
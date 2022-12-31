package com.ledzer.invoice.query.projection;

import java.util.List;

import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ledzer.invoice.entity.Invoice;
import com.ledzer.invoice.query.query.FindCustomerInvoiceQuery;
import com.ledzer.invoice.query.query.FindInvoiceQuery;
import com.ledzer.invoice.query.query.GetInvoiceQuery;
import com.ledzer.invoice.repository.InvoiceRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class InvoiceProjection {

	@Autowired
	private InvoiceRepository invoiceRepository;

	@QueryHandler
	public Invoice handle(FindInvoiceQuery query) {
		log.debug("Handling FindBankAccountQuery query: {}", query);
		return invoiceRepository.findByUid(query.getUid());
	}

	@QueryHandler
	public List<Invoice> handle(GetInvoiceQuery query) {
		log.debug("Handling GetInvoiceQuery query: {}", query);
		return invoiceRepository.findAll();
	}

	@QueryHandler
	public List<Invoice> handle(FindCustomerInvoiceQuery query) {
		log.debug("Handling FindBankAccountQuery query: {}", query);
		return invoiceRepository.findByCustomerId(Long.parseLong(query.getCustomerId()));
	}

}

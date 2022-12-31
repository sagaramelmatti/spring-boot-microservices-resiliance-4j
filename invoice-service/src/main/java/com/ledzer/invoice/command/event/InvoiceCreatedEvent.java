package com.ledzer.invoice.command.event;

import java.util.Date;
import java.util.List;

import com.ledzer.invoice.command.dto.InvoiceDetailsCommandDTO;
import com.ledzer.invoice.dto.CustomerPaymentDetailDTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InvoiceCreatedEvent {
	

	private String uid;
	private final String name;
	private final Date invoiceDate;
	private final Integer invoiceNumber;
	private final String invoiceString;
	private final Integer invoiceYear;
	private final String invoiceType;
	private final Double itemTotal;
	private final Double itemTax;
	private final Double billTotal;
	private final Double roundValue;
	private final Double invoiceTotal;
	private final String invoiceTotalWord;
	private final Double amountPaid;
	private final Double amountDue;
	private final Long businessId;
	private final Long customerId;
	private final CustomerPaymentDetailDTO  customerPaymentDetailDTO;
	private final List<InvoiceDetailsCommandDTO>  invoiceDetailsCommandDTO;
	

}

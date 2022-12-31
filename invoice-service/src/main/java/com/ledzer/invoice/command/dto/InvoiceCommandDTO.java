package com.ledzer.invoice.command.dto;
import java.util.List;

import com.ledzer.invoice.dto.CustomerPaymentDetailDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class InvoiceCommandDTO {
	
	private String id;
	private String invoiceId;
	private String name;
	private String invoiceDate;
	private String invoiceNumber;
	private String invoiceString;
	private String invoiceYear;
	private String invoiceType;
	private String itemTotal;
	private String itemTax;
	private String billTotal;
	private String roundValue;
	private String invoiceTotal;
	private String invoiceTotalWord;
	private String amountPaid;
	private String amountDue;
	private String businessId;
	private String customerId;
	private CustomerPaymentDetailDTO  customerPaymentDetailDTO;
	private List<InvoiceDetailsCommandDTO>  invoiceDetailsCommandDTO;
	
}

package com.ledzer.invoice.command.command;

import java.util.Date;
import java.util.List;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import com.ledzer.invoice.command.dto.InvoiceDetailsCommandDTO;
import com.ledzer.invoice.dto.CustomerPaymentDetailDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateInvoiceCommand {
	
	@TargetAggregateIdentifier
    private String uid;
	private String name;
	private Date invoiceDate;
	private Integer invoiceNumber;
	private String invoiceString;
	private Integer invoiceYear;
	private String invoiceType;
	private Double itemTotal;
	private Double itemTax;
	private Double billTotal;
	private Double roundValue;
	private Double invoiceTotal;
	private String invoiceTotalWord;
	private Double amountPaid;
	private Double amountDue;
	private Long businessId;
	private Long customerId;
	private CustomerPaymentDetailDTO  customerPaymentDetailDTO;
	private List<InvoiceDetailsCommandDTO>  invoiceDetailsCommandDTO;
	
}

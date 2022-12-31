package com.ledzer.invoice.command.aggregate;

import java.util.Date;
import java.util.List;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import com.ledzer.invoice.command.command.CreateInvoiceCommand;
import com.ledzer.invoice.command.dto.InvoiceDetailsCommandDTO;
import com.ledzer.invoice.command.event.InvoiceCreatedEvent;
import com.ledzer.invoice.dto.CustomerPaymentDetailDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Aggregate
public class InvoiceAggregate {

	@AggregateIdentifier
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
	private CustomerPaymentDetailDTO customerPaymentDetailDTO;
	private List<InvoiceDetailsCommandDTO> invoiceDetailsCommandDTO;

	@CommandHandler
	public InvoiceAggregate(CreateInvoiceCommand command) {

		AggregateLifecycle.apply(new InvoiceCreatedEvent(
				command.getUid(), 
				command.getName(), 
				command.getInvoiceDate(),
				command.getInvoiceNumber(), 
				command.getInvoiceString(), 
				command.getInvoiceYear(),
				command.getInvoiceType(), 
				command.getItemTotal(), 
				command.getItemTax(), 
				command.getBillTotal(),
				command.getRoundValue(), 
				command.getInvoiceTotal(), 
				command.getInvoiceTotalWord(),
				command.getAmountPaid(), 
				command.getAmountDue(), 
				command.getBusinessId(), 
				command.getCustomerId(),
				command.getCustomerPaymentDetailDTO(), 
				command.getInvoiceDetailsCommandDTO()

		));
	}

	@EventSourcingHandler
	public void on(InvoiceCreatedEvent event) {
		this.uid = event.getUid();
		this.name = event.getName();
		this.invoiceDate = event.getInvoiceDate();
		this.invoiceNumber = event.getInvoiceNumber();
		this.invoiceString = event.getInvoiceString();
		this.invoiceYear = event.getInvoiceYear();
		this.invoiceType = event.getInvoiceType();
		this.itemTotal = event.getItemTotal();
		this.itemTax = event.getItemTax();
		this.billTotal = event.getBillTotal();
		this.roundValue = event.getRoundValue();
		this.invoiceTotal = event.getInvoiceTotal();
		this.invoiceTotalWord = event.getInvoiceTotalWord();
		this.amountPaid = event.getAmountPaid();
		this.amountDue = event.getAmountDue();
		this.businessId = event.getBusinessId();
		this.customerId = event.getCustomerId();
		this.customerPaymentDetailDTO = event.getCustomerPaymentDetailDTO();
		this.invoiceDetailsCommandDTO = event.getInvoiceDetailsCommandDTO();

	}

}

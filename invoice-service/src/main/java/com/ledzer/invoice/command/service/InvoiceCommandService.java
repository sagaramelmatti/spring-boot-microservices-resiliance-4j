package com.ledzer.invoice.command.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import com.ledzer.invoice.command.command.CreateInvoiceCommand;
import com.ledzer.invoice.command.dto.InvoiceCommandDTO;
import com.ledzer.invoice.command.dto.InvoiceDetailsCommandDTO;
import com.ledzer.invoice.entity.Invoice;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class InvoiceCommandService {

	private final CommandGateway commandGateway;

	public CompletableFuture<Invoice> createInvoice(InvoiceCommandDTO invoiceCommandDTO) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		
		Date invoiceDate = null;
		try {
			invoiceDate = sdf.parse(invoiceCommandDTO.getInvoiceDate());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return this.commandGateway
			.send(new CreateInvoiceCommand(
						UUID.randomUUID().toString(),
						invoiceCommandDTO.getName(), 
						invoiceDate,
						Integer.parseInt(invoiceCommandDTO.getInvoiceNumber()), 
						invoiceCommandDTO.getInvoiceString(),
						Integer.parseInt(invoiceCommandDTO.getInvoiceYear()), 
						invoiceCommandDTO.getInvoiceType(),
						Double.parseDouble(invoiceCommandDTO.getItemTotal()), 
						Double.parseDouble(invoiceCommandDTO.getItemTax()),
						Double.parseDouble(invoiceCommandDTO.getBillTotal()), 
						Double.parseDouble(invoiceCommandDTO.getRoundValue()),
						Double.parseDouble(invoiceCommandDTO.getInvoiceTotal()), 
						invoiceCommandDTO.getInvoiceTotalWord(),
						Double.parseDouble(invoiceCommandDTO.getAmountPaid()), 
						Double.parseDouble(invoiceCommandDTO.getAmountDue()),
						Long.parseLong(invoiceCommandDTO.getBusinessId()), 
						Long.parseLong(invoiceCommandDTO.getCustomerId()),
						invoiceCommandDTO.getCustomerPaymentDetailDTO(), 
						invoiceCommandDTO.getInvoiceDetailsCommandDTO()
			));
	}
}

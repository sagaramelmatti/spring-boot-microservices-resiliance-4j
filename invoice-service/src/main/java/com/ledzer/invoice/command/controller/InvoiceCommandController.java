package com.ledzer.invoice.command.controller;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ledzer.invoice.command.dto.InvoiceCommandDTO;
import com.ledzer.invoice.command.service.InvoiceCommandService;
import com.ledzer.invoice.entity.Invoice;

@RestController
public class InvoiceCommandController {

    @Autowired
    private InvoiceCommandService invoiceCommandService;
    
    @PostMapping
    public CompletableFuture<Invoice> createAccount(@RequestBody InvoiceCommandDTO invoiceCommandDTO) {
        return this.invoiceCommandService.createInvoice(invoiceCommandDTO);
    }
}
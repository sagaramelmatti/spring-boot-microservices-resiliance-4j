package com.ledzer.invoice.query.query;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FindCustomerInvoiceQuery {
    private String customerId;
}
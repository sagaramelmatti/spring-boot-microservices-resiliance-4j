package com.ledzer.report.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CustomerSalesReportDTO {
	
	private String id;
	private String name;
	private String invoiceDate;
	private String invoiceString;
	private String itemTotal;
	private String itemTax;
	private String billTotal;
	private String roundValue;
	private String invoiceTotal;
	private String amountPaid;
	private String amountDue;
}

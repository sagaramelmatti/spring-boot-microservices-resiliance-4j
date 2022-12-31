package com.ledzer.invoice.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class InvoiceDTO {
	
	private String id;
	private String name;
	private String invoiceDate;
	private String invoiceNumber;
	private String invoiceString;
	private String invoiceYear;
	private String invoiceType;
	private String customerId;
    private String customerName;
	private String itemTotal;
	private String itemTax;
	private String billTotal;
	private String roundValue;
	private String invoiceTotal;
	private String invoiceTotalWord;
	private String amountPaid;
	private String amountDue;
	private String businessId;
	private String businessName;
	private String businessAddress;
	private String businessCity;
	private String businessStateId;
	private String businessStateName;
	private String businessContactNo;
	private String businessGstno;
	
	private String customerAddress;
	private String customerEmail;
	private String customerCity;
	private String customerStateId;
	private String customerStateName;
	private String customerContactNo;
	private String customerGstno;
	private String customerTotalOutBal;
	private CustomerPaymentDetailDTO  customerPaymentDetailDTO;
	private List<InvoiceDetailsDTO>  invoiceDetailsDTOList;
	
}

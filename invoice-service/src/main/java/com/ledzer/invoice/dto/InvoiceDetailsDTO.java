package com.ledzer.invoice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class InvoiceDetailsDTO {

	private String id;
	private String hsnCode;
	private String invoiceId;
	private String invoiceName;
	private String productId;
	private String productName;
	private String rate;
	private String quantity;
	private String taxableAmount;
	private String gst;
	private String gstAmt;
	private String sgstPer;
	private String sgstAmt;
	private String cgstPer;
	private String cgstAmt;
	private String igstPer;
	private String igstAmt;
}
